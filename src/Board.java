import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Dustin on 1/16/14.
 */
public class Board extends JPanel {
    private MiniBoard[][] subGame;
    private boolean playerXTurn;

    public Board() {
        super();
        playerXTurn = true;
        subGame = new MiniBoard[3][3];
        setLayout(new GridLayout(3, 3, 10, 10));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                subGame[i][j] = new MiniBoard(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Box caller = (Box) e.getSource();
                        if (caller.getText().equals("")) {
                            caller.setText((playerXTurn) ? "X" : "O");
                            caller.setForeground((playerXTurn) ? Color.RED : Color.BLUE);
                            caller.getGame().hasWinner(playerXTurn ? 1 : 2);
                            if (!subGame[caller.getRow()][caller.getCol()].hasWinner(-1)) {
                                setGameFocus(caller.getRow(), caller.getCol());
                            } else {
                                setGameFocusAll();
                            }
                            if (gameWon()) {
                                JOptionPane.showMessageDialog(getComponent(0), ((playerXTurn) ? "Player X " : "Player O ") + "Has Won!");
                                System.exit(0);
                            }
                            if (gameTie()) {
                                JOptionPane.showMessageDialog(getComponent(0), "There was a tie!");
                                System.exit(0);
                            }
                            playerXTurn = !playerXTurn;
                        }
                    }
                });
                add(subGame[i][j]);
            }
        }
    }

    private boolean gameTie() {
        boolean tie = true;
        for (int i = 0; i < 3 && tie; i++) {
            for (int j = 0; j < 3 && tie; j++) {
                tie = subGame[i][j].hasWinner(-1);
            }
        }
        return tie;//
    }

    private boolean gameWon() {
        boolean win = checkRows();
        if (!win) {
            win = checkCols();
        }
        if (!win) {
            win = checkDiags();
        }
        return win;
    }

    private boolean checkCols() {
        for (int i = 0; i < 3; i++) {

            int p = subGame[0][i].getWinner();
            boolean winner = p != -1;
            for (int j = 1; j < 3 && winner; j++) {
                winner = p == subGame[j][i].getWinner();
            }
            if (winner) {
                return winner;
            }
        }
        return false;
    }

    private boolean checkDiags() {
        int p = subGame[1][1].getWinner();
        if (p != -1 && (((subGame[0][0].getWinner() == p) && subGame[2][2].getWinner() == p) || (subGame[2][0].getWinner() == p && subGame[0][2].getWinner() == p))) {
            return true;
        }
        return false;
    }

    private boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            int p = subGame[i][0].getWinner();
            boolean winner = p != -1;
            for (int j = 1; j < 3 && winner; j++) {
                winner = p == subGame[i][j].getWinner();
            }
            if (winner) {
                return winner;
            }
        }
        return false;
    }

    private void setGameFocusAll() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!subGame[i][j].hasWinner(-1)) {
                    subGame[i][j].setFocus(true);
                } else {
                    subGame[i][j].setFocus(false);
                }
            }
        }
    }

    private void setGameFocus(int r, int c) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                subGame[i][j].setFocus(false);
            }
        }
        subGame[r][c].setFocus(true);
    }
}
