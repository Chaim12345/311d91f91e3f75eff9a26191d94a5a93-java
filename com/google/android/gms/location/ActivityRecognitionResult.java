package com.google.android.gms.location;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.List;
@SafeParcelable.Class(creator = "ActivityRecognitionResultCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes2.dex */
public class ActivityRecognitionResult extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<ActivityRecognitionResult> CREATOR = new zzn();
    @SafeParcelable.Field(id = 1)

    /* renamed from: a  reason: collision with root package name */
    List f6603a;
    @SafeParcelable.Field(id = 2)

    /* renamed from: b  reason: collision with root package name */
    long f6604b;
    @SafeParcelable.Field(id = 3)

    /* renamed from: c  reason: collision with root package name */
    long f6605c;
    @SafeParcelable.Field(id = 4)

    /* renamed from: d  reason: collision with root package name */
    int f6606d;
    @Nullable
    @SafeParcelable.Field(id = 5)

    /* renamed from: e  reason: collision with root package name */
    Bundle f6607e;

    @VisibleForTesting
    public ActivityRecognitionResult(@NonNull DetectedActivity detectedActivity, long j2, long j3) {
        this(Collections.singletonList(detectedActivity), j2, j3, 0, null);
    }

    public ActivityRecognitionResult(@NonNull List<DetectedActivity> list, long j2, long j3) {
        this(list, j2, j3, 0, null);
    }

    @ShowFirstParty
    @SafeParcelable.Constructor
    public ActivityRecognitionResult(@NonNull @SafeParcelable.Param(id = 1) List list, @SafeParcelable.Param(id = 2) long j2, @SafeParcelable.Param(id = 3) long j3, @SafeParcelable.Param(id = 4) int i2, @Nullable @SafeParcelable.Param(id = 5) Bundle bundle) {
        boolean z = true;
        Preconditions.checkArgument(list != null && list.size() > 0, "Must have at least 1 detected activity");
        Preconditions.checkArgument((j2 <= 0 || j3 <= 0) ? false : z, "Must set times");
        this.f6603a = list;
        this.f6604b = j2;
        this.f6605c = j3;
        this.f6606d = i2;
        this.f6607e = bundle;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0027, code lost:
        if ((r0 instanceof com.google.android.gms.location.ActivityRecognitionResult) != false) goto L19;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x002c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x002d  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ActivityRecognitionResult extractResult(@NonNull Intent intent) {
        Bundle extras;
        ActivityRecognitionResult activityRecognitionResult;
        if (hasResult(intent) && (extras = intent.getExtras()) != null) {
            Object obj = extras.get("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT");
            if (obj instanceof byte[]) {
                obj = SafeParcelableSerializer.deserializeFromBytes((byte[]) obj, CREATOR);
            }
            activityRecognitionResult = (ActivityRecognitionResult) obj;
            if (activityRecognitionResult == null) {
                return activityRecognitionResult;
            }
            List zza = zza(intent);
            if (zza == null || zza.isEmpty()) {
                return null;
            }
            return (ActivityRecognitionResult) zza.get(zza.size() - 1);
        }
        activityRecognitionResult = null;
        if (activityRecognitionResult == null) {
        }
    }

    public static boolean hasResult(@Nullable Intent intent) {
        if (intent == null) {
            return false;
        }
        if (intent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT")) {
            return true;
        }
        List zza = zza(intent);
        return (zza == null || zza.isEmpty()) ? false : true;
    }

    @Nullable
    public static List zza(@NonNull Intent intent) {
        if (intent != null && intent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT_LIST")) {
            return SafeParcelableSerializer.deserializeIterableFromIntentExtra(intent, "com.google.android.location.internal.EXTRA_ACTIVITY_RESULT_LIST", CREATOR);
        }
        return null;
    }

    private static boolean zzb(@Nullable Bundle bundle, @Nullable Bundle bundle2) {
        int length;
        if (bundle == null) {
            return bundle2 == null;
        } else if (bundle2 != null && bundle.size() == bundle2.size()) {
            for (String str : bundle.keySet()) {
                if (!bundle2.containsKey(str)) {
                    return false;
                }
                Object obj = bundle.get(str);
                Object obj2 = bundle2.get(str);
                if (obj == null) {
                    if (obj2 != null) {
                        return false;
                    }
                } else if (obj instanceof Bundle) {
                    if (!zzb(bundle.getBundle(str), bundle2.getBundle(str))) {
                        return false;
                    }
                } else if (obj.getClass().isArray()) {
                    if (obj2 != null && obj2.getClass().isArray() && (length = Array.getLength(obj)) == Array.getLength(obj2)) {
                        for (int i2 = 0; i2 < length; i2++) {
                            if (Objects.equal(Array.get(obj, i2), Array.get(obj2, i2))) {
                            }
                        }
                        continue;
                    }
                    return false;
                } else if (!obj.equals(obj2)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @ShowFirstParty
    public final boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            ActivityRecognitionResult activityRecognitionResult = (ActivityRecognitionResult) obj;
            if (this.f6604b == activityRecognitionResult.f6604b && this.f6605c == activityRecognitionResult.f6605c && this.f6606d == activityRecognitionResult.f6606d && Objects.equal(this.f6603a, activityRecognitionResult.f6603a) && zzb(this.f6607e, activityRecognitionResult.f6607e)) {
                return true;
            }
        }
        return false;
    }

    public int getActivityConfidence(int i2) {
        for (DetectedActivity detectedActivity : this.f6603a) {
            if (detectedActivity.getType() == i2) {
                return detectedActivity.getConfidence();
            }
        }
        return 0;
    }

    public long getElapsedRealtimeMillis() {
        return this.f6605c;
    }

    @NonNull
    public DetectedActivity getMostProbableActivity() {
        return (DetectedActivity) this.f6603a.get(0);
    }

    @NonNull
    public List<DetectedActivity> getProbableActivities() {
        return this.f6603a;
    }

    public long getTime() {
        return this.f6604b;
    }

    @ShowFirstParty
    public final int hashCode() {
        return Objects.hashCode(Long.valueOf(this.f6604b), Long.valueOf(this.f6605c), Integer.valueOf(this.f6606d), this.f6603a, this.f6607e);
    }

    @NonNull
    public String toString() {
        String valueOf = String.valueOf(this.f6603a);
        long j2 = this.f6604b;
        long j3 = this.f6605c;
        StringBuilder sb = new StringBuilder(valueOf.length() + 124);
        sb.append("ActivityRecognitionResult [probableActivities=");
        sb.append(valueOf);
        sb.append(", timeMillis=");
        sb.append(j2);
        sb.append(", elapsedRealtimeMillis=");
        sb.append(j3);
        sb.append("]");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, this.f6603a, false);
        SafeParcelWriter.writeLong(parcel, 2, this.f6604b);
        SafeParcelWriter.writeLong(parcel, 3, this.f6605c);
        SafeParcelWriter.writeInt(parcel, 4, this.f6606d);
        SafeParcelWriter.writeBundle(parcel, 5, this.f6607e, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
