package com.OTG.rampant.engine;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import java.util.ArrayList;

public class Engine implements Runnable {

	public static final int TARGET_FPS = 60;

	public static final int TARGET_UPS = 60;

	private Window window;
	private Thread gameThread;
	private Timer timer;
	private AbstractGame game;

	private double lastFps;
	
	private int fps;
	
	private int width, height;

	public Engine(String name, AbstractGame game, boolean vSync) {

		this.width = game.getWidth();
		this.height = game.getHeight();
		this.game = game;
		
		timer = new Timer();
		gameThread = new Thread(this, "GameThread");
		window = new Window(name, width, height, vSync);

		this.start();

	}

	public synchronized void start() {
		String osName = System.getProperty("os.name");
		if (osName.contains("Mac")) {
			gameThread.run();
		} else {
			gameThread.start();
		}
	}

	public synchronized void stop() {
		//TODO: Implement cleanup
	}

	@Override
	public void run() {
		try {
			init();
			gameLoop();
		} catch (Exception excp) {
			excp.printStackTrace();
		} finally {
			cleanup();
		}
	}

	private void gameLoop() {
		float elapsedTime;
		float accumulator = 0f;
		float interval = 1f / TARGET_UPS;

		boolean running = true;
		while (running && !window.windowShouldClose()) {
			elapsedTime = timer.getElapsedTime();
			accumulator += elapsedTime;

			input();

			while (accumulator >= interval) {
				update(interval);
				accumulator -= interval;
			}

			render();

			if (!window.isvSync()) {
				sync();
			}
		}
	}

	private void input() {
		// TODO Need Keyboard/Mouse input class
		game.getMouse().update();
		
	}

	private void update(float interval) {
		game.update(interval);
	}

	private void render() {
		if (timer.getLastLoopTime() - lastFps > 1) {
			lastFps = timer.getLastLoopTime();
			System.out.println(fps);
			fps = 0;
		}
		fps++;

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glfwPollEvents();

		game.render();
		
		glfwSwapBuffers(window.getWindow());

	}

	private void sync() {
		float loopSlot = 1f / TARGET_FPS;
		double endTime = timer.getLastLoopTime() + loopSlot;
		while (timer.getTime() < endTime) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException ie) {
			}
		}
	}

	private void cleanup() {
		// TODO Auto-generated method stub

	}

	private void init() throws Exception {
		window.init();
		game.setWindow(window.getWindow());
		timer.init();
		lastFps = timer.getTime();
		fps = 0;
		game.init();
	}

}
