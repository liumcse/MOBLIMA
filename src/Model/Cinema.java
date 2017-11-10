package Model;

import java.io.Serializable;
import Model.Constant.*;

/**
 * Created by LiuMingyu on 6/11/17.
 */

public class Cinema implements Serializable {
    private Cineplex cineplex;
    private boolean isPlatinum;
    private MovieType movieType;
    private String code;
    private double basePrice;

    public Cinema(Cineplex cineplex, boolean isPlatinum, MovieType movieType, String code, double basePrice) {
        this.cineplex = cineplex;
        this.isPlatinum = isPlatinum;
        this.movieType = movieType;
        this.code = code;
        this.basePrice = basePrice;
    }

    public Cinema() {

    }

    public void setPlatinum(boolean isPlatinum) {
        this.isPlatinum = isPlatinum;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
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

    public MovieType getMovieType() {
        return movieType;
    }

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