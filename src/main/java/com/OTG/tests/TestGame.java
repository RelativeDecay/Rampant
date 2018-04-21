package com.OTG.tests;

import static org.lwjgl.glfw.GLFW.*;

import com.OTG.rampant.engine.AbstractGame;
import com.OTG.rampant.engine.GameObject;

public class TestGame extends AbstractGame {

	public TestGame(int width, int height) {
		super(width, height);
	}

	@Override
	public void init() {
		super.init();
		for (int i = 0; i < 3; i++) {
			objects.add(new Dummy(width / 2, height / 2));
		}
	}

	@Override
	public void update(float interval) {
		if (mouse.isLeftButtonClick() || mouse.isRightButtonPressed()) {
			objects.add(new Dummy((float) mouse.getX() - 16, (float) mouse.getY() - 16));
			mouse.setRecieved(GLFW_MOUSE_BUTTON_1);
		}

		// System.out.println("X: " + mouse.getX() + " Y: " + mouse.getY());

		for (GameObject o : objects) {
			o.update();
		}
	}

}
