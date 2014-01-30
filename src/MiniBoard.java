import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Dustin on 1/16/14.
 */
public class MiniBoard extends JPanel {
    private Box[][] button;
    private int winner;

    public MiniBoard(ActionListener listener) {
        super();
        winner = -1;
        button = new Box[3][3];
        setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                button[i][j] = new Box(i, j, this);
                button[i][j].addActionListener(listener);
                button[i][j].setFont(new Font("Awesome", Font.BOLD, 20));
                add(button[i][j]);

            }
        }
    }

    public void setFocus(boolean b) {
        if (b) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (button[i][j].getText().equals("")) {
                        button[i][j].setEnabled(true);
                    }
                }
            }
        } else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    button[i][j].setEnabled(false);
                }
            }
        }
    }

    public boolean hasWinner(int player) {
        if (winner != -1) {
            return true;
        }
        boolean win = checkRows();
        if (!win) {
            win = checkCols();
        }
        if (!win) {
            win = checkDiags();
        }
        if (win) {
            setDesign(player);
            winner = player;
        }

        return win;
    }

    private void setDesign(int player) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                button[i][j].setBackground((player == 1) ? Color.RED : Color.BLUE);
            }
        }
    }

    private boolean checkDiags() {
        String str = button[1][1].getText();
        if (!str.equals("") && (((button[0][0].getText().equals(str) && button[2][2].getText().equals(str)) || (button[2][0].getText().equals(str) && button[0][2].getText().equals(str))))) {
            return true;
        }
        return false;
    }

    private boolean checkCols() {
        for (int i = 0; i < 3; i++) {

            String str = button[0][i].getText();
            boolean winner = !str.equals("");
            for (int j = 1; j < 3 && winner; j++) {
                winner = str.equals(button[j][i].getText());
            }
            if (winner) {
                return winner;
            }
        }
        return false;
    }

    private boolean checkRows() {
        for (int i = 0; i < 3; i++) {

            String str = button[i][0].getText();
            boolean winner = !str.equals("");
            for (int j = 1; j < 3 && winner; j++) {
                winner = str.equals(button[i][j].getText());
            }
            if (winner) {
                return winner;
            }
        }
        return false;
    }

    public int getWinner() {
        return winner;
    }
}
