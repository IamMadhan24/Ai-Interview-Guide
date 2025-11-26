import { useState, useRef, useEffect } from "react";
import ChatMessage from "../components/ChatBot/ChatMessage";
import ChatInput from "../components/ChatBot/ChatInput";
import "./ChatBot.css";

const ChatBotPage = () => {
  const [messages, setMessages] = useState([
    { sender: "bot", text: "Hi! I'm your AI Interview Assistant. Ask me anything about interviews, technology, resume tips, or concepts!" }
  ]);

  const containerRef = useRef(null);

  // Auto-scroll
  useEffect(() => {
    containerRef.current?.scrollTo({
      top: containerRef.current.scrollHeight,
      behavior: "smooth"
    });
  }, [messages]);

  async function sendMessageToBackend(question) {
    try {
      const res = await fetch(`${import.meta.env.VITE_API_URL}/api/v1/chat`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ message: question })
      });

      const data = await res.json();
      return data.reply;
    } catch (err) {
      return "Sorry, something went wrong while connecting to the AI.";
    }
  }

  async function handleSend(message) {
    if (!message.trim()) return;

    setMessages(prev => [...prev, { sender: "user", text: message }]);

    const reply = await sendMessageToBackend(message);

    setMessages(prev => [...prev, { sender: "bot", text: reply }]);
  }

  return (
    <div className="chatbot-wrapper">

      <h2 className="chat-title">AI Chat Bot</h2>

      {/* CHAT AREA */}
      <div className="chat-container" ref={containerRef}>
        {messages.map((msg, index) => (
          <ChatMessage key={index} sender={msg.sender} text={msg.text} />
        ))}
      </div>

      {/* INPUT STICKY INSIDE CHAT WRAPPER */}
      <div className="chat-input-wrapper">
        <ChatInput onSend={handleSend} />
      </div>

    </div>
  );
};

export default ChatBotPage;
