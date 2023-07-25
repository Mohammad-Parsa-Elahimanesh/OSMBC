package backend.block.mario;

public class KillerMario extends Mario {

    @Override
    double getShotSpeed() {
        return super.getShotSpeed() * 2;
    }

    public int getCost() {
        return 15;
    }

    public String getName() {
        return "Killer Mario";
    }

    public String getDescription() {
        return "Kill enemies faster than drinking a glass of water !";
    }

    @Override
    public String getImageName() {
        return "KillerMario.png";
    }
}
