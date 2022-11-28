package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
/* JADX INFO: Access modifiers changed from: package-private */
@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes.dex */
public final class FragmentState implements Parcelable {
    public static final Parcelable.Creator<FragmentState> CREATOR = new Parcelable.Creator<FragmentState>() { // from class: androidx.fragment.app.FragmentState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FragmentState createFromParcel(Parcel parcel) {
            return new FragmentState(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FragmentState[] newArray(int i2) {
            return new FragmentState[i2];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    final String f3018a;

    /* renamed from: b  reason: collision with root package name */
    final String f3019b;

    /* renamed from: c  reason: collision with root package name */
    final boolean f3020c;

    /* renamed from: d  reason: collision with root package name */
    final int f3021d;

    /* renamed from: e  reason: collision with root package name */
    final int f3022e;

    /* renamed from: f  reason: collision with root package name */
    final String f3023f;

    /* renamed from: g  reason: collision with root package name */
    final boolean f3024g;

    /* renamed from: h  reason: collision with root package name */
    final boolean f3025h;

    /* renamed from: i  reason: collision with root package name */
    final boolean f3026i;

    /* renamed from: j  reason: collision with root package name */
    final Bundle f3027j;

    /* renamed from: k  reason: collision with root package name */
    final boolean f3028k;

    /* renamed from: l  reason: collision with root package name */
    final int f3029l;

    /* renamed from: m  reason: collision with root package name */
    Bundle f3030m;

    FragmentState(Parcel parcel) {
        this.f3018a = parcel.readString();
        this.f3019b = parcel.readString();
        this.f3020c = parcel.readInt() != 0;
        this.f3021d = parcel.readInt();
        this.f3022e = parcel.readInt();
        this.f3023f = parcel.readString();
        this.f3024g = parcel.readInt() != 0;
        this.f3025h = parcel.readInt() != 0;
        this.f3026i = parcel.readInt() != 0;
        this.f3027j = parcel.readBundle();
        this.f3028k = parcel.readInt() != 0;
        this.f3030m = parcel.readBundle();
        this.f3029l = parcel.readInt();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FragmentState(Fragment fragment) {
        this.f3018a = fragment.getClass().getName();
        this.f3019b = fragment.mWho;
        this.f3020c = fragment.mFromLayout;
        this.f3021d = fragment.mFragmentId;
        this.f3022e = fragment.mContainerId;
        this.f3023f = fragment.mTag;
        this.f3024g = fragment.mRetainInstance;
        this.f3025h = fragment.mRemoving;
        this.f3026i = fragment.mDetached;
        this.f3027j = fragment.mArguments;
        this.f3028k = fragment.mHidden;
        this.f3029l = fragment.mMaxState.ordinal();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentState{");
        sb.append(this.f3018a);
        sb.append(" (");
        sb.append(this.f3019b);
        sb.append(")}:");
        if (this.f3020c) {
            sb.append(" fromLayout");
        }
        if (this.f3022e != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.f3022e));
        }
        String str = this.f3023f;
        if (str != null && !str.isEmpty()) {
            sb.append(" tag=");
            sb.append(this.f3023f);
        }
        if (this.f3024g) {
            sb.append(" retainInstance");
        }
        if (this.f3025h) {
            sb.append(" removing");
        }
        if (this.f3026i) {
            sb.append(" detached");
        }
        if (this.f3028k) {
            sb.append(" hidden");
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f3018a);
        parcel.writeString(this.f3019b);
        parcel.writeInt(this.f3020c ? 1 : 0);
        parcel.writeInt(this.f3021d);
        parcel.writeInt(this.f3022e);
        parcel.writeString(this.f3023f);
        parcel.writeInt(this.f3024g ? 1 : 0);
        parcel.writeInt(this.f3025h ? 1 : 0);
        parcel.writeInt(this.f3026i ? 1 : 0);
        parcel.writeBundle(this.f3027j);
        parcel.writeInt(this.f3028k ? 1 : 0);
        parcel.writeBundle(this.f3030m);
        parcel.writeInt(this.f3029l);
    }
}
