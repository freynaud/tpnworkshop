package com.element34;

import com.element34.Timed;
import java.lang.instrument.Instrumentation;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;
import org.testng.ITestNGListener;

public class ConnectByteBuddy implements ITestNGListener {

  static {
    Instrumentation inst = ByteBuddyAgent.install();

    new AgentBuilder.Default()
        .type(ElementMatchers.nameStartsWith("com.element34"))
        .transform((builder, typeDescription, classLoader, module) -> builder
            .method(ElementMatchers.isAnnotatedWith(Timed.class))

            .intercept(Advice.to(Calculate.class)))
        .installOn(inst);
  }
}
