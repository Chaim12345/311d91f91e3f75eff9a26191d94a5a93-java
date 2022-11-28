package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import java.util.ArrayList;
/* JADX INFO: Access modifiers changed from: package-private */
@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes.dex */
public final class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = new Parcelable.Creator<BackStackState>() { // from class: androidx.fragment.app.BackStackState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BackStackState createFromParcel(Parcel parcel) {
            return new BackStackState(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BackStackState[] newArray(int i2) {
            return new BackStackState[i2];
        }
    };
    private static final String TAG = "FragmentManager";

    /* renamed from: a  reason: collision with root package name */
    final int[] f2881a;

    /* renamed from: b  reason: collision with root package name */
    final ArrayList f2882b;

    /* renamed from: c  reason: collision with root package name */
    final int[] f2883c;

    /* renamed from: d  reason: collision with root package name */
    final int[] f2884d;

    /* renamed from: e  reason: collision with root package name */
    final int f2885e;

    /* renamed from: f  reason: collision with root package name */
    final String f2886f;

    /* renamed from: g  reason: collision with root package name */
    final int f2887g;

    /* renamed from: h  reason: collision with root package name */
    final int f2888h;

    /* renamed from: i  reason: collision with root package name */
    final CharSequence f2889i;

    /* renamed from: j  reason: collision with root package name */
    final int f2890j;

    /* renamed from: k  reason: collision with root package name */
    final CharSequence f2891k;

    /* renamed from: l  reason: collision with root package name */
    final ArrayList f2892l;

    /* renamed from: m  reason: collision with root package name */
    final ArrayList f2893m;

    /* renamed from: n  reason: collision with root package name */
    final boolean f2894n;

    public BackStackState(Parcel parcel) {
        this.f2881a = parcel.createIntArray();
        this.f2882b = parcel.createStringArrayList();
        this.f2883c = parcel.createIntArray();
        this.f2884d = parcel.createIntArray();
        this.f2885e = parcel.readInt();
        this.f2886f = parcel.readString();
        this.f2887g = parcel.readInt();
        this.f2888h = parcel.readInt();
        this.f2889i = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f2890j = parcel.readInt();
        this.f2891k = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f2892l = parcel.createStringArrayList();
        this.f2893m = parcel.createStringArrayList();
        this.f2894n = parcel.readInt() != 0;
    }

    public BackStackState(BackStackRecord backStackRecord) {
        int size = backStackRecord.f3038a.size();
        this.f2881a = new int[size * 5];
        if (!backStackRecord.f3044g) {
            throw new IllegalStateException("Not on back stack");
        }
        this.f2882b = new ArrayList(size);
        this.f2883c = new int[size];
        this.f2884d = new int[size];
        int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            FragmentTransaction.Op op = (FragmentTransaction.Op) backStackRecord.f3038a.get(i2);
            int i4 = i3 + 1;
            this.f2881a[i3] = op.f3055a;
            ArrayList arrayList = this.f2882b;
            Fragment fragment = op.f3056b;
            arrayList.add(fragment != null ? fragment.mWho : null);
            int[] iArr = this.f2881a;
            int i5 = i4 + 1;
            iArr[i4] = op.f3057c;
            int i6 = i5 + 1;
            iArr[i5] = op.f3058d;
            int i7 = i6 + 1;
            iArr[i6] = op.f3059e;
            iArr[i7] = op.f3060f;
            this.f2883c[i2] = op.f3061g.ordinal();
            this.f2884d[i2] = op.f3062h.ordinal();
            i2++;
            i3 = i7 + 1;
        }
        this.f2885e = backStackRecord.f3043f;
        this.f2886f = backStackRecord.f3046i;
        this.f2887g = backStackRecord.f2880t;
        this.f2888h = backStackRecord.f3047j;
        this.f2889i = backStackRecord.f3048k;
        this.f2890j = backStackRecord.f3049l;
        this.f2891k = backStackRecord.f3050m;
        this.f2892l = backStackRecord.f3051n;
        this.f2893m = backStackRecord.f3052o;
        this.f2894n = backStackRecord.f3053p;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BackStackRecord instantiate(FragmentManager fragmentManager) {
        BackStackRecord backStackRecord = new BackStackRecord(fragmentManager);
        int i2 = 0;
        int i3 = 0;
        while (i2 < this.f2881a.length) {
            FragmentTransaction.Op op = new FragmentTransaction.Op();
            int i4 = i2 + 1;
            op.f3055a = this.f2881a[i2];
            if (FragmentManager.c0(2)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Instantiate ");
                sb.append(backStackRecord);
                sb.append(" op #");
                sb.append(i3);
                sb.append(" base fragment #");
                sb.append(this.f2881a[i4]);
            }
            String str = (String) this.f2882b.get(i3);
            op.f3056b = str != null ? fragmentManager.M(str) : null;
            op.f3061g = Lifecycle.State.values()[this.f2883c[i3]];
            op.f3062h = Lifecycle.State.values()[this.f2884d[i3]];
            int[] iArr = this.f2881a;
            int i5 = i4 + 1;
            int i6 = iArr[i4];
            op.f3057c = i6;
            int i7 = i5 + 1;
            int i8 = iArr[i5];
            op.f3058d = i8;
            int i9 = i7 + 1;
            int i10 = iArr[i7];
            op.f3059e = i10;
            int i11 = iArr[i9];
            op.f3060f = i11;
            backStackRecord.f3039b = i6;
            backStackRecord.f3040c = i8;
            backStackRecord.f3041d = i10;
            backStackRecord.f3042e = i11;
            backStackRecord.b(op);
            i3++;
            i2 = i9 + 1;
        }
        backStackRecord.f3043f = this.f2885e;
        backStackRecord.f3046i = this.f2886f;
        backStackRecord.f2880t = this.f2887g;
        backStackRecord.f3044g = true;
        backStackRecord.f3047j = this.f2888h;
        backStackRecord.f3048k = this.f2889i;
        backStackRecord.f3049l = this.f2890j;
        backStackRecord.f3050m = this.f2891k;
        backStackRecord.f3051n = this.f2892l;
        backStackRecord.f3052o = this.f2893m;
        backStackRecord.f3053p = this.f2894n;
        backStackRecord.d(1);
        return backStackRecord;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeIntArray(this.f2881a);
        parcel.writeStringList(this.f2882b);
        parcel.writeIntArray(this.f2883c);
        parcel.writeIntArray(this.f2884d);
        parcel.writeInt(this.f2885e);
        parcel.writeString(this.f2886f);
        parcel.writeInt(this.f2887g);
        parcel.writeInt(this.f2888h);
        TextUtils.writeToParcel(this.f2889i, parcel, 0);
        parcel.writeInt(this.f2890j);
        TextUtils.writeToParcel(this.f2891k, parcel, 0);
        parcel.writeStringList(this.f2892l);
        parcel.writeStringList(this.f2893m);
        parcel.writeInt(this.f2894n ? 1 : 0);
    }
}
