package com.udr013.ThreadInteraction;

import java.util.Set;

public class NotifyAllReaders {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        Thread thread = new Thread(calculator);
        thread.setName("zero");
        thread.start();

        Thread thread1  = new Reader(calculator);
        thread1.setName("first");
        thread1.start();

        Thread thread2  = new Reader(calculator);
        thread2.setName("second");
        thread2.start();

        Thread thread3  = new Reader(calculator);
        thread3.setName("third");
        thread3.start();



       Set<Thread> threads = Thread.getAllStackTraces().keySet();
       for(Thread t: threads ){
           System.out.println(t.getState() + " - " + t.getName());
       }
    }

}

class Calculator implements Runnable {
    int total;

    @Override
    public void run() {
        synchronized (this){
            for(int i = 0; i < 100; i++){
                total += i;
                System.out.println(total);
            }
        }
        notify();
//        notifyAll();
    }
}

class Reader extends Thread {
    Calculator c;

    public Reader(Calculator calculator){
        this.c = calculator;
    }

    @Override
    public void run() {
        synchronized (c){
            try {
                System.out.println("Waiting for calculation ... ");
                c.wait();
                System.out.println("total is: " + c.total);
            } catch (InterruptedException e) {
                System.out.println("total is: " + c.total);
            }
        }
        System.out.println(c + Thread.currentThread().getName());
    }


}
