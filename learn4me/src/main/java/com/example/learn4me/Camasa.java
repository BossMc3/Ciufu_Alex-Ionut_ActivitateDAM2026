package com.example.learn4me;

public class Camasa {
    private String material;
    private String marime;
    private int nrNasturi;
    private String culoare;
    private boolean is4Men;

    public Camasa(String material, String marime, int nrNasturi, String culoare, boolean is4Men) {
        this.material = material;
        this.marime = marime;
        this.nrNasturi = nrNasturi;
        this.culoare = culoare;
        this.is4Men = is4Men;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMarime() {
        return marime;
    }

    public void setMarime(String marime) {
        this.marime = marime;
    }

    public int getNrNasturi() {
        return nrNasturi;
    }

    public void setNrNasturi(int nrNasturi) {
        this.nrNasturi = nrNasturi;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public boolean isIs4Men() {
        return is4Men;
    }

    public void setIs4Men(boolean is4Men) {
        this.is4Men = is4Men;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Camasa{");
        sb.append("material='").append(material).append('\'');
        sb.append(", marime='").append(marime).append('\'');
        sb.append(", nrNasturi=").append(nrNasturi);
        sb.append(", culoare='").append(culoare).append('\'');
        sb.append(", is4Men=").append(is4Men);
        sb.append('}');
        return sb.toString();
    }
}
