package frontend.menu.shop;

import backend.Manager;
import backend.User;
import backend.block.Saleable;
import frontend.Massage;
import frontend.tile.TileButton;

import java.awt.*;

public class AD extends TileButton {
    private final transient Manager manager = Manager.getInstance();
    boolean isSold = false;
    transient Saleable sales;

    AD(Saleable sales, User user) {
        setTileSize(Manager.COLUMN / Shop.COLUMN, Manager.ROW / Shop.ROW);
        this.sales = sales;
        for (String sold : user.bought)
            if (sales.getName().equals(sold))
                this.isSold = true;
        setToolTipText("<html>" + sales.getName() + "<br>" + sales.getDescription() + "<br>" + sales.getCost() + "</html>");
        addActionListener(e -> {
            if (isSold)
                new Massage("you have this since before !");
            else if (manager.currentUser().coins < sales.getCost())
                new Massage("you don't have enough coins :/");
            else {
                isSold = true;
                manager.currentUser().buy(sales);
                manager.currentUser().coins -= sales.getCost();
                new Massage("you buy this successfully !");
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(sales.getImage(), getWidth() / 4, getHeight() / 4, getWidth() / 2, getHeight() / 2, null);
    }

}
