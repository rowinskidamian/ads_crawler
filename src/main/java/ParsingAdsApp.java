import dao.SingleAdDao;
import model.SingleAd;
import parsers.ParseAdByUrl;
import parsers.ParsePagesNumber;

import java.util.*;

public class ParsingAdsApp {

    private static final String URL_URSYNOW = "https://www.olx.pl/nieruchomosci/stancje-pokoje/warszawa/q-pok%C3%B3j/" +
            "?search%5Bdistrict_id%5D=373";

    public static void main(String[] args) {

        List<String> listOfUrlsToParse = ParsePagesNumber.getFrom(URL_URSYNOW);
        SingleAdDao singleAdDao = new SingleAdDao();
        System.out.println("Strony do sparsowania");
        listOfUrlsToParse.forEach(System.out::println);

        Map<String, SingleAd> mapWithAdsAll = new LinkedHashMap<>();

//        Map<String, SingleAd> ursynowAds = ParseAdByUrl.getFrom(URL_URSYNOW, "Ursynów");
//        Set<String> keysMapUrsynowSet = ursynowAds.keySet();

        for (int i = 0; i < listOfUrlsToParse.size(); i++) {
            try {
                Thread.sleep(getRandomThreadMillis());

                Map<String, SingleAd> mapWithAds = ParseAdByUrl.getFrom(listOfUrlsToParse.get(i), "Ursynów");
                Set<String> stringSet = mapWithAds.keySet();
                stringSet.forEach(k -> mapWithAdsAll.put(k, mapWithAds.get(k)));
                System.out.println("Strona sprasowana: " + i);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        System.out.println("Lista ogłoszeń:");
//
//        for (String s : mapWithAdsAll.keySet()) {
//            System.out.println(mapWithAdsAll.get(s));
//        }
        System.out.println("Liczba ogłoszeń w bazie:");
        System.out.println(mapWithAdsAll.size());
    }

    private static int getRandomThreadMillis() {
        Random random = new Random();
        int currentRandom;
        while (true) {
            currentRandom = random.nextInt(1500);
            if (currentRandom > 1000)
                return currentRandom;
        }
    }
}
