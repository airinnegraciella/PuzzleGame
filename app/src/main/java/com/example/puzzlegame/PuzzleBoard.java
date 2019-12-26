package com.example.puzzlegame;

public class PuzzleBoard {
    private int size;
    private int totalTile;

    private Puzzle[][] board;

    private int currX_row;
    private int currX_col;

    public PuzzleBoard(){
        size = 4;
        totalTile = size*size;
    }

    public PuzzleBoard(int size){
        this.size = size;
        totalTile = size*size;
    }

    public void initBoard(){
        int value = 1;
        board = new Puzzle[size][size];
        for(int row=0; row<size; row++){
            for(int col=0; col<size; col++){
                if(row==(size-1) && col==(size-1)){
                    board[row][col]= new Puzzle(row,col,"");
                    currX_row = row;
                    currX_col = col;
                }
                else{
                    board[row][col]= new Puzzle(row,col,Integer.toString(value));
                }
                value++;
            }
        }
        shuffleBoard();
        printBoard();
    }

    public void shuffleBoard(){
        for(int row=0; row<size; row++){
            for(int col=0; col<size; col++){
                int loop = 200;
                boolean flag = true;
                while(flag && loop-->=0){
                    int getMove = shuffleMove();
                    if(getMove==1){
                        moveUp();
//                        System.out.println("UP" +" Row: " + currX_row + " Col: " + currX_col);
                    }
                    else if(getMove==2){
                        moveDown();
//                        System.out.println("DOWN"+" Row: " + currX_row + " Col: " + currX_col);
                    }
                    else if(getMove==3){
                        moveRight();
//                        System.out.println("RIGHT"+" Row: " + currX_row + " Col: " + currX_col);
                    }
                    else if(getMove==4){
                        moveLeft();
//                        System.out.println("LEFT"+" Row: " + currX_row + " Col: " + currX_col);
                    }
//                    printBoard();
                    if(currX_row==row && currX_col==col){
                        flag = false;
                    }
                }
            }
        }


    }

    public int shuffleMove(){
        return (int)(Math.random()*4)+1;
    }

    public void moveUp(){
        if((currX_row-1)>=0){
            swap(currX_row-1,currX_col);
        }
    }

    public void moveDown(){
        if((currX_row+1)<=(size-1)){
            swap(currX_row+1,currX_col);
        }
    }

    public void moveRight(){
        if((currX_col+1)<=(size-1)){
            swap(currX_row,currX_col+1);
        }
    }

    public void moveLeft(){
        if((currX_col-1)>=0){
            swap(currX_row,currX_col-1);
        }
    }

    public void swap(int targetRow, int targetCol){
        Puzzle temp = board[currX_row][currX_col];
        board[currX_row][currX_col]=board[targetRow][targetCol];
        board[targetRow][targetCol]=temp;
        currX_row=targetRow;
        currX_col=targetCol;
    }

    public void printBoard(){
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print(board[row][col].getValue() + "\t");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public boolean isWinning(){
        boolean flag = true;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if(!board[row][col].isPositionTrue(row,col)){
                    flag = false;
                    break;
                }
            }
            System.out.println("");
            if(!flag){
                break;
            }
        }
        return flag;
    }

    public Puzzle[][] getBoard(){
        return board;
    }

    public int getCurrX_col() {
        return currX_col;
    }

    public int getCurrX_row() {
        return currX_row;
    }
}
