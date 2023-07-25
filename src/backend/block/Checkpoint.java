package backend.block;


import backend.Manager;
import backend.gamePlay.Section;
import frontend.menu.game.CheckpointMenu;

public class Checkpoint extends Block {
    public double spendTime;
    boolean active = true;
    private double cooldown = 0.0;

    public Checkpoint(double x, double y) {
        super(1, 3, x, y);
    }

    public void destroy() {
        Manager.getInstance().currentSection().getCoin(Manager.getInstance().currentSection().progressRisk() / 4);
        remove();
        resume();
    }

    public void save() {
        active = false;
        spendTime = Manager.getInstance().currentSection().getSpentTime();
        Manager.getInstance().currentSection().getCoin(-Manager.getInstance().currentSection().progressRisk());
        Manager.getInstance().currentSection().savedCheckpoints.add(this);
        resume();
    }

    public void resume() {
        Manager.getInstance().currentSection().timer.start();
    }

    @Override
    public void update() {
        cooldown = Math.max(cooldown - Section.delay, 0.0);
        if (isIntersect(Manager.getInstance().currentMario()) && cooldown == 0.0 && active) {
            cooldown = 3;
            Manager.getInstance().currentMario().reset();
            Manager.getInstance().currentSection().timer.stop();
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
