package controller;

import java.util.Arrays;

public class Command {
	private String[] args;
	
	public Command(String body) {
		args = body.split(" ");
	}
	
	public String getBody() {
		return Arrays.toString(args);
	}
	
	public String getArg(int i) {
		return args[i];
	}
	
	public String getType() {
		return args[0];
	}
}
