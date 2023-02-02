package m08.uf3.drops.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import m08.uf3.drops.helper.AssetManager;
import m08.uf3.drops.Screens.GameScreen;
import  m08.uf3.drops.Utils.Settings;


public class Bucket extends Actor {

    public static final int WALLET_STANDING = 0;
    public static final int WALLET_RIGHT = 1;
    public static final int WALLET_LEFT = 2;

    private Vector2 position;
    private int width, height;
    private int direction;

    private TextureRegion[] animacionRight;
    private int currentFrame = 0;
    private float frameTime = 0.1f;
    private float stateTime = 0;

    private Rectangle collisionRect;

    public Bucket(float x, float y, int width, int height){
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);

        direction = WALLET_STANDING;

        collisionRect = new Rectangle();
        collisionRect.x = x;
        collisionRect.y = y;
        collisionRect.width = this.width;
        collisionRect.height = this.height;

        animacionRight = AssetManager.playerRightAnimation;

        setBounds(position.x, position.y, width, height);
        setTouchable(Touchable.enabled);
    }

    public void act(float delta){
        super.act(delta);
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)){
                this.position.x -= Settings.PLAYER_VELOCITY * Gdx.graphics.getDeltaTime();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)){
                this.position.x += Settings.PLAYER_VELOCITY * Gdx.graphics.getDeltaTime();
            }

            if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)){
                this.position.y += Settings.PLAYER_VELOCITY * Gdx.graphics.getDeltaTime();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)){
                this.position.y -= Settings.PLAYER_VELOCITY * Gdx.graphics.getDeltaTime();
            }

            //Colision personaje con los bordes del mapa
            if (this.position.y <= 5){
                this.position.y = 5;
            }
            if (this.position.x <= 5){
                this.position.x = 5;
            }
            if (this.position.x >= 3840 - this.width - 5){
                this.position.x = 3840 - this.width - 5;
            }
            if (this.position.y >= 2160 - this.height - 2){
                this.position.y = 2160 - this.height- 2;
            }

            collisionRect.x = this.position.x;
            collisionRect.y = this.position.y;
            collisionRect.width = this.width;
            collisionRect.height = this.height;

        stateTime += delta;
        if (stateTime >= frameTime){
            currentFrame++;
            if (currentFrame >= animacionRight.length){
                currentFrame = 0;
            }
            stateTime = 0;
        }

    }

    // Canviem la wallet de la spacecraft: Puja
    public void goRight() {
        direction = WALLET_RIGHT;
    }

    // Canviem la wallet de la spacecraft: Baixa
    public void goLeft() {
        direction = WALLET_LEFT;
    }

    // Posem la wallet al seu estat original
    public void goStraight() {
        direction = WALLET_STANDING;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(getPLayerDirection(), this.position.x, this.position.y, width, height);
    }

    private TextureRegion getPLayerDirection() {
        TextureRegion playerDir = null;

        playerDir = AssetManager.playerDown;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)){
            playerDir = animacionRight[currentFrame];
        }
        return playerDir;
    }

    // Getters dels atributs principals
    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Rectangle getCollisionRectBucket() {
        return collisionRect;
    }

}
