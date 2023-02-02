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

    // Font
    public static BitmapFont font;

    //Load textures
    public static void load() {

        //Load Map
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Maps/map.tmx");

        //Load skin DOESN'T WORK
        //skin = new Skin(Gdx.files.internal("skin/neon-ui.json"));


        // load the images for the droplet and the bucket, 64x64 pixels each
        soldierImage = new Texture(Gdx.files.internal("soldier.png"));



        // load the drop sound effect and the rain background "music"
        dropSound = Gdx.audio.newSound(Gdx.files.internal("augh.mp3"));


    }


    public static void dispose() {
        soldierImage.dispose();
        dropSound.dispose();

    }
}
