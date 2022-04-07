package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

public class FieldPanel extends JPanel {
    //fraction of square space occupied by piece
    private static final double PIECE_FRACTION = 0.75;
    private static final int LINE_WIDTH = 5;
    private final int fieldSize;
    private String field;

    public FieldPanel(int fieldSize, ClickListener listener) {
        this.fieldSize = fieldSize;
        this.field = "-".repeat(fieldSize * fieldSize);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                listener.onSquareClick(squareNum((int) event.getPoint().getX(), (int) event.getPoint().getY()));
            }

            // e.g. when touchpad is used
            @Override
            public void mousePressed(MouseEvent event) {
                listener.onSquareClick(squareNum((int) event.getPoint().getX(), (int) event.getPoint().getY()));
            }
        });
    }

    private int squareNum(int x, int y) {
        return y / squareHeight() * fieldSize + x / squareWidth();
    }

    private int squareWidth() {
        return (int) getSize().getWidth() / fieldSize;
    }

    private int squareHeight() {
        return (int) getSize().getHeight() / fieldSize;
    }

    private int squareXCenter(int j) {
        return (j * squareWidth()) + (squareWidth() / 2);
    }

    private int squareYCenter(int i) {
        return (i * squareHeight()) + (squareHeight() / 2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                drawSquare(g, i, j);
            }
        }
        drawField((Graphics2D) g);
    }

    public void setField(String field) {
        this.field = field;
    }

    private void drawField(Graphics2D g) {
        int xCenter;
        int yCenter;
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                xCenter = squareXCenter(j);
                yCenter = squareYCenter(i);
                drawPiece(g, field.charAt(i + j * fieldSize), xCenter, yCenter);
            }
        }
    }

    private void drawPiece(Graphics2D g, char piece, int xCenter, int yCenter) {
        g.setStroke(new BasicStroke(LINE_WIDTH));
        int xFrom = xCenter - (int) (PIECE_FRACTION * (squareWidth() / 2));
        int xTo = xCenter + (int) (PIECE_FRACTION * (squareWidth() / 2));
        int yFrom = yCenter - (int) (PIECE_FRACTION * (squareHeight() / 2));
        int yTo = yCenter + (int) (PIECE_FRACTION * (squareHeight() / 2));
        int width = (int) (PIECE_FRACTION * squareWidth());
        int height = (int) (PIECE_FRACTION * squareHeight());

        switch (piece) {
            case 'X' -> {
                g.draw(new Line2D.Double(xFrom, yFrom, xTo, yTo));
                g.drawLine(xFrom, yTo, xTo, yFrom);
            }
            case 'O' -> g.drawOval(xFrom, yFrom, width, height);
        }
    }

    private void drawSquare(Graphics g, int i, int j) {
        int x = j * squareWidth();
        int y = i * squareHeight();
        g.drawLine(x, y + squareHeight() - 1, x, y);
        g.drawLine(x, y, x + squareWidth() - 1, y);
        g.drawLine(x + 1, y + squareHeight() - 1, x + squareWidth() - 1, y + squareHeight() - 1);
        g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1, x + squareWidth() - 1, y + 1);
    }

}
