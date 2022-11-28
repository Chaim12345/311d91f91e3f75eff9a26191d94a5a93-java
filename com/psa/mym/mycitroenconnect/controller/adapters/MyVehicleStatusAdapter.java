package com.psa.mym.mycitroenconnect.controller.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.model.my_car.MyVehicleStatus;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class MyVehicleStatusAdapter extends RecyclerView.Adapter<MyVehicleStatusViewHolder> {
    @Nullable
    private Context context;
    @NotNull
    private List<MyVehicleStatus> myVehicleStatusList;

    /* loaded from: classes3.dex */
    public final class MyVehicleStatusViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @NotNull
        private AppCompatImageView ivIcon;
        @NotNull
        private AppCompatImageView ivIconBackground;
        @NotNull
        private AppCompatTextView tvLabel;
        @NotNull
        private AppCompatTextView tvValue;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyVehicleStatusViewHolder(@NotNull MyVehicleStatusAdapter myVehicleStatusAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.tvLabel);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.tvLabel)");
            this.tvLabel = (AppCompatTextView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.tvValue);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.tvValue)");
            this.tvValue = (AppCompatTextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.ivIcon);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.ivIcon)");
            this.ivIcon = (AppCompatImageView) findViewById3;
            View findViewById4 = itemView.findViewById(R.id.ivIconBackground);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "itemView.findViewById(R.id.ivIconBackground)");
            this.ivIconBackground = (AppCompatImageView) findViewById4;
        }

        @NotNull
        public final AppCompatImageView getIvIcon() {
            return this.ivIcon;
        }

        @NotNull
        public final AppCompatImageView getIvIconBackground() {
            return this.ivIconBackground;
        }

        @NotNull
        public final AppCompatTextView getTvLabel() {
            return this.tvLabel;
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

        public final void setIvIconBackground(@NotNull AppCompatImageView appCompatImageView) {
            Intrinsics.checkNotNullParameter(appCompatImageView, "<set-?>");
            this.ivIconBackground = appCompatImageView;
        }

        public final void setTvLabel(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvLabel = appCompatTextView;
        }

        public final void setTvValue(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvValue = appCompatTextView;
        }
    }

    public MyVehicleStatusAdapter(@NotNull List<MyVehicleStatus> myVehicleStatusList) {
        Intrinsics.checkNotNullParameter(myVehicleStatusList, "myVehicleStatusList");
        this.myVehicleStatusList = myVehicleStatusList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.myVehicleStatusList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull MyVehicleStatusViewHolder holder, int i2) {
        boolean areEqual;
        Context context;
        int i3;
        Intrinsics.checkNotNullParameter(holder, "holder");
        MyVehicleStatus myVehicleStatus = this.myVehicleStatusList.get(i2);
        holder.getTvLabel().setText(myVehicleStatus.getStatusLabel());
        holder.getTvValue().setText(myVehicleStatus.getStatus());
        AppCompatImageView ivIconBackground = holder.getIvIconBackground();
        Context context2 = this.context;
        Intrinsics.checkNotNull(context2);
        ivIconBackground.setColorFilter(ContextCompat.getColor(context2, myVehicleStatus.getIconBackgroundColor()), PorterDuff.Mode.SRC_IN);
        AppCompatImageView ivIcon = holder.getIvIcon();
        Context context3 = this.context;
        Intrinsics.checkNotNull(context3);
        ivIcon.setImageDrawable(ContextCompat.getDrawable(context3, myVehicleStatus.getIcon()));
        String status = myVehicleStatus.getStatus();
        Context context4 = this.context;
        if (Intrinsics.areEqual(status, context4 != null ? context4.getString(R.string.lbl_on) : null)) {
            areEqual = true;
        } else {
            Context context5 = this.context;
            areEqual = Intrinsics.areEqual(status, context5 != null ? context5.getString(R.string.open) : null);
        }
        AppCompatImageView ivIcon2 = holder.getIvIcon();
        if (areEqual) {
            context = this.context;
            Intrinsics.checkNotNull(context);
            i3 = R.color.primary_color_1;
        } else {
            context = this.context;
            Intrinsics.checkNotNull(context);
            i3 = R.color.hot_grey_70;
        }
        ivIcon2.setColorFilter(ContextCompat.getColor(context, i3), PorterDuff.Mode.SRC_IN);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public MyVehicleStatusViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (this.context == null) {
            this.context = parent.getContext();
        }
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.item_view_my_vehicle_status, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(context)\n          …le_status, parent, false)");
        return new MyVehicleStatusViewHolder(this, inflate);
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void updateList(@NotNull List<MyVehicleStatus> vehicleStatusList) {
        Intrinsics.checkNotNullParameter(vehicleStatusList, "vehicleStatusList");
        this.myVehicleStatusList = vehicleStatusList;
        notifyDataSetChanged();
    }
}
