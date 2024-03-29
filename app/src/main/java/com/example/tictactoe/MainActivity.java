package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int[] state = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    //All possible win configuration
    int[][] win = {{0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 4, 8}, {2, 4, 6}};

    // pl=0 - o | 1 - x | 2 - none
    int pl = 0;
    boolean playable = true;

    
    //overriding click function
    public void click(View view) {

        ImageView xo = (ImageView) view;


        int st = Integer.parseInt(xo.getTag().toString());

        if (state[st] == 2 && playable == true) {


            xo.setScaleX(0f);
            xo.setScaleY(0f);
            state[st] = pl;


            if (pl == 0) {
                xo.setImageResource(R.drawable.o);
                xo.animate().scaleY(1f).scaleX(1f).setDuration(300);
                pl = 1;
            } else {
                xo.setImageResource(R.drawable.x);
                xo.animate().scaleY(1f).scaleX(1f).setDuration(300);
                pl = 0;
            }

            for (int[] w : win) {

                if (state[w[0]] == state[w[1]] && state[w[1]] == state[w[2]] && state[w[1]] != 2) {


                    TextView winner = (TextView) findViewById(R.id.winnerText);
                    winner.setVisibility(View.VISIBLE);


                    playable = false;
                    if (pl == 0) {

                        winner.setText("X Won");
                        winner.animate().rotationXBy(360).setDuration(500);

                    }

                    if (pl == 1) {

                        winner.setText("O Won");
                        winner.animate().rotationXBy(360).setDuration(500);

                    }

                    Button replay = (Button) findViewById(R.id.replay);
                    replay.setVisibility(View.VISIBLE);
                }

            }
        }




    }


    public void replay(View view){

        Button replay = (Button) findViewById(R.id.replay);
        replay.setVisibility(View.INVISIBLE);
        TextView winner = (TextView) findViewById(R.id.winnerText);
        winner.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {

            ImageView child = (ImageView) gridLayout.getChildAt(i);
            child.setImageDrawable(null);

        }

        for (int j=0; j<state.length; j++) {

            state[j]=2;
        }

        pl = 0;
        playable = true;




    }


}
