package com.google.android.gms.safetynet;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
@SafeParcelable.Class(creator = "SafeBrowsingDataCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public class SafeBrowsingData extends AbstractSafeParcelable {
    @Nullable
    @SafeParcelable.Field(getter = "getMetadata", id = 2)
    private String zzb;
    @Nullable
    @SafeParcelable.Field(getter = "getListsDataHolder", id = 3)
    private DataHolder zzc;
    @Nullable
    @SafeParcelable.Field(getter = "getFileDescriptor", id = 4)
    private ParcelFileDescriptor zzd;
    @Nullable
    @SafeParcelable.Field(getter = "getLastUpdateTimeMs", id = 5)
    private long zze;
    @Nullable
    @SafeParcelable.Field(getter = "getState", id = 6)
    private byte[] zzf;
    private byte[] zzg;
    private File zzh;
    private static final String zza = SafeBrowsingData.class.getSimpleName();
    @NonNull
    public static final Parcelable.Creator<SafeBrowsingData> CREATOR = new zzj();

    public SafeBrowsingData() {
        this(null, null, null, 0L, null);
    }

    public SafeBrowsingData(long j2, @NonNull byte[] bArr) {
        this(null, null, null, j2, bArr);
    }

    public SafeBrowsingData(@NonNull String str) {
        this(str, null, null, 0L, null);
    }

    public SafeBrowsingData(@NonNull String str, @NonNull DataHolder dataHolder) {
        this(str, dataHolder, null, 0L, null);
    }

    @SafeParcelable.Constructor
    public SafeBrowsingData(@Nullable @SafeParcelable.Param(id = 2) String str, @Nullable @SafeParcelable.Param(id = 3) DataHolder dataHolder, @Nullable @SafeParcelable.Param(id = 4) ParcelFileDescriptor parcelFileDescriptor, @Nullable @SafeParcelable.Param(id = 5) long j2, @Nullable @SafeParcelable.Param(id = 6) byte[] bArr) {
        this.zzb = str;
        this.zzc = dataHolder;
        this.zzd = parcelFileDescriptor;
        this.zze = j2;
        this.zzf = bArr;
    }

    private static final void zza(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException unused) {
        }
    }

    @Nullable
    public ParcelFileDescriptor getFileDescriptor() {
        return this.zzd;
    }

    public long getLastUpdateTimeMs() {
        return this.zze;
    }

    @Nullable
    public DataHolder getListsDataHolder() {
        return this.zzc;
    }

    @Nullable
    public String getMetadata() {
        return this.zzb;
    }

    @Nullable
    public byte[] getSerializedLists() {
        ParcelFileDescriptor parcelFileDescriptor = this.zzd;
        if (parcelFileDescriptor == null) {
            return null;
        }
        DataInputStream dataInputStream = new DataInputStream(new ParcelFileDescriptor.AutoCloseInputStream(parcelFileDescriptor));
        try {
            byte[] bArr = new byte[dataInputStream.readInt()];
            dataInputStream.read(bArr);
            return bArr;
        } catch (IOException unused) {
            return null;
        } finally {
            zza(dataInputStream);
            this.zzd = null;
        }
    }

    @Nullable
    public byte[] getState() {
        return this.zzf;
    }

    public void setSerializedLists(@NonNull byte[] bArr) {
        this.zzg = bArr;
    }

    public void setTempDir(@NonNull File file) {
        if (file != null) {
            this.zzh = file;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x003d  */
    @Override // android.os.Parcelable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void writeToParcel(@NonNull Parcel parcel, int i2) {
        File file;
        FileOutputStream fileOutputStream;
        File file2 = null;
        if (this.zzd == null && this.zzg != null) {
            File file3 = this.zzh;
            if (file3 != null) {
                try {
                    file = File.createTempFile("xlb", ".tmp", file3);
                    try {
                        fileOutputStream = new FileOutputStream(file);
                        this.zzd = ParcelFileDescriptor.open(file, 268435456);
                        if (file != null) {
                            file.delete();
                        }
                    } catch (IOException unused) {
                        if (file != null) {
                            file.delete();
                        }
                        fileOutputStream = null;
                        if (fileOutputStream != null) {
                        }
                        zzj.a(this, parcel, i2);
                        this.zzd = null;
                    } catch (Throwable th) {
                        th = th;
                        file2 = file;
                        if (file2 != null) {
                            file2.delete();
                        }
                        throw th;
                    }
                } catch (IOException unused2) {
                    file = null;
                } catch (Throwable th2) {
                    th = th2;
                }
                if (fileOutputStream != null) {
                    DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(fileOutputStream));
                    try {
                        dataOutputStream.writeInt(this.zzg.length);
                        dataOutputStream.write(this.zzg);
                        zza(dataOutputStream);
                        i2 |= 1;
                    } catch (IOException unused3) {
                        zza(dataOutputStream);
                    } catch (Throwable th3) {
                        zza(dataOutputStream);
                        throw th3;
                    }
                }
            }
            fileOutputStream = null;
            if (fileOutputStream != null) {
            }
        }
        zzj.a(this, parcel, i2);
        this.zzd = null;
    }
}
