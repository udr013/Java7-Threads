package com.udr013.bankaccount;

public class AccountDanger implements Runnable {

    private static Account account = new Account();

    public static void main(String[] args) {
        account.setBalance(400);
        AccountDanger r  = new AccountDanger(); //create runnable object
        Thread one = new Thread(r);
        one.setName("Fred");
        Thread two = new Thread(r);
        two.setName("Wilma");

        one.start();
        two.start();

    }


    @Override
    public void run() {
     for(int i = 0; i < 10; i++){
         makeWithdrawal(30);
         if (account.getBalance() <0){
             System.out.println("account is overdrawn!");
         }
     }
    }

//    private void makeWithdrawal(int amount) {
    private synchronized void makeWithdrawal(int amount) { //just add synchronized to solve the isssue of overdrawal
        if (account.getBalance()  >=  amount){
            System.out.println(Thread.currentThread().getName() + "  is doing a withdraw");
            try {
                Thread.sleep(500);  //If a thread goes to sleep it holds any locks it has, it doesn't release them!
            } catch (InterruptedException ie){
                return;
            }

            account.withdraw(amount);
            System.out.println(Thread.currentThread().getName() + " completes the withdrawal");
        } else {
            System.out.println("Not enough in account for " + Thread.currentThread().getName() + " to withdraw: " + account.getBalance() );
        }
    }
}

class Account {
    private  int balance = 0;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void withdraw (int amount){
        balance = balance - amount;
    }
}

class Blabla {
    static int count;

    public static void getCount() throws ClassNotFoundException {
        Class bla = Class.forName("Blabla");
        synchronized (bla) {
            //do stuff
        }
    }
}