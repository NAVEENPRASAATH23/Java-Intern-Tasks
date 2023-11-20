package com.wordCounter;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParagraphCounterGUI extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = -423432215200987618L;
	private JTextArea inputTextArea;
    private JTextArea resultTextArea;

    public ParagraphCounterGUI() {
        // Set up the JFrame
        setTitle("Paragraph Counter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        inputTextArea = new JTextArea();
        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);

        JButton countButton = new JButton("Count");
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countAndDisplay();
            }
        });

        // Set up layout
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(new JScrollPane(inputTextArea));
        add(countButton);
        add(new JScrollPane(resultTextArea));

        // Set visible
        setVisible(true);
    }

    private void countAndDisplay() {
        String inputParagraphs = inputTextArea.getText();
        int paragraphCount = ParagraphCounter.countParagraphs(inputParagraphs);
        int wordCount = ParagraphCounter.countWords(inputParagraphs);
        int charCount = ParagraphCounter.countCharacters(inputParagraphs);

        String result = "Number of paragraphs: " + paragraphCount +
                "\nNumber of words: " + wordCount +
                "\nNumber of characters: " + charCount;

        resultTextArea.setText(result);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ParagraphCounterGUI());
    }
}
