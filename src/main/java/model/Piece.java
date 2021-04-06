package model;

public enum Piece {
    NOUGHT("O"),
    CROSS("X"),
    EMPTY(" ");

    private final String symbol;

    Piece(String symbol) {
        this.symbol = symbol;
    }

    public static Piece getPieceBySymbol(String symbol) {
        if (symbol.equals(NOUGHT.symbol)) {
            return NOUGHT;
        } else if (symbol.equals(CROSS.symbol)) {
            return CROSS;
        } else {
            return EMPTY;
        }
    }

    public static Piece getInversePiece(Piece piece) {
        if (piece == CROSS) {
            return NOUGHT;
        } else if (piece == NOUGHT) {
            return CROSS;
        } else {
            return EMPTY;
        }
    }

    @Override
    public String toString() {
        return symbol;
    }
}
