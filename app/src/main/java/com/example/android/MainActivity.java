package com.example.android.courtcounter;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
// COMMIT!
public class MainActivity extends AppCompatActivity {
    // Declaring all findViewByIds
    MediaPlayer mediaPlayer;
    Button button3A;
    Button button2A;
    Button button1A;
    Button button0A;
    Button button3B;
    Button button2B;
    Button button1B;
    Button button0B;
    TextView textViewA;
    TextView textViewB;
    TextView scoreViewA;
    TextView scoreViewB;
    TextView randomA;
    TextView randomB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.sound_file_1);
        mediaPlayer.start(); // no need to call prepare(); create() does that for you
        generate(); // Generate numbers as soon as the app starts
        // Set initial boolean states
        isPlayersAturn = true;
        isPlayersBturn = true;
        hasCheckedZero = false;
        // Release the media player if playback has finished
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                releaseMediaPlayer();
            }
        });
        // Initialising all findViewByIds
        button3A = (Button) findViewById(R.id.three_points_A);
        button2A = (Button) findViewById(R.id.two_points_A);
        button1A = (Button) findViewById(R.id.one_point_A);
        button0A = (Button) findViewById(R.id.zero_points_A);
        button3B = (Button) findViewById(R.id.three_points_B);
        button2B = (Button) findViewById(R.id.two_points_B);
        button1B = (Button) findViewById(R.id.one_point_B);
        button0B = (Button) findViewById(R.id.zero_points_B);
        textViewA = (TextView) findViewById(R.id.generatedA);
        textViewB = (TextView) findViewById(R.id.generatedB);
        scoreViewA = (TextView) findViewById(R.id.team_a_score);
        scoreViewB = (TextView) findViewById(R.id.team_b_score);
        randomA = (TextView) findViewById(R.id.random_string_A);
        randomB = (TextView) findViewById(R.id.random_string_B);
    }
    // Release media player if activity has stopped
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
    // Release media player method
    public void releaseMediaPlayer() {
        // If media player is not empty(something is playing) -> release it
        if(mediaPlayer != null) {
            mediaPlayer.release();
            // set mediaPlayer to null
            mediaPlayer = null;
        }
    }
    // Creating the global variables that we need
    int scoreTeamA = 0; // int to store score A
    int scoreTeamB = 0; // int to store score B
    // Creating the random object
    Random rand = new Random();
    // Creating the range of randomness
    int range = 10 - 0 + 1;
    // Ints to store the random A and random B
    int generatedTeamA;
    int generatedTeamB;
    // Creating booleans to make app fully functional
    boolean isPlayersAturn;
    boolean isPlayersBturn;
    boolean hasCheckedZero;

    // Creating the method that generates the 2 random numbers
    public void generate() {
        generatedTeamA = rand.nextInt(range);
        generatedTeamB = rand.nextInt(range);
    }
    // Method to show the random number at the end of the game
    public void showRandom() {
        randomA.setText(String.valueOf(generatedTeamA));
        randomB.setText(String.valueOf(generatedTeamB));
    }
    public void victorySound(){
        mediaPlayer = MediaPlayer.create(this, R.raw.victory);
        mediaPlayer.start();
    }
    // Method to compare score B with random B
    public void checkScoreB() {
            // If score B is equal to random B, player B wins
            if(scoreTeamB == generatedTeamB) {
                displayTextForTeamB(getResources().getString(R.string.winner));
                displayTextForTeamA(getResources().getString(R.string.loser));
                textViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
                textViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meLuckyColorText, null));
                scoreViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
                scoreViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meLuckyColorText, null));
                showRandom();
                gameOver();
                victorySound();
            }
            // If score B exceeded random B, make player A winner
            if(scoreTeamB > generatedTeamB) {
                displayTextForTeamA(getResources().getString(R.string.winner));
                displayTextForTeamB(getResources().getString(R.string.loser));
                textViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meLuckyColorText, null));
                textViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
                scoreViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meLuckyColorText, null));
                scoreViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
                showRandom();
                gameOver();
                victorySound();
            }
    }
    // Method to compare score A with random A
    public void checkScoreA() {
            if(scoreTeamA == generatedTeamA) {
                displayTextForTeamA(getResources().getString(R.string.winner));
                displayTextForTeamB(getResources().getString(R.string.loser));
                textViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meLuckyColorText, null));
                textViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
                scoreViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meLuckyColorText, null));
                scoreViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
                showRandom();
                gameOver();
                victorySound();
            }
            // If score A exceeded random A, make player B winner
            if(scoreTeamA > generatedTeamA) {
                displayTextForTeamB(getResources().getString(R.string.winner));
                displayTextForTeamA(getResources().getString(R.string.loser));
                textViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meLuckyColorText, null));
                textViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
                scoreViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meLuckyColorText, null));
                scoreViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
                showRandom();
                gameOver();
                victorySound();
        }
    }
    // Method that checks if random is 0 (Players can do this at the very begining)
    // If player A checks for 0, and his random is not 0 but random B is 0, player B wins! so choose wisely
    public void checkZeroA(View view) {
        Toast noZero = Toast.makeText(MainActivity.this, "No need to check for 0!", Toast.LENGTH_LONG);
        // Only check for 0 if nobody has checked yet
        if(!hasCheckedZero) {
            if(scoreTeamA == 0 || scoreTeamB == 0) {
                // First check for player A then for player B if not true
                if(scoreTeamA == 0 && generatedTeamA == 0) {
                    displayTextForTeamA(getResources().getString(R.string.bazzinga));
                    displayTextForTeamB(getResources().getString(R.string.loser));
                    textViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meLuckyColorText, null));
                    scoreViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meLuckyColorText, null));
                    textViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
                    scoreViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
                    showRandom();
                    gameOver();
                    victorySound();
                } else if(scoreTeamB == 0 && generatedTeamB == 0) {
                    displayTextForTeamB(getResources().getString(R.string.bazzinga));
                    displayTextForTeamA(getResources().getString(R.string.loser));
                    textViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
                    scoreViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
                    scoreViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meLuckyColorText, null));
                    textViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meLuckyColorText, null));
                    showRandom();
                    gameOver();
                    victorySound();
                }
                isPlayersAturn = false;
                isPlayersBturn = true;
                hasCheckedZero = true;
            }
            else {
                noZero.show();
            }
        } else {
            noZero.show();
        }

    }
    // If player B checks for 0, and his random is not 0 but random A is 0, player A wins! so choose wisely
    public void checkZeroB(View view) {
        Toast noZero = Toast.makeText(MainActivity.this, "No need to check for 0!", Toast.LENGTH_LONG);
        // Only check for 0 if nobody has checked yet
        if(!hasCheckedZero) {
            if(scoreTeamA == 0 || scoreTeamB == 0) {
                // First check for player B then for player A if not true
                if (scoreTeamB == 0 && generatedTeamB == 0) {
                    displayTextForTeamB(getResources().getString(R.string.bazzinga));
                    displayTextForTeamA(getResources().getString(R.string.loser));
                    textViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meLuckyColorText, null));
                    scoreViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meLuckyColorText, null));
                    textViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
                    scoreViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
                    showRandom();
                    gameOver();
                    victorySound();
                } else if (scoreTeamA == 0 && generatedTeamA == 0) {
                    displayTextForTeamA(getResources().getString(R.string.bazzinga));
                    displayTextForTeamB(getResources().getString(R.string.loser));
                    textViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
                    scoreViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
                    scoreViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meLuckyColorText, null));
                    textViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meLuckyColorText, null));
                    showRandom();
                    gameOver();
                    victorySound();
                }
                isPlayersAturn = true;
                isPlayersBturn = false;
                hasCheckedZero = true;
            }
            else {
                noZero.show();
            }
        } else {
            noZero.show();
        }
    }
    // Method to add 3 points to score A
    public void threePoints(View view) {
        Toast notYourTurn = Toast.makeText(this, "Not your turn!", Toast.LENGTH_LONG);
        if(isPlayersAturn) {
            scoreTeamA = scoreTeamA + 3;
            displayForTeamA(scoreTeamA);
            displayTextForTeamA(getResources().getString(R.string.rawr));
            textViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
            checkScoreA();
            isPlayersAturn = false;
            isPlayersBturn = true;
        }
        else {
            notYourTurn.show();
        }
    }
    // Method to add 2 points to score A
    public void twoPoints(View view) {
        Toast notYourTurn = Toast.makeText(this, "Not your turn!", Toast.LENGTH_LONG);
        if(isPlayersAturn) {
            scoreTeamA = scoreTeamA + 2;
            displayForTeamA(scoreTeamA);
            displayTextForTeamA(getResources().getString(R.string.boring));
            textViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.mooColor, null));
            checkScoreA();
            isPlayersAturn = false;
            isPlayersBturn = true;
        }
        else {
            notYourTurn.show();
        }
    }
    // Method to add 1 point to score A
    public void freeThrow(View view) {
        Toast notYourTurn = Toast.makeText(this, "Not your turn!", Toast.LENGTH_LONG);
        if(isPlayersAturn) {
            scoreTeamA++;
            displayForTeamA(scoreTeamA);
            displayTextForTeamA(getResources().getString(R.string.meow));
            textViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meowColor, null));
            checkScoreA();
            isPlayersAturn = false;
            isPlayersBturn = true;
        }
        else {
            notYourTurn.show();
        }
    }
    // Method to add 3 points to score B
    public void threePointsB(View view) {
        Toast notYourTurn = Toast.makeText(this, "Not your turn!", Toast.LENGTH_LONG);
        if(isPlayersBturn) {
            scoreTeamB = scoreTeamB + 3;
            displayForTeamB(scoreTeamB);
            displayTextForTeamB(getResources().getString(R.string.rawr));
            textViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
            checkScoreB();
            isPlayersBturn = false;
            isPlayersAturn = true;
        }
        else {
            notYourTurn.show();
        }
    }
    // Method to add 2 points to score B
    public void twoPointsB(View view) {
        Toast notYourTurn = Toast.makeText(this, "Not your turn!", Toast.LENGTH_LONG);
        if(isPlayersBturn) {
            scoreTeamB = scoreTeamB + 2;
            displayForTeamB(scoreTeamB);
            displayTextForTeamB(getResources().getString(R.string.boring));
            textViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.mooColor, null));
            checkScoreB();
            isPlayersBturn = false;
            isPlayersAturn = true;
        }
        else {
            notYourTurn.show();
        }
    }
    // Method to add 1 point to score B
    public void freeThrowB(View view) {
        Toast notYourTurn = Toast.makeText(this, "Not your turn!", Toast.LENGTH_LONG);
        if(isPlayersBturn) {
        scoreTeamB++;
        displayForTeamB(scoreTeamB);
        displayTextForTeamB(getResources().getString(R.string.meow));
        textViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meowColor, null));
        checkScoreB();
            isPlayersBturn = false;
            isPlayersAturn = true;
        }
        else {
            notYourTurn.show();
        }
    }
    // Method for reset button to reset everything
    public void resetScore(View view) {
        releaseMediaPlayer();
        mediaPlayer = MediaPlayer.create(this, R.raw.sound_file_1);
        mediaPlayer.start(); // no need to call prepare(); create() does that for you
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        generate();
        displayTextForTeamA(getResources().getString(R.string.generated_number));
        displayTextForTeamB(getResources().getString(R.string.generated_number));
        randomA.setText(getResources().getString(R.string.random));
        randomB.setText(getResources().getString(R.string.random));
        textViewA.setTextColor(Color.GRAY);
        textViewB.setTextColor(Color.GRAY);
        scoreViewA.setTextColor(Color.BLACK);
        scoreViewB.setTextColor(Color.BLACK);
        button3A.setEnabled(true);
        button2A.setEnabled(true);
        button1A.setEnabled(true);
        button0A.setEnabled(true);
        button3B.setEnabled(true);
        button2B.setEnabled(true);
        button1B.setEnabled(true);
        button0B.setEnabled(true);
        isPlayersAturn = true;
        isPlayersBturn = true;
        hasCheckedZero = false;
    }
    public void gameOver() {
        button3A.setEnabled(false);
        button2A.setEnabled(false);
        button1A.setEnabled(false);
        button0A.setEnabled(false);
        button3B.setEnabled(false);
        button2B.setEnabled(false);
        button1B.setEnabled(false);
        button0B.setEnabled(false);
        releaseMediaPlayer();
    }
    public void displayForTeamA(int score) {
        scoreViewA.setText(String.valueOf(score));
    }
    public void displayTextForTeamA(String score) {
        textViewA.setText(score);
    }
    public void displayForTeamB(int score) {
        scoreViewB.setText(String.valueOf(score));
    }
    public void displayTextForTeamB(String score) {
        textViewB.setText(score);
    }
}