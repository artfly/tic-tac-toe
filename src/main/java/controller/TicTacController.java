package controller;

import model.Field;
import view.ClickListener;
import view.NewGameListener;
import view.TicTacFrame;

public class TicTacController implements Runnable, ClickListener, NewGameListener {
    private final Field field;
    private final TicTacFrame frame;

    public TicTacController(Field field) {
        this.field = field;
        this.frame = new TicTacFrame(field.getSize(), this, this);
    }

    public void run() {
        newGame();
    }

    @Override
    public void newGame() {
        field.clear();
        frame.update(field.toString());
        String userPiece = frame.addPieceChoice();
        setPieces(userPiece);
    }

    @Override
    public void onSquareClick(int squareNum) {
        field.update(squareNum);
        frame.update(field.toString());
        if (field.hasEnded()) {
            frame.end(field.getWinner());
        }
    }

    private void setPieces(String userPiece) {
        field.setPieces(userPiece);
        frame.update(field.toString());
    }
}
