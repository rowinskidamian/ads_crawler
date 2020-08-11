package pl.damianrowinski;

import pl.damianrowinski.parsing.dao.DistrictUrlToParseDao;
import pl.damianrowinski.parsing.model.SingleAd;
import pl.damianrowinski.parsing.model.DistrictUrlToParse;
import pl.damianrowinski.parsing.parsers.ParseAdByUrl;
import pl.damianrowinski.parsing.parsers.ParsePagesNumber;

import java.util.*;

public class ParsingAdsApp {

    public static void start() {

        List<DistrictUrlToParse> listOfDistrictsToParse = getListOfDistrictsToParse();
        addAdsFromDistrictsToBase(listOfDistrictsToParse);

    }

    private static void addAdsFromDistrictsToBase(List<DistrictUrlToParse> listOfDistrictsToParse) {

        listOfDistrictsToParse.stream()
                .forEach(district -> {
                    System.out.println("Parsuję dzielnicę:" + district.getDistrictName());
                    List<String> listOfUrlsToParse = getListOfUrlToParse(district.getUrl());
                    addAdsToBase(listOfUrlsToParse, district.getDistrictName());
                });
    }

    private static List<DistrictUrlToParse> getListOfDistrictsToParse() {
        DistrictUrlToParseDao districtUrlToParseDao = new DistrictUrlToParseDao();
        return districtUrlToParseDao.findAll();
    }

    private static List<String> getListOfUrlToParse(String districtUrl) {
        List<String> listOfUrlsToParse = ParsePagesNumber.getFrom(districtUrl);
        System.out.println("Strony do sparsowania");
        listOfUrlsToParse.forEach(System.out::println);
        return listOfUrlsToParse;
    }

    private static void addAdsToBase(List<String> listOfUrlsToParse, String districtName) {
        Map<String, SingleAd> mapWithAdsAll = new LinkedHashMap<>();
        for (int i = 0; i < listOfUrlsToParse.size(); i++) {
            try {
                Thread.sleep(getRandomThreadMillis());

                Map<String, SingleAd> mapWithAds = ParseAdByUrl.addAdsToBaseFrom(listOfUrlsToParse.get(i), districtName);
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
