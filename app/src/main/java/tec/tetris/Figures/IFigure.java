package tec.tetris.Figures;

import tec.tetris.Board;

public class IFigure extends AbstractFigure {

    public IFigure(){
        coordenates = new int[][] {{1,1},{2,1},{3,1},{4,1}};
    }

    @Override
    public void rotate() {
        for(int[] pair : coordenates){
            int i = pair[0];
            pair[0] = pair[1];
            pair[1] = i;
        }
    }

    @Override
    public boolean collidesRotation(int[][] board) {
        for(int[] pair : coordenates){
            if( board[pair[1]][pair[0]] == Board.OCCUPIED_SPACE)
                return true;
        }
        return false;
    }
}
