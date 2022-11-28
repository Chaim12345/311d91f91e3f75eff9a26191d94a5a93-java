package android.support.v4.os;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.v4.os.IResultReceiver;
import androidx.annotation.RestrictTo;
@SuppressLint({"BanParcelableUsage"})
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class ResultReceiver implements Parcelable {
    public static final Parcelable.Creator<ResultReceiver> CREATOR = new Parcelable.Creator<ResultReceiver>() { // from class: android.support.v4.os.ResultReceiver.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ResultReceiver createFromParcel(Parcel parcel) {
            return new ResultReceiver(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ResultReceiver[] newArray(int i2) {
            return new ResultReceiver[i2];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    final boolean f143a;

    /* renamed from: b  reason: collision with root package name */
    final Handler f144b;

    /* renamed from: c  reason: collision with root package name */
    IResultReceiver f145c;

    /* loaded from: classes.dex */
    class MyResultReceiver extends IResultReceiver.Stub {
        MyResultReceiver() {
        }

        @Override // android.support.v4.os.IResultReceiver
        public void send(int i2, Bundle bundle) {
            ResultReceiver resultReceiver = ResultReceiver.this;
            Handler handler = resultReceiver.f144b;
            if (handler != null) {
                handler.post(new MyRunnable(i2, bundle));
            } else {
                resultReceiver.a(i2, bundle);
            }
        }
    }

    /* loaded from: classes.dex */
    class MyRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final int f147a;

        /* renamed from: b  reason: collision with root package name */
        final Bundle f148b;

        MyRunnable(int i2, Bundle bundle) {
            this.f147a = i2;
            this.f148b = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            ResultReceiver.this.a(this.f147a, this.f148b);
        }
    }

    public ResultReceiver(Handler handler) {
        this.f143a = true;
        this.f144b = handler;
    }

    ResultReceiver(Parcel parcel) {
        this.f143a = false;
        this.f144b = null;
        this.f145c = IResultReceiver.Stub.asInterface(parcel.readStrongBinder());
    }

    protected void a(int i2, Bundle bundle) {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void send(int i2, Bundle bundle) {
        if (this.f143a) {
            Handler handler = this.f144b;
            if (handler != null) {
                handler.post(new MyRunnable(i2, bundle));
                return;
            } else {
                a(i2, bundle);
                return;
            }
        }
        IResultReceiver iResultReceiver = this.f145c;
        if (iResultReceiver != null) {
            try {
                iResultReceiver.send(i2, bundle);
            } catch (RemoteException unused) {
            }
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        synchronized (this) {
            if (this.f145c == null) {
                this.f145c = new MyResultReceiver();
            }
            parcel.writeStrongBinder(this.f145c.asBinder());
        }
    }
}
