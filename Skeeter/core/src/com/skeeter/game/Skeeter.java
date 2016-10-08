package com.skeeter.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;
import java.util.ArrayList;

public class Skeeter extends ApplicationAdapter implements InputProcessor{

    // Only public thing avaliable outside of this class
    // List of all sprites currently in play

	static ArrayList<GameSprite> sprites = new ArrayList<GameSprite>();

    // Private not accessable outside of class
    private SpriteBatch batch;
    private Texture backImg;

    private Shooter shooter; // The shooter (includes their gun)

    private Point mouseAim = new Point(400,400); // Point the mouse is aiming at


	@Override
	// Called when the program starts (main)
	public void create () {
		batch = new SpriteBatch();

        // TODO TEXTURES FOR SHOOTER AND GUN
        Texture shooterTex = new Texture("");
        Texture gunTex = new Texture("");
        shooter = new Shooter(shooterTex,gunTex);
        sprites.add(shooter);
	}

	@Override
	// Called every frame
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Physics Update
        update(Gdx.graphics.getDeltaTime());

        // Drawing begins
		batch.begin();

        batch.draw(backImg,0,0); // Draw the background image

		for (GameSprite sprite: sprites){
		    sprite.draw(batch);
        }
		batch.end();
	}

	// Called every frame before drawing to be used to update the physics on all the sprites in the game
	private void update(float deltaT){
	    for (GameSprite sprite: sprites){ // Called each frame to allow the sprite to process the physics
	        sprite.update(deltaT);
        }
    }
	
	@Override
	// Called at end of game
	public void dispose () {
		batch.dispose();
		backImg.dispose();
	}

	/* INPUT PROCESSING */

	// Fire the gun
	private void fire(){

	    // Call the fire method from the physics class providing it with the angle of the gun in radians
    }

    @Override
    public boolean keyDown(int keycode) {
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
