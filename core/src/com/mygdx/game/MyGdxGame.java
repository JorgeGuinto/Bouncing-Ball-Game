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
	private Ball ball;
	private Paddle paddle;
	private Random rand = new Random();
	@Override
	public void create () {
		int blockWidth = 63;
		int blockHeight = 20;

		shape = new ShapeRenderer();
		ball = new Ball(25,(Gdx.graphics.getHeight()/2)-15,10, 4, 4);
		paddle = new Paddle();

		for (int y = Gdx.graphics.getHeight()/2; y < Gdx.graphics.getHeight(); y += blockHeight + 10) {
			for (int x = 0; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
				blocks.add(new Block(x, y, blockWidth, blockHeight));
			}
		}

//		For lots of balls
//		for (int i = 0; i < 10; i++) {
//			balls.add(new Ball(
//					rand.nextInt(Gdx.graphics.getWidth()-50),
//					rand.nextInt(Gdx.graphics.getHeight()-50),
//					rand.nextInt(50),
//					rand.nextInt(15),
//					rand.nextInt(15)));
//		}
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shape.begin(ShapeRenderer.ShapeType.Filled);
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

		for(int i = 0; i < blocks.size(); i++) {
			Block tempBlock = blocks.get(i);
			if (tempBlock.isDestroyed()){
				blocks.remove(i);
				i--;
			}
		}

		ball.update();
		paddle.update();
		paddle.draw(shape);
		ball.draw(shape);

//		for(Ball ball : balls) {
//			ball.update();
//			ball.draw(shape);
//		}
		shape.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		super.dispose();
	}
}
