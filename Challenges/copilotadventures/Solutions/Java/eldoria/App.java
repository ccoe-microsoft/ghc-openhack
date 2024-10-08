import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

    private static final String SCROLL_URL = "https://raw.githubusercontent.com/microsoft/CopilotAdventures/main/Data/scrolls.txt";
    private static final String SECRETS_PATTERN = "\\{\\*(.*?)\\*\\}";

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        fetchAndDecipherScroll(SCROLL_URL);
    }

    private static void fetchAndDecipherScroll(String url) {
        System.out.println("Fetching scroll from " + url);
        try {
            String scrollContent = fetchContentFromUrl(url);
            extractAndPrintSecrets(scrollContent);
        } catch (IOException | InterruptedException ex) {
            System.err.println("An error occurred while fetching or deciphering the scroll: " + ex.getMessage());
        }
    }

    private static String fetchContentFromUrl(String url) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private static void extractAndPrintSecrets(String content) {
        Pattern pattern = Pattern.compile(SECRETS_PATTERN);
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }
}