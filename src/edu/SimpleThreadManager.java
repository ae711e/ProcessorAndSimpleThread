/*
 * Copyright (c) 2017. Aleksey Eremin
 * 12.01.17 12:07
 *
 */

package edu;

/**
 * Created by ae on 12.01.2017.
 * Многозадачность простой менеджер потоков
 * by novel  http://java-course.ru/begin/multithread_01/ (Как создавать потоки)
 */
public class SimpleThreadManager {
  
  public static void main(String[] args)
  {
    for(int i=0; i<10; i++) {
      Thread first = new SimpleThread(i+1);
      first.start();
    }
  }
}


class SimpleThread extends Thread {
  int id;
  SimpleThread() {
    ;
  }
  
  SimpleThread(int i)
  {
    id=i;
  }
  
  @Override
  public void run() {
    // super.run();
    try {
      long pause = Math.round(Math.random()*2000);
      Thread.sleep(pause);
      System.out.println(this.id+" Simple Thread - pause = " + pause);
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }
  }
}