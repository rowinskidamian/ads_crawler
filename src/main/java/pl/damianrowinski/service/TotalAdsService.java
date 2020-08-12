package pl.damianrowinski.service;

import pl.damianrowinski.data_analysis.model.DistrictAdsAmount;
import pl.damianrowinski.parsing.dao.SingleAdDao;
import pl.damianrowinski.parsing.model.SingleAd;

import java.util.LinkedList;
import java.util.List;

public class TotalAdsService {

    public static List<DistrictAdsAmount> getTotal() {
        List<String> baseNames = List.of("ochota_ads", "praga_polnoc_ads", "srodmiescie_ads", "targowek_ads",
                "ursynow_ads", "wola_ads");
        List<DistrictAdsAmount> districtAdsAmountList = new LinkedList<>();

        baseNames.forEach(base -> districtAdsAmountList.add(getDistrictAndSize(base)));

        return districtAdsAmountList;
    }

    private static DistrictAdsAmount getDistrictAndSize(String districtBaseName) {
        SingleAdDao singleAdDao = SingleAdDao.getSingleAdDaoForBase(districtBaseName);
        List<SingleAd> singleAdDaoAll = singleAdDao.findAll();
        return new DistrictAdsAmount(districtBaseName, singleAdDaoAll.size());
    }
}
