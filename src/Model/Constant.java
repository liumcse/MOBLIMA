package Model;

/**
 * This class contains all the Enum classes
 *
 * @version 1.0
 */
public class Constant {
    /**
     * Movie status
     */
    public enum MovieStatus {
        COMING_SOON("Coming soon"),
        END_OF_SHOWING("End of showing"),
        NOW_SHOWING("Now showing");

        private String status;
        MovieStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() { return status; }

    }

    /**
     * Cineplex
     */
    public enum Cineplex {
        JEM("JEM"),
        TheCathay("The Cathay"),
        WestMall("West Mall"),
        AMK_Hub("AMK Hub");

        private String cineplex;

        Cineplex(String cineplex) {
            this.cineplex = cineplex;
        }

        @Override
        public String toString() { return cineplex; }
    }

    /**
     * Age restriction
     */
    public enum AgeRestriction {
        G("G"), PG("PG"), PG13("PG13"), NC16("NC16"), M18("M18"), R21("R21");

        private String movieRestriction;
        AgeRestriction(String movieRestriction) {
            this.movieRestriction = movieRestriction;
        }

        @Override
        public String toString() {
            return movieRestriction;
        }
    }
}
