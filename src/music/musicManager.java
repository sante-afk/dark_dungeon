package music;

import frames.Menu;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;


public class musicManager {

    private static final Random RANDOM = new Random();

    public static Clip playMusicMenu () {
        try {
            InputStream mainTheme = Menu.class.getResourceAsStream("/sounds/mainTheme.wav");
            AudioInputStream audioStream =
                    AudioSystem.getAudioInputStream(new BufferedInputStream(mainTheme));
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException s) {
            s.printStackTrace();
        }
        return null;
    }
    public static Clip playPathMusic() {
        boolean randomPathMusic = RANDOM.nextBoolean();
        String sound = randomPathMusic ? "mainPath.wav" : "path.wav";
        try {
            URL soundUrl = Menu.class.getResource("/sounds/" + sound);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundUrl);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException s) {
            s.printStackTrace();
        }
        return null;
    }
    public static Clip playFightMusic() {
        try {
            URL soundUrl = Menu.class.getResource("/sounds/fight.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundUrl);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException r) {
            r.printStackTrace();
        }
        return null;
    }
    public static void stopMusic(Clip clip) {
        clip.stop();
        clip.close();
    }
}
