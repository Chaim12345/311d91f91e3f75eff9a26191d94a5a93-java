package com.psa.mym.mycitroenconnect;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.annotation.CheckResult;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestListener;
import java.io.File;
import java.net.URL;
import java.util.List;
/* loaded from: classes.dex */
public class GlideRequest<TranscodeType> extends RequestBuilder<TranscodeType> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public GlideRequest(@NonNull Glide glide, @NonNull RequestManager requestManager, @NonNull Class cls, @NonNull Context context) {
        super(glide, requestManager, cls, context);
    }

    GlideRequest(@NonNull Class cls, @NonNull RequestBuilder requestBuilder) {
        super(cls, requestBuilder);
    }

    @Override // com.bumptech.glide.RequestBuilder
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> addListener(@Nullable RequestListener<TranscodeType> requestListener) {
        return (GlideRequest) super.addListener((RequestListener) requestListener);
    }

    @Override // com.bumptech.glide.RequestBuilder, com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public /* bridge */ /* synthetic */ RequestBuilder apply(@NonNull BaseRequestOptions baseRequestOptions) {
        return apply((BaseRequestOptions<?>) baseRequestOptions);
    }

    @Override // com.bumptech.glide.RequestBuilder, com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public /* bridge */ /* synthetic */ BaseRequestOptions apply(@NonNull BaseRequestOptions baseRequestOptions) {
        return apply((BaseRequestOptions<?>) baseRequestOptions);
    }

    @Override // com.bumptech.glide.RequestBuilder, com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> apply(@NonNull BaseRequestOptions<?> baseRequestOptions) {
        return (GlideRequest) super.apply(baseRequestOptions);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    public GlideRequest<TranscodeType> autoClone() {
        return (GlideRequest) super.autoClone();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> centerCrop() {
        return (GlideRequest) super.centerCrop();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> centerInside() {
        return (GlideRequest) super.centerInside();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> circleCrop() {
        return (GlideRequest) super.circleCrop();
    }

    @Override // com.bumptech.glide.RequestBuilder, com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    public GlideRequest<TranscodeType> clone() {
        return (GlideRequest) super.clone();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public /* bridge */ /* synthetic */ BaseRequestOptions decode(@NonNull Class cls) {
        return decode((Class<?>) cls);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> decode(@NonNull Class<?> cls) {
        return (GlideRequest) super.decode(cls);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> disallowHardwareConfig() {
        return (GlideRequest) super.disallowHardwareConfig();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> diskCacheStrategy(@NonNull DiskCacheStrategy diskCacheStrategy) {
        return (GlideRequest) super.diskCacheStrategy(diskCacheStrategy);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> dontAnimate() {
        return (GlideRequest) super.dontAnimate();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> dontTransform() {
        return (GlideRequest) super.dontTransform();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> downsample(@NonNull DownsampleStrategy downsampleStrategy) {
        return (GlideRequest) super.downsample(downsampleStrategy);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> encodeFormat(@NonNull Bitmap.CompressFormat compressFormat) {
        return (GlideRequest) super.encodeFormat(compressFormat);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> encodeQuality(@IntRange(from = 0, to = 100) int i2) {
        return (GlideRequest) super.encodeQuality(i2);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> error(@DrawableRes int i2) {
        return (GlideRequest) super.error(i2);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> error(@Nullable Drawable drawable) {
        return (GlideRequest) super.error(drawable);
    }

    @Override // com.bumptech.glide.RequestBuilder
    @NonNull
    public GlideRequest<TranscodeType> error(@Nullable RequestBuilder<TranscodeType> requestBuilder) {
        return (GlideRequest) super.error((RequestBuilder) requestBuilder);
    }

    @Override // com.bumptech.glide.RequestBuilder
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> error(Object obj) {
        return (GlideRequest) super.error(obj);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> fallback(@DrawableRes int i2) {
        return (GlideRequest) super.fallback(i2);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> fallback(@Nullable Drawable drawable) {
        return (GlideRequest) super.fallback(drawable);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> fitCenter() {
        return (GlideRequest) super.fitCenter();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> format(@NonNull DecodeFormat decodeFormat) {
        return (GlideRequest) super.format(decodeFormat);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> frame(@IntRange(from = 0) long j2) {
        return (GlideRequest) super.frame(j2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bumptech.glide.RequestBuilder
    @NonNull
    @CheckResult
    /* renamed from: j */
    public GlideRequest h() {
        return new GlideRequest(File.class, this).apply((BaseRequestOptions<?>) RequestBuilder.f4663a);
    }

    @Override // com.bumptech.glide.RequestBuilder
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> listener(@Nullable RequestListener<TranscodeType> requestListener) {
        return (GlideRequest) super.listener((RequestListener) requestListener);
    }

    @Override // com.bumptech.glide.RequestBuilder
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable Bitmap bitmap) {
        return (GlideRequest) super.m26load(bitmap);
    }

    @Override // com.bumptech.glide.RequestBuilder
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable Drawable drawable) {
        return (GlideRequest) super.m27load(drawable);
    }

    @Override // com.bumptech.glide.RequestBuilder
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable Uri uri) {
        return (GlideRequest) super.m28load(uri);
    }

    @Override // com.bumptech.glide.RequestBuilder
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable File file) {
        return (GlideRequest) super.m29load(file);
    }

    @Override // com.bumptech.glide.RequestBuilder
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable @DrawableRes @RawRes Integer num) {
        return (GlideRequest) super.m30load(num);
    }

    @Override // com.bumptech.glide.RequestBuilder
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable Object obj) {
        return (GlideRequest) super.m31load(obj);
    }

    @Override // com.bumptech.glide.RequestBuilder
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable String str) {
        return (GlideRequest) super.m32load(str);
    }

    @Override // com.bumptech.glide.RequestBuilder
    @CheckResult
    @Deprecated
    public GlideRequest<TranscodeType> load(@Nullable URL url) {
        return (GlideRequest) super.m33load(url);
    }

    @Override // com.bumptech.glide.RequestBuilder
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable byte[] bArr) {
        return (GlideRequest) super.m34load(bArr);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    public GlideRequest<TranscodeType> lock() {
        return (GlideRequest) super.lock();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> onlyRetrieveFromCache(boolean z) {
        return (GlideRequest) super.onlyRetrieveFromCache(z);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> optionalCenterCrop() {
        return (GlideRequest) super.optionalCenterCrop();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> optionalCenterInside() {
        return (GlideRequest) super.optionalCenterInside();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> optionalCircleCrop() {
        return (GlideRequest) super.optionalCircleCrop();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> optionalFitCenter() {
        return (GlideRequest) super.optionalFitCenter();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public /* bridge */ /* synthetic */ BaseRequestOptions optionalTransform(@NonNull Transformation transformation) {
        return optionalTransform((Transformation<Bitmap>) transformation);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> optionalTransform(@NonNull Transformation<Bitmap> transformation) {
        return (GlideRequest) super.optionalTransform(transformation);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public <Y> GlideRequest<TranscodeType> optionalTransform(@NonNull Class<Y> cls, @NonNull Transformation<Y> transformation) {
        return (GlideRequest) super.optionalTransform((Class) cls, (Transformation) transformation);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> override(int i2) {
        return (GlideRequest) super.override(i2);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> override(int i2, int i3) {
        return (GlideRequest) super.override(i2, i3);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> placeholder(@DrawableRes int i2) {
        return (GlideRequest) super.placeholder(i2);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> placeholder(@Nullable Drawable drawable) {
        return (GlideRequest) super.placeholder(drawable);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> priority(@NonNull Priority priority) {
        return (GlideRequest) super.priority(priority);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public /* bridge */ /* synthetic */ BaseRequestOptions set(@NonNull Option option, @NonNull Object obj) {
        return set((Option<Option>) option, (Option) obj);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public <Y> GlideRequest<TranscodeType> set(@NonNull Option<Y> option, @NonNull Y y) {
        return (GlideRequest) super.set((Option<Option<Y>>) option, (Option<Y>) y);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> signature(@NonNull Key key) {
        return (GlideRequest) super.signature(key);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> sizeMultiplier(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
        return (GlideRequest) super.sizeMultiplier(f2);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> skipMemoryCache(boolean z) {
        return (GlideRequest) super.skipMemoryCache(z);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> theme(@Nullable Resources.Theme theme) {
        return (GlideRequest) super.theme(theme);
    }

    @Override // com.bumptech.glide.RequestBuilder
    @NonNull
    @CheckResult
    @Deprecated
    public GlideRequest<TranscodeType> thumbnail(float f2) {
        return (GlideRequest) super.thumbnail(f2);
    }

    @Override // com.bumptech.glide.RequestBuilder
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> thumbnail(@Nullable RequestBuilder<TranscodeType> requestBuilder) {
        return (GlideRequest) super.thumbnail((RequestBuilder) requestBuilder);
    }

    @Override // com.bumptech.glide.RequestBuilder
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> thumbnail(@Nullable List<RequestBuilder<TranscodeType>> list) {
        return (GlideRequest) super.thumbnail((List) list);
    }

    @Override // com.bumptech.glide.RequestBuilder
    @NonNull
    @SafeVarargs
    @CheckResult
    public final GlideRequest<TranscodeType> thumbnail(@Nullable RequestBuilder<TranscodeType>... requestBuilderArr) {
        return (GlideRequest) super.thumbnail((RequestBuilder[]) requestBuilderArr);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> timeout(@IntRange(from = 0) int i2) {
        return (GlideRequest) super.timeout(i2);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public /* bridge */ /* synthetic */ BaseRequestOptions transform(@NonNull Transformation transformation) {
        return transform((Transformation<Bitmap>) transformation);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public /* bridge */ /* synthetic */ BaseRequestOptions transform(@NonNull Transformation[] transformationArr) {
        return transform((Transformation<Bitmap>[]) transformationArr);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> transform(@NonNull Transformation<Bitmap> transformation) {
        return (GlideRequest) super.transform(transformation);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public <Y> GlideRequest<TranscodeType> transform(@NonNull Class<Y> cls, @NonNull Transformation<Y> transformation) {
        return (GlideRequest) super.transform((Class) cls, (Transformation) transformation);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> transform(@NonNull Transformation<Bitmap>... transformationArr) {
        return (GlideRequest) super.transform(transformationArr);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    @Deprecated
    public /* bridge */ /* synthetic */ BaseRequestOptions transforms(@NonNull Transformation[] transformationArr) {
        return transforms((Transformation<Bitmap>[]) transformationArr);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    @Deprecated
    public GlideRequest<TranscodeType> transforms(@NonNull Transformation<Bitmap>... transformationArr) {
        return (GlideRequest) super.transforms(transformationArr);
    }

    @Override // com.bumptech.glide.RequestBuilder
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> transition(@NonNull TransitionOptions<?, ? super TranscodeType> transitionOptions) {
        return (GlideRequest) super.transition((TransitionOptions) transitionOptions);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> useAnimationPool(boolean z) {
        return (GlideRequest) super.useAnimationPool(z);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> useUnlimitedSourceGeneratorsPool(boolean z) {
        return (GlideRequest) super.useUnlimitedSourceGeneratorsPool(z);
    }
}
