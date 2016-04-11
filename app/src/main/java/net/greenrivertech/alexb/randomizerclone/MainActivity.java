/*
    Author:     Alex Ball
    Date:       04/11/2016
    Filename:   MainActivity.java

    This activity is the main activity in the project. The user presses
    the button that covers most of the screen, and an arrow pointing
    left or right appears for 4 seconds, then disappears.
 */
package net.greenrivertech.alexb.randomizerclone;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import java.util.Random;

/**
 * This activity is the main activity in the project. The user presses
 * the button that covers most of the screen, and an arrow pointing
 * left or right appears for 4 seconds, then disappears.
 *
 * @author Alex Ball
 */
public class MainActivity extends AppCompatActivity {
    //a random object for selecting left or right arrow.
    private final Random rand = new Random();

    //the timer that will reset the button's image to null when it expires.
    //because it is saved as a field, the button can be pressed while an
    //arrow is on-screen, which generates a new arrow and resets the timer.
    private CountDownTimer currentTimer;

    //constants representing the left or right arrow
    public final static int LEFT_ARROW_INT = 0;
    public final static int RIGHT_ARROW_INT = 1;

    //number of milliseconds that the arrow should be on screen. default is 4000 for 4 seconds.
    public final static int MILLISECONDS_ON_SCREEN = 4000;

    /**
     * When created, this activity sets the event listener for the main button.
     *
     * @param savedInstanceState The saved state of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get the main button
        final ImageButton mainButton = (ImageButton) findViewById(R.id.mainButton);

        //listener that changes the image on the button when it is clicked. the image
        //will remain for MILLISECONDS_ON_SCREEN milliseconds (default 4 seconds).
        if (mainButton != null) {
            mainButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //stop the current timer, if one is running.
                    if (currentTimer != null) {
                        currentTimer.cancel();
                    }

                    //randomly choose which direction to point the arrow.
                    //since there is only left and right
                    //for this app, the max value is 1 (right arrow).
                    int direction = rand.nextInt(RIGHT_ARROW_INT + 1);

                    //change image
                    if (direction == RIGHT_ARROW_INT) {
                        mainButton.setImageResource(R.drawable.left_arrow_medium);
                    } else if (direction == LEFT_ARROW_INT) {
                        mainButton.setImageResource(R.drawable.right_arrow_medium);
                    }

                    //set a new countdownTimer
                    //when timer expires, change image back to nothing
                    currentTimer = new CountDownTimer(MILLISECONDS_ON_SCREEN, MILLISECONDS_ON_SCREEN) {
                        @Override
                        public void onFinish() {
                            //set image to nothing
                            mainButton.setImageDrawable(null);
                        }

                        @Override
                        public void onTick(long millisUntilFinished) {
                            //do nothing
                        }
                    };

                    //start the timer.
                    currentTimer.start();
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
