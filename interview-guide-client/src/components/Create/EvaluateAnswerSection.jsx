import { useState } from "react";
import "./CreateSections.css";

const EvaluateAnswerSection = () => {
  const [question, setQuestion] = useState("");
  const [candidateAnswer, setCandidateAnswer] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [result, setResult] = useState(null);

  async function handleSubmit(e) {
    e.preventDefault();
    setError("");
    setResult(null);

    if (!question.trim() || !candidateAnswer.trim()) {
      setError("Please provide both the question and your answer.");
      return;
    }

    setLoading(true);
    try {
      const res = await fetch(`${import.meta.env.VITE_API_URL}/api/v1/evaluate`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          question: question.trim(),
          candidateAnswer: candidateAnswer.trim(),
        }),
      });

      if (!res.ok) throw new Error(await res.text());
      setResult(await res.json());
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  }

  return (
    <section className="section-card">
      <form onSubmit={handleSubmit}>

        <div className="form-group">
          <label className="form-label">Question</label>
          <input
            className="form-input"
            placeholder="Paste the interview question here"
            value={question}
            onChange={(e) => setQuestion(e.target.value)}
          />
        </div>

        <div className="form-group">
          <label className="form-label">Your Answer</label>
          <textarea
            className="form-textarea"
            rows="6"
            placeholder="Write your answer here"
            value={candidateAnswer}
            onChange={(e) => setCandidateAnswer(e.target.value)}
          />
        </div>

        {error && <p className="error-text">{error}</p>}

        <button className="btn-primary form-group">
          {loading ? "Evaluating..." : "Evaluate Answer"}
        </button>
      </form>

      {result && (
        <div className="result-card">
          <h3 className="result-title">Evaluation Result</h3>

          <p className="result-text">
            <strong>Score:</strong> {result.score} / 10
          </p>

          <div className="form-group">
            <strong>Summary</strong>
            <p className="result-text">{result.summary}</p>
          </div>

          <div className="form-group">
            <strong>Areas to Improve</strong>
            <ul className="list">
              {result.areasToImprove?.length
                ? result.areasToImprove.map((a, i) => (
                    <li key={i} className="list-item">{a}</li>
                  ))
                : <li className="text-muted">No suggestions provided.</li>
              }
            </ul>
          </div>

          <div className="form-group">
            <strong>Suggested Revision</strong>
            <p className="result-text">{result.suggestedRevision}</p>
          </div>
        </div>
      )}
    </section>
  );
};

export default EvaluateAnswerSection;
