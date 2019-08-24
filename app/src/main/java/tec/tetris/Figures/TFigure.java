package tec.tetris.Figures;

public class TFigure extends AbstractFigure {

    private int rotation;

    public TFigure(){
        coordenates = new int[][] {{2,1},{1,2},{2,2},{3,2}};
        rotation = 0;
    }

    @Override
    public void rotate() {

    }

    @Override
    public boolean collidesRotation(int[][] board) {
        return false;
    }
}
