package com.udr013.ThreadAndRunnable;


public class Sing extends Thread {

    @Override
    public void run(){
    	System.out.println("Singing.... first voice");
	}

	// no need to override this method, just for demonstration
	@Override
	public void start(){
		super.start(); // if we don't call super run will not start a thread
		System.out.println("Starting...");
	}
}
