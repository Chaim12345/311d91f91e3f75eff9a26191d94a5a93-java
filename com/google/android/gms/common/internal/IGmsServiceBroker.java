package com.google.android.gms.common.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
/* loaded from: classes.dex */
public interface IGmsServiceBroker extends IInterface {

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IGmsServiceBroker {
        public Stub() {
            attachInterface(this, "com.google.android.gms.common.internal.IGmsServiceBroker");
        }

        @Override // android.os.IInterface
        @NonNull
        @KeepForSdk
        public IBinder asBinder() {
            return this;
        }

        /* JADX WARN: Code restructure failed: missing block: B:54:0x009d, code lost:
            if (r5.readInt() != 0) goto L50;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x00ba, code lost:
            if (r5.readInt() != 0) goto L50;
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x00cb, code lost:
            if (r5.readInt() != 0) goto L50;
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x00d2, code lost:
            if (r5.readInt() != 0) goto L50;
         */
        /* JADX WARN: Code restructure failed: missing block: B:68:0x00e2, code lost:
            if (r5.readInt() != 0) goto L50;
         */
        /* JADX WARN: Code restructure failed: missing block: B:69:0x00e4, code lost:
            r4 = (android.os.Bundle) android.os.Bundle.CREATOR.createFromParcel(r5);
         */
        @Override // android.os.Binder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final boolean onTransact(int i2, @NonNull Parcel parcel, @Nullable Parcel parcel2, int i3) {
            IGmsCallbacks zzaaVar;
            if (i2 > 16777215) {
                return super.onTransact(i2, parcel, parcel2, i3);
            }
            parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
            IBinder readStrongBinder = parcel.readStrongBinder();
            if (readStrongBinder == null) {
                zzaaVar = null;
            } else {
                IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsCallbacks");
                zzaaVar = queryLocalInterface instanceof IGmsCallbacks ? (IGmsCallbacks) queryLocalInterface : new zzaa(readStrongBinder);
            }
            if (i2 == 46) {
                getService(zzaaVar, parcel.readInt() != 0 ? GetServiceRequest.CREATOR.createFromParcel(parcel) : null);
                Preconditions.checkNotNull(parcel2);
                parcel2.writeNoException();
                return true;
            } else if (i2 == 47) {
                if (parcel.readInt() != 0) {
                    zzaj.CREATOR.createFromParcel(parcel);
                }
                throw new UnsupportedOperationException();
            } else {
                parcel.readInt();
                if (i2 != 4) {
                    parcel.readString();
                    if (i2 == 1) {
                        parcel.readString();
                        parcel.createStringArray();
                        parcel.readString();
                    } else if (i2 != 2 && i2 != 23 && i2 != 25 && i2 != 27) {
                        if (i2 != 30) {
                            if (i2 == 34) {
                                parcel.readString();
                            } else if (i2 != 41 && i2 != 43 && i2 != 37 && i2 != 38) {
                                switch (i2) {
                                    case 9:
                                        parcel.readString();
                                        parcel.createStringArray();
                                        parcel.readString();
                                        parcel.readStrongBinder();
                                        parcel.readString();
                                        break;
                                    case 10:
                                        parcel.readString();
                                        parcel.createStringArray();
                                        break;
                                    case 19:
                                        parcel.readStrongBinder();
                                        break;
                                }
                            }
                        }
                        parcel.createStringArray();
                        parcel.readString();
                    }
                }
                throw new UnsupportedOperationException();
            }
        }
    }

    @KeepForSdk
    void getService(@NonNull IGmsCallbacks iGmsCallbacks, @Nullable GetServiceRequest getServiceRequest);
}
