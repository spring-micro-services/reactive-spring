package com.tvajjala.reactive.spring.hooks;

import brave.Span;
import brave.Tracer;
import com.tvajjala.reactive.spring.context.ThreadContext;
import com.tvajjala.reactive.spring.context.ThreadContextHolder;
import rx.functions.Action0;

public class ContextHandoverAction implements Action0{


    private Action0 actual;
    private final Tracer tracer;
    private final Span parent;
    private final ThreadContext threadContext;


    public ContextHandoverAction(Action0 actual, ThreadContext threadContext, Tracer tracer ){
        this.actual=actual;
        this.threadContext=threadContext;
        this.tracer = tracer;
        this.parent = this.tracer.currentSpan();
    }


    @Override
    public void call() {

        Span span = this.parent;
        boolean created = false;
        if (span != null) {
            span = this.tracer.toSpan(this.parent.context());
        } else {
            span = this.tracer.nextSpan().name("rxjava").start();
            span.tag("thread", Thread.currentThread().getName());
            created = true;
        }

        try {
            Tracer.SpanInScope ws = this.tracer.withSpanInScope(span);
            Throwable var4 = null;

            try {
                ThreadContextHolder.setContext(threadContext);

                this.actual.call();


            } catch (Throwable var20) {
                var4 = var20;
                throw var20;
            } finally {
                if (ws != null) {
                    if (var4 != null) {
                        try {
                            ws.close();
                        } catch (Throwable var19) {
                            var4.addSuppressed(var19);
                        }
                    } else {
                        ws.close();
                    }
                }

            }
        } finally {

            ThreadContextHolder.clear();

            if (created) {
                span.finish();
            }

        }

    }
}
