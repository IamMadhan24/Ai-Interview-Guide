import { useNavigate } from "react-router-dom";
import "./Hero.css";

const Hero = () => {
  const navigate = useNavigate();

  return (
    <section className="hero-container">
      <h1 className="hero-title">
        Ace every interview with a <br /> smarter way to practice
      </h1>

      <p className="hero-subtitle">
        Get AI-powered interview questions, answer suggestions, answer evaluation, resume analysis, and an interactive chatbot.
      </p>

      <button
        onClick={() => navigate("/questions")}
        className="hero-btn"
      >
        Try AI Guide
      </button>
    </section>
  );
};

export default Hero;
