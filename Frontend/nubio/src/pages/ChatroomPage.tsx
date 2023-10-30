import React, { useEffect, useState } from "react";
import { Client } from "@stomp/stompjs";

const ChatRoom = () => {
  const [messages, setMessages] = useState([]);

  useEffect(() => {
    const client = new Client({
      brokerURL: "ws://localhost:8080/ws",
      connectHeaders: {
        login: "guest",
        passcode: "guest",
      },
      debug: function (str) {
        console.log(str);
      },
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
    });

    client.onConnect = function (frame) {
      client.subscribe("/topic/messages", function (message) {
        setMessages((prevMessages) => [...prevMessages, message.body]);
        console.log(message.body);
      });
    };

    client.onStompError = function (frame) {
      console.log("Broker reported error: " + frame.headers["message"]);
      console.log("Additional details: " + frame.body);
    };

    client.activate();

    return () => {
      client.deactivate();
    };
  }, []);

  return (
    <div>
      {messages.map((msg, index) => (
        <div key={index}>{msg}</div>
      ))}
    </div>
  );
};

export default ChatRoom;
