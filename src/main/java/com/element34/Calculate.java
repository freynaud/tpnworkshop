package com.element34;

import java.lang.reflect.Method;
import net.bytebuddy.asm.Advice;

public class Calculate {


  @Advice.OnMethodEnter
  public static Clock enter(
      @Advice.Origin Method method,
      @Advice.This Object request) {
    System.out.println("in " + method.getName());

    return new Clock();
  }


  @Advice.OnMethodExit
  public static void exit(
      @Advice.Origin Method method,
      @Advice.Enter Clock tmp) {
    System.out.println("out " + method.getName() + "=>" + tmp.duration());

  }
  //}

}

