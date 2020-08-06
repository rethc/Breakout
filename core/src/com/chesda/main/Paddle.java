package com.chesda.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle {

    private int x, y, width, height, leftLimit, rightLimit, topLimit, bottomLimit, movementSpeed;

    public Paddle(int x, int y, int width, int height, int movementSpeed){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.movementSpeed = movementSpeed;
        updateLimits();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLeftLimit() {
        return leftLimit;
    }

    public int getRightLimit() {
        return rightLimit;
    }

    public int getTopLimit() {
        return topLimit;
    }

    public int getBottomLimit() {
        return bottomLimit;
    }

    public void updateLimits(){
        this.leftLimit = x;
        this.rightLimit = x + this.width;
        this.bottomLimit = y;
        this.topLimit = y + this.height;
    }

    public void draw(ShapeRenderer shape){
        shape.rect(x, y, width, height);
    }

    public void update(){
        // Test Paddle Functionaility this.y = (Gdx.graphics.getHeight() - (Gdx.input.getY() + this.height / 2));
        //this.x = (Gdx.input.getX() - this.width / 2);

        if (Math.abs(Gdx.input.getX() - this.x) > 30 ) {
            // margin where we don't move the paddle when the cursor is close enough, make sure MARGIN > movementSpeed
            if (this.x < Gdx.input.getX()) {
                this.x += movementSpeed;
            }
            else {
                this.x -= movementSpeed;
            }
        }
        updateLimits();
    }
}
