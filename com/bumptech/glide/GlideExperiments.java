package com.bumptech.glide;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class GlideExperiments {
    private final Map<Class<?>, Experiment> experiments;

    /* loaded from: classes.dex */
    static final class Builder {
        private final Map<Class<?>, Experiment> experiments = new HashMap();

        Builder b(Experiment experiment) {
            this.experiments.put(experiment.getClass(), experiment);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public GlideExperiments c() {
            return new GlideExperiments(this);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder d(Experiment experiment, boolean z) {
            if (z) {
                b(experiment);
            } else {
                this.experiments.remove(experiment.getClass());
            }
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface Experiment {
    }

    GlideExperiments(Builder builder) {
        this.experiments = Collections.unmodifiableMap(new HashMap(builder.experiments));
    }

    public boolean isEnabled(Class<? extends Experiment> cls) {
        return this.experiments.containsKey(cls);
    }
}
