import "./FeatureCard.css";

const FeatureCard = ({ icon, title, children }) => (
  <div className="feature-card">
    <div className="feature-icon">
      {icon}
    </div>

    <h3 className="feature-title">{title}</h3>

    <p className="feature-text">{children}</p>
  </div>
);

export default FeatureCard;
