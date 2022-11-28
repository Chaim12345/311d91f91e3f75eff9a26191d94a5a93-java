package com.google.firebase.messaging;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class SharedPreferencesQueue {
    @GuardedBy("internalQueue")
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    final ArrayDeque f10065a = new ArrayDeque();
    @GuardedBy("internalQueue")
    private boolean bulkOperation = false;
    private final String itemSeparator;
    private final String queueName;
    private final SharedPreferences sharedPreferences;
    private final Executor syncExecutor;

    private SharedPreferencesQueue(SharedPreferences sharedPreferences, String str, String str2, Executor executor) {
        this.sharedPreferences = sharedPreferences;
        this.queueName = str;
        this.itemSeparator = str2;
        this.syncExecutor = executor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public static SharedPreferencesQueue b(SharedPreferences sharedPreferences, String str, String str2, Executor executor) {
        SharedPreferencesQueue sharedPreferencesQueue = new SharedPreferencesQueue(sharedPreferences, str, str2, executor);
        sharedPreferencesQueue.initQueue();
        return sharedPreferencesQueue;
    }

    @GuardedBy("internalQueue")
    private String checkAndSyncState(String str) {
        checkAndSyncState(str != null);
        return str;
    }

    @GuardedBy("internalQueue")
    private boolean checkAndSyncState(boolean z) {
        if (z && !this.bulkOperation) {
            syncStateAsync();
        }
        return z;
    }

    @WorkerThread
    private void initQueue() {
        synchronized (this.f10065a) {
            this.f10065a.clear();
            String string = this.sharedPreferences.getString(this.queueName, "");
            if (!TextUtils.isEmpty(string) && string.contains(this.itemSeparator)) {
                String[] split = string.split(this.itemSeparator, -1);
                if (split.length == 0) {
                    Log.e(Constants.TAG, "Corrupted queue. Please check the queue contents and item separator provided");
                }
                for (String str : split) {
                    if (!TextUtils.isEmpty(str)) {
                        this.f10065a.add(str);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public void syncState() {
        synchronized (this.f10065a) {
            this.sharedPreferences.edit().putString(this.queueName, serialize()).commit();
        }
    }

    private void syncStateAsync() {
        this.syncExecutor.execute(new Runnable() { // from class: com.google.firebase.messaging.b0
            @Override // java.lang.Runnable
            public final void run() {
                SharedPreferencesQueue.this.syncState();
            }
        });
    }

    public boolean add(@NonNull String str) {
        boolean checkAndSyncState;
        if (TextUtils.isEmpty(str) || str.contains(this.itemSeparator)) {
            return false;
        }
        synchronized (this.f10065a) {
            checkAndSyncState = checkAndSyncState(this.f10065a.add(str));
        }
        return checkAndSyncState;
    }

    @GuardedBy("internalQueue")
    public void beginTransaction() {
        this.bulkOperation = true;
    }

    public void clear() {
        synchronized (this.f10065a) {
            this.f10065a.clear();
            checkAndSyncState(true);
        }
    }

    @GuardedBy("internalQueue")
    public void commitTransaction() {
        this.bulkOperation = false;
        syncStateAsync();
    }

    @Nullable
    public String peek() {
        String str;
        synchronized (this.f10065a) {
            str = (String) this.f10065a.peek();
        }
        return str;
    }

    public String remove() {
        String checkAndSyncState;
        synchronized (this.f10065a) {
            checkAndSyncState = checkAndSyncState((String) this.f10065a.remove());
        }
        return checkAndSyncState;
    }

    public boolean remove(@Nullable Object obj) {
        boolean checkAndSyncState;
        synchronized (this.f10065a) {
            checkAndSyncState = checkAndSyncState(this.f10065a.remove(obj));
        }
        return checkAndSyncState;
    }

    @NonNull
    @GuardedBy("internalQueue")
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        Iterator it = this.f10065a.iterator();
        while (it.hasNext()) {
            sb.append((String) it.next());
            sb.append(this.itemSeparator);
        }
        return sb.toString();
    }

    @VisibleForTesting
    public String serializeSync() {
        String serialize;
        synchronized (this.f10065a) {
            serialize = serialize();
        }
        return serialize;
    }

    public int size() {
        int size;
        synchronized (this.f10065a) {
            size = this.f10065a.size();
        }
        return size;
    }

    @NonNull
    public List<String> toList() {
        ArrayList arrayList;
        synchronized (this.f10065a) {
            arrayList = new ArrayList(this.f10065a);
        }
        return arrayList;
    }
}
