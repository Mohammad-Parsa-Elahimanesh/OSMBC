package backend.offline.block.item;

public class Flower extends Item {
    Flower(double x, double y) {
        super(x, y);
    }

    @Override
    protected String getImageName() {
        return "flower.png";
    }
}
