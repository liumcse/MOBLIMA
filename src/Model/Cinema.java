package Model;

import Model.Constant.*;

import java.io.Serializable;

/**
 * Created by LiuMingyu on 6/11/17.
 */

public class Cinema implements Serializable {
    private Cineplex cineplex;
    private boolean isPlatinum;
    private boolean is3D;
    private String code;
    private double basePrice;

    public Cinema(Cineplex cineplex, boolean isPlatinum, boolean is3D, String code, double basePrice) {
        this.cineplex = cineplex;
        this.isPlatinum = isPlatinum;
        this.is3D = is3D;
        this.code = code;
        this.basePrice = basePrice;
    }

    public Cinema() {

    }

    public boolean isPlatinum() {
        return isPlatinum;
    }

    public Cineplex getCineplex() {
        return cineplex;
    }

    public String getCode() {
        return code;
    }

    public boolean is3D() {
        return is3D;
    }

    public void setBasePrice(double basePrice ) { this.basePrice = basePrice; }

    public double getBasePrice() {
        return basePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cinema cinema = (Cinema) o;

        return code.equals(cinema.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public String toString() {
        // TODO modify this
        return code;
    }
}