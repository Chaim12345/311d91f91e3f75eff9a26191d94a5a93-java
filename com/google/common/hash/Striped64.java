package com.google.common.hash;

import com.google.common.annotations.GwtIncompatible;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Random;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import sun.misc.Unsafe;
@GwtIncompatible
/* loaded from: classes2.dex */
abstract class Striped64 extends Number {
    private static final Unsafe UNSAFE;
    private static final long baseOffset;
    private static final long busyOffset;

    /* renamed from: d  reason: collision with root package name */
    static final ThreadLocal f9251d = new ThreadLocal();

    /* renamed from: e  reason: collision with root package name */
    static final Random f9252e = new Random();

    /* renamed from: f  reason: collision with root package name */
    static final int f9253f = Runtime.getRuntime().availableProcessors();
    @NullableDecl

    /* renamed from: a  reason: collision with root package name */
    volatile transient Cell[] f9254a;

    /* renamed from: b  reason: collision with root package name */
    volatile transient long f9255b;

    /* renamed from: c  reason: collision with root package name */
    volatile transient int f9256c;

    /* loaded from: classes2.dex */
    static final class Cell {
        private static final Unsafe UNSAFE;
        private static final long valueOffset;

        /* renamed from: a  reason: collision with root package name */
        volatile long f9257a;

        static {
            try {
                Unsafe unsafe = Striped64.getUnsafe();
                UNSAFE = unsafe;
                valueOffset = unsafe.objectFieldOffset(Cell.class.getDeclaredField("a"));
            } catch (Exception e2) {
                throw new Error(e2);
            }
        }

        Cell(long j2) {
            this.f9257a = j2;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final boolean a(long j2, long j3) {
            return UNSAFE.compareAndSwapLong(this, valueOffset, j2, j3);
        }
    }

    static {
        try {
            Unsafe unsafe = getUnsafe();
            UNSAFE = unsafe;
            baseOffset = unsafe.objectFieldOffset(Striped64.class.getDeclaredField("b"));
            busyOffset = unsafe.objectFieldOffset(Striped64.class.getDeclaredField("c"));
        } catch (Exception e2) {
            throw new Error(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Unsafe getUnsafe() {
        try {
            try {
                return Unsafe.getUnsafe();
            } catch (PrivilegedActionException e2) {
                throw new RuntimeException("Could not initialize intrinsics", e2.getCause());
            }
        } catch (SecurityException unused) {
            return (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() { // from class: com.google.common.hash.Striped64.1
                @Override // java.security.PrivilegedExceptionAction
                public Unsafe run() {
                    Field[] declaredFields;
                    for (Field field : Unsafe.class.getDeclaredFields()) {
                        field.setAccessible(true);
                        Object obj = field.get(null);
                        if (Unsafe.class.isInstance(obj)) {
                            return (Unsafe) Unsafe.class.cast(obj);
                        }
                    }
                    throw new NoSuchFieldError("the Unsafe");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean b(long j2, long j3) {
        return UNSAFE.compareAndSwapLong(this, baseOffset, j2, j3);
    }

    final boolean c() {
        return UNSAFE.compareAndSwapInt(this, busyOffset, 0, 1);
    }

    abstract long d(long j2, long j3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void e(long j2) {
        Cell[] cellArr = this.f9254a;
        this.f9255b = j2;
        if (cellArr != null) {
            for (Cell cell : cellArr) {
                if (cell != null) {
                    cell.f9257a = j2;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0023 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x00ee A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void f(long j2, @NullableDecl int[] iArr, boolean z) {
        int i2;
        int[] iArr2;
        boolean z2;
        int length;
        boolean z3;
        int length2;
        if (iArr == null) {
            iArr2 = new int[1];
            f9251d.set(iArr2);
            i2 = f9252e.nextInt();
            if (i2 == 0) {
                i2 = 1;
            }
            iArr2[0] = i2;
        } else {
            i2 = iArr[0];
            iArr2 = iArr;
        }
        boolean z4 = false;
        int i3 = i2;
        boolean z5 = z;
        while (true) {
            Cell[] cellArr = this.f9254a;
            if (cellArr != null && (length = cellArr.length) > 0) {
                Cell cell = cellArr[(length - 1) & i3];
                if (cell == null) {
                    if (this.f9256c == 0) {
                        Cell cell2 = new Cell(j2);
                        if (this.f9256c == 0 && c()) {
                            try {
                                Cell[] cellArr2 = this.f9254a;
                                if (cellArr2 != null && (length2 = cellArr2.length) > 0) {
                                    int i4 = (length2 - 1) & i3;
                                    if (cellArr2[i4] == null) {
                                        cellArr2[i4] = cell2;
                                        z3 = true;
                                        if (!z3) {
                                            return;
                                        }
                                    }
                                }
                                z3 = false;
                                if (!z3) {
                                }
                            } finally {
                            }
                        }
                    }
                    z4 = false;
                } else if (z5) {
                    long j3 = cell.f9257a;
                    if (cell.a(j3, d(j3, j2))) {
                        return;
                    }
                    if (length < f9253f && this.f9254a == cellArr) {
                        if (!z4) {
                            z4 = true;
                        } else if (this.f9256c == 0 && c()) {
                            try {
                                if (this.f9254a == cellArr) {
                                    Cell[] cellArr3 = new Cell[length << 1];
                                    for (int i5 = 0; i5 < length; i5++) {
                                        cellArr3[i5] = cellArr[i5];
                                    }
                                    this.f9254a = cellArr3;
                                }
                                this.f9256c = 0;
                                z4 = false;
                            } finally {
                            }
                        }
                    }
                    z4 = false;
                } else {
                    z5 = true;
                }
                int i6 = i3 ^ (i3 << 13);
                int i7 = i6 ^ (i6 >>> 17);
                i3 = i7 ^ (i7 << 5);
                iArr2[0] = i3;
            } else if (this.f9256c == 0 && this.f9254a == cellArr && c()) {
                try {
                    if (this.f9254a == cellArr) {
                        Cell[] cellArr4 = new Cell[2];
                        cellArr4[i3 & 1] = new Cell(j2);
                        this.f9254a = cellArr4;
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (z2) {
                        return;
                    }
                } finally {
                }
            } else {
                long j4 = this.f9255b;
                if (b(j4, d(j4, j2))) {
                    return;
                }
            }
        }
    }
}
