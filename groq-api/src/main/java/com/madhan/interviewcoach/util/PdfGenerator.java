package com.madhan.interviewcoach.util;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class PdfGenerator {

    // ============= 1) RESUME REPORT =============
    public static byte[] generateResumeReport(
            String title,
            String summary,
            List<String> strengths,
            List<String> weaknesses,
            List<String> improvements
    ) throws Exception {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document doc = new Document();
        PdfWriter.getInstance(doc, out);
        doc.open();

        Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
        Font headerFont = new Font(Font.HELVETICA, 12, Font.BOLD);
        Font textFont = new Font(Font.HELVETICA, 11);

        doc.add(new Paragraph(title, titleFont));
        doc.add(new Paragraph("\n"));

        if (summary != null && !summary.isEmpty()) {
            doc.add(new Paragraph(summary, textFont));
            doc.add(new Paragraph("\n"));
        }

        doc.add(new Paragraph("Strengths", headerFont));
        if (strengths != null && !strengths.isEmpty()) {
            for (String s : strengths) doc.add(new Paragraph("• " + s, textFont));
        } else doc.add(new Paragraph("No strengths listed.", textFont));
        doc.add(new Paragraph("\n"));

        doc.add(new Paragraph("Weaknesses", headerFont));
        if (weaknesses != null && !weaknesses.isEmpty()) {
            for (String w : weaknesses) doc.add(new Paragraph("• " + w, textFont));
        } else doc.add(new Paragraph("No weaknesses listed.", textFont));
        doc.add(new Paragraph("\n"));

        doc.add(new Paragraph("Suggested Improvements", headerFont));
        if (improvements != null && !improvements.isEmpty()) {
            for (String imp : improvements) doc.add(new Paragraph("• " + imp, textFont));
        } else doc.add(new Paragraph("No suggestions provided.", textFont));

        doc.close();
        return out.toByteArray();
    }


    // ============= 2) QUESTIONS PDF =============
    public static byte[] generateQuestionsPdf(
            String title,
            String subtitle,
            List<String> questions
    ) throws Exception {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document doc = new Document();
        PdfWriter.getInstance(doc, out);
        doc.open();

        Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
        Font textFont = new Font(Font.HELVETICA, 11);

        doc.add(new Paragraph(title, titleFont));
        doc.add(new Paragraph("\n"));
        doc.add(new Paragraph(subtitle, textFont));
        doc.add(new Paragraph("\n"));

        doc.add(new Paragraph("Questions:\n", titleFont));
        if (questions != null && !questions.isEmpty()) {
            for (String q : questions) doc.add(new Paragraph(q, textFont));
        } else doc.add(new Paragraph("No questions available.", textFont));

        doc.close();
        return out.toByteArray();
    }


    // ============= 3) ANSWER PDF =============
    public static byte[] generateAnswerPdf(String question, String answer, String role) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document doc = new Document();
        PdfWriter.getInstance(doc, out);
        doc.open();

        Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
        Font headerFont = new Font(Font.HELVETICA, 12, Font.BOLD);
        Font textFont = new Font(Font.HELVETICA, 11);

        doc.add(new Paragraph("AI Interview Answer", titleFont));
        doc.add(new Paragraph("\n"));

        doc.add(new Paragraph("Question:", headerFont));
        doc.add(new Paragraph(question == null ? "-" : question, textFont));
        doc.add(new Paragraph("\n"));

        doc.add(new Paragraph("Role:", headerFont));
        doc.add(new Paragraph(role == null ? "-" : role, textFont));
        doc.add(new Paragraph("\n"));

        doc.add(new Paragraph("Answer:", headerFont));
        doc.add(new Paragraph(answer == null ? "-" : answer, textFont));

        doc.close();
        return out.toByteArray();
    }



    // ============= 4) EVALUATION PDF =============
    public static byte[] generateEvaluationPdf(
            String summary,
            Integer score,
            List<String> areasToImprove,
            String suggestedRevision
    ) throws Exception {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document doc = new Document();
        PdfWriter.getInstance(doc, out);
        doc.open();

        Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
        Font headerFont = new Font(Font.HELVETICA, 12, Font.BOLD);
        Font textFont = new Font(Font.HELVETICA, 11);

        doc.add(new Paragraph("Answer Evaluation Report", titleFont));
        doc.add(new Paragraph("\n"));

        doc.add(new Paragraph("Summary:", headerFont));
        doc.add(new Paragraph(summary, textFont));
        doc.add(new Paragraph("\n"));

        doc.add(new Paragraph("Score: " + score + "/10", headerFont));
        doc.add(new Paragraph("\n"));

        doc.add(new Paragraph("Areas to Improve:", headerFont));
        if (areasToImprove != null && !areasToImprove.isEmpty()) {
            for (String a : areasToImprove) doc.add(new Paragraph("• " + a, textFont));
        } else doc.add(new Paragraph("No areas listed.", textFont));
        doc.add(new Paragraph("\n"));

        doc.add(new Paragraph("Suggested Revision:", headerFont));
        doc.add(new Paragraph(suggestedRevision, textFont));

        doc.close();
        return out.toByteArray();
    }
}
