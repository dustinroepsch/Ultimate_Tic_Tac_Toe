import javax.swing.*;

/**
 * Created by Dustin on 1/16/14.
 */
public class Box extends JButton {
    private int row;
    private int col;
    private MiniBoard game;

    public Box(int r, int c, MiniBoard board) {
        super("");
        row = r;
        col = c;
        game = board;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public MiniBoard getGame() {
        return game;
    }
}
