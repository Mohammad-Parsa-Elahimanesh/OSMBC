package backend.offline.block.flag;

import backend.Manager;
import backend.offline.block.Block;
import backend.offline.game_play.Section;
import frontend.menu.game.AudioPlayer;

public class Flag extends Block {
    final FlagPicture flagPicture;
    boolean finished = false;

    public Flag(double x, double y, Section section) {
        super(1, 12, x, y);
        flagPicture = new FlagPicture(x + 0.5, y);
        section.add(flagPicture);
        section.add(this);
    }

    @Override
    public void update() {

        if (isIntersect(Manager.currentMario()) || finished) {
            if (!finished)
                AudioPlayer.playSound("completeSection");
            finished = true;
            flagPicture.goUp();
            if (flagPicture.Y + flagPicture.H + 1.5 >= Y + H)
                Manager.currentMario().nextASAP = true;
        }
        super.update();
    }

    @Override
    protected String getImageName() {
        return "rod.png";
    }

    @Override
    protected boolean pushed(Direction side) {
        return true;
    }
}
