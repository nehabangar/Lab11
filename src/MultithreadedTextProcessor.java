import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MultithreadedTextProcessor {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java MultithreadedTextProcessor <file_path1> <file_path2> ...");
            return;
        }

        for (String filePath : args) {
            Thread thread = new Thread(() -> {
                try {
                    long wordCount = countWords(filePath);
                    System.out.println("Word count in " + filePath + ": " + wordCount);
                } catch (IOException e) {
                    System.out.println("Error reading the file " + filePath + ": " + e.getMessage());
                }
            });

            thread.start();
        }
    }

    private static long countWords(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] words = content.split("\\s+");
        return words.length;
    }
}
