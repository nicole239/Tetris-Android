package tec.tetris.Figures;

import tec.tetris.Board;

public class IFigure extends AbstractFigure {

    public IFigure(){
        coordenates = new int[][] {{1,1},{2,1},{3,1},{4,1}};
    }
    boolean rotation = true;

    @Override
    public void rotate() {
        int pivot = coordenates[1][(rotation?0:1)];
        int pivot2 = coordenates[1][(rotation?1:0)] - 1;
        for(int i=0; i<4;  i++){
            coordenates[i][(rotation?0:1)] = pivot;
            coordenates[i][(rotation?1:0)] = pivot2;
            pivot2 ++;
        }
        rotation = !rotation;
    }

    @Override
    public boolean collidesRotation(int[][] board) {
        int pivot = coordenates[1][(rotation?0:1)];
        int pivot2 = coordenates[1][(rotation?1:0)] - 1;
        for(int i=0; i<4;  i++){
            if (board[(rotation?pivot:pivot2)][(rotation?pivot2:pivot)] == Board.OCCUPIED_SPACE)
                return true;
            pivot2 ++;
        }
        return false;
    }
}
