package categoryB.Class_problemsW9;

public class W9LibraryCatalogLookup {
    static class W9LibraryBook {
        String isbn;
        String title;
        W9LibraryBook(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }
    }
    public static String findBook(
            W9LibraryBook[] catalog, String targetIsbn) {
        int low = 0;
        int high = catalog.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = catalog[mid].isbn.compareTo(targetIsbn);

            if (comparison == 0) {
                return catalog[mid].title;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return "Not Found";
    }

    public static void main(String[] args) {
        W9LibraryBook[] catalog = {
                new W9LibraryBook("0001112223", "Introduction to Algebra"),
                new W9LibraryBook("0002223334", "Beginning Python"),
                new W9LibraryBook("0003334445", "Classic Mythology"),
                new W9LibraryBook("0004445556", "Data and Society"),
                new W9LibraryBook("0005556667", "European History")
        };
        System.out.println(findBook(catalog, "0003334445"));
    }
}