package com.skeeter.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Skeeter extends ApplicationAdapter implements InputProcessor{

    // Only public thing avaliable outside of this class
    // List of all sprites currently in play
	public static ArrayList<GameSprite> sprites = new ArrayList<GameSprite>();

    // Private not accessable outside of class
    private SpriteBatch batch;
    private Texture backImg;

	@Override
	// Called when the program starts (main)
	public void create () {
		batch = new SpriteBatch();
	}

	@Override
	// Called every frame
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Physics Update
        update();

        // Draw
		batch.begin();

        batch.draw(backImg,0,0); // Draw the background image

		for (GameSprite sprite: sprites){
		    sprite.draw(batch);
        }
		batch.end();
	}

	private void update(){
	    for (GameSprite sprite: sprites){ // Called each frame to allow the sprite to process the physics
	        sprite.update();
        }
    }
	
	@Override
	// Called at end of game
	public void dispose () {
		batch.dispose();
		backImg.dispose();
	}

	/* INPUT PROCESSING */

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    // Not required but had to be added
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
