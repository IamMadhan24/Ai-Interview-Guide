const ChatMessage = ({ sender, text }) => {
  const isUser = sender === "user";

  return (
    <div className={`chat-message ${isUser ? "user" : "bot"}`}>
      <div className="bubble">{text}</div>
    </div>
  );
};

export default ChatMessage;
