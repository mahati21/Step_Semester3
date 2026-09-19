package categoryB.Assignment_problemsW5;

import java.util.Arrays;
class LoanReceipt {
    protected final String memberId;
    protected final String[] bookIds;
    public LoanReceipt(String memberId, String[] bookIds) {
        this.memberId = memberId;
        this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
    }
    String[] getBookIds()
    {
        return Arrays.copyOf(bookIds, bookIds.length);
    }
    LoanReceipt withCorrectedBookId(int index, String newId)
    {
        String[] copy = Arrays.copyOf(bookIds, bookIds.length);
        copy[index] = newId;
        return new LoanReceipt(memberId, copy);
    }
}
class ReferenceOnlyLoanReceipt extends LoanReceipt {
    String roomNumber;
    public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber)
    {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }
}
class CirculationLedger {
    static String branchCode;
    static
    {
        branchCode = "PT-001";
    }
    static String processNightlyCirculation(LoanReceipt[] receipts) {
        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;
        for (LoanReceipt receipt : receipts)
        {
            if (receipt == null)
            {
                nullSkipped++;
            }
            else
            {
                processed++;
                if (receipt instanceof ReferenceOnlyLoanReceipt)
                {
                    referenceOnly++;
                }
                else
                {
                    regular++;
                }
            }
        }
        return processed + " processed | " + nullSkipped + " null skipped | " +
                referenceOnly + " reference-only | " + regular + " regular";
    }
    public static void main(String[] args) {
        LoanReceipt r = new LoanReceipt("LIB-8841", new String[]{"BK-100", "BK-101"}
        );
        String[] ids = r.getBookIds();
        ids[0] = "HACKED";
        System.out.println(r.getBookIds()[0]);
        LoanReceipt corrected = r.withCorrectedBookId(1, "BK-102");
        System.out.println(Arrays.toString(r.getBookIds()));
        System.out.println(Arrays.toString(corrected.getBookIds()));
        LoanReceipt[] receipts = {
                new ReferenceOnlyLoanReceipt("LIB-001", new String[]{"BK-200"}, "Reading Room 3"), null,
                new LoanReceipt("LIB-002", new String[]{"BK-201"})
        };
        System.out.println(CirculationLedger.processNightlyCirculation(receipts));
    }
}
