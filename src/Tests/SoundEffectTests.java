package Tests;

import static org.junit.Assert.*;

import java.net.MalformedURLException;

import soundeffects.SoundEffectsManager;
import org.junit.Test;

public class SoundEffectTests {

	@Test
	public void TestWinSoundEffect() {
		try {
			SoundEffectsManager manager = SoundEffectsManager.getInstance();
			manager.PlaySoundEffect("Win");
			Thread.sleep(2000);
		} catch (MalformedURLException | InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void TestLoseSoundEffect() {
		try {
			SoundEffectsManager manager = SoundEffectsManager.getInstance();
			manager.PlaySoundEffect("Lose");
			Thread.sleep(2000);
		} catch (MalformedURLException | InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void TestLaserSoundEffect() {
		try {
			SoundEffectsManager manager = SoundEffectsManager.getInstance();
			manager.PlaySoundEffect("Laser");
			Thread.sleep(2000);
		} catch (MalformedURLException | InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void TestAllySoundEffect() {
		try {
			SoundEffectsManager manager = SoundEffectsManager.getInstance();
			manager.PlaySoundEffect("Ally");
			Thread.sleep(2000);
		} catch (MalformedURLException | InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void TestEnemySoundEffect() {
		try {
			SoundEffectsManager manager = SoundEffectsManager.getInstance();
			manager.PlaySoundEffect("Enemy");
			Thread.sleep(2000);
		} catch (MalformedURLException | InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

}
