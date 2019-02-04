package pl.coderslab.workshop1.task5;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Task5 {
    // searching popular words program
    private final static String[] EXCLUDED_WORDS = {"ponieważ", "oraz", "dlatego", "albo", "lecz", "gdyż", "żeby"};

    public static void main(String[] args) {
        String url = "https://www.onet.pl/";
        try {
            List<String> popularWords = getPopularWords(url);
            Path popularWordsPath = writeListToFile(popularWords, "popular_words.txt");
            List<String> filteredPopularWords = readListFromFile(popularWordsPath);
            filteredPopularWords.removeAll(Arrays.asList(EXCLUDED_WORDS));
            writeListToFile(filteredPopularWords, "filtered_popular_words.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Path writeListToFile(List<String> list, String uri) throws IOException {
        Path path = Paths.get(uri);
        return Files.write(path, list);
    }

    static List<String> readListFromFile(Path path) throws IOException {
        List<String> list = Files.readAllLines(path);
        return list;
    }

    static List<String> getPopularWords(String url) throws IOException {
        Connection connection = Jsoup.connect(url);
        Elements elements = connection.get().select("span.title");
        StringBuilder words = new StringBuilder();
        for (Element e : elements) {
            words.append(e.text());
        }
        List<String> popularWords = Arrays.stream(words.toString().split("[\\s\\p{Punct}\\d]"))
                .filter(x -> x.length() > 3)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        return popularWords;
    }

}
