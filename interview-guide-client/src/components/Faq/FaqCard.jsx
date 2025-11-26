import "./FaqCard.css";

const FaqCard = ({ question, children }) => (
  <div className="faq-card">
    <h3 className="faq-question">{question}</h3>
    <p className="faq-answer">{children}</p>
  </div>
);

export default FaqCard;
