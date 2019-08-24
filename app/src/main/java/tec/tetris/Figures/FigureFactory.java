package tec.tetris.Figures;

import java.util.Random;

import tec.tetris.BlockColor;

public class FigureFactory {

    private static final Random RANDOM = new Random();
    private static final int FIGURES_NUMBER = 7;


    public static AbstractFigure getRandomFigure(){
        AbstractFigure figure;
        int figureNumber = RANDOM.nextInt(FIGURES_NUMBER);
        int rotations = RANDOM.nextInt(3);

        switch (figureNumber){
            case 0:
                figure =  new IFigure();
                break;
            case 1:
                figure =   new JFigure();
                break;
            case 2:
                figure =   new LFigure();
                break;
            case 3:
                figure =  new OFigure();
                break;
            case 4:
                figure =  new SFigure();
                break;
            case 5:
                figure =  new TFigure();
                break;
            default:
                figure =  new ZFigure();
        }

        for(int i=0; i<rotations; i++){
            figure.rotate();
        }
        figure.color = BlockColor.getRandom();
        return figure;
    }

}
