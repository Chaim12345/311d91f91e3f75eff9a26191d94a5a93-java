package org.apache.http.impl.client;

import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes3.dex */
public final class FutureRequestExecutionMetrics {
    private final AtomicLong activeConnections = new AtomicLong();
    private final AtomicLong scheduledConnections = new AtomicLong();
    private final DurationCounter successfulConnections = new DurationCounter();
    private final DurationCounter failedConnections = new DurationCounter();
    private final DurationCounter requests = new DurationCounter();
    private final DurationCounter tasks = new DurationCounter();

    /* loaded from: classes3.dex */
    static class DurationCounter {
        private final AtomicLong count = new AtomicLong(0);
        private final AtomicLong cumulativeDuration = new AtomicLong(0);

        DurationCounter() {
        }

        public long averageDuration() {
            long j2 = this.count.get();
            if (j2 > 0) {
                return this.cumulativeDuration.get() / j2;
            }
            return 0L;
        }

        public long count() {
            return this.count.get();
        }

        public void increment(long j2) {
            this.count.incrementAndGet();
            this.cumulativeDuration.addAndGet(System.currentTimeMillis() - j2);
        }

        public String toString() {
            return "[count=" + count() + ", averageDuration=" + averageDuration() + "]";
        }
    }

    public long getActiveConnectionCount() {
        return this.activeConnections.get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AtomicLong getActiveConnections() {
        return this.activeConnections;
    }

    public long getFailedConnectionAverageDuration() {
        return this.failedConnections.averageDuration();
    }

    public long getFailedConnectionCount() {
        return this.failedConnections.count();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DurationCounter getFailedConnections() {
        return this.failedConnections;
    }

    public long getRequestAverageDuration() {
        return this.requests.averageDuration();
    }

    public long getRequestCount() {
        return this.requests.count();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DurationCounter getRequests() {
        return this.requests;
    }

    public long getScheduledConnectionCount() {
        return this.scheduledConnections.get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AtomicLong getScheduledConnections() {
        return this.scheduledConnections;
    }

    public long getSuccessfulConnectionAverageDuration() {
        return this.successfulConnections.averageDuration();
    }

    public long getSuccessfulConnectionCount() {
        return this.successfulConnections.count();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DurationCounter getSuccessfulConnections() {
        return this.successfulConnections;
    }

    public long getTaskAverageDuration() {
        return this.tasks.averageDuration();
    }

    public long getTaskCount() {
        return this.tasks.count();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DurationCounter getTasks() {
        return this.tasks;
    }

    public String toString() {
        return "[activeConnections=" + this.activeConnections + ", scheduledConnections=" + this.scheduledConnections + ", successfulConnections=" + this.successfulConnections + ", failedConnections=" + this.failedConnections + ", requests=" + this.requests + ", tasks=" + this.tasks + "]";
    }
}
