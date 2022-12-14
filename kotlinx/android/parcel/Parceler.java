package kotlinx.android.parcel;

import android.os.Parcel;
import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public interface Parceler<T> {

    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        @NotNull
        public static <T> T[] newArray(@NotNull Parceler<T> parceler, int i2) {
            throw new NotImplementedError("Generated by Android Extensions automatically");
        }
    }

    T create(@NotNull Parcel parcel);

    @NotNull
    T[] newArray(int i2);

    void write(T t2, @NotNull Parcel parcel, int i2);
}
