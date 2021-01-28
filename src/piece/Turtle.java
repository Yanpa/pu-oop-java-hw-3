package piece;

import board.GameBoard;
import board.GameTile;

import java.awt.*;

public class Turtle {
    private int col;
    private int row;
    private int positionInX;
    private int positionInY;

    /**
     * Конструктор, който взима като параметри колона, ред и графика, за да нарисува костенурката, без допълнително
     * извикване на методи от този клас.
     * @param row
     * @param col
     * @param g
     */
    public Turtle(int row, int col, Graphics g){
        this.row = row;
        this.col = col;

        this.positionInY = this.row * GameTile.TILE_SIDE_SIZE;
        this.positionInX = this.col * GameTile.TILE_SIDE_SIZE;

        renderTurtle(g);
    }

    /**
     * Рисува костенурка с прозрачен център и червена окръжност.
     * @param g
     */
    public void renderTurtle(Graphics g){
        g.setColor(Color.red);
        g.drawOval(positionInX + 25, positionInY + 55, 50, 50);
        g.setColor(new Color(0, 0, 0, 0));
        g.fillOval(positionInX + 25, positionInY + 60, 40, 40);
    }
}
