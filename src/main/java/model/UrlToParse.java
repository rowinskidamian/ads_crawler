package model;

public class UrlToParse {
    private int id;
    private String districtName;
    private String url;

    public UrlToParse(int id, String districtName, String url) {
        this.id = id;
        this.districtName = districtName;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "UrlToParse{" +
                "id=" + id +
                ", districtName='" + districtName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
