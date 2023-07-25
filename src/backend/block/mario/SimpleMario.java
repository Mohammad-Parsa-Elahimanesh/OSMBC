package backend.block.mario;

import backend.block.Block;

public class SimpleMario extends Mario {

    public int getCost() {
        return 0;
    }

    public String getName() {
        return "simple mario";
    }

    public String getDescription() {
        return "enjoy life and It's greatest world's secret !";
    }

    @Override
    public String getImageName() {
        return (task.get(Block.Direction.DOWN) && !task.get(Block.Direction.UP) && state != MarioState.mini ? "Seating" : "") +
                (state == MarioState.giga ? "Giga" : "") +
                "SimpleMario.png";
    }
}
