import javax.swing.*;
import java.awt.*;

public class ChatBotGUI extends JFrame {

    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;

    public ChatBotGUI() {

        setTitle("StudyMate AI ChatBot");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Color bgColor = new Color(30, 30, 30);
        Color textColor = Color.WHITE;
        Color buttonColor = new Color(0, 120, 215);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setBackground(bgColor);
        chatArea.setForeground(textColor);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(chatArea);

        inputField = new JTextField();
        inputField.setBackground(new Color(45, 45, 45));
        inputField.setForeground(Color.WHITE);
        inputField.setFont(new Font("Arial", Font.PLAIN, 16));

        sendButton = new JButton("Send");
        sendButton.setBackground(buttonColor);
        sendButton.setForeground(Color.WHITE);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        chatArea.append(
                "STUDYMATE AI CHATBOT\n\n" +
                "Available Commands:\n" +
                "- hello\n" +
                "- java\n" +
                "- python\n" +
                "- ai\n" +
                "- dsa\n" +
                "- oop\n" +
                "- time\n" +
                "- date\n" +
                "- motivate me\n" +
                "- bye\n\n" +
                "Ready to help you!\n\n"
        );

        sendButton.addActionListener(e -> sendMessage());

        inputField.addActionListener(e -> sendMessage());

        setVisible(true);
    }

    private void sendMessage() {

        String userMessage = inputField.getText().trim();

        if (userMessage.isEmpty()) {
            return;
        }

        chatArea.append("You: " + userMessage + "\n");

        String botResponse = ChatBotLogic.getResponse(userMessage);

        chatArea.append("Bot: " + botResponse + "\n\n");

        chatArea.setCaretPosition(
                chatArea.getDocument().getLength()
        );

        inputField.setText("");
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChatBotGUI();
            }
        });
    }
}