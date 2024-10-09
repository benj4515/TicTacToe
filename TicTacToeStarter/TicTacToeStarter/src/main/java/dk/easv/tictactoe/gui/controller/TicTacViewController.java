
package dk.easv.tictactoe.gui.controller;

// Java imports
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

// Project imports
import dk.easv.tictactoe.bll.GameBoard;
import dk.easv.tictactoe.bll.IGameBoard;

/**
 *
 * @author EASV
 */
public class TicTacViewController implements Initializable
{
    private int i;
    @FXML
    private Label lblPlayer;

    @FXML
    private Button btnNewGame;

    @FXML
    private GridPane gridPane;
    
    private static final String TXT_PLAYER = "Player: ";
    private IGameBoard game;

    /**
     * Event handler for the grid buttons
     *
     * @param event
     */
    @FXML
    private void handleButtonAction(ActionEvent event)
    {


        try
        {
            Integer row = GridPane.getRowIndex((Node) event.getSource());
            Integer col = GridPane.getColumnIndex((Node) event.getSource());
            int r = (row == null) ? 0 : row;
            int c = (col == null) ? 0 : col;
            int player = game.getNextPlayer();
            if (game.play(c, r))
            {
                i++;
                if (game.isGameOver())
                {
                    int winner = game.getWinner();
                    displayWinner(winner);
                    Button btn = (Button) event.getSource();
                    String xOrO = player == 0 ? "🐗" : "🐻";
                    btn.setText(xOrO);
                }
                else
                {
                    Button btn = (Button) event.getSource();
                    String xOrO = player == 0 ? "🐗" : "🐻";
                    btn.setText(xOrO);
                    game.getNextPlayer();
                    setPlayer();

                }
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Event handler for starting a new game
     *
     * @param event
     */
    @FXML
    private void handleNewGame(ActionEvent event)
    {
        game.newGame();
        setPlayer();
        clearBoard();
        i = 0;
    }

    /**
     * Initializes a new controller
     *
     * @param url
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param rb
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        game = new GameBoard();
        setPlayer();
    }

    /**
     * Set the next player
     */
    private void setPlayer()
    {
        if(game.getNextPlayer() == 1){
            lblPlayer.setText(TXT_PLAYER + "🐻");
        }
        else {
            lblPlayer.setText(TXT_PLAYER + "🐗");
        }

    }


    /**
     * Finds a winner or a draw and displays a message based
     * @param winner
     */
    private void displayWinner(int winner)
    {
        if(i>=9){
            winner = -1;
        }

        if (winner == 1)
            lblPlayer.setText("Player " + "🐗" + " wins!!!");
        else if (winner == 0) {
            lblPlayer.setText("Player " + "🐻" + " wins!!!");
        }else if (winner == -1){
            lblPlayer.setText("It's a draw :-(");
        }
    }

    /**
     * Clears the game board in the GUI
     */
    private void clearBoard()
    {
        for(Node n : gridPane.getChildren())
        {
            Button btn = (Button) n;
            btn.setText("");
        }
    }
}
