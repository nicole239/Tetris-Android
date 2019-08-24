package tec.tetris.Figures;

public class OFigure extends AbstractFigure {

    public OFigure(){
        coordenates = new int[][] {{1,1},{2,1},{1,2},{2,2}};
    }

    @Override
    public void rotate() {
    }

    @Override
    public boolean collidesRotation(int[][] board) {
        return false;
    }
}
