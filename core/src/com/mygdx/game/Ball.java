package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball {
    // == Fields ==
    private int x;
    private int y;
    private int size;
    private int xSpeed;
    private int ySpeed;
    private Color color = Color.WHITE;
    private boolean ballDestroyed = false;

    // == Constructor ==
    public Ball(int x, int y, int size, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    // == Public Methods ==
    public void update() {
        x += xSpeed;
        y += ySpeed;
        if (x <= this.size || x >= Gdx.graphics.getWidth() -this.size) {
            xSpeed = -xSpeed;
        }
        if (y >= Gdx.graphics.getHeight() - this.size) {
            ySpeed = -ySpeed;
        }
        if (y <= this.size) {
            ySpeed = -ySpeed;
            ballDestroyed = true;
        }
    }
    public void draw(ShapeRenderer shape) {
        shape.circle(x, y, size);
        shape.setColor(color);
    }

    public boolean checkCollision(Paddle paddle) {
        return checkCollision(paddle, null);
    }
    public boolean checkCollision(Block block) {
        return checkCollision(null, block);
    }

    public boolean isBallDestroyed() {
        return ballDestroyed;
    }

    // == Private methods ==
    private boolean checkCollision(Paddle paddle, Block block) {
        int x;
        int y;
        int width;
        int height;

        if (paddle != null) {
            x = paddle.getX();
            y = paddle.getY();
            width = paddle.getWidth();
            height = paddle.getHeight();
        } else {
            x = block.getX();
            y = block.getY();
            width = block.getWidth();
            height = block.getHeight();
        }

        if(collidesWith(x, y, width, height)){
            if (paddle != null){
                color = Color.BLUE;
                this.xSpeed = leftRightPaddleCollision(x,width) ?
                        (this.xSpeed < 0 ? this.xSpeed : -this.xSpeed) :
                        (this.xSpeed <= 0 ? -this.xSpeed : this.xSpeed);
                this.ySpeed = -this.ySpeed;
            } else {
                color = Color.GREEN;
                block.setDestroyed(true);
                int upDown = blockUpDownCollision(x,y,width,height);
                this.ySpeed = upDown >= 0 ? -this.ySpeed: this.ySpeed;
                this.xSpeed = upDown >= 0 ? this.xSpeed: -this.xSpeed;
            }
            return true;

        } else {
            color = Color.WHITE;
        }
        return false;
    }

    private boolean collidesWith(int x, int y, int width, int height) {
        //        Check if collides with any part of the paddle or block
        return Math.pow(x-this.x,2) + Math.pow(y-this.y, 2) <= Math.pow(size, 2) ||
                Math.pow(x + width-this.x,2) + Math.pow(y-this.y, 2) <= Math.pow(size, 2) ||
                Math.pow(x-this.x,2) + Math.pow(y + height-this.y, 2) <= Math.pow(size, 2) ||
                Math.pow(x + width-this.x,2) + Math.pow(y + height-this.y, 2) <= Math.pow(size, 2) ||
                (x + width > this.x && x < this.x &&
                        (y + height < this.y && (y + height > (this.y-this.size)) ||
                                y > this.y && y < (this.y+this.size) ||
                                (y <= this.y && y + height >= this.y)));
    }

    private boolean leftRightPaddleCollision(int x, int width) {
//        True = collides on left
        return this.x < (x + width/2);
    }

    private int blockUpDownCollision(int x, int y, int width, int height){
//        0 = down collision
//        1 = up collision
        if (this.x >= x + this.xSpeed && this.x <= x + width - this.xSpeed && this.y + this.size <= y + this.ySpeed){
            return 0;
        } else if (this.x >= x + this.xSpeed && this.x <= x + width - this.xSpeed && this.y - this.size >= y + height/2){// - this.ySpeed){
            return 1;
        }
        return -1;
    }
}
