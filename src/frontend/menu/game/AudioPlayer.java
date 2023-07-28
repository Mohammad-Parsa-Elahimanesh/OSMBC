package frontend.menu.game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class AudioPlayer {
    static final Clip background = getClip("background");
    public static boolean silenceForever = false;
    private static boolean silence = true;

    private AudioPlayer() {
    }


    public static void setSilence(boolean silence) {
        if (silence == AudioPlayer.silence || silenceForever)
            return;
        if (silence)
            background.stop();
        else {
            background.loop(Clip.LOOP_CONTINUOUSLY);
            background.start();
        }
        AudioPlayer.silence = silence;
    }

    private static Clip getClip(String name) {
        Clip clip;
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Sounds//" + name + ".wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new NullPointerException();
        }
        return clip;
    }

    public static void playSound(String name) {
        if (!silence && !silenceForever)
            getClip(name).start();
    }

}
