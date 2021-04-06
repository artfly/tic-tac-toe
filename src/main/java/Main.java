import model.Field;
import presenter.TicTacController;
import view.TicTacFrame;

public class Main {
    private static final int FIELD_SIZE = 3;

    public static void main(String[] args) {
        Field field = new Field(FIELD_SIZE);
        TicTacController controller = new TicTacController(field);
        controller.run();
    }
}
