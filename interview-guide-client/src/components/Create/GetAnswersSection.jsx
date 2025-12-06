import { useState } from "react";
import "./CreateSections.css";

const GetAnswersSection = () => {
  const [question, setQuestion] = useState("");
  const [role, setRole] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [answer, setAnswer] = useState("");

  async function handleSubmit(e) {
    e.preventDefault();
    setError("");
    setAnswer("");

    if (!question.trim()) return setError("Please enter a question.");

    setLoading(true);
    try {
      const res = await fetch(`${import.meta.env.VITE_API_URL}/api/v1/interview/answer`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ question, role }),
      });

      if (!res.ok) throw new Error(await res.text());
      setAnswer(await res.text());
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  }

    async function downloadPdf() {
      const res = await fetch(`${import.meta.env.VITE_API_URL}/api/v1/report/answer`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          question: question,
          answer: answer,
          role: role,
        }),
      });

      const blob = await res.blob();
      const url = URL.createObjectURL(blob);
      const a = document.createElement("a");

      a.href = url;
      a.download = "answer_report.pdf";
      a.click();
    }


  return (
    <section className="section-card">
      <form onSubmit={handleSubmit}>

        <div className="form-group">
          <label className="form-label">Enter Question</label>
          <textarea
            className="form-textarea"
            rows="4"
            placeholder="Type your interview question..."
            value={question}
            onChange={(e) => setQuestion(e.target.value)}
          />
        </div>

        <div className="form-group">
          <label className="form-label">Your Role / Job Position</label>
          <input
            className="form-input"
            placeholder="Java Developer, Backend Developer"
            value={role}
            onChange={(e) => setRole(e.target.value)}
          />
        </div>

        {error && <p className="error-text">{error}</p>}

        <button className="btn-primary form-group cursor-pointer" disabled={loading}>
          {loading ? "Generating..." : "Get AI Answer"}
        </button>
      </form>

      {answer && (
        <div className="result-card relative">
          <h3 className="result-title">AI Suggested Answer</h3>
          <p className="result-text whitespace-pre-line">{answer}</p>

          <button
            className="btn-secondary absolute-top-right cursor-pointer"
            onClick={downloadPdf}
          >
            Download Report
          </button>
        </div>
      )}
    </section>
  );
};

export default GetAnswersSection;
