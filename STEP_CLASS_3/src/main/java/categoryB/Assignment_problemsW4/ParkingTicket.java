package categoryB.Assignment_problemsW4;

class ParkingTicket {
    String vehicleNo;
    double ratePerMinute;
    public ParkingTicket(String vehicleNo, double ratePerMinute)
    {
        this.vehicleNo = vehicleNo;
        this.ratePerMinute = ratePerMinute;
    }
    final double calculateFine(int overstayMinutes)
    {
        return overstayMinutes * ratePerMinute;
    }
    final void printReceipt(int overstayMinutes)
    {
        System.out.println(vehicleNo + " - Fine: Rs " + calculateFine(overstayMinutes));
    }

    public static void main(String[] args) {
        String[] vehicleNos = {"TN09AB1234","TN22CD5678","TN09EF9012","TN10GH3456"};
        double[] rates = {2, 2, 3, 2};
        int[] minutes = {15, 0, -5, 8};
        for (int i = 0; i < vehicleNos.length; i++)
        {
            ParkingTicket t = new ParkingTicket(vehicleNos[i], rates[i]);
            if (minutes[i] > 0) t.printReceipt(minutes[i]);
            else System.out.println(vehicleNos[i] + " - No fine, within allotted time");
        }
    }
}

