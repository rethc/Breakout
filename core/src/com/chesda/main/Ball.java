package com.chesda.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;

public class Ball {
    private int x, y, size, xSpeed, ySpeed, leftLimit, rightLimit, topLimit, bottomLimit;
    Color color = Color.WHITE;

    public Ball(int x, int y, int size, int speed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = this.ySpeed = speed;
        updateLimits();
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public int getySpeed() { return ySpeed; }

    public void addxSpeed() {
        this.x += this.getxSpeed();
        updateLimits();
    }

    public void addySpeed() {
        this.y += this.getySpeed();
        updateLimits();
    }

    public void updateLimits() {
        this.leftLimit = this.x - this.size;
        this.rightLimit = this.x + this.size;
        this.topLimit = this.y + this.size;
        this.bottomLimit = this.y - this.size;
    }

    public void update() {
        this.addxSpeed();
        this.addySpeed();
        if (this.rightLimit > Gdx.graphics.getWidth() || this.leftLimit < 0) {
            this.xSpeed = -this.xSpeed;
        }
        if (this.topLimit > Gdx.graphics.getHeight() || this.bottomLimit < 0) {
            this.ySpeed = -this.ySpeed;
        }
    }

    public void draw(ShapeRenderer shape) {
        shape.setColor(color);
        shape.circle(x, y, size);
    }

    public void checkCollision(Paddle paddle) {
        if (collidesWith(paddle)) {
            this.ySpeed = -this.ySpeed;
        }
    }

    public void checkCollision(Block block) {
        if (collidesWith(block)) {
            this.ySpeed = -this.ySpeed;
            block.destroyed = true;
        }
    }

    private boolean collidesWith(Paddle paddle) {
        if (this.leftLimit <= paddle.getRightLimit()
                && paddle.getRightLimit() <= this.rightLimit + paddle.getWidth()
                && this.topLimit >= paddle.getBottomLimit()
                && paddle.getBottomLimit() >= this.bottomLimit - paddle.getHeight()) {
            return true;
        }
        return false;
    }

    private boolean collidesWith(Block block) {
        if (this.leftLimit <= block.getRightLimit()
                && block.getRightLimit() <= this.rightLimit + block.getWidth()
                && this.topLimit >= block.getBottomLimit()
                && block.getBottomLimit() >= this.bottomLimit - block.getHeight()) {
            return true;
        }
        return false;
    }
}
