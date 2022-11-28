package com.psa.mym.mycitroenconnect.controller.adapters;

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
public final class MyVehicleHealthAdapter extends RecyclerView.Adapter<MyVehicleStatusViewHolder> {
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
        private AppCompatTextView tvStatus;
        @NotNull
        private AppCompatTextView tvStatusLabel;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyVehicleStatusViewHolder(@NotNull MyVehicleHealthAdapter myVehicleHealthAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.tvStatusLabel);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.tvStatusLabel)");
            this.tvStatusLabel = (AppCompatTextView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.tvStatus);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.tvStatus)");
            this.tvStatus = (AppCompatTextView) findViewById2;
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
        public final AppCompatTextView getTvStatus() {
            return this.tvStatus;
        }

        @NotNull
        public final AppCompatTextView getTvStatusLabel() {
            return this.tvStatusLabel;
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

        public final void setTvStatus(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvStatus = appCompatTextView;
        }

        public final void setTvStatusLabel(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvStatusLabel = appCompatTextView;
        }
    }

    public MyVehicleHealthAdapter(@NotNull List<MyVehicleStatus> myVehicleStatusList) {
        Intrinsics.checkNotNullParameter(myVehicleStatusList, "myVehicleStatusList");
        this.myVehicleStatusList = myVehicleStatusList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.myVehicleStatusList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull MyVehicleStatusViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        MyVehicleStatus myVehicleStatus = this.myVehicleStatusList.get(i2);
        holder.getTvStatusLabel().setText(myVehicleStatus.getStatusLabel());
        holder.getTvStatus().setText(myVehicleStatus.getStatus());
        AppCompatImageView ivIconBackground = holder.getIvIconBackground();
        Context context = this.context;
        Intrinsics.checkNotNull(context);
        ivIconBackground.setColorFilter(ContextCompat.getColor(context, myVehicleStatus.getIconBackgroundColor()), PorterDuff.Mode.SRC_IN);
        AppCompatImageView ivIcon = holder.getIvIcon();
        Context context2 = this.context;
        Intrinsics.checkNotNull(context2);
        ivIcon.setImageDrawable(ContextCompat.getDrawable(context2, myVehicleStatus.getIcon()));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public MyVehicleStatusViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (this.context == null) {
            this.context = parent.getContext();
        }
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.item_view_my_vehicle_health, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(context)\n          …le_health, parent, false)");
        return new MyVehicleStatusViewHolder(this, inflate);
    }

    public final void updateList(@NotNull List<MyVehicleStatus> vehicleStatusList) {
        Intrinsics.checkNotNullParameter(vehicleStatusList, "vehicleStatusList");
        this.myVehicleStatusList = vehicleStatusList;
        notifyDataSetChanged();
    }
}
