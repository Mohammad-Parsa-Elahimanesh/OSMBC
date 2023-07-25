package backend.block;

import backend.Manager;
import backend.game_play.Section;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.min;

public abstract class Block {
    static final double G = 35;
    static final double MAX_MOVE = 0.8;
    static final double EPS = 0.01;
    static final String MIRRORED = "MIRRORED";
    static Map<String, BufferedImage> images = new HashMap<>();
    public double X, W, H, Y;
    protected double vx = 0;
    protected double vy = 0;

    protected Block(double w, double h, double x, double y) {
        setShape(w, h, x, y);
    }

    protected boolean neighbor(Block other) {
        double x = distanceHorizontal(other);
        double y = distanceVertical(other);
        return min(x, y) < 0 && Math.max(x, y) == 0;
    }

    public void remove() {
        Manager.getInstance().currentSection().del(this);
    }

    protected Boolean inSide(Block other, Direction side) {
        if (isIntersect(other))
            return false;
        if (side.isHorizontal())
            return distanceVertical(other) < 0 &&
                    ((side == Direction.LEFT && other.X + other.W <= X) || (side == Direction.RIGHT && X + W <= other.X));
        else
            return distanceHorizontal(other) < 0 &&
                    ((side == Direction.DOWN && other.Y + other.H <= Y) || (side == Direction.UP && Y + H <= other.Y));
    }

    protected boolean neighbor(Block other, Direction direction) {
        return neighbor(other) && inSide(other, direction);
    }

    public boolean isIntersect(Block other) {
        return distanceHorizontal(other) < 0 && distanceVertical(other) < 0 && other != this;
    }

    double manhattanDistance(Block other) {
        return Math.max(0, distanceHorizontal(other)) + Math.max(0, distanceVertical(other));
    }

    protected double distance(Block other) {
        return Math.sqrt(Math.pow(Math.max(0, distanceHorizontal(other)), 2)
                + Math.pow(Math.max(0, distanceVertical(other)), 2));
    }

    double distanceVertical(Block other) {
        return Math.max(-EPS, Math.max(Y, other.Y) - min(Y + H, other.Y + other.H));
    }

    double distanceHorizontal(Block other) {
        return Math.max(-EPS, Math.max(X, other.X) - min(X + W, other.X + other.W));
    }

    public void setShape(double w, double h, double x, double y) {
        W = w;
        H = h;
        X = x;
        Y = y;
    }

    protected abstract String getImageName();

    private BufferedImage getMirroredImage(BufferedImage originalImage) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        BufferedImage mirrorImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < height; y++) {
            for (int lx = 0, rx = width - 1; lx < width; lx++, rx--) {
                int p = originalImage.getRGB(lx, y);
                mirrorImage.setRGB(rx, y, p);
            }
        }
        return mirrorImage;
    }

    public Image getImage() {
        if (!images.containsKey(getImageName())) {
            try {
                images.put(getImageName(), ImageIO.read(new File("Images\\" + getImageName())));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (vx < 0 && !images.containsKey(MIRRORED + getImageName())) {
            images.put(MIRRORED + getImageName(), getMirroredImage(images.get(getImageName())));
        }
        return images.get((vx < 0 ? MIRRORED : "") + getImageName());
    }

    protected double push(Direction direction) {
        double canMove = MAX_MOVE;
        for (Block block : Manager.getInstance().currentSection().blocks)
            if (neighbor(block, direction)) {
                if (!block.pushed(direction.opposite()))
                    canMove = 0;
            } else if (Boolean.TRUE.equals(inSide(block, direction)))
                canMove = min(canMove, manhattanDistance(block));
        return canMove;
    }

    public int getDirection() {
        return vx < 0 ? -1 : 1;
    }

    protected abstract boolean pushed(Direction side);

    protected boolean doesGravityAffects() {
        return false;
    }

    protected void intersect(Block block) {
    }

    public void draw(Graphics g, int cameraLeftLine) {
        if (cameraLeftLine <= Manager.SINGLE_BLOCK_WIDTH * X || (X + W - Manager.COLUMN) * Manager.SINGLE_BLOCK_WIDTH <= cameraLeftLine) {
            g.drawImage(getImage(), (int) (Manager.SINGLE_BLOCK_WIDTH * X - cameraLeftLine), (int) (Manager.SCREEN_HEIGHT - Manager.SINGLE_BLOCK_HEIGHT * (Y + H)), (int) (W * Manager.SINGLE_BLOCK_WIDTH), (int) (H * Manager.SINGLE_BLOCK_HEIGHT), null);
        }
    }

    public void update() {
        if (doesGravityAffects()) {
            if (push(Direction.DOWN) > 0)
                vy -= Section.DELAY * G;
            else if (vy < 0)
                vy = 0;
        }

        if (Math.abs(vx) < EPS && vx != 0) vx = EPS * getDirection();
        if (Math.abs(vy) < EPS && vy != 0) vy = EPS * (vy < 0 ? -1 : 1);

        if (vx < 0) {
            double maxMove = -vx * Section.DELAY;
            double canMove = maxMove;
            while (canMove > 0) {
                canMove = min(maxMove, push(Direction.LEFT));
                X -= canMove;
                maxMove -= canMove;
            }
            if (maxMove > 0)
                pushed(Direction.LEFT);
        }
        if (vx > 0) {
            double maxMove = vx * Section.DELAY;
            double canMove = maxMove;
            while (canMove > 0) {
                canMove = min(maxMove, push(Direction.RIGHT));
                X += canMove;
                maxMove -= canMove;
            }
            if (maxMove > 0)
                pushed(Direction.RIGHT);
        }

        if (vy < 0) {
            double maxMove = -vy * Section.DELAY;
            double canMove = maxMove;
            while (canMove > 0) {
                canMove = min(maxMove, push(Direction.DOWN));
                Y -= canMove;
                maxMove -= canMove;
            }
            if (maxMove > 0)
                pushed(Direction.DOWN);
        }
        if (vy > 0) {
            double maxMove = vy * Section.DELAY;
            double canMove = maxMove;
            while (canMove > 0) {
                canMove = min(maxMove, push(Direction.UP));
                Y += canMove;
                maxMove -= canMove;
            }
            if (maxMove > 0)
                pushed(Direction.UP);
        }
    }

    @Override
    public String toString() {
        return "Block " + getImageName() + " {" +
                "X=" + X +
                ", SCREEN_WIDTH=" + W +
                ", H=" + H +
                ", Y=" + Y +
                '}';
    }

    public enum Direction {
        LEFT,
        UP,
        RIGHT,
        DOWN;


        public Direction opposite() {
            return switch (this) {
                case LEFT -> RIGHT;
                case RIGHT -> LEFT;
                case UP -> DOWN;
                case DOWN -> UP;
            };
        }

        public boolean isHorizontal() {
            return this == RIGHT || this == LEFT;
        }
    }
}


