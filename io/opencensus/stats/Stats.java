package io.opencensus.stats;

import io.opencensus.internal.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
/* loaded from: classes3.dex */
public final class Stats {
    private static final Logger logger = Logger.getLogger(Stats.class.getName());
    private static final StatsComponent statsComponent = a(StatsComponent.class.getClassLoader());

    private Stats() {
    }

    static StatsComponent a(@Nullable ClassLoader classLoader) {
        try {
            return (StatsComponent) Provider.createInstance(Class.forName("io.opencensus.impl.stats.StatsComponentImpl", true, classLoader), StatsComponent.class);
        } catch (ClassNotFoundException e2) {
            logger.log(Level.FINE, "Couldn't load full implementation for StatsComponent, now trying to load lite implementation.", (Throwable) e2);
            try {
                return (StatsComponent) Provider.createInstance(Class.forName("io.opencensus.impllite.stats.StatsComponentImplLite", true, classLoader), StatsComponent.class);
            } catch (ClassNotFoundException e3) {
                logger.log(Level.FINE, "Couldn't load lite implementation for StatsComponent, now using default implementation for StatsComponent.", (Throwable) e3);
                return NoopStats.c();
            }
        }
    }

    public static StatsCollectionState getState() {
        return statsComponent.getState();
    }

    public static StatsRecorder getStatsRecorder() {
        return statsComponent.getStatsRecorder();
    }

    public static ViewManager getViewManager() {
        return statsComponent.getViewManager();
    }

    @Deprecated
    public static void setState(StatsCollectionState statsCollectionState) {
        statsComponent.setState(statsCollectionState);
    }
}
