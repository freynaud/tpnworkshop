package com.element34;

public class Clock {

  private long start = System.currentTimeMillis();

  public long duration(){
    return System.currentTimeMillis() - start;
  }
}
