package com.psa.mym.mycitroenconnect.controller.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.model.TripCardData;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class MyTripsCardAdapter extends RecyclerView.Adapter<CardViewHolder> {
    @NotNull
    private final Context context;
    @NotNull
    private List<TripCardData> words;

    /* loaded from: classes3.dex */
    public static final class CardViewHolder extends RecyclerView.ViewHolder {
        @NotNull
        private final ImageView icon;
        @NotNull
        private final TextView title;
        @NotNull
        private final TextView tvCardUnit;
        @NotNull
        private final TextView value;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CardViewHolder(@NotNull View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.tvCardTitle);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.tvCardTitle)");
            this.title = (TextView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.tvCardValue);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.tvCardValue)");
            this.value = (TextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.tvCardIcon);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.tvCardIcon)");
            this.icon = (ImageView) findViewById3;
            View findViewById4 = itemView.findViewById(R.id.tvCardUnit);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "itemView.findViewById(R.id.tvCardUnit)");
            this.tvCardUnit = (TextView) findViewById4;
        }

        @NotNull
        public final ImageView getIcon() {
            return this.icon;
        }

        @NotNull
        public final TextView getTitle() {
            return this.title;
        }

        @NotNull
        public final TextView getTvCardUnit() {
            return this.tvCardUnit;
        }

        @NotNull
        public final TextView getValue() {
            return this.value;
        }
    }

    public MyTripsCardAdapter(@NotNull Context context, @NotNull List<TripCardData> words) {
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
        holder.getTitle().setText(this.words.get(i2).getTitle());
        holder.getValue().setText(this.words.get(i2).getValue());
        holder.getTvCardUnit().setText(this.words.get(i2).getValueSuffix());
        holder.getIcon().setImageResource(this.words.get(i2).getIcon());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public CardViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.layout_trip_card, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(context).inflate(R.…trip_card, parent, false)");
        return new CardViewHolder(inflate);
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void updateList(@NotNull List<TripCardData> tripCardDataList) {
        Intrinsics.checkNotNullParameter(tripCardDataList, "tripCardDataList");
        this.words = tripCardDataList;
        notifyDataSetChanged();
    }
}
