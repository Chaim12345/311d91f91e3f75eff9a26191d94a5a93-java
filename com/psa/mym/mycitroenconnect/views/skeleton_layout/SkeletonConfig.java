package com.psa.mym.mycitroenconnect.views.skeleton_layout;

import android.content.Context;
import androidx.annotation.ColorInt;
import androidx.core.content.ContextCompat;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.mask.SkeletonShimmerDirection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class SkeletonConfig implements SkeletonStyle {
    @NotNull
    private final ReadWriteProperty maskColor$delegate;
    @NotNull
    private final ReadWriteProperty maskCornerRadius$delegate;
    @NotNull
    private final ReadWriteProperty shimmerAngle$delegate;
    @NotNull
    private final ReadWriteProperty shimmerColor$delegate;
    @NotNull
    private final ReadWriteProperty shimmerDirection$delegate;
    @NotNull
    private final ReadWriteProperty shimmerDurationInMillis$delegate;
    @NotNull
    private final ReadWriteProperty showShimmer$delegate;
    @NotNull
    private final List<Function0<Unit>> valueObservers;

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ KProperty[] f10783a = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(SkeletonConfig.class, "maskColor", "getMaskColor()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(SkeletonConfig.class, "maskCornerRadius", "getMaskCornerRadius()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(SkeletonConfig.class, "showShimmer", "getShowShimmer()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(SkeletonConfig.class, "shimmerColor", "getShimmerColor()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(SkeletonConfig.class, "shimmerDurationInMillis", "getShimmerDurationInMillis()J", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(SkeletonConfig.class, "shimmerDirection", "getShimmerDirection()Lcom/psa/mym/mycitroenconnect/views/skeleton_layout/mask/SkeletonShimmerDirection;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(SkeletonConfig.class, "shimmerAngle", "getShimmerAngle()I", 0))};
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        /* renamed from: default  reason: not valid java name */
        public final SkeletonConfig m174default(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new SkeletonConfig(ContextCompat.getColor(context, R.color.skeleton_mask), 8.0f, true, ContextCompat.getColor(context, R.color.skeleton_shimmer), SkeletonLayout.DEFAULT_SHIMMER_DURATION_IN_MILLIS, SkeletonLayout.Companion.getDEFAULT_SHIMMER_DIRECTION(), 0);
        }
    }

    public SkeletonConfig(@ColorInt int i2, float f2, boolean z, @ColorInt int i3, long j2, @NotNull SkeletonShimmerDirection shimmerDirection, int i4) {
        Intrinsics.checkNotNullParameter(shimmerDirection, "shimmerDirection");
        this.maskColor$delegate = observable(Integer.valueOf(i2));
        this.maskCornerRadius$delegate = observable(Float.valueOf(f2));
        this.showShimmer$delegate = observable(Boolean.valueOf(z));
        this.shimmerColor$delegate = observable(Integer.valueOf(i3));
        this.shimmerDurationInMillis$delegate = observable(Long.valueOf(j2));
        this.shimmerDirection$delegate = observable(shimmerDirection);
        this.shimmerAngle$delegate = observable(Integer.valueOf(i4));
        this.valueObservers = new ArrayList();
    }

    private final <T> ReadWriteProperty<Object, T> observable(final T t2) {
        Delegates delegates = Delegates.INSTANCE;
        return new ObservableProperty<T>(t2) { // from class: com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonConfig$observable$$inlined$observable$1
            @Override // kotlin.properties.ObservableProperty
            protected void a(@NotNull KProperty property, Object obj, Object obj2) {
                Intrinsics.checkNotNullParameter(property, "property");
                this.onValueChanged();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onValueChanged() {
        Iterator<T> it = this.valueObservers.iterator();
        while (it.hasNext()) {
            ((Function0) it.next()).invoke();
        }
    }

    public final void addValueObserver(@NotNull Function0<Unit> onValueChanged) {
        Intrinsics.checkNotNullParameter(onValueChanged, "onValueChanged");
        this.valueObservers.add(onValueChanged);
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    @ColorInt
    public int getMaskColor() {
        return ((Number) this.maskColor$delegate.getValue(this, f10783a[0])).intValue();
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public float getMaskCornerRadius() {
        return ((Number) this.maskCornerRadius$delegate.getValue(this, f10783a[1])).floatValue();
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public int getShimmerAngle() {
        return ((Number) this.shimmerAngle$delegate.getValue(this, f10783a[6])).intValue();
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    @ColorInt
    public int getShimmerColor() {
        return ((Number) this.shimmerColor$delegate.getValue(this, f10783a[3])).intValue();
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    @NotNull
    public SkeletonShimmerDirection getShimmerDirection() {
        return (SkeletonShimmerDirection) this.shimmerDirection$delegate.getValue(this, f10783a[5]);
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public long getShimmerDurationInMillis() {
        return ((Number) this.shimmerDurationInMillis$delegate.getValue(this, f10783a[4])).longValue();
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public boolean getShowShimmer() {
        return ((Boolean) this.showShimmer$delegate.getValue(this, f10783a[2])).booleanValue();
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public void setMaskColor(int i2) {
        this.maskColor$delegate.setValue(this, f10783a[0], Integer.valueOf(i2));
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public void setMaskCornerRadius(float f2) {
        this.maskCornerRadius$delegate.setValue(this, f10783a[1], Float.valueOf(f2));
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public void setShimmerAngle(int i2) {
        this.shimmerAngle$delegate.setValue(this, f10783a[6], Integer.valueOf(i2));
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public void setShimmerColor(int i2) {
        this.shimmerColor$delegate.setValue(this, f10783a[3], Integer.valueOf(i2));
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public void setShimmerDirection(@NotNull SkeletonShimmerDirection skeletonShimmerDirection) {
        Intrinsics.checkNotNullParameter(skeletonShimmerDirection, "<set-?>");
        this.shimmerDirection$delegate.setValue(this, f10783a[5], skeletonShimmerDirection);
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public void setShimmerDurationInMillis(long j2) {
        this.shimmerDurationInMillis$delegate.setValue(this, f10783a[4], Long.valueOf(j2));
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public void setShowShimmer(boolean z) {
        this.showShimmer$delegate.setValue(this, f10783a[2], Boolean.valueOf(z));
    }
}
