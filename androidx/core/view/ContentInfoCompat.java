package androidx.core.view;

import android.content.ClipData;
import android.content.ClipDescription;
import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
import androidx.core.util.Predicate;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class ContentInfoCompat {
    public static final int FLAG_CONVERT_TO_PLAIN_TEXT = 1;
    public static final int SOURCE_APP = 0;
    public static final int SOURCE_CLIPBOARD = 1;
    public static final int SOURCE_DRAG_AND_DROP = 3;
    public static final int SOURCE_INPUT_METHOD = 2;
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    final ClipData f2635a;

    /* renamed from: b  reason: collision with root package name */
    final int f2636b;

    /* renamed from: c  reason: collision with root package name */
    final int f2637c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    final Uri f2638d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    final Bundle f2639e;

    /* loaded from: classes.dex */
    public static final class Builder {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        ClipData f2640a;

        /* renamed from: b  reason: collision with root package name */
        int f2641b;

        /* renamed from: c  reason: collision with root package name */
        int f2642c;
        @Nullable

        /* renamed from: d  reason: collision with root package name */
        Uri f2643d;
        @Nullable

        /* renamed from: e  reason: collision with root package name */
        Bundle f2644e;

        public Builder(@NonNull ClipData clipData, int i2) {
            this.f2640a = clipData;
            this.f2641b = i2;
        }

        public Builder(@NonNull ContentInfoCompat contentInfoCompat) {
            this.f2640a = contentInfoCompat.f2635a;
            this.f2641b = contentInfoCompat.f2636b;
            this.f2642c = contentInfoCompat.f2637c;
            this.f2643d = contentInfoCompat.f2638d;
            this.f2644e = contentInfoCompat.f2639e;
        }

        @NonNull
        public ContentInfoCompat build() {
            return new ContentInfoCompat(this);
        }

        @NonNull
        public Builder setClip(@NonNull ClipData clipData) {
            this.f2640a = clipData;
            return this;
        }

        @NonNull
        public Builder setExtras(@Nullable Bundle bundle) {
            this.f2644e = bundle;
            return this;
        }

        @NonNull
        public Builder setFlags(int i2) {
            this.f2642c = i2;
            return this;
        }

        @NonNull
        public Builder setLinkUri(@Nullable Uri uri) {
            this.f2643d = uri;
            return this;
        }

        @NonNull
        public Builder setSource(int i2) {
            this.f2641b = i2;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public @interface Flags {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public @interface Source {
    }

    ContentInfoCompat(Builder builder) {
        this.f2635a = (ClipData) Preconditions.checkNotNull(builder.f2640a);
        this.f2636b = Preconditions.checkArgumentInRange(builder.f2641b, 0, 3, "source");
        this.f2637c = Preconditions.checkFlagsArgument(builder.f2642c, 1);
        this.f2638d = builder.f2643d;
        this.f2639e = builder.f2644e;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    static String a(int i2) {
        return (i2 & 1) != 0 ? "FLAG_CONVERT_TO_PLAIN_TEXT" : String.valueOf(i2);
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    static String b(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? String.valueOf(i2) : "SOURCE_DRAG_AND_DROP" : "SOURCE_INPUT_METHOD" : "SOURCE_CLIPBOARD" : "SOURCE_APP";
    }

    private static ClipData buildClipData(ClipDescription clipDescription, List<ClipData.Item> list) {
        ClipData clipData = new ClipData(new ClipDescription(clipDescription), list.get(0));
        for (int i2 = 1; i2 < list.size(); i2++) {
            clipData.addItem(list.get(i2));
        }
        return clipData;
    }

    @NonNull
    public ClipData getClip() {
        return this.f2635a;
    }

    @Nullable
    public Bundle getExtras() {
        return this.f2639e;
    }

    public int getFlags() {
        return this.f2637c;
    }

    @Nullable
    public Uri getLinkUri() {
        return this.f2638d;
    }

    public int getSource() {
        return this.f2636b;
    }

    @NonNull
    public Pair<ContentInfoCompat, ContentInfoCompat> partition(@NonNull Predicate<ClipData.Item> predicate) {
        if (this.f2635a.getItemCount() == 1) {
            boolean test = predicate.test(this.f2635a.getItemAt(0));
            return Pair.create(test ? this : null, test ? null : this);
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < this.f2635a.getItemCount(); i2++) {
            ClipData.Item itemAt = this.f2635a.getItemAt(i2);
            if (predicate.test(itemAt)) {
                arrayList.add(itemAt);
            } else {
                arrayList2.add(itemAt);
            }
        }
        return arrayList.isEmpty() ? Pair.create(null, this) : arrayList2.isEmpty() ? Pair.create(this, null) : Pair.create(new Builder(this).setClip(buildClipData(this.f2635a.getDescription(), arrayList)).build(), new Builder(this).setClip(buildClipData(this.f2635a.getDescription(), arrayList2)).build());
    }

    @NonNull
    public String toString() {
        return "ContentInfoCompat{clip=" + this.f2635a + ", source=" + b(this.f2636b) + ", flags=" + a(this.f2637c) + ", linkUri=" + this.f2638d + ", extras=" + this.f2639e + "}";
    }
}
