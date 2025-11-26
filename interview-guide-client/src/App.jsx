import { Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar/Navbar";
import Features from "./components/Features/Features";
import Faq from "./components/Faq/Faq";
import Footer from "./components/Footer/Footer";
import LandingPage from "./pages/LandingPage"; 
import CreatePage from "./pages/CreatePage";
import ChatBotPage from "./pages/ChatbotPage";
import ScrollToTop from "./components/ScrollToTop";
import "./App.css";

const App = () => {
  return (
    <div className="app-wrapper">
      <ScrollToTop />
      <Navbar />
      
      <main className="grow">
        <Routes>
          <Route path="/" element={<LandingPage />} />

          <Route path="/questions" element={<CreatePage />} />
          <Route path="/answers" element={<CreatePage />} />
          <Route path="/evaluate" element={<CreatePage />} />
          <Route path="/resume" element={<CreatePage />} />

          <Route path="/chat" element={<ChatBotPage />} />
          <Route path="/features" element={<Features />} />
          <Route path="/faq" element={<Faq />} />
        </Routes>
      </main>

      <Footer />
    </div>
  );
};

export default App;
