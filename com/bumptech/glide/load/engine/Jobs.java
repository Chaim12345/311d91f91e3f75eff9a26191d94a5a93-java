package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.Key;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
final class Jobs {
    private final Map<Key, EngineJob<?>> jobs = new HashMap();
    private final Map<Key, EngineJob<?>> onlyCacheJobs = new HashMap();

    private Map<Key, EngineJob<?>> getJobMap(boolean z) {
        return z ? this.onlyCacheJobs : this.jobs;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EngineJob a(Key key, boolean z) {
        return getJobMap(z).get(key);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(Key key, EngineJob engineJob) {
        getJobMap(engineJob.j()).put(key, engineJob);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(Key key, EngineJob engineJob) {
        Map<Key, EngineJob<?>> jobMap = getJobMap(engineJob.j());
        if (engineJob.equals(jobMap.get(key))) {
            jobMap.remove(key);
        }
    }
}
