import frames.Menu;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Main {

    public static void main () throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Menu.menuFrame();
    }

}