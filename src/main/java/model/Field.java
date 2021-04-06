package model;


public class Field {
    private final int size;
    private final Piece[][] field;
    private Piece winner;
    private Piece myPiece;
    private Piece userPiece;

    public Field(int size) {
        this.size = size;
        this.field = new Piece[size][size];
    }

    public void update(int pieceNum) {
        if (hasEnded()) {
            return;
        }

        int y = pieceNum / size;
        int x = pieceNum % size;
        if (field[x][y] == Piece.EMPTY) {
            field[x][y] = userPiece;
            winner = checkForEnd(userPiece, x, y);
            if (winner == null) {
                makeMove();
            }
        }
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = Piece.EMPTY;
            }
        }
        winner = null;
    }

    public String getWinner() {
        return winner.toString();
    }

    public void setPieces(String pieceType) {
        this.userPiece = Piece.getPieceBySymbol(pieceType);
        this.myPiece = Piece.getInversePiece(userPiece);
        if (userPiece == Piece.NOUGHT) {
            makeMove();
        }
    }

    public boolean hasEnded() {
        return winner != null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                builder.append(field[i][j].toString());
            }
        }
        return builder.toString();
    }

    private void makeMove() {
        int bestScore = -2;
        int x = 0;
        int y = 0;
        int score;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[i][j] == Piece.EMPTY) {
                    field[i][j] = myPiece;
                    score = minimax(field, userPiece, i, j);
                    field[i][j] = Piece.EMPTY;

                    if (score > bestScore) {
                        bestScore = score;
                        x = i;
                        y = j;
                    }
                }
            }
        }
        field[x][y] = myPiece;
        winner = checkForEnd(myPiece, x, y);
    }

    private int minimax(Piece[][] field, Piece currentPiece, int xPrev, int yPrev) {
        Piece winner = checkForEnd(Piece.getInversePiece(currentPiece), xPrev, yPrev);
        if (winner != null) {
            return gameResult(winner);
        }

        boolean maximize = currentPiece == myPiece;
        int bestScore = maximize ? -2 : 2;
        int nextScore;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[i][j] == Piece.EMPTY) {
                    field[i][j] = currentPiece;
                    nextScore = minimax(field, Piece.getInversePiece(currentPiece), i, j);
                    field[i][j] = Piece.EMPTY;
                    if (maximize && nextScore > bestScore || !maximize && nextScore < bestScore) {
                        bestScore = nextScore;
                    }
                }
            }
        }
        return bestScore;
    }

    // returns null if game didn't end
    private Piece checkForEnd(Piece addedPiece, int x, int y) {
        for (int i = 0; i < size; i++) {
            if (field[i][y] != addedPiece) {
                break;
            }
            if (i == size - 1) {
                return addedPiece;
            }
        }

        for (int j = 0; j < size; j++) {
            if (field[x][j] != addedPiece) {
                break;
            }
            if (j == size - 1) {
                return addedPiece;
            }
        }

        if (x == y) {
            for (int i = 0; i < size; i++) {
                if (field[i][i] != addedPiece) {
                    break;
                }
                if (i == size - 1) {
                    return addedPiece;
                }
            }
        }

        if (x == (size - 1) - y) {
            for (int i = 0; i < size; i++) {
                if (field[i][(size - 1) - i] != addedPiece) {
                    break;
                }
                if (i == size - 1) {
                    return addedPiece;
                }
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[i][j] == Piece.EMPTY) {
                    return null;
                }
            }
        }
        return Piece.EMPTY;
    }

    public int getSize() {
        return size;
    }

    private int gameResult(Piece winner) {
        if (winner == myPiece) {
            return 1;
        } else if (winner == userPiece) {
            return -1;
        } else {
            return 0;
        }
    }
}
