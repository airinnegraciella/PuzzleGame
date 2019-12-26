package com.example.puzzlegame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Game game = new Game();             //create a new game
    GridLayout gridLayout;              //declaring the layout(grid)
    RelativeLayout layoutSwipe;         //declaring the layout(relative) - for swiping
    TextView[][] textViews;             //declaring a 2D array of TextView
    int size = 4;                       //size of the board game
    Button btn_newGame;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayout = findViewById(R.id.grid_layout);        //setting the var with the id
        btn_newGame = (Button) findViewById(R.id.btn_new_game);
        btn_newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "New Game", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getBaseContext(), MainActivity.class); //change it to your main class
                //the following 2 tags are for clearing the backStack and start fresh
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
                startActivity(i);
            }
        });
        layoutSwipe = findViewById(R.id.layout_swipe);      //setting the var with the id
        layoutSwipe.setOnTouchListener(new OnSwipeTouchListener(this){      //swiping motion (custom class)
            /*
            on every swipe motion:
            - the 'blank' tile goes the opposite direction of the swipe.
            because the moving function is to swap the 'blank' tile with the 'targeted' tile
            - then update the board with drawBoard().
            - then check if we won.
             */
            public void onSwipeTop() {
                Toast.makeText(MainActivity.this, "top", Toast.LENGTH_SHORT).show();
                game.getNewBoard().moveDown();
                drawBoard();
                checkWin();
            }
            public void onSwipeRight() {
                Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
                game.getNewBoard().moveLeft();
                drawBoard();
                checkWin();
            }
            public void onSwipeLeft() {
                Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
                game.getNewBoard().moveRight();
                drawBoard();
                checkWin();
            }
            public void onSwipeBottom() {
                Toast.makeText(MainActivity.this, "bottom", Toast.LENGTH_SHORT).show();
                game.getNewBoard().moveUp();
                drawBoard();
                checkWin();
            }
        });
        initBoard();        //initialise the board
    }

    private void initBoard() {
        textViews = new TextView[size][size];       //creating a new TextView 2D array
        gridLayout.setColumnCount(size);            //setting the column of the grid on the layout
        for(int row=0; row<size; row++){
            for(int col=0; col<size; col++){
                //to inflate a new view layout
                TextView view = (TextView)LayoutInflater.from(this).inflate(R.layout.item_puzzle,gridLayout,false);
                gridLayout.addView(view);       //attach the view to the parent
                textViews[row][col]=view;       //adding the view to the array
                if(game.getNewBoard().getBoard()[row][col].getValue().isEmpty()){       //if it's the 'blank' tile -> set it to white
                    textViews[row][col].setBackgroundColor(ContextCompat.getColor(this,R.color.white));
                }
                else{                                                                   //if not -> set it to it's primary colour; indicating it's a tile
                    textViews[row][col].setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary));
                }
                textViews[row][col].setText(game.getNewBoard().getBoard()[row][col].getValue());    //putting the numbers on the tile
            }
        }
    }

    private void drawBoard(){       //for updating the board; each tile
        for(int row=0; row<size; row++){
            for(int col=0; col<size; col++){
                if(game.getNewBoard().getBoard()[row][col].getValue().isEmpty()){    //checking if it's the 'blank' tile
                    textViews[row][col].setBackgroundColor(ContextCompat.getColor(this,R.color.white));
                }
                else{
                    textViews[row][col].setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary));
                }
                textViews[row][col].setText(game.getNewBoard().getBoard()[row][col].getValue());
            }
        }
    }

    private void checkWin(){        //checks if the user won the game
        if(game.getNewBoard().isWinning()){
            Toast.makeText(MainActivity.this,"You Win!", Toast.LENGTH_LONG).show();
        }

    }


}
