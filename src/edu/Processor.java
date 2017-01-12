/*
 * Copyright (c) 2017. Aleksey Eremin
 * 12.01.17 11:28
 *
 */

/*
 Многопоточная вычислительная задача из 2 этапов.
 by novel  http://java-course.ru/begin/multithread_01/
 */

package edu;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by ae on 12.01.2017.
 */
public class Processor {
  public static final int STR_COUNT = 1000;
  public static final int TASK_COUNT =  10000;
  
  public static void main(String[] args)
  {
    {
      BigTaskOneThread bt = new BigTaskOneThread();
      long d1 = System.currentTimeMillis();
      Long result = bt.startTask();
      long d2 = System.currentTimeMillis();
      d1= d2 -d1;
      System.out.println(result + ", Time 1: " + d1);
    }
    
    {
      BigTaskManyThreads bt = new BigTaskManyThreads();
      long d1 = System.currentTimeMillis();
      Long result = bt.startTask();
      long d2 = System.currentTimeMillis();
      d1= d2 -d1;
      System.out.println(result + ", Time 2: " + d1);
    }
    // exit of program
  }
  
  public Long process()
  {
    Long summa = 0L;
  
    SecureRandom random = new SecureRandom();
    for(int i=0; i<Processor.TASK_COUNT; i++) {
      String g = new BigInteger(500, random).toString(32);
      for(char c: g.toCharArray()) {
        summa += c;
      }
    }
    return summa;
  }
  
}// end class Processor


class BigTaskOneThread {
  public Long startTask()
  {
    Long summa = 0L;
    for(int i=0; i<Processor.STR_COUNT; i++) {
      Processor p = new Processor();
      summa += p.process();
    }
    return summa;
  }
}//BigTaskOneThread


class BigTaskManyThreads {
  public Long startTask()
  {
    int ap = Runtime.getRuntime().availableProcessors();
    ExecutorService es = Executors.newFixedThreadPool(ap);
    
    Long summa = 0L;
    try {
      List<MyCallable> threads = new ArrayList<MyCallable>();
      for(int i=0; i<Processor.STR_COUNT; i++) {
        threads.add(new MyCallable());
      }
      List<Future<Long>> result = es.invokeAll(threads);
      
      for(Future<Long> f: result) {
        summa += f.get();
      }
      es.shutdown();
      
    } catch(InterruptedException | ExecutionException ex) {
      ex.printStackTrace();
    }
    return summa;
  }

}// end class BigTaskManyThreads

class MyCallable implements Callable<Long> {
  @Override
  public Long call() throws Exception {
    Processor p = new Processor();
    return p.process();
  }
} // end class  MyCallable


