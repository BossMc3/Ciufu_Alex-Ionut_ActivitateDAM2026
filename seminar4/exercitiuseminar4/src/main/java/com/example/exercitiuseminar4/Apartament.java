package com.example.exercitiuseminar4;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Apartament implements Parcelable {
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

    protected Apartament(Parcel in) {
        adresa = in.readString();
        pretEuro = in.readDouble();
        areParcare = in.readByte() != 0;
        tipCompartimentare = in.readString();
        cartier = in.readString();
        evaloareStare = in.readFloat();
        esteMobilat = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(adresa);
        dest.writeDouble(pretEuro);
        dest.writeByte((byte) (areParcare ? 1 : 0));
        dest.writeString(tipCompartimentare);
        dest.writeString(cartier);
        dest.writeFloat(evaloareStare);
        dest.writeByte((byte) (esteMobilat ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Apartament> CREATOR = new Creator<Apartament>() {
        @Override
        public Apartament createFromParcel(Parcel in) {
            return new Apartament(in);
        }

        @Override
        public Apartament[] newArray(int size) {
            return new Apartament[size];
        }
    };

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


    public void setDate(String adresa, double pretEuro,
                        boolean areParcare,
                        String tipCompartimentare,
                        String cartier,
                        float evaloareStare,
                        boolean esteMobilat) {

        this.adresa = adresa;
        this.pretEuro = pretEuro;
        this.areParcare = areParcare;
        this.tipCompartimentare = tipCompartimentare;
        this.cartier = cartier;
        this.evaloareStare = evaloareStare;
        this.esteMobilat = esteMobilat;
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
