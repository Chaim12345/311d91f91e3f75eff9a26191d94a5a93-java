package androidx.room;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.LiveData;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
/* loaded from: classes.dex */
public class InvalidationTracker {
    private static final String CREATE_TRACKING_TABLE_SQL = "CREATE TEMP TABLE room_table_modification_log(table_id INTEGER PRIMARY KEY, invalidated INTEGER NOT NULL DEFAULT 0)";
    private static final String INVALIDATED_COLUMN_NAME = "invalidated";
    private static final String TABLE_ID_COLUMN_NAME = "table_id";
    private static final String[] TRIGGERS = {"UPDATE", "DELETE", "INSERT"};
    private static final String UPDATE_TABLE_NAME = "room_table_modification_log";
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    final HashMap f3866a;

    /* renamed from: b  reason: collision with root package name */
    final String[] f3867b;

    /* renamed from: c  reason: collision with root package name */
    final RoomDatabase f3868c;

    /* renamed from: d  reason: collision with root package name */
    AtomicBoolean f3869d;

    /* renamed from: e  reason: collision with root package name */
    volatile SupportSQLiteStatement f3870e;
    @SuppressLint({"RestrictedApi"})
    @VisibleForTesting

    /* renamed from: f  reason: collision with root package name */
    final SafeIterableMap f3871f;
    @VisibleForTesting

