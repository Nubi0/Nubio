import React, { useEffect, useState, useCallback, useReducer } from "react";
import { Client } from "@stomp/stompjs";
import { useParams, useLocation } from "react-router-dom";
import axios from "axios";
const actionTypes = {
  ADD_MESSAGE: "ADD_MESSAGE",
  RESET_MESSAGES: "RESET_MESSAGES",
};
interface Message {
  nickname: string;
  content: string;
}
const initialMessageState: Message[] = [];

interface MessageAction {
  type: string;
  payload?: Message;
}

function messageReducer(state: Message[], action: MessageAction): Message[] {
  switch (action.type) {
    case actionTypes.ADD_MESSAGE:
      return action.payload ? [...state, action.payload] : state;
    case actionTypes.RESET_MESSAGES:
      return initialMessageState;
    default:
      return state;
  }
}

const ChatRoom = () => {
  const { roomId } = useParams<{ roomId: string }>();
  const [client, setClient] = useState<Client | null>(null);
  const [newMessage, setNewMessage] = useState<string>("");
  const [state, dispatch] = useReducer(messageReducer, initialMessageState);
  const location = useLocation();
  const { nickname } = location.state || {};
  console.log("Nickname from state:", nickname);

  useEffect(() => {
    const stompClient = new Client({
      brokerURL:
        "ws://ec2-13-124-94-38.ap-northeast-2.compute.amazonaws.com:8083/ws-chat",
      debug: function (str) {
        console.log(str);
      },
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
    });

    stompClient.onConnect = (frame) => {
      stompClient.subscribe(`/chatting/topic/${roomId}`, (message) => {
        // JSON 문자열을 객체로 파싱합니다.
        const parsedMessage = JSON.parse(message.body);

        dispatch({
          type: actionTypes.ADD_MESSAGE,
          payload: {
            nickname: parsedMessage.nickname,
            content: parsedMessage.content,
          },
        });
      });

      stompClient.subscribe(`/chatting/topic/room/${roomId}`, (message) => {
        const parsedMessage = JSON.parse(message.body);
        dispatch({
          type: actionTypes.ADD_MESSAGE,
          payload: parsedMessage,
        });
      });
    };
    stompClient.onStompError = function (frame) {
      console.log("Broker reported error: " + frame.headers["message"]);
      console.log("Additional details: " + frame.body);
    };

    stompClient.activate();
    setClient(stompClient);

    return () => {
      stompClient.deactivate();
    };
  }, [roomId]);

  const sendMessage = useCallback(async () => {
    if (client && client.connected && newMessage.trim() !== "") {
      const messageToSend = JSON.stringify({
        nickname,
        content: newMessage,
      });
      console.log(nickname);
      client.publish({
        destination: `/chatting/topic/${roomId}`,
        body: newMessage,
      });

      if (roomId) {
        const formData = new FormData();
        const chatting = {
          message_type: "TEXT",
          room_id: roomId,
          content: newMessage,
        };
        formData.append(
          "chatting",
          new Blob([JSON.stringify(chatting)], { type: "application/json" })
        );

        try {
          const response = await axios.post(
            `${process.env.REACT_APP_SERVER_URL}/chatting/v1/chatting/message`,
            formData,
            {
              headers: {
                "Content-Type": "multipart/form-data",
              },
            }
          );

          if (response.status === 200) {
            console.log("Message sent to the backend:", response.data);
          } else {
            console.error(
              "Failed to send message to the backend:",
              response.data
            );
          }
        } catch (error) {
          console.error("Error sending message to the backend:", error);
        }

        setNewMessage("");
      } else {
        console.error("Room ID is undefined, cannot send message.");
      }
    } else {
      alert("연결이 끊어졌거나 메시지를 보낼 수 없습니다.");
    }
  }, [client, newMessage, roomId, nickname]);

  return (
    <div>
      <div>
        {state.map((msg: Message, index: number) => (
          <div key={index}>
            <strong>{msg.nickname}: </strong>
            {msg.content}
          </div>
        ))}
      </div>
      <input
        type="text"
        value={newMessage}
        onChange={(e) => setNewMessage(e.target.value)}
      />
      <button onClick={sendMessage}>Send</button>
    </div>
  );
};

export default ChatRoom;
