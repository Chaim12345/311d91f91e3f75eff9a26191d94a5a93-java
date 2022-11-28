package com.google.android.libraries.places.api.model;

import android.os.Parcelable;
import androidx.annotation.RecentlyNonNull;
import com.google.auto.value.AutoValue;
import java.util.List;
@AutoValue
/* loaded from: classes2.dex */
public abstract class AddressComponents implements Parcelable {
    @RecentlyNonNull
    public static AddressComponents newInstance(@RecentlyNonNull List<AddressComponent> list) {
        return new zzab(list);
    }

    @RecentlyNonNull
    public abstract List<AddressComponent> asList();
}
