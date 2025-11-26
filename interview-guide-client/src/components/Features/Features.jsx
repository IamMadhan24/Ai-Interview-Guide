import FeatureCard from "./FeatureCard.jsx";
import { Eye, FileClock, Sparkles } from "lucide-react";
import "./Features.css";

const Features = () => {
  return (
    <section className="features-container">
      <h2 className="features-title">AI Interview Guide Features</h2>

      <div className="features-grid">
        <FeatureCard
        icon={<Eye size={24} />}
        title="Smart Interview Q&A Generation"
        >
          Get tailored interview questions from any topic you enter, along with AI-generated answers when you ask. Practice instantly and learn how to respond with confidence.
        </FeatureCard>



        <FeatureCard
        icon={<FileClock size={24} />}
        title="Real-time Answer Evaluation"
        >
          AI evaluates your written answers, gives a score out of 10, highlights improvements, and provides a refined version to help you strengthen your interview responses.
       </FeatureCard>


        <FeatureCard
        icon={<Sparkles size={24} />}
        title="Resume Analysis & AI Interview ChatBot"
        >
          Upload your resume for an AI-powered breakdown with a summary, strengths, weaknesses, and improvement tips.  
          Ask any interview-related doubts to our AI ChatBot and get instant, accurate answers.
      </FeatureCard>


      </div>
    </section>
  );
};

export default Features;
