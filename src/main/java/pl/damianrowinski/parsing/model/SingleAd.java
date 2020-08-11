package pl.damianrowinski.parsing.model;

import java.sql.Date;
import java.util.Objects;

public class SingleAd {
    private int id;
    private String title;
    private String link;
    private int price;
    private Date dateUploaded;
    private String district;

    public SingleAd() {
    }

    public SingleAd(String title, String link, String price, Date dateUploaded, String district) {
        this.title = title;
        this.link = link;
        this.price = getPriceFromString(price);
        this.dateUploaded = dateUploaded;
        this.district = district;
    }

    public SingleAd(int id, String title, String link, int price, Date dateUploaded, String district) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.price = price;
        this.dateUploaded = dateUploaded;
        this.district = district;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDateUploaded() {
        return dateUploaded;
    }

    public void setDateUploaded(Date dateUploaded) {
        this.dateUploaded = dateUploaded;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    private int getPriceFromString(String price) {
        String replacement = price.replaceAll("[\\szł]", "");
        String[] split = replacement.split(",");
        replacement = split[0];
        return Integer.parseInt(replacement);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleAd that = (SingleAd) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(link, that.link) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, link, price);
    }

    @Override
    public String toString() {
        return title + ", " + price + ", " + link;
    }
}
