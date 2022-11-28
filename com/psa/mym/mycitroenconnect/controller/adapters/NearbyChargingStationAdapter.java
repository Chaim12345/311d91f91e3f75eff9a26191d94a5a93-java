package com.psa.mym.mycitroenconnect.controller.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.model.NearbyStationModel;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class NearbyChargingStationAdapter extends RecyclerView.Adapter<ViewHolder> {
    @Nullable
    private NearbyChargingInterface onClickListener;
    @NotNull
    private ArrayList<NearbyStationModel> stationList;

    /* loaded from: classes3.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ NearbyChargingStationAdapter f10465a;
        @Nullable
        private LinearLayoutCompat layoutRootView;
        @Nullable
        private AppCompatTextView tvChargeRate;
        @Nullable
        private AppCompatTextView tvChargeStationsName;
        @Nullable
        private AppCompatTextView tvChargeSubLocation;
        @Nullable
        private AppCompatTextView tvChargeTime;
        @Nullable
        private AppCompatTextView tvChargeUnit;
        @Nullable
        private AppCompatTextView tvDistance;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull NearbyChargingStationAdapter nearbyChargingStationAdapter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.f10465a = nearbyChargingStationAdapter;
            this.tvDistance = (AppCompatTextView) view.findViewById(R.id.tvDistance);
            this.tvChargeStationsName = (AppCompatTextView) view.findViewById(R.id.tvChargeStationsName);
            this.tvChargeSubLocation = (AppCompatTextView) view.findViewById(R.id.tvChargeSubLocation);
            this.tvChargeTime = (AppCompatTextView) view.findViewById(R.id.tvChargeTime);
            this.tvChargeRate = (AppCompatTextView) view.findViewById(R.id.tvChargeRate);
            this.tvChargeUnit = (AppCompatTextView) view.findViewById(R.id.tvChargeUnit);
            this.layoutRootView = (LinearLayoutCompat) view.findViewById(R.id.layoutRootView);
            view.setOnClickListener(this);
        }

        @Nullable
        public final LinearLayoutCompat getLayoutRootView() {
            return this.layoutRootView;
        }

        @Nullable
        public final AppCompatTextView getTvChargeRate() {
            return this.tvChargeRate;
        }

        @Nullable
        public final AppCompatTextView getTvChargeStationsName() {
            return this.tvChargeStationsName;
        }

        @Nullable
        public final AppCompatTextView getTvChargeSubLocation() {
            return this.tvChargeSubLocation;
        }

        @Nullable
        public final AppCompatTextView getTvChargeTime() {
            return this.tvChargeTime;
        }

        @Nullable
        public final AppCompatTextView getTvChargeUnit() {
            return this.tvChargeUnit;
        }

        @Nullable
        public final AppCompatTextView getTvDistance() {
            return this.tvDistance;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(@Nullable View view) {
            NearbyChargingInterface onClickListener = this.f10465a.getOnClickListener();
            if (onClickListener != null) {
                Object obj = this.f10465a.stationList.get(getAbsoluteAdapterPosition());
                Intrinsics.checkNotNullExpressionValue(obj, "stationList[absoluteAdapterPosition]");
                onClickListener.onItemClick((NearbyStationModel) obj);
            }
        }

        public final void setLayoutRootView(@Nullable LinearLayoutCompat linearLayoutCompat) {
            this.layoutRootView = linearLayoutCompat;
        }

        public final void setTvChargeRate(@Nullable AppCompatTextView appCompatTextView) {
            this.tvChargeRate = appCompatTextView;
        }

        public final void setTvChargeStationsName(@Nullable AppCompatTextView appCompatTextView) {
            this.tvChargeStationsName = appCompatTextView;
        }

        public final void setTvChargeSubLocation(@Nullable AppCompatTextView appCompatTextView) {
            this.tvChargeSubLocation = appCompatTextView;
        }

        public final void setTvChargeTime(@Nullable AppCompatTextView appCompatTextView) {
            this.tvChargeTime = appCompatTextView;
        }

        public final void setTvChargeUnit(@Nullable AppCompatTextView appCompatTextView) {
            this.tvChargeUnit = appCompatTextView;
        }

        public final void setTvDistance(@Nullable AppCompatTextView appCompatTextView) {
            this.tvDistance = appCompatTextView;
        }
    }

    public NearbyChargingStationAdapter(@NotNull ArrayList<NearbyStationModel> stationList, @Nullable NearbyChargingInterface nearbyChargingInterface) {
        Intrinsics.checkNotNullParameter(stationList, "stationList");
        this.stationList = stationList;
        this.onClickListener = nearbyChargingInterface;
    }

    public /* synthetic */ NearbyChargingStationAdapter(ArrayList arrayList, NearbyChargingInterface nearbyChargingInterface, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(arrayList, (i2 & 2) != 0 ? null : nearbyChargingInterface);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.stationList.size();
    }

    @Nullable
    public final NearbyChargingInterface getOnClickListener() {
        return this.onClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        NearbyStationModel nearbyStationModel = this.stationList.get(holder.getAbsoluteAdapterPosition());
        Intrinsics.checkNotNullExpressionValue(nearbyStationModel, "stationList[holder.absoluteAdapterPosition]");
        NearbyStationModel nearbyStationModel2 = nearbyStationModel;
        AppCompatTextView tvChargeStationsName = holder.getTvChargeStationsName();
        if (tvChargeStationsName != null) {
            tvChargeStationsName.setText(nearbyStationModel2.getStationName());
        }
        AppCompatTextView tvChargeSubLocation = holder.getTvChargeSubLocation();
        if (tvChargeSubLocation != null) {
            tvChargeSubLocation.setText(nearbyStationModel2.getStationSubLocation());
        }
        AppCompatTextView tvChargeRate = holder.getTvChargeRate();
        if (tvChargeRate != null) {
            tvChargeRate.setText(nearbyStationModel2.getStationCharge());
        }
        AppCompatTextView tvChargeTime = holder.getTvChargeTime();
        if (tvChargeTime != null) {
            tvChargeTime.setText(nearbyStationModel2.getStationTime());
        }
        AppCompatTextView tvDistance = holder.getTvDistance();
        if (tvDistance != null) {
            tvDistance.setText(nearbyStationModel2.getStationDistance());
        }
        AppCompatTextView tvChargeUnit = holder.getTvChargeUnit();
        if (tvChargeUnit == null) {
            return;
        }
        tvChargeUnit.setText(nearbyStationModel2.getStationUnit());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(uat.psa.mym.mycitroenconnect.R.layout.cell_charging_station, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context)\n   …g_station, parent, false)");
        return new ViewHolder(this, inflate);
    }

    public final void setOnClickListener(@Nullable NearbyChargingInterface nearbyChargingInterface) {
        this.onClickListener = nearbyChargingInterface;
    }

    public final void updateStationList(@NotNull ArrayList<NearbyStationModel> serviceArrayList) {
        Intrinsics.checkNotNullParameter(serviceArrayList, "serviceArrayList");
        this.stationList = serviceArrayList;
        notifyDataSetChanged();
    }
}
