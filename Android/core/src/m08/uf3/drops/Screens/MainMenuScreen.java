package m08.uf3.drops.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.Set;

import m08.uf3.drops.Drops;
import m08.uf3.drops.Utils.Settings;

public class MainMenuScreen implements Screen {

    GameScreen gameScreen;
    OrthographicCamera camera;

    final Drops game;
    private Stage stage;
    TextButton button;
    Label title, message;

    //para background
    private SpriteBatch batch;
    private Texture backgroundTexture;
    private Sprite backgroundSprite;


    public MainMenuScreen(Drops game) {
        this.game = game;


        // Creem la càmera de les dimensions del joc
        camera = new OrthographicCamera(Settings.GAME_WIDTH, Settings.GAME_HEIGHT);
        camera.setToOrtho(false);

        // Creem el viewport amb les mateixes dimensions que la càmera
        StretchViewport viewport = new StretchViewport(Settings.GAME_WIDTH, Settings.GAME_HEIGHT, camera);

        // Creem l'stage i assginem el viewport
        //stage = new Stage(viewport);
        stage = new Stage(viewport);
        gameScreen = new GameScreen(stage.getBatch(), stage.getViewport(), game);

        //mySkin = new Skin(Gdx.files.internal(Settings.skin));


        crearLabels();
        //stage.addActor(title);
        //stage.addActor(message);
        stage.addActor(button);
    }

    private void crearLabels(){
        Gdx.input.setInputProcessor(stage);
        BitmapFont font = new BitmapFont();

        title = new Label("PLAY", new Label.LabelStyle(font, Color.WHITE));
        title.setFontScale(2, 2);

        /*
        title.setPosition((Settings.GAME_WIDTH - (title.getWidth() * Settings.TITLE_RESCALE_SIZE)) / 2, ((Settings.GAME_HEIGHT - title.getHeight()) / 2) + 50);
        message = new Label("Pulsa en la pantalla para empezar", new Label.LabelStyle(font, Color.WHITE));
        message.setPosition((Settings.GAME_WIDTH - (message.getWidth() * Settings.TITLE_RESCALE_SIZE)) / 2, (Settings.GAME_HEIGHT - message.getHeight()) / 2);*/

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.fontColor = Color.GOLD;

        button = new TextButton("", textButtonStyle);
        button.setLabel(title);
        button.setColor(Color.BLACK);
        button.setPosition((Settings.GAME_WIDTH - (button.getWidth() * Settings.TITLE_RESCALE_SIZE)) / 2, (Settings.GAME_HEIGHT - button.getHeight()) / 1.2f);


    }

    @Override
    public void show() {
        //Pulsar boton PLAY
        if (button.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(gameScreen);
                dispose();
            }
        }));

        //cosas fondo
        batch = new SpriteBatch();
        backgroundTexture = new Texture(Gdx.files.internal("background.jpg"));
        backgroundSprite = new Sprite(backgroundTexture);

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float aspectRatio = screenWidth / screenHeight;
        float textureWidth = backgroundTexture.getWidth();
        float textureHeight = backgroundTexture.getHeight();
        float textureAspectRatio = textureWidth / textureHeight;

        if (textureAspectRatio > aspectRatio) {
            float scale = screenHeight / textureHeight;
            float width = textureWidth * scale;
            float height = textureHeight * scale;
            float x = (screenWidth - width) / 100;
            float y = 0;
            backgroundSprite.setBounds(x, y, width, height);
        } else {
            float scale = screenWidth / textureWidth;
            float width = textureWidth * scale;
            float height = textureHeight * scale;
            float x = 0;
            float y = (screenHeight - height) / 100;
            backgroundSprite.setBounds(x, y, width, height);
        }


    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        //PINTAR FONDO
        batch.begin();
        backgroundSprite.draw(batch);
        batch.end();

        //PINTAR STAGE
        stage.draw();
        stage.act(delta);



        // Si es fa clic en la pantalla, canviem la pantalla
        /*if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(stage.getBatch(), stage.getViewport(), game));
            dispose();
        }*/
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }


    //...Rest of class omitted for succinctness.

}