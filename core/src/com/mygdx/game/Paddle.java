package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle {
    // == Fields ==
    private int x;
    private int y = 20;
    private int width = 150;
    private int height = 10;

    // == Public Methods ==
    public void update() {

        if (Gdx.input.getX() - 75 < 0){
            this.x = 0;
        } else if(Gdx.input.getX() + 75 > Gdx.graphics.getWidth()){
            this.x = Gdx.graphics.getWidth() - 150;
        } else {
            this.x = Gdx.input.getX() - 75;
        }
//        this.y = Gdx.graphics.getHeight() - Gdx.input.getY();
    }
    public void draw(ShapeRenderer shape) {
        shape.rect(this.x, this.y, this.width, this.height);
    }

    // == Getters ==
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
