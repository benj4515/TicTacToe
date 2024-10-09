
package dk.easv.tictactoe.bll;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 *
 * @author EASV
 */
public class GameBoard implements IGameBoard
{
    int id = 1;
    private char[][] board = new char[3][3];
    public char[][] getBoard(){
        return board;
    }
    public void setBoard(char[][] board){
        this.board = board;
    }
    public int checkBoard(){
        // Vertical win for X
        if (board[0][0] == 'X' && board[0][1] == 'X' && board[0][2] == 'X'){
            return 1;
        } else if (board[1][0] == 'X' && board[1][1] == 'X' && board[1][2] == 'X') {
            return 1;
        }else if (board[2][0] == 'X' && board[2][1] == 'X' && board[2][2] == 'X') {
            return 1;
        }
        // Horizontal win for X
        if (board[0][1] == 'X' && board[1][1] == 'X' && board[2][1] == 'X'){
            return 1;
        } else if (board[0][2] == 'X' && board[1][2] == 'X' && board[2][2] == 'X') {
            return 1;
        }else if (board[0][0] == 'X' && board[1][0] == 'X' && board[2][0] == 'X') {
            return 1;
        }
        // Diagonal win for X
        if (board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X'){
            return 1;
        } else if (board[2][0] == 'X' && board[1][1] == 'X' && board[0][2] == 'X') {
            return 1;
        }
        // Vertical win for O
        if (board[0][0] == 'O' && board[0][1] == 'O' && board[0][2] == 'O'){
            return 2;
        } else if (board[1][0] == 'O' && board[1][1] == 'O' && board[1][2] == 'O') {
            return 2;
        }else if (board[2][0] == 'O' && board[2][1] == 'O' && board[2][2] == 'O') {
            return 2;
        }
        // Horizontal win for O
        if (board[0][1] == 'O' && board[1][1] == 'O' && board[2][1] == 'O'){
            return 2;
        } else if (board[0][2] == 'X' && board[1][2] == 'X' && board[2][2] == 'O') {
            return 2;
        }else if (board[0][0] == 'O' && board[1][0] == 'O' && board[2][0] == 'O') {
            return 2;
        }
        // Diagonal win for O
        if (board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O'){
            return 2;
        } else if (board[2][0] == 'O' && board[1][1] == 'O' && board[0][2] == 'O') {
            return 2;
        }
        else {
            return -1;
        }
    }

    /** Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    public int getNextPlayer()
    {

        if (id == 0){
            id = 1;
            return 0;
        }
        else{
            id = 0;
            return 1;
        }


    }
    /*for (int a = 0; a< 8; a++){
        String line = switch (a){
            case 0 -> Array.get(char [0][0]board )
            case 1 -> (char)Array.getChar(char []board, int 0,0)
        }*/


    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is successfull the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver == true
     * this method will always return false.
     */
    public boolean play(int col, int row)
    {
        if (getBoard()[row][col] == 0){
            if (id == 0){
                getBoard()[row][col] = 'O';

            } else if (id == 1){
                getBoard()[row][col] = 'X';
            }
            setBoard(board);

            System.out.println(Arrays.deepToString(board));
            System.out.println(checkBoard());
            return true;

        }
       else{
           return false;
        }

    }

    /**
     * Tells us if the game has ended either by draw or by meeting the winning
     * condition.
     *
     * @return true if the game is over, else it will return false.
     */
    public boolean isGameOver()
    {
        //TODO Implement this method

            if (checkBoard() != -1){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    public int getWinner()
    {
        if(checkBoard() == 1){
            return 1;
        } else if (checkBoard() == 2) {
            return 0;
        } else {
            return -1;
        }
        //TODO Implement this method
    }

    /**
     * Resets the game to a new game state.
     */
    public void newGame()
    {
        board = new char[3][3];
    }
}
