import model.SingleAd;
import parsers.ParseAdByUrl;
import parsers.ParsePagesNumber;

import java.util.List;
import java.util.Map;

public class ParsingAdsApp {

    private static final String URL_URSYNOW = "https://www.olx.pl/nieruchomosci/stancje-pokoje/warszawa/q-pok%C3%B3j/" +
            "?search%5Bdistrict_id%5D=373";

    public static void main(String[] args) {

        List<String> listOfUrlsToParse = ParsePagesNumber.getFrom(URL_URSYNOW);

        listOfUrlsToParse.forEach(System.out::println);


//        Map<String, SingleAd> mapWithAds = ParseAdByUrl.getFrom(URL_URSYNOW);
//
//        for (String s : mapWithAds.keySet()) {
//            System.out.println(mapWithAds.get(s));
//        }

    }
}
