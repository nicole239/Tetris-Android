package tec.tetris;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.logging.LogRecord;

import tec.tetris.Figures.AbstractFigure;
import tec.tetris.Figures.FigureFactory;
import tec.tetris.Figures.IFigure;

public class MainActivity extends AppCompatActivity {

    int duration = 1000;
    GridLayout gridLayout;
    RelativeLayout startScreen;
    TextView txtMessage;
    TextView txtPoints;
    Board tetrisBoard;
    AbstractFigure actualFigure;
    MediaPlayer mediaPlayer;
    final Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tetrisBoard = new Board();

        startScreen = findViewById(R.id.startScreen);
        txtMessage = findViewById(R.id.txtMessage);
        txtPoints = findViewById(R.id.txtPoints);

        gridLayout = findViewById(R.id.gridLayout);
        gridLayout.setRowCount(Board.ROW_COUNT);
        gridLayout.setColumnCount(Board.COLUMN_COUNT);
        createBoard();

        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.tetris_music );
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        RelativeLayout topLayout = findViewById(R.id.topLayout);
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

    }

    private void start(){
        clearBoard();
        nextFigure();

        Runnable run = new Runnable() {
            @Override
            public void run() {
                moveDown();
                handler.postDelayed(this,duration);
            }
        };
        handler.post(run);
    }

    public void btnStart_Click(View view){
        startScreen.setVisibility(View.INVISIBLE);
        start();
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
        if(tetrisBoard.collidesTop(actualFigure)){
            gameover();
        }else {
            paintFigure();
        }
    }

    private void gameover() {
        int points = tetrisBoard.getPoints();
        txtMessage.setText("Points: "+Integer.toString(points));
        startScreen.setVisibility(View.VISIBLE);
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
            txtPoints.setText(String.valueOf(points));

            for(Integer row: rows){
                eraseRow(row);
                vibrate();
            }
        }
    }

    private void vibrate()
    {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(250, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
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
        int width = gridLayout.getWidth()/Board.COLUMN_COUNT;
        int height = gridLayout.getHeight()/Board.ROW_COUNT;
        for(int row = 0; row < Board.ROW_COUNT; row++){
            for(int column = 0; column < Board.COLUMN_COUNT; column++){

                ImageView img = new ImageView(this);

                if(row == 0 || row == Board.ROW_COUNT-1 || column == 0 || column == Board.COLUMN_COUNT-1){
                    img.setImageResource(R.drawable.black_block);
                    img.setTag("BLACK");
                }else{
                    img.setImageResource(R.drawable.grey_block);
                    img.setAlpha(0.3f);
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
                imgView.setAlpha(0.3f);
                imgView.setTag("GREY");
                break;
            case BLACK:
                imgView.setAlpha(1f);
                imgView.setImageResource(R.drawable.black_block);
                imgView.setTag("BLACK");
                break;
            case RED:
                imgView.setAlpha(1f);
                imgView.setImageResource(R.drawable.red_block);
                imgView.setTag("RED");
                break;
            case GREEN:
                imgView.setAlpha(1f);
                imgView.setImageResource(R.drawable.green_block);
                imgView.setTag("GREEN");
                break;
            case BLUE:
                imgView.setAlpha(1f);
                imgView.setImageResource(R.drawable.blue_block);
                imgView.setTag("BLUE");
                break;
            case LIGHT_BLUE:
                imgView.setAlpha(1f);
                imgView.setImageResource(R.drawable.light_blue_block);
                imgView.setTag("LIGHT_BLUE");
                break;
            case ORANGE:
                imgView.setAlpha(1f);
                imgView.setImageResource(R.drawable.orange_block);
                imgView.setTag("ORANGE");
                break;
            case YELLOW:
                imgView.setAlpha(1f);
                imgView.setImageResource(R.drawable.yellow_block);
                imgView.setTag("YELLOW");
                break;
            case PURPLE:
                imgView.setAlpha(1f);
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
