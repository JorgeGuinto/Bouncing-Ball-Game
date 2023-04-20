package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Block {
    // == Fields ==
    private int x,y,width,height;
    private boolean destroyed;

    // == Constructor ==
    public Block(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.destroyed = false;

    }

    // == Public Methods ==
    public void update() {
    }

    public void draw(ShapeRenderer shape){
        shape.rect(x, y, width, height);
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
    public boolean isDestroyed() {
        return destroyed;
    }

    // == Setters ==
    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
}
