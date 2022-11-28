package com.psa.mym.mycitroenconnect.controller.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.model.secondary_user.SharedVehicle;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ChildAccountInvitationAdapter extends RecyclerView.Adapter<CardViewHolder> {
    @NotNull
    private final Context context;
    @NotNull
    private final ArrayList<SharedVehicle> sharedVehicleList;

    /* loaded from: classes3.dex */
    public static final class CardViewHolder extends RecyclerView.ViewHolder {
        @NotNull
        private final AppCompatImageView ivCar;
        @NotNull
        private final TextView tvMobileNumber;
        @NotNull
        private final TextView tvModelName;
        @NotNull
        private final TextView tvOwnerName;
        @NotNull
        private final TextView tvRegistrationNumber;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CardViewHolder(@NotNull View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.tvModelName);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.tvModelName)");
            this.tvModelName = (TextView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.tvRegistrationNumber);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.tvRegistrationNumber)");
            this.tvRegistrationNumber = (TextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.tvMobileNumber);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.tvMobileNumber)");
            this.tvMobileNumber = (TextView) findViewById3;
            View findViewById4 = itemView.findViewById(R.id.tvOwnerName);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "itemView.findViewById(R.id.tvOwnerName)");
            this.tvOwnerName = (TextView) findViewById4;
            View findViewById5 = itemView.findViewById(R.id.ivCar);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "itemView.findViewById(R.id.ivCar)");
            this.ivCar = (AppCompatImageView) findViewById5;
        }

        @NotNull
        public final AppCompatImageView getIvCar() {
            return this.ivCar;
        }

        @NotNull
        public final TextView getTvMobileNumber() {
            return this.tvMobileNumber;
        }

        @NotNull
        public final TextView getTvModelName() {
            return this.tvModelName;
        }

        @NotNull
        public final TextView getTvOwnerName() {
            return this.tvOwnerName;
        }

        @NotNull
        public final TextView getTvRegistrationNumber() {
            return this.tvRegistrationNumber;
        }
    }

    public ChildAccountInvitationAdapter(@NotNull Context context, @NotNull ArrayList<SharedVehicle> sharedVehicleList) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sharedVehicleList, "sharedVehicleList");
        this.context = context;
        this.sharedVehicleList = sharedVehicleList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.sharedVehicleList.size();
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
        String str;
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getTvModelName().setText(this.sharedVehicleList.get(i2).getCarModelName());
        holder.getTvRegistrationNumber().setText(this.sharedVehicleList.get(i2).getVehicleRegNo());
        String mobileNum = this.sharedVehicleList.get(i2).getMobileNum();
        if (mobileNum != null) {
            str = mobileNum.substring(8, mobileNum.length());
            Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
        } else {
            str = null;
        }
        TextView tvMobileNumber = holder.getTvMobileNumber();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = this.context.getString(R.string.mask_mobile_number);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.mask_mobile_number)");
        String format = String.format(string, Arrays.copyOf(new Object[]{str}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        tvMobileNumber.setText(format);
        holder.getTvOwnerName().setText(this.sharedVehicleList.get(i2).getOwnerName());
        holder.getIvCar().setImageResource(this.sharedVehicleList.get(i2).getCarImage());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public CardViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.cell_child_account_invitation, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(context)\n          …nvitation, parent, false)");
        return new CardViewHolder(inflate);
    }
}
