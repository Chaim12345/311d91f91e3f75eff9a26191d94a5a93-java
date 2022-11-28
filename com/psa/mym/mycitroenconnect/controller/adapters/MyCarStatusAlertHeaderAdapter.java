package com.psa.mym.mycitroenconnect.controller.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.model.my_car.VehicleAlerts;
import com.psa.mym.mycitroenconnect.model.notification.Notification;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import java.util.Comparator;
import java.util.List;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class MyCarStatusAlertHeaderAdapter extends RecyclerView.Adapter<MyCarStatusAlertViewHolder> {
    @Nullable
    private Context context;
    @NotNull
    private List<VehicleAlerts> vehicleAlerts;

    /* loaded from: classes3.dex */
    public final class MyCarStatusAlertViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ MyCarStatusAlertHeaderAdapter f10463a;
        @NotNull
        private AppCompatImageView ivExpandCollapse;
        @NotNull
        private LinearLayoutCompat llHeader;
        @NotNull
        private RecyclerView rvAlertChild;
        @NotNull
        private AppCompatTextView tvAlertCount;
        @NotNull
        private AppCompatTextView tvAlertLbl;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyCarStatusAlertViewHolder(@NotNull MyCarStatusAlertHeaderAdapter myCarStatusAlertHeaderAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.f10463a = myCarStatusAlertHeaderAdapter;
            View findViewById = itemView.findViewById(R.id.tvAlertCount);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.tvAlertCount)");
            this.tvAlertCount = (AppCompatTextView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.tvAlertLbl);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.tvAlertLbl)");
            this.tvAlertLbl = (AppCompatTextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.ivExpandCollapse);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.ivExpandCollapse)");
            this.ivExpandCollapse = (AppCompatImageView) findViewById3;
            View findViewById4 = itemView.findViewById(R.id.rvAlertChild);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "itemView.findViewById(R.id.rvAlertChild)");
            this.rvAlertChild = (RecyclerView) findViewById4;
            View findViewById5 = itemView.findViewById(R.id.llHeader);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "itemView.findViewById(R.id.llHeader)");
            LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) findViewById5;
            this.llHeader = linearLayoutCompat;
            linearLayoutCompat.setOnClickListener(this);
        }

        @NotNull
        public final AppCompatImageView getIvExpandCollapse() {
            return this.ivExpandCollapse;
        }

        @NotNull
        public final RecyclerView getRvAlertChild() {
            return this.rvAlertChild;
        }

        @NotNull
        public final AppCompatTextView getTvAlertCount() {
            return this.tvAlertCount;
        }

        @NotNull
        public final AppCompatTextView getTvAlertLbl() {
            return this.tvAlertLbl;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(@Nullable View view) {
            if (!Intrinsics.areEqual(view, this.llHeader) || getAbsoluteAdapterPosition() == -1) {
                return;
            }
            MyCarStatusAlertHeaderAdapter myCarStatusAlertHeaderAdapter = this.f10463a;
            myCarStatusAlertHeaderAdapter.onItemClicked((VehicleAlerts) myCarStatusAlertHeaderAdapter.vehicleAlerts.get(getAbsoluteAdapterPosition()));
        }

        public final void setIvExpandCollapse(@NotNull AppCompatImageView appCompatImageView) {
            Intrinsics.checkNotNullParameter(appCompatImageView, "<set-?>");
            this.ivExpandCollapse = appCompatImageView;
        }

        public final void setRvAlertChild(@NotNull RecyclerView recyclerView) {
            Intrinsics.checkNotNullParameter(recyclerView, "<set-?>");
            this.rvAlertChild = recyclerView;
        }

        public final void setTvAlertCount(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvAlertCount = appCompatTextView;
        }

        public final void setTvAlertLbl(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvAlertLbl = appCompatTextView;
        }
    }

    public MyCarStatusAlertHeaderAdapter(@NotNull List<VehicleAlerts> vehicleAlerts) {
        Intrinsics.checkNotNullParameter(vehicleAlerts, "vehicleAlerts");
        this.vehicleAlerts = vehicleAlerts;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"NotifyDataSetChanged"})
    public final void onItemClicked(VehicleAlerts vehicleAlerts) {
        if (vehicleAlerts != null) {
            vehicleAlerts.setExpanded(!vehicleAlerts.isExpanded());
        }
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.vehicleAlerts.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull MyCarStatusAlertViewHolder holder, int i2) {
        AppCompatImageView ivExpandCollapse;
        Context context;
        int i3;
        Intrinsics.checkNotNullParameter(holder, "holder");
        VehicleAlerts vehicleAlerts = this.vehicleAlerts.get(i2);
        AppCompatTextView tvAlertCount = holder.getTvAlertCount();
        tvAlertCount.setText(String.valueOf(vehicleAlerts.getAlertCounts()));
        Drawable background = tvAlertCount.getBackground();
        Context context2 = tvAlertCount.getContext();
        Intrinsics.checkNotNull(context2);
        DrawableCompat.setTint(background, ContextCompat.getColor(context2, vehicleAlerts.getBackgroundColor()));
        Context context3 = tvAlertCount.getContext();
        Intrinsics.checkNotNull(context3);
        tvAlertCount.setTextColor(ContextCompat.getColor(context3, vehicleAlerts.getCountTextColor()));
        List<Notification> alerts = vehicleAlerts.getAlerts();
        if (alerts.size() > 1) {
            CollectionsKt__MutableCollectionsJVMKt.sortWith(alerts, new Comparator() { // from class: com.psa.mym.mycitroenconnect.controller.adapters.MyCarStatusAlertHeaderAdapter$onBindViewHolder$lambda-1$$inlined$sortByDescending$1
                @Override // java.util.Comparator
                public final int compare(T t2, T t3) {
                    int compareValues;
                    compareValues = ComparisonsKt__ComparisonsKt.compareValues(Long.valueOf(((Notification) t3).getSignalTimeStamp()), Long.valueOf(((Notification) t2).getSignalTimeStamp()));
                    return compareValues;
                }
            });
        }
        holder.getRvAlertChild().setAdapter(new MyCarStatusAlertChildAdapter(vehicleAlerts.getAlerts()));
        holder.getRvAlertChild().setLayoutManager(new LinearLayoutManager(tvAlertCount.getContext()));
        ExtensionsKt.setDivider(holder.getRvAlertChild(), R.drawable.ic_horizontal_line);
        holder.getTvAlertLbl().setText(vehicleAlerts.getAlertLabel());
        if (vehicleAlerts.isExpanded()) {
            holder.getRvAlertChild().setVisibility(0);
            ivExpandCollapse = holder.getIvExpandCollapse();
            context = this.context;
            Intrinsics.checkNotNull(context);
            i3 = R.drawable.ic_collapse_minus;
        } else {
            holder.getRvAlertChild().setVisibility(8);
            ivExpandCollapse = holder.getIvExpandCollapse();
            context = this.context;
            Intrinsics.checkNotNull(context);
            i3 = R.drawable.ic_expand_add;
        }
        ivExpandCollapse.setImageDrawable(ContextCompat.getDrawable(context, i3));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public MyCarStatusAlertViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (this.context == null) {
            this.context = parent.getContext();
        }
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.item_view_my_car_status_alert_header, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(context)\n          …rt_header, parent, false)");
        return new MyCarStatusAlertViewHolder(this, inflate);
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void updateVehicleAlertList(@NotNull List<VehicleAlerts> vehicleAlerts) {
        Intrinsics.checkNotNullParameter(vehicleAlerts, "vehicleAlerts");
        this.vehicleAlerts = vehicleAlerts;
        notifyDataSetChanged();
    }
}
