package com.psa.mym.mycitroenconnect.controller.adapters.view_pager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewpager.widget.PagerAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import java.util.Objects;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ImageSliderAdapter extends PagerAdapter {
    @NotNull
    private Context context;
    @NotNull
    private int[] images;
    @NotNull
    private LayoutInflater mLayoutInflater;
    @NotNull
    private ImageView.ScaleType scaleType;
    private boolean shouldDisplayRounded;

    public ImageSliderAdapter(@NotNull Context context, @NotNull int[] images, @NotNull ImageView.ScaleType scaleType, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(images, "images");
        Intrinsics.checkNotNullParameter(scaleType, "scaleType");
        this.context = context;
        this.images = images;
        this.scaleType = scaleType;
        this.shouldDisplayRounded = z;
        Object systemService = context.getSystemService("layout_inflater");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.view.LayoutInflater");
        this.mLayoutInflater = (LayoutInflater) systemService;
    }

    public /* synthetic */ ImageSliderAdapter(Context context, int[] iArr, ImageView.ScaleType scaleType, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, iArr, (i2 & 4) != 0 ? ImageView.ScaleType.FIT_XY : scaleType, (i2 & 8) != 0 ? false : z);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(@NotNull ViewGroup container, int i2, @NotNull Object object) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(object, "object");
        container.removeView((LinearLayout) object);
    }

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.images.length;
    }

    @NotNull
    public final ImageView.ScaleType getScaleType() {
        return this.scaleType;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    @NotNull
    public Object instantiateItem(@NotNull ViewGroup container, int i2) {
        View inflate;
        Intrinsics.checkNotNullParameter(container, "container");
        if (this.shouldDisplayRounded) {
            inflate = this.mLayoutInflater.inflate(R.layout.cell_non_vin_pager_item_rounded, container, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "mLayoutInflater.inflate(…ounded, container, false)");
            View findViewById = inflate.findViewById(R.id.imageViewMain);
            Objects.requireNonNull(findViewById, "null cannot be cast to non-null type android.widget.ImageView");
            ImageView imageView = (ImageView) findViewById;
            imageView.setScaleType(this.scaleType);
            Glide.with(this.context).load(Integer.valueOf(this.images[i2])).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new RoundedCorners(20))).into(imageView);
        } else {
            inflate = this.mLayoutInflater.inflate(R.layout.cell_non_vin_pager_item_1, container, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "mLayoutInflater.inflate(…item_1, container, false)");
            View findViewById2 = inflate.findViewById(R.id.imageViewMain);
            Objects.requireNonNull(findViewById2, "null cannot be cast to non-null type android.widget.ImageView");
            ImageView imageView2 = (ImageView) findViewById2;
            imageView2.setScaleType(this.scaleType);
            imageView2.setImageResource(this.images[i2]);
        }
        Objects.requireNonNull(container);
        container.addView(inflate);
        return inflate;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(@NotNull View view, @NotNull Object object) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(object, "object");
        return view == ((LinearLayout) object);
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.context = context;
    }

    public final void setScaleType(@NotNull ImageView.ScaleType scaleType) {
        Intrinsics.checkNotNullParameter(scaleType, "<set-?>");
        this.scaleType = scaleType;
    }

    public final void updateImageList(@NotNull int[] images) {
        Intrinsics.checkNotNullParameter(images, "images");
        this.images = images;
        notifyDataSetChanged();
    }
}
