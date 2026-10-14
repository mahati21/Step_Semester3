package categoryB.Class_problemsW8;

interface RentalVehicle {
    String getName();
    double getDailyRate();
}
class Sedan implements RentalVehicle {
    public String getName() { return "Sedan A"; }
    public double getDailyRate() { return 50; }
}
class SUV implements RentalVehicle {
    public String getName() { return "SUV B"; }
    public double getDailyRate() { return 80; }
}
class RentalCustomer {
    private final String name;
    RentalCustomer(String name) {
        this.name = name;
    }
    String getName() { return name; }
}
class VehicleRental {
    private final RentalVehicle vehicle;
    private final RentalCustomer customer;
    private final int days;
    private boolean returned;
    VehicleRental(RentalVehicle vehicle, RentalCustomer customer, int days) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
    }
    double getCharge() {
        return vehicle.getDailyRate() * days;
    }
    void returnVehicle() {
        returned = true;
        System.out.println(vehicle.getName() + " returned by " + customer.getName() + ".");
    }
    boolean isReturned() {
        return returned;
    }
}
class RentalService {
    private VehicleRental activeRental;
    void rent(RentalVehicle vehicle, RentalCustomer customer, int days) {
        if (days <= 0) {
            System.out.println("Rental days must be greater than zero.");
            return;
        }
        if (activeRental != null && !activeRental.isReturned()) {
            System.out.println(vehicle.getName() + " is currently unavailable.");
            return;
        }
        activeRental = new VehicleRental(vehicle, customer, days);
        System.out.printf("%s rented successfully by %s. Rental charge: $%.2f.%n",
                vehicle.getName(), customer.getName(), activeRental.getCharge());
    }
    void returnVehicle() {
        if (activeRental == null || activeRental.isReturned()) {
            System.out.println("There is no active rental to return.");
            return;
        }
        activeRental.returnVehicle();
    }
}
public class VehicleRentalDemo {
    public static void main(String[] args) {
        RentalService sedanService = new RentalService();

        RentalCustomer customer1 = new RentalCustomer("Customer 1");
        RentalCustomer customer2 = new RentalCustomer("Customer 2");
        RentalCustomer customer3 = new RentalCustomer("Customer 3");

        RentalVehicle sedan = new Sedan();
        RentalVehicle suv = new SUV();

        sedanService.rent(sedan, customer1, 3);
        sedanService.rent(sedan, customer2, 2);
        sedanService.returnVehicle();
        sedanService.rent(sedan, customer2, 2);

        RentalService suvService = new RentalService();
        suvService.rent(suv, customer3, 5);
    }
}
