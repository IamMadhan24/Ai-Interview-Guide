package com.madhan.interviewcoach.util;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class PdfGenerator {

    // 1) Resume Report (FULL ANALYSIS)
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

        // Title
        doc.add(new Paragraph(title, titleFont));
        doc.add(new Paragraph("\n"));

        // Summary
        if (summary != null && !summary.isEmpty()) {
            doc.add(new Paragraph(summary, textFont));
            doc.add(new Paragraph("\n"));
        }

        // Strengths
        doc.add(new Paragraph("Strengths", headerFont));
        if (strengths != null && !strengths.isEmpty()) {
            for (String s : strengths) {
                doc.add(new Paragraph("• " + s, textFont));
            }
        } else {
            doc.add(new Paragraph("No strengths listed.", textFont));
        }
        doc.add(new Paragraph("\n"));

        // Weaknesses
        doc.add(new Paragraph("Weaknesses", headerFont));
        if (weaknesses != null && !weaknesses.isEmpty()) {
            for (String w : weaknesses) {
                doc.add(new Paragraph("• " + w, textFont));
            }
        } else {
            doc.add(new Paragraph("No weaknesses listed.", textFont));
        }
        doc.add(new Paragraph("\n"));

        // Improvements
        doc.add(new Paragraph("Suggested Improvements", headerFont));
        if (improvements != null && !improvements.isEmpty()) {
            for (String imp : improvements) {
                doc.add(new Paragraph("• " + imp, textFont));
            }
        } else {
            doc.add(new Paragraph("No suggestions provided.", textFont));
        }

        doc.close();
        return out.toByteArray();
    }

    // 2) QUESTIONS PDF (SIMPLE FORMAT)
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

        // Title
        doc.add(new Paragraph(title, titleFont));
        doc.add(new Paragraph("\n"));

        // Subtitle
        doc.add(new Paragraph(subtitle, textFont));
        doc.add(new Paragraph("\n"));

        // Questions list
        doc.add(new Paragraph("Questions:\n", titleFont));

        if (questions != null && !questions.isEmpty()) {
            for (String q : questions) {
                doc.add(new Paragraph("• " + q, textFont));
            }
        } else {
            doc.add(new Paragraph("No questions available.", textFont));
        }

        doc.close();
        return out.toByteArray();
    }
}
