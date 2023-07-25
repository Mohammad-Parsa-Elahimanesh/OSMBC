package backend.block.item;

public class Coin extends Item {
    public Coin(double x, double y) {
        super(x, y);
    }

    @Override
    protected String getImageName() {
        return "Coin.png";
    }
}
