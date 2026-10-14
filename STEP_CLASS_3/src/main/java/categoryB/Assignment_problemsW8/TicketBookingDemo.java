package categoryB.Assignment_problemsW8;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

enum SeatCategory {
    REGULAR(150.00),
    PREMIUM(250.00),
    RECLINER(400.00);
    private final double price;
    SeatCategory(double price) {
        this.price = price;
    }
    double getPrice() {
        return price;
    }
}
class Customer {
    private final String name;
    Customer(String name) {
        this.name = name;
    }
    String getName() {
        return name;
    }
}
class Seat {
    private final String id;
    private final SeatCategory category;
    Seat(String id, SeatCategory category) {
        this.id = id;
        this.category = category;
    }
    String getId() { return id; }
    double getPrice() { return category.getPrice(); }
}
class Show {
    private final String name;
    private final LocalDateTime startTime;
    private final List<Seat> bookedSeats = new ArrayList<>();

    Show(String name, LocalDateTime startTime) {
        this.name = name;
        this.startTime = startTime;
    }
    String getName() {
        return name;
    }
    boolean hasStarted() {
        return !LocalDateTime.now().isBefore(startTime);
    }
    boolean areAvailable(List<Seat> seats) {
        for (Seat seat : seats) {
            for (Seat booked : bookedSeats) {
                if (booked.getId().equalsIgnoreCase(seat.getId())) {
                    System.out.println("Seat " + seat.getId()
                            + " is already booked for this show.");
                    return false;
                }
            }
        }
        return true;
    }
    void reserve(List<Seat> seats) {
        bookedSeats.addAll(seats);
    }

    void release(List<Seat> seats) {
        bookedSeats.removeAll(seats);
    }
}
class Booking {
    private final Customer customer;
    private final Show show;
    private final List<Seat> seats;
    private boolean cancelled;
    Booking(Customer customer, Show show, List<Seat> seats) {
        this.customer = customer;
        this.show = show;
        this.seats = new ArrayList<>(seats);
    }
    double getTotal() {
        double total = 0;
        for (Seat seat : seats) {
            total += seat.getPrice();
        }
        return total;
    }
    boolean cancel() {
        if (cancelled) {
            System.out.println("This booking is already cancelled.");
            return false;
        }
        if (show.hasStarted()) {
            System.out.println("Cannot cancel: the show has already started.");
            return false;
        }
        show.release(seats);
        cancelled = true;
        System.out.println(customer.getName() + "'s booking cancelled. Seats "
                + seatIds() + " released.");
        return true;
    }
    String seatIds() {
        List<String> ids = new ArrayList<>();
        for (Seat seat : seats) {
            ids.add(seat.getId());
        }
        return String.join(", ", ids);
    }
    Customer getCustomer() { return customer; }
}
class TicketCounter {
    Booking book(Customer customer, Show show, List<Seat> seats) {
        if (seats == null || seats.isEmpty() || seats.size() > 6) {
            System.out.println("A booking must contain between 1 and 6 seats.");
            return null;
        }
        if (!show.areAvailable(seats)) {
            return null;
        }
        show.reserve(seats);
        Booking booking = new Booking(customer, show, seats);
        System.out.printf(Locale.US, "Booking confirmed for %s: %s. Total: ₹%.2f.%n", customer.getName(), booking.seatIds(), booking.getTotal());
        return booking;
    }
}

public class TicketBookingDemo {
    public static void main(String[] args) {
        TicketCounter counter = new TicketCounter();
        Show show = new Show("7 PM show", LocalDateTime.now().plusHours(2));

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Booking ashaBooking = counter.book(asha, show, Arrays.asList(new Seat("A1", SeatCategory.REGULAR), new Seat("A2", SeatCategory.REGULAR), new Seat("F5", SeatCategory.PREMIUM)));

        counter.book(ravi, show, List.of(new Seat("A2", SeatCategory.REGULAR)));

        counter.book(ravi, show, List.of(new Seat("R1", SeatCategory.RECLINER)));

        if (ashaBooking != null) {
            ashaBooking.cancel();
        }
        counter.book(neha, show, List.of(new Seat("A2", SeatCategory.REGULAR)));
    }
}
