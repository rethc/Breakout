package com.chesda.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.ArrayList;

public class Main extends Game {
    private ShapeRenderer shape;
    private Ball ball;
    private Paddle paddle;
    private ArrayList<Block> blocks = new ArrayList<>();
    int blockWidth = 55;
    int blockHeight = 20;

    //Tiled test
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private ArrayList blockTiled;

    public void changeScreen(Screen newScreen){
        Screen oldScreen = getScreen();
        setScreen(newScreen);
        //Dispose the old screen to release resources
        if(oldScreen != null)
            oldScreen.dispose();
    }



    @Override
    public void create() {
        shape = new ShapeRenderer();
        ball = new Ball(100, 100, 10, 20);
        paddle = new Paddle(50, 10, 150, 20, 30);

        /*
        for (int y = Gdx.graphics.getHeight() / 2; y < Gdx.graphics.getHeight(); y += blockHeight + 10) {
            for (int x = 0; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
                blocks.add(new Block(x, y, blockWidth, blockHeight));
            }
        }*/

        blockTiled = new ArrayList<TiledMapTileLayer.Cell>();

        // Camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.translate(0,90);
        camera.update();
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("world.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);

        //render the current screen
        super.render();

    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

       ball.update();
        paddle.update();
        ball.checkCollision(paddle);

        shape.begin(ShapeRenderer.ShapeType.Filled);
        ball.draw(shape);
        paddle.draw(shape);



        for (Block block : blocks) {
            block.draw(shape);
            ball.checkCollision(block);
        }

        for (int i = 0; i < blocks.size(); i++) {
            Block b = blocks.get(i);
            if (b.destroyed) {
                blocks.remove(b);
                i--;
            }
        }
        shape.end();

        camera.update();
        renderer.setView(camera);
        renderer.render();
    }

    @Override
    public void dispose() {
        shape.dispose();
    }
}
