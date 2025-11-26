import { Link } from "react-router-dom";
import "./Footer.css";

const Footer = () => {
  return (
    <footer className="footer-bg">
      <div className="footer-container">

        <div className="footer-grid">

          {/* Brand Column */}
          <div>
            <div className="footer-brand">
              <div className="footer-logo-circle">Ai</div>
              <h3 className="footer-logo-text">Interview Guide</h3>
            </div>

            <p className="footer-desc">
              Your all-in-one AI-powered interview preparation platform.
              Generate questions, get AI-suggested answers, evaluate your responses,
              and analyze your resume to boost your job-readiness with ease.
            </p>

          </div>

          {/* Links Column */}
          <div>
            <h4 className="footer-heading">Links</h4>
            <ul className="footer-list">
              <li><Link to="/" className="footer-link">Home</Link></li>
              <li><Link to="/create" className="footer-link">AI Guide</Link></li>
              <li><Link to="/features" className="footer-link">Features</Link></li>
              <li><Link to="/faq" className="footer-link">FAQ</Link></li>
            </ul>
          </div>

          {/* Features Column */}
          <div>
            <h4 className="footer-heading">Features</h4>
            <ul className="footer-list">
              <li><Link to="/questions" className="footer-link">Question Generator</Link></li>
              <li><Link to="/answers" className="footer-link">Answer Suggestions</Link></li>
              <li><Link to="/evaluate" className="footer-link">Answer Evaluation</Link></li>
              <li><Link to="/resume" className="footer-link">Resume Analysis</Link></li>
              <li><Link to="/chat" className="footer-link">AI Interview ChatBot</Link></li>
              </ul>
          </div>

          {/* Legal Column */}
          <div>
            <h4 className="footer-heading">Legal</h4>
            <ul className="footer-list">
              <li className="footer-link">Terms of Service</li>
              <li className="footer-link">Privacy Policy</li>
            </ul>
          </div>

        </div>

        {/* Bottom bar */}
        <div className="footer-bottom">
          <p>&copy; 2025 AI Interview Guide. All rights reserved.</p>
        </div>

      </div>
    </footer>
  );
};

export default Footer;
