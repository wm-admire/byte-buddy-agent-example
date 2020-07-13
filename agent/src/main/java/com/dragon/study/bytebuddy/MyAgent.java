package com.dragon.study.bytebuddy;

import com.dragon.study.bytebuddy.advice.AdviceProfiledTransformer;
import net.bytebuddy.agent.builder.AgentBuilder;

import java.lang.instrument.Instrumentation;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * Created by dragon on 16/3/29.
 */
public class MyAgent {

    public static void premain(String arg, Instrumentation instrumentation) {

        new AgentBuilder.Default().disableClassFormatChanges()
                .with(DebugListener.getListener())
                .type(named("com.dragon.study.bytebuddy.advice.AdviceProfiled"))
                .transform(new AdviceProfiledTransformer())
                .installOn(instrumentation);

    }


}
