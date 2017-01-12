/*
 * Copyright (c) 2017. Aleksey Eremin
 * 12.01.17 12:42
 *
 */

package edu;

/**
 * Created by ae on 12.01.2017.
 * Многопоточность с помощью интерфейса Runnable
 */
public class SimpleThreadManager3
{
 public static void main(String[] args)
 {
   for(int i=0; i<10; i++) {
     Runnable first = new SimpleRunnable();  // создаем объект нашего класса с интерфейсом
     Thread t = new Thread(first);   // создаем поток с нашим объектом
     t.start();                      // запускаем поток
   }
 }
}


class SimpleRunnable implements Runnable
{
  @Override
  public void run()
  {
    try {
      long pause = Math.round(Math.random()*2000);
      Thread.sleep(pause);
      System.out.println("Simple runnable pause=" + pause);
    } catch(InterruptedException ex) {
      ex.printStackTrace();
    }
    
  }
}