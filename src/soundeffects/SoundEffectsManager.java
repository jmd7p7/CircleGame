package soundeffects;

import java.net.MalformedURLException;
import java.util.HashMap;

public class SoundEffectsManager implements ISoundEffect{
	
	private static SoundEffectsManager instance = null;
	private HashMap<String, AudioClipSoundEffect> sounds;
	
	private SoundEffectsManager() throws MalformedURLException{
		loadSounds();
	}
	
	private void loadSounds() throws MalformedURLException {
		sounds = new HashMap<String, AudioClipSoundEffect>();
		sounds.put("Ally", new AllyAudioClipSoundEffect("Resources/Audio/Ally.AU"));
		sounds.put("Enemy", new AllyAudioClipSoundEffect("Resources/Audio/Enemy.AU"));
		sounds.put("Win", new AllyAudioClipSoundEffect("Resources/Audio/Win.AU"));
		sounds.put("Laser", new AllyAudioClipSoundEffect("Resources/Audio/Laser.AU"));
		sounds.put("Lose", new AllyAudioClipSoundEffect("Resources/Audio/Lose.AU"));
	}
	
	public static SoundEffectsManager getInstance() throws MalformedURLException{
		if(instance == null){
			instance = new SoundEffectsManager();
		}
		return instance;
	}

	@Override
	public void PlaySoundEffect(String soundName) {
		sounds.get(soundName).PlaySoundEffect();
		
	}
}
