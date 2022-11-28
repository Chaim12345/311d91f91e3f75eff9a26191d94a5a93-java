package com.psa.mym.mycitroenconnect.controller.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.exifinterface.media.ExifInterface;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.views.ZoomImageView;
import java.util.Set;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ImagePreviewDialog extends Dialog implements View.OnClickListener {
    @NotNull
    private String imageURL;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImagePreviewDialog(@NotNull Context context, @NotNull String imageURL) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(imageURL, "imageURL");
        this.imageURL = imageURL;
    }

    public /* synthetic */ ImagePreviewDialog(Context context, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? "" : str);
    }

    private final void init() {
        if (this.imageURL.length() > 0) {
            String uri = Uri.parse(this.imageURL).buildUpon().appendQueryParameter("scale", ExifInterface.GPS_MEASUREMENT_2D).build().toString();
            Intrinsics.checkNotNullExpressionValue(uri, "uri.toString()");
            this.imageURL = uri;
        }
        ((AppCompatImageView) findViewById(R.id.ivClose)).setOnClickListener(this);
        int i2 = R.id.ivPreview;
        ((ZoomImageView) findViewById(i2)).setDebugInfoVisible(false);
        ((ZoomImageView) findViewById(i2)).setOnDrawableLoaded(new ImagePreviewDialog$init$1(this));
        ((ZoomImageView) findViewById(i2)).setSwipeToDismissEnabled(false);
        ((ZoomImageView) findViewById(i2)).setOnDismiss(ImagePreviewDialog$init$2.INSTANCE);
        ((ZoomImageView) findViewById(i2)).setDismissProgressListener(ImagePreviewDialog$init$3.INSTANCE);
        Logger logger = Logger.INSTANCE;
        logger.e("Preview URL: " + this.imageURL);
        Glide.with(getContext()).load(this.imageURL).listener(new RequestListener<Drawable>() { // from class: com.psa.mym.mycitroenconnect.controller.dialog.ImagePreviewDialog$init$4
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(@Nullable GlideException glideException, @Nullable Object obj, @Nullable Target<Drawable> target, boolean z) {
                View progress = ImagePreviewDialog.this.findViewById(R.id.progress);
                Intrinsics.checkNotNullExpressionValue(progress, "progress");
                ExtensionsKt.hide(progress);
                return false;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onResourceReady(@Nullable Drawable drawable, @Nullable Object obj, @Nullable Target<Drawable> target, @Nullable DataSource dataSource, boolean z) {
                View progress = ImagePreviewDialog.this.findViewById(R.id.progress);
                Intrinsics.checkNotNullExpressionValue(progress, "progress");
                ExtensionsKt.hide(progress);
                return false;
            }
        }).override(Integer.MIN_VALUE).into((ZoomImageView) findViewById(i2));
    }

    @NotNull
    public final Uri addUriParameter(@NotNull Uri uri, @NotNull String key, @NotNull String newValue) {
        Intrinsics.checkNotNullParameter(uri, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(newValue, "newValue");
        Set<String> queryParameterNames = uri.getQueryParameterNames();
        Uri.Builder clearQuery = uri.buildUpon().clearQuery();
        boolean z = false;
        for (String str : queryParameterNames) {
            clearQuery.appendQueryParameter(str, Intrinsics.areEqual(str, key) ? newValue : uri.getQueryParameter(str));
            if (Intrinsics.areEqual(str, key)) {
                z = true;
            }
        }
        if (!z) {
            clearQuery.appendQueryParameter(key, newValue);
        }
        Uri build = clearQuery.build();
        Intrinsics.checkNotNullExpressionValue(build, "newUri.build()");
        return build;
    }

    @NotNull
    public final String getImageURL() {
        return this.imageURL;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (Intrinsics.areEqual(view, (AppCompatImageView) findViewById(R.id.ivClose))) {
            dismiss();
        }
    }

    @Override // android.app.Dialog
    protected void onCreate(@Nullable Bundle bundle) {
        View decorView;
        super.onCreate(bundle);
        requestWindowFeature(1);
        if (getWindow() != null) {
            Window window = getWindow();
            if (window != null) {
                window.setBackgroundDrawable(new ColorDrawable(0));
            }
            Rect rect = new Rect();
            Window window2 = getWindow();
            if (window2 != null && (decorView = window2.getDecorView()) != null) {
                decorView.getWindowVisibleDisplayFrame(rect);
            }
            Window window3 = getWindow();
            if (window3 != null) {
                window3.setLayout((int) (rect.width() * 0.95f), (int) (rect.height() * 0.3f));
            }
        }
        setContentView(uat.psa.mym.mycitroenconnect.R.layout.dialog_image_preview);
        init();
    }

    public final void setImageURL(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.imageURL = str;
    }
}
