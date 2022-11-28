package com.psa.mym.mycitroenconnect.controller.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.R;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ServiceAvailableAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull
    private ArrayList<String> serviceList;

    /* loaded from: classes3.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @Nullable
        private AppCompatTextView tvAvailableService;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull ServiceAvailableAdapter serviceAvailableAdapter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.tvAvailableService = (AppCompatTextView) view.findViewById(R.id.tvAvailableService);
        }

        @Nullable
        public final AppCompatTextView getTvAvailableService() {
            return this.tvAvailableService;
        }

        public final void setTvAvailableService(@Nullable AppCompatTextView appCompatTextView) {
            this.tvAvailableService = appCompatTextView;
        }
    }

    public ServiceAvailableAdapter(@NotNull ArrayList<String> serviceList) {
        Intrinsics.checkNotNullParameter(serviceList, "serviceList");
        this.serviceList = serviceList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.serviceList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        String str = this.serviceList.get(holder.getAbsoluteAdapterPosition());
        Intrinsics.checkNotNullExpressionValue(str, "serviceList[holder.absoluteAdapterPosition]");
        String str2 = str;
        AppCompatTextView tvAvailableService = holder.getTvAvailableService();
        if (tvAvailableService == null) {
            return;
        }
        tvAvailableService.setText(str2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(uat.psa.mym.mycitroenconnect.R.layout.cell_available_services, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context)\n   …_services, parent, false)");
        return new ViewHolder(this, inflate);
    }

    public final void updateServiceList(@NotNull ArrayList<String> serviceArrayList) {
        Intrinsics.checkNotNullParameter(serviceArrayList, "serviceArrayList");
        this.serviceList = serviceArrayList;
        notifyDataSetChanged();
    }
}
