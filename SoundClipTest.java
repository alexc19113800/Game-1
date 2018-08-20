import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

// To play sound using Clip, the process need to be alive.
// Hence, we use a Swing application.
public class SoundClipTest extends JFrame {
    AudioInputStream audioIn;
    Clip clip;
    boolean loop;
    // Constructor
    public SoundClipTest(String link, boolean l) {
        loop = l;
        this.setVisible(false);

        try {
            // Open an audio input stream.
            URL url = this.getClass().getClassLoader().getResource(link);
            audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        try{
            if(loop)
                clip.loop(Clip.LOOP_CONTINUOUSLY); 
            else{
                clip.start();
            }
        }catch(Exception e){
            System.out.println("Close audio error");
        }
    }

    public void stop(){
        try{
            clip.stop();
            clip.flush();
            clip.setFramePosition(0);
        }catch(Exception e){
            System.out.println("Close audio error");
            System.err.printf("Exception occured while trying to playback file '%s'. (%s)%n",
                "audioIn", e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public void close(){
        try{
            audioIn.close();
        }catch(Exception e){
            System.out.println("Close audio error");
        }
    }

    public static void main(String[] args) {
        new SoundClipTest("audio/bg_music.wav", true);
    }
}