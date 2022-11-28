package androidx.room;

import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.sqlite.db.SupportSQLiteProgram;
import androidx.sqlite.db.SupportSQLiteQuery;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class RoomSQLiteQuery implements SupportSQLiteQuery, SupportSQLiteProgram {
    private static final int BLOB = 5;
    private static final int DOUBLE = 3;
    private static final int LONG = 2;
    private static final int NULL = 1;
    private static final int STRING = 4;
    @VisibleForTesting

    /* renamed from: g  reason: collision with root package name */
    static final TreeMap f3935g = new TreeMap();
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    final long[] f3936a;
    @VisibleForTesting

    /* renamed from: b  reason: collision with root package name */
    final double[] f3937b;
    @VisibleForTesting

    /* renamed from: c  reason: collision with root package name */
    final String[] f3938c;
    @VisibleForTesting

    /* renamed from: d  reason: collision with root package name */
    final byte[][] f3939d;
    @VisibleForTesting

    /* renamed from: e  reason: collision with root package name */
    final int f3940e;
    @VisibleForTesting

    /* renamed from: f  reason: collision with root package name */
    int f3941f;
    private final int[] mBindingTypes;
    private volatile String mQuery;

    private RoomSQLiteQuery(int i2) {
        this.f3940e = i2;
        int i3 = i2 + 1;
        this.mBindingTypes = new int[i3];
        this.f3936a = new long[i3];
        this.f3937b = new double[i3];
        this.f3938c = new String[i3];
        this.f3939d = new byte[i3];
    }

    public static RoomSQLiteQuery acquire(String str, int i2) {
        TreeMap treeMap = f3935g;
        synchronized (treeMap) {
            Map.Entry ceilingEntry = treeMap.ceilingEntry(Integer.valueOf(i2));
            if (ceilingEntry == null) {
                RoomSQLiteQuery roomSQLiteQuery = new RoomSQLiteQuery(i2);
                roomSQLiteQuery.a(str, i2);
                return roomSQLiteQuery;
            }
            treeMap.remove(ceilingEntry.getKey());
            RoomSQLiteQuery roomSQLiteQuery2 = (RoomSQLiteQuery) ceilingEntry.getValue();
            roomSQLiteQuery2.a(str, i2);
            return roomSQLiteQuery2;
        }
    }

    public static RoomSQLiteQuery copyFrom(SupportSQLiteQuery supportSQLiteQuery) {
        RoomSQLiteQuery acquire = acquire(supportSQLiteQuery.getSql(), supportSQLiteQuery.getArgCount());
        supportSQLiteQuery.bindTo(new SupportSQLiteProgram() { // from class: androidx.room.RoomSQLiteQuery.1
            @Override // androidx.sqlite.db.SupportSQLiteProgram
            public void bindBlob(int i2, byte[] bArr) {
                RoomSQLiteQuery.this.bindBlob(i2, bArr);
            }

            @Override // androidx.sqlite.db.SupportSQLiteProgram
            public void bindDouble(int i2, double d2) {
                RoomSQLiteQuery.this.bindDouble(i2, d2);
            }

            @Override // androidx.sqlite.db.SupportSQLiteProgram
            public void bindLong(int i2, long j2) {
                RoomSQLiteQuery.this.bindLong(i2, j2);
            }

            @Override // androidx.sqlite.db.SupportSQLiteProgram
            public void bindNull(int i2) {
                RoomSQLiteQuery.this.bindNull(i2);
            }

            @Override // androidx.sqlite.db.SupportSQLiteProgram
            public void bindString(int i2, String str) {
                RoomSQLiteQuery.this.bindString(i2, str);
            }

            @Override // androidx.sqlite.db.SupportSQLiteProgram
            public void clearBindings() {
                RoomSQLiteQuery.this.clearBindings();
            }

            @Override // java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }
        });
        return acquire;
    }

    private static void prunePoolLocked() {
        TreeMap treeMap = f3935g;
        if (treeMap.size() <= 15) {
            return;
        }
        int size = treeMap.size() - 10;
        Iterator it = treeMap.descendingKeySet().iterator();
        while (true) {
            int i2 = size - 1;
            if (size <= 0) {
                return;
            }
            it.next();
            it.remove();
            size = i2;
        }
    }

    void a(String str, int i2) {
        this.mQuery = str;
        this.f3941f = i2;
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void bindBlob(int i2, byte[] bArr) {
        this.mBindingTypes[i2] = 5;
        this.f3939d[i2] = bArr;
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void bindDouble(int i2, double d2) {
        this.mBindingTypes[i2] = 3;
        this.f3937b[i2] = d2;
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void bindLong(int i2, long j2) {
        this.mBindingTypes[i2] = 2;
        this.f3936a[i2] = j2;
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void bindNull(int i2) {
        this.mBindingTypes[i2] = 1;
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void bindString(int i2, String str) {
        this.mBindingTypes[i2] = 4;
        this.f3938c[i2] = str;
    }

    @Override // androidx.sqlite.db.SupportSQLiteQuery
    public void bindTo(SupportSQLiteProgram supportSQLiteProgram) {
        for (int i2 = 1; i2 <= this.f3941f; i2++) {
            int i3 = this.mBindingTypes[i2];
            if (i3 == 1) {
                supportSQLiteProgram.bindNull(i2);
            } else if (i3 == 2) {
                supportSQLiteProgram.bindLong(i2, this.f3936a[i2]);
            } else if (i3 == 3) {
                supportSQLiteProgram.bindDouble(i2, this.f3937b[i2]);
            } else if (i3 == 4) {
                supportSQLiteProgram.bindString(i2, this.f3938c[i2]);
            } else if (i3 == 5) {
                supportSQLiteProgram.bindBlob(i2, this.f3939d[i2]);
            }
        }
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void clearBindings() {
        Arrays.fill(this.mBindingTypes, 1);
        Arrays.fill(this.f3938c, (Object) null);
        Arrays.fill(this.f3939d, (Object) null);
        this.mQuery = null;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public void copyArgumentsFrom(RoomSQLiteQuery roomSQLiteQuery) {
        int argCount = roomSQLiteQuery.getArgCount() + 1;
        System.arraycopy(roomSQLiteQuery.mBindingTypes, 0, this.mBindingTypes, 0, argCount);
        System.arraycopy(roomSQLiteQuery.f3936a, 0, this.f3936a, 0, argCount);
        System.arraycopy(roomSQLiteQuery.f3938c, 0, this.f3938c, 0, argCount);
        System.arraycopy(roomSQLiteQuery.f3939d, 0, this.f3939d, 0, argCount);
        System.arraycopy(roomSQLiteQuery.f3937b, 0, this.f3937b, 0, argCount);
    }

    @Override // androidx.sqlite.db.SupportSQLiteQuery
    public int getArgCount() {
        return this.f3941f;
    }

    @Override // androidx.sqlite.db.SupportSQLiteQuery
    public String getSql() {
        return this.mQuery;
    }

    public void release() {
        TreeMap treeMap = f3935g;
        synchronized (treeMap) {
            treeMap.put(Integer.valueOf(this.f3940e), this);
            prunePoolLocked();
        }
    }
}
