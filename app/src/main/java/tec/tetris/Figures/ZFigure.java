package tec.tetris.Figures;

import tec.tetris.Board;

public class ZFigure extends AbstractFigure {

    private boolean rotation;

    public ZFigure(){
        coordenates = new int[][] {{1,1},{2,1},{2,2},{3,2}};
        rotation = true;
    }

    @Override
    public void rotate() {
        if(rotation){
            coordenates[0][1]+=2;
            coordenates[3][0]-=2;
        }else{
            coordenates[0][1]-=2;
            coordenates[3][0]+=2;
        }
        rotation = !rotation;
    }

    @Override
    public boolean collidesRotation(int[][] board) {
        if(rotation) {
            if (board[coordenates[0][0]][coordenates[0][1] + 2] == Board.OCCUPIED_SPACE ||
                    board[coordenates[3][0] - 2][coordenates[3][1]] == Board.OCCUPIED_SPACE)
                return true;
        }else{
            if (board[coordenates[0][0]][coordenates[0][1] - 2] == Board.OCCUPIED_SPACE ||
                    board[coordenates[3][0] + 2][coordenates[3][1]] == Board.OCCUPIED_SPACE)
                return true;
        }
        return false;
    }
}
