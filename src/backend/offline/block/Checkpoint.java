package backend.offline.block;


import backend.Manager;
import backend.offline.game_play.Section;
import frontend.menu.offline_game.CheckpointMenu;

public class Checkpoint extends Block {
    public double spendTime;
    boolean active = true;
    private double cooldown = 0.0;

    public Checkpoint(double x, double y) {
        super(1, 3, x, y);
    }

    public void destroy() {
        Manager.currentSection().getCoin(Manager.currentSection().progressRisk() / 4);
        remove();
        resume();
    }

    public void save() {
        active = false;
        spendTime = Manager.currentSection().getSpentTime();
        Manager.currentSection().getCoin(-Manager.currentSection().progressRisk());
        Manager.currentSection().savedCheckpoints.add(this);
        resume();
    }

    public void resume() {
        Manager.currentMario().reset();
        Manager.currentSection().timer.start();
    }

    @Override
    public void update() {
        cooldown = Math.max(cooldown - Section.DELAY, 0.0);
        if (isIntersect(Manager.currentMario()) && cooldown == 0.0 && active) {
            cooldown = 3;
            Manager.currentMario().reset();
            Manager.currentSection().timer.stop();
            new CheckpointMenu(this);
        }
        super.update();
    }

    @Override
    protected boolean doesGravityAffects() {
        return true;
    }

    @Override
    protected String getImageName() {
        return "checkpoint.png";
    }

    @Override
    protected boolean pushed(Direction side) {
        return true;
    }
}
