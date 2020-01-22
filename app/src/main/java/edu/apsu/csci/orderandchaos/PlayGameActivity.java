/*
    Daniel Davis
    CSCI 4010
    25 November 2019
 */

package edu.apsu.csci.orderandchaos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PlayGameActivity extends AppCompatActivity implements View.OnClickListener {

    // Constants for the board
    public static final int ROWS = 6, COLS = 6;
    public static int[][] board = new int[ROWS][COLS];
    public static int moves = 0;

    // Objects used frequently
    public static Intent intent;
    public ImageButton imageButton;
    public TextView turn_textView;
    public RadioButton xButton;
    public RadioButton oButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_game);

        initializeBoard();
    }

    @Override
    public void onClick(View view) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (view.getId() == board[i][j]) {
                    imageButton = findViewById(board[i][j]);

                    // If X is selected and the square is empty
                    if (xButton.isChecked() && imageButton.getTag() == null) {
                        imageButton.setBackgroundResource(R.drawable.x);
                        imageButton.setTag(R.drawable.x);
                        board[i][j] = R.drawable.x;

                        changeTurn();
                    }

                    // If O is selected and the square is empty
                    else if (oButton.isChecked() && imageButton.getTag() == null) {
                        imageButton.setBackgroundResource(R.drawable.o);
                        imageButton.setTag(R.drawable.o);
                        board[i][j] = R.drawable.o;

                        changeTurn();
                    }
                }
            }
        }
    }

    // Indicates whose turn it is and checks for a winner
    private void changeTurn() {
        moves++;

        if (isOrderWinner()) {
            intent = new Intent(getBaseContext(), GameEndActivity.class);
            intent.putExtra("Order", R.string.order_wins);
            startActivity(intent);
        } else if (moves >= 36) {
            intent = new Intent(getBaseContext(), GameEndActivity.class);
            startActivity(intent);
        } else if (turn_textView.getText().equals(getString(R.string.order_turn))) {
            turn_textView.setText(R.string.chaos_turn);
        } else {
            turn_textView.setText(R.string.order_turn);
        }
    }

    // Initializes the listeners and objects needed for the board
    private void initializeBoard() {
        View child;
        ViewGroup layout = findViewById(R.id.board_layout);
        turn_textView = findViewById(R.id.turn_textView);
        xButton = findViewById(R.id.x_button);
        oButton = findViewById(R.id.o_button);
        moves = 0;

        for (int i = 0; i < layout.getChildCount(); i++) {
            child = layout.getChildAt(i);
            if (child instanceof LinearLayout) {
                ViewGroup rowLayout = findViewById(child.getId());
                for (int j = 0; j < rowLayout.getChildCount(); j++) {
                    child = rowLayout.getChildAt(j);
                    if (child instanceof ImageButton) {
                        child.setOnClickListener(this);
                        board[i][j] = child.getId();
                    }
                }
            }
        }
    }

    // Algorithm that scans for a match of 5 anywhere on the board
    public boolean isOrderWinner() {
        // Check horizontal
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length - 4; col++) {
                int position = board[row][col];
                if (position == board[row][col + 1] &&
                        position == board[row][col + 2] &&
                        position == board[row][col + 3] &&
                        position == board[row][col + 4]) {
                    return true;
                }
            }
        }

        // Check vertical
        for (int row = 0; row < board.length - 4; row++) {
            for (int col = 0; col < board[row].length; col++) {
                int position = board[row][col];
                if (position == board[row + 1][col] &&
                        position == board[row + 2][col] &&
                        position == board[row + 3][col] &&
                        position == board[row + 4][col]) {
                    return true;
                }
            }
        }

        // Check diagonal
        for (int row = 0; row < board.length - 4; row++) {
            for (int col = 0; col < board[row].length - 4; col++) {
                int position = board[row][col];
                if (position == board[row + 1][col + 1] &&
                        position == board[row + 2][col + 2] &&
                        position == board[row + 3][col + 3] &&
                        position == board[row + 4][col + 4]) {
                    return true;
                }
            }
        }

        // Check reverse-diagonal
        for (int row = 0; row < board.length - 4; row++) {
            for (int col = 4; col < board[row].length; col++) {
                int position = board[row][col];
                if (position == board[row + 1][col - 1] &&
                        position == board[row + 2][col - 2] &&
                        position == board[row + 3][col - 3] &&
                        position == board[row + 4][col - 4]) {
                    return true;
                }
            }
        }

        return false;
    }
}
