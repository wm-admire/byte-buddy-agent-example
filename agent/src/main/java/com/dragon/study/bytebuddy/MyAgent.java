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

        String okHttpInterceptor = "com.dragon.study.bytebuddy.okhttp.OkHttpInterceptor";
        String redisInterceptor = "com.dragon.study.bytebuddy.redis.RedisInterceptor";
        String metricsInterceptor = "com.dragon.study.bytebuddy.metrics.MetricsInterceptor";
        String mysqlInterceptor = "com.dragon.study.bytebuddy.mysql.MysqlInterceptor";
        String thriftServerInterceptor = "com.dragon.study.bytebuddy.thrift.ThriftServerInterceptor";
        String jerseyInterceptor = "com.dragon.study.bytebuddy.jersey.JerseyDispatcherInterceptor";


        new AgentBuilder.Default()
                .with(DebugListener.getListener())
                .type(named("com.dragon.study.bytebuddy.advice.AdviceProfiled"))
                .transform(new AdviceProfiledTransformer())
                .installOn(instrumentation);

    }


}
