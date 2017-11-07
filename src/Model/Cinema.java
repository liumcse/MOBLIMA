package Model;

/**
 * Created by LiuMingyu on 6/11/17.
 */

public class Cinema {
    private Constant.Cineplex cineplex;
    private boolean isPlatinum;
    private Constant.MovieType movieType;
    private String code;

    public Cinema(Constant.Cineplex cineplex) {
        this.cineplex = cineplex;
    }

    public Constant.Cineplex getCineplex() {
        return cineplex;
    }
}