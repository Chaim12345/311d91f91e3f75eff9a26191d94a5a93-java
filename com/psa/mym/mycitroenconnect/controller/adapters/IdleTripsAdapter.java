package com.psa.mym.mycitroenconnect.controller.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.model.trip.TripResponseDTOX;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class IdleTripsAdapter extends RecyclerView.Adapter<CardViewHolder> {
    @NotNull
    private final Context context;
    @NotNull
    private final String vehicleType;
    @NotNull
    private final List<TripResponseDTOX> words;

    /* loaded from: classes3.dex */
    public static final class CardViewHolder extends RecyclerView.ViewHolder {
        @NotNull
        private final TextView idleTripDateTime;
        @NotNull
        private final View idlingTripDivider;
        @NotNull
        private final TextView tvIdleTripCount;
        @NotNull
        private final TextView tvIdlingLossValue;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CardViewHolder(@NotNull View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.idleTripDateTime);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.idleTripDateTime)");
            this.idleTripDateTime = (TextView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.tvIdleTripCount);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.tvIdleTripCount)");
            this.tvIdleTripCount = (TextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.tvIdlingLossValue);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.tvIdlingLossValue)");
            this.tvIdlingLossValue = (TextView) findViewById3;
            View findViewById4 = itemView.findViewById(R.id.idlingTripDivider);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "itemView.findViewById(R.id.idlingTripDivider)");
            this.idlingTripDivider = findViewById4;
        }

        @NotNull
        public final TextView getIdleTripDateTime() {
            return this.idleTripDateTime;
        }

        @NotNull
        public final View getIdlingTripDivider() {
            return this.idlingTripDivider;
        }

        @NotNull
        public final TextView getTvIdleTripCount() {
            return this.tvIdleTripCount;
        }

        @NotNull
        public final TextView getTvIdlingLossValue() {
            return this.tvIdlingLossValue;
        }
    }

    public IdleTripsAdapter(@NotNull Context context, @NotNull List<TripResponseDTOX> words, @NotNull String vehicleType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(words, "words");
        Intrinsics.checkNotNullParameter(vehicleType, "vehicleType");
        this.context = context;
        this.words = words;
        this.vehicleType = vehicleType;
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
        TextView tvIdlingLossValue;
        StringBuilder sb;
        String str;
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getIdleTripDateTime().setText(AppUtil.Companion.convertDate(this.words.get(i2).getTripStartDate()));
        holder.getTvIdleTripCount().setText(this.words.get(i2).getTripName());
        if (this.vehicleType.equals(AppConstants.ICE)) {
            tvIdlingLossValue = holder.getTvIdlingLossValue();
            sb = new StringBuilder();
            sb.append((int) this.words.get(i2).getIdleEnergyConsumption());
            str = " L";
        } else {
            tvIdlingLossValue = holder.getTvIdlingLossValue();
            sb = new StringBuilder();
            sb.append((int) this.words.get(i2).getIdleEnergyConsumption());
            str = " kw";
        }
        sb.append(str);
        tvIdlingLossValue.setText(sb.toString());
        if (i2 == this.words.size() - 1) {
            holder.getIdlingTripDivider().setVisibility(8);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public CardViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.layout_idling_trip, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(context).inflate(R.…ling_trip, parent, false)");
        return new CardViewHolder(inflate);
    }
}
