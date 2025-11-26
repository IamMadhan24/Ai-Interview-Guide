import { useState } from "react";
import { Send } from "lucide-react";

const ChatInput = ({ onSend }) => {
  const [msg, setMsg] = useState("");

  function handleSubmit(e) {
    e.preventDefault();
    onSend(msg);
    setMsg("");
  }

  return (
    <form className="chat-input" onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder="Ask your interview doubts..."
        value={msg}
        onChange={(e) => setMsg(e.target.value)}
      />
      <button type="submit">
        <Send size={20} />
      </button>
    </form>
  );
};

export default ChatInput;
