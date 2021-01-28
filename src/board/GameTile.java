package board;

import java.awt.*;

public class GameTile {
    public static final int TILE_SIDE_SIZE = 100;
    public static final int THE_BAR_SIZE = 30;

    private Color color;
    private int col;
    private int row;

    public GameTile(int row, int col, Color color){
        this.row = row;
        this.col = col;
        this.color = color;
    }

    /**
     * Разделя полето на различни квадрати и чертае един квадрат, който играе ролята на бордер, след това бива запълнен
     * с цвета на съответната плочка
     * @param g
     */
    public void render(Graphics g) {
        int positionByX = this.col * TILE_SIDE_SIZE;
        int positionByY = this.row * TILE_SIDE_SIZE;

        g.setColor(this.color);
        g.fillRect(positionByX, positionByY + THE_BAR_SIZE, TILE_SIDE_SIZE, TILE_SIDE_SIZE);

        g.setColor(Color.black);
        g.drawRect(positionByX, positionByY + THE_BAR_SIZE, TILE_SIDE_SIZE, TILE_SIDE_SIZE);

        if(this.row == 2 && this.col == 2){
            g.setColor(Color.gray);
            g.fillOval(positionByX + 20, positionByY + THE_BAR_SIZE + 20, 60, 60);
        }
    }
}