    /* renamed from: g  reason: collision with root package name */
    Runnable f3872g;
    private volatile boolean mInitialized;
    private final InvalidationLiveDataContainer mInvalidationLiveDataContainer;
    private MultiInstanceInvalidationClient mMultiInstanceInvalidationClient;
    private ObservedTableTracker mObservedTableTracker;
    @NonNull
    private Map<String, Set<String>> mViewTables;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class ObservedTableTracker {

        /* renamed from: a  reason: collision with root package name */
        final long[] f3874a;

        /* renamed from: b  reason: collision with root package name */
        final boolean[] f3875b;

        /* renamed from: c  reason: collision with root package name */
        final int[] f3876c;

        /* renamed from: d  reason: collision with root package name */
        boolean f3877d;

        /* renamed from: e  reason: collision with root package name */
        boolean f3878e;

        ObservedTableTracker(int i2) {
            long[] jArr = new long[i2];
            this.f3874a = jArr;
            boolean[] zArr = new boolean[i2];
            this.f3875b = zArr;
            this.f3876c = new int[i2];
            Arrays.fill(jArr, 0L);
            Arrays.fill(zArr, false);
        }

        @Nullable
        int[] a() {
            synchronized (this) {
                if (this.f3877d && !this.f3878e) {
                    int length = this.f3874a.length;
                    int i2 = 0;
                    while (true) {
                        int i3 = 1;
                        if (i2 >= length) {
                            this.f3878e = true;
                            this.f3877d = false;
                            return this.f3876c;
                        }
                        boolean z = this.f3874a[i2] > 0;
                        boolean[] zArr = this.f3875b;
                        if (z != zArr[i2]) {
                            int[] iArr = this.f3876c;
                            if (!z) {
                                i3 = 2;
                            }
                            iArr[i2] = i3;
                        } else {
                            this.f3876c[i2] = 0;
                        }
                        zArr[i2] = z;
                        i2++;
                    }
                }
                return null;
            }
        }

        boolean b(int... iArr) {
            boolean z;
            synchronized (this) {
                z = false;
                for (int i2 : iArr) {
                    long[] jArr = this.f3874a;
                    long j2 = jArr[i2];
                    jArr[i2] = 1 + j2;
                    if (j2 == 0) {
                        this.f3877d = true;
                        z = true;
                    }
                }
            }
            return z;
        }

        boolean c(int... iArr) {
            boolean z;
            synchronized (this) {
                z = false;
                for (int i2 : iArr) {
                    long[] jArr = this.f3874a;
                    long j2 = jArr[i2];
                    jArr[i2] = j2 - 1;
                    if (j2 == 1) {
                        this.f3877d = true;
                        z = true;
                    }
                }
            }
            return z;
        }

        void d() {
            synchronized (this) {
                this.f3878e = false;
            }
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Observer {

        /* renamed from: a  reason: collision with root package name */
        final String[] f3879a;

        public Observer(@NonNull String[] strArr) {
            this.f3879a = (String[]) Arrays.copyOf(strArr, strArr.length);
        }

        boolean a() {
            return false;
        }

        public abstract void onInvalidated(@NonNull Set<String> set);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class ObserverWrapper {

        /* renamed from: a  reason: collision with root package name */
        final int[] f3880a;

        /* renamed from: b  reason: collision with root package name */
        final Observer f3881b;
        private final Set<String> mSingleTableSet;
        private final String[] mTableNames;

        ObserverWrapper(Observer observer, int[] iArr, String[] strArr) {
            Set<String> set;
            this.f3881b = observer;
            this.f3880a = iArr;
            this.mTableNames = strArr;
            if (iArr.length == 1) {
                HashSet hashSet = new HashSet();
                hashSet.add(strArr[0]);
                set = Collections.unmodifiableSet(hashSet);
            } else {
                set = null;
            }
            this.mSingleTableSet = set;
        }

        void a(Set set) {
            int length = this.f3880a.length;
            Set<String> set2 = null;
            for (int i2 = 0; i2 < length; i2++) {
                if (set.contains(Integer.valueOf(this.f3880a[i2]))) {
                    if (length == 1) {
                        set2 = this.mSingleTableSet;
                    } else {
                        if (set2 == null) {
                            set2 = new HashSet<>(length);
                        }
                        set2.add(this.mTableNames[i2]);
                    }
                }
            }
            if (set2 != null) {
                this.f3881b.onInvalidated(set2);
            }
        }

        void b(String[] strArr) {
            Set<String> set = null;
            if (this.mTableNames.length == 1) {
                int length = strArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (strArr[i2].equalsIgnoreCase(this.mTableNames[0])) {
                        set = this.mSingleTableSet;
                        break;
                    } else {
                        i2++;
                    }
                }
            } else {
                HashSet hashSet = new HashSet();
                for (String str : strArr) {
                    String[] strArr2 = this.mTableNames;
                    int length2 = strArr2.length;
                    int i3 = 0;
                    while (true) {
                        if (i3 < length2) {
                            String str2 = strArr2[i3];
                            if (str2.equalsIgnoreCase(str)) {
                                hashSet.add(str2);
                                break;
                            }
                            i3++;
                        }
                    }
                }
                if (hashSet.size() > 0) {
                    set = hashSet;
                }
            }
            if (set != null) {
                this.f3881b.onInvalidated(set);
            }
        }
    }

    /* loaded from: classes.dex */
    static class WeakObserver extends Observer {

        /* renamed from: b  reason: collision with root package name */
        final InvalidationTracker f3882b;

        /* renamed from: c  reason: collision with root package name */
        final WeakReference f3883c;

        WeakObserver(InvalidationTracker invalidationTracker, Observer observer) {
            super(observer.f3879a);
            this.f3882b = invalidationTracker;
            this.f3883c = new WeakReference(observer);
        }

        @Override // androidx.room.InvalidationTracker.Observer
        public void onInvalidated(@NonNull Set<String> set) {
            Observer observer = (Observer) this.f3883c.get();
            if (observer == null) {
                this.f3882b.removeObserver(this);
            } else {
                observer.onInvalidated(set);
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public InvalidationTracker(RoomDatabase roomDatabase, Map<String, String> map, Map<String, Set<String>> map2, String... strArr) {
        this.f3869d = new AtomicBoolean(false);
        this.mInitialized = false;
        this.f3871f = new SafeIterableMap();
        this.f3872g = new Runnable() { // from class: androidx.room.InvalidationTracker.1
            private Set<Integer> checkUpdatedTable() {
                HashSet hashSet = new HashSet();
                Cursor query = InvalidationTracker.this.f3868c.query(new SimpleSQLiteQuery("SELECT * FROM room_table_modification_log WHERE invalidated = 1;"));
                while (query.moveToNext()) {
                    try {
                        hashSet.add(Integer.valueOf(query.getInt(0)));
                    } catch (Throwable th) {
                        query.close();
                        throw th;
                    }
                }
                query.close();
                if (!hashSet.isEmpty()) {
                    InvalidationTracker.this.f3870e.executeUpdateDelete();
                }
                return hashSet;
            }

            @Override // java.lang.Runnable
            public void run() {
                Lock d2 = InvalidationTracker.this.f3868c.d();
                Set<Integer> set = null;
                try {
                    try {
                        d2.lock();
                    } finally {
                        d2.unlock();
                    }
                } catch (SQLiteException | IllegalStateException e2) {
                    Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e2);
                }
                if (InvalidationTracker.this.a()) {
                    if (InvalidationTracker.this.f3869d.compareAndSet(true, false)) {
                        if (InvalidationTracker.this.f3868c.inTransaction()) {
                            return;
                        }
                        RoomDatabase roomDatabase2 = InvalidationTracker.this.f3868c;
                        if (roomDatabase2.f3910b) {
                            SupportSQLiteDatabase writableDatabase = roomDatabase2.getOpenHelper().getWritableDatabase();
                            writableDatabase.beginTransaction();
                            try {
                                set = checkUpdatedTable();
                                writableDatabase.setTransactionSuccessful();
                                writableDatabase.endTransaction();
                            } catch (Throwable th) {
                                writableDatabase.endTransaction();
                                throw th;
                            }
                        } else {
                            set = checkUpdatedTable();
                        }
                        if (set == null || set.isEmpty()) {
                            return;
                        }
                        synchronized (InvalidationTracker.this.f3871f) {
                            Iterator it = InvalidationTracker.this.f3871f.iterator();
                            while (it.hasNext()) {
                                ((ObserverWrapper) ((Map.Entry) it.next()).getValue()).a(set);
                            }
                        }
                    }
                }
            }
        };
        this.f3868c = roomDatabase;
        this.mObservedTableTracker = new ObservedTableTracker(strArr.length);
        this.f3866a = new HashMap();
        this.mViewTables = map2;
        this.mInvalidationLiveDataContainer = new InvalidationLiveDataContainer(roomDatabase);
        int length = strArr.length;
        this.f3867b = new String[length];
        for (int i2 = 0; i2 < length; i2++) {
            String str = strArr[i2];
            Locale locale = Locale.US;
            String lowerCase = str.toLowerCase(locale);
            this.f3866a.put(lowerCase, Integer.valueOf(i2));
            String str2 = map.get(strArr[i2]);
            if (str2 != null) {
                this.f3867b[i2] = str2.toLowerCase(locale);
            } else {
                this.f3867b[i2] = lowerCase;
            }
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            Locale locale2 = Locale.US;
            String lowerCase2 = entry.getValue().toLowerCase(locale2);
            if (this.f3866a.containsKey(lowerCase2)) {
                String lowerCase3 = entry.getKey().toLowerCase(locale2);
                HashMap hashMap = this.f3866a;
                hashMap.put(lowerCase3, hashMap.get(lowerCase2));
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public InvalidationTracker(RoomDatabase roomDatabase, String... strArr) {
        this(roomDatabase, new HashMap(), Collections.emptyMap(), strArr);
    }

    private static void appendTriggerName(StringBuilder sb, String str, String str2) {
        sb.append("`");
        sb.append("room_table_modification_trigger_");
        sb.append(str);
        sb.append("_");
        sb.append(str2);
        sb.append("`");
    }

    private String[] resolveViews(String[] strArr) {
        HashSet hashSet = new HashSet();
        for (String str : strArr) {
            String lowerCase = str.toLowerCase(Locale.US);
            if (this.mViewTables.containsKey(lowerCase)) {
                hashSet.addAll(this.mViewTables.get(lowerCase));
            } else {
                hashSet.add(str);
            }
        }
        return (String[]) hashSet.toArray(new String[hashSet.size()]);
    }

    private void startTrackingTable(SupportSQLiteDatabase supportSQLiteDatabase, int i2) {
        String[] strArr;
        supportSQLiteDatabase.execSQL("INSERT OR IGNORE INTO room_table_modification_log VALUES(" + i2 + ", 0)");
        String str = this.f3867b[i2];
        StringBuilder sb = new StringBuilder();
        for (String str2 : TRIGGERS) {
            sb.setLength(0);
            sb.append("CREATE TEMP TRIGGER IF NOT EXISTS ");
            appendTriggerName(sb, str, str2);
            sb.append(" AFTER ");
            sb.append(str2);
            sb.append(" ON `");
            sb.append(str);
            sb.append("` BEGIN UPDATE ");
            sb.append(UPDATE_TABLE_NAME);
            sb.append(" SET ");
            sb.append(INVALIDATED_COLUMN_NAME);
            sb.append(" = 1");
            sb.append(" WHERE ");
            sb.append(TABLE_ID_COLUMN_NAME);
            sb.append(" = ");
            sb.append(i2);
            sb.append(" AND ");
            sb.append(INVALIDATED_COLUMN_NAME);
            sb.append(" = 0");
            sb.append("; END");
            supportSQLiteDatabase.execSQL(sb.toString());
        }
    }

    private void stopTrackingTable(SupportSQLiteDatabase supportSQLiteDatabase, int i2) {
        String[] strArr;
        String str = this.f3867b[i2];
        StringBuilder sb = new StringBuilder();
        for (String str2 : TRIGGERS) {
            sb.setLength(0);
            sb.append("DROP TRIGGER IF EXISTS ");
            appendTriggerName(sb, str, str2);
            supportSQLiteDatabase.execSQL(sb.toString());
        }
    }

    private String[] validateAndResolveTableNames(String[] strArr) {
        String[] resolveViews = resolveViews(strArr);
        for (String str : resolveViews) {
            if (!this.f3866a.containsKey(str.toLowerCase(Locale.US))) {
                throw new IllegalArgumentException("There is no table with name " + str);
            }
        }
        return resolveViews;
    }

    boolean a() {
        if (this.f3868c.isOpen()) {
            if (!this.mInitialized) {
                this.f3868c.getOpenHelper().getWritableDatabase();
            }
            if (this.mInitialized) {
                return true;
            }
            Log.e("ROOM", "database is not initialized even though it is open");
            return false;
        }
        return false;
    }

    @SuppressLint({"RestrictedApi"})
    @WorkerThread
    public void addObserver(@NonNull Observer observer) {
        ObserverWrapper observerWrapper;
        String[] resolveViews = resolveViews(observer.f3879a);
        int[] iArr = new int[resolveViews.length];
        int length = resolveViews.length;
        for (int i2 = 0; i2 < length; i2++) {
            Integer num = (Integer) this.f3866a.get(resolveViews[i2].toLowerCase(Locale.US));
            if (num == null) {
                throw new IllegalArgumentException("There is no table with name " + resolveViews[i2]);
            }
            iArr[i2] = num.intValue();
        }
        ObserverWrapper observerWrapper2 = new ObserverWrapper(observer, iArr, resolveViews);
        synchronized (this.f3871f) {
            observerWrapper = (ObserverWrapper) this.f3871f.putIfAbsent(observer, observerWrapper2);
        }
        if (observerWrapper == null && this.mObservedTableTracker.b(iArr)) {
            e();
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void addWeakObserver(Observer observer) {
        addObserver(new WeakObserver(this, observer));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(SupportSQLiteDatabase supportSQLiteDatabase) {
        synchronized (this) {
            if (this.mInitialized) {
                Log.e("ROOM", "Invalidation tracker is initialized twice :/.");
                return;
            }
            supportSQLiteDatabase.execSQL("PRAGMA temp_store = MEMORY;");
            supportSQLiteDatabase.execSQL("PRAGMA recursive_triggers='ON';");
            supportSQLiteDatabase.execSQL(CREATE_TRACKING_TABLE_SQL);
            f(supportSQLiteDatabase);
            this.f3870e = supportSQLiteDatabase.compileStatement("UPDATE room_table_modification_log SET invalidated = 0 WHERE invalidated = 1 ");
            this.mInitialized = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(Context context, String str) {
        this.mMultiInstanceInvalidationClient = new MultiInstanceInvalidationClient(context, str, this, this.f3868c.getQueryExecutor());
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public <T> LiveData<T> createLiveData(String[] strArr, Callable<T> callable) {
        return createLiveData(strArr, false, callable);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public <T> LiveData<T> createLiveData(String[] strArr, boolean z, Callable<T> callable) {
        return this.mInvalidationLiveDataContainer.a(validateAndResolveTableNames(strArr), z, callable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d() {
        MultiInstanceInvalidationClient multiInstanceInvalidationClient = this.mMultiInstanceInvalidationClient;
        if (multiInstanceInvalidationClient != null) {
            multiInstanceInvalidationClient.a();
            this.mMultiInstanceInvalidationClient = null;
        }
    }

    void e() {
        if (this.f3868c.isOpen()) {
            f(this.f3868c.getOpenHelper().getWritableDatabase());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(SupportSQLiteDatabase supportSQLiteDatabase) {
        if (supportSQLiteDatabase.inTransaction()) {
            return;
        }
        while (true) {
            try {
                Lock d2 = this.f3868c.d();
                d2.lock();
                try {
                    int[] a2 = this.mObservedTableTracker.a();
                    if (a2 == null) {
                        return;
                    }
                    int length = a2.length;
                    supportSQLiteDatabase.beginTransaction();
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = a2[i2];
                        if (i3 == 1) {
                            startTrackingTable(supportSQLiteDatabase, i2);
                        } else if (i3 == 2) {
                            stopTrackingTable(supportSQLiteDatabase, i2);
                        }
                    }
                    supportSQLiteDatabase.setTransactionSuccessful();
                    supportSQLiteDatabase.endTransaction();
                    this.mObservedTableTracker.d();
                } finally {
                    d2.unlock();
                }
            } catch (SQLiteException | IllegalStateException e2) {
                Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e2);
                return;
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    @VisibleForTesting(otherwise = 3)
    public void notifyObserversByTableNames(String... strArr) {
        synchronized (this.f3871f) {
            Iterator it = this.f3871f.iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (!((Observer) entry.getKey()).a()) {
                    ((ObserverWrapper) entry.getValue()).b(strArr);
                }
            }
        }
    }

    public void refreshVersionsAsync() {
        if (this.f3869d.compareAndSet(false, true)) {
            this.f3868c.getQueryExecutor().execute(this.f3872g);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    @WorkerThread
    public void refreshVersionsSync() {
        e();
        this.f3872g.run();
    }

    @SuppressLint({"RestrictedApi"})
    @WorkerThread
    public void removeObserver(@NonNull Observer observer) {
        ObserverWrapper observerWrapper;
        synchronized (this.f3871f) {
            observerWrapper = (ObserverWrapper) this.f3871f.remove(observer);
        }
        if (observerWrapper == null || !this.mObservedTableTracker.c(observerWrapper.f3880a)) {
            return;
        }
        e();
    }
}
