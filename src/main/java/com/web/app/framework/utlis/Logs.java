package com.web.app.framework.utlis;

public class Logs {

	public Console console() {
		return new Console();
	}

	public class Console {
		
		Console() {}

		public void pass(String details) {
			System.out.println(JavaHelpers.getCurrentDate("dd/MM/yyyy hh:mm:sss") + " PASSED: " + details);
		}

		public void fail(String details) {
			System.err.println(JavaHelpers.getCurrentDate("dd/MM/yyyy hh:mm:sss") + " FAILED: " + details);
		}

		public void waring(String details) {
			System.err.println(JavaHelpers.getCurrentDate("dd/MM/yyyy hh:mm:sss") + " WARNING: " + details);
		}

		public void info(String details) {
			System.out.println(JavaHelpers.getCurrentDate("dd/MM/yyyy hh:mm:sss") + " INFORMATION: " + details);
		}

	}

}