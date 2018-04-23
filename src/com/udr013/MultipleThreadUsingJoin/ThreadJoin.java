package com.udr013.MultipleThreadUsingJoin;

public class ThreadJoin {

    public static void main(String[] args) {
        ScreenDesign design = new ScreenDesign();
        design.setName("Design thead");
        design.start();
        System.out.println(design + " --" + design.getState());
        Developer developer = new Developer(design);
        developer.code();
    }

    private static class ScreenDesign extends Thread {
        public void run(){
            for (int i = 0; i <10; i++){
                System.out.println(i);
                try {
                    Thread.sleep(300L);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Developer {
        ScreenDesign design;
        Developer(ScreenDesign screenDesign){
            this.design = screenDesign;
        }

        private void code(){
            try {
                System.out.println("Waiting for design to complete");
                System.out.println(design +"--" + design.getState());
                design.join(30);
                design.join();
                System.out.println(design +"--" + design.getState());
                System.out.println("coding started");

                System.out.println(design +"--" + design.getState());
            }catch (InterruptedException ie){
                System.out.println(ie);
            }
        }
    }
}
