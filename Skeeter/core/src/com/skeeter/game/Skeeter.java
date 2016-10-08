package com.skeeter.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.skeeter.birds.Bird;
import com.skeeter.birds.Birdy;

import java.awt.*;
import java.util.ArrayList;

/* Paul Lancaster + libGdx Gen
*/
public class Skeeter extends ApplicationAdapter implements InputProcessor{

    // Only public thing avaliable outside of this class
    // List of all sprites currently in play

	public static ArrayList<Bird> sprites = new ArrayList<Bird>();


    // Private not accessable outside of class
    private SpriteBatch batch;
    private Texture backTex;
    private Texture menuTex;
    private Rectangle playButton;

    private Shooter shooter; // The shooter (includes their gun)

    private Point mouseAim = new Point(400,400); // Point the mouse is aiming at

    private static int gameState = 0; // Game state of game, 0 = start menu, 1= in game, 2 = game over

    public static int score = 0;

    private BitmapFont font;

    public static double acc;

	@Override
	// Called when the program starts (main)
	public void create () {
        batch = new SpriteBatch();

        font = new BitmapFont();
        font.setColor(Color.RED);

        playButton = new Rectangle(0,0,400,300);

        String bckPath = "backImg.png";
        String gunPath = "gun.png";
        String guyPath = "shooter.png";
        String gunFiredPath = "gunFiring.png";
        String phesantPath = "pheasantup.png";
        String menuPath = "menubackground.png";

        AssetManager assetManager = new AssetManager();
        assetManager.load(bckPath, Texture.class);
        assetManager.load(gunPath, Texture.class);
        assetManager.load(guyPath, Texture.class);
        assetManager.load(gunFiredPath, Texture.class);
        assetManager.load(phesantPath, Texture.class);
        assetManager.load(menuPath,Texture.class);

        assetManager.finishLoading(); // Block until all assets finished loading, TODO replace with a loading screen
        // TODO TEXTURES FOR SHOOTER AND GUN
        Texture shooterTex = assetManager.get(guyPath, Texture.class);
        Texture gunTex = assetManager.get(gunPath,Texture.class);
        Texture gunFiredTex = assetManager.get(gunFiredPath, Texture.class);
        Texture pTex = assetManager.get(phesantPath, Texture.class);
        menuTex = assetManager.get(menuPath, Texture.class);
        backTex = assetManager.get(bckPath, Texture.class);

        shooter = new Shooter(shooterTex,gunTex, gunFiredTex);

        Birdy pt = new Birdy(pTex, 700, 300, 60, 30);
        Birdy pt2 = new Birdy(pTex, 800, 300, 60, 30);
        Birdy pt3 = new Birdy(pTex, 900, 300, 60, 30);
        Birdy pt4 = new Birdy(pTex, 1100, 300, 60, 30);
        Birdy pt5 = new Birdy(pTex, 2000, 300, 60, 30);
        //PheasantOnSteroids ptS = new PheasantOnSteroids(pTex, 700, 300, 60,30);
        sprites.add(pt);
        sprites.add(pt2);
        sprites.add(pt3);
        sprites.add(pt4);
        sprites.add(pt5);

        //sprites.add(ptS);
        //http://gamedev.stackexchange.com/questions/63326/libgdx-inputlistenner-not-working
        Gdx.input.setInputProcessor(this);

        acc = 0.6;
	}

	@Override
	// Called every frame
	public void render () {
		Gdx.gl.glClearColor(1, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        switch(gameState) {
            case 0: {
                renderMenu(batch);
                break;
            }
            case 1: {
                renderGame(batch);
                break;
            }
            case 2: {
                renderEnd(batch);
                break;
            }
            default:
                System.out.println("Invalid game state " +gameState);
        }
	}
	private void renderMenu(Batch batch){
	    batch.begin();
        batch.draw(menuTex,0,0);
        batch.end();
    }

	private void renderGame(Batch batch){
        // Physics Update
        update(Gdx.graphics.getDeltaTime());
        // Drawing begins
        batch.begin();
        batch.draw(backTex, 0, 0); // Draw the background image
        shooter.draw(batch);
        for (Bird sprite : sprites) {
            sprite.draw(batch);
        }

        font.draw(batch, String.valueOf(score),600, 100);
        batch.end();
    }

    private void renderEnd(Batch batch){
        batch.begin();
        batch.draw(backTex,0,0);
        font.draw(batch, " Your score was " + score + " :D , click to restart", 300,200);
        batch.end();
    }

	// Called every frame before drawing to be used to update the physics on all the sprites in the game
	private void update(float deltaT){ // Delta T is the time difference between this and the last frame in seconds
	    for (Bird sprite: sprites){ // Called each frame to allow the sprite to process the physics
	        sprite.update(deltaT);
        }
        shooter.update(deltaT,mouseAim);
    }
	
	@Override
	// Called at end of game
	public void dispose () {
		batch.dispose();
		backTex.dispose();
        menuTex.dispose();
	}

	/* INPUT PROCESSING */

	// Fire the gun
	private void fire(){
	    shooter.gunFired();
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
        if (button == Input.Buttons.LEFT && gameState == 0){
            System.out.println("Pressed");
            if (playButton.contains(screenX,screenY)){
                gameState = 1;
            }
        }
        if (button == Input.Buttons.LEFT ){
            if (gameState == 1) {
                fire();
            }
            if (gameState == 2){
                for (Bird bird: sprites){
                    bird.kill();
                    Skeeter.acc = 0;
                    Skeeter.score = 0;
                }
                gameState = 0;
            }
        }

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

    public static void gameOver() {
        System.out.println("Game Over");
        gameState = 2;
    }
}
