package ru.evrnsky.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *  It is main game screen
 *  @author: Egor Voronyansky
 *  @since: 23.06.2016
 */
public class GameScreen extends ScreenAdapter {

    /**
     * This object uses for drawing textures
     */
    private SpriteBatch batch;
    /**
     * It is texture of snake head
     */
    private Texture snakeHead;
    /**
     * It is constant and variable for movement.
     * Using for calculate time
     */
    private static final float MOVE_TIME = 1f;
    private  float timer = MOVE_TIME;
    /**
     * It is constant for movement
     * User for calculate position
     */
    private static final int SNAKE_MOVEMENT = 32;

    /**
     * It is x coord of snake
     */
    private int snakeX = 0;
    /**
     * It is y coord of snake
     */
    private int snakeY = 0;


    /**
     * At this method need init all variables and load textures
     * Call once time
     */
    @Override
    public void show() {
        batch = new SpriteBatch();
        snakeHead = new Texture(Gdx.files.internal("snakeHead.png"));
    }

    /**
     * This method call 60 times per second
     * @param delta - it is time between frames
     */
    @Override
    public void render(float delta) {

        /**
         * Calculate may snake movement
         * If spend more than one second update timer and move snake
         */
        timer -= delta;
        if(timer <= 0) {
            timer = MOVE_TIME;
            snakeX += SNAKE_MOVEMENT;
        }

        /**
         * Clear screen before drawing
         */
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /**
         * It is drawing section
         */
        batch.begin();
        batch.draw(snakeHead, snakeX, snakeY);
        batch.end();
    }

    /**
     * This method calls when screen chande size
     * @param width - new wight of screen
     * @param height - new height of screen
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     * This method call when user return to back or minimize screen
     */
    @Override
    public void hide() {

    }

    /**
     * This method call when user want to stop game
     */
    @Override
    public void pause() {

    }

    /**
     * This method call after pause. It launch game
     */
    @Override
    public void resume() {

    }

    /**
     * Clear resources because phone and table are not power
     */
    @Override
    public void dispose() {
        batch.dispose();
        snakeHead.dispose();
    }
}


