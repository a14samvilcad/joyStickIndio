package m08.uf3.drops.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import m08.uf3.drops.helper.AssetManager;
import  m08.uf3.drops.Utils.Settings;


public class Player extends Actor {

    public static final int WALLET_STANDING = 0;
    public static final int WALLET_RIGHT = 1;
    public static final int WALLET_LEFT = 2;

    private Vector2 position;
    private int width, height;
    private int direction;

    private TextureRegion[] animacionRight;
    private TextureRegion[] animacionLeft;
    private TextureRegion[] animacionStatic;

    private Texture bulletTexture;

    private int currentFrame = 0;
    private float frameTime = 0.1f;
    private float stateTime = 0;

    private float timeSinceLastShot;
    private final float shotInterval = 0.5f; // intervalo de tiempo en segundos entre cada disparo

    private Rectangle collisionRect;

    //Mapita manager
    private TiledMapTileLayer mapTileLayer;
    private MapProperties propertiesMapa;

    public Player(float x, float y, int width, int height, TiledMapTileLayer mapTileLayer, MapProperties propertiesMapa){
        this.width = width;
        this.height = height;
        this.mapTileLayer = mapTileLayer;
        this.propertiesMapa = propertiesMapa;
        position = new Vector2(x, y);

        direction = WALLET_STANDING;

        bulletTexture = AssetManager.Bala;


        collisionRect = new Rectangle();
        collisionRect.x = x;
        collisionRect.y = y;
        collisionRect.width = this.width;
        collisionRect.height = this.height;

        animacionRight = AssetManager.playerRightAnimation;
        animacionLeft = AssetManager.playerLeftAnimation;
        animacionStatic = AssetManager.playerStaticAnimation;




        setBounds(position.x, position.y, width, height);
        setTouchable(Touchable.enabled);
    }

    public void act(float delta){
        super.act(delta);
        timeSinceLastShot += delta;
        //Cambios SAMU
        float oldX = this.position.x;
        float oldY = this.position.y;

            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)){
                this.position.x -= Settings.PLAYER_VELOCITY * Gdx.graphics.getDeltaTime();
                if(buscaColision(mapTileLayer, propertiesMapa.get("tilewidth", Integer.class), propertiesMapa.get("tileheight", Integer.class))) {
                    this.position.x = oldX;
                    this.position.y = oldY;
                }


            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)){
                this.position.x += Settings.PLAYER_VELOCITY * Gdx.graphics.getDeltaTime();
                if(buscaColision(mapTileLayer, propertiesMapa.get("tilewidth", Integer.class), propertiesMapa.get("tileheight", Integer.class))) {
                    this.position.x = oldX;
                    this.position.y = oldY;
                }
            }

            if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)){
                this.position.y += Settings.PLAYER_VELOCITY * Gdx.graphics.getDeltaTime();
                if(buscaColision(mapTileLayer, propertiesMapa.get("tilewidth", Integer.class), propertiesMapa.get("tileheight", Integer.class))) {
                    this.position.x = oldX;
                    this.position.y = oldY;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)){
                this.position.y -= Settings.PLAYER_VELOCITY * Gdx.graphics.getDeltaTime();
                if(buscaColision(mapTileLayer, propertiesMapa.get("tilewidth", Integer.class), propertiesMapa.get("tileheight", Integer.class))) {
                    this.position.x = oldX;
                    this.position.y = oldY;
                }
            }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            shoot();
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

    private boolean buscaColision(TiledMapTileLayer mapLayer, int tileWidth, int tileHeight) {
        int tileX = (int) ((this.position.x + (width / 2)) / tileWidth);
        int tileY = (int) ((this.position.y + (height / 2)) / tileHeight);

        return mapLayer.getCell(tileX, tileY)
                .getTile().getProperties().containsKey("blocked");
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

    public void shoot() {
        if (timeSinceLastShot >= shotInterval) {
            timeSinceLastShot = 0;
            float bulletXVelocity = Settings.BULLET_VELOCITY;
            float bulletYVelocity = 0;

            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
                bulletXVelocity = -Settings.BULLET_VELOCITY;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
                bulletXVelocity = 0;
                bulletYVelocity = Settings.BULLET_VELOCITY;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
                bulletXVelocity = 0;
                bulletYVelocity = -Settings.BULLET_VELOCITY;
            }

            Bullet bullet = new Bullet(position.x + width/2, position.y + 28, bulletXVelocity, bulletYVelocity, bulletTexture);
            getParent().addActor(bullet);
        }
    }
    public class Bullet extends Actor {
        private Texture texture;
        private Vector2 position;
        private float xVelocity;
        private float yVelocity;

        public Bullet(float x, float y, float xVelocity, float yVelocity, Texture texture) {
            this.position = new Vector2(x, y);
            this.xVelocity = xVelocity;
            this.yVelocity = yVelocity;
            this.texture = texture;
        }

        @Override
        public void act(float delta) {
            super.act(delta);
            position.x += xVelocity * delta;
            position.y += yVelocity * delta;
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            super.draw(batch, parentAlpha);
            batch.draw(texture, position.x, position.y);
        }
    }


    private TextureRegion getPLayerDirection() {
        TextureRegion playerDir = null;

        playerDir = animacionStatic[currentFrame];

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)){
            playerDir = animacionLeft[currentFrame];
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)){
            playerDir = animacionRight[currentFrame];
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.D)){
            playerDir = animacionRight[currentFrame];
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.D)){
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
