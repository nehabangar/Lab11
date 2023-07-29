import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SingleThreadedTextProcessor {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java SingleThreadedTextProcessor <file_path>");
            return;
        }

        String filePath = args[0];

        try {
            long wordCount = countWords(filePath);
            System.out.println("Word count in " + filePath + ": " + wordCount);
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    private static long countWords(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] words = content.split("\\s+");
        return words.length;
    }
}
