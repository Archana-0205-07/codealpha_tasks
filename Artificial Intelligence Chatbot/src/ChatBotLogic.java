import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class ChatBotLogic {

    private static HashMap<String, String> faq = new HashMap<>();

    static {
        faq.put("java", "Java is an object-oriented programming language.");
        faq.put("python", "Python is widely used in AI, Data Science and Web Development.");
        faq.put("ai", "Artificial Intelligence enables machines to simulate human intelligence.");
        faq.put("dsa", "DSA stands for Data Structures and Algorithms.");
        faq.put("oop", "OOP stands for Object-Oriented Programming.");
    }

    public static String getResponse(String input) {

        input = input.toLowerCase().trim();

        if (input.contains("hello") || input.contains("hi")) {
            return "Hello! How can I help you today?";
        }

        if (input.contains("time") || input.contains("date")) {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            return "Current Date & Time: " +
                    LocalDateTime.now().format(formatter);
        }

        if (input.contains("motivate")) {

            String[] quotes = {
                    "Success comes from consistency.",
                    "Small progress is still progress.",
                    "Practice daily and never give up.",
                    "Every expert was once a beginner.",
                    "Your future is created by what you do today."
            };

            int random = (int)(Math.random() * quotes.length);

            return quotes[random];
        }

        if (faq.containsKey(input)) {
            return faq.get(input);
        }

        if (input.contains("bye")) {
            return "Goodbye! Happy Learning.";
        }

        return "Sorry, I don't understand that yet.";
    }
}