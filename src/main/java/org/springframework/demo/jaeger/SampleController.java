package org.springframework.demo.jaeger;

import io.opentelemetry.api.OpenTelemetry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
    private final JaegerExample jaegerExample;

    public SampleController(OpenTelemetry openTelemetry) {
        this.jaegerExample = new JaegerExample(openTelemetry);
    }

    @GetMapping("/sample")
    public String generateTrace() {
        // Use the existing JaegerExample class
        for (int i = 0; i < 3; i++) {
            jaegerExample.mySampleUseCase();
        }
        return "Traces generated! Check Jaeger UI at http://localhost:16686";
    }
}
