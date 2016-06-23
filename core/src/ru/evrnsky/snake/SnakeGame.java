package ru.evrnsky.snake;

import com.badlogic.gdx.Game;

/**
 *  SnakeGame class is extends from Game class and have all methods from Game class
 *  @author: Egor Voronyansky
 *  @since: 23.06.2016
 */
public class SnakeGame extends Game {


	@Override
	public void render() {
		super.render();
	}

	@Override
	public void create() {
		super.setScreen(new GameScreen());
	}
}
