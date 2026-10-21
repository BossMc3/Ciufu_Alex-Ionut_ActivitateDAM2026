package com.example.exercitiuseminar4;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Apartament{
    private String adresa;
    private double pretEuro;
    private boolean areParcare;
    private String tipCompartimentare;

    private String cartier;
    private float evaloareStare;
    private boolean esteMobilat;

    public Apartament(String _adresa, double _pretEuro, boolean _areParcare, String _tipCompartimentare, String _Cartier, float _evaloareStare, boolean _esteMobilat)
    {
        this.adresa = _adresa;
        this.pretEuro = _pretEuro;
        this.areParcare = _areParcare;
        this.tipCompartimentare = _tipCompartimentare;
        this.cartier = _Cartier;
        this.evaloareStare = _evaloareStare;
        this.esteMobilat = _esteMobilat;
    }

    public String getAdresa() {
        return adresa;
    }
    public double getPretEuro() {
        return pretEuro;
    }
    public boolean isAreParcare() {
        return areParcare;
    }
    public String getTipCompartimentare() {
        return tipCompartimentare;
    }
    public String getCartier() {
        return cartier;
    }
    public float getEvaloareStare() {
        return evaloareStare;
    }
    public boolean isEsteMobilat() {
        return esteMobilat;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Apartament{");
        sb.append("adresa='").append(adresa).append('\'');
        sb.append(", pretEuro=").append(pretEuro);
        sb.append(", areParcare=").append(areParcare);
        sb.append(", tipCompartimentare='").append(tipCompartimentare).append('\'');
        sb.append(", cartier='").append(cartier).append('\'');
        sb.append(", evaloareStare=").append(evaloareStare);
        sb.append(", esteMobilat=").append(esteMobilat);
        sb.append('}');
        return sb.toString();
    }
}
