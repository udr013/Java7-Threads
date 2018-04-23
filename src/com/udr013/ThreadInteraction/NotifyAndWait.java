package com.udr013.ThreadInteraction;

public class NotifyAndWait {

    public static void main(String[] args) throws InterruptedException {
        ThreadB b = new ThreadB();
        b.start();
        b.wait(); //Exception in thread "main" java.lang.IllegalMonitorStateException we don't have monitor lock
        synchronized (b){ // here the code synchronizes with b this is because in order to call wait() on the object
            try {          // Thread main has to be the owner of the lock on b
                System.out.println("Waiting toi complete...");
                System.out.println("Total is : " + b.total);
                b.wait(); // we tell the main thread to wait for b to notify us
                            // when the thread waits, it releases the lock and waits to continue, the thread needs the
                            // lock, so it may be blocked unil it gets it
                System.out.println("Total is : " + b.total);
            } catch (InterruptedException i){
            System.out.println("Total is : " + b.total);
        }
            System.out.println("Total in main is : " + b.total); //if no wait on b this was probably close to "O"
        }
    }
}

        class ThreadB extends Thread{
            int total;

            @Override
            public void run() {
                synchronized (this){
                    for (int i =0; i < 100; i++){
                        total +=i;
                        System.out.println("Total is : " + total);
                    }
                    notify();
                }
            }
        }

