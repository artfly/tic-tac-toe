package presenter;

import model.Field;
import model.FieldListener;
import view.TicTacView;

public class TicTacPresenter implements Runnable, FieldListener {
    private TicTacView view;
    private final Field field;

    public TicTacPresenter(Field field) {
        this.field = field;
    }

    public void setView(TicTacView view) {
        this.view = view;
    }

    public void run() {
        field.setListener(this);
        view.attachPresenter(this);
        newGame();
    }

    public void addPiece(int pieceNum) {
        field.update(pieceNum);
    }

    @Override
    public void update(boolean hasEnded) {
        view.update(field.toString());
        if (hasEnded) {
            view.end(field.getWinner());
        }
    }

    public void newGame() {
        field.clear();
        view.start(field.toString());
    }

    public void setUserPiece(String pieceType) {
        field.setPieces(pieceType);
    }
}
