package com.example.puzzlegame;

public class Puzzle {
    private final int finalRow;
    private final int finalCol;
    private final String value;

    public Puzzle(int finalRow, int finalCol, String value) {
        this.finalRow = finalRow;
        this.finalCol = finalCol;
        this.value = value;
    }

    public int getFinalRow() {
        return finalRow;
    }

    public int getFinalCol() {
        return finalCol;
    }

    public String getValue() {
        return value;
    }

    public boolean isPositionTrue(int currRow, int currCol){
        if(currRow == finalRow && currCol == finalCol){
            return true;
        }
        else {
            return false;
        }
    }
}
