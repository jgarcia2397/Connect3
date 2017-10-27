package com.josh2.connect3round2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String winner;
    int counterColor = 0;
    boolean gameActive = true;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int [][] winPositions = {{0,3,6},{1,4,7},{2,5,8},{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6}};

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tapped = Integer.parseInt(counter.getTag().toString());
        if(gameState[tapped] == 2 && gameActive) {
            gameState[tapped] = counterColor;
            counter.setTranslationY(-1000f);
            if(counterColor == 0) {
                counter.setImageResource(R.drawable.yellow);
                counterColor = 1;
            } else if(counterColor == 1){
                counter.setImageResource(R.drawable.red);
                counterColor = 0;
            }
            counter.animate().alpha(1f).translationYBy(1000f).setDuration(500);
            for(int[] i : winPositions) {
                if(gameState[i[0]] == gameState[i[1]] && gameState[i[1]] == gameState[i[2]] && gameState[i[0]] != 2) {
                    gameActive = false;
                    if(gameState[i[0]] == 0) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " has won!");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                } else {
                    boolean gameOver = true;
                    for (int counterState : gameState) {
                        if (counterState == 2) gameOver = false;
                    }
                    if (gameOver) {
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText("It's a draw!");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                    }

                }
            }
        }
    }

    public void playAgain(View view) {
        gameActive = true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        int counterColor = 0;
        for(int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
