import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTextProcessor {
    private static final int THREAD_POOL_SIZE = 5; // You can adjust this based on your needs

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java ThreadPoolTextProcessor <file_path1> <file_path2> ...");
            return;
        }

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        for (String filePath : args) {
            executor.execute(() -> {
                try {
                    long wordCount = countWords(filePath);
                    System.out.println("Word count in " + filePath + ": " + wordCount);
                } catch (IOException e) {
                    System.out.println("Error reading the file " + filePath + ": " + e.getMessage());
                }
            });
        }

        executor.shutdown();
    }

    private static long countWords(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] words = content.split("\\s+");
        return words.length;
    }
}
