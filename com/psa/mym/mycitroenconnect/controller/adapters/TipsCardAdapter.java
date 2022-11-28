package com.psa.mym.mycitroenconnect.controller.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.model.TipsCardData;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class TipsCardAdapter extends RecyclerView.Adapter<CardViewHolder> {
    @NotNull
    private final Context context;
    @NotNull
    private final List<TipsCardData> words;

    /* loaded from: classes3.dex */
    public static final class CardViewHolder extends RecyclerView.ViewHolder {
        @NotNull
        private final TextView tipDesc;
        @NotNull
        private final TextView tipTitle;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CardViewHolder(@NotNull View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.tvDbTipsTitle);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.tvDbTipsTitle)");
            this.tipTitle = (TextView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.tvDbTipsDesc);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.tvDbTipsDesc)");
            this.tipDesc = (TextView) findViewById2;
        }

        @NotNull
        public final TextView getTipDesc() {
            return this.tipDesc;
        }

        @NotNull
        public final TextView getTipTitle() {
            return this.tipTitle;
        }
    }

    public TipsCardAdapter(@NotNull Context context, @NotNull List<TipsCardData> words) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(words, "words");
        this.context = context;
        this.words = words;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.words.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        return i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull CardViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getTipTitle().setText(this.words.get(i2).getTitle());
        holder.getTipDesc().setText(this.words.get(i2).getDescription());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public CardViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.layout_db_tips_card, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(context).inflate(R.…tips_card, parent, false)");
        return new CardViewHolder(inflate);
    }
}
