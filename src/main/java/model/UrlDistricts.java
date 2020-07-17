package model;

public enum UrlDistricts {
    URSYNOW("https://www.olx.pl/nieruchomosci/stancje-pokoje/warszawa/q-pok%C3%B3j/?search%5Bdistrict_id%5D=373");

    private String http;

    UrlDistricts(String http) {
        this.http = http;
    }
}
