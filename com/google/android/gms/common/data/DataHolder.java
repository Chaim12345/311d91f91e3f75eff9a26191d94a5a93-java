package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.sqlite.CursorWrapper;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.tls.CipherSuite;
@KeepForSdk
@KeepName
@SafeParcelable.Class(creator = "DataHolderCreator", validate = true)
/* loaded from: classes.dex */
public final class DataHolder extends AbstractSafeParcelable implements Closeable {
    @NonNull
    @KeepForSdk
    public static final Parcelable.Creator<DataHolder> CREATOR = new zaf();
    private static final Builder zaf = new zab(new String[0], null);
    @SafeParcelable.VersionField(id = 1000)

    /* renamed from: a  reason: collision with root package name */
    final int f5732a;

    /* renamed from: b  reason: collision with root package name */
    Bundle f5733b;

    /* renamed from: c  reason: collision with root package name */
    int[] f5734c;

    /* renamed from: d  reason: collision with root package name */
    int f5735d;

    /* renamed from: e  reason: collision with root package name */
    boolean f5736e;
    @SafeParcelable.Field(getter = "getColumns", id = 1)
    private final String[] zag;
    @SafeParcelable.Field(getter = "getWindows", id = 2)
    private final CursorWindow[] zah;
    @SafeParcelable.Field(getter = "getStatusCode", id = 3)
    private final int zai;
    @Nullable
    @SafeParcelable.Field(getter = "getMetadata", id = 4)
    private final Bundle zaj;
    private boolean zak;

    @KeepForSdk
    /* loaded from: classes.dex */
    public static class Builder {
        private final String[] zaa;
        private final ArrayList<HashMap<String, Object>> zab = new ArrayList<>();
        private final HashMap<Object, Integer> zac = new HashMap<>();

        /* JADX INFO: Access modifiers changed from: package-private */
        public /* synthetic */ Builder(String[] strArr, String str, zac zacVar) {
            this.zaa = (String[]) Preconditions.checkNotNull(strArr);
        }

        @NonNull
        @KeepForSdk
        public DataHolder build(int i2) {
            return new DataHolder(this, i2);
        }

        @NonNull
        @KeepForSdk
        public DataHolder build(int i2, @NonNull Bundle bundle) {
            return new DataHolder(this, i2, bundle);
        }

        @NonNull
        @KeepForSdk
        public Builder withRow(@NonNull ContentValues contentValues) {
            Asserts.checkNotNull(contentValues);
            HashMap<String, Object> hashMap = new HashMap<>(contentValues.size());
            for (Map.Entry<String, Object> entry : contentValues.valueSet()) {
                hashMap.put(entry.getKey(), entry.getValue());
            }
            return zaa(hashMap);
        }

