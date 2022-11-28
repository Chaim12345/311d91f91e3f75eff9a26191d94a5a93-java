package com.google.firebase.messaging;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class TopicsSubscriber {
    private static final long MAX_DELAY_SEC = TimeUnit.HOURS.toSeconds(8);
    private static final long MIN_DELAY_SEC = 30;
    private static final long RPC_TIMEOUT_SEC = 30;
    private final Context context;
    private final FirebaseMessaging firebaseMessaging;
    private final Metadata metadata;
    private final GmsRpc rpc;
    private final TopicsStore store;
    private final ScheduledExecutorService syncExecutor;
    @GuardedBy("pendingOperations")
    private final Map<String, ArrayDeque<TaskCompletionSource<Void>>> pendingOperations = new ArrayMap();
    @GuardedBy("this")
    private boolean syncScheduledOrRunning = false;

    private TopicsSubscriber(FirebaseMessaging firebaseMessaging, Metadata metadata, TopicsStore topicsStore, GmsRpc gmsRpc, Context context, @NonNull ScheduledExecutorService scheduledExecutorService) {
        this.firebaseMessaging = firebaseMessaging;
        this.metadata = metadata;
        this.store = topicsStore;
        this.rpc = gmsRpc;
        this.context = context;
        this.syncExecutor = scheduledExecutorService;
    }

    private void addToPendingOperations(TopicOperation topicOperation, TaskCompletionSource<Void> taskCompletionSource) {
        ArrayDeque<TaskCompletionSource<Void>> arrayDeque;
        synchronized (this.pendingOperations) {
            String serialize = topicOperation.serialize();
            if (this.pendingOperations.containsKey(serialize)) {
                arrayDeque = this.pendingOperations.get(serialize);
            } else {
                ArrayDeque<TaskCompletionSource<Void>> arrayDeque2 = new ArrayDeque<>();
                this.pendingOperations.put(serialize, arrayDeque2);
                arrayDeque = arrayDeque2;
            }
            arrayDeque.add(taskCompletionSource);
        }
    }

    @WorkerThread
    private static <T> void awaitTask(Task<T> task) {
        try {
            Tasks.await(task, 30L, TimeUnit.SECONDS);
        } catch (InterruptedException e2) {
            e = e2;
            throw new IOException("SERVICE_NOT_AVAILABLE", e);
        } catch (ExecutionException e3) {
            Throwable cause = e3.getCause();
            if (cause instanceof IOException) {
                throw ((IOException) cause);
            }
            if (!(cause instanceof RuntimeException)) {
                throw new IOException(e3);
            }
            throw ((RuntimeException) cause);
        } catch (TimeoutException e4) {
            e = e4;
            throw new IOException("SERVICE_NOT_AVAILABLE", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public static Task b(final FirebaseMessaging firebaseMessaging, final Metadata metadata, final GmsRpc gmsRpc, final Context context, @NonNull final ScheduledExecutorService scheduledExecutorService) {
        return Tasks.call(scheduledExecutorService, new Callable() { // from class: com.google.firebase.messaging.c0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                TopicsSubscriber lambda$createInstance$0;
                lambda$createInstance$0 = TopicsSubscriber.lambda$createInstance$0(context, scheduledExecutorService, firebaseMessaging, metadata, gmsRpc);
                return lambda$createInstance$0;
            }
        });
    }

    @WorkerThread
    private void blockingSubscribeToTopic(String str) {
        awaitTask(this.rpc.e(this.firebaseMessaging.n(), str));
    }

    @WorkerThread
    private void blockingUnsubscribeFromTopic(String str) {
        awaitTask(this.rpc.f(this.firebaseMessaging.n(), str));
    }

    static boolean d() {
        return Log.isLoggable(Constants.TAG, 3) || (Build.VERSION.SDK_INT == 23 && Log.isLoggable(Constants.TAG, 3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ TopicsSubscriber lambda$createInstance$0(Context context, ScheduledExecutorService scheduledExecutorService, FirebaseMessaging firebaseMessaging, Metadata metadata, GmsRpc gmsRpc) {
        return new TopicsSubscriber(firebaseMessaging, metadata, TopicsStore.getInstance(context, scheduledExecutorService), gmsRpc, context, scheduledExecutorService);
    }

    private void markCompletePendingOperation(TopicOperation topicOperation) {
        synchronized (this.pendingOperations) {
            String serialize = topicOperation.serialize();
            if (this.pendingOperations.containsKey(serialize)) {
                ArrayDeque<TaskCompletionSource<Void>> arrayDeque = this.pendingOperations.get(serialize);
                TaskCompletionSource<Void> poll = arrayDeque.poll();
                if (poll != null) {
                    poll.setResult(null);
                }
                if (arrayDeque.isEmpty()) {
                    this.pendingOperations.remove(serialize);
                }
            }
        }
    }

    private void startSync() {
        if (e()) {
            return;
        }
        m(0L);
    }

    boolean c() {
        return this.store.b() != null;
    }

    synchronized boolean e() {
        return this.syncScheduledOrRunning;
    }

    @WorkerThread
    boolean f(TopicOperation topicOperation) {
        String str;
        StringBuilder sb;
        try {
            String operation = topicOperation.getOperation();
            char c2 = 65535;
            int hashCode = operation.hashCode();
            if (hashCode != 83) {
                if (hashCode == 85 && operation.equals("U")) {
                    c2 = 1;
                }
            } else if (operation.equals(ExifInterface.LATITUDE_SOUTH)) {
                c2 = 0;
            }
            if (c2 == 0) {
                blockingSubscribeToTopic(topicOperation.getTopic());
                if (d()) {
                    sb = new StringBuilder();
                    sb.append("Subscribe to topic: ");
                    sb.append(topicOperation.getTopic());
                    sb.append(" succeeded.");
                }
            } else if (c2 == 1) {
                blockingUnsubscribeFromTopic(topicOperation.getTopic());
                if (d()) {
                    sb = new StringBuilder();
                    sb.append("Unsubscribe from topic: ");
                    sb.append(topicOperation.getTopic());
                    sb.append(" succeeded.");
                }
            } else if (d()) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Unknown topic operation");
                sb2.append(topicOperation);
                sb2.append(".");
            }
            return true;
        } catch (IOException e2) {
            if ("SERVICE_NOT_AVAILABLE".equals(e2.getMessage()) || "INTERNAL_SERVER_ERROR".equals(e2.getMessage())) {
                str = "Topic operation failed: " + e2.getMessage() + ". Will retry Topic operation.";
            } else if (e2.getMessage() != null) {
                throw e2;
            } else {
                str = "Topic operation failed without exception message. Will retry Topic operation.";
            }
            Log.e(Constants.TAG, str);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(Runnable runnable, long j2) {
        this.syncExecutor.schedule(runnable, j2, TimeUnit.SECONDS);
    }

    @VisibleForTesting
    Task h(TopicOperation topicOperation) {
        this.store.a(topicOperation);
        TaskCompletionSource<Void> taskCompletionSource = new TaskCompletionSource<>();
        addToPendingOperations(topicOperation, taskCompletionSource);
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void i(boolean z) {
        this.syncScheduledOrRunning = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j() {
        if (c()) {
            startSync();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Task k(String str) {
        Task h2 = h(TopicOperation.subscribe(str));
        j();
        return h2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public boolean l() {
        while (true) {
            synchronized (this) {
                TopicOperation b2 = this.store.b();
                if (b2 == null) {
                    d();
                    return true;
                } else if (!f(b2)) {
                    return false;
                } else {
                    this.store.c(b2);
                    markCompletePendingOperation(b2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m(long j2) {
        g(new TopicsSyncTask(this, this.context, this.metadata, Math.min(Math.max(30L, 2 * j2), MAX_DELAY_SEC)), j2);
        i(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Task n(String str) {
        Task h2 = h(TopicOperation.unsubscribe(str));
        j();
        return h2;
    }
}
