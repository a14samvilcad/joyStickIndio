package m08.uf3.drops.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetManager {

    //Map
    public static TiledMap map;
    public static TmxMapLoader mapLoader;
    public static Pixmap cursorZombie;

    public static Texture soldierImage;
    public static Sound dropSound;

    //Textures
    public static Texture PlayerSoldierRunRight;
    public static Texture PlayerSoldierRunLeft;
    public static Texture PlayerSoldierStatic;
    public static Texture Bala;
    public static Texture ZombieGhoulStatic;






    //Images
    public static TextureRegion playerLeft, playerRight, playerStatic;
    public static TextureRegion ZombieGhoulStaticRegion;


    public static TextureRegion[] playerRightAnimation, playerLeftAnimation, playerStaticAnimation;
    public static TextureRegion[] ZombieGhoulStaticAnimation;

    // Font
    public static BitmapFont font;

    //Load textures
    public static void load() {

        ZombieGhoulStatic = new Texture(Gdx.files.internal("soldier.png"));

        //Load Map
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Maps/map.tmx");

        Bala = new Texture(Gdx.files.internal("playerAnimation/Bullets/defaultBullet.png"));

        cursorZombie = new Pixmap(Gdx.files.internal("cursor.png"));
        Pixmap smallCursor = new Pixmap(cursorZombie.getWidth()/2, cursorZombie.getHeight()/2, cursorZombie.getFormat());
        smallCursor.drawPixmap(cursorZombie, 0, 0, cursorZombie.getWidth(), cursorZombie.getHeight(), 0, 0, smallCursor.getWidth(), smallCursor.getHeight());
        cursorZombie = smallCursor;

        ZombieGhoulStatic = new Texture(Gdx.files.internal("Zombies/GhoulZombie.png"));
        ZombieGhoulStatic.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        //Jugador Default
        PlayerSoldierStatic = new Texture(Gdx.files.internal("playerAnimation/Red/Gunner_Red_Idle.png"));
        PlayerSoldierStatic.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        PlayerSoldierRunRight = new Texture(Gdx.files.internal("playerAnimation/Red/Gunner_Red_Run.png"));
        PlayerSoldierRunRight.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        PlayerSoldierRunLeft = new Texture(Gdx.files.internal("playerAnimation/Red/Gunner_Red_Run_Left.png"));
        PlayerSoldierRunLeft.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);


        ZombieGhoulStaticRegion = new TextureRegion(ZombieGhoulStatic, 0, 3, 32, 32);
        ZombieGhoulStaticRegion.flip(false, false);

        //Jugador estatico
        playerStatic = new TextureRegion(PlayerSoldierStatic, 0, 0, 48, 48);
        playerStatic.flip(false, false);


        playerLeft = new TextureRegion(PlayerSoldierRunLeft, 0, 0, 48, 48);
        playerLeft.flip(false, false);

        playerRight = new TextureRegion(PlayerSoldierRunRight, 0, 0, 48, 48);
        playerRight.flip(false, false);

        //Animacion ZombieGhoul
        ZombieGhoulStaticAnimation = new TextureRegion[4];
        for (int i = 0; i < ZombieGhoulStaticAnimation.length; i++) {
            ZombieGhoulStaticAnimation[i] = new TextureRegion(ZombieGhoulStaticRegion, i * 32, 3, 32, 32);
        }

        //Animación estatica
        playerStaticAnimation = new TextureRegion[6];
        for (int i = 0; i < playerStaticAnimation.length; i++) {
            playerStaticAnimation[i] = new TextureRegion(PlayerSoldierStatic, i * 48, 0, 48, 48);
        }


        //Correr derecha
        playerRightAnimation = new TextureRegion[6];
        for (int i = 0; i < playerRightAnimation.length; i++) {
            playerRightAnimation[i] = new TextureRegion(PlayerSoldierRunRight, i * 48, 0, 48, 48);
        }

        playerLeftAnimation = new TextureRegion[6];
        for (int i = 0; i < playerLeftAnimation.length; i++) {
            playerLeftAnimation[i] = new TextureRegion(PlayerSoldierRunLeft, i * 48, 0, 48, 48);
        }

    }


    public static void dispose() {
        soldierImage.dispose();
        dropSound.dispose();
        cursorZombie.dispose();

    }
}
