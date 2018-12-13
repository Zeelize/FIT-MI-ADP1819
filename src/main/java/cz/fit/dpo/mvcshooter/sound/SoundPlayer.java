package cz.fit.dpo.mvcshooter.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlayer {
    public static synchronized void playCollisionEffect() {
        playEffect("/sounds/collision.wav");
    }

    public static synchronized void playShootEffect() {
        playEffect("/sounds/shoot.wav");
    }

    public static synchronized void playReloadEffect() {
        playEffect("/sounds/reload.wav");
    }

    public static synchronized void playNewLevelEffect() {
        playEffect("/sounds/levelUp.wav");
    }

    public static synchronized void playEmptyMagazineEffect() {
        playEffect("/sounds/empty.wav");
    }

    private static synchronized void playEffect(String path) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            getClass().getResourceAsStream(path));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }
}
