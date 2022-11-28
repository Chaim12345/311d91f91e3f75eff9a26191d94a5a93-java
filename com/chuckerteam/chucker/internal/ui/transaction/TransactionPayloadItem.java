package com.chuckerteam.chucker.internal.ui.transaction;

import android.graphics.Bitmap;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadItem;", "", "<init>", "()V", "BodyLineItem", "HeaderItem", "ImageItem", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadItem$HeaderItem;", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadItem$BodyLineItem;", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadItem$ImageItem;", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public abstract class TransactionPayloadItem {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\bR\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadItem$BodyLineItem;", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadItem;", "Landroid/text/SpannableStringBuilder;", "line", "Landroid/text/SpannableStringBuilder;", "getLine", "()Landroid/text/SpannableStringBuilder;", "setLine", "(Landroid/text/SpannableStringBuilder;)V", "<init>", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class BodyLineItem extends TransactionPayloadItem {
        @NotNull
        private SpannableStringBuilder line;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BodyLineItem(@NotNull SpannableStringBuilder line) {
            super(null);
            Intrinsics.checkNotNullParameter(line, "line");
            this.line = line;
        }

        @NotNull
        public final SpannableStringBuilder getLine() {
            return this.line;
        }

        public final void setLine(@NotNull SpannableStringBuilder spannableStringBuilder) {
            Intrinsics.checkNotNullParameter(spannableStringBuilder, "<set-?>");
            this.line = spannableStringBuilder;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bR\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadItem$HeaderItem;", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadItem;", "Landroid/text/Spanned;", "headers", "Landroid/text/Spanned;", "getHeaders", "()Landroid/text/Spanned;", "<init>", "(Landroid/text/Spanned;)V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class HeaderItem extends TransactionPayloadItem {
        @NotNull
        private final Spanned headers;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public HeaderItem(@NotNull Spanned headers) {
            super(null);
            Intrinsics.checkNotNullParameter(headers, "headers");
            this.headers = headers;
        }

        @NotNull
        public final Spanned getHeaders() {
            return this.headers;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\f\u0010\rR\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadItem$ImageItem;", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadItem;", "Landroid/graphics/Bitmap;", "image", "Landroid/graphics/Bitmap;", "getImage", "()Landroid/graphics/Bitmap;", "", "luminance", "Ljava/lang/Double;", "getLuminance", "()Ljava/lang/Double;", "<init>", "(Landroid/graphics/Bitmap;Ljava/lang/Double;)V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class ImageItem extends TransactionPayloadItem {
        @NotNull
        private final Bitmap image;
        @Nullable
        private final Double luminance;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ImageItem(@NotNull Bitmap image, @Nullable Double d2) {
            super(null);
            Intrinsics.checkNotNullParameter(image, "image");
            this.image = image;
            this.luminance = d2;
        }

        @NotNull
        public final Bitmap getImage() {
            return this.image;
        }

        @Nullable
        public final Double getLuminance() {
            return this.luminance;
        }
    }

    private TransactionPayloadItem() {
    }

    public /* synthetic */ TransactionPayloadItem(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
