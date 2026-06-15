import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatBotLogic {

    public static String getResponse(String input) {

        input = input.toLowerCase().trim();

        if (input.contains("hello") || input.contains("hi")) {
            return "Hello! How can I help you today?";
        }

        if (input.contains("java")) {
            return "Java is an object-oriented programming language.";
        }

        if (input.contains("python")) {
            return "Python is used in AI, Data Science and Web Development.";
        }

        if (input.contains("ai")) {
            return "Artificial Intelligence enables machines to simulate human intelligence.";
        }

        if (input.contains("time") || input.contains("date")) {

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            return "Current Date & Time: " +
                    LocalDateTime.now().format(formatter);
        }

        if (input.contains("bye")) {
            return "Goodbye! Happy Learning.";
        }

        return "Sorry, I don't understand that yet.";
    }
}