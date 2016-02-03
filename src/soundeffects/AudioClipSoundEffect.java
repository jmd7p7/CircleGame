package soundeffects;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

public abstract class AudioClipSoundEffect {

    private AudioClip clip;
    
    public AudioClipSoundEffect(String path) throws MalformedURLException{
    	URL url = Paths.get(path).toUri().toURL();
    	this.clip = Applet.newAudioClip(url);
    }
    
	public void PlaySoundEffect() {
		this.clip.stop();
		this.clip.play();
	}

}
