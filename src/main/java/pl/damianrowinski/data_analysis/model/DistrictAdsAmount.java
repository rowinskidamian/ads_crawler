package pl.damianrowinski.data_analysis.model;

public class DistrictAdsAmount {
    private String districtName;
    private int noOfAds;

    public DistrictAdsAmount(String districtName, int noOfAds) {
        this.districtName = districtName;
        this.noOfAds = noOfAds;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public int getNoOfAds() {
        return noOfAds;
    }

    public void setNoOfAds(int noOfAds) {
        this.noOfAds = noOfAds;
    }

    @Override
    public String toString() {
        return "DistrictAdsAmount{" +
                "districtName='" + districtName + '\'' +
                ", noOfAds=" + noOfAds +
                '}';
    }
}
