package ru.evrnsky.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
     * This is constants for directions of movement
     */
    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;
    private int snakeDirection = RIGHT;


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
        checkBoundaries();
        handleInput();

        timer -= delta;
        if(timer <= 0) {
            timer = MOVE_TIME;
            snakeMovement();
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
     * Checking that our snake do not exit from boundaries
     */
    private void checkBoundaries() {
        if(snakeX + snakeHead.getWidth() > Gdx.graphics.getWidth())
            snakeX = Gdx.graphics.getWidth() - snakeHead.getWidth();
        else if(snakeX < 0)
            snakeX = 0;
        if(snakeY + snakeHead.getHeight() > Gdx.graphics.getHeight())
            snakeY = Gdx.graphics.getHeight() - snakeHead.getHeight();
        else if(snakeY < 0)
                snakeY = 0;
    }

    /**
     * Handling input from user by choosing correct direct for snake
     */
    private void handleInput() {
        boolean isRight = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean isLeft = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean isUp = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean isDown = Gdx.input.isKeyPressed(Input.Keys.DOWN);

        if(isRight) snakeDirection = RIGHT;
        if(isLeft) snakeDirection = LEFT;
        if(isUp) snakeDirection = UP;
        if(isDown) snakeDirection = DOWN;
    }

    /**
     * Is it update snake position depends on direct
     */
    private void snakeMovement() {
        switch(snakeDirection) {
            case RIGHT:
                snakeX += SNAKE_MOVEMENT;
                break;
            case LEFT:
                snakeX -= SNAKE_MOVEMENT;
                break;
            case UP:
                snakeY += SNAKE_MOVEMENT;
                break;
            case DOWN:
                snakeY -= SNAKE_MOVEMENT;
        }
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


