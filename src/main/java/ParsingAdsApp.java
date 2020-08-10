import dao.UrlToParseDao;
import model.SingleAd;
import model.UrlDistricts;
import model.UrlToParse;
import parsers.ParseAdByUrl;
import parsers.ParsePagesNumber;

import java.util.*;

public class ParsingAdsApp {

    private static final String URL_URSYNOW = "https://www.olx.pl/nieruchomosci/stancje-pokoje/warszawa/q-pok%C3%B3j/" +
            "?search%5Bdistrict_id%5D=373";

    public static void main(String[] args) {

        Map<Integer, UrlToParse> mapOfDistrictsToParse = getMapOfDistrictsToParse();

        List<String> listOfUrlsToParse = getListOfUrlToParse();

        addAdsToBase(listOfUrlsToParse);
    }

    private static Map<Integer, UrlToParse> getMapOfDistrictsToParse() {
        UrlToParseDao urlToParseDao = new UrlToParseDao();
        return urlToParseDao.findAll();
    }

    private static List<String> getListOfUrlToParse() {
        List<String> listOfUrlsToParse = ParsePagesNumber.getFrom(URL_URSYNOW);
        System.out.println("Strony do sparsowania");
        listOfUrlsToParse.forEach(System.out::println);
        return listOfUrlsToParse;
    }

    private static void addAdsToBase(List<String> listOfUrlsToParse) {
        Map<String, SingleAd> mapWithAdsAll = new LinkedHashMap<>();
        for (int i = 0; i < listOfUrlsToParse.size(); i++) {
            try {
                Thread.sleep(getRandomThreadMillis());

                Map<String, SingleAd> mapWithAds = ParseAdByUrl.addAdsToBaseFrom(listOfUrlsToParse.get(i), "Ursynów");
                Set<String> stringSet = mapWithAds.keySet();
                stringSet.forEach(k -> mapWithAdsAll.put(k, mapWithAds.get(k)));
                System.out.println("Strona sprasowana: " + i);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
