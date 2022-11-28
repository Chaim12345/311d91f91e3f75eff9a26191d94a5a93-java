package com.psa.mym.mycitroenconnect.controller.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.model.DrivingBehaviourCardData;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class DrivingBehaviourCardAdapter extends RecyclerView.Adapter<CardViewHolder> {
    @NotNull
    private final Context context;
    @NotNull
    private final List<DrivingBehaviourCardData> words;

    /* loaded from: classes3.dex */
    public static final class CardViewHolder extends RecyclerView.ViewHolder {
        @NotNull
        private final ImageView icon;
        @NotNull
        private final TextView label;
        @NotNull
        private final TextView value;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CardViewHolder(@NotNull View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.tvDbCardIcon);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.tvDbCardIcon)");
            this.icon = (ImageView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.tvDbCardValue);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.tvDbCardValue)");
            this.value = (TextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.tvDbCardLabel);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.tvDbCardLabel)");
            this.label = (TextView) findViewById3;
        }

        @NotNull
        public final ImageView getIcon() {
            return this.icon;
        }

        @NotNull
        public final TextView getLabel() {
            return this.label;
        }

        @NotNull
        public final TextView getValue() {
            return this.value;
        }
    }

    public DrivingBehaviourCardAdapter(@NotNull Context context, @NotNull List<DrivingBehaviourCardData> words) {
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
        holder.getIcon().setImageResource(this.words.get(i2).getIcon());
        holder.getValue().setText(String.valueOf(this.words.get(i2).getCount()));
        holder.getLabel().setText(this.words.get(i2).getLabel());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public CardViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.layout_db_card, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(context).inflate(R.…t_db_card, parent, false)");
        return new CardViewHolder(inflate);
    }
}
