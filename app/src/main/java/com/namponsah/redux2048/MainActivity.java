/**
 * MainActivity
 *  Handles the onclick events for gui of 2048
 *
 * @author NB Amponsah
 */
package com.namponsah.redux2048;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
     * two dimensional array of buttons to show innerDigits
     */
    private Button[][] tileButtons;

    /**
     * instance of gameBoard of two dimensional SpecTile objects
     */
    private TwentyFortyEightGrid gameBoard;

    /**
     * array of respective colors
     */
    private int[] color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * can't change it now, I'm really sorry
         */
        color = new int[2049];
        color[1] = Color.rgb(238,225,230);
        color[2] = Color.rgb(238,225,216);
        color[4] = Color.rgb(235,224,201);
        color[8] = Color.rgb(242,174,122);
        color[16] = Color.rgb(246,148,100);
        color[32] = Color.rgb(246,124,95);
        color[64] = Color.rgb(244,92,59);
        color[128] = Color.rgb(237,207,115);
        color[256] = Color.rgb(237,204,97);
        color[512] = Color.rgb(237,200,80);
        color[1024] = Color.rgb(237,197,63);
        color[2048] = Color.rgb(238,194,46);

        /**
         * initialize our innerDigits to empty i.e. 0
         */
        gameBoard = new TwentyFortyEightGrid();

        /**
         * initialize button array with buttons from activity.xml
         */
        tileButtons = new Button[][]{
                {
                        findViewById(R.id.button00),
                        findViewById(R.id.button01),
                        findViewById(R.id.button02),
                        findViewById(R.id.button03)
                },
                {
                        findViewById(R.id.button10),
                        findViewById(R.id.button11),
                        findViewById(R.id.button12),
                        findViewById(R.id.button13)
                },
                {
                        findViewById(R.id.button20),
                        findViewById(R.id.button21),
                        findViewById(R.id.button22),
                        findViewById(R.id.button23)
                },
                {
                        findViewById(R.id.button30),
                        findViewById(R.id.button31),
                        findViewById(R.id.button32),
                        findViewById(R.id.button33)
                }
        };

        updateGameboard();
    }

    /**
     * update button with respective color and display text version of respective
     * SpecTile object's innerDigit
     * also update the instructions textView to reflect the current gameBoard status
     */
    public void updateGameboard(){
        for(int i = 0; i < gameBoard.getSize(); i += 1){
            for(int j = 0; j < gameBoard.getSize(); j += 1){
                tileButtons[i][j].setBackgroundColor(color[(gameBoard.getTile(i, j))]);

                if(gameBoard.getTile(i, j) == 0){
                    tileButtons[i][j].setText("");
                }else{
                    tileButtons[i][j].setText(String.valueOf(gameBoard.getTile(i, j)));
                }
            }
        }

        final TextView topText = findViewById(R.id.textView);

        for(int i = 0; i < gameBoard.getSize(); i += 1) {
            for (int j = 0; j < gameBoard.getSize(); j += 1) {
                if (gameBoard.getTile(i, j) == 2048) {
                    topText.setText(R.string.Winner);
                    topText.setTextColor(Color.WHITE);
                    topText.setBackgroundColor(Color.rgb(103, 138, 112)); //a shade of green #678a70
                } else if (gameBoard.getTile(i, j) > 2048) {
                    topText.setText(R.string.above2048);
                    topText.setTextColor(Color.WHITE);
                    topText.setBackgroundColor(Color.rgb(103, 138, 112)); //a shade of green #678a70
                } else {
                    topText.setText(R.string.Instructions);
                    topText.setTextColor(Color.BLACK);
                    topText.setBackgroundColor(Color.WHITE);
                }
                if (gameBoard.noPossibleMove()) {
                    topText.setText(R.string.lost);
                    topText.setTextColor(Color.RED);
                    topText.setBackgroundColor(Color.WHITE);
                }

            }
        }
    }

    public void startNewGame(View view){
        gameBoard.clean();

        final RadioButton option0 = findViewById(R.id.radio0);
        final RadioButton option1 = findViewById(R.id.radio1);
        final RadioButton option2 = findViewById(R.id.radio2);
        final RadioButton option3 = findViewById(R.id.radio3);

        if (option0.isChecked()){
            gameBoard.generateTile(0);
        }else if(option1.isChecked()){
            gameBoard.generateTile(1);
        }else if(option2.isChecked()){
            gameBoard.generateTile(2);
        }else if(option3.isChecked()){
            gameBoard.generateTile(3);
        }
        else{
            option0.setChecked(true);
            gameBoard.generateTile(0);
        }
        updateGameboard();
    }

    // diff settings
    public void vanillaFlag(View view) {
        final RadioButton option0 = findViewById(R.id.radio0);
        option0.setChecked(true);

        final RadioButton option1 = findViewById(R.id.radio1);
        option1.setChecked(false);

        final RadioButton option2 = findViewById(R.id.radio2);
        option2.setChecked(false);

        final RadioButton option3 = findViewById(R.id.radio3);
        option3.setChecked(false);
    }

    public void mediumFlag(View view) {
        final RadioButton option0 = findViewById(R.id.radio0);
        option0.setChecked(false);

        final RadioButton option1 = findViewById(R.id.radio1);
        option1.setChecked(true);

        final RadioButton option2 = findViewById(R.id.radio2);
        option2.setChecked(false);

        final RadioButton option3 = findViewById(R.id.radio3);
        option3.setChecked(false);
    }

    public void ilmmpFlag(View view) {
        final RadioButton option0 = findViewById(R.id.radio0);
        option0.setChecked(false);

        final RadioButton option1 = findViewById(R.id.radio1);
        option1.setChecked(false);

        final RadioButton option2 = findViewById(R.id.radio2);
        option2.setChecked(true);

        final RadioButton option3 = findViewById(R.id.radio3);
        option3.setChecked(false);
    }

    public void isyhcdFlag(View view) {
        final RadioButton option0 = findViewById(R.id.radio0);
        option0.setChecked(false);

        final RadioButton option1 = findViewById(R.id.radio1);
        option1.setChecked(false);

        final RadioButton option2 = findViewById(R.id.radio2);
        option2.setChecked(false);

        final RadioButton option3 = findViewById(R.id.radio3);
        option3.setChecked(true);
    }

    // direction to move board
    public void enumUP(View view) {
        gameBoard.moveBoard(Direction.UP);

        final RadioButton option0 = findViewById(R.id.radio0);
        final RadioButton option1 = findViewById(R.id.radio1);
        final RadioButton option2 = findViewById(R.id.radio2);
        final RadioButton option3 = findViewById(R.id.radio3);

        if (option0.isChecked()){
            gameBoard.generateTile(0);
        }else if(option1.isChecked()){
            gameBoard.generateTile(1);
        }else if(option2.isChecked()){
            gameBoard.generateTile(2);
        }else if(option3.isChecked()){
            gameBoard.generateTile(3);
        }
        else{
            option0.setChecked(true);
            gameBoard.generateTile(0);
        }

        updateGameboard();
    }

    public void enumDOWN(View view) {
        gameBoard.moveBoard(Direction.DOWN);

        final RadioButton option0 = findViewById(R.id.radio0);
        final RadioButton option1 = findViewById(R.id.radio1);
        final RadioButton option2 = findViewById(R.id.radio2);
        final RadioButton option3 = findViewById(R.id.radio3);

        if (option0.isChecked()){
            gameBoard.generateTile(0);
        }else if(option1.isChecked()){
            gameBoard.generateTile(1);
        }else if(option2.isChecked()){
            gameBoard.generateTile(2);
        }else if(option3.isChecked()){
            gameBoard.generateTile(3);
        }
        else{
            option0.setChecked(true);
            gameBoard.generateTile(0);
        }

        updateGameboard();
    }

    public void enumLEFT(View view) {
        gameBoard.moveBoard(Direction.LEFT);

        final RadioButton option0 = findViewById(R.id.radio0);
        final RadioButton option1 = findViewById(R.id.radio1);
        final RadioButton option2 = findViewById(R.id.radio2);
        final RadioButton option3 = findViewById(R.id.radio3);

        if (option0.isChecked()){
            gameBoard.generateTile(0);
        }else if(option1.isChecked()){
            gameBoard.generateTile(1);
        }else if(option2.isChecked()){
            gameBoard.generateTile(2);
        }else if(option3.isChecked()){
            gameBoard.generateTile(3);
        }
        else{
            option0.setChecked(true);
            gameBoard.generateTile(0);
            gameBoard.generateTile(0);
        }

        updateGameboard();
    }

    public void enumRIGHT(View view) {
        gameBoard.moveBoard(Direction.RIGHT);

        final RadioButton option0 = findViewById(R.id.radio0);
        final RadioButton option1 = findViewById(R.id.radio1);
        final RadioButton option2 = findViewById(R.id.radio2);
        final RadioButton option3 = findViewById(R.id.radio3);

        if (option0.isChecked()){
            gameBoard.generateTile(0);
        }else if(option1.isChecked()){
            gameBoard.generateTile(1);
        }else if(option2.isChecked()){
            gameBoard.generateTile(2);
        }else if(option3.isChecked()){
            gameBoard.generateTile(3);
        }
        else{
            option0.setChecked(true);
            gameBoard.generateTile(0);
        }

        updateGameboard();
    }
}