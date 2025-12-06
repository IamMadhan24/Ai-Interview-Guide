import { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";

import GetQuestionsSection from "../components/Create/GetQuestionsSection";
import GetAnswersSection from "../components/Create/GetAnswersSection";
import EvaluateAnswerSection from "../components/Create/EvaluateAnswerSection";
import ResumeAnalysisSection from "../components/Create/ResumeAnalysisSection";

import "../components/Create/CreateSections.css";
import Popup from "./Popup";

const CreatePage = () => {
  const location = useLocation();
  const navigate = useNavigate();

  const currentPath = location.pathname.split("/")[1];
  const allowedTabs = ["questions", "answers", "evaluate", "resume"];

  const initialTab = allowedTabs.includes(currentPath) ? currentPath : "questions";
  const [activeTab, setActiveTab] = useState(initialTab);

  useEffect(() => {
    setActiveTab(initialTab);
  }, [initialTab]);

  function changeTab(tab) {
    navigate(`/${tab}`);
  }

  return (
    <div className="container mx-auto py-8 px-4 md:px-8">
      <Popup />

      {/* Tabs */}
      <nav className="tabs">
        <button
          onClick={() => changeTab("questions")}
          className={activeTab === "questions" ? "tab-active" : "tab-btn"}
        >
          Get Questions
        </button>

        <button
          onClick={() => changeTab("answers")}
          className={activeTab === "answers" ? "tab-active" : "tab-btn"}
        >
          Get Answers
        </button>

        <button
          onClick={() => changeTab("evaluate")}
          className={activeTab === "evaluate" ? "tab-active" : "tab-btn"}
        >
          Evaluate Answer
        </button>

        <button
          onClick={() => changeTab("resume")}
          className={activeTab === "resume" ? "tab-active" : "tab-btn"}
        >
          Resume Analysis
        </button>
      </nav>

      {/* Content */}
      {activeTab === "questions" && <GetQuestionsSection />}
      {activeTab === "answers" && <GetAnswersSection />}
      {activeTab === "evaluate" && <EvaluateAnswerSection />}
      {activeTab === "resume" && <ResumeAnalysisSection />}
    </div>
  );
};

export default CreatePage;
