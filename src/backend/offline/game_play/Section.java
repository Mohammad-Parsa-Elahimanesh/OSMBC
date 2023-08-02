package backend.offline.game_play;

import backend.Manager;
import backend.offline.block.Block;
import backend.offline.block.Checkpoint;
import backend.offline.block.Pipe;
import backend.offline.block.brick.Spring;
import backend.offline.block.brick.*;
import backend.offline.block.enemy.Goomba;
import backend.offline.block.enemy.Koopa;
import backend.offline.block.enemy.Spiny;
import backend.offline.block.flag.Flag;
import backend.offline.block.mario.Mario;
import backend.offline.block.mario.MarioState;
import frontend.menu.offline_game.AudioPlayer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Section {
    public static final double DELAY = 0.03;

    public final Mario mario;
    public final List<Checkpoint> savedCheckpoints = new ArrayList<>();
    public final List<Block> blocks = new ArrayList<>();
    final String name;
    private final List<Block> mustBeAdded = new ArrayList<>();
    private final List<Block> mustBeRemoved = new ArrayList<>();
    private int length;
    private int wholeTime;    public Timer timer = new Timer((int) (DELAY * 1000), e -> {
        Manager.currentGame().offlineGameFrame.repaint();
        Manager.currentSection().update();
    }) {
        @Override
        public void start() {
            super.start();
            AudioPlayer.setSilence(false);
        }

        @Override
        public void stop() {
            super.stop();
            AudioPlayer.setSilence(true);
        }
    };
    private Section nextSection;
    private double spentTime = 0;
    private int coins = 0;
    Section(int section, Mario mario, String name) throws Exception {
        this.name = name;
        add(new Brick(1, 30, -1, 0));
        this.mario = mario.getClass().getDeclaredConstructor().newInstance();
        switch (section) {
            case 0 -> section0();
            case 1 -> section1();
            case 2 -> section2();
        }
        add(this.mario);
        add(new Brick(3, 2, length - 3.0, 0));
        add(new Brick(1, 30, length, 0));
        new Flag(length - 3.0, 2, this);
    }

    Section(Mario mario, Section original, int hideNumber) throws Exception {
        name = "Secret";
        add(new Brick(1, 30, -1, 0));
        this.mario = mario.getClass().getDeclaredConstructor().newInstance();
        new Pipe(0, 2, false, this);
        switch (hideNumber) {
            case 0 -> hiddenSection0();
            case 1 -> hiddenSection1();
        }
        Pipe endPipe = new Pipe(length - 2, 2, false, this);
        add(new Brick(1, 30, length, 0));
        add(this.mario);
        endPipe.destination = original;
    }

    static Section makeSections(Mario mario) {
        try {
            Section section0 = new Section(0, mario, "learn");
            Section section1 = new Section(1, mario, "explore");
            Section section2 = new Section(2, mario, "fight");
            section0.nextSection = section1;
            section1.nextSection = section2;
            return section0;
        } catch (Exception e) {
            System.err.println("Can not make Sections Correctly!");
        }
        return null;
    }

    public int getLength() {
        return length;
    }

    public double getWholeTime() {
        return wholeTime;
    }

    public double getSpentTime() {
        return spentTime;
    }

    public void add(Block block) {
        mustBeAdded.add(block);
    }

    public void del(Block block) {
        mustBeRemoved.add(block);
    }

    private void updateBlocks() {
        blocks.addAll(mustBeAdded);
        mustBeAdded.clear();
        blocks.removeAll(mustBeRemoved);
        mustBeRemoved.clear();
    }

    public int getCoins() {
        return coins;
    }

    public void getCoin(int number) {
        coins += number;
    }

    void section0() throws Exception {
        length = 95;
        wholeTime = 60;

        add(new Brick(33, 2, 0, 0));
        add(new Soft(SoftType.Coin, 3, 5));
        add(new Soft(SoftType.Simple, 6, 5));
        add(new Solid(SolidType.Coins, 9, 5));
        add(new Solid(SolidType.Simple, 12, 5));
        for (int i = 1; i <= 9; i++)
            add(new Solid(SolidType.Prize, 15 + i, 5));
        for (int i = 1; i <= 7; i++) {
            add(new Soft(SoftType.Coin, 26 + i, 5));
            add(new Solid(SolidType.Prize, 26 + i, 9));
        }

        add(new Brick(10, 2, 36, 0));
        add(new Goomba(38, 2));
        add(new Goomba(42, 2));

        add(new Brick(5, 2, 48, 0));
        add(new Checkpoint(50, 2));
        add(new Spring(52, 4));

        add(new Brick(20, 2, 60, 0));
        add(new Koopa(65, 2));
        add(new Spiny(70, 2));
        add(new Koopa(75, 2));

        new Pipe(83, 3, true, this);
        Pipe secretPipe = new Pipe(87, 3, false, this);
        secretPipe.destination = new Section(mario, this, 0);
    }

    void section1() throws Exception {
        length = 70;
        wholeTime = 50;
        add(new Brick(40, 1, 0, 0));
        add(new Brick(30, 1, 5, 1));
        add(new Goomba(10, 2));
        add(new Goomba(20, 2));
        add(new Goomba(30, 2));
        for (int i = 10; i <= 30; i++)
            add(new Soft(SoftType.Simple, i, 7));
        add(new Goomba(15, 8));
        add(new Spiny(18, 8));
        add(new Goomba(20, 8));
        add(new Spiny(18, 8));
        add(new Goomba(25, 8));
        for (int i = 15; i <= 25; i++)
            add(new Solid(SolidType.Prize, i, 11));
        add(new Koopa(17, 12));
        add(new Koopa(20, 12));
        add(new Koopa(23, 12));

        add(new Brick(1, 4, 45, 0));
        add(new Brick(1, 7, 48, 0));
        add(new Brick(1, 10, 51, 0));
        new Pipe(55, 10, true, this);
        new Pipe(59, 10, true, this);
        Pipe secretpipe = new Pipe(63, 10, true, this);
        secretpipe.destination = new Section(mario, this, 1);
    }

    void section2() {
        length = 115;
        wholeTime = 100;
        add(new Brick(5, 1, 0, 0));
        for (int i = 2; i <= 4; i++)
            add(new Solid(SolidType.Coins, i, 9));

        add(new Brick(5, 1, 6, 3));
        for (double i = 6; i < 11; i += 2.5)
            add(new Goomba(i, 4));
        for (int i = 7; i <= 9; i++)
            add(new Solid(SolidType.Prize, i, 12));

        add(new Brick(5, 1, 11, 6));
        for (int i = 11; i <= 16; i += 2)
            add(new Koopa(i, 7));
        for (int i = 12; i <= 14; i++)
            add(new Soft(SoftType.Coin, i, 15));

        add(new Brick(4, 1, 16, 9));
        add(new Spiny(18, 10));
        add(new Brick(1, 1, 23, 2));
        add(new Brick(1, 1, 27, 6));
        add(new Brick(1, 1, 34, 3));
        add(new Brick(1, 1, 37, 9));
        add(new Checkpoint(37, 10));

        add(new Brick(10, 1, 45, 1));
        add(new Spiny(50, 2));
        add(new Brick(10, 1, 58, 5));
        add(new Spiny(61, 6));
        add(new Spiny(67, 6));
        add(new Brick(10, 1, 71, 9));
        add(new Spiny(72, 10));
        add(new Spiny(76, 10));
        add(new Spiny(80, 10));

        add(new Spring(85, 1));
        add(new Spring(93, 8));
        add(new Spring(102, 3));

    }

    void hiddenSection0() {
        length = 24;
        wholeTime = 20;
        for (int y = 0; y < 20; y += 4)
            for (int x = 2; x <= 21; x++)
                add(new Soft(SoftType.Coin, x, y));
    }

    void hiddenSection1() {
        length = 24;
        wholeTime = 15;
        add(new Brick(length - 4.0, 1, 2, 0));
        for (int x = 3; x <= 21; x += 3)
            add(new Goomba(x, 2));
    }

    void sectionReward() {
        Manager.currentGame().score += (wholeTime - spentTime) * mario.getPowerLevel();
        Manager.currentGame().score += mario.heart * 20 * mario.getPowerLevel();
        Manager.currentUser().offlineCoins += coins;
    }

    void marioDie() {
        mario.shield = 0;
        if (mario.Y < 0)
            mario.state = MarioState.mini;
        switch (mario.state) {
            case mini -> {
                Manager.currentGame().record.wholeTime += spentTime;
                AudioPlayer.playSound("marioDeath");
                spentTime = 0;
                coins -= ((savedCheckpoints.size() + 1) * coins + progressRisk()) / (savedCheckpoints.size() + 4);
                mario.reset();
                if (savedCheckpoints.isEmpty()) {
                    mario.X = 0;
                    mario.Y = 2;
                    spentTime = 0;
                } else {
                    mario.X = savedCheckpoints.get(savedCheckpoints.size() - 1).X;
                    mario.Y = savedCheckpoints.get(savedCheckpoints.size() - 1).Y;
                    spentTime = savedCheckpoints.get(savedCheckpoints.size() - 1).spendTime;
                }
                mario.heart--;
                if (mario.heart <= 0) {
                    timer.stop();
                    Manager.currentGame().endGame();
                }
                return;
            }
            case mega -> mario.state = MarioState.mini;
            case giga -> mario.state = MarioState.mega;
        }
        mario.beAlive();
    }

    double progressRate() {
        return mario.travelledDistance / length;
    }

    public int progressRisk() {
        return (int) (progressRate() * coins);
    }

    void update() {
        updateBlocks();
        for (Block block : blocks)
            block.update();
        if (!mario.isAlive())
            marioDie();
        else if (mario.goNext())
            nextSection();
        spentTime += Section.DELAY;
    }

    void nextSection() {
        Manager.currentGame().record.wholeTime += spentTime;
        timer.stop();
        Manager.currentSection().sectionReward();
        if (nextSection == null) {
            timer.stop();
            Manager.currentGame().record.score = Manager.currentGame().score;
            Manager.currentUser().records.add(Manager.currentGame().record);
            Manager.currentGame().endGame();
            return;
        }
        nextSection.mario.heart = mario.heart;
        nextSection.mario.state = mario.state;
        Manager.currentGame().currentSection = nextSection;
        Manager.currentSection().timer.start();

    }

    @Override
    public String toString() {
        return name;
    }




}
