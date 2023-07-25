package backend.block.item;

import backend.block.Block;

abstract public class Item extends Block {
    Item(double x, double y) {
        super(1, 1, x, y);
    }

    public static Item RandomItem(double x, double y) {
        if (Math.random() < 0.65) return new Coin(x, y);
        else if (Math.random() < 0.6) return new Mushroom(x, y);
        else if (Math.random() < 0.5) return new Flower(x, y);
        else return new Star(x, y);
    }

    @Override
    protected boolean pushed(Direction side) {
        return true;
    }

    @Override
    protected boolean doesGravityAffects() {
        return true;
    }
}
