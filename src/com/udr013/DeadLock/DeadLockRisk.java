package com.udr013.DeadLock;

public class DeadLockRisk {

    private static class Resource {
        public int value;
    }

    private Resource resourceA = new Resource();
    private Resource resourceB = new Resource();

    public int read(){
        synchronized (resourceA){  // possible deadlock
            synchronized (resourceB){
                return resourceB.value + resourceA.value;
            }
        }
    }

    public void write(int a, int b){
        synchronized (resourceB){ // possible deadlock
            synchronized (resourceA){
                resourceB.value = a;
                resourceA.value = b;
            }
        }
    }
}
// this can easily be fixed by swapping the resources in either one of the methods, to change order of locking..