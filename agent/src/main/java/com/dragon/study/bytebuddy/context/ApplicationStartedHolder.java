package com.dragon.study.bytebuddy.context;


import com.dragon.study.bytebuddy.DebugListener;
import com.dragon.study.bytebuddy.advice.AdviceProfiledTransformer;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

import static net.bytebuddy.matcher.ElementMatchers.named;

//1
public class ApplicationStartedHolder implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        System.out.println("application started event");
//    Instrumentation instrumentation;
//    try {
//      instrumentation = ByteBuddyAgent.getInstrumentation();
//    } catch (IllegalStateException e) {
//      instrumentation = ByteBuddyAgent.install(
//          new ByteBuddyAgent.AttachmentProvider.Compound(
//              ByteBuddyAgent.AttachmentProvider.DEFAULT));
//    }
        ByteBuddyAgent.install();
        new AgentBuilder.Default()
                .with(DebugListener.getListener())
                .type(named("com.dragon.study.bytebuddy.advice.AdviceProfiled"))
                .transform(new AdviceProfiledTransformer())
                .installOnByteBuddyAgent();
    }
}
