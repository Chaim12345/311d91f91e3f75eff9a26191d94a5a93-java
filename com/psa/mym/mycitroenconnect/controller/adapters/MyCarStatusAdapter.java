package com.psa.mym.mycitroenconnect.controller.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.model.my_car.MyCarStatus;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class MyCarStatusAdapter extends RecyclerView.Adapter<MyCarStatusViewHolder> {
    @Nullable
    private Context context;
    @NotNull
    private List<MyCarStatus> myCarStatusList;

    /* loaded from: classes3.dex */
    public final class MyCarStatusViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @NotNull
        private AppCompatImageView ivIcon;
        @NotNull
        private AppCompatTextView tvTitle;
        @NotNull
        private AppCompatTextView tvUnit;
        @NotNull
        private AppCompatTextView tvValue;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyCarStatusViewHolder(@NotNull MyCarStatusAdapter myCarStatusAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.tvTitle);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.tvTitle)");
            this.tvTitle = (AppCompatTextView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.tvValue);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.tvValue)");
            this.tvValue = (AppCompatTextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.tvUnit);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.tvUnit)");
            this.tvUnit = (AppCompatTextView) findViewById3;
            View findViewById4 = itemView.findViewById(R.id.ivIcon);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "itemView.findViewById(R.id.ivIcon)");
            this.ivIcon = (AppCompatImageView) findViewById4;
        }

        @NotNull
        public final AppCompatImageView getIvIcon() {
            return this.ivIcon;
        }

        @NotNull
        public final AppCompatTextView getTvTitle() {
            return this.tvTitle;
        }

        @NotNull
        public final AppCompatTextView getTvUnit() {
            return this.tvUnit;
        }

        @NotNull
        public final AppCompatTextView getTvValue() {
            return this.tvValue;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(@Nullable View view) {
        }

        public final void setIvIcon(@NotNull AppCompatImageView appCompatImageView) {
            Intrinsics.checkNotNullParameter(appCompatImageView, "<set-?>");
            this.ivIcon = appCompatImageView;
        }

        public final void setTvTitle(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvTitle = appCompatTextView;
        }

        public final void setTvUnit(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvUnit = appCompatTextView;
        }

        public final void setTvValue(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvValue = appCompatTextView;
        }
    }

    public MyCarStatusAdapter(@NotNull List<MyCarStatus> myCarStatusList) {
        Intrinsics.checkNotNullParameter(myCarStatusList, "myCarStatusList");
        this.myCarStatusList = myCarStatusList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.myCarStatusList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull MyCarStatusViewHolder holderMy, int i2) {
        Intrinsics.checkNotNullParameter(holderMy, "holderMy");
        MyCarStatus myCarStatus = this.myCarStatusList.get(i2);
        holderMy.getTvTitle().setText(myCarStatus.getTitle());
        holderMy.getTvValue().setText(myCarStatus.getValue());
        holderMy.getTvUnit().setText(myCarStatus.getUnit());
        AppCompatImageView ivIcon = holderMy.getIvIcon();
        Context context = this.context;
        ivIcon.setImageDrawable(context != null ? ContextCompat.getDrawable(context, myCarStatus.getIcon()) : null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public MyCarStatusViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (this.context == null) {
            this.context = parent.getContext();
        }
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.item_view_my_car_status, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(context)\n          …ar_status, parent, false)");
        return new MyCarStatusViewHolder(this, inflate);
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void updateList(@NotNull List<MyCarStatus> myCarStatusList) {
        Intrinsics.checkNotNullParameter(myCarStatusList, "myCarStatusList");
        this.myCarStatusList = myCarStatusList;
        notifyDataSetChanged();
    }
}
