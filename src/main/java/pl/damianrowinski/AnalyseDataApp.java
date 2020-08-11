package pl.damianrowinski;

import pl.damianrowinski.parsing.dao.SingleAdDao;
import pl.damianrowinski.parsing.model.SingleAd;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnalyseDataApp {

    public static void main(String[] args) {

        String baseDistrictName = "ursynow_ads";

        SingleAdDao singleAdDaoUrsynow = SingleAdDao.getSingleAdDaoForBase(baseDistrictName);
        List<SingleAd> singleAdDaoUrsynowAll = singleAdDaoUrsynow.findAll();

        SingleAd singleAdMin = singleAdDaoUrsynowAll.stream()
                .min(Comparator.comparingInt(SingleAd::getPrice))
                .get();

        System.out.println("Najtańsze:");
        System.out.println(singleAdMin);

        long countLowerThan550 = singleAdDaoUrsynowAll.stream()
                .filter(a -> a.getPrice() < 550)
                .count();

        System.out.println("Liczba pokoi tańszych niż 550:");
        System.out.println(countLowerThan550);

        SingleAd singleAdMax = singleAdDaoUrsynowAll.stream()
                .max(Comparator.comparingInt(SingleAd::getPrice))
                .get();

        System.out.println("Najdroższe:");
        System.out.println(singleAdMax);

        long moreThan1600 = singleAdDaoUrsynowAll.stream()
                .filter(a -> a.getPrice() > 1400)
                .count();
        System.out.println("Liczba pokoi droższych niż 1600:");
        System.out.println(moreThan1600);

        System.out.println("Liczba pokoi w bazie:" + baseDistrictName);
        System.out.println(singleAdDaoUrsynowAll.size());

        System.out.println("Liczba pokoi z ceną, gdy odrzucimy 5% najtańsze i 1% najdroższe");

        System.out.println("Posortowane wg ceny:");
        List<SingleAd> sortedSingleAdsList = singleAdDaoUrsynowAll.stream()
                .sorted(Comparator.comparingInt(SingleAd::getPrice))
                .collect(Collectors.toList());

        int skip5percent = (int) ( sortedSingleAdsList.size() * 0.05);
        int limit1percent = (int) ( sortedSingleAdsList.size() * 0.01);
        int adsNumberWithoutFirst5percentAndLast1Percent = sortedSingleAdsList.size() - skip5percent - limit1percent;

        List<SingleAd> listWithout5percentLowAnd1PercentHighPrice = sortedSingleAdsList.stream()
                .skip(skip5percent)
                .limit(adsNumberWithoutFirst5percentAndLast1Percent)
                .collect(Collectors.toList());

        System.out.println("Liczba ogłoszeń bez 5% najtańszych i 1% najdroższych:");
        System.out.println(listWithout5percentLowAnd1PercentHighPrice.size());

//        System.out.println("Ogłoszenia posortowane:");
//        listWithout5percentLowAnd1PercentHighPrice.stream()
////                .forEach(System.out::println);

        long excludeDoubleRooms = listWithout5percentLowAnd1PercentHighPrice.stream()
                .filter(ad -> !ad.getTitle().contains("dwuosob"))
                .filter(ad -> !ad.getTitle().contains("2 osób"))
                .count();

        System.out.println("Liczba ogłoszeń bez \"dwuosob\" :");
        System.out.println(excludeDoubleRooms);


    }
}
