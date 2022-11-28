package pl.droidsonroids.gif;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import androidx.annotation.NonNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class GifViewSavedState extends View.BaseSavedState {
    public static final Parcelable.Creator<GifViewSavedState> CREATOR = new Parcelable.Creator<GifViewSavedState>() { // from class: pl.droidsonroids.gif.GifViewSavedState.1
        @Override // android.os.Parcelable.Creator
        public GifViewSavedState createFromParcel(Parcel parcel) {
            return new GifViewSavedState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public GifViewSavedState[] newArray(int i2) {
            return new GifViewSavedState[i2];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    final long[][] f15272a;

    private GifViewSavedState(Parcel parcel) {
        super(parcel);
        this.f15272a = new long[parcel.readInt()];
        int i2 = 0;
        while (true) {
            long[][] jArr = this.f15272a;
            if (i2 >= jArr.length) {
                return;
            }
            jArr[i2] = parcel.createLongArray();
            i2++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GifViewSavedState(Parcelable parcelable, long[] jArr) {
        super(parcelable);
        this.f15272a = r2;
        long[][] jArr2 = {jArr};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GifViewSavedState(Parcelable parcelable, Drawable... drawableArr) {
        super(parcelable);
        this.f15272a = new long[drawableArr.length];
        for (int i2 = 0; i2 < drawableArr.length; i2++) {
            Drawable drawable = drawableArr[i2];
            if (drawable instanceof GifDrawable) {
                this.f15272a[i2] = ((GifDrawable) drawable).f15254f.n();
            } else {
                this.f15272a[i2] = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Drawable drawable, int i2) {
        long[][] jArr = this.f15272a;
        if (jArr[i2] == null || !(drawable instanceof GifDrawable)) {
            return;
        }
        GifDrawable gifDrawable = (GifDrawable) drawable;
        gifDrawable.a(gifDrawable.f15254f.C(jArr[i2], gifDrawable.f15253e));
    }

    @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeInt(this.f15272a.length);
        for (long[] jArr : this.f15272a) {
            parcel.writeLongArray(jArr);
        }
    }
}
