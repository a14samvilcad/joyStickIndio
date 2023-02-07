package m08.uf3.drops;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import m08.uf3.drops.Screens.MainMenuScreen;
import m08.uf3.drops.Utils.JoyStick;
import m08.uf3.drops.helper.AssetManager;

public class Drops extends Game {

	public void create() {
		AssetManager.load();
		setScreen(new MainMenuScreen(this));
	}

	public void render() {
		super.render(); // important!
	}

	public void dispose() {
		super.dispose();
		AssetManager.dispose();
	}

}