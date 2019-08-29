package tec.tetris;

import java.util.ArrayList;

import tec.tetris.Figures.AbstractFigure;

public class Board {
    public final static int ROW_COUNT = 19;
    public final static int COLUMN_COUNT = 11;
    public final static int OCCUPIED_SPACE = 1;
    public final static int FREE_SPACE = -1;

    public int[][] board;
    private int points;

    public Board(){
        board = new int[COLUMN_COUNT][ROW_COUNT];
        resetBoard();
    }

    public void resetBoard(){
        for(int row = 0; row < ROW_COUNT; row++ ){
            for(int column = 0; column < COLUMN_COUNT; column++){

                if(row == 0 || row == ROW_COUNT-1 || column == 0 || column == COLUMN_COUNT-1){
                    board[column][row] = OCCUPIED_SPACE;
                }else{
                    board[column][row] = FREE_SPACE;
                }
            }
        }
        points = 0;
    }

    public void integrateFigure(AbstractFigure figure){
        for(int[] pair : figure.coordenates){
            board[pair[0]][pair[1]] = OCCUPIED_SPACE;
        }
    }

    public boolean collidesTop(AbstractFigure figure){
        for(int[]pair : figure.coordenates){
            if(board[pair[0]][pair[1]] == OCCUPIED_SPACE){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Integer> fullRows(){
        ArrayList<Integer> completedRows = new ArrayList();
        int occupiedSpaces = 0;
        for(int row = 1; row < ROW_COUNT-1; row++ ){
            for(int column = 0; column < COLUMN_COUNT; column++){

                if(board[column][row] == OCCUPIED_SPACE){
                    occupiedSpaces++;
                }
            }
            if(occupiedSpaces == COLUMN_COUNT) {
                completedRows.add(row);
            }
            occupiedSpaces = 0;

        }
        return completedRows;
    }


    public int addPoints(int consecutiveRows){
        points += 10 * (consecutiveRows*consecutiveRows);
        return points;
    }

    public int getPoints(){
        return points;
    }

}
