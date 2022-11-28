package io.opencensus.trace;

import io.opencensus.common.Clock;
import io.opencensus.internal.Provider;
import io.opencensus.trace.config.TraceConfig;
import io.opencensus.trace.export.ExportComponent;
import io.opencensus.trace.propagation.PropagationComponent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
/* loaded from: classes3.dex */
public final class Tracing {
    private static final Logger logger = Logger.getLogger(Tracing.class.getName());
    private static final TraceComponent traceComponent = a(TraceComponent.class.getClassLoader());

    private Tracing() {
    }

    static TraceComponent a(@Nullable ClassLoader classLoader) {
        try {
            return (TraceComponent) Provider.createInstance(Class.forName("io.opencensus.impl.trace.TraceComponentImpl", true, classLoader), TraceComponent.class);
        } catch (ClassNotFoundException e2) {
            logger.log(Level.FINE, "Couldn't load full implementation for TraceComponent, now trying to load lite implementation.", (Throwable) e2);
            try {
                return (TraceComponent) Provider.createInstance(Class.forName("io.opencensus.impllite.trace.TraceComponentImplLite", true, classLoader), TraceComponent.class);
            } catch (ClassNotFoundException e3) {
                logger.log(Level.FINE, "Couldn't load lite implementation for TraceComponent, now using default implementation for TraceComponent.", (Throwable) e3);
                return TraceComponent.a();
            }
        }
    }

    public static Clock getClock() {
        return traceComponent.getClock();
    }

    public static ExportComponent getExportComponent() {
        return traceComponent.getExportComponent();
    }

    public static PropagationComponent getPropagationComponent() {
        return traceComponent.getPropagationComponent();
    }

    public static TraceConfig getTraceConfig() {
        return traceComponent.getTraceConfig();
    }

    public static Tracer getTracer() {
        return traceComponent.getTracer();
    }
}
