package model;

import java.util.Objects;

public class SingleAd {
    private String title;
    private String link;
    private String price;

    public SingleAd(String title, String link, String price) {
        this.title = title;
        this.link = link;
        this.price = price;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
