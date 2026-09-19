package categoryB.Class_problemsW5;

class MovieBookingProfile {
    private String name;
    private boolean confirmed;
    private String otp;
    public MovieBookingProfile() {
    }
    public MovieBookingProfile(String name) {
        this();
        this.name = name;
    }
    String getName() {
        return name;
    }
    void setName(String name) {
        this.name = name;
    }
    boolean isConfirmed() {
        return confirmed;
    }
    void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
    void setOtp(String otp) {
        if (otp != null && otp.matches("\\d{4,6}"))
        {
            this.otp = otp;
        }
    }
    public static void main(String[] args) {
        MovieBookingProfile p = new MovieBookingProfile("Rahul Dev");
        System.out.println(p.getName());
        p.setConfirmed(true);
        System.out.println(p.isConfirmed());
        p.setOtp("4471");
    }
}
