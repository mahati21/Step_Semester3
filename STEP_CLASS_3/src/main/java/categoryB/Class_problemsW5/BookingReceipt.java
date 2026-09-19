package categoryB.Class_problemsW5;

import java.util.Arrays;

class BookingReceipt {
    protected final String bookingId;
    protected final String[] seatNumbers;
    public BookingReceipt(String bookingId, String[] seatNumbers) {
        this.bookingId = bookingId;
        this.seatNumbers = Arrays.copyOf(seatNumbers, seatNumbers.length);
    }
    String[] getSeatNumbers() {
        return Arrays.copyOf(seatNumbers, seatNumbers.length);
    }
    BookingReceipt withUpdatedSeat(int index, String newSeat) {
        String[] copy = Arrays.copyOf(seatNumbers, seatNumbers.length);
        copy[index] = newSeat;
        return new BookingReceipt(bookingId, copy);
    }
}
class GroupBookingReceipt extends BookingReceipt {
    int groupSize;
    public GroupBookingReceipt(String bookingId, String[] seatNumbers, int groupSize) {
        super(bookingId, seatNumbers);
        this.groupSize = groupSize;
    }
}
class BookingSettlement {
    static String processNightlySettlement(BookingReceipt[] receipts) {
        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;
        for (BookingReceipt receipt : receipts)
        {
            if (receipt == null)
            {
                nullSkipped++;
            }
            else
            {
                processed++;
                if (receipt instanceof GroupBookingReceipt)
                {
                    group++;
                }
                else
                {
                    individual++;
                }
            }
        }
        return processed + " processed | " + nullSkipped + " null skipped | " + group + " group | " + individual + " individual";
    }
    public static void main(String[] args) {
        BookingReceipt b = new BookingReceipt("CH-1001", new String[]{"A1", "A2"});
        String[] seats = b.getSeatNumbers();
        seats[0] = "X";
        System.out.println(b.getSeatNumbers()[0]);
        BookingReceipt updated = b.withUpdatedSeat(1, "A3");
        System.out.println(Arrays.toString(b.getSeatNumbers()));
        System.out.println(Arrays.toString(updated.getSeatNumbers()));
        BookingReceipt[] receipts = {
                new GroupBookingReceipt("CH-2002", new String[]{"B1", "B2"}, 2), null,
                new BookingReceipt("CH-3003", new String[]{"C1"})
        };
        System.out.println(BookingSettlement.processNightlySettlement(receipts));
    }
}
