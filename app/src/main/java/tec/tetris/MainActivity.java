package tec.tetris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.gridLayout);
        gridLayout.setRowCount(Board.ROW_COUNT);
        gridLayout.setColumnCount(Board.COLUMN_COUNT);
        clearBoard();
    }

    public void moveDown(View view){

        view.setVisibility(View.INVISIBLE);

        ImageView img = new ImageView(this);
        img.setImageResource(R.drawable.ic_launcher_background);

        gridLayout.addView(img, new GridLayout.LayoutParams(
                GridLayout.spec(5, GridLayout.CENTER),
                GridLayout.spec(2, GridLayout.CENTER)));
    }


    private void clearBoard(){

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

            }
        }
    }
}
