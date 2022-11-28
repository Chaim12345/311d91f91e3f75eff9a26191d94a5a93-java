package androidx.room;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import java.util.concurrent.Callable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class InvalidationLiveDataContainer {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    final Set f3865a = Collections.newSetFromMap(new IdentityHashMap());
    private final RoomDatabase mDatabase;

    /* JADX INFO: Access modifiers changed from: package-private */
    public InvalidationLiveDataContainer(RoomDatabase roomDatabase) {
        this.mDatabase = roomDatabase;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LiveData a(String[] strArr, boolean z, Callable callable) {
        return new RoomTrackingLiveData(this.mDatabase, this, z, callable, strArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(LiveData liveData) {
        this.f3865a.add(liveData);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(LiveData liveData) {
        this.f3865a.remove(liveData);
    }
}
