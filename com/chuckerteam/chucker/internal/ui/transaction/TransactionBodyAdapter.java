package com.chuckerteam.chucker.internal.ui.transaction;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.chuckerteam.chucker.databinding.ChuckerTransactionItemBodyLineBinding;
import com.chuckerteam.chucker.databinding.ChuckerTransactionItemHeadersBinding;
import com.chuckerteam.chucker.databinding.ChuckerTransactionItemImageBinding;
import com.chuckerteam.chucker.internal.support.SearchHighlightUtilKt;
import com.chuckerteam.chucker.internal.ui.transaction.TransactionPayloadItem;
import com.chuckerteam.chucker.internal.ui.transaction.TransactionPayloadViewHolder;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 !2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001!B\u0007¢\u0006\u0004\b \u0010\u001aJ\u0014\u0010\u0007\u001a\u00020\u00062\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003J\u0018\u0010\u000b\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tH\u0016J\u0018\u0010\u000f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\tH\u0016J\b\u0010\u0010\u001a\u00020\tH\u0016J\u0010\u0010\u0011\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J'\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\tH\u0000¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u001b\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\u0019\u0010\u001aR&\u0010\u001e\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u001cj\b\u0012\u0004\u0012\u00020\u0004`\u001d8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001f¨\u0006\""}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionBodyAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadViewHolder;", "", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadItem;", "bodyItems", "", "setItems", "holder", "", AppConstants.ARG_POSITION, "onBindViewHolder", "Landroid/view/ViewGroup;", "parent", "viewType", "onCreateViewHolder", "getItemCount", "getItemViewType", "", "newText", "backgroundColor", "foregroundColor", "highlightQueryWithColors$com_github_ChuckerTeam_Chucker_library", "(Ljava/lang/String;II)V", "highlightQueryWithColors", "resetHighlight$com_github_ChuckerTeam_Chucker_library", "()V", "resetHighlight", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", FirebaseAnalytics.Param.ITEMS, "Ljava/util/ArrayList;", "<init>", "Companion", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class TransactionBodyAdapter extends RecyclerView.Adapter<TransactionPayloadViewHolder> {
    public static final Companion Companion = new Companion(null);
    private static final int TYPE_BODY_LINE = 2;
    private static final int TYPE_HEADERS = 1;
    private static final int TYPE_IMAGE = 3;
    private final ArrayList<TransactionPayloadItem> items = new ArrayList<>();

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0007\u0010\bR\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0005\u001a\u00020\u00028\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0004R\u0016\u0010\u0006\u001a\u00020\u00028\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0006\u0010\u0004¨\u0006\t"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionBodyAdapter$Companion;", "", "", "TYPE_BODY_LINE", "I", "TYPE_HEADERS", "TYPE_IMAGE", "<init>", "()V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        TransactionPayloadItem transactionPayloadItem = this.items.get(i2);
        if (transactionPayloadItem instanceof TransactionPayloadItem.HeaderItem) {
            return 1;
        }
        if (transactionPayloadItem instanceof TransactionPayloadItem.BodyLineItem) {
            return 2;
        }
        if (transactionPayloadItem instanceof TransactionPayloadItem.ImageItem) {
            return 3;
        }
        throw new NoWhenBranchMatchedException();
    }

    public final void highlightQueryWithColors$com_github_ChuckerTeam_Chucker_library(@NotNull String newText, int i2, int i3) {
        Iterable<IndexedValue> withIndex;
        boolean contains;
        Intrinsics.checkNotNullParameter(newText, "newText");
        ArrayList<TransactionPayloadItem> arrayList = this.items;
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (obj instanceof TransactionPayloadItem.BodyLineItem) {
                arrayList2.add(obj);
            }
        }
        withIndex = CollectionsKt___CollectionsKt.withIndex(arrayList2);
        for (IndexedValue indexedValue : withIndex) {
            int component1 = indexedValue.component1();
            TransactionPayloadItem.BodyLineItem bodyLineItem = (TransactionPayloadItem.BodyLineItem) indexedValue.component2();
            contains = StringsKt__StringsKt.contains((CharSequence) bodyLineItem.getLine(), (CharSequence) newText, true);
            if (contains) {
                bodyLineItem.getLine().clearSpans();
                String spannableStringBuilder = bodyLineItem.getLine().toString();
                Intrinsics.checkNotNullExpressionValue(spannableStringBuilder, "item.line.toString()");
                bodyLineItem.setLine(SearchHighlightUtilKt.highlightWithDefinedColors(spannableStringBuilder, newText, i2, i3));
            } else {
                Object[] spans = bodyLineItem.getLine().getSpans(0, bodyLineItem.getLine().length() - 1, Object.class);
                Intrinsics.checkNotNullExpressionValue(spans, "spans");
                if (!(spans.length == 0)) {
                    bodyLineItem.getLine().clearSpans();
                }
            }
            notifyItemChanged(component1 + 1);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull TransactionPayloadViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        TransactionPayloadItem transactionPayloadItem = this.items.get(i2);
        Intrinsics.checkNotNullExpressionValue(transactionPayloadItem, "items[position]");
        holder.bind(transactionPayloadItem);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public TransactionPayloadViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        if (i2 == 1) {
            ChuckerTransactionItemHeadersBinding inflate = ChuckerTransactionItemHeadersBinding.inflate(from, parent, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "ChuckerTransactionItemHe…(inflater, parent, false)");
            return new TransactionPayloadViewHolder.HeaderViewHolder(inflate);
        } else if (i2 != 2) {
            ChuckerTransactionItemImageBinding inflate2 = ChuckerTransactionItemImageBinding.inflate(from, parent, false);
            Intrinsics.checkNotNullExpressionValue(inflate2, "ChuckerTransactionItemIm…(inflater, parent, false)");
            return new TransactionPayloadViewHolder.ImageViewHolder(inflate2);
        } else {
            ChuckerTransactionItemBodyLineBinding inflate3 = ChuckerTransactionItemBodyLineBinding.inflate(from, parent, false);
            Intrinsics.checkNotNullExpressionValue(inflate3, "ChuckerTransactionItemBo…(inflater, parent, false)");
            return new TransactionPayloadViewHolder.BodyLineViewHolder(inflate3);
        }
    }

    public final void resetHighlight$com_github_ChuckerTeam_Chucker_library() {
        Iterable<IndexedValue> withIndex;
        ArrayList<TransactionPayloadItem> arrayList = this.items;
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (obj instanceof TransactionPayloadItem.BodyLineItem) {
                arrayList2.add(obj);
            }
        }
        withIndex = CollectionsKt___CollectionsKt.withIndex(arrayList2);
        for (IndexedValue indexedValue : withIndex) {
            int component1 = indexedValue.component1();
            TransactionPayloadItem.BodyLineItem bodyLineItem = (TransactionPayloadItem.BodyLineItem) indexedValue.component2();
            Object[] spans = bodyLineItem.getLine().getSpans(0, bodyLineItem.getLine().length() - 1, Object.class);
            Intrinsics.checkNotNullExpressionValue(spans, "spans");
            if (!(spans.length == 0)) {
                bodyLineItem.getLine().clearSpans();
                notifyItemChanged(component1 + 1);
            }
        }
    }

    public final void setItems(@NotNull List<? extends TransactionPayloadItem> bodyItems) {
        Intrinsics.checkNotNullParameter(bodyItems, "bodyItems");
        this.items.clear();
        this.items.addAll(bodyItems);
        notifyDataSetChanged();
    }
}
