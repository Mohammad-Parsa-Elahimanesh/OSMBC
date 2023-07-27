package frontend.menu.game;

import backend.Manager;
import backend.block.Checkpoint;

import javax.swing.*;
import java.awt.*;

public class CheckpointMenu extends JFrame {
    final Checkpoint checkpoint;
    final int progressRisk;

    public CheckpointMenu(Checkpoint checkpoint) {
        this.checkpoint = checkpoint;
        progressRisk = Manager.getInstance().currentSection().progressRisk();
        setSize(200, 100);
        setLocation((Manager.SCREEN_WIDTH - getWidth()) / 2+Manager.location.x, (Manager.SCREEN_HEIGHT - getHeight()) / 2+Manager.location.y);
        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(new GridLayout(3, 1));
        panel.add(save());
        panel.add(ignore());
        panel.add(destroy());
        setUndecorated(true);
        setVisible(true);
    }

    JButton save() {
        JButton save = new JButton("Save");
        save.addActionListener(e -> {
            if (Manager.getInstance().currentSection().getCoins() < progressRisk)
                return;
            checkpoint.save();
            dispose();
        });
        save.setToolTipText("Your progress and time will be saved and you must pay " + progressRisk + " coins.");
        return save;
    }

    JButton ignore() {
        JButton ignore = new JButton("Ignore");
        ignore.addActionListener(e -> {
            checkpoint.resume();
            dispose();
        });
        ignore.setToolTipText("Ignore checkpoint.");
        return ignore;
    }

    JButton destroy() {
        JButton destroy = new JButton("Destroy");
        destroy.addActionListener(e -> {
            checkpoint.destroy();
            dispose();
        });
        destroy.setToolTipText("Checkpoint will be destroyed and you gain " + progressRisk / 4 + " coins.");
        return destroy;
    }
}
