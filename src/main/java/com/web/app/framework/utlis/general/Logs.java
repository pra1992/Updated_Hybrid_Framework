package com.web.app.framework.utlis.general;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Logs {

	public Console console() {
		return new Console();
	}
	
	public File file() {
		return new File();
	}
 
	public class Console {
		
		String pattern = "dd-MM-yyyy'T'HH:mm:ss.SSS";
		
		public Console() {}

		public void pass(String details) {
			System.out.println(JavaHelpers.getCurrentDate(pattern) + " [PASSED]: " + details);
		}

		public void fail(String details) {
			System.err.println(JavaHelpers.getCurrentDate(pattern) + " [FAILED]: " + details);
		}

		public void warning(String details) {
			System.err.println(JavaHelpers.getCurrentDate(pattern) + " [WARNING]: " + details);
		}

		public void info(String details) {
			System.out.println(JavaHelpers.getCurrentDate(pattern) + " [INFORMATION]: " + details);
		}

	}
	
	public class File {
		
		String logsFolder;
		String pattern = "dd-MM-yyyy'T'HH:mm:ss.SSS";
		
		public File() {
			logsFolder = JavaHelpers.getFolderPathIfNotCreateNew("logs");
		}
		
		public void pass(String details) {
			try {
				JavaHelpers.createFileIfNot(logsFolder+"app.log");
				Files.writeString(
						Path.of(logsFolder+"app.log"), 
						JavaHelpers.getCurrentDate(pattern) + " [PASSED]: " + details + "\n", 
						StandardOpenOption.APPEND);
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
		
		public void fail(String details) {
			try {
				JavaHelpers.createFileIfNot(logsFolder+"error.log");
				Files.writeString(
						Path.of(logsFolder+"error.log"), 
						JavaHelpers.getCurrentDate(pattern) + " [FAILED]: " + details + "\n", 
						StandardOpenOption.APPEND);
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
		
		public void warning(String details) {
			try {
				JavaHelpers.createFileIfNot(logsFolder+"error.log");
				Files.writeString(
						Path.of(logsFolder+"error.log"), 
						JavaHelpers.getCurrentDate(pattern) + " [WARNING]: " + details + "\n", 
						StandardOpenOption.APPEND);
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
		
		public void info(String details) {
			try {
				JavaHelpers.createFileIfNot(logsFolder+"app.log");
				Files.writeString(
						Path.of(logsFolder+"app.log"), 
						JavaHelpers.getCurrentDate(pattern) + " [INFORMATION]: " + details + "\n", 
						StandardOpenOption.APPEND);
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
		
	}

}