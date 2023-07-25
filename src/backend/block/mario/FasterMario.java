package backend.block.mario;

public class FasterMario extends Mario {
    @Override
    public int getSpeed() {
        return super.getSpeed() * 3 / 2;
    }

    public int getCost() {
        return 20;
    }

    public String getName() {
        return "ّFaster Mario";
    }

    public String getDescription() {
        return "Go faster and faster with Faster Mario!";
    }

    @Override
    public String getImageName() {
        return "FasterMario.png";
    }
}