        @NonNull
        public Builder zaa(@NonNull HashMap<String, Object> hashMap) {
            Asserts.checkNotNull(hashMap);
            this.zab.add(hashMap);
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SafeParcelable.Constructor
    public DataHolder(@SafeParcelable.Param(id = 1000) int i2, @SafeParcelable.Param(id = 1) String[] strArr, @SafeParcelable.Param(id = 2) CursorWindow[] cursorWindowArr, @SafeParcelable.Param(id = 3) int i3, @Nullable @SafeParcelable.Param(id = 4) Bundle bundle) {
        this.f5736e = false;
        this.zak = true;
        this.f5732a = i2;
        this.zag = strArr;
        this.zah = cursorWindowArr;
        this.zai = i3;
        this.zaj = bundle;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    @KeepForSdk
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public DataHolder(@NonNull Cursor cursor, int i2, @Nullable Bundle bundle) {
        this(r8, (CursorWindow[]) r1.toArray(new CursorWindow[r1.size()]), i2, bundle);
        int i3;
        CursorWrapper cursorWrapper = new CursorWrapper(cursor);
        String[] columnNames = cursorWrapper.getColumnNames();
        ArrayList arrayList = new ArrayList();
        try {
            int count = cursorWrapper.getCount();
            CursorWindow window = cursorWrapper.getWindow();
            if (window == null || window.getStartPosition() != 0) {
                i3 = 0;
            } else {
                window.acquireReference();
                cursorWrapper.setWindow(null);
                arrayList.add(window);
                i3 = window.getNumRows();
            }
            while (i3 < count) {
                if (!cursorWrapper.moveToPosition(i3)) {
                    break;
                }
                CursorWindow window2 = cursorWrapper.getWindow();
                if (window2 != null) {
                    window2.acquireReference();
                    cursorWrapper.setWindow(null);
                } else {
                    window2 = new CursorWindow(false);
                    window2.setStartPosition(i3);
                    cursorWrapper.fillWindow(i3, window2);
                }
                if (window2.getNumRows() == 0) {
                    break;
                }
                arrayList.add(window2);
                i3 = window2.getStartPosition() + window2.getNumRows();
            }
            cursorWrapper.close();
        } catch (Throwable th) {
            cursorWrapper.close();
            throw th;
        }
    }

    private DataHolder(Builder builder, int i2, @Nullable Bundle bundle) {
        this(builder.zaa, zaf(builder, -1), i2, (Bundle) null);
    }

    @KeepForSdk
    public DataHolder(@NonNull String[] strArr, @NonNull CursorWindow[] cursorWindowArr, int i2, @Nullable Bundle bundle) {
        this.f5736e = false;
        this.zak = true;
        this.f5732a = 1;
        this.zag = (String[]) Preconditions.checkNotNull(strArr);
        this.zah = (CursorWindow[]) Preconditions.checkNotNull(cursorWindowArr);
        this.zai = i2;
        this.zaj = bundle;
        zad();
    }

    @NonNull
    @KeepForSdk
    public static Builder builder(@NonNull String[] strArr) {
        return new Builder(strArr, null, null);
    }

    @NonNull
    @KeepForSdk
    public static DataHolder empty(int i2) {
        return new DataHolder(zaf, i2, (Bundle) null);
    }

    private final void zae(String str, int i2) {
        Bundle bundle = this.f5733b;
        if (bundle == null || !bundle.containsKey(str)) {
            String valueOf = String.valueOf(str);
            throw new IllegalArgumentException(valueOf.length() != 0 ? "No such column: ".concat(valueOf) : new String("No such column: "));
        } else if (isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
        } else {
            if (i2 < 0 || i2 >= this.f5735d) {
                throw new CursorIndexOutOfBoundsException(i2, this.f5735d);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x0138, code lost:
        if (r5 != false) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x013a, code lost:
        r5 = new java.lang.StringBuilder(74);
        r5.append("Couldn't populate window data for row ");
        r5.append(r4);
        r5.append(" - allocating new window.");
        r2.freeLastRow();
        r2 = new android.database.CursorWindow(false);
        r2.setStartPosition(r4);
        r2.setNumColumns(r12.zaa.length);
        r3.add(r2);
        r4 = r4 - 1;
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0167, code lost:
        r4 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0171, code lost:
        throw new com.google.android.gms.common.data.zad("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static CursorWindow[] zaf(Builder builder, int i2) {
        long j2;
        if (builder.zaa.length == 0) {
            return new CursorWindow[0];
        }
        ArrayList arrayList = builder.zab;
        int size = arrayList.size();
        CursorWindow cursorWindow = new CursorWindow(false);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(cursorWindow);
        cursorWindow.setNumColumns(builder.zaa.length);
        int i3 = 0;
        boolean z = false;
        while (i3 < size) {
            try {
                if (!cursorWindow.allocRow()) {
                    StringBuilder sb = new StringBuilder(72);
                    sb.append("Allocating additional cursor window for large data set (row ");
                    sb.append(i3);
                    sb.append(")");
                    cursorWindow = new CursorWindow(false);
                    cursorWindow.setStartPosition(i3);
                    cursorWindow.setNumColumns(builder.zaa.length);
                    arrayList2.add(cursorWindow);
                    if (!cursorWindow.allocRow()) {
                        Log.e("DataHolder", "Unable to allocate row to hold data.");
                        arrayList2.remove(cursorWindow);
                        return (CursorWindow[]) arrayList2.toArray(new CursorWindow[arrayList2.size()]);
                    }
                }
                Map map = (Map) arrayList.get(i3);
                int i4 = 0;
                boolean z2 = true;
                while (true) {
                    if (i4 < builder.zaa.length) {
                        if (!z2) {
                            break;
                        }
                        String str = builder.zaa[i4];
                        Object obj = map.get(str);
                        if (obj == null) {
                            z2 = cursorWindow.putNull(i3, i4);
                        } else if (obj instanceof String) {
                            z2 = cursorWindow.putString((String) obj, i3, i4);
                        } else {
                            if (obj instanceof Long) {
                                j2 = ((Long) obj).longValue();
                            } else if (obj instanceof Integer) {
                                z2 = cursorWindow.putLong(((Integer) obj).intValue(), i3, i4);
                            } else if (obj instanceof Boolean) {
                                j2 = true != ((Boolean) obj).booleanValue() ? 0L : 1L;
                            } else if (obj instanceof byte[]) {
                                z2 = cursorWindow.putBlob((byte[]) obj, i3, i4);
                            } else if (obj instanceof Double) {
                                z2 = cursorWindow.putDouble(((Double) obj).doubleValue(), i3, i4);
                            } else if (!(obj instanceof Float)) {
                                String obj2 = obj.toString();
                                StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 32 + obj2.length());
                                sb2.append("Unsupported object for column ");
                                sb2.append(str);
                                sb2.append(": ");
                                sb2.append(obj2);
                                throw new IllegalArgumentException(sb2.toString());
                            } else {
                                z2 = cursorWindow.putDouble(((Float) obj).floatValue(), i3, i4);
                            }
                            z2 = cursorWindow.putLong(j2, i3, i4);
                        }
                        i4++;
                    } else if (z2) {
                        z = false;
                    }
                }
            } catch (RuntimeException e2) {
                int size2 = arrayList2.size();
                for (int i5 = 0; i5 < size2; i5++) {
                    ((CursorWindow) arrayList2.get(i5)).close();
                }
                throw e2;
            }
        }
        return (CursorWindow[]) arrayList2.toArray(new CursorWindow[arrayList2.size()]);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    @KeepForSdk
    public void close() {
        synchronized (this) {
            if (!this.f5736e) {
                this.f5736e = true;
                int i2 = 0;
                while (true) {
                    CursorWindow[] cursorWindowArr = this.zah;
                    if (i2 >= cursorWindowArr.length) {
                        break;
                    }
                    cursorWindowArr[i2].close();
                    i2++;
                }
            }
        }
    }

    protected final void finalize() {
        try {
            if (this.zak && this.zah.length > 0 && !isClosed()) {
                close();
                String obj = toString();
                StringBuilder sb = new StringBuilder(String.valueOf(obj).length() + CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA256);
                sb.append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: ");
                sb.append(obj);
                sb.append(")");
                Log.e("DataBuffer", sb.toString());
            }
        } finally {
            super.finalize();
        }
    }

    @KeepForSdk
    public boolean getBoolean(@NonNull String str, int i2, int i3) {
        zae(str, i2);
        return Long.valueOf(this.zah[i3].getLong(i2, this.f5733b.getInt(str))).longValue() == 1;
    }

    @NonNull
    @KeepForSdk
    public byte[] getByteArray(@NonNull String str, int i2, int i3) {
        zae(str, i2);
        return this.zah[i3].getBlob(i2, this.f5733b.getInt(str));
    }

    @KeepForSdk
    public int getCount() {
        return this.f5735d;
    }

    @KeepForSdk
    public int getInteger(@NonNull String str, int i2, int i3) {
        zae(str, i2);
        return this.zah[i3].getInt(i2, this.f5733b.getInt(str));
    }

    @KeepForSdk
    public long getLong(@NonNull String str, int i2, int i3) {
        zae(str, i2);
        return this.zah[i3].getLong(i2, this.f5733b.getInt(str));
    }

    @Nullable
    @KeepForSdk
    public Bundle getMetadata() {
        return this.zaj;
    }

    @KeepForSdk
    public int getStatusCode() {
        return this.zai;
    }

    @NonNull
    @KeepForSdk
    public String getString(@NonNull String str, int i2, int i3) {
        zae(str, i2);
        return this.zah[i3].getString(i2, this.f5733b.getInt(str));
    }

    @KeepForSdk
    public int getWindowIndex(int i2) {
        int length;
        int i3 = 0;
        Preconditions.checkState(i2 >= 0 && i2 < this.f5735d);
        while (true) {
            int[] iArr = this.f5734c;
            length = iArr.length;
            if (i3 >= length) {
                break;
            } else if (i2 < iArr[i3]) {
                i3--;
                break;
            } else {
                i3++;
            }
        }
        return i3 == length ? i3 - 1 : i3;
    }

    @KeepForSdk
    public boolean hasColumn(@NonNull String str) {
        return this.f5733b.containsKey(str);
    }

    @KeepForSdk
    public boolean hasNull(@NonNull String str, int i2, int i3) {
        zae(str, i2);
        return this.zah[i3].isNull(i2, this.f5733b.getInt(str));
    }

    @KeepForSdk
    public boolean isClosed() {
        boolean z;
        synchronized (this) {
            z = this.f5736e;
        }
        return z;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringArray(parcel, 1, this.zag, false);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.zah, i2, false);
        SafeParcelWriter.writeInt(parcel, 3, getStatusCode());
        SafeParcelWriter.writeBundle(parcel, 4, getMetadata(), false);
        SafeParcelWriter.writeInt(parcel, 1000, this.f5732a);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        if ((i2 & 1) != 0) {
            close();
        }
    }

    public final double zaa(@NonNull String str, int i2, int i3) {
        zae(str, i2);
        return this.zah[i3].getDouble(i2, this.f5733b.getInt(str));
    }

    public final float zab(@NonNull String str, int i2, int i3) {
        zae(str, i2);
        return this.zah[i3].getFloat(i2, this.f5733b.getInt(str));
    }

    public final void zac(@NonNull String str, int i2, int i3, @NonNull CharArrayBuffer charArrayBuffer) {
        zae(str, i2);
        this.zah[i3].copyStringToBuffer(i2, this.f5733b.getInt(str), charArrayBuffer);
    }

    public final void zad() {
        this.f5733b = new Bundle();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            String[] strArr = this.zag;
            if (i3 >= strArr.length) {
                break;
            }
            this.f5733b.putInt(strArr[i3], i3);
            i3++;
        }
        this.f5734c = new int[this.zah.length];
        int i4 = 0;
        while (true) {
            CursorWindow[] cursorWindowArr = this.zah;
            if (i2 >= cursorWindowArr.length) {
                this.f5735d = i4;
                return;
            }
            this.f5734c[i2] = i4;
            i4 += this.zah[i2].getNumRows() - (i4 - cursorWindowArr[i2].getStartPosition());
            i2++;
        }
    }
}
