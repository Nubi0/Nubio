import React, { useEffect, useState, useCallback } from "react";
import { Client } from "@stomp/stompjs";
import { useParams } from "react-router-dom";
import axios from "axios";

const ChatRoom = () => {
  const { roomId } = useParams<{ roomId: string }>();
  const [client, setClient] = useState<Client | null>(null);
  const [messages, setMessages] = useState<string[]>([]);
  const [newMessage, setNewMessage] = useState<string>("");

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

        setMessages((prevMessages) => [...prevMessages, parsedMessage.content]);
      });

      stompClient.subscribe(`/chatting/topic/room/${roomId}`, (message) => {
        const parsedMessage = JSON.parse(message.body);

        setMessages((prevMessages) => [...prevMessages, parsedMessage.content]);
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
  }, [client, newMessage, roomId]);

  return (
    <div>
      <div>
        {messages.map((msg, index) => (
          <div key={index}>{msg}</div>
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
