package tec.tetris.Figures;

import tec.tetris.Board;

public class SFigure extends AbstractFigure {

    private boolean rotation;

    public SFigure(){
        coordenates = new int[][] {{2,1},{3,1},{1,2},{2,2}};
        rotation = true;
    }

    @Override
    public void rotate() {
        if(rotation){
            coordenates[0][1]+=2;
            coordenates[1][0]-=2;
        }else{
            coordenates[0][1]-=2;
            coordenates[1][0]+=2;
        }
        rotation = !rotation;
    }

    @Override
    public boolean collidesRotation(int[][] board) {
        if(rotation) {
            if (board[coordenates[0][0]][coordenates[0][1] + 2] == Board.OCCUPIED_SPACE ||
                    board[coordenates[1][0] - 2][coordenates[1][1]] == Board.OCCUPIED_SPACE)
                return true;
        }else{
            if (board[coordenates[0][0]][coordenates[0][1] - 2] == Board.OCCUPIED_SPACE ||
                    board[coordenates[1][0] + 2][coordenates[1][1]] == Board.OCCUPIED_SPACE)
                return true;
        }
        return false;
    }
}
