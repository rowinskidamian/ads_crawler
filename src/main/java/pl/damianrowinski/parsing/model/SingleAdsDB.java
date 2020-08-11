package pl.damianrowinski.parsing.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class SingleAdsDB {

    private Map<String, SingleAd> mapDB = new LinkedHashMap<>();

    public Map<String, SingleAd> getMapDB() {
        return mapDB;
    }

    public void setMapDB(Map<String, SingleAd> mapDB) {
        this.mapDB = mapDB;
    }

    public void addAdToDB(SingleAd singleAd) {
        String link = singleAd.getLink();
        mapDB.put(link, singleAd);
    }
}
