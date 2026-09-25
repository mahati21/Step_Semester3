package categoryB.Assignment_problemsW7;

abstract class ArtPiece {
    private static int counter = 0;
    private final String pieceId;
    public ArtPiece() {
        counter++;
        pieceId = "ART-" + counter;
    }
    public abstract String describe();
    public String getPieceId() {
        return pieceId;
    }
}
class Painting extends ArtPiece {
    private String title;
    public Painting(String title) {
        this.title = title;
    }
    @Override
    public String describe() {
        return "Painting: " + title + ", framed on canvas";
    }
}
class Sculpture extends ArtPiece {
    private String title;
    public Sculpture(String title) {
        this.title = title;
    }
    @Override
    public String describe() {
        return "Sculpture: " + title + ", carved from stone";
    }
}
