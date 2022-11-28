package com.bumptech.glide.load.resource.drawable;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.engine.Initializable;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.util.Preconditions;
/* loaded from: classes.dex */
public abstract class DrawableResource<T extends Drawable> implements Resource<T>, Initializable {

    /* renamed from: a  reason: collision with root package name */
    protected final Drawable f4795a;

    public DrawableResource(T t2) {
        this.f4795a = (Drawable) Preconditions.checkNotNull(t2);
    }

    @Override // com.bumptech.glide.load.engine.Resource
    @NonNull
    public final T get() {
        Drawable.ConstantState constantState = this.f4795a.getConstantState();
        return constantState == null ? (T) this.f4795a : (T) constantState.newDrawable();
    }

    public void initialize() {
        Bitmap firstFrame;
        Drawable drawable = this.f4795a;
        if (drawable instanceof BitmapDrawable) {
            firstFrame = ((BitmapDrawable) drawable).getBitmap();
        } else if (!(drawable instanceof GifDrawable)) {
            return;
        } else {
            firstFrame = ((GifDrawable) drawable).getFirstFrame();
        }
        firstFrame.prepareToDraw();
    }
}
