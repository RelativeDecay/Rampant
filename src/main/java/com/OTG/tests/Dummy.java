package com.OTG.tests;

import java.util.Random;

import com.OTG.rampant.engine.GameObject;
import com.OTG.rampant.engine.components.RenderComponent;

public class Dummy extends GameObject {

	Random r = new Random();
	
	private float dy, dx, speed;

	public Dummy(float x, float y) {
		super(x, y);

		components.add(new RenderComponent(r.nextFloat(), r.nextFloat(), r.nextFloat(), 32, 32));
		dy = r.nextFloat() - 0.5f;
		dx = r.nextFloat() - 0.5f;
		speed = (r.nextFloat() * 10) + 10;
	}

	public void update() {
		super.update();
		if(x < 0 || x > 1280 - 32) {
			dx *= -1;
		}
		if(y < 0 || y > 720 - 32) {
			dy *= -1;
		}
		
		
		x += dx * speed;
		y += dy * speed;
	}

}
