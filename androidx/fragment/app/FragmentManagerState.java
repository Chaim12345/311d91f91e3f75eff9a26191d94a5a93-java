package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.FragmentManager;
import java.util.ArrayList;
/* JADX INFO: Access modifiers changed from: package-private */
@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes.dex */
public final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator<FragmentManagerState> CREATOR = new Parcelable.Creator<FragmentManagerState>() { // from class: androidx.fragment.app.FragmentManagerState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FragmentManagerState createFromParcel(Parcel parcel) {
            return new FragmentManagerState(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FragmentManagerState[] newArray(int i2) {
            return new FragmentManagerState[i2];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    ArrayList f3010a;

    /* renamed from: b  reason: collision with root package name */
    ArrayList f3011b;

    /* renamed from: c  reason: collision with root package name */
    BackStackState[] f3012c;

    /* renamed from: d  reason: collision with root package name */
    int f3013d;

    /* renamed from: e  reason: collision with root package name */
    String f3014e;

    /* renamed from: f  reason: collision with root package name */
    ArrayList f3015f;

    /* renamed from: g  reason: collision with root package name */
    ArrayList f3016g;

    /* renamed from: h  reason: collision with root package name */
    ArrayList f3017h;

    public FragmentManagerState() {
        this.f3014e = null;
        this.f3015f = new ArrayList();
        this.f3016g = new ArrayList();
    }

    public FragmentManagerState(Parcel parcel) {
        this.f3014e = null;
        this.f3015f = new ArrayList();
        this.f3016g = new ArrayList();
        this.f3010a = parcel.createTypedArrayList(FragmentState.CREATOR);
        this.f3011b = parcel.createStringArrayList();
        this.f3012c = (BackStackState[]) parcel.createTypedArray(BackStackState.CREATOR);
        this.f3013d = parcel.readInt();
        this.f3014e = parcel.readString();
        this.f3015f = parcel.createStringArrayList();
        this.f3016g = parcel.createTypedArrayList(Bundle.CREATOR);
        this.f3017h = parcel.createTypedArrayList(FragmentManager.LaunchedFragmentInfo.CREATOR);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeTypedList(this.f3010a);
        parcel.writeStringList(this.f3011b);
        parcel.writeTypedArray(this.f3012c, i2);
        parcel.writeInt(this.f3013d);
        parcel.writeString(this.f3014e);
        parcel.writeStringList(this.f3015f);
        parcel.writeTypedList(this.f3016g);
        parcel.writeTypedList(this.f3017h);
    }
}
