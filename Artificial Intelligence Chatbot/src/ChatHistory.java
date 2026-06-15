import java.io.FileWriter;
import java.io.IOException;

public class ChatHistory {

    public static void saveMessage(String sender, String message) {

        try {
            FileWriter writer =
                    new FileWriter("chat_history.txt", true);

            writer.write(sender + ": " + message + "\n");

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}