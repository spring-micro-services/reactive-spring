package com.tvajjala.reactive.spring.context;

/**
 * @author ThirupathiReddy Vajjala
 */
public final class ThreadContextHolder {

    private static final ThreadContextStrategy STRATEGY=new ThreadContextStrategy();


    public static ThreadContext getThreadContext(){
        return STRATEGY.getContext();
    }

    public static void setContext(ThreadContext applicationContext){
        STRATEGY.setContext(applicationContext);
    }


    public static void clear(){
        STRATEGY.clearContext();
    }

}
