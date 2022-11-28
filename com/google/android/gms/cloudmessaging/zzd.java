package com.google.android.gms.cloudmessaging;

import android.os.Build;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.cloudmessaging.IMessengerCompat;
/* loaded from: classes.dex */
public final class zzd implements Parcelable {
    public static final Parcelable.Creator<zzd> CREATOR = new zzb();

    /* renamed from: a  reason: collision with root package name */
    Messenger f5604a;

    /* renamed from: b  reason: collision with root package name */
    IMessengerCompat f5605b;

    public zzd(IBinder iBinder) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.f5604a = new Messenger(iBinder);
        } else {
            this.f5605b = new IMessengerCompat.Proxy(iBinder);
        }
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            return zza().equals(((zzd) obj).zza());
        } catch (ClassCastException unused) {
            return false;
        }
    }

    public final int hashCode() {
        return zza().hashCode();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        Messenger messenger = this.f5604a;
        parcel.writeStrongBinder(messenger != null ? messenger.getBinder() : this.f5605b.asBinder());
    }

    public final IBinder zza() {
        Messenger messenger = this.f5604a;
        return messenger != null ? messenger.getBinder() : this.f5605b.asBinder();
    }

    public final void zzb(Message message) {
        Messenger messenger = this.f5604a;
        if (messenger != null) {
            messenger.send(message);
        } else {
            this.f5605b.send(message);
        }
    }
}
