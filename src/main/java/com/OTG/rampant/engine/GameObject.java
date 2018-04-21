package com.OTG.rampant.engine;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

import com.OTG.rampant.engine.components.Component;

public abstract class GameObject {

	protected float x, y;
	protected ArrayList<Component> components;
	
	public GameObject(float x, float y) {
		this.x = x;
		this.y = y;
		components = new ArrayList<Component>();
	}
	
	public void init() {}

	public void update() {
		for(Component c : components) {
			c.update();
		}
	}

	public void render() {
		glPushMatrix();
		{
			glTranslatef(x, y, 0);
			for(Component c : components) {
				c.render();
			}
		}
		glPopMatrix();
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

}
