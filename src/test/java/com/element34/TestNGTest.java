package com.element34;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.reporters.EmailableReporter2;

@Listeners({ConnectByteBuddy.class, EmailableReporter2.class})
public class TestNGTest {


  @Test
  public void apiTest() {
    API api = new API();
    api.call1();
    api.call2();
    api.call3("3");
  }

  @Test
  public void apiTest2() {
    API api = new API();
    api.call1();
    api.call2();
    api.call3("3");
    api.call3("3");
  }

  @Test
  public void mix() {
    API api = new API();

    PageObject object = new PageObject();
    object.step1();
    api.call2();
    object.step2();
  }
}
