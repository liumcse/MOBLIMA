public class Constant {
    public enum Status {

        COMMING_SOON("Coming soon"),
        PREVIEW("Preview"),
        NOW_SHOWING("Now showing");

        private String status;
        Status(String status) {
            this.status = status;
        }

        public String value() {return this.status;}
    }
}
