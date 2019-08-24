package tec.tetris.Figures;

import tec.tetris.BlockColor;
import tec.tetris.Board;

public abstract class AbstractFigure {

    public int[][] coordenates;
    public BlockColor color;

    public abstract void rotate();
    public abstract boolean collidesRotation(int[][] board);

    public void moveDown(){
        for(int[] pair : coordenates){
            pair[1]++;
        }
    }
    public  void moveLeft(){
        for(int[] pair : coordenates){
            pair[0]--;
        }
    }
    public void moveRight(){
        for(int[] pair : coordenates){
            pair[0]++;
        }
    }

    public boolean collidesDown(int[][] board){
        for(int[] pair : coordenates){
            if( board[pair[0]][pair[1]++] == Board.OCCUPIED_SPACE)
                return true;
        }
        return false;
    }

    public boolean collidesLeft(int[][] board){
        for(int[] pair : coordenates){
            if( board[pair[0]--][pair[1]] == Board.OCCUPIED_SPACE)
                return true;
        }
        return false;
    }
    public boolean collidesRight(int[][] board){
        for(int[] pair : coordenates){
            if( board[pair[0]++][pair[1]] == Board.OCCUPIED_SPACE)
                return true;
        }
        return false;
    }

}
