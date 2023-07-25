package frontend.menu.shop;

import backend.Manager;
import frontend.menu.MainMenu;
import frontend.tile.TileButton;
import frontend.tile.TileLabel;

import javax.swing.*;
import java.awt.*;

public class Shop extends JFrame {
    static final int ROW = 2;
    static final int COLUMN = 3;
    private final transient Manager manager = Manager.getInstance();

    public Shop() {
        super();
        JPanel panel = new JPanel(new GridLayout(ROW, COLUMN));
        setUndecorated(true);

        setSize(Manager.SCREEN_WIDTH, Manager.SCREEN_HEIGHT);
        JPanel[][] gridPanel = new JPanel[ROW][COLUMN];
        for (int i = 0; i < ROW; i++)
            for (int j = 0; j < COLUMN; j++) {
                gridPanel[i][j] = new JPanel(new GridLayout(1, 1));
                panel.add(gridPanel[i][j]);
            }
        gridPanel[0][0].add(showCoin());
        gridPanel[1][0].add(backButton());
        add(panel);
        setVisible(true);
    }

    TileLabel showCoin() {
        TileLabel showCoin = new TileLabel();
        showCoin.setTileSize(Manager.COLUMN / COLUMN, Manager.ROW / ROW);
        showCoin.setText("Coins: " + manager.currentUser().coins);

        Timer t = new Timer(3000, e -> showCoin.setText("Coins: " + manager.currentUser().coins));

        t.start();
        showCoin.setFont(new Font("Arial", Font.PLAIN, 50));
        return showCoin;
    }

    TileButton backButton() {
        TileButton back = new TileButton();
        back.setText("Back");
        back.setFont(new Font("Arial", Font.PLAIN, 40));
        back.setTileSize(Manager.COLUMN / COLUMN, Manager.ROW / ROW);

        back.addActionListener(e -> {
            dispose();
            new MainMenu();
        });
        return back;
    }
}
