package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.Random;

public class MyGdxGame extends Game {

	public SpriteBatch batch;
	private ShapeRenderer shape;
	private ArrayList<Ball> balls = new ArrayList<>();
	private ArrayList<Block> blocks = new ArrayList<>();
	private Paddle paddle;
	private Random rand = new Random();
	private int xSpeed = 4;
	private int ySpeed = -4;
	private int size = 8;
	@Override
	public void create () {
		int blockWidth = 63;
		int blockHeight = 20;

		shape = new ShapeRenderer();
		balls.add(new Ball(25,(Gdx.graphics.getHeight()/2)-15,size, xSpeed, ySpeed));
		paddle = new Paddle();

		for (int y = Gdx.graphics.getHeight()/2; y < Gdx.graphics.getHeight(); y += blockHeight + 10) {
			for (int x = 0; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
				blocks.add(new Block(x, y, blockWidth, blockHeight));
			}
		}
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shape.begin(ShapeRenderer.ShapeType.Filled);

		for(int i = 0; i < balls.size(); i++) {
			Ball ball = balls.get(i);
			boolean paddleCollide = ball.checkCollision(paddle);

			for (Block block : blocks) {
				if (!paddleCollide){
					if (ball.checkCollision(block)) {
						break;
					}
				}
				block.update();
				block.draw(shape);
			}

			if (!ball.isBallDestroyed()) {
				ball.update();
				ball.draw(shape);
			}
		}

		for(int i = 0; i < blocks.size(); i++) {
			Block tempBlock = blocks.get(i);
			if (tempBlock.isDestroyed()){
				if (Math.random() > .8){
					balls.add(new Ball(tempBlock.getX() + tempBlock.getWidth()/2,
							tempBlock.getY(),
							size,
							xSpeed, ySpeed));
				}
				blocks.remove(i);
				i--;
			}
		}

		paddle.update();
		paddle.draw(shape);
		shape.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		super.dispose();
	}
}
