package com.udr013.ThreadAndRunnable;

public class SingingAndDancing{

	public static void main(String[] args){
		///class Sing implements Thread and overrides run()
		Thread sing = new Sing();
		Thread sing2 = new Thread(new Sing2()); // now we instantiate a new Thread by passing it a Runnable target
		sing.start();
		sing2.start();
		System.out.println("Dancing");
	}
}
