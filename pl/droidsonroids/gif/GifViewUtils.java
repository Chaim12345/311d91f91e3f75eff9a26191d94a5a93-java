package pl.droidsonroids.gif;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.RawRes;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.bouncycastle.tls.CipherSuite;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public final class GifViewUtils {

    /* renamed from: a  reason: collision with root package name */
    static final List f15273a = Arrays.asList("raw", "drawable", "mipmap");

    /* loaded from: classes4.dex */
    static class GifImageViewAttributes extends GifViewAttributes {

        /* renamed from: c  reason: collision with root package name */
        final int f15274c;

        /* renamed from: d  reason: collision with root package name */
        final int f15275d;

        GifImageViewAttributes() {
            this.f15274c = 0;
            this.f15275d = 0;
        }

        GifImageViewAttributes(ImageView imageView, AttributeSet attributeSet, int i2, int i3) {
            super(imageView, attributeSet, i2, i3);
            this.f15274c = getResourceId(imageView, attributeSet, true);
            this.f15275d = getResourceId(imageView, attributeSet, false);
        }

        private static int getResourceId(ImageView imageView, AttributeSet attributeSet, boolean z) {
            int attributeResourceValue = attributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res/android", z ? "src" : "background", 0);
            if (attributeResourceValue > 0) {
                if (GifViewUtils.f15273a.contains(imageView.getResources().getResourceTypeName(attributeResourceValue)) && !GifViewUtils.e(imageView, z, attributeResourceValue)) {
                    return attributeResourceValue;
                }
            }
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class GifViewAttributes {

        /* renamed from: a  reason: collision with root package name */
        boolean f15276a;

        /* renamed from: b  reason: collision with root package name */
        final int f15277b;

        /* JADX INFO: Access modifiers changed from: package-private */
        public GifViewAttributes() {
            this.f15276a = false;
            this.f15277b = -1;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public GifViewAttributes(View view, AttributeSet attributeSet, int i2, int i3) {
            TypedArray obtainStyledAttributes = view.getContext().obtainStyledAttributes(attributeSet, R.styleable.GifView, i2, i3);
            this.f15276a = obtainStyledAttributes.getBoolean(R.styleable.GifView_freezesAnimation, false);
            this.f15277b = obtainStyledAttributes.getInt(R.styleable.GifView_loopCount, -1);
            obtainStyledAttributes.recycle();
        }
    }

    private GifViewUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(int i2, Drawable drawable) {
        if (drawable instanceof GifDrawable) {
            ((GifDrawable) drawable).setLoopCount(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float b(@NonNull Resources resources, @DrawableRes @RawRes int i2) {
        TypedValue typedValue = new TypedValue();
        resources.getValue(i2, typedValue, true);
        int i3 = typedValue.density;
        if (i3 == 0) {
            i3 = CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256;
        } else if (i3 == 65535) {
            i3 = 0;
        }
        int i4 = resources.getDisplayMetrics().densityDpi;
        if (i3 <= 0 || i4 <= 0) {
            return 1.0f;
        }
        return i4 / i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GifImageViewAttributes c(ImageView imageView, AttributeSet attributeSet, int i2, int i3) {
        if (attributeSet == null || imageView.isInEditMode()) {
            return new GifImageViewAttributes();
        }
        GifImageViewAttributes gifImageViewAttributes = new GifImageViewAttributes(imageView, attributeSet, i2, i3);
        int i4 = gifImageViewAttributes.f15277b;
        if (i4 >= 0) {
            a(i4, imageView.getDrawable());
            a(i4, imageView.getBackground());
        }
        return gifImageViewAttributes;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean d(ImageView imageView, Uri uri) {
        if (uri != null) {
            try {
                imageView.setImageDrawable(new GifDrawable(imageView.getContext().getContentResolver(), uri));
                return true;
            } catch (IOException unused) {
                return false;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean e(ImageView imageView, boolean z, int i2) {
        Resources resources = imageView.getResources();
        if (resources != null) {
            try {
                if (f15273a.contains(resources.getResourceTypeName(i2))) {
                    GifDrawable gifDrawable = new GifDrawable(resources, i2);
                    if (z) {
                        imageView.setImageDrawable(gifDrawable);
                        return true;
                    }
                    imageView.setBackground(gifDrawable);
                    return true;
                }
                return false;
            } catch (Resources.NotFoundException | IOException unused) {
            }
        }
        return false;
    }
}
