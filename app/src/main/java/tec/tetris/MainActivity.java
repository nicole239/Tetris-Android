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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import tec.tetris.Figures.AbstractFigure;
import tec.tetris.Figures.FigureFactory;
import tec.tetris.Figures.IFigure;

public class MainActivity extends AppCompatActivity {
    GridLayout gridLayout;
    Board tetrisBoard;
    AbstractFigure actualFigure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tetrisBoard = new Board();

        gridLayout = findViewById(R.id.gridLayout);
        gridLayout.setRowCount(Board.ROW_COUNT);
        gridLayout.setColumnCount(Board.COLUMN_COUNT);
        createBoard();

        LinearLayout topLayout = findViewById(R.id.topLayout);
        topLayout.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeTop() {
                rotate();
            }
            public void onSwipeRight() {
                moveRight();
            }
            public void onSwipeLeft() {
                moveLeft();
            }
            public void onSwipeBottom() {
                moveDown();
            }
        });

        //TEMPORAL FOR TESTS
        nextFigure();
        //----------------------
    }

    private void moveDown(){
        if(!actualFigure.collidesDown(tetrisBoard.board)){
            eraseFigure();
            actualFigure.moveDown();
            paintFigure();
        }else {
            tetrisBoard.integrateFigure(actualFigure);
            checkFullRow();
            nextFigure();
        }
    }

    private void moveRight(){
        if(!actualFigure.collidesRight(tetrisBoard.board)){
            eraseFigure();
            actualFigure.moveRight();
            paintFigure();
        }else{
            //Add sound effect
        }
    }

    private void moveLeft(){
        if(!actualFigure.collidesLeft(tetrisBoard.board)){
            eraseFigure();
            actualFigure.moveLeft();
            paintFigure();
        }
    }

    private void rotate(){
        if(!actualFigure.collidesRotation(tetrisBoard.board)){
            eraseFigure();
            actualFigure.rotate();
            paintFigure();
        }
    }

    public void nextFigure(){
        actualFigure = FigureFactory.getRandomFigure();
        paintFigure();
    }

    private void eraseFigure(){
        for(int[] pair : actualFigure.coordenates){
            paintBlock(pair[1],pair[0],BlockColor.GREY);
        }
    }

    private void checkFullRow(){
        ArrayList<Integer> rows = tetrisBoard.fullRows();
        if(!rows.isEmpty()) {
            int points = tetrisBoard.addPoints(rows.size());
            for(Integer row: rows){
                eraseRow(row);
            }
        }

    }

    private void eraseRow(int erasedRow){
        for(int row = erasedRow; row > 0; row--) {
            for (int column = 0; column < Board.COLUMN_COUNT; column++) {
                if(row == erasedRow) {
                    tetrisBoard.board[column][row] = Board.FREE_SPACE;
                    paintBlock(row,column,BlockColor.GREY);
                }else {
                    tetrisBoard.board[column][row + 1] = tetrisBoard.board[column][row];
                    BlockColor color = getBlockColor(row,column);
                    paintBlock(row+1,column,color);
                }
            }
        }
    }

    private void paintFigure(){
        for(int[] pair : actualFigure.coordenates){
            paintBlock(pair[1],pair[0],actualFigure.color);
        }
    }


    private void createBoard(){
        for(int row = 0; row < Board.ROW_COUNT; row++){
            for(int column = 0; column < Board.COLUMN_COUNT; column++){

                ImageView img = new ImageView(this);

                if(row == 0 || row == Board.ROW_COUNT-1 || column == 0 || column == Board.COLUMN_COUNT-1){
                    img.setImageResource(R.drawable.black_block);
                    img.setTag("BLACK");
                }else{
                    img.setImageResource(R.drawable.grey_block);
                    img.setTag("GREY");
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
        tetrisBoard.resetBoard();
    }

    private void paintBlock(int row, int column, BlockColor color ){

        int index = row*Board.COLUMN_COUNT + column;

        ImageView imgView = (ImageView)gridLayout.getChildAt(index);

        switch(color){
            case GREY:
                imgView.setImageResource(R.drawable.grey_block);
                imgView.setTag("GREY");
                break;
            case BLACK:
                imgView.setImageResource(R.drawable.black_block);
                imgView.setTag("BLACK");
                break;
            case RED:
                imgView.setImageResource(R.drawable.red_block);
                imgView.setTag("RED");
                break;
            case GREEN:
                imgView.setImageResource(R.drawable.green_block);
                imgView.setTag("GREEN");
                break;
            case BLUE:
                imgView.setImageResource(R.drawable.blue_block);
                imgView.setTag("BLUE");
                break;
            case LIGHT_BLUE:
                imgView.setImageResource(R.drawable.light_blue_block);
                imgView.setTag("LIGHT_BLUE");
                break;
            case ORANGE:
                imgView.setImageResource(R.drawable.orange_block);
                imgView.setTag("ORANGE");
                break;
            case YELLOW:
                imgView.setImageResource(R.drawable.yellow_block);
                imgView.setTag("YELLOW");
                break;
            case PURPLE:
                imgView.setImageResource(R.drawable.purple_block);
                imgView.setTag("PURPLE");
                break;
        }

    }

    private BlockColor getBlockColor(int row,int column){
        int index = row*Board.COLUMN_COUNT + column;
        ImageView imgView = (ImageView)gridLayout.getChildAt(index);

        String colorName = (String)imgView.getTag();
        return BlockColor.valueOf(colorName);
    }
}
