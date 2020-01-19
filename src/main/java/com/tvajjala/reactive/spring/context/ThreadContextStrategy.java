package com.tvajjala.reactive.spring.context;

import org.springframework.util.Assert;

/**
 * @author ThirupathiReddy Vajjala
 */
public class ThreadContextStrategy {


    ThreadLocal<ThreadContext> contextHolder = ThreadLocal.withInitial(()-> new ThreadContext());


    public void clearContext(){
        contextHolder.remove();
    }



    public void setContext(ThreadContext applicationContext){
        Assert.notNull(applicationContext, "applicationContext should not be null");
        contextHolder.set(applicationContext);
    }

    public ThreadContext getContext(){

        ThreadContext ctx=contextHolder.get();

        if(ctx==null){
            ctx= this.createEmptyContext();
            contextHolder.set(ctx);
        }

        return ctx;
    }

    private ThreadContext createEmptyContext() {
        return new ThreadContext();
    }


}
