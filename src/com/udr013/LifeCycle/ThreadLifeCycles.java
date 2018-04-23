package com.udr013.LifeCycle;

import java.util.Set;

public class ThreadLifeCycles {

    public static void main(String[] args) {

        Set<Thread> threads = Thread.getAllStackTraces().keySet();
         for (Thread t : threads){
             try {
                 Thread.sleep(600L);
             }catch (InterruptedException e){
                 System.out.println("interrupted");
             }

             System.out.println( t + "--->" + t.getState());

         }
    }
}
