package Model;

import Model.Constant.*;

import java.io.Serializable;

/**
 * This class contains all information of a cinema - including its cineplex,
 * whether it is platinum, whether it is 3D cinema, the cinema code and the
 * base price of the cinema.
 *
 * @version 1.0
 */
public class Cinema implements Serializable {
    private Cineplex cineplex;
    private boolean isPlatinum;
    private boolean is3D;
    private String code;
    private double basePrice;

    /**
     * Allocates a {@code Cinema} object and initializes it specified by its
     * cineplex, whether it is boolean, whether it is 3D cinema, the cinema
     * code and the base price of the cinema.
     * @param cineplex the cineplex of the cinema
     * @param isPlatinum true if the cinema is platinum, false if not
     * @param is3D true if the cinema is a 3D cinema, false if not
     * @param code the cinema code
     * @param basePrice the base price of the cinema
     */
    public Cinema(Cineplex cineplex, boolean isPlatinum, boolean is3D, String code, double basePrice) {
        this.cineplex = cineplex;
        this.isPlatinum = isPlatinum;
        this.is3D = is3D;
        this.code = code;
        this.basePrice = basePrice;
    }

    /**
     * This method is to get whether the cinema is platinum.
     * @return true if the cinema is platinum, false if not
     */
    public boolean isPlatinum() {
        return isPlatinum;
    }

    /**
     * This method is to get the cineplex of the cinema.
     * @return the cineplex of the cinema
     */
    public Cineplex getCineplex() {
        return cineplex;
    }

    /**
     * This method is to get the cinema code.
     * @return the cinema code
     */
    public String getCode() {
        return code;
    }

    /**
     * This method is to get whether the cinema is a 3D cinema.
     * @return true if the cinema is platinum, false if not
     */
    public boolean is3D() {
        return is3D;
    }

    /**
     * This method is to set the base price of the cinema
     * @param basePrice the base price to be assigned
     */
    public void setBasePrice(double basePrice ) { this.basePrice = basePrice; }

    /**
     * This method is to get the base price of the cinema
     * @return the base price of the cinema
     */
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