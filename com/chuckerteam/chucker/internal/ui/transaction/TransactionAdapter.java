package com.chuckerteam.chucker.internal.ui.transaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.chuckerteam.chucker.R;
import com.chuckerteam.chucker.databinding.ChuckerListItemTransactionBinding;
import com.chuckerteam.chucker.internal.data.entity.HttpTransaction;
import com.chuckerteam.chucker.internal.data.entity.HttpTransactionTuple;
import com.chuckerteam.chucker.internal.ui.transaction.ProtocolResources;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.http.message.TokenParser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0002!\"B\u001b\b\u0000\u0012\u0006\u0010\u001e\u001a\u00020\u001d\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u001a¢\u0006\u0004\b\u001f\u0010 J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\u001c\u0010\b\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0003H\u0016J\u001c\u0010\f\u001a\u00020\u000b2\n\u0010\t\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\n\u001a\u00020\u0003H\u0016J\u0014\u0010\u0010\u001a\u00020\u000b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rR\u001c\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0013\u001a\u00020\u00038\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0015\u001a\u00020\u00038\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0014R\u0016\u0010\u0016\u001a\u00020\u00038\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0014R\u0016\u0010\u0017\u001a\u00020\u00038\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0014R\u0016\u0010\u0018\u001a\u00020\u00038\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0014R\u0016\u0010\u0019\u001a\u00020\u00038\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u0014R\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u001a8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001c¨\u0006#"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionAdapter$TransactionViewHolder;", "", "getItemCount", "Landroid/view/ViewGroup;", "parent", "viewType", "onCreateViewHolder", "holder", AppConstants.ARG_POSITION, "", "onBindViewHolder", "", "Lcom/chuckerteam/chucker/internal/data/entity/HttpTransactionTuple;", "httpTransactions", "setData", "transactions", "Ljava/util/List;", "colorDefault", "I", "colorRequested", "colorError", "color500", "color400", "color300", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionAdapter$TransactionClickListListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionAdapter$TransactionClickListListener;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionAdapter$TransactionClickListListener;)V", "TransactionClickListListener", "TransactionViewHolder", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class TransactionAdapter extends RecyclerView.Adapter<TransactionViewHolder> {
    private final int color300;
    private final int color400;
    private final int color500;
    private final int colorDefault;
    private final int colorError;
    private final int colorRequested;
    private final TransactionClickListListener listener;
    private List<HttpTransactionTuple> transactions;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H&¨\u0006\b"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionAdapter$TransactionClickListListener;", "", "", "transactionId", "", AppConstants.ARG_POSITION, "", "onTransactionClick", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public interface TransactionClickListListener {
        void onTransactionClick(long j2, int i2);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0002J\u0012\u0010\f\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0007R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0012\u001a\u00020\u00118\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionAdapter$TransactionViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroid/view/View$OnClickListener;", "Lcom/chuckerteam/chucker/internal/ui/transaction/ProtocolResources;", "resources", "", "setProtocolImage", "Lcom/chuckerteam/chucker/internal/data/entity/HttpTransactionTuple;", "transaction", "setStatusColor", "Landroid/view/View;", "v", "onClick", "bind", "", "transactionId", "Ljava/lang/Long;", "Lcom/chuckerteam/chucker/databinding/ChuckerListItemTransactionBinding;", "itemBinding", "Lcom/chuckerteam/chucker/databinding/ChuckerListItemTransactionBinding;", "<init>", "(Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionAdapter;Lcom/chuckerteam/chucker/databinding/ChuckerListItemTransactionBinding;)V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public final class TransactionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ TransactionAdapter f4969a;
        private final ChuckerListItemTransactionBinding itemBinding;
        private Long transactionId;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TransactionViewHolder(@NotNull TransactionAdapter transactionAdapter, ChuckerListItemTransactionBinding itemBinding) {
            super(itemBinding.getRoot());
            Intrinsics.checkNotNullParameter(itemBinding, "itemBinding");
            this.f4969a = transactionAdapter;
            this.itemBinding = itemBinding;
            this.itemView.setOnClickListener(this);
        }

        private final void setProtocolImage(ProtocolResources protocolResources) {
            ImageView imageView = this.itemBinding.ssl;
            View itemView = this.itemView;
            Intrinsics.checkNotNullExpressionValue(itemView, "itemView");
            imageView.setImageDrawable(AppCompatResources.getDrawable(itemView.getContext(), protocolResources.getIcon()));
            ImageView imageView2 = this.itemBinding.ssl;
            View itemView2 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(itemView2, "itemView");
            ImageViewCompat.setImageTintList(imageView2, ColorStateList.valueOf(ContextCompat.getColor(itemView2.getContext(), protocolResources.getColor())));
        }

        private final void setStatusColor(HttpTransactionTuple httpTransactionTuple) {
            int i2;
            if (httpTransactionTuple.getStatus() == HttpTransaction.Status.Failed) {
                i2 = this.f4969a.colorError;
            } else if (httpTransactionTuple.getStatus() == HttpTransaction.Status.Requested) {
                i2 = this.f4969a.colorRequested;
            } else {
                if (httpTransactionTuple.getResponseCode() != null) {
                    Integer responseCode = httpTransactionTuple.getResponseCode();
                    Intrinsics.checkNotNull(responseCode);
                    if (responseCode.intValue() >= 500) {
                        i2 = this.f4969a.color500;
                    } else {
                        Integer responseCode2 = httpTransactionTuple.getResponseCode();
                        Intrinsics.checkNotNull(responseCode2);
                        if (responseCode2.intValue() >= 400) {
                            i2 = this.f4969a.color400;
                        } else {
                            Integer responseCode3 = httpTransactionTuple.getResponseCode();
                            Intrinsics.checkNotNull(responseCode3);
                            if (responseCode3.intValue() >= 300) {
                                i2 = this.f4969a.color300;
                            }
                        }
                    }
                }
                i2 = this.f4969a.colorDefault;
            }
            this.itemBinding.code.setTextColor(i2);
            this.itemBinding.path.setTextColor(i2);
        }

        @SuppressLint({"SetTextI18n"})
        public final void bind(@NotNull HttpTransactionTuple transaction) {
            String str;
            TextView size;
            Intrinsics.checkNotNullParameter(transaction, "transaction");
            this.transactionId = Long.valueOf(transaction.getId());
            ChuckerListItemTransactionBinding chuckerListItemTransactionBinding = this.itemBinding;
            TextView path = chuckerListItemTransactionBinding.path;
            Intrinsics.checkNotNullExpressionValue(path, "path");
            path.setText(transaction.getMethod() + TokenParser.SP + transaction.getFormattedPath(false));
            TextView host = chuckerListItemTransactionBinding.host;
            Intrinsics.checkNotNullExpressionValue(host, "host");
            host.setText(transaction.getHost());
            TextView timeStart = chuckerListItemTransactionBinding.timeStart;
            Intrinsics.checkNotNullExpressionValue(timeStart, "timeStart");
            timeStart.setText(DateFormat.getTimeInstance().format(transaction.getRequestDate()));
            setProtocolImage(transaction.isSsl() ? new ProtocolResources.Https() : new ProtocolResources.Http());
            if (transaction.getStatus() == HttpTransaction.Status.Complete) {
                TextView code = chuckerListItemTransactionBinding.code;
                Intrinsics.checkNotNullExpressionValue(code, "code");
                code.setText(String.valueOf(transaction.getResponseCode()));
                TextView duration = chuckerListItemTransactionBinding.duration;
                Intrinsics.checkNotNullExpressionValue(duration, "duration");
                duration.setText(transaction.getDurationString());
                size = chuckerListItemTransactionBinding.size;
                Intrinsics.checkNotNullExpressionValue(size, "size");
                str = transaction.getTotalSizeString();
            } else {
                TextView code2 = chuckerListItemTransactionBinding.code;
                Intrinsics.checkNotNullExpressionValue(code2, "code");
                str = "";
                code2.setText("");
                TextView duration2 = chuckerListItemTransactionBinding.duration;
                Intrinsics.checkNotNullExpressionValue(duration2, "duration");
                duration2.setText("");
                size = chuckerListItemTransactionBinding.size;
                Intrinsics.checkNotNullExpressionValue(size, "size");
            }
            size.setText(str);
            if (transaction.getStatus() == HttpTransaction.Status.Failed) {
                TextView code3 = chuckerListItemTransactionBinding.code;
                Intrinsics.checkNotNullExpressionValue(code3, "code");
                code3.setText("!!!");
            }
            setStatusColor(transaction);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(@Nullable View view) {
            Long l2 = this.transactionId;
            if (l2 != null) {
                long longValue = l2.longValue();
                TransactionClickListListener transactionClickListListener = this.f4969a.listener;
                if (transactionClickListListener != null) {
                    transactionClickListListener.onTransactionClick(longValue, getAdapterPosition());
                }
            }
        }
    }

    public TransactionAdapter(@NotNull Context context, @Nullable TransactionClickListListener transactionClickListListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.listener = transactionClickListListener;
        this.transactions = new ArrayList();
        this.colorDefault = ContextCompat.getColor(context, R.color.chucker_status_default);
        this.colorRequested = ContextCompat.getColor(context, R.color.chucker_status_requested);
        this.colorError = ContextCompat.getColor(context, R.color.chucker_status_error);
        this.color500 = ContextCompat.getColor(context, R.color.chucker_status_500);
        this.color400 = ContextCompat.getColor(context, R.color.chucker_status_400);
        this.color300 = ContextCompat.getColor(context, R.color.chucker_status_300);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.transactions.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull TransactionViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.transactions.get(i2));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public TransactionViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ChuckerListItemTransactionBinding inflate = ChuckerListItemTransactionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "ChuckerListItemTransacti….context), parent, false)");
        return new TransactionViewHolder(this, inflate);
    }

    public final void setData(@NotNull List<HttpTransactionTuple> httpTransactions) {
        Intrinsics.checkNotNullParameter(httpTransactions, "httpTransactions");
        this.transactions = httpTransactions;
        notifyDataSetChanged();
    }
}
