package edu.rosehulman.somasur.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TicTacToeGame mGame = new TicTacToeGame(this);
    private TextView mGameStateTextView;
    private Button[][] mTicTacToeButtons;
    private Button newGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGameStateTextView = findViewById(R.id.message_text);

        newGameButton = findViewById(R.id.new_game_button);
        newGameButton.setOnClickListener(this);

        mTicTacToeButtons = new Button[TicTacToeGame.NUM_ROWS][TicTacToeGame.NUM_COLUMNS];
        for (int row = 0; row < TicTacToeGame.NUM_ROWS; row++) {
            for (int col = 0; col < TicTacToeGame.NUM_COLUMNS; col++) {
                int id = getResources().getIdentifier("button" + row + col, "id", getPackageName());
                Log.d("EEE ", "Finding tic id " + id);
                mTicTacToeButtons[row][col] = findViewById(id);
                mTicTacToeButtons[row][col].setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.new_game_button) {
            mGame.resetGame();
        }
        for (int row = 0; row < TicTacToeGame.NUM_ROWS; row++) {
            for (int col = 0; col < TicTacToeGame.NUM_COLUMNS; col++) {
                if (v.getId() == mTicTacToeButtons[row][col].getId()) {
                    Log.d("TTT ", "button pressed at " + row + col);
                    mGame.pressedButtonAtLocation(row, col);
                }
                mTicTacToeButtons[row][col].setText(mGame.stringForButtonAtLocation(row, col));
            }
        }
        mGameStateTextView.setText(mGame.stringForGameState());
    }
}
