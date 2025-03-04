package loaders;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {
    private Clip clip; //声音剪辑对象

    public void loadAudio(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File audioFile = new File(filePath);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
    }

    public AudioPlayer(String filePath) throws Exception{
        loadAudio(filePath);
    }

    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }

    public void stop(){
        clip.stop();
    }

    public void close(){
        clip.close();
    }
}
