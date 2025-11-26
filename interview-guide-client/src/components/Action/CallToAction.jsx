import { useNavigate } from "react-router-dom";
import "./CallToAction.css";

const CallToAction = () => {
  const navigate = useNavigate();

  return (
    <div className="cta-section">
      <div className="cta-container">
        <div className="cta-box">

          {/* DESKTOP CONTENT */}
          <div className="hidden md:block">
            <h2 className="cta-title">
              Improve Your Interview Skills with AI Guidance
            </h2>

            <p className="cta-text">
              Practice smarter with AI-generated questions, answer evaluation,
              resume analysis, and instant chatbot assistance — everything you
              need to become job-ready.
            </p>

            <button
              onClick={() => navigate("/questions")}
              className="cta-button"
            >
              Try Our AI Guide For Free
            </button>
          </div>

          {/* MOBILE CONTENT */}
          <div className="block md:hidden">
            <h2 className="cta-title">
              Chat with Your AI Interview Assistant
            </h2>

            <p className="cta-text">
              Ask doubts, get answers instantly, and learn interview concepts
              through our AI Chat Bot — your 24/7 interview helper.
            </p>

            <button
              onClick={() => navigate("/chat")}
              className="cta-button"
            >
              Open AI Chat Bot
            </button>
          </div>

        </div>
      </div>
    </div>
  );
};

export default CallToAction;
