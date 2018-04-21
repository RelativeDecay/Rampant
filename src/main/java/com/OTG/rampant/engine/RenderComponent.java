package com.OTG.rampant.engine;

import static org.lwjgl.opengl.GL11.*;

public class RenderComponent implements Component{

	private float r, g, b, sx, sy;

	public RenderComponent(float r, float g, float b, float sx, float sy) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.sx = sx;
		this.sy = sy;
	}

	@Override
	public void render() {

		glColor3d(r, g, b);
		
		glBegin(GL_QUADS);
		{
			glVertex2f(0f, 0f);
			
			//glColor3d(1-r, 1-g, 1-b);
			glVertex2f(0f, sy);
			
			//glColor3d(r, g, b);
			glVertex2f(sx, sy);
			
			//glColor3d(1-r, 1-g, 1-b);
			glVertex2f(sx, 0);
		}
		glEnd();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
