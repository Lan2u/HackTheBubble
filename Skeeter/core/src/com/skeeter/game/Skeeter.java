package com.skeeter.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.awt.*;
import java.util.ArrayList;

public class Skeeter extends ApplicationAdapter implements InputProcessor{

    // Only public thing avaliable outside of this class
    // List of all sprites currently in play

	private static ArrayList<GameSprite> sprites = new ArrayList<GameSprite>();

    // Private not accessable outside of class
    private SpriteBatch batch;
    private Texture backTex;

    private Shooter shooter; // The shooter (includes their gun)

    private Point mouseAim = new Point(400,400); // Point the mouse is aiming at


	@Override
	// Called when the program starts (main)
	public void create () {
		batch = new SpriteBatch();

        AssetManager assetManager = new AssetManager();
        assetManager.load("/cs/home/pl59/HackTheBubble/Skeeter/core/assets/backgroundimage.png", Texture.class);
        assetManager.load("/cs/home/pl59/HackTheBubble/Skeeter/core/assets/gun.png", Texture.class);
        assetManager.load("/cs/home/pl59/HackTheBubble/Skeeter/core/assets/shooter.png", Texture.class);

        assetManager.finishLoading(); // Block until all assets finished loading, TODO replace with a loading screen
        // TODO TEXTURES FOR SHOOTER AND GUN
        Texture shooterTex = assetManager.get("/cs/home/pl59/HackTheBubble/Skeeter/core/assets/shooter.png", Texture.class);
        Texture gunTex = assetManager.get("/cs/home/pl59/HackTheBubble/Skeeter/core/assets/gun.png",Texture.class);
        backTex = assetManager.get("/cs/home/pl59/HackTheBubble/Skeeter/core/assets/backgroundimage.png", Texture.class);

        shooter = new Shooter(shooterTex,gunTex);

	}

	@Override
	// Called every frame
	public void render () {
		Gdx.gl.glClearColor(1, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Physics Update
        update(Gdx.graphics.getDeltaTime());

        // Drawing begins
		batch.begin();

        batch.draw(backTex,0,0); // Draw the background image

        shooter.draw(batch);

		for (GameSprite sprite: sprites){
		    sprite.draw(batch);
        }


		batch.end();
	}

	// Called every frame before drawing to be used to update the physics on all the sprites in the game
	private void update(float deltaT){ // Delta T is the time difference between this and the last frame in seconds
	    for (GameSprite sprite: sprites){ // Called each frame to allow the sprite to process the physics
	        sprite.update(deltaT);
        }
        shooter.update(deltaT,mouseAim);
    }
	
	@Override
	// Called at end of game
	public void dispose () {
		batch.dispose();
		backTex.dispose();

	}

	/* INPUT PROCESSING */

	// Fire the gun
	private void fire(){
	    shooter.gunFired();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE){
            fire();
        }
        return false;
    }
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mouseAim.x = screenX;
        mouseAim.y = screenY;
        return true;
    }

    // Not required but had to be added (other input handling that may or may not be used in future)
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
