import model.Field;
import presenter.TicTacPresenter;
import view.TicTacFrame;
import view.TicTacView;

public class Main {
    private static final int FIELD_SIZE = 3;

    public static void main(String[] args) {
        TicTacView view = new TicTacFrame(FIELD_SIZE);
        Field field = new Field(FIELD_SIZE);
        TicTacPresenter presenter = new TicTacPresenter(field);
        presenter.setView(view);
        presenter.run();
    }
}
