package com.OTG.rampant.engine;

import java.util.ArrayList;

import com.OTG.rampant.input.MouseInput;

public abstract class AbstractGame {
	
	protected int width, height;
	protected long window;
	protected MouseInput mouse;
	
	protected ArrayList<GameObject> objects;
	
	public abstract void update(float interval);
	
	//Implements
	
	public AbstractGame(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void init() {
		objects = new ArrayList<GameObject>();
	}
	
	public void render() {
		for (GameObject o : objects) {
			o.render();
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void setWindow(long window) {
		this.window = window;
		mouse = new MouseInput(width, height, window);
	}
	
	public float getWindow() {
		return this.window;
	}
	
	public MouseInput getMouse() {
		return mouse;
	}
}
