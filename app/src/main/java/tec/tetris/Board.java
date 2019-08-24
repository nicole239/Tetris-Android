package tec.tetris;

import tec.tetris.Figures.AbstractFigure;

public class Board {
    public final static int ROW_COUNT = 50;
    public final static int COLUMN_COUNT = 10;
    public final static int OCCUPIED_SPACE = 1;
    public final static int FREE_SPACE = -1;

    public int[][] board;

    public Board(){
        board = new int[ROW_COUNT][COLUMN_COUNT];
        resetBoard();
    }

    public void resetBoard(){
        for(int row = 0; row < ROW_COUNT; row++ ){
            for(int column = 0; column < COLUMN_COUNT; column++){

                if(row == 0 || row == ROW_COUNT-1 || column == 0 || column == COLUMN_COUNT-1){
                    board[row][column] = OCCUPIED_SPACE;
                }else{
                    board[row][column] = FREE_SPACE;
                }
            }
        }
    }

    public void integrateFigure(AbstractFigure figure){
        for(int[] pair : figure.coordenates){
            board[pair[0]][pair[1]] = OCCUPIED_SPACE;
        }
    }

}
