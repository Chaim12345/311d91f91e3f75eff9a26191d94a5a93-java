package androidx.car.app.serialization;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import java.util.Objects;
/* loaded from: classes.dex */
public final class Bundleable implements Parcelable {
    @NonNull
    public static final Parcelable.Creator<Bundleable> CREATOR = new Parcelable.Creator<Bundleable>() { // from class: androidx.car.app.serialization.Bundleable.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Bundleable createFromParcel(Parcel parcel) {
            Bundle readBundle = parcel.readBundle(getClass().getClassLoader());
            Objects.requireNonNull(readBundle);
            return new Bundleable(readBundle);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Bundleable[] newArray(int i2) {
            return new Bundleable[i2];
        }
    };
    private final Bundle mBundle;

    Bundleable(Bundle bundle) {
        this.mBundle = bundle;
    }

    private Bundleable(Object obj) {
        this.mBundle = Bundler.toBundle(obj);
    }

    @NonNull
    public static Bundleable create(@NonNull Object obj) {
        return new Bundleable(obj);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @NonNull
    public Object get() {
        return Bundler.fromBundle(this.mBundle);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i2) {
        parcel.writeBundle(this.mBundle);
    }
}
