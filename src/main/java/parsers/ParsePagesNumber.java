package parsers;

import model.SingleAd;
import model.SingleAdsDB;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ParsePagesNumber {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64)" +
            " AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36";

    public static List<String> getFrom(String url) {
        List<String> list = new ArrayList<>();


        Connection connection = Jsoup.connect(url)
                .userAgent(USER_AGENT);
        try {
            Document document = connection.get();
            Elements pagesList = document.select("[data-cy=page-link-last]");
            String text = pagesList.text();

            int noOfPages = Integer.parseInt(text);

            for (int i = 0; i < noOfPages; i++) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(url)
                        .append("&page=")
                        .append(i+1);
                list.add(stringBuilder.toString());
            }


        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return list;
    }
}
