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

    public Cinema(Cineplex cineplex, boolean isPlatinum, MovieType movieType, String code) {
        this.cineplex = cineplex;
        this.isPlatinum = isPlatinum;
        this.movieType = movieType;
        this.code = code;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public boolean isPlatinum() {
        return isPlatinum;
    }

    public Cineplex getCineplex() {
        return cineplex;
    }

    @Override
    public String toString() {
        // TODO modify this
        return code;
    }
}