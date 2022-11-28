package com.chuckerteam.chucker.internal.ui.transaction;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.chuckerteam.chucker.R;
import com.chuckerteam.chucker.databinding.ChuckerTransactionItemBodyLineBinding;
import com.chuckerteam.chucker.databinding.ChuckerTransactionItemHeadersBinding;
import com.chuckerteam.chucker.databinding.ChuckerTransactionItemImageBinding;
import com.chuckerteam.chucker.internal.support.ChessboardDrawable;
import com.chuckerteam.chucker.internal.ui.transaction.TransactionPayloadItem;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0003\n\u000b\fB\u0011\b\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&\u0082\u0001\u0003\r\u000e\u000f¨\u0006\u0010"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadItem;", "item", "", "bind", "Landroid/view/View;", "view", "<init>", "(Landroid/view/View;)V", "BodyLineViewHolder", "HeaderViewHolder", "ImageViewHolder", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadViewHolder$HeaderViewHolder;", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadViewHolder$BodyLineViewHolder;", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadViewHolder$ImageViewHolder;", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public abstract class TransactionPayloadViewHolder extends RecyclerView.ViewHolder {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R\u0016\u0010\u0007\u001a\u00020\u00068\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadViewHolder$BodyLineViewHolder;", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadViewHolder;", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadItem;", "item", "", "bind", "Lcom/chuckerteam/chucker/databinding/ChuckerTransactionItemBodyLineBinding;", "bodyBinding", "Lcom/chuckerteam/chucker/databinding/ChuckerTransactionItemBodyLineBinding;", "<init>", "(Lcom/chuckerteam/chucker/databinding/ChuckerTransactionItemBodyLineBinding;)V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class BodyLineViewHolder extends TransactionPayloadViewHolder {
        private final ChuckerTransactionItemBodyLineBinding bodyBinding;

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public BodyLineViewHolder(@NotNull ChuckerTransactionItemBodyLineBinding bodyBinding) {
            super(r0, null);
            Intrinsics.checkNotNullParameter(bodyBinding, "bodyBinding");
            TextView root = bodyBinding.getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "bodyBinding.root");
            this.bodyBinding = bodyBinding;
        }

        @Override // com.chuckerteam.chucker.internal.ui.transaction.TransactionPayloadViewHolder
        public void bind(@NotNull TransactionPayloadItem item) {
            Intrinsics.checkNotNullParameter(item, "item");
            if (item instanceof TransactionPayloadItem.BodyLineItem) {
                TextView textView = this.bodyBinding.bodyLine;
                Intrinsics.checkNotNullExpressionValue(textView, "bodyBinding.bodyLine");
                textView.setText(((TransactionPayloadItem.BodyLineItem) item).getLine());
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R\u0016\u0010\u0007\u001a\u00020\u00068\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadViewHolder$HeaderViewHolder;", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadViewHolder;", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadItem;", "item", "", "bind", "Lcom/chuckerteam/chucker/databinding/ChuckerTransactionItemHeadersBinding;", "headerBinding", "Lcom/chuckerteam/chucker/databinding/ChuckerTransactionItemHeadersBinding;", "<init>", "(Lcom/chuckerteam/chucker/databinding/ChuckerTransactionItemHeadersBinding;)V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class HeaderViewHolder extends TransactionPayloadViewHolder {
        private final ChuckerTransactionItemHeadersBinding headerBinding;

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public HeaderViewHolder(@NotNull ChuckerTransactionItemHeadersBinding headerBinding) {
            super(r0, null);
            Intrinsics.checkNotNullParameter(headerBinding, "headerBinding");
            TextView root = headerBinding.getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "headerBinding.root");
            this.headerBinding = headerBinding;
        }

        @Override // com.chuckerteam.chucker.internal.ui.transaction.TransactionPayloadViewHolder
        public void bind(@NotNull TransactionPayloadItem item) {
            Intrinsics.checkNotNullParameter(item, "item");
            if (item instanceof TransactionPayloadItem.HeaderItem) {
                TextView textView = this.headerBinding.responseHeaders;
                Intrinsics.checkNotNullExpressionValue(textView, "headerBinding.responseHeaders");
                textView.setText(((TransactionPayloadItem.HeaderItem) item).getHeaders());
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u000f\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0016R\u0016\u0010\f\u001a\u00020\u000b8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\r¨\u0006\u0011"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadViewHolder$ImageViewHolder;", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadViewHolder;", "", "luminance", "Landroid/graphics/drawable/Drawable;", "createContrastingBackground", "(Ljava/lang/Double;)Landroid/graphics/drawable/Drawable;", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadItem;", "item", "", "bind", "Lcom/chuckerteam/chucker/databinding/ChuckerTransactionItemImageBinding;", "imageBinding", "Lcom/chuckerteam/chucker/databinding/ChuckerTransactionItemImageBinding;", "<init>", "(Lcom/chuckerteam/chucker/databinding/ChuckerTransactionItemImageBinding;)V", "Companion", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class ImageViewHolder extends TransactionPayloadViewHolder {
        private static final Companion Companion = new Companion(null);
        public static final double LUMINANCE_THRESHOLD = 0.25d;
        private final ChuckerTransactionItemImageBinding imageBinding;

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004¨\u0006\u0007"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadViewHolder$ImageViewHolder$Companion;", "", "", "LUMINANCE_THRESHOLD", "D", "<init>", "()V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
        /* loaded from: classes.dex */
        private static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public ImageViewHolder(@NotNull ChuckerTransactionItemImageBinding imageBinding) {
            super(r0, null);
            Intrinsics.checkNotNullParameter(imageBinding, "imageBinding");
            FrameLayout root = imageBinding.getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "imageBinding.root");
            this.imageBinding = imageBinding;
        }

        private final Drawable createContrastingBackground(Double d2) {
            ChessboardDrawable.Companion companion;
            Context context;
            int i2;
            int i3;
            if (d2 == null) {
                return null;
            }
            if (d2.doubleValue() < 0.25d) {
                companion = ChessboardDrawable.Companion;
                View itemView = this.itemView;
                Intrinsics.checkNotNullExpressionValue(itemView, "itemView");
                context = itemView.getContext();
                Intrinsics.checkNotNullExpressionValue(context, "itemView.context");
                i2 = R.color.chucker_chessboard_even_square_light;
                i3 = R.color.chucker_chessboard_odd_square_light;
            } else {
                companion = ChessboardDrawable.Companion;
                View itemView2 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(itemView2, "itemView");
                context = itemView2.getContext();
                Intrinsics.checkNotNullExpressionValue(context, "itemView.context");
                i2 = R.color.chucker_chessboard_even_square_dark;
                i3 = R.color.chucker_chessboard_odd_square_dark;
            }
            return companion.createPattern(context, i2, i3, R.dimen.chucker_half_grid);
        }

        @Override // com.chuckerteam.chucker.internal.ui.transaction.TransactionPayloadViewHolder
        public void bind(@NotNull TransactionPayloadItem item) {
            Intrinsics.checkNotNullParameter(item, "item");
            if (item instanceof TransactionPayloadItem.ImageItem) {
                TransactionPayloadItem.ImageItem imageItem = (TransactionPayloadItem.ImageItem) item;
                this.imageBinding.binaryData.setImageBitmap(imageItem.getImage());
                FrameLayout root = this.imageBinding.getRoot();
                Intrinsics.checkNotNullExpressionValue(root, "imageBinding.root");
                root.setBackground(createContrastingBackground(imageItem.getLuminance()));
            }
        }
    }

    private TransactionPayloadViewHolder(View view) {
        super(view);
    }

    public /* synthetic */ TransactionPayloadViewHolder(View view, DefaultConstructorMarker defaultConstructorMarker) {
        this(view);
    }

    public abstract void bind(@NotNull TransactionPayloadItem transactionPayloadItem);
}
