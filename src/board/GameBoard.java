package board;

import piece.Guard;
import piece.Leader;
import piece.Turtle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class GameBoard extends JFrame implements MouseListener {
    public static final int NUMBER_OF_TILES_ON_SIDE = 5;
    public static int tempRow, tempCol, releasePointRow, releasePointCol;

    private Object[][] pieceCollection;
    private Object selection = new Object();


    public GameBoard(){
        this.pieceCollection = new Object[NUMBER_OF_TILES_ON_SIDE][NUMBER_OF_TILES_ON_SIDE];

        for(int row = 0; row < NUMBER_OF_TILES_ON_SIDE; row++){
            for(int col = 0; col<NUMBER_OF_TILES_ON_SIDE; col++){
                pieceCollection[row][col] = null;
            }
        }

        //Leaders
        this.pieceCollection[0][4] = (new Leader(0,4, Color.YELLOW));
        this.pieceCollection[4][0] = (new Leader(4,0,Color.GREEN));

        //Guards
        this.pieceCollection[0][0] = (new Guard(0, 0, Color.yellow));
        this.pieceCollection[0][1] = (new Guard(0, 1, Color.yellow));
        this.pieceCollection[0][2] = (new Guard(0, 2, Color.YELLOW));
        this.pieceCollection[0][3] = (new Guard(0, 3, Color.YELLOW));

        this.pieceCollection[4][1] = (new Guard(4, 1, Color.green));
        this.pieceCollection[4][2] = (new Guard(4, 2, Color.green));
        this.pieceCollection[4][3] = (new Guard(4, 3, Color.GREEN));
        this.pieceCollection[4][4] = (new Guard(4, 4, Color.GREEN));


        this.setSize(500,530);
        this.setName("CitySmashNewGen...");
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addMouseListener(this);
    }

    /**
     * Колкото и да мисля, не мога да стигна до решението на проблема, ще продължа да работя по него, но определено, ще
     * е след крайният срок, затова ще трябва да го предам така.
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        //TODO: God help us all
        int selectedColumn = this.getCoordinates(e.getX());
        int selectedRow = this.getCoordinates(e.getY() - GameTile.THE_BAR_SIZE);
        tempCol = selectedColumn;
        tempRow = selectedRow;

        if(this.selection != null || thereIsBoardPiece(selectedRow, selectedColumn) && isBoardPieceGuard(tempRow, tempCol)){
            Guard g = (Guard) selection;
            if(g.isGuardMoveValid(thereIsBoardPiece(releasePointRow, releasePointCol))){
                g.moveGuard(releasePointRow, releasePointCol, (Object[][]) pieceCollection[releasePointRow][releasePointCol]);
                pieceCollection[tempRow][tempCol] = null;
                tempRow = 0;
                tempCol = 0;
                this.repaint();

                this.selection = null;
            }
        }

        if(this.selection != null || thereIsBoardPiece(selectedRow, selectedColumn) && isBoardPieceLeader(tempRow, tempCol)){
            Leader leader = (Leader) selection;
            if(Leader.isItMovingEast);
            if(Leader.isItMovingNorth);
            if(Leader.isItMovingSouth);
            if(Leader.isItMovingWest);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        releasePointCol = this.getCoordinates(e.getX());
        releasePointRow = this.getCoordinates(e.getY() - GameTile.THE_BAR_SIZE);

        System.out.println("Release point Col: " + releasePointCol);
        System.out.println("Release point Row: " + releasePointRow);

        if(this.selection != null && thereIsBoardPiece(tempRow, tempCol) && isBoardPieceGuard(tempRow, tempCol)){
            Guard g = (Guard)this.selection;
            if(g.isGuardMoveValid(thereIsBoardPiece(releasePointRow, releasePointCol))){

                //Проверява дали на плочката, на която пускаме мишката има Turtle, ако има унищожава избраният гард и съответният Turtle
                if(isBoardPieceTurtle(releasePointRow, releasePointCol)){
                    pieceCollection[releasePointRow][releasePointCol] = null;
                    pieceCollection[tempRow][tempCol] = null;
                }

                else {
                    g.moveGuard(releasePointRow, releasePointCol, (Object[][]) pieceCollection[releasePointRow][releasePointCol]);
                    pieceCollection[tempRow][tempCol] = null;
                    tempRow = 0;
                    tempCol = 0;
                    this.repaint();

                    this.selection = null;
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    /**
     * Рисува полето, като разпределя всички плочки и фигури върху дъската, взимайки координатите на колоните и редовете
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        Random rand = new Random();
        int randColPositionOfTurtle = rand.nextInt(2);
        int randColPositionOfSecondTurtle = rand.nextInt(2) + 3;

        for (int row = 0; row < NUMBER_OF_TILES_ON_SIDE; row++){
            for (int col = 0; col < NUMBER_OF_TILES_ON_SIDE; col++){
                renderGameTile(row, col, g);
                renderPiece(row, col, g);
            }
        }

        //Добавям двете костенурки в ArrayList-a и ги поставям върху дъската на случаен принцип
        pieceCollection[2][randColPositionOfTurtle] = new Turtle(2, randColPositionOfTurtle, g);
        pieceCollection[2][randColPositionOfSecondTurtle] = new Turtle(2, randColPositionOfSecondTurtle, g);
    }

    /**
     *
     * @param dimension
     * @return Връща едно число, вместо трицифрени координати
     */
    private int getCoordinates(int dimension){
        return dimension / GameTile.TILE_SIDE_SIZE;
    }

    /**
     * Разпределя цветовете на плочките, спрямо разположението им на игровата дъска
     * @param row
     * @param col
     * @param g
     */
    private void renderGameTile(int row, int col, Graphics g){
        if(isRed(row, col)){
            GameTile gameTile = new GameTile(row, col, new Color(248, 121, 83));
            gameTile.render(g);
        }
        else if(isDarkGray(row, col)){
            GameTile gameTile = new GameTile(row, col, new Color(32,32,32));
            gameTile.render(g);
        }
        else if (isLightGray(row, col)){
            GameTile gameTile = new GameTile(row, col, Color.gray);
            gameTile.render(g);
        }
        else{
            GameTile gameTile = new GameTile(row, col, Color.white);
            gameTile.render(g);
        }
    }

    private boolean isRed (int row, int col){
        return ((row == 0 && col == 0) || (row == 0 && col == 4) || (row == 4 && col == 1) || (row == 4 && col == 3));
    }

    private boolean isDarkGray (int row, int col){
        return ((row == 0 && col == 1) || (row == 0 && col == 3) || (row == 4 && col == 0) || (row == 4 && col == 4));
    }

    private boolean isLightGray (int row, int col){
        return ((row == 1 && col == 0) || (row == 1 && col == 1) || (row == 1 && col == 3) || (row == 1 && col == 4) ||
                (row == 3 && col == 0) || (row == 3 && col == 1) || (row == 3 && col == 3) || (row == 3 && col == 4));
    }

    /**
     * Взима колона и ред, и рисува фигурите на точните места
     * @param row
     * @param col
     * @param g
     */
    private void renderPiece(int row, int col, Graphics g){
        if(thereIsBoardPiece(row, col) && isBoardPieceGuard(row, col)){
            Guard guard = (Guard) this.getBoardPiece(row, col);
            guard.render(g);
        }

        if(thereIsBoardPiece(row, col) && isBoardPieceLeader(row, col)){
            Leader leader = (Leader) this.getBoardPiece(row, col);
            leader.render(g);
        }
    }

    private void renderTurtle(int row, int col, Graphics g){
        if(!thereIsBoardPiece(row, col)){
            Turtle turtle = new Turtle(row, col, g);
            turtle.renderTurtle(g);
        }
    }

    private Object getBoardPiece(int row, int col){
        return pieceCollection[row][col];
    }

    public boolean thereIsBoardPiece(int row, int col){
        return getBoardPiece(row, col) != null;
    }

    private boolean isBoardPieceLeader(int row, int col){
        return getBoardPiece(row, col).getClass().getSimpleName().equals("Leader");
    }

    private boolean isBoardPieceGuard(int row, int col){
        return getBoardPiece(row, col).getClass().getSimpleName().equals("Guard");
    }

    private boolean isBoardPieceTurtle(int row, int col){
        return getBoardPiece(row, col).getClass().getSimpleName().equals("Turtle");
    }
}
