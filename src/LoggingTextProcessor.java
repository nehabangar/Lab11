import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingTextProcessor {
    private static final int THREAD_POOL_SIZE = 5;
    private static final Logger logger = Logger.getLogger(LoggingTextProcessor.class.getName());

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java LoggingTextProcessor <file_path1> <file_path2> ...");
            return;
        }

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        for (String filePath : args) {
            executor.execute(() -> {
                try {
                    long wordCount = countWords(filePath);
                    System.out.println("Word count in " + filePath + ": " + wordCount);
                    logToFile(filePath, wordCount);
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

    private static void logToFile(String filePath, long wordCount) {
        String logMessage = String.format("File: %s, Word count: %d", filePath, wordCount);
        logger.log(Level.INFO, logMessage);
    }
}
