import React, { useEffect, useState, useCallback, useReducer } from "react";
import { Client } from "@stomp/stompjs";
import { useParams, useLocation } from "react-router-dom";
import axios from "axios";
import Announcement from "@/components/chatPage/Announcement";
import ChatHeader from "@/components/chatPage/ChatHeader";
import {
  MessageList,
  MessageItem,
  InputField,
  SendButton,
  ChatInputArea,
  ChatPageLayout,
} from "../styles/SChatroomPage";
const actionTypes = {
  ADD_MESSAGE: "ADD_MESSAGE",
  SET_MESSAGES: "SET_MESSAGES",
  RESET_MESSAGES: "RESET_MESSAGES",
};

interface Message {
  nickname: string;
  content: string;
}

const initialMessageState: Message[] = [];

interface AddMessageAction {
  type: typeof actionTypes.ADD_MESSAGE;
  payload: Message;
}

interface SetMessagesAction {
  type: typeof actionTypes.SET_MESSAGES;
  payload: Message[];
}

interface ResetMessagesAction {
  type: typeof actionTypes.RESET_MESSAGES;
}

// MessageAction은 세 가지 액션 타입 중 하나입니다.
type MessageAction = AddMessageAction | SetMessagesAction | ResetMessagesAction;

function messageReducer(state: Message[], action: MessageAction): Message[] {
  switch (action.type) {
    case actionTypes.ADD_MESSAGE:
      return [...state, (action as AddMessageAction).payload];
    case actionTypes.SET_MESSAGES:
      return (action as SetMessagesAction).payload;
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
  const [page, setPage] = useState(0);
  const pageSize = 10;
  const [totalPages, setTotalPages] = useState(0);
  const [chatRoomInfo, setChatRoomInfo] = useState(null);
  console.log("Nickname from state:", nickname);

  useEffect(() => {
    const stompClient = new Client({
      brokerURL: "wss://nubi0.com/api/ws/ws-chat",
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

      // stompClient.subscribe(`/chatting/topic/room/${roomId}`, (message) => {
      //   const parsedMessage = JSON.parse(message.body);
      //   dispatch({
      //     type: actionTypes.ADD_MESSAGE,
      //     payload: parsedMessage,
      //   });
      // });
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

  const fetchChatHistory = async (pageNumber: number) => {
    const params = {
      roomid: roomId,
      page: pageNumber,
    };
    try {
      const response = await axios.get(
        process.env.REACT_APP_SERVER_URL + "/chatting/v1/chatting/history",
        { params }
      );

      dispatch({
        type: actionTypes.SET_MESSAGES,
        payload: response.data.data.content.reverse().map((msg: any) => ({
          nickname: msg.nickname,
          content: msg.content,
        })),
      });

      setTotalPages(response.data.data.meta.total_pages);
    } catch (error) {
      console.error("Error fetching chat history:", error);
    }
  };

  useEffect(() => {
    fetchChatHistory(page);
  }, [page, roomId]);

  const handlePageChange = (newPage: number) => {
    setPage(newPage);
  };
  const sendMessage = useCallback(async () => {
    if (client && client.connected && newMessage.trim() !== "") {
      const messageToSend = JSON.stringify({
        nickname,
        content: newMessage,
      });
      console.log(nickname);
      // client.publish({
      //   destination: `/chatting/topic/${roomId}`,
      //   body: messageToSend,
      // });

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
    <ChatPageLayout>
      <ChatHeader />
      <MessageList>
        <Announcement />
        {state.map((msg: Message, index: number) => (
          <MessageItem key={index}>
            <strong>{msg.nickname}: </strong>
            {msg.content}
          </MessageItem>
        ))}
      </MessageList>
      <ChatInputArea>
        <InputField
          type="text"
          value={newMessage}
          onChange={(e) => setNewMessage(e.target.value)}
          onKeyPress={(e) => {
            if (e.key === "Enter") {
              sendMessage();
            }
          }}
          placeholder="메시지를 입력하세요..."
        />
        <SendButton onClick={sendMessage}>Send</SendButton>
      </ChatInputArea>
    </ChatPageLayout>
  );
};

export default ChatRoom;
