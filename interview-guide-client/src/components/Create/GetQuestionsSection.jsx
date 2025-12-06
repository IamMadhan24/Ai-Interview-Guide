import { useState } from "react";
import "./CreateSections.css";

const DEFAULT_COUNT = 5;

const GetQuestionsSection = () => {
  const [topic, setTopic] = useState("");
  const [difficulty, setDifficulty] = useState("medium");
  const [count, setCount] = useState(DEFAULT_COUNT);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [response, setResponse] = useState(null);

  async function handleSubmit(e) {
    e.preventDefault();
    setError("");

    if (!topic.trim()) return setError("Please enter a topic.");

    setLoading(true);
    setResponse(null);

    try {
      const res = await fetch(`${import.meta.env.VITE_API_URL}/api/v1/questions/generate`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ topic, difficulty, count }),
      });

      if (!res.ok) throw new Error(await res.text());
      setResponse(await res.json());
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  }

  async function downloadPdf() {
    const res = await fetch(`${import.meta.env.VITE_API_URL}/api/v1/report/questions`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(response),
    });

    const blob = await res.blob();
    const url = URL.createObjectURL(blob);
    const a = document.createElement("a");
    
    a.href = url;
    a.download = "questions_report.pdf";
    a.click();
  }

  return (
    <section className="section-card">

      <form onSubmit={handleSubmit}>
        
        <div className="form-group">
          <label className="form-label">Topic</label>
          <input
            className="form-input"
            placeholder="Spring Boot, SQL..."
            value={topic}
            onChange={(e) => setTopic(e.target.value)}
          />
        </div>

        <div className="flex-row form-group">
          
          <div>
            <label className="form-label">Difficulty</label>
            <select
              className="form-select"
              value={difficulty}
              onChange={(e) => setDifficulty(e.target.value)}
            >
              <option value="easy">easy</option>
              <option value="medium">medium</option>
              <option value="hard">hard</option>
            </select>
          </div>

          <div>
            <label className="form-label">Count</label>
            <input
              className="form-input w-24"
              type="number"
              min="1"
              value={count}
              onChange={(e) => setCount(e.target.value)}
            />
          </div>

        </div>

        {error && <p className="error-text">{error}</p>}

        <button className="btn-primary form-group" disabled={loading}>
          {loading ? "Generating..." : "Get Questions"}
        </button>
      </form>

      {response && (
        <div className="result-card relative">
          
          <h3 className="result-title">
            Questions — {response.topic} ({response.difficulty})
          </h3>

          <ul>
            {response.questions.map((q, i) => (
              <li key={i} >{q}</li>
            ))}
          </ul>

          <button
            className="btn-secondary absolute-top-right"
            onClick={downloadPdf}
          >
            Download Report
          </button>
        </div>
      )}

    </section>
  );
};

export default GetQuestionsSection;
