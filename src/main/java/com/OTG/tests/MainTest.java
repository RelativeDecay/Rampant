package com.OTG.tests;

import com.OTG.rampant.engine.Engine;

public class MainTest {

	public static void main(String[] args) {
		Engine e = new Engine("Rampant V.1", new TestGame(1280, 720), false);
	}
}
