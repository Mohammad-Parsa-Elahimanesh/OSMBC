package backend.block.mario;

public class JumperMario extends Mario {
    @Override
    public double getJumpSpeed() {
        return super.getJumpSpeed() * 5 / 4;
    }

    public int getCost() {
        return 50;
    }

    public String getName() {
        return "Jumper Mario";
    }

    public String getDescription() {
        return "Jump on the eiffel by Jumper Mario!";
    }

    @Override
    public String getImageName() {
        return "JumperMario.png";
    }
}
