package org.springframework.demo.jaeger;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;

public final class JaegerExample {
    private final Tracer tracer;

    public JaegerExample(OpenTelemetry openTelemetry) {
        tracer = openTelemetry.getTracer("org.springframework.demo.jaeger.JaegerExample");
    }

    public void mySampleUseCase() {
        // Generate a span
        Span span = this.tracer.spanBuilder("Jaeger Sample Use Case").startSpan();
        span.addEvent("Event 0");
        // execute my use case - here we simulate a wait
        doWork();
        span.addEvent("Event 1");
        span.end();
        System.out.println(span.getSpanContext());
    }

    private void doWork() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // do the right thing here
        }
    }
}