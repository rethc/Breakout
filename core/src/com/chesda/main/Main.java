package com.chesda.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Main extends ApplicationAdapter {
	ShapeRenderer shape;
	int x = 50;
	int y = 50;
	int xSpeed = 5;
	
	@Override
	public void create () {
		shape = new ShapeRenderer();
	}

	@Override
	public void render () {
		x += xSpeed;
		if (x > Gdx.graphics.getWidth()) {
			xSpeed = -5;
		}
		if (x < 0) {
			xSpeed = 5;
		}

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shape.begin(ShapeRenderer.ShapeType.Filled);
		shape.circle(x, y, 50);
		shape.end();
	}
	
	@Override
	public void dispose () {

	}
}
