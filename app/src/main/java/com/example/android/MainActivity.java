package com.example.android.courtcounter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import java.util.Random;
// COMMIT!
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generate(); // Generate numbers as soon as the app starts
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
    // Creating the method that generates the 2 random numbers
    public void generate() {
        generatedTeamA = rand.nextInt(range);
        generatedTeamB = rand.nextInt(range);
    }
    // Method to show the random number at the end of the game
    public void showRandom() {
        TextView randomA = (TextView) findViewById(R.id.random_string_A);
        TextView randomB = (TextView) findViewById(R.id.random_string_B);
        randomA.setText(String.valueOf(generatedTeamA));
        randomB.setText(String.valueOf(generatedTeamB));
    }
    // Method to compare score B with random B
    public void checkScoreB() {
        // Creating local variables to get the text views, I noticed it didn't let me create them outside methods
        TextView textViewA = (TextView) findViewById(R.id.generatedA);
        TextView textViewB = (TextView) findViewById(R.id.generatedB);
        TextView scoreViewA = (TextView) findViewById(R.id.team_a_score);
        TextView scoreViewB = (TextView) findViewById(R.id.team_b_score);
        // If score B is equal to random B, player B wins
        if(scoreTeamB == generatedTeamB) {
            displayTextForTeamB(getResources().getString(R.string.winner));
            displayTextForTeamA(getResources().getString(R.string.loser));
            textViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
            textViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meLuckyColorText, null));
            scoreViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
            scoreViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meLuckyColorText, null));
            showRandom();
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
        }

    }
    // Method to compare score A with random A
    public void checkScoreA() {
        TextView textViewA = (TextView) findViewById(R.id.generatedA);
        TextView textViewB = (TextView) findViewById(R.id.generatedB);
        TextView scoreViewA = (TextView) findViewById(R.id.team_a_score);
        TextView scoreViewB = (TextView) findViewById(R.id.team_b_score);
        if(scoreTeamA == generatedTeamA) {
            displayTextForTeamA(getResources().getString(R.string.winner));
            displayTextForTeamB(getResources().getString(R.string.loser));
            textViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meLuckyColorText, null));
            textViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
            scoreViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meLuckyColorText, null));
            scoreViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
            showRandom();
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
        }

    }
    // Method that checks if random is 0 (Players can do this at the very begining)
    // If player A checks for 0, and his random is not 0 but random B is 0, player B wins! so choose wisely
    public void checkZeroA(View view) {
        // Creating local objects for textiew so we can alter them
        TextView textViewA = (TextView) findViewById(R.id.generatedA);
        TextView textViewB = (TextView) findViewById(R.id.generatedB);
        TextView scoreViewA = (TextView) findViewById(R.id.team_a_score);
        TextView scoreViewB = (TextView) findViewById(R.id.team_b_score);
        // First check for player A then for player B if not true
        if(scoreTeamA == 0 && generatedTeamA == 0) {
            displayTextForTeamA(getResources().getString(R.string.bazzinga));
            displayTextForTeamB(getResources().getString(R.string.loser));
            textViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meLuckyColorText, null));
            scoreViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meLuckyColorText, null));
            textViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
            scoreViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
            showRandom();
        } else if(scoreTeamB == 0 && generatedTeamB == 0) {
            displayTextForTeamB(getResources().getString(R.string.bazzinga));
            displayTextForTeamA(getResources().getString(R.string.loser));
            textViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
            scoreViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
            scoreViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meLuckyColorText, null));
            textViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meLuckyColorText, null));
            showRandom();
        }
    }
    // If player B checks for 0, and his random is not 0 but random A is 0, player A wins! so choose wisely
    public void checkZeroB(View view) {
        // Creating local objects for textivew so we can alter them
        TextView textViewA = (TextView) findViewById(R.id.generatedA);
        TextView textViewB = (TextView) findViewById(R.id.generatedB);
        TextView scoreViewA = (TextView) findViewById(R.id.team_a_score);
        TextView scoreViewB = (TextView) findViewById(R.id.team_b_score);
        // First check for player B then for player A if not true
        if(scoreTeamB == 0 && generatedTeamB == 0) {
            displayTextForTeamB(getResources().getString(R.string.bazzinga));
            displayTextForTeamA(getResources().getString(R.string.loser));
            textViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meLuckyColorText, null));
            scoreViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meLuckyColorText, null));
            textViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
            scoreViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
            showRandom();
        } else if(scoreTeamA == 0 && generatedTeamA == 0) {
            displayTextForTeamA(getResources().getString(R.string.bazzinga));
            displayTextForTeamB(getResources().getString(R.string.loser));
            textViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
            scoreViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
            scoreViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meLuckyColorText, null));
            textViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meLuckyColorText, null));
            showRandom();
        }
    }
    // Method to add 3 points to score A
    public void threePoints(View view) {
        scoreTeamA = scoreTeamA + 3;
        displayForTeamA(scoreTeamA);
        displayTextForTeamA(getResources().getString(R.string.rawr));
        TextView textViewA = (TextView) findViewById(R.id.generatedA);
        textViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
        checkScoreA();
    }
    // Method to add 2 points to score A
    public void twoPoints(View view) {
        scoreTeamA = scoreTeamA + 2;
        displayForTeamA(scoreTeamA);
        displayTextForTeamA(getResources().getString(R.string.boring));
        TextView textViewA = (TextView) findViewById(R.id.generatedA);
        textViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.mooColor, null));
        checkScoreA();
    }
    // Method to add 1 point to score A
    public void freeThrow(View view) {
        scoreTeamA++;
        displayForTeamA(scoreTeamA);
        displayTextForTeamA(getResources().getString(R.string.meow));
        TextView textViewA = (TextView) findViewById(R.id.generatedA);
        textViewA.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meowColor, null));
        checkScoreA();
    }
    // Method to add 3 points to score B
    public void threePointsB(View view) {
        scoreTeamB = scoreTeamB + 3;
        displayForTeamB(scoreTeamB);
        displayTextForTeamB(getResources().getString(R.string.rawr));
        TextView textViewB = (TextView) findViewById(R.id.generatedB);
        textViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.firyColor, null));
        checkScoreB();
    }
    // Method to add 2 points to score B
    public void twoPointsB(View view) {
        scoreTeamB = scoreTeamB + 2;
        displayForTeamB(scoreTeamB);
        displayTextForTeamB(getResources().getString(R.string.boring));
        TextView textViewB = (TextView) findViewById(R.id.generatedB);
        textViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.mooColor, null));
        checkScoreB();
    }
    // Method to add 1 point to score B
    public void freeThrowB(View view) {
        scoreTeamB++;
        displayForTeamB(scoreTeamB);
        displayTextForTeamB(getResources().getString(R.string.meow));
        TextView textViewB = (TextView) findViewById(R.id.generatedB);
        textViewB.setTextColor(ResourcesCompat.getColor(getResources(), R.color.meowColor, null));
        checkScoreB();
    }
    // Method for reset button to reset everything
    public void resetScore(View view) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        generate();
        displayTextForTeamA(getResources().getString(R.string.generated_number));
        displayTextForTeamB(getResources().getString(R.string.generated_number));
        TextView textViewA = (TextView) findViewById(R.id.generatedA);
        TextView textViewB = (TextView) findViewById(R.id.generatedB);
        TextView scoreViewA = (TextView) findViewById(R.id.team_a_score);
        TextView scoreViewB = (TextView) findViewById(R.id.team_b_score);
        TextView randomA = (TextView) findViewById(R.id.random_string_A);
        TextView randomB = (TextView) findViewById(R.id.random_string_B);
        randomA.setText(getResources().getString(R.string.random));
        randomB.setText(getResources().getString(R.string.random));
        textViewA.setTextColor(Color.GRAY);
        textViewB.setTextColor(Color.GRAY);
        scoreViewA.setTextColor(Color.BLACK);
        scoreViewB.setTextColor(Color.BLACK);
    }
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }
    public void displayTextForTeamA(String score) {
        TextView text = (TextView) findViewById(R.id.generatedA);
        text.setText(score);
    }
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }
    public void displayTextForTeamB(String score) {
        TextView text = (TextView) findViewById(R.id.generatedB);
        text.setText(score);
    }
}
