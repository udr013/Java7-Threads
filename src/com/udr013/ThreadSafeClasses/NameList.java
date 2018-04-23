
package com.udr013.ThreadSafeClasses;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class NameList {

    private List names = Collections.synchronizedList(new LinkedList());

    public synchronized void add(String name) {  //we are still responsible for our synchronization
        names.add(name);                          //Collection.syn.. may well be redundant now ;)

    }

    public synchronized String removeFirst(){  //sometimes throws indexOutOfBounds exception, we can't rely on
        if (names.size() >0){                  // synchronized methods of Collections.synchronizedList()
            return (String) names.remove(0); //so we still need to synchronize it

        }else {
            return null;
        }
    }

    public static void main(String[] args) {
        final NameList nameList = new NameList();

        nameList.add("blabla");

        class NameDropper  extends Thread {
            @Override
            public void run() {
               String name = nameList.removeFirst();
                System.out.println(name);   //blabla // null
            }
        }

        Thread t1 = new NameDropper();
        Thread t2 = new NameDropper();
        t1.start();
        t2.start();

    }
}

