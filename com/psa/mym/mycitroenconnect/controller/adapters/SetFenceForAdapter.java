package com.psa.mym.mycitroenconnect.controller.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.model.geo_fence.GeoFenceInfo;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import java.util.ArrayList;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class SetFenceForAdapter extends RecyclerView.Adapter<GeoFenceInfoViewHolder> {
    @Nullable
    private Context context;
    @NotNull
    private ArrayList<GeoFenceInfo> fenceInfoList;
    @Nullable
    private OnSetFenceForSelect onSetFenceFor;

    /* loaded from: classes3.dex */
    public final class GeoFenceInfoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ SetFenceForAdapter f10476a;
        @NotNull
        private final MaterialCardView cvMapSelect;
        @NotNull
        private final View divider;
        @NotNull
        private final AppCompatImageView ivFence;
        @NotNull
        private final AppCompatRadioButton ivSelect;
        @NotNull
        private final AppCompatTextView tvFenceInfo;
        @NotNull
        private final AppCompatTextView tvFenceType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public GeoFenceInfoViewHolder(@NotNull SetFenceForAdapter setFenceForAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.f10476a = setFenceForAdapter;
            AppCompatTextView appCompatTextView = (AppCompatTextView) itemView.findViewById(R.id.tvFenceType);
            Intrinsics.checkNotNullExpressionValue(appCompatTextView, "itemView.tvFenceType");
            this.tvFenceType = appCompatTextView;
            AppCompatTextView appCompatTextView2 = (AppCompatTextView) itemView.findViewById(R.id.tvFenceInfo);
            Intrinsics.checkNotNullExpressionValue(appCompatTextView2, "itemView.tvFenceInfo");
            this.tvFenceInfo = appCompatTextView2;
            AppCompatImageView appCompatImageView = (AppCompatImageView) itemView.findViewById(R.id.ivFence);
            Intrinsics.checkNotNullExpressionValue(appCompatImageView, "itemView.ivFence");
            this.ivFence = appCompatImageView;
            View findViewById = itemView.findViewById(R.id.divider);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.divider");
            this.divider = findViewById;
            AppCompatRadioButton appCompatRadioButton = (AppCompatRadioButton) itemView.findViewById(R.id.ivSelect);
            Intrinsics.checkNotNullExpressionValue(appCompatRadioButton, "itemView.ivSelect");
            this.ivSelect = appCompatRadioButton;
            MaterialCardView materialCardView = (MaterialCardView) itemView.findViewById(R.id.cvMapSelect);
            Intrinsics.checkNotNullExpressionValue(materialCardView, "itemView.cvMapSelect");
            this.cvMapSelect = materialCardView;
            itemView.setOnClickListener(this);
            appCompatRadioButton.setClickable(false);
        }

        @NotNull
        public final MaterialCardView getCvMapSelect() {
            return this.cvMapSelect;
        }

        @NotNull
        public final View getDivider() {
            return this.divider;
        }

        @NotNull
        public final AppCompatImageView getIvFence() {
            return this.ivFence;
        }

        @NotNull
        public final AppCompatRadioButton getIvSelect() {
            return this.ivSelect;
        }

        @NotNull
        public final AppCompatTextView getTvFenceInfo() {
            return this.tvFenceInfo;
        }

        @NotNull
        public final AppCompatTextView getTvFenceType() {
            return this.tvFenceType;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(@Nullable View view) {
            OnSetFenceForSelect onSetFenceForSelect;
            if (getAbsoluteAdapterPosition() > -1) {
                ArrayList arrayList = this.f10476a.fenceInfoList;
                SetFenceForAdapter setFenceForAdapter = this.f10476a;
                int i2 = 0;
                for (Object obj : arrayList) {
                    int i3 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    GeoFenceInfo geoFenceInfo = (GeoFenceInfo) obj;
                    geoFenceInfo.setSelected(i2 == getAbsoluteAdapterPosition());
                    if (geoFenceInfo.isSelected() && (onSetFenceForSelect = setFenceForAdapter.onSetFenceFor) != null) {
                        onSetFenceForSelect.selectSetFenceFor(geoFenceInfo);
                    }
                    i2 = i3;
                }
                this.f10476a.notifyDataSetChanged();
            }
        }
    }

    public SetFenceForAdapter(@NotNull ArrayList<GeoFenceInfo> fenceInfoList) {
        Intrinsics.checkNotNullParameter(fenceInfoList, "fenceInfoList");
        this.fenceInfoList = fenceInfoList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.fenceInfoList.size();
    }

    @Nullable
    public final GeoFenceInfo getSelectedSetFenceFor() {
        for (GeoFenceInfo geoFenceInfo : this.fenceInfoList) {
            if (geoFenceInfo.isSelected()) {
                return geoFenceInfo;
            }
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull GeoFenceInfoViewHolder holder, int i2) {
        int i3;
        MaterialCardView cvMapSelect;
        Intrinsics.checkNotNullParameter(holder, "holder");
        GeoFenceInfo geoFenceInfo = this.fenceInfoList.get(i2);
        Intrinsics.checkNotNullExpressionValue(geoFenceInfo, "fenceInfoList[position]");
        GeoFenceInfo geoFenceInfo2 = geoFenceInfo;
        if (geoFenceInfo2.isSelected()) {
            holder.getIvSelect().setChecked(true);
            cvMapSelect = holder.getCvMapSelect();
            i3 = 2;
        } else {
            i3 = 0;
            holder.getIvSelect().setChecked(false);
            cvMapSelect = holder.getCvMapSelect();
        }
        cvMapSelect.setStrokeWidth(i3);
        holder.getTvFenceType().setText(geoFenceInfo2.getTitle());
        holder.getTvFenceInfo().setText(geoFenceInfo2.getDetail());
        Context context = this.context;
        if (context != null) {
            Glide.with(context).load(Integer.valueOf(geoFenceInfo2.getFenceImage())).fitCenter().into(holder.getIvFence());
        }
        int size = this.fenceInfoList.size() - 1;
        View divider = holder.getDivider();
        if (i2 == size) {
            ExtensionsKt.hide(divider);
        } else {
            ExtensionsKt.show(divider);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public GeoFenceInfoViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (this.context == null) {
            this.context = parent.getContext();
        }
        View inflate = LayoutInflater.from(this.context).inflate(uat.psa.mym.mycitroenconnect.R.layout.item_view_set_fence_for, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(context)\n          …fence_for, parent, false)");
        return new GeoFenceInfoViewHolder(this, inflate);
    }

    public final void setOnSetFenceFor(@NotNull OnSetFenceForSelect onSetFenceFor) {
        Intrinsics.checkNotNullParameter(onSetFenceFor, "onSetFenceFor");
        this.onSetFenceFor = onSetFenceFor;
    }
}
