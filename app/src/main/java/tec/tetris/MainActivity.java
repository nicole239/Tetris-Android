package tec.tetris;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;

import tec.tetris.Figures.AbstractFigure;
import tec.tetris.Figures.FigureFactory;
import tec.tetris.Figures.IFigure;

public class MainActivity extends AppCompatActivity {
    GridLayout gridLayout;
    Board board;
    AbstractFigure actualFigure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        board = new Board();

        gridLayout = findViewById(R.id.gridLayout);
        gridLayout.setRowCount(Board.ROW_COUNT);
        gridLayout.setColumnCount(Board.COLUMN_COUNT);
        createBoard();
    }

    public void moveDown(View view){
        clearBoard();
        nextFigure();
        paintFigure();

    }

    public void nextFigure(){
        actualFigure = FigureFactory.getRandomFigure();
    }

    public void paintFigure(){
        for(int[] pair : actualFigure.coordenates){
            paintBlock(pair[0],pair[1],actualFigure.color);
        }
    }


    private void createBoard(){
        for(int row = 0; row < Board.ROW_COUNT; row++){
            for(int column = 0; column < Board.COLUMN_COUNT; column++){

                ImageView img = new ImageView(this);

                if(row == 0 || row == Board.ROW_COUNT-1 || column == 0 || column == Board.COLUMN_COUNT-1){
                    img.setImageResource(R.drawable.black_block);
                }else{
                    img.setImageResource(R.drawable.grey_block);
                }

                gridLayout.addView(img, new GridLayout.LayoutParams(
                        GridLayout.spec(row, GridLayout.CENTER),
                        GridLayout.spec(column, GridLayout.CENTER)));

                img.requestLayout();
                img.getLayoutParams().width = 100;
                img.getLayoutParams().height = 100;
            }
        }
    }

    private void clearBoard(){
        for(int row = 0; row < Board.ROW_COUNT; row++){
            for(int column = 0; column < Board.COLUMN_COUNT; column++){
                if(row == 0 || row == Board.ROW_COUNT-1 || column == 0 || column == Board.COLUMN_COUNT-1){
                   paintBlock(row,column,BlockColor.BLACK);
                }else{
                    paintBlock(row,column,BlockColor.GREY);
                }
            }
        }
    }

    private void paintBlock(int row, int column, BlockColor color ){

        int index = row*Board.COLUMN_COUNT + column;

        ImageView imgView = (ImageView)gridLayout.getChildAt(index);

        switch(color){
            case GREY:
                imgView.setImageResource(R.drawable.grey_block);
                break;
            case BLACK:
                imgView.setImageResource(R.drawable.black_block);
                break;
            case RED:
                imgView.setImageResource(R.drawable.red_block);
                break;
            case GREEN:
                imgView.setImageResource(R.drawable.green_block);
                break;
            case BLUE:
                imgView.setImageResource(R.drawable.blue_block);
                break;
            case LIGHT_BLUE:
                imgView.setImageResource(R.drawable.light_blue_block);
                break;
            case ORANGE:
                imgView.setImageResource(R.drawable.orange_block);
                break;
            case YELLOW:
                imgView.setImageResource(R.drawable.yellow_block);
                break;
            case PURPLE:
                imgView.setImageResource(R.drawable.purple_block);
                break;
        }

    }
}
