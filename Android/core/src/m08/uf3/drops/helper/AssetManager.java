package m08.uf3.drops.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
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

    //Skin
    public static Skin skin;

    //ImageButton play
    ImageButton buttonPlay;

    public static Texture soldierImage;
    public static Sound dropSound;

    //Textures
    public static Texture PlayerSoldierRunRight;
    public static Texture PlayerSoldierRunLeft;
    public static Texture PlayerSoldierStatic;



    //Images
    public static TextureRegion playerUp, playerDown, playerLeft, playerRight, playerStatic;

    public static TextureRegion[] playerRightAnimation, playerLeftAnimation, playerUpAnimation, playerDownAnimation, playerStaticAnimation;

    // Font
    public static BitmapFont font;

    //Load textures
    public static void load() {

        //Load Map
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Maps/map.tmx");

        //Jugador Default
        PlayerSoldierStatic = new Texture(Gdx.files.internal("playerAnimation/Red/Gunner_Red_Idle.png"));
        PlayerSoldierStatic.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        PlayerSoldierRunRight = new Texture(Gdx.files.internal("playerAnimation/Red/Gunner_Red_Run.png"));
        PlayerSoldierRunRight.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        PlayerSoldierRunLeft = new Texture(Gdx.files.internal("playerAnimation/Red/Gunner_Red_Run_Left.png"));
        PlayerSoldierRunLeft.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        //Jugador estatico
        playerStatic = new TextureRegion(PlayerSoldierStatic, 0, 0, 48, 48);
        playerStatic.flip(false, false);

        playerDown = new TextureRegion(PlayerSoldierRunRight, 0, 0, 48, 48);
        playerDown.flip(false, false);

        playerUp = new TextureRegion(PlayerSoldierRunRight, 0, 0, 48, 48);
        playerUp.flip(false, false);

        playerLeft = new TextureRegion(PlayerSoldierRunLeft, 0, 0, 48, 48);
        playerLeft.flip(false, false);

        playerRight = new TextureRegion(PlayerSoldierRunRight, 0, 0, 48, 48);
        playerRight.flip(false, false);


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

    }
}
