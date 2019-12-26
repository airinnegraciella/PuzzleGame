package com.example.puzzlegame;

public class Game {
    private PuzzleBoard newBoard;

    public Game(){
        newBoard = new PuzzleBoard();
        newBoard.initBoard();
    }

    public Game(String level){
        if(level.equals("easy")){
            newBoard = new PuzzleBoard(3);
            newBoard.initBoard();
        }
        else if(level.equals("normal")){
            newBoard = new PuzzleBoard();
            newBoard.initBoard();
        }
        else if(level.equals("hard")){
            newBoard = new PuzzleBoard(5);
            newBoard.initBoard();
        }
        else {
            System.out.println("Invalid input!");
        }
    }

    public PuzzleBoard getNewBoard(){
        return newBoard;
    }
}
