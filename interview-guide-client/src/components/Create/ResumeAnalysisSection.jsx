import { useState, useRef } from "react";
import { X } from "lucide-react";
import "./CreateSections.css";

const ResumeAnalysisSection = () => {
  const [file, setFile] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [result, setResult] = useState(null);

  const inputRef = useRef(null);

  function handleFileChange(e) {
    const selected = e.target.files?.[0];
    if (!selected) return;

    if (!selected.name.toLowerCase().endsWith(".pdf")) {
      setError("Only PDF files are supported.");
      return;
    }

    setError("");
    setFile(selected);
    setResult(null);
  }

  async function handleSubmit(e) {
    e.preventDefault();
    setError("");
    setResult(null);

    if (!file) return setError("Please upload a resume.");

    setLoading(true);

    try {
      const form = new FormData();
      form.append("file", file);

      const res = await fetch(`${import.meta.env.VITE_API_URL}/api/v1/resume/evaluate`, {
        method: "POST",
        body: form,
      });

      if (!res.ok) throw new Error(await res.text());
      setResult(await res.json());
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  }

  async function downloadPdf() {
    const res = await fetch(`${import.meta.env.VITE_API_URL}/api/v1/report/resume`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(result),
    });

    const blob = await res.blob();
    const url = URL.createObjectURL(blob);
    const a = document.createElement("a");

    a.href = url;
    a.download = "resume_report.pdf";
    a.click();
  }

  return (
    <section className="section-card">

      <form onSubmit={handleSubmit}>
        
        {!file ? (
          <div className="file-drop" onClick={() => inputRef.current.click()}>
            <p className="result-text">Drag & Drop your resume here</p>
            <p className="text-muted">or click to browse (PDF only)</p>

            <input
              ref={inputRef}
              type="file"
              accept="application/pdf"
              onChange={handleFileChange}
              className="hidden-input"
            />
          </div>
        ) : (
          <div className="file-info">
            <div>
              <p className="result-text">{file.name}</p>
              <p className="text-muted">PDF File Selected</p>
            </div>

            <button type="button" onClick={() => setFile(null)}>
              <X size={22} />
            </button>
          </div>
        )}

        {error && <p className="error-text">{error}</p>}

        <button className="btn-primary form-group cursor-pointer" disabled={loading}>
          {loading ? "Analyzing..." : "Analyze Resume"}
        </button>
      </form>

      {result && (
        <div className="result-card relative">
          
          <h3 className="result-title">Resume Analysis</h3>

          <div className="form-group">
            <strong>Summary</strong>
            <p className="result-text">{result.summary}</p>
          </div>

          <div className="form-group">
            <strong>Strengths</strong>
            <ul className="list">
              {result.strengths.map((s, i) => (
                <li key={i} className="list-item">{s}</li>
              ))}
            </ul>
          </div>

          <div className="form-group">
            <strong>Weaknesses</strong>
            <ul className="list">
              {result.weaknesses.map((s, i) => (
                <li key={i} className="list-item">{s}</li>
              ))}
            </ul>
          </div>

          <div className="form-group">
            <strong>Suggested Improvements</strong>
            <ul className="list">
              {result.suggestedImprovements.length ? (
                result.suggestedImprovements.map((imp, i) => (
                  <li key={i} className="list-item">{imp}</li>
                ))
              ) : (
                <li className="text-muted">No suggestions provided.</li>
              )}
            </ul>
          </div>

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

export default ResumeAnalysisSection;
