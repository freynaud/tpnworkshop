package com.element34;

import java.util.concurrent.TimeoutException;

public class PageObject {

  API api = new API();
  private int counter = 0;


  @Timed
  public void step1() {
    api.call1();
  }

  @Timed
  public void step2() {
    api.call1();
    api.call2();
  }

  public void step3() throws TimeoutException {
    if (counter == 0) {
      counter++;
      throw new TimeoutException("expected");
    } else {
      System.out.println("step3 succeeded !");
    }

  }

}
