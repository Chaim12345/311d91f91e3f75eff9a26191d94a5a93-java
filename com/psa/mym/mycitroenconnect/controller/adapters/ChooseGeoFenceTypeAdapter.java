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
import com.psa.mym.mycitroenconnect.model.geo_fence.GeoFenceType;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import java.util.ArrayList;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ChooseGeoFenceTypeAdapter extends RecyclerView.Adapter<ChooseGeoFenceViewHolder> {
    @Nullable
    private Context context;
    @NotNull
    private ArrayList<GeoFenceType> fenceTypeList;
    @Nullable
    private OnGeoFenceTypeSelect onGeoFenceTypeSelect;

    /* loaded from: classes3.dex */
    public final class ChooseGeoFenceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ ChooseGeoFenceTypeAdapter f10453a;
        @NotNull
        private final MaterialCardView cvMapSelect;
        @NotNull
        private final AppCompatImageView ivFence;
        @NotNull
        private final AppCompatRadioButton ivSelect;
        @NotNull
        private final AppCompatTextView tvFenceType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ChooseGeoFenceViewHolder(@NotNull ChooseGeoFenceTypeAdapter chooseGeoFenceTypeAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.f10453a = chooseGeoFenceTypeAdapter;
            AppCompatRadioButton appCompatRadioButton = (AppCompatRadioButton) itemView.findViewById(R.id.ivSelect);
            Intrinsics.checkNotNullExpressionValue(appCompatRadioButton, "itemView.ivSelect");
            this.ivSelect = appCompatRadioButton;
            AppCompatTextView appCompatTextView = (AppCompatTextView) itemView.findViewById(R.id.tvFenceType);
            Intrinsics.checkNotNullExpressionValue(appCompatTextView, "itemView.tvFenceType");
            this.tvFenceType = appCompatTextView;
            AppCompatImageView appCompatImageView = (AppCompatImageView) itemView.findViewById(R.id.ivFence);
            Intrinsics.checkNotNullExpressionValue(appCompatImageView, "itemView.ivFence");
            this.ivFence = appCompatImageView;
            MaterialCardView materialCardView = (MaterialCardView) itemView.findViewById(R.id.cvMapSelect);
            Intrinsics.checkNotNullExpressionValue(materialCardView, "itemView.cvMapSelect");
            this.cvMapSelect = materialCardView;
            itemView.setOnClickListener(this);
        }

        @NotNull
        public final MaterialCardView getCvMapSelect() {
            return this.cvMapSelect;
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
        public final AppCompatTextView getTvFenceType() {
            return this.tvFenceType;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(@Nullable View view) {
            OnGeoFenceTypeSelect onGeoFenceTypeSelect;
            if (!Intrinsics.areEqual(view, this.itemView) || getAbsoluteAdapterPosition() <= -1) {
                return;
            }
            ArrayList arrayList = this.f10453a.fenceTypeList;
            ChooseGeoFenceTypeAdapter chooseGeoFenceTypeAdapter = this.f10453a;
            int i2 = 0;
            for (Object obj : arrayList) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                GeoFenceType geoFenceType = (GeoFenceType) obj;
                geoFenceType.setSelected(i2 == getAbsoluteAdapterPosition());
                if (geoFenceType.isSelected() && (onGeoFenceTypeSelect = chooseGeoFenceTypeAdapter.onGeoFenceTypeSelect) != null) {
                    onGeoFenceTypeSelect.selectedGeoFenceType(geoFenceType);
                }
                i2 = i3;
            }
            this.f10453a.notifyDataSetChanged();
        }
    }

    public ChooseGeoFenceTypeAdapter(@NotNull ArrayList<GeoFenceType> fenceTypeList) {
        Intrinsics.checkNotNullParameter(fenceTypeList, "fenceTypeList");
        this.fenceTypeList = fenceTypeList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.fenceTypeList.size();
    }

    public final int getSelectedFence() {
        int i2 = -1;
        for (GeoFenceType geoFenceType : this.fenceTypeList) {
            if (geoFenceType.isSelected()) {
                i2 = geoFenceType.getFenceId();
            }
        }
        return i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ChooseGeoFenceViewHolder holder, int i2) {
        MaterialCardView cvMapSelect;
        int i3;
        Intrinsics.checkNotNullParameter(holder, "holder");
        GeoFenceType geoFenceType = this.fenceTypeList.get(i2);
        Intrinsics.checkNotNullExpressionValue(geoFenceType, "fenceTypeList[position]");
        GeoFenceType geoFenceType2 = geoFenceType;
        if (geoFenceType2.isSelected()) {
            ExtensionsKt.show(holder.getIvSelect());
            cvMapSelect = holder.getCvMapSelect();
            i3 = 2;
        } else {
            ExtensionsKt.hide(holder.getIvSelect());
            cvMapSelect = holder.getCvMapSelect();
            i3 = 0;
        }
        cvMapSelect.setStrokeWidth(i3);
        holder.getTvFenceType().setText(geoFenceType2.getFenceType());
        Context context = this.context;
        if (context != null) {
            Glide.with(context).load(Integer.valueOf(geoFenceType2.getFenceImage())).fitCenter().into(holder.getIvFence());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ChooseGeoFenceViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (this.context == null) {
            this.context = parent.getContext();
        }
        View inflate = LayoutInflater.from(this.context).inflate(uat.psa.mym.mycitroenconnect.R.layout.item_view_choose_geo_fence_type, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(context)\n          …ence_type, parent, false)");
        return new ChooseGeoFenceViewHolder(this, inflate);
    }

    public final void setonGeoFenceTypeSelect(@NotNull OnGeoFenceTypeSelect onGeoFenceTypeSelect) {
        Intrinsics.checkNotNullParameter(onGeoFenceTypeSelect, "onGeoFenceTypeSelect");
        this.onGeoFenceTypeSelect = onGeoFenceTypeSelect;
    }
}
