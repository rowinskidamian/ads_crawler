package parsers;

import dao.SingleAdDao;
import model.SingleAd;
import model.SingleAdsDB;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

public class ParseAdByUrl {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64)" +
            " AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36";

    public static Map<String, SingleAd> getFrom(String url, String district) {

        Connection connection = Jsoup.connect(url)
                .userAgent(USER_AGENT);
        SingleAdsDB singleAdsDB = new SingleAdsDB();
        SingleAdDao singleAdDao = new SingleAdDao();

        try {
            Document document = connection.get();
            Elements advertisementContainer = document.select("#offers_table .wrap");

            for (Element element : advertisementContainer) {
                String adTitle = element.select("h3 strong").text();
                String adLink = element.select("h3 a[href]").attr("href");
                String adPrice = element.select(".price strong").text();
                Date date = Date.valueOf(LocalDate.now());

                SingleAd singleAd = new SingleAd(adTitle, adLink, adPrice, date, district);
                singleAdsDB.addAdToDB(singleAd);
                singleAdDao.create(singleAd);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return singleAdsDB.getMapDB();
    }


}
