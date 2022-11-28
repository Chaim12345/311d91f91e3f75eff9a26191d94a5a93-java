package com.psa.mym.mycitroenconnect.controller.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.model.geo_fence.GeoFenceRepeat;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import java.util.ArrayList;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class GeoFenceRepeatAdapter extends RecyclerView.Adapter<GeoFenceRepeatViewHolder> {
    @Nullable
    private Context context;
    private final boolean isMultipleSelection;
    @Nullable
    private OnRepeatGeoFenceSelect onRepeatGeoFenceSelect;
    @NotNull
    private ArrayList<GeoFenceRepeat> repeatList;

    /* loaded from: classes3.dex */
    public final class GeoFenceRepeatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ GeoFenceRepeatAdapter f10461a;
        @NotNull
        private final MaterialCardView cvRepeat;
        @NotNull
        private final AppCompatImageView ivSelect;
        @NotNull
        private final AppCompatTextView tvRepeat;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public GeoFenceRepeatViewHolder(@NotNull GeoFenceRepeatAdapter geoFenceRepeatAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.f10461a = geoFenceRepeatAdapter;
            AppCompatTextView appCompatTextView = (AppCompatTextView) itemView.findViewById(R.id.tvRepeat);
            Intrinsics.checkNotNullExpressionValue(appCompatTextView, "itemView.tvRepeat");
            this.tvRepeat = appCompatTextView;
            AppCompatImageView appCompatImageView = (AppCompatImageView) itemView.findViewById(R.id.ivSelect);
            Intrinsics.checkNotNullExpressionValue(appCompatImageView, "itemView.ivSelect");
            this.ivSelect = appCompatImageView;
            MaterialCardView materialCardView = (MaterialCardView) itemView.findViewById(R.id.cvRepeat);
            Intrinsics.checkNotNullExpressionValue(materialCardView, "itemView.cvRepeat");
            this.cvRepeat = materialCardView;
            itemView.setOnClickListener(this);
        }

        @NotNull
        public final MaterialCardView getCvRepeat() {
            return this.cvRepeat;
        }

        @NotNull
        public final AppCompatImageView getIvSelect() {
            return this.ivSelect;
        }

        @NotNull
        public final AppCompatTextView getTvRepeat() {
            return this.tvRepeat;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(@Nullable View view) {
            GeoFenceRepeat geoFenceRepeat;
            OnRepeatGeoFenceSelect onRepeatGeoFenceSelect;
            if (!Intrinsics.areEqual(view, this.itemView) || getAbsoluteAdapterPosition() <= -1) {
                return;
            }
            boolean z = true;
            if (this.f10461a.isMultipleSelection) {
                geoFenceRepeat = (GeoFenceRepeat) this.f10461a.repeatList.get(getAbsoluteAdapterPosition());
                z = true ^ ((GeoFenceRepeat) this.f10461a.repeatList.get(getAbsoluteAdapterPosition())).isSelected();
            } else {
                for (GeoFenceRepeat geoFenceRepeat2 : this.f10461a.repeatList) {
                    geoFenceRepeat2.setSelected(false);
                }
                geoFenceRepeat = (GeoFenceRepeat) this.f10461a.repeatList.get(getAbsoluteAdapterPosition());
            }
            geoFenceRepeat.setSelected(z);
            if (((GeoFenceRepeat) this.f10461a.repeatList.get(getAbsoluteAdapterPosition())).isCustom() && (onRepeatGeoFenceSelect = this.f10461a.onRepeatGeoFenceSelect) != null) {
                onRepeatGeoFenceSelect.onCustomSelect();
            }
            this.f10461a.notifyDataSetChanged();
        }
    }

    public GeoFenceRepeatAdapter(@NotNull ArrayList<GeoFenceRepeat> repeatList, boolean z) {
        Intrinsics.checkNotNullParameter(repeatList, "repeatList");
        this.repeatList = repeatList;
        this.isMultipleSelection = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.repeatList.size();
    }

    @NotNull
    public final ArrayList<String> getSelectedDays() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (GeoFenceRepeat geoFenceRepeat : this.repeatList) {
            if (geoFenceRepeat.isSelected()) {
                String upperCase = geoFenceRepeat.getRepeatType().toUpperCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                arrayList.add(upperCase);
            }
        }
        return arrayList;
    }

    @NotNull
    public final String getSelectedTimePeriod() {
        String str = "";
        for (GeoFenceRepeat geoFenceRepeat : this.repeatList) {
            if (geoFenceRepeat.isSelected()) {
                str = geoFenceRepeat.isCustom() ? geoFenceRepeat.getCustomTime() : geoFenceRepeat.getRepeatType();
            }
        }
        return str;
    }

    public final boolean isCustomTimeSelected() {
        for (GeoFenceRepeat geoFenceRepeat : this.repeatList) {
            if (geoFenceRepeat.isSelected() && geoFenceRepeat.isCustom()) {
                return true;
            }
        }
        return false;
    }

    public final boolean isSelected() {
        ArrayList arrayList = new ArrayList();
        for (GeoFenceRepeat geoFenceRepeat : this.repeatList) {
            arrayList.add(Boolean.valueOf(geoFenceRepeat.isSelected()));
        }
        return arrayList.contains(Boolean.TRUE);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull GeoFenceRepeatViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        GeoFenceRepeat geoFenceRepeat = this.repeatList.get(i2);
        Intrinsics.checkNotNullExpressionValue(geoFenceRepeat, "repeatList[position]");
        GeoFenceRepeat geoFenceRepeat2 = geoFenceRepeat;
        holder.getTvRepeat().setText(geoFenceRepeat2.getRepeatType());
        if (geoFenceRepeat2.isCustom()) {
            if (geoFenceRepeat2.getCustomTime().length() > 0) {
                AppCompatTextView tvRepeat = holder.getTvRepeat();
                tvRepeat.setText(geoFenceRepeat2.getRepeatType() + " \n " + geoFenceRepeat2.getCustomTime());
            }
        }
        holder.getTvRepeat().setSelected(geoFenceRepeat2.isSelected());
        holder.getCvRepeat().setSelected(geoFenceRepeat2.isSelected());
        boolean isSelected = geoFenceRepeat2.isSelected();
        AppCompatImageView ivSelect = holder.getIvSelect();
        if (isSelected) {
            ExtensionsKt.show(ivSelect);
        } else {
            ExtensionsKt.invisible(ivSelect);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public GeoFenceRepeatViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (this.context == null) {
            this.context = parent.getContext();
        }
        View inflate = LayoutInflater.from(this.context).inflate(uat.psa.mym.mycitroenconnect.R.layout.item_view_repeat_geo_fence, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(context)\n          …geo_fence, parent, false)");
        return new GeoFenceRepeatViewHolder(this, inflate);
    }

    public final void selectDayTime(int i2) {
        if (i2 != -1) {
            this.repeatList.get(i2).setSelected(true);
            notifyItemChanged(i2);
        }
    }

    public final void setOnRepeatGeoFenceSelect(@NotNull OnRepeatGeoFenceSelect onRepeatGeoFenceSelect) {
        Intrinsics.checkNotNullParameter(onRepeatGeoFenceSelect, "onRepeatGeoFenceSelect");
        this.onRepeatGeoFenceSelect = onRepeatGeoFenceSelect;
    }
}
