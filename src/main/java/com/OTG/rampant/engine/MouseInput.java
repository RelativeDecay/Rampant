package com.OTG.rampant.engine;

import static org.lwjgl.glfw.GLFW.*;

public class MouseInput {

	//TODO: Refine Clicking Behavior
	
	private double x, y;

	private boolean inWindow = false;
	private long window;

	private boolean leftButtonPressed = false;
	private boolean rightButtonPressed = false;

	private boolean recievedL, recievedR;

	public MouseInput(int width, int height, long window) {

		this.window = window;

		glfwSetCursorPosCallback(window, (windowHandle, xpos, ypos) -> {
			x = xpos;
			y = height - ypos;
		});
		glfwSetCursorEnterCallback(window, (windowHandle, entered) -> {
			inWindow = entered;
		});
		glfwSetMouseButtonCallback(window, (windowHandle, button, action, mode) -> {
			if (button == GLFW_MOUSE_BUTTON_1) {
				leftButtonPressed = action == GLFW_PRESS;
				recievedL = false;
			}
			if (button == GLFW_MOUSE_BUTTON_2) {
				rightButtonPressed = action == GLFW_PRESS;
				recievedR = false;
			}

			// leftButtonPressed = button == GLFW_MOUSE_BUTTON_1 && action == GLFW_PRESS;
			// rightButtonPressed = button == GLFW_MOUSE_BUTTON_2 && action == GLFW_PRESS;
		});
	}


	public boolean isLeftButtonPressed() {
		return glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_LEFT) == 1;
	}

	public boolean isRightButtonPressed() {
		return glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_RIGHT) == 1;
	}

	public boolean isLeftButtonClick() {
		return isLeftButtonPressed() && !recievedL;
	}

	public boolean isRightButtonClick() {
		return isRightButtonPressed() && !recievedR;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public boolean isInWindow() {
		return inWindow;
	}
	
	public void setRecieved(int button) {
		if(button == GLFW_MOUSE_BUTTON_1) {
			recievedL = true;
		}
		if(button == GLFW_MOUSE_BUTTON_2) {
			recievedR = true;
		}
	}

	public void update() {

	}

}
