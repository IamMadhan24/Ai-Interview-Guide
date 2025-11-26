import FaqCard from "./FaqCard";
import "./Faq.css";

const Faq = () => {
  return (
    <section className="faq-container">
      <h2 className="faq-title">Frequently Asked Interview Questions</h2>

      <div className="faq-grid">

        <FaqCard question="1. Tell me about yourself">
          A short and structured intro works best. Follow this simple formula:
          <br /><br />
          <strong>Who you are → What you've done → What you're looking for.</strong>
          <br /><br />
          Example: “I'm a B.Tech graduate skilled in Java, Spring Boot, and React. I've
          built multiple projects including an AI Interview Guide app. I'm now looking
          for a role where I can contribute to backend development and continue learning.”
        </FaqCard>

        <FaqCard question="2. What are your strengths?">
          Choose 2-3 strengths that fit the job. Keep it honest and relevant.
          <br /><br />
          Example: “My strengths are problem-solving, quick learning, and writing clean,
          maintainable code. I stay calm under pressure and focus on delivering results.”
        </FaqCard>

        <FaqCard question="3. What are your weaknesses?">
          Select a real, fixable weakness and show how you're improving.
          <br /><br />
          Example: “I used to struggle with time management when handling multiple tasks.
          I've improved by using planning tools and breaking work into smaller steps.”
        </FaqCard>

        <FaqCard question="4. Why should we hire you?">
          Connect your skills to what the company needs.
          <br /><br />
          Example: “You should hire me because I have strong technical skills in Java and
          Spring Boot, I adapt quickly, and I'm committed to delivering quality work. I can
          contribute from day one and grow within the team.”
        </FaqCard>

      </div>
    </section>
  );
};

export default Faq;
