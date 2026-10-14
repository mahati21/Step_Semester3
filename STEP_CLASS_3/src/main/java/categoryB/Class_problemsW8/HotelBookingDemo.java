package categoryB.Class_problemsW8;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

abstract class Room {
    private int roomNumber;
    private boolean isAvailable;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isAvailable = true;
    }

    public int getRoomNumber() { return roomNumber; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    public abstract double calculatePrice(int days);
}

class StandardRoom extends Room {
    public StandardRoom(int roomNumber) { super(roomNumber); }
    @Override
    public double calculatePrice(int days) { return days * 100.0; }
}

class DeluxeRoom extends Room {
    public DeluxeRoom(int roomNumber) { super(roomNumber); }
    @Override
    public double calculatePrice(int days) { return days * 200.0; }
}

class Suite extends Room {
    public Suite(int roomNumber) { super(roomNumber); }
    @Override
    public double calculatePrice(int days) { return days * 350.0; }
}

class Customer {
    private String name;
    private List<Reservation> reservations;

    public Customer(String name) {
        this.name = name;
        this.reservations = new ArrayList<>();
    }

    public String getName() { return name; }
    public void addReservation(Reservation r) { reservations.add(r); }
    public void removeReservation(Reservation r) { reservations.remove(r); }
}

class Reservation {
    private Customer customer;
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate cancellationDeadline;
    private boolean isCancelled;

    public Reservation(Customer customer, Room room, LocalDate startDate, LocalDate endDate, LocalDate cancellationDeadline) {
        this.customer = customer;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cancellationDeadline = cancellationDeadline;
        this.isCancelled = false;
    }

    public Room getRoom() { return room; }
    public Customer getCustomer() { return customer; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public boolean isCancelled() { return isCancelled; }

    public boolean overlaps(LocalDate newStart, LocalDate newEnd) {
        return !(endDate.isBefore(newStart) || startDate.isAfter(newEnd));
    }

    public boolean cancel(LocalDate requestDate) {
        if (!isCancelled && !requestDate.isAfter(cancellationDeadline)) {
            isCancelled = true;
            return true;
        }
        return false;
    }
}

class Hotel {
    private List<Room> rooms = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();

    public void addRoom(Room room) { rooms.add(room); }
    public boolean checkAvailability(Room room, LocalDate start, LocalDate end) {
        for (Reservation r : reservations) {
            if (!r.isCancelled() && r.getRoom().getRoomNumber() == room.getRoomNumber()) {
                if (r.overlaps(start, end)) {
                    return false;
                }
            }
        }
        return true;
    }

    public Reservation createReservation(Customer customer, Room room, LocalDate start, LocalDate end, LocalDate deadline) {
        if (checkAvailability(room, start, end)) {
            Reservation res = new Reservation(customer, room, start, end, deadline);
            reservations.add(res);
            customer.addReservation(res);
            return res;
        }
        return null;
    }
}