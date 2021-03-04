package com.element34;


public class BaseWithParam {

  public BaseWithParam() {

  }


  public String example(String test) {
    System.out.println("value inside method:" + test);
    return "base:" + test;
  }
}
