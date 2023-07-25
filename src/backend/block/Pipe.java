package backend.block;

import backend.Manager;
import backend.block.enemy.KillerPlant;
import backend.gamePlay.Section;

public class Pipe extends Block {
    public Section destination = null;

    public Pipe(double x, double h, boolean hasPlant, Section section) {
        super(2, h, x, 0);
        section.add(this);
        if (hasPlant)
            section.add(new KillerPlant(this));
    }

    @Override
    protected String getImageName() {
        return "Pipe.png";
    }

    @Override
    protected boolean pushed(Direction side) {
        if (neighbor(Manager.getInstance().currentMario(), Direction.UP) && Manager.getInstance().currentMario().isDirection(Direction.DOWN) && destination != null) {
            Manager.getInstance().currentSection().timer.stop();
            Manager.getInstance().currentMario().Y += 0.5;
            destination.mario.state = Manager.getInstance().currentMario().state;
            destination.mario.heart = Manager.getInstance().currentMario().heart;
            Manager.getInstance().currentGame().currentSection = destination;
            destination = null;
            Manager.getInstance().currentSection().timer.start();
        }

        return false;
    }
}
