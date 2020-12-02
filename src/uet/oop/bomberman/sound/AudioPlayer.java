package uet.oop.bomberman.sound;

import sun.applet.Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer implements Runnable {
    private static boolean muted = false;
    private Clip clip;

    public AudioPlayer(String sound) {
        try {
            clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    Main.class.getResourceAsStream("/sound/" + sound + ".wav"));
            clip.open(inputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void loop() {
        if (!muted) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    @Override
    public void run() {
        this.loop();
    }

    public void stop() {
        clip.stop();
    }

    public void mute() {
        muted = !muted;
        if (muted) {
            this.stop();
        } else {
            this.loop();
        }
    }
}
