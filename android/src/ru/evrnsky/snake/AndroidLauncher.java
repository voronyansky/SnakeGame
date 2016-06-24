package ru.evrnsky.snake;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import ru.evrnsky.snake.SnakeGame;

/**
 * Launcher of android version
 */
public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new SnakeGame(), config);
		config.useAccelerometer = false;
		config.useCompass = false;
	}
}
