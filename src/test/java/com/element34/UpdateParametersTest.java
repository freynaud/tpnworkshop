package com.element34;

import com.element34.UpdateParametersTest.ParamInterceptor.ReturnInterceptor;
import java.lang.instrument.Instrumentation;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;
import org.testng.annotations.Test;


public class UpdateParametersTest {


  @Test
  public void base() {
    BaseWithParam param = new BaseWithParam();
    System.out.println(param.example("test"));
  }

  @Test
  public void instrumented() {

    Instrumentation inst = ByteBuddyAgent.install();

    new AgentBuilder.Default()
        .type(ElementMatchers.named("com.element34.BaseWithParam"))
        .transform((builder, typeDescription, classLoader, module) -> builder
            .visit(
                Advice
                    .to(ParamInterceptor.class)
                    .on(ElementMatchers.named("example"))))
        .installOn(inst);

    new BaseWithParam().example("Hello");

  }

  @Test
  public void returnValue() {

    Instrumentation inst = ByteBuddyAgent.install();

    new AgentBuilder.Default()
        .type(ElementMatchers.named("com.element34.BaseWithParam"))
        .transform((builder, typeDescription, classLoader, module) -> builder
            .visit(
                Advice
                    .to(ReturnInterceptor.class)
                    .on(ElementMatchers.named("example"))))
        .installOn(inst);

    System.out.println("final : " + new BaseWithParam().example("Hello"));

  }

//  @Test
//  public void instrumentedInstance() {
//
//    Instrumentation inst = ByteBuddyAgent.install();
//
//    new AgentBuilder.Default()
//
//        .type(ElementMatchers.named("com.element34.BaseWithParam"))
//        .transform((builder, typeDescription, classLoader, module) -> builder
//            .visit(
//                Advice
//                    .to(ParamInterceptor.class)
//                    .on(ElementMatchers.named("example"))))
//        .installOn(inst);
//
//    new BaseWithParam().example("Hello");
//
//  }

  public static class ParamInterceptor {



    @Advice.OnMethodEnter
    private static void enter(@Advice.Argument(value = 0, readOnly = false) String value) {
      String newValue = "world";
      System.out.println("ParamInterceptor got:" + value + " and will replace with " + newValue);
      value = newValue;
    }

    public static class ReturnInterceptor {


      @Advice.OnMethodEnter
      private static void enter(@Advice.Argument(value = 0, readOnly = false) String value) {
        String newValue = "world";
        System.out.println("ReturnInterceptor enters with " + value + " , changes to " + newValue);
        value = newValue;
      }

      @Advice.OnMethodExit
      private static String exit(@Advice.Return(readOnly = false) String value) {
        String newReturn = "woot";
        System.out.println("ReturnInterceptor return was " + value + " , changes to " + newReturn);
        value = newReturn;
        return value;
      }
    }
  }

//  public class AdviceChange {
//
//    private final String value;
//
//    public AdviceChange(String value) {
//      this.value = value;
//    }
//
//    @Advice.OnMethodEnter
//    private void enter(@Advice.Argument(value = 0, readOnly = false) String value) {
//      System.out.println("ADVICE" + value);
//      value = this.value;
//    }
//
//  }
}
