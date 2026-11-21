package com.example.seminar8;

public class ElementGalerie {
    private String urlImagine;
    private String descriere;
    private String urlPaginaWeb;

    public ElementGalerie(String urlImagine, String descriere, String urlPaginaWeb)
    {
        this.urlImagine = urlImagine;
        this.descriere = descriere;
        this.urlPaginaWeb = urlPaginaWeb;
    }

    public String getUrlImagine() {
        return urlImagine;
    }

    public String getDescriere() {
        return descriere;
    }

    public String getUrlPaginaWeb() {
        return urlPaginaWeb;
    }
}
