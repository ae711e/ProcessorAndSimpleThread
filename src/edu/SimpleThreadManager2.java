/*
 * Copyright (c) 2017. Aleksey Eremin
 * 12.01.17 12:34
 *
 */

package edu;
/*
 Запуск потоков с анонимным классом
 */

public class SimpleThreadManager2 {
  
  public static void main(String[] args)
  {
    for(int i=0; i<10; i++) {
      Thread first = new Thread()
                     {
                       @Override
                       public void run()
                       {
                         try {
                           long pause = Math.round(Math.random()*2000);
                           Thread.sleep(pause);
                           System.out.println(" Simple2 Thread - pause = " + pause);
                           
                         } catch (InterruptedException ex) {
                           ex.printStackTrace();
                         }
                       }
                     };
      first.start();
    }
  }
}
