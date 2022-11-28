package com.bumptech.glide.request.target;

import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public abstract class ThumbnailImageViewTarget<T> extends ImageViewTarget<T> {
    public ThumbnailImageViewTarget(ImageView imageView) {
        super(imageView);
    }

    @Deprecated
    public ThumbnailImageViewTarget(ImageView imageView, boolean z) {
        super(imageView, z);
    }

    @Override // com.bumptech.glide.request.target.ImageViewTarget
    protected void c(@Nullable Object obj) {
        ViewGroup.LayoutParams layoutParams = ((ImageView) this.f4829a).getLayoutParams();
        Drawable d2 = d(obj);
        if (layoutParams != null && layoutParams.width > 0 && layoutParams.height > 0) {
            d2 = new FixedSizeDrawable(d2, layoutParams.width, layoutParams.height);
        }
        ((ImageView) this.f4829a).setImageDrawable(d2);
    }

    protected abstract Drawable d(Object obj);
}
