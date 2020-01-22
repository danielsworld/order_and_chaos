/*
    Daniel Davis
    CSCI 4010
    25 November 2019
 */

package edu.apsu.csci.orderandchaos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class GameEndActivity extends AppCompatActivity {

    public static Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_end);
        
        TextView textView = findViewById(R.id.winner_textView);
        if (getIntent().getExtras() != null) {
            textView.setText(R.string.order_wins);
        } else {
            textView.setText(R.string.chaos_wins);
        }

        // Restart the game
        Button replayButton = findViewById(R.id.replayButton);
        replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), PlayGameActivity.class);
                startActivity(intent);
            }
        });

        // Return to main menu
        Button menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
