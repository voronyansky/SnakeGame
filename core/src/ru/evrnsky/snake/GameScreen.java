package ru.evrnsky.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

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
     * It is texture of snake body
     */
    private Texture snakeBody;
    private Array<BodyPart> bodyParts = new Array<BodyPart>();
    private int snakeXBeforeUpdate = 0, snakeYBeforeUpdate = 0;
    /**
     * It is constant and variable for movement.
     * Using for calculate time
     */
    private static final float MOVE_TIME = 0.65f;
    private  float timer = MOVE_TIME;
    /**
     * It is constant for movement
     * User for calculate position
     */
    private static final int SNAKE_MOVEMENT = 32;

    /**
     * It is x coord of snake
     */
    public int snakeX = 0;
    /**
     * It is y coord of snake
     */
    public int snakeY = 0;

    /**
     * This is constants for directions of movement
     */
    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;
    private int snakeDirection = RIGHT;

    /**
     * Texture of apple
     */
    private Texture apple;
    /**
     * It is flag which means avaiable of apple
     */
    private boolean appleAvaiable = false;
    /**
     * Coords of apple
     */
    private int appleX = 0, appleY = 0;


    /**
     * At this method need init all variables and load textures
     * Call once time
     */
    @Override
    public void show() {
        batch = new SpriteBatch();
        snakeHead = new Texture(Gdx.files.internal("snakeHead.png"));
        apple = new Texture(Gdx.files.internal("apple.png"));
        snakeBody = new Texture(Gdx.files.internal("snakeBody.png"));
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
        handleInput();

        timer -= delta;
        if(timer <= 0) {
            timer = MOVE_TIME;
            snakeMovement();
            checkBoundaries();
            bodyPartUpdate();
        }
        checkAppleCollision();
        checkAndPlaceApple();


        /**
         * Clear screen
         */
        clearScreen();

        /**
         * Draw textures
         */
        draw();

    }

    /**
     * Clear screen before drawing
     */
    private void clearScreen() {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    /**
     * It is drawing section
     */

    private void draw() {
        batch.begin();
        batch.draw(snakeHead, snakeX, snakeY);
        if(appleAvaiable)
            batch.draw(apple, appleX, appleY);
        for(BodyPart bodyPart : bodyParts)
            bodyPart.draw(batch);
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
        snakeXBeforeUpdate = snakeX;
        snakeYBeforeUpdate = snakeY;
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

    private void bodyPartUpdate() {
        if(bodyParts.size > 0) {
            BodyPart part = bodyParts.removeIndex(0);
            part.setPosition(snakeXBeforeUpdate, snakeYBeforeUpdate);
            bodyParts.add(part);
        }
    }

    public void checkAndPlaceApple() {
        if(!appleAvaiable)
            do {
                appleX = MathUtils.random(Gdx.graphics.getWidth() / SNAKE_MOVEMENT - 1) * SNAKE_MOVEMENT;
                appleY = MathUtils.random(Gdx.graphics.getWidth() / SNAKE_MOVEMENT - 1) * SNAKE_MOVEMENT;
                appleAvaiable = true;
            }while (snakeX == appleX && snakeY == appleY);
    }

    /**
     * Check than we have collision
     */
    private void checkAppleCollision() {
        if(appleX == snakeX && appleY == snakeY) {
            BodyPart newPart = new BodyPart(snakeBody);
            newPart.setPosition(snakeX, snakeY);
            bodyParts.insert(0, newPart);
            appleAvaiable = false;
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

    /**
     * Inner class which determine a body part of snake
     */
    public class BodyPart {
        private Texture bodyTexture;
        private int x,y;

        public BodyPart(Texture texture) {
            this.bodyTexture = texture;
        }

        public void setPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void draw(SpriteBatch batch) {
            if(!(snakeX == x && snakeY == y))
                batch.draw(bodyTexture, x, y);
        }
    }
}


