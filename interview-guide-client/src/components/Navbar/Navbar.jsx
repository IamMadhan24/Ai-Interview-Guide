import { useState } from "react";
import { Link, NavLink, useLocation } from "react-router-dom";
import { Menu, X } from "lucide-react";
import "./Navbar.css";

const Navbar = () => {
  const [menuOpen, setMenuOpen] = useState(false);
  const location = useLocation();

   const aiGuidePaths = ["/questions", "/answers", "/evaluate", "/resume"];

  const aiGuideActive = aiGuidePaths.some((p) =>
    location.pathname.startsWith(p)
  );

  return (
    <header className="nav-container">
      <nav className="nav-content">

        {/* Logo */}
        <Link to="/" className="logo">
          <div className="logo-circle">Ai</div>
          <span>Interview Guide</span>
        </Link>

        {/* Desktop Links */}
        <div className="nav-links">
          <NavLink
            to="/"
            className={({ isActive }) =>
              `nav-link ${isActive ? "nav-active" : ""}`
            }
          >
            Home
          </NavLink>

          <Link
            to="/questions"
            className={`nav-link ${aiGuideActive ? "nav-active" : ""}`}
          >
            AI Guide
          </Link>

          <NavLink
            to="/features"
            className={({ isActive }) =>
              `nav-link ${isActive ? "nav-active" : ""}`
            }
          >
            Features
          </NavLink>

          <NavLink
            to="/faq"
            className={({ isActive }) =>
              `nav-link ${isActive ? "nav-active" : ""}`
            }
          >
            FAQ
          </NavLink>
        </div>

        {/* Desktop Button */}
        <Link to="/chat" className="chat-wrapper">
          <button className="chat-btn">AI Interview ChatBot</button>
        </Link>

        {/* Mobile Hamburger */}
        <button className="hamburger-btn" onClick={() => setMenuOpen(!menuOpen)}>
          {menuOpen ? <X size={28} /> : <Menu size={28} />}
        </button>
      </nav>

      {/* Mobile Menu */}
      {menuOpen && (
        <div className="mobile-menu">
          <NavLink to="/" onClick={() => setMenuOpen(false)} className="mobile-link">
            Home
          </NavLink>

          {/* Mobile AI GUIDE link with manual active */}
          <Link
            to="/questions"
            onClick={() => setMenuOpen(false)}
            className={`mobile-link ${aiGuideActive ? "nav-active" : ""}`}
          >
            AI Guide
          </Link>

          <NavLink
            to="/features"
            onClick={() => setMenuOpen(false)}
            className="mobile-link"
          >
            Features
          </NavLink>

          <NavLink
            to="/faq"
            onClick={() => setMenuOpen(false)}
            className="mobile-link"
          >
            FAQ
          </NavLink>
        </div>
      )}
    </header>
  );
};

export default Navbar;
