import React, { FC, useState } from "react";

interface Message {
  user: string;
  content: string;
}

const ChatRoom: FC = () => {
  const [messages, setMessages] = useState<Message[]>([]);
  const [newMessage, setNewMessage] = useState<string>("");

  const handleSendMessage = () => {
    if (newMessage.trim()) {
      setMessages([...messages, { user: "You", content: newMessage.trim() }]);
      setNewMessage("");
    }
  };

  return (
    <div className="chat-room">
      <div className="messages">
        {messages.map((message, index) => (
          <div key={index} className={message.user}>
            <div className="message-content">{message.content}</div>
          </div>
        ))}
      </div>
      <div className="new-message">
        <input
          type="text"
          value={newMessage}
          onChange={(e) => setNewMessage(e.target.value)}
        />
        <button onClick={handleSendMessage}>Send</button>
      </div>
    </div>
  );
};

export default ChatRoom;
