package com.udr013.Synchronized;

class ShoppingCart {

    public static void main(String[] args) {
        Book book = new Book("blabla");
        Thread task1 = new OnlineBuy(book);
        task1.start();

        Thread task2 = new OnlineBuy(book);
        task2.start();
        System.out.println(book.copiesSold);
        Thread task4 = new OnlineBuy(book);
        task4.start();

        Thread task32 = new Thread(new OnlineReturn(book));
        task32.start();

        Thread task5 = new OnlineBuy(book);
        task5.start();
        System.out.println(book.copiesSold);
        Thread task6 = new OnlineBuy(book);
        task6.start();

        Thread task3 = new Thread(new OnlineReturn(book));
        task3.start();

        Thread task7 = new OnlineBuy(book);
        task7.start();

        Thread task33 = new Thread(new OnlineReturn(book));
//        task3.start(); // Exception in thread "main" java.lang.IllegalThreadStateException was already TERMINATED or called
        task33.start();

        System.out.println(book.copiesSold);
        Thread task8 = new OnlineBuy(book);
        task8.start();

        Thread task9 = new OnlineBuy(book);
        task9.start();

        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(book.copiesSold);
    }
}

   class Book {
        String title;
        int copiesSold = 0;
        double rank = 0;

        Book(String title) {
            this.title = title;
        }

        synchronized public void newSale() {
            copiesSold++;
        }

        synchronized public void returnBook() {
            copiesSold--;
        }

       public void rankChanged() {
           copiesSold++;
       }
    }

    class OnlineBuy extends Thread {

        private Book book;

        public OnlineBuy(Book book) {
            this.book = book;
        }

        @Override
        public void run() {
            book.newSale();
        }
    }


    class OnlineReturn implements Runnable {
        private Book book;

        public OnlineReturn(Book book) {
            this.book = book;
        }

        @Override
        public void run() {
            book.returnBook();
        }
    }


