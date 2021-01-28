package piece;

import board.GameBoard;
import board.GameTile;

import java.awt.*;

public class Guard {
    private int row;
    private int col;
    private Color color;
    private int positionInX;
    private int positionInY;

    public Guard(int row, int col, Color color){
        this.row = row;
        this.col = col;
        this.color = color;

        positionInX = col * GameTile.TILE_SIDE_SIZE;
        positionInY = row * GameTile.TILE_SIDE_SIZE;
    }

    /**
     * Взима цвета на зададената фигура, проверява го и рисува кръг със съответният цвят и рисува външен и по-голям
     * кръг с различен цвят около него
     * @param g
     */
    public void render(Graphics g){

        if(this.color.equals(Color.GREEN) || this.color.equals(Color.green)){
            g.setColor(Color.yellow);
            g.fillOval(positionInX + 25, positionInY + 55, 50, 50);

            g.setColor(this.color);
            g.fillOval(positionInX + 30, positionInY + 60, 40, 40);
        }

        if(this.color.equals(Color.YELLOW) || this.color.equals(Color.yellow)){
            g.setColor(Color.green);
            g.fillOval(positionInX + 25, positionInY + 55, 50, 50);

            g.setColor(this.color);
            g.fillOval(positionInX + 30, positionInY + 60, 40, 40);
        }
    }

    public Object moveGuard(int newRow, int newCol, Object[][] array){
        this.row = newRow;
        this.col = newCol;
        return array[row][col];
    }

    /**
     * Булев метод, който проверява дали хода, който играча избира е валиден, дали се намира в полето за игра и дали
     * се движи само с един ход по полето.
     * @param isThereBoardPiece
     * @return
     */
    public boolean isGuardMoveValid(boolean isThereBoardPiece) {
        boolean isInTheBoardCol = (GameBoard.releasePointCol <= 4 && GameBoard.releasePointCol >= 0);
        boolean isInTheBoardRow = (GameBoard.releasePointRow <= 4 && GameBoard.releasePointRow >= 0);
        boolean isReleaseInTheBoard = (isInTheBoardCol && isInTheBoardRow);

        boolean isOnlyOneMoveCol = (GameBoard.releasePointCol == GameBoard.tempCol + 1 || GameBoard.releasePointCol == GameBoard.tempCol - 1);
        boolean isOnlyOneMoveRow = (GameBoard.releasePointRow == GameBoard.tempRow + 1 || GameBoard.releasePointRow == GameBoard.tempRow - 1);
        boolean isOnlyOneMove = (isOnlyOneMoveCol || isOnlyOneMoveRow);

        if((isReleaseInTheBoard && isOnlyOneMove) && !isThereBoardPiece){
            System.out.println("The move was valid");
            return true;
        }
        System.out.println("Invalid move, try again");
        return false;
    }
}
