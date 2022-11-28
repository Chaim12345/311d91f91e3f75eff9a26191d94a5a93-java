package com.fasterxml.jackson.core.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes.dex */
class ThreadLocalBufferManager {
    private final Object RELEASE_LOCK = new Object();
    private final Map<SoftReference<BufferRecycler>, Boolean> _trackedRecyclers = new ConcurrentHashMap();
    private final ReferenceQueue<BufferRecycler> _refQueue = new ReferenceQueue<>();

    /* loaded from: classes.dex */
    private static final class ThreadLocalBufferManagerHolder {

        /* renamed from: a  reason: collision with root package name */
        static final ThreadLocalBufferManager f5248a = new ThreadLocalBufferManager();

        private ThreadLocalBufferManagerHolder() {
        }
    }

    ThreadLocalBufferManager() {
    }

    public static ThreadLocalBufferManager instance() {
        return ThreadLocalBufferManagerHolder.f5248a;
    }

    private void removeSoftRefsClearedByGc() {
        while (true) {
            SoftReference softReference = (SoftReference) this._refQueue.poll();
            if (softReference == null) {
                return;
            }
            this._trackedRecyclers.remove(softReference);
        }
    }

    public int releaseBuffers() {
        int i2;
        synchronized (this.RELEASE_LOCK) {
            i2 = 0;
            removeSoftRefsClearedByGc();
            for (SoftReference<BufferRecycler> softReference : this._trackedRecyclers.keySet()) {
                softReference.clear();
                i2++;
            }
            this._trackedRecyclers.clear();
        }
        return i2;
    }

    public SoftReference<BufferRecycler> wrapAndTrack(BufferRecycler bufferRecycler) {
        SoftReference<BufferRecycler> softReference = new SoftReference<>(bufferRecycler, this._refQueue);
        this._trackedRecyclers.put(softReference, Boolean.TRUE);
        removeSoftRefsClearedByGc();
        return softReference;
    }
}
