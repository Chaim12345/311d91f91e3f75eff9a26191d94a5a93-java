package com.psa.mym.mycitroenconnect.controller.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.widget.TextViewCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textview.MaterialTextView;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.model.geo_fence.GetGeoFenceResponseItem;
import com.psa.mym.mycitroenconnect.model.geo_fence.LocationData;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt__CharJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class GeoFenceAdapter extends RecyclerView.Adapter<GeoFenceViewHolder> {
    @Nullable
    private Context context;
    @Nullable
    private GeoFenceClickListener geoFenceClickListener;
    @NotNull
    private ArrayList<GetGeoFenceResponseItem> geoFenceList;
    private long lastClickTime;

    /* loaded from: classes3.dex */
    public final class GeoFenceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ GeoFenceAdapter f10460a;
        @NotNull
        private View divider;
        @NotNull
        private AppCompatImageView ivDelete;
        @NotNull
        private AppCompatImageView ivEdit;
        @NotNull
        private LinearLayoutCompat llGeoFenceDetails;
        @NotNull
        private SwitchMaterial switchFence;
        @NotNull
        private AppCompatTextView tvDate;
        @NotNull
        private AppCompatTextView tvFenceType;
        @NotNull
        private AppCompatTextView tvRadius;
        @NotNull
        private AppCompatTextView tvTime;
        @NotNull
        private AppCompatTextView tvTitle;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public GeoFenceViewHolder(@NotNull GeoFenceAdapter geoFenceAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.f10460a = geoFenceAdapter;
            MaterialTextView materialTextView = (MaterialTextView) itemView.findViewById(R.id.tvRadius);
            Intrinsics.checkNotNullExpressionValue(materialTextView, "itemView.tvRadius");
            this.tvRadius = materialTextView;
            MaterialTextView materialTextView2 = (MaterialTextView) itemView.findViewById(R.id.tvTitle);
            Intrinsics.checkNotNullExpressionValue(materialTextView2, "itemView.tvTitle");
            this.tvTitle = materialTextView2;
            MaterialTextView materialTextView3 = (MaterialTextView) itemView.findViewById(R.id.tvFenceType);
            Intrinsics.checkNotNullExpressionValue(materialTextView3, "itemView.tvFenceType");
            this.tvFenceType = materialTextView3;
            MaterialTextView materialTextView4 = (MaterialTextView) itemView.findViewById(R.id.tvDate);
            Intrinsics.checkNotNullExpressionValue(materialTextView4, "itemView.tvDate");
            this.tvDate = materialTextView4;
            MaterialTextView materialTextView5 = (MaterialTextView) itemView.findViewById(R.id.tvTime);
            Intrinsics.checkNotNullExpressionValue(materialTextView5, "itemView.tvTime");
            this.tvTime = materialTextView5;
            SwitchMaterial switchMaterial = (SwitchMaterial) itemView.findViewById(R.id.switchFence);
            Intrinsics.checkNotNullExpressionValue(switchMaterial, "itemView.switchFence");
            this.switchFence = switchMaterial;
            AppCompatImageView appCompatImageView = (AppCompatImageView) itemView.findViewById(R.id.ivEdit);
            Intrinsics.checkNotNullExpressionValue(appCompatImageView, "itemView.ivEdit");
            this.ivEdit = appCompatImageView;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) itemView.findViewById(R.id.ivDelete);
            Intrinsics.checkNotNullExpressionValue(appCompatImageView2, "itemView.ivDelete");
            this.ivDelete = appCompatImageView2;
            View findViewById = itemView.findViewById(R.id.divider);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.divider");
            this.divider = findViewById;
            LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) itemView.findViewById(R.id.llGeoFenceDetails);
            Intrinsics.checkNotNullExpressionValue(linearLayoutCompat, "itemView.llGeoFenceDetails");
            this.llGeoFenceDetails = linearLayoutCompat;
            SharedPref.Companion companion = SharedPref.Companion;
            Context context = geoFenceAdapter.context;
            Intrinsics.checkNotNull(context);
            if (companion.isGuestUser(context)) {
                ExtensionsKt.hide(this.ivDelete);
                this.switchFence.setClickable(false);
            } else {
                ExtensionsKt.show(this.ivDelete);
                this.switchFence.setClickable(true);
                this.switchFence.setOnClickListener(this);
                this.llGeoFenceDetails.setOnClickListener(this);
                this.tvRadius.setOnClickListener(this);
            }
            this.ivEdit.setOnClickListener(this);
            this.ivDelete.setOnClickListener(this);
        }

        @NotNull
        public final View getDivider() {
            return this.divider;
        }

        @NotNull
        public final AppCompatImageView getIvDelete() {
            return this.ivDelete;
        }

        @NotNull
        public final AppCompatImageView getIvEdit() {
            return this.ivEdit;
        }

        @NotNull
        public final LinearLayoutCompat getLlGeoFenceDetails() {
            return this.llGeoFenceDetails;
        }

        @NotNull
        public final SwitchMaterial getSwitchFence() {
            return this.switchFence;
        }

        @NotNull
        public final AppCompatTextView getTvDate() {
            return this.tvDate;
        }

        @NotNull
        public final AppCompatTextView getTvFenceType() {
            return this.tvFenceType;
        }

        @NotNull
        public final AppCompatTextView getTvRadius() {
            return this.tvRadius;
        }

        @NotNull
        public final AppCompatTextView getTvTime() {
            return this.tvTime;
        }

        @NotNull
        public final AppCompatTextView getTvTitle() {
            return this.tvTitle;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(@Nullable View view) {
            GeoFenceClickListener geoFenceClickListener;
            if (Intrinsics.areEqual(view, this.switchFence) || SystemClock.elapsedRealtime() - this.f10460a.lastClickTime >= 1500) {
                this.f10460a.lastClickTime = SystemClock.elapsedRealtime();
                if (this.f10460a.geoFenceClickListener == null || getAbsoluteAdapterPosition() <= -1) {
                    return;
                }
                if (!(!this.f10460a.geoFenceList.isEmpty()) || this.f10460a.geoFenceList.size() <= 0) {
                    return;
                }
                if (Intrinsics.areEqual(view, this.tvRadius) ? true : Intrinsics.areEqual(view, this.llGeoFenceDetails) ? true : Intrinsics.areEqual(view, this.ivEdit)) {
                    GeoFenceClickListener geoFenceClickListener2 = this.f10460a.geoFenceClickListener;
                    if (geoFenceClickListener2 != null) {
                        Object obj = this.f10460a.geoFenceList.get(getAbsoluteAdapterPosition());
                        Intrinsics.checkNotNullExpressionValue(obj, "geoFenceList[absoluteAdapterPosition]");
                        geoFenceClickListener2.onEditClick((GetGeoFenceResponseItem) obj);
                    }
                } else if (!Intrinsics.areEqual(view, this.ivDelete)) {
                    if (!Intrinsics.areEqual(view, this.switchFence) || (geoFenceClickListener = this.f10460a.geoFenceClickListener) == null) {
                        return;
                    }
                    geoFenceClickListener.onGeoFenceSwitchChange(this.switchFence.isChecked(), getAbsoluteAdapterPosition());
                } else {
                    GeoFenceClickListener geoFenceClickListener3 = this.f10460a.geoFenceClickListener;
                    if (geoFenceClickListener3 != null) {
                        int absoluteAdapterPosition = getAbsoluteAdapterPosition();
                        Object obj2 = this.f10460a.geoFenceList.get(getAbsoluteAdapterPosition());
                        Intrinsics.checkNotNullExpressionValue(obj2, "geoFenceList[absoluteAdapterPosition]");
                        geoFenceClickListener3.onDelete(absoluteAdapterPosition, (GetGeoFenceResponseItem) obj2);
                    }
                }
            }
        }

        public final void setDivider(@NotNull View view) {
            Intrinsics.checkNotNullParameter(view, "<set-?>");
            this.divider = view;
        }

        public final void setIvDelete(@NotNull AppCompatImageView appCompatImageView) {
            Intrinsics.checkNotNullParameter(appCompatImageView, "<set-?>");
            this.ivDelete = appCompatImageView;
        }

        public final void setIvEdit(@NotNull AppCompatImageView appCompatImageView) {
            Intrinsics.checkNotNullParameter(appCompatImageView, "<set-?>");
            this.ivEdit = appCompatImageView;
        }

        public final void setLlGeoFenceDetails(@NotNull LinearLayoutCompat linearLayoutCompat) {
            Intrinsics.checkNotNullParameter(linearLayoutCompat, "<set-?>");
            this.llGeoFenceDetails = linearLayoutCompat;
        }

        public final void setSwitchFence(@NotNull SwitchMaterial switchMaterial) {
            Intrinsics.checkNotNullParameter(switchMaterial, "<set-?>");
            this.switchFence = switchMaterial;
        }

        public final void setTvDate(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvDate = appCompatTextView;
        }

        public final void setTvFenceType(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvFenceType = appCompatTextView;
        }

        public final void setTvRadius(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvRadius = appCompatTextView;
        }

        public final void setTvTime(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvTime = appCompatTextView;
        }

        public final void setTvTitle(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvTitle = appCompatTextView;
        }
    }

    public GeoFenceAdapter() {
        this(null, 1, null);
    }

    public GeoFenceAdapter(@NotNull ArrayList<GetGeoFenceResponseItem> geoFenceList) {
        Intrinsics.checkNotNullParameter(geoFenceList, "geoFenceList");
        this.geoFenceList = geoFenceList;
    }

    public /* synthetic */ GeoFenceAdapter(ArrayList arrayList, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? new ArrayList() : arrayList);
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void deActiveAllFence() {
        for (GetGeoFenceResponseItem getGeoFenceResponseItem : this.geoFenceList) {
            getGeoFenceResponseItem.setFenceStatus("D");
        }
        notifyDataSetChanged();
    }

    public final int getEnableFenceCount() {
        int i2 = 0;
        for (GetGeoFenceResponseItem getGeoFenceResponseItem : this.geoFenceList) {
            if (Intrinsics.areEqual(getGeoFenceResponseItem.getFenceStatus(), ExifInterface.GPS_MEASUREMENT_IN_PROGRESS)) {
                i2++;
            }
        }
        return i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.geoFenceList.size();
    }

    /* JADX WARN: Removed duplicated region for block: B:79:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x026a  */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @SuppressLint({"SetTextI18n"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onBindViewHolder(@NotNull GeoFenceViewHolder holder, int i2) {
        AppUtil.Companion companion;
        List<String> daysOfWeek;
        AppCompatTextView tvDate;
        StringBuilder sb;
        int size;
        String valueOf;
        Integer valueOf2;
        Intrinsics.checkNotNullParameter(holder, "holder");
        GetGeoFenceResponseItem getGeoFenceResponseItem = this.geoFenceList.get(i2);
        Intrinsics.checkNotNullExpressionValue(getGeoFenceResponseItem, "geoFenceList[position]");
        GetGeoFenceResponseItem getGeoFenceResponseItem2 = getGeoFenceResponseItem;
        String fenceMode = getGeoFenceResponseItem2.getFenceMode();
        String str = null;
        if (fenceMode != null) {
            int hashCode = fenceMode.hashCode();
            if (hashCode != -1349088399) {
                if (hashCode != -938578798) {
                    if (hashCode == 108704329 && fenceMode.equals(AppConstants.GEO_FENCE_MODE_ROUTE)) {
                        AppCompatTextView tvRadius = holder.getTvRadius();
                        AppUtil.Companion companion2 = AppUtil.Companion;
                        LocationData sourceLocation = getGeoFenceResponseItem2.getSourceLocation();
                        Intrinsics.checkNotNull(sourceLocation);
                        LocationData destinationLocation = getGeoFenceResponseItem2.getDestinationLocation();
                        Intrinsics.checkNotNull(destinationLocation);
                        tvRadius.setText(ExtensionsKt.toKM(companion2.getDistance(sourceLocation, destinationLocation)));
                        TextViewCompat.setTextAppearance(holder.getTvRadius(), uat.psa.mym.mycitroenconnect.R.style.Font18spMediumDarkRed);
                    }
                } else if (fenceMode.equals(AppConstants.GEO_FENCE_MODE_RADIUS)) {
                    Integer radius = getGeoFenceResponseItem2.getRadius();
                    holder.getTvRadius().setText((radius != null ? Integer.valueOf(radius.intValue() / 1000) : null) != null ? ExtensionsKt.toKM(valueOf2.intValue()) : null);
                    TextViewCompat.setTextAppearance(holder.getTvRadius(), uat.psa.mym.mycitroenconnect.R.style.Font18spMediumDarkRed);
                }
            } else if (fenceMode.equals(AppConstants.GEO_FENCE_MODE_CUSTOM) && this.context != null) {
                AppCompatTextView tvRadius2 = holder.getTvRadius();
                Context context = this.context;
                tvRadius2.setText(context != null ? context.getString(uat.psa.mym.mycitroenconnect.R.string.custom_fence) : null);
                TextViewCompat.setTextAppearance(holder.getTvRadius(), uat.psa.mym.mycitroenconnect.R.style.Font14spRegularHotGrey40);
            }
        }
        holder.getTvTitle().setText(getGeoFenceResponseItem2.getFenceName());
        AppCompatTextView tvFenceType = holder.getTvFenceType();
        StringBuilder sb2 = new StringBuilder();
        String fenceMode2 = getGeoFenceResponseItem2.getFenceMode();
        boolean z = false;
        if (fenceMode2 != null) {
            if (fenceMode2.length() > 0) {
                StringBuilder sb3 = new StringBuilder();
                char charAt = fenceMode2.charAt(0);
                if (Character.isLowerCase(charAt)) {
                    Locale ROOT = Locale.ROOT;
                    Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
                    valueOf = CharsKt__CharJVMKt.titlecase(charAt, ROOT);
                } else {
                    valueOf = String.valueOf(charAt);
                }
                sb3.append((Object) valueOf);
                String substring = fenceMode2.substring(1);
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
                sb3.append(substring);
                str = sb3.toString();
            } else {
                str = fenceMode2;
            }
        }
        sb2.append(str);
        sb2.append(" Fence");
        tvFenceType.setText(sb2.toString());
        if (!Intrinsics.areEqual(getGeoFenceResponseItem2.getTimeMode(), AppConstants.GEO_FENCE_TIME_MODE_SKIP)) {
            try {
                ExtensionsKt.show(holder.getTvTime());
                ExtensionsKt.show(holder.getTvDate());
                AppCompatTextView tvTime = holder.getTvTime();
                StringBuilder sb4 = new StringBuilder();
                companion = AppUtil.Companion;
                String startTime = getGeoFenceResponseItem2.getStartTime();
                Intrinsics.checkNotNull(startTime);
                sb4.append(companion.convertToTime(startTime));
                sb4.append(" - ");
                String endTime = getGeoFenceResponseItem2.getEndTime();
                Intrinsics.checkNotNull(endTime);
                sb4.append(companion.convertToTime(endTime));
                tvTime.setText(sb4.toString());
            } catch (Exception unused) {
            }
            if (!Intrinsics.areEqual(getGeoFenceResponseItem2.getTimeMode(), AppConstants.GEO_FENCE_TIME_MODE_FULL_DAY_ALL_DAYS) && !Intrinsics.areEqual(getGeoFenceResponseItem2.getTimeMode(), AppConstants.GEO_FENCE_TIME_MODE_FULL_DAY_WEEK_DAYS) && !Intrinsics.areEqual(getGeoFenceResponseItem2.getTimeMode(), AppConstants.GEO_FENCE_TIME_MODE_FULL_DAY_CUSTOM_DAYS) && !Intrinsics.areEqual(getGeoFenceResponseItem2.getTimeMode(), AppConstants.GEO_FENCE_TIME_MODE_SET_TIME_ALL_DAYS) && !Intrinsics.areEqual(getGeoFenceResponseItem2.getTimeMode(), AppConstants.GEO_FENCE_TIME_MODE_SET_TIME_WEEK_DAYS) && !Intrinsics.areEqual(getGeoFenceResponseItem2.getTimeMode(), AppConstants.GEO_FENCE_TIME_MODE_SET_TIME_CUSTOM_DAYS)) {
                tvDate = holder.getTvDate();
                sb = new StringBuilder();
                String startTime2 = getGeoFenceResponseItem2.getStartTime();
                Intrinsics.checkNotNull(startTime2);
                sb.append(companion.convertToDate(startTime2));
                sb.append(" - ");
                String endTime2 = getGeoFenceResponseItem2.getEndTime();
                Intrinsics.checkNotNull(endTime2);
                sb.append(companion.convertToDate(endTime2));
                tvDate.setText(sb.toString());
                holder.getSwitchFence().setChecked(Intrinsics.areEqual(getGeoFenceResponseItem2.getFenceStatus(), ExifInterface.GPS_MEASUREMENT_IN_PROGRESS));
                size = this.geoFenceList.size() - 1;
                View divider = holder.getDivider();
                if (i2 != size) {
                    ExtensionsKt.hide(divider);
                    return;
                } else {
                    ExtensionsKt.show(divider);
                    return;
                }
            }
            if (getGeoFenceResponseItem2.getDaysOfWeek() != null && (!daysOfWeek.isEmpty())) {
                z = true;
            }
            if (z) {
                AppCompatTextView tvDate2 = holder.getTvDate();
                List<String> daysOfWeek2 = getGeoFenceResponseItem2.getDaysOfWeek();
                Intrinsics.checkNotNull(daysOfWeek2);
                tvDate2.setText(TextUtils.join(", ", daysOfWeek2));
                holder.getSwitchFence().setChecked(Intrinsics.areEqual(getGeoFenceResponseItem2.getFenceStatus(), ExifInterface.GPS_MEASUREMENT_IN_PROGRESS));
                size = this.geoFenceList.size() - 1;
                View divider2 = holder.getDivider();
                if (i2 != size) {
                }
            } else {
                tvDate = holder.getTvDate();
                sb = new StringBuilder();
                String startTime3 = getGeoFenceResponseItem2.getStartTime();
                Intrinsics.checkNotNull(startTime3);
                sb.append(companion.convertToDate(startTime3));
                sb.append(" - ");
                String endTime3 = getGeoFenceResponseItem2.getEndTime();
                Intrinsics.checkNotNull(endTime3);
                sb.append(companion.convertToDate(endTime3));
                tvDate.setText(sb.toString());
                holder.getSwitchFence().setChecked(Intrinsics.areEqual(getGeoFenceResponseItem2.getFenceStatus(), ExifInterface.GPS_MEASUREMENT_IN_PROGRESS));
                size = this.geoFenceList.size() - 1;
                View divider22 = holder.getDivider();
                if (i2 != size) {
                }
            }
        }
        ExtensionsKt.hide(holder.getTvTime());
        ExtensionsKt.hide(holder.getTvDate());
        holder.getSwitchFence().setChecked(Intrinsics.areEqual(getGeoFenceResponseItem2.getFenceStatus(), ExifInterface.GPS_MEASUREMENT_IN_PROGRESS));
        size = this.geoFenceList.size() - 1;
        View divider222 = holder.getDivider();
        if (i2 != size) {
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public GeoFenceViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (this.context == null) {
            this.context = parent.getContext();
        }
        View inflate = LayoutInflater.from(this.context).inflate(uat.psa.mym.mycitroenconnect.R.layout.item_view_geo_fence_list, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(context)\n          …ence_list, parent, false)");
        return new GeoFenceViewHolder(this, inflate);
    }

    public final void setOnGeoFenceClickListener(@NotNull GeoFenceClickListener geoFenceClickListener) {
        Intrinsics.checkNotNullParameter(geoFenceClickListener, "geoFenceClickListener");
        this.geoFenceClickListener = geoFenceClickListener;
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void updateList(@NotNull ArrayList<GetGeoFenceResponseItem> geoFenceList) {
        Intrinsics.checkNotNullParameter(geoFenceList, "geoFenceList");
        geoFenceList.addAll(geoFenceList);
        notifyDataSetChanged();
    }
}
