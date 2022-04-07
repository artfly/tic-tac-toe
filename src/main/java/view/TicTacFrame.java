package view;


import javax.swing.*;
import java.awt.*;

public class TicTacFrame extends JFrame {
    private static final String NAME = "TickTackToe";
    private static final String MENU = "Menu";
    private static final String NEW_GAME = "New game";
    private static final String EXIT = "Exit";

    private static final String[] PIECE_OPTIONS = {"X", "O"};
    private static final int INITIAL_CHOICE = 0;
    private static final String PIECE_QUESTION = "Which side are you on?";

    private static final String PIECE_TITlE = "Choose your side";
    private static final String WINNER_MESSAGE = "The winner is %s! Start a new game?";
    private static final String DRAW_MESSAGE = "It's a draw! Start a new game?";
    private static final String[] WINNER_OPTIONS = {"New game", "Cancel"};
    private static final String END_GAME = "End game";

    private final NewGameListener newGameListener;
    private FieldPanel fieldPanel;

    public TicTacFrame(int fieldSize, NewGameListener newGameListener, ClickListener clickListener) {
        super(NAME);

        this.newGameListener = newGameListener;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(600, 600));

        setupMenu();
        createFieldPanel(fieldSize, clickListener);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void setupMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu(MENU);

        JMenuItem newGameItem = new JMenuItem(NEW_GAME);
        JMenuItem exitItem = new JMenuItem(EXIT);

        newGameItem.addActionListener(event -> newGameListener.newGame());
        exitItem.addActionListener(event -> System.exit(0));

        menu.add(newGameItem);
        menu.add(exitItem);
        menuBar.add(menu);

        this.setJMenuBar(menuBar);
    }

    private void createFieldPanel(int fieldSize, ClickListener listener) {
        fieldPanel = new FieldPanel(fieldSize, listener);
        setContentPane(fieldPanel);
    }

    public String addPieceChoice() {
        int choice = JOptionPane.showOptionDialog(this, PIECE_QUESTION, PIECE_TITlE, JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, PIECE_OPTIONS, PIECE_OPTIONS[INITIAL_CHOICE]);
        if (choice == JOptionPane.CLOSED_OPTION) {
            choice = INITIAL_CHOICE;
        }
        return PIECE_OPTIONS[choice];
    }

    public void update(String field) {
        fieldPanel.setField(field);
        fieldPanel.repaint();
    }

    public void end(String winner) {
        String message = WINNER_MESSAGE;
        if (winner.trim().length() == 0) {
            message = DRAW_MESSAGE;
        }
        int choice = JOptionPane.showOptionDialog(this, String.format(message, winner), END_GAME,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, WINNER_OPTIONS, WINNER_OPTIONS[1]);
        if (choice == JOptionPane.YES_OPTION) {
            newGameListener.newGame();
        }
    }
}


