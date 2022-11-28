package com.psa.mym.mycitroenconnect.controller.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisteredVehicleItem;
import java.util.ArrayList;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class SelectCarAccessAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int VIEW_TYPE_CAR_ACCESS_GRANTED = 2;
    public static final int VIEW_TYPE_CAR_ACCESS_NOT_GRANTED = 3;
    public static final int VIEW_TYPE_CAR_CONFIRM_DLG = 4;
    public static final int VIEW_TYPE_DEFAULT = 1;
    @Nullable
    private CarAccessListener carAccessListener;
    @NotNull
    private ArrayList<RegisteredVehicleItem> carList;
    @Nullable
    private Context context;
    private int lastCheckedPos;

    /* loaded from: classes3.dex */
    public final class CarAccessGrantedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ SelectCarAccessAdapter f10473a;
        @NotNull
        private AppCompatButton btnRevokeAccess;
        @NotNull
        private AppCompatTextView tvCagCarNumber;
        @NotNull
        private AppCompatTextView tvCagName;
        @NotNull
        private AppCompatTextView tvCagStatus;
        @NotNull
        private View viewStatusDot;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CarAccessGrantedViewHolder(@NotNull SelectCarAccessAdapter selectCarAccessAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.f10473a = selectCarAccessAdapter;
            AppCompatTextView appCompatTextView = (AppCompatTextView) itemView.findViewById(R.id.tvCagName);
            Intrinsics.checkNotNullExpressionValue(appCompatTextView, "itemView.tvCagName");
            this.tvCagName = appCompatTextView;
            AppCompatTextView appCompatTextView2 = (AppCompatTextView) itemView.findViewById(R.id.tvCagCarNumber);
            Intrinsics.checkNotNullExpressionValue(appCompatTextView2, "itemView.tvCagCarNumber");
            this.tvCagCarNumber = appCompatTextView2;
            AppCompatTextView appCompatTextView3 = (AppCompatTextView) itemView.findViewById(R.id.tvCagStatus);
            Intrinsics.checkNotNullExpressionValue(appCompatTextView3, "itemView.tvCagStatus");
            this.tvCagStatus = appCompatTextView3;
            View findViewById = itemView.findViewById(R.id.viewStatusDot);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.viewStatusDot");
            this.viewStatusDot = findViewById;
            AppCompatButton appCompatButton = (AppCompatButton) itemView.findViewById(R.id.btnRevokeAccess);
            Intrinsics.checkNotNullExpressionValue(appCompatButton, "itemView.btnRevokeAccess");
            this.btnRevokeAccess = appCompatButton;
            appCompatButton.setOnClickListener(this);
        }

        @NotNull
        public final AppCompatButton getBtnRevokeAccess() {
            return this.btnRevokeAccess;
        }

        @NotNull
        public final AppCompatTextView getTvCagCarNumber() {
            return this.tvCagCarNumber;
        }

        @NotNull
        public final AppCompatTextView getTvCagName() {
            return this.tvCagName;
        }

        @NotNull
        public final AppCompatTextView getTvCagStatus() {
            return this.tvCagStatus;
        }

        @NotNull
        public final View getViewStatusDot() {
            return this.viewStatusDot;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(@Nullable View view) {
            CarAccessListener carAccessListener = this.f10473a.carAccessListener;
            if (carAccessListener != null) {
                int absoluteAdapterPosition = getAbsoluteAdapterPosition();
                Object obj = this.f10473a.carList.get(getAbsoluteAdapterPosition());
                Intrinsics.checkNotNullExpressionValue(obj, "carList[absoluteAdapterPosition]");
                carAccessListener.onCarAccessButtonTap(absoluteAdapterPosition, (RegisteredVehicleItem) obj);
            }
        }

        public final void setBtnRevokeAccess(@NotNull AppCompatButton appCompatButton) {
            Intrinsics.checkNotNullParameter(appCompatButton, "<set-?>");
            this.btnRevokeAccess = appCompatButton;
        }

        public final void setTvCagCarNumber(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvCagCarNumber = appCompatTextView;
        }

        public final void setTvCagName(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvCagName = appCompatTextView;
        }

        public final void setTvCagStatus(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvCagStatus = appCompatTextView;
        }

        public final void setViewStatusDot(@NotNull View view) {
            Intrinsics.checkNotNullParameter(view, "<set-?>");
            this.viewStatusDot = view;
        }
    }

    /* loaded from: classes3.dex */
    public final class CarAccessNotGrantedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ SelectCarAccessAdapter f10474a;
        @NotNull
        private AppCompatButton btnGrantAccess;
        @NotNull
        private AppCompatTextView tvCarName;
        @NotNull
        private AppCompatTextView tvCarNumber;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CarAccessNotGrantedViewHolder(@NotNull SelectCarAccessAdapter selectCarAccessAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.f10474a = selectCarAccessAdapter;
            AppCompatTextView appCompatTextView = (AppCompatTextView) itemView.findViewById(R.id.tvCangCarName);
            Intrinsics.checkNotNullExpressionValue(appCompatTextView, "itemView.tvCangCarName");
            this.tvCarName = appCompatTextView;
            AppCompatTextView appCompatTextView2 = (AppCompatTextView) itemView.findViewById(R.id.tvCangCarNumber);
            Intrinsics.checkNotNullExpressionValue(appCompatTextView2, "itemView.tvCangCarNumber");
            this.tvCarNumber = appCompatTextView2;
            AppCompatButton appCompatButton = (AppCompatButton) itemView.findViewById(R.id.btnGrantAccess);
            Intrinsics.checkNotNullExpressionValue(appCompatButton, "itemView.btnGrantAccess");
            this.btnGrantAccess = appCompatButton;
            appCompatButton.setOnClickListener(this);
        }

        @NotNull
        public final AppCompatButton getBtnGrantAccess() {
            return this.btnGrantAccess;
        }

        @NotNull
        public final AppCompatTextView getTvCarName() {
            return this.tvCarName;
        }

        @NotNull
        public final AppCompatTextView getTvCarNumber() {
            return this.tvCarNumber;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(@Nullable View view) {
            CarAccessListener carAccessListener = this.f10474a.carAccessListener;
            if (carAccessListener != null) {
                int absoluteAdapterPosition = getAbsoluteAdapterPosition();
                Object obj = this.f10474a.carList.get(getAbsoluteAdapterPosition());
                Intrinsics.checkNotNullExpressionValue(obj, "carList[absoluteAdapterPosition]");
                carAccessListener.onCarAccessButtonTap(absoluteAdapterPosition, (RegisteredVehicleItem) obj);
            }
        }

        public final void setBtnGrantAccess(@NotNull AppCompatButton appCompatButton) {
            Intrinsics.checkNotNullParameter(appCompatButton, "<set-?>");
            this.btnGrantAccess = appCompatButton;
        }

        public final void setTvCarName(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvCarName = appCompatTextView;
        }

        public final void setTvCarNumber(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvCarNumber = appCompatTextView;
        }
    }

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes3.dex */
    public final class SelectCarAccessViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ SelectCarAccessAdapter f10475a;
        @NotNull
        private MaterialRadioButton rbSelect;
        @NotNull
        private AppCompatTextView tvCarName;
        @NotNull
        private AppCompatTextView tvCarNumber;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SelectCarAccessViewHolder(@NotNull SelectCarAccessAdapter selectCarAccessAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.f10475a = selectCarAccessAdapter;
            AppCompatTextView appCompatTextView = (AppCompatTextView) itemView.findViewById(R.id.tvCarName);
            Intrinsics.checkNotNullExpressionValue(appCompatTextView, "itemView.tvCarName");
            this.tvCarName = appCompatTextView;
            AppCompatTextView appCompatTextView2 = (AppCompatTextView) itemView.findViewById(R.id.tvCarNumber);
            Intrinsics.checkNotNullExpressionValue(appCompatTextView2, "itemView.tvCarNumber");
            this.tvCarNumber = appCompatTextView2;
            MaterialRadioButton materialRadioButton = (MaterialRadioButton) itemView.findViewById(R.id.rbSelect);
            Intrinsics.checkNotNullExpressionValue(materialRadioButton, "itemView.rbSelect");
            this.rbSelect = materialRadioButton;
            materialRadioButton.setOnClickListener(this);
        }

        @NotNull
        public final MaterialRadioButton getRbSelect() {
            return this.rbSelect;
        }

        @NotNull
        public final AppCompatTextView getTvCarName() {
            return this.tvCarName;
        }

        @NotNull
        public final AppCompatTextView getTvCarNumber() {
            return this.tvCarNumber;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(@Nullable CompoundButton compoundButton, boolean z) {
            ArrayList arrayList = this.f10475a.carList;
            if (!z) {
                ((RegisteredVehicleItem) arrayList.get(getAbsoluteAdapterPosition())).setAccessible(z);
                CarAccessListener carAccessListener = this.f10475a.carAccessListener;
                if (carAccessListener != null) {
                    int absoluteAdapterPosition = getAbsoluteAdapterPosition();
                    Object obj = this.f10475a.carList.get(getAbsoluteAdapterPosition());
                    Intrinsics.checkNotNullExpressionValue(obj, "carList[absoluteAdapterPosition]");
                    carAccessListener.onCarAccessChanged(absoluteAdapterPosition, (RegisteredVehicleItem) obj);
                    return;
                }
                return;
            }
            SelectCarAccessAdapter selectCarAccessAdapter = this.f10475a;
            int i2 = 0;
            for (Object obj2 : arrayList) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                RegisteredVehicleItem registeredVehicleItem = (RegisteredVehicleItem) obj2;
                registeredVehicleItem.setAccessible(i2 == getAbsoluteAdapterPosition());
                CarAccessListener carAccessListener2 = selectCarAccessAdapter.carAccessListener;
                if (carAccessListener2 != null) {
                    carAccessListener2.onCarAccessChanged(i2, registeredVehicleItem);
                }
                i2 = i3;
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(@Nullable View view) {
            if (Intrinsics.areEqual(view, this.rbSelect)) {
                this.f10475a.lastCheckedPos = getAbsoluteAdapterPosition();
                this.f10475a.notifyDataSetChanged();
            }
        }

        public final void setRbSelect(@NotNull MaterialRadioButton materialRadioButton) {
            Intrinsics.checkNotNullParameter(materialRadioButton, "<set-?>");
            this.rbSelect = materialRadioButton;
        }

        public final void setTvCarName(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvCarName = appCompatTextView;
        }

        public final void setTvCarNumber(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvCarNumber = appCompatTextView;
        }
    }

    public SelectCarAccessAdapter(@NotNull ArrayList<RegisteredVehicleItem> carList) {
        Intrinsics.checkNotNullParameter(carList, "carList");
        this.carList = carList;
        this.lastCheckedPos = -1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.carList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        return this.carList.get(i2).getViewType();
    }

    public final boolean isCarAccessGiven() {
        ArrayList arrayList = new ArrayList();
        for (RegisteredVehicleItem registeredVehicleItem : this.carList) {
            arrayList.add(Boolean.valueOf(registeredVehicleItem.isAccessible()));
        }
        return arrayList.contains(Boolean.TRUE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x00c2, code lost:
        if (r3 != null) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00f1, code lost:
        if (r3 != null) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00f3, code lost:
        r2 = r3.getString(uat.psa.mym.mycitroenconnect.R.string.lbl_secondary_user_status_pending);
     */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onBindViewHolder(@NotNull RecyclerView.ViewHolder holder, int i2) {
        AppCompatTextView tvCagStatus;
        Context context;
        Intrinsics.checkNotNullParameter(holder, "holder");
        RegisteredVehicleItem registeredVehicleItem = this.carList.get(i2);
        Intrinsics.checkNotNullExpressionValue(registeredVehicleItem, "carList[position]");
        RegisteredVehicleItem registeredVehicleItem2 = registeredVehicleItem;
        int viewType = registeredVehicleItem2.getViewType();
        if (viewType == 1) {
            SelectCarAccessViewHolder selectCarAccessViewHolder = (SelectCarAccessViewHolder) holder;
            selectCarAccessViewHolder.getTvCarName().setText(registeredVehicleItem2.getCarModelName());
            selectCarAccessViewHolder.getTvCarNumber().setText(registeredVehicleItem2.getVehicleNumber());
            if (this.lastCheckedPos > -1) {
                selectCarAccessViewHolder.getRbSelect().setChecked(this.lastCheckedPos == selectCarAccessViewHolder.getAbsoluteAdapterPosition());
                registeredVehicleItem2.setAccessible(this.lastCheckedPos == selectCarAccessViewHolder.getAbsoluteAdapterPosition());
            }
        } else if (viewType != 2) {
            if (viewType == 3) {
                CarAccessNotGrantedViewHolder carAccessNotGrantedViewHolder = (CarAccessNotGrantedViewHolder) holder;
                carAccessNotGrantedViewHolder.getTvCarName().setText(registeredVehicleItem2.getCarModelName());
                carAccessNotGrantedViewHolder.getTvCarNumber().setText(registeredVehicleItem2.getVehicleNumber());
            } else if (viewType != 4) {
            } else {
                CarAccessNotGrantedViewHolder carAccessNotGrantedViewHolder2 = (CarAccessNotGrantedViewHolder) holder;
                carAccessNotGrantedViewHolder2.getTvCarName().setText(registeredVehicleItem2.getCarModelName());
                carAccessNotGrantedViewHolder2.getTvCarNumber().setText(registeredVehicleItem2.getVehicleNumber());
                carAccessNotGrantedViewHolder2.getBtnGrantAccess().setVisibility(8);
            }
        } else {
            CarAccessGrantedViewHolder carAccessGrantedViewHolder = (CarAccessGrantedViewHolder) holder;
            carAccessGrantedViewHolder.getTvCagName().setText(registeredVehicleItem2.getCarModelName());
            carAccessGrantedViewHolder.getTvCagCarNumber().setText(registeredVehicleItem2.getVehicleNumber());
            String inviteStatus = registeredVehicleItem2.getInviteStatus();
            if (inviteStatus != null) {
                int hashCode = inviteStatus.hashCode();
                if (hashCode != -1994383672) {
                    if (hashCode != -682587753) {
                        if (hashCode == 476588369 && inviteStatus.equals(AppConstants.SECONDARY_USER_STATE_CANCELLED)) {
                            tvCagStatus = carAccessGrantedViewHolder.getTvCagStatus();
                            Context context2 = this.context;
                            if (context2 != null) {
                                r2 = context2.getString(uat.psa.mym.mycitroenconnect.R.string.lbl_secondary_user_status_cancelled);
                            }
                        }
                    } else if (inviteStatus.equals(AppConstants.SECONDARY_USER_STATE_PENDING)) {
                        tvCagStatus = carAccessGrantedViewHolder.getTvCagStatus();
                        context = this.context;
                    }
                    tvCagStatus.setText(r2);
                    carAccessGrantedViewHolder.getViewStatusDot().setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.ic_pending);
                    return;
                } else if (inviteStatus.equals(AppConstants.SECONDARY_USER_STATE_VERIFIED)) {
                    AppCompatTextView tvCagStatus2 = carAccessGrantedViewHolder.getTvCagStatus();
                    Context context3 = this.context;
                    tvCagStatus2.setText(context3 != null ? context3.getString(uat.psa.mym.mycitroenconnect.R.string.lbl_secondary_user_status_active) : null);
                    carAccessGrantedViewHolder.getViewStatusDot().setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.ic_active);
                    return;
                }
            }
            tvCagStatus = carAccessGrantedViewHolder.getTvCagStatus();
            context = this.context;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        RecyclerView.ViewHolder selectCarAccessViewHolder;
        RecyclerView.ViewHolder viewHolder;
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (this.context == null) {
            this.context = parent.getContext();
        }
        if (i2 == 1) {
            View inflate = LayoutInflater.from(this.context).inflate(uat.psa.mym.mycitroenconnect.R.layout.item_view_select_car_access, parent, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "from(context)\n          …ar_access, parent, false)");
            selectCarAccessViewHolder = new SelectCarAccessViewHolder(this, inflate);
        } else if (i2 == 2) {
            View inflate2 = LayoutInflater.from(this.context).inflate(uat.psa.mym.mycitroenconnect.R.layout.item_view_car_access_granted, parent, false);
            Intrinsics.checkNotNullExpressionValue(inflate2, "from(context)\n          …s_granted, parent, false)");
            selectCarAccessViewHolder = new CarAccessGrantedViewHolder(this, inflate2);
        } else if (i2 == 3) {
            View inflate3 = LayoutInflater.from(this.context).inflate(uat.psa.mym.mycitroenconnect.R.layout.item_view_car_access_not_granted, parent, false);
            Intrinsics.checkNotNullExpressionValue(inflate3, "from(context)\n          …t_granted, parent, false)");
            selectCarAccessViewHolder = new CarAccessNotGrantedViewHolder(this, inflate3);
        } else if (i2 != 4) {
            viewHolder = null;
            Intrinsics.checkNotNull(viewHolder);
            return viewHolder;
        } else {
            View inflate4 = LayoutInflater.from(this.context).inflate(uat.psa.mym.mycitroenconnect.R.layout.item_view_car_access_not_granted, parent, false);
            Intrinsics.checkNotNullExpressionValue(inflate4, "from(context)\n          …t_granted, parent, false)");
            selectCarAccessViewHolder = new CarAccessNotGrantedViewHolder(this, inflate4);
        }
        viewHolder = selectCarAccessViewHolder;
        Intrinsics.checkNotNull(viewHolder);
        return viewHolder;
    }

    public final void setCarAccessListener(@Nullable CarAccessListener carAccessListener) {
        this.carAccessListener = carAccessListener;
    }
}
