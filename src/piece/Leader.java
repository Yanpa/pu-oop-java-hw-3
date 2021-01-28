package piece;

import board.GameBoard;
import board.GameTile;

import java.awt.*;

public class Leader {
    GameBoard gameBoard = null;
    //Поставяме няколко статични булеви променливи, чрез които от въведените координати разбираме на къде ще се движи лидера
    public static boolean isItMovingNorth = (GameBoard.releasePointCol < GameBoard.tempCol);
    public static boolean isItMovingSouth = (GameBoard.releasePointCol > GameBoard.tempCol);
    public static boolean isItMovingEast = (GameBoard.releasePointRow > GameBoard.tempRow);
    public static boolean isItMovingWest = (GameBoard.releasePointRow < GameBoard.tempRow);

    private int row;
    private int col;
    private int positionInX;
    private int positionInY;
    private Color color;


    boolean isInTheBoardCol = (GameBoard.releasePointCol <= 4 && GameBoard.releasePointCol >= 0);
    boolean isInTheBoardRow = (GameBoard.releasePointRow <= 4 && GameBoard.releasePointRow >= 0);
    boolean isReleaseInTheBoard = (isInTheBoardCol && isInTheBoardRow);


    public Leader(int row, int col, Color color){
        this.row = row;
        this.col = col;
        this.color = color;

        positionInX = col * GameTile.TILE_SIDE_SIZE;
        positionInY = row * GameTile.TILE_SIDE_SIZE;
    }

    /**
     * Рисува лидера
     * @param g
     */
    public void render(Graphics g){
        g.setColor(this.color);
        g.fillRect(positionInX + 20, positionInY + 50, 60 ,60);
    }


    /**
     * Следващите четири метода проверяват дали ходовете които, играча иска да направи са валидни, като използвам
     * while(), който скъсява разстоянието между началната точка и избраната точка и чрез if(), ако срещне друга
     * фигура по пътя си връща, че ходът е грешен.
     * @return
     */
    public boolean isLeaderMoveNorthValid(){
        int startingPosition = GameBoard.tempCol;
        int endPoint = GameBoard.releasePointCol;

        if(isReleaseInTheBoard){
            while(endPoint != startingPosition){
                if(gameBoard.thereIsBoardPiece(GameBoard.tempRow, startingPosition)){
                    System.out.println("Move is Invalid");
                    return false;
                }
                startingPosition--;
            }
            System.out.println("Move is Valid");
            return true;
        }
        return false;
    }

    public boolean isLeaderMoveSouthValid(){
        int startingPosition = GameBoard.tempCol;
        int endPoint = GameBoard.releasePointCol;

        if(isReleaseInTheBoard){
            while(endPoint != startingPosition){
                if(gameBoard.thereIsBoardPiece(GameBoard.tempRow, startingPosition)){
                    System.out.println("Move is Invalid");
                    return false;
                }
                startingPosition++;
            }
            System.out.println("Move is Valid");
            return true;
        }
        return false;
    }

    public boolean isLeaderMoveWestValid(){
        int startingPosition = GameBoard.tempRow;
        int endPoint = GameBoard.releasePointRow;

        if(isReleaseInTheBoard){
            while(endPoint != startingPosition){
                if(gameBoard.thereIsBoardPiece(GameBoard.tempCol, startingPosition)){
                    System.out.println("Move is Invalid");
                    return false;
                }
                startingPosition--;
            }
            System.out.println("Move is Valid");
            return true;
        }
        return false;
    }

    public boolean isLeaderMoveEastValid(){
        int startingPosition = GameBoard.tempRow;
        int endPoint = GameBoard.releasePointRow;

        if(isReleaseInTheBoard){
            while(endPoint != startingPosition){
                if(gameBoard.thereIsBoardPiece(GameBoard.tempCol, startingPosition)){
                    System.out.println("Move is Invalid");
                    return false;
                }
                startingPosition++;
            }
            System.out.println("Move is Valid");
            return true;
        }
        return false;
    }
}
