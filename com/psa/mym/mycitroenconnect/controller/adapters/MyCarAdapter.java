package com.psa.mym.mycitroenconnect.controller.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCar;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class MyCarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_ITEM = 1;
    @NotNull
    private List<? extends Object> carList;
    @NotNull
    private Context context;
    @Nullable
    private AppCompatRadioButton lastChecked;
    private int lastCheckedPos;
    @Nullable
    private CarInterface onCarListener;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes3.dex */
    public final class HeaderViewHolder extends RecyclerView.ViewHolder {
        @Nullable
        private AppCompatTextView tvHeader;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public HeaderViewHolder(@NotNull MyCarAdapter myCarAdapter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.tvHeader = (AppCompatTextView) view.findViewById(R.id.tvHeader);
        }

        @Nullable
        public final AppCompatTextView getTvHeader() {
            return this.tvHeader;
        }

        public final void setTvHeader(@Nullable AppCompatTextView appCompatTextView) {
            this.tvHeader = appCompatTextView;
        }
    }

    /* loaded from: classes3.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ MyCarAdapter f10462a;
        @Nullable
        private AppCompatRadioButton checkMyCar;
        @Nullable
        private AppCompatImageView ivCar;
        @Nullable
        private MaterialCardView layoutCarCard;
        @Nullable
        private LinearLayoutCompat layoutCarTitle;
        @Nullable
        private AppCompatTextView tvCarName;
        @Nullable
        private AppCompatTextView tvCarNumber;
        @Nullable
        private AppCompatTextView tvViewDetails;
        @Nullable
        private AppCompatTextView tvViewOnly;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull MyCarAdapter myCarAdapter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.f10462a = myCarAdapter;
            this.tvCarName = (AppCompatTextView) view.findViewById(R.id.tvCarName);
            this.tvCarNumber = (AppCompatTextView) view.findViewById(R.id.tvCarNumber);
            this.tvViewOnly = (AppCompatTextView) view.findViewById(R.id.tvViewOnly);
            this.tvViewDetails = (AppCompatTextView) view.findViewById(R.id.tvViewDetails);
            this.ivCar = (AppCompatImageView) view.findViewById(R.id.ivCar);
            this.checkMyCar = (AppCompatRadioButton) view.findViewById(R.id.chkMyCar);
            this.layoutCarTitle = (LinearLayoutCompat) view.findViewById(R.id.layoutCarTitle);
            this.layoutCarCard = (MaterialCardView) view.findViewById(R.id.layoutCarCard);
            AppCompatTextView appCompatTextView = this.tvViewDetails;
            if (appCompatTextView != null) {
                appCompatTextView.setOnClickListener(this);
            }
            AppCompatRadioButton appCompatRadioButton = this.checkMyCar;
            if (appCompatRadioButton != null) {
                appCompatRadioButton.setEnabled(false);
            }
            AppCompatRadioButton appCompatRadioButton2 = this.checkMyCar;
            if (appCompatRadioButton2 != null) {
                appCompatRadioButton2.setClickable(false);
            }
            LinearLayoutCompat linearLayoutCompat = this.layoutCarTitle;
            if (linearLayoutCompat != null) {
                linearLayoutCompat.setOnClickListener(this);
            }
            AppCompatImageView appCompatImageView = this.ivCar;
            if (appCompatImageView != null) {
                appCompatImageView.setOnClickListener(this);
            }
            AppCompatTextView appCompatTextView2 = this.tvCarNumber;
            if (appCompatTextView2 != null) {
                appCompatTextView2.setOnClickListener(this);
            }
        }

        @Nullable
        public final AppCompatRadioButton getCheckMyCar() {
            return this.checkMyCar;
        }

        @Nullable
        public final AppCompatImageView getIvCar() {
            return this.ivCar;
        }

        @Nullable
        public final MaterialCardView getLayoutCarCard() {
            return this.layoutCarCard;
        }

        @Nullable
        public final LinearLayoutCompat getLayoutCarTitle() {
            return this.layoutCarTitle;
        }

        @Nullable
        public final AppCompatTextView getTvCarName() {
            return this.tvCarName;
        }

        @Nullable
        public final AppCompatTextView getTvCarNumber() {
            return this.tvCarNumber;
        }

        @Nullable
        public final AppCompatTextView getTvViewDetails() {
            return this.tvViewDetails;
        }

        @Nullable
        public final AppCompatTextView getTvViewOnly() {
            return this.tvViewOnly;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(@Nullable View view) {
            CarInterface carInterface;
            CarInterface carInterface2;
            Integer valueOf = view != null ? Integer.valueOf(view.getId()) : null;
            if (valueOf != null && valueOf.intValue() == uat.psa.mym.mycitroenconnect.R.id.tvViewDetails) {
                if (this.f10462a.onCarListener == null || getAbsoluteAdapterPosition() <= -1 || (carInterface2 = this.f10462a.onCarListener) == null) {
                    return;
                }
                carInterface2.onCarViewDetails((MyCar) this.f10462a.carList.get(getAbsoluteAdapterPosition()), getAbsoluteAdapterPosition());
                return;
            }
            boolean z = false;
            if ((((valueOf != null && valueOf.intValue() == uat.psa.mym.mycitroenconnect.R.id.layoutCarTitle) || (valueOf != null && valueOf.intValue() == uat.psa.mym.mycitroenconnect.R.id.chkMyCar)) || (valueOf != null && valueOf.intValue() == uat.psa.mym.mycitroenconnect.R.id.ivCar)) || (valueOf != null && valueOf.intValue() == uat.psa.mym.mycitroenconnect.R.id.tvCarNumber)) {
                z = true;
            }
            if (!z || this.f10462a.onCarListener == null || getAbsoluteAdapterPosition() <= -1) {
                return;
            }
            MyCar myCar = (MyCar) this.f10462a.carList.get(getAbsoluteAdapterPosition());
            if (myCar.isVehicleSelected() || (carInterface = this.f10462a.onCarListener) == null) {
                return;
            }
            carInterface.onCarChange(myCar, getAbsoluteAdapterPosition());
        }

        public final void setCheckMyCar(@Nullable AppCompatRadioButton appCompatRadioButton) {
            this.checkMyCar = appCompatRadioButton;
        }

        public final void setIvCar(@Nullable AppCompatImageView appCompatImageView) {
            this.ivCar = appCompatImageView;
        }

        public final void setLayoutCarCard(@Nullable MaterialCardView materialCardView) {
            this.layoutCarCard = materialCardView;
        }

        public final void setLayoutCarTitle(@Nullable LinearLayoutCompat linearLayoutCompat) {
            this.layoutCarTitle = linearLayoutCompat;
        }

        public final void setTvCarName(@Nullable AppCompatTextView appCompatTextView) {
            this.tvCarName = appCompatTextView;
        }

        public final void setTvCarNumber(@Nullable AppCompatTextView appCompatTextView) {
            this.tvCarNumber = appCompatTextView;
        }

        public final void setTvViewDetails(@Nullable AppCompatTextView appCompatTextView) {
            this.tvViewDetails = appCompatTextView;
        }

        public final void setTvViewOnly(@Nullable AppCompatTextView appCompatTextView) {
            this.tvViewOnly = appCompatTextView;
        }
    }

    public MyCarAdapter(@NotNull Context context, @NotNull List<? extends Object> carList, @Nullable CarInterface carInterface) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(carList, "carList");
        this.context = context;
        this.carList = carList;
        this.onCarListener = carInterface;
        this.lastCheckedPos = -1;
    }

    public /* synthetic */ MyCarAdapter(Context context, List list, CarInterface carInterface, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, list, (i2 & 4) != 0 ? null : carInterface);
    }

    private final boolean isHeaderPosition(int i2) {
        return this.carList.get(i2) instanceof String;
    }

    private final boolean isMaximumCountItemSelected() {
        ArrayList arrayList = new ArrayList();
        for (Object obj : this.carList) {
            if (obj instanceof MyCar) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : arrayList) {
            if (((MyCar) obj2).isVehicleSelected()) {
                arrayList2.add(obj2);
            }
        }
        return arrayList.size() >= 1;
    }

    private final boolean isMoreThanOneCar() {
        ArrayList arrayList = new ArrayList();
        for (Object obj : this.carList) {
            if (obj instanceof MyCar) {
                arrayList.add(obj);
            }
        }
        return (arrayList.isEmpty() ^ true) && arrayList.size() > 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.carList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        return !isHeaderPosition(i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0118  */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onBindViewHolder(@NotNull RecyclerView.ViewHolder holder, int i2) {
        MaterialCardView layoutCarCard;
        Context context;
        int i3;
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (holder instanceof HeaderViewHolder) {
            String str = (String) this.carList.get(i2);
            AppCompatTextView tvHeader = ((HeaderViewHolder) holder).getTvHeader();
            if (tvHeader == null) {
                return;
            }
            tvHeader.setText(str);
        } else if (!(holder instanceof ViewHolder)) {
        } else {
            MyCar myCar = (MyCar) this.carList.get(i2);
            ViewHolder viewHolder = (ViewHolder) holder;
            AppCompatTextView tvCarName = viewHolder.getTvCarName();
            if (tvCarName != null) {
                tvCarName.setText(myCar.getCarModelName());
            }
            if (myCar.getVehicleRegNo() == null || TextUtils.isEmpty(myCar.getVehicleRegNo())) {
                AppCompatTextView tvCarNumber = viewHolder.getTvCarNumber();
                if (tvCarNumber != null) {
                    ExtensionsKt.hide(tvCarNumber);
                }
            } else {
                AppCompatTextView tvCarNumber2 = viewHolder.getTvCarNumber();
                if (tvCarNumber2 != null) {
                    tvCarNumber2.setText(myCar.getVehicleRegNo());
                }
            }
            Integer vehicleImage = myCar.getVehicleImage();
            if (vehicleImage != null) {
                int intValue = vehicleImage.intValue();
                AppCompatImageView ivCar = viewHolder.getIvCar();
                if (ivCar != null) {
                    ivCar.setImageResource(intValue);
                }
            }
            AppCompatRadioButton checkMyCar = viewHolder.getCheckMyCar();
            if (checkMyCar != null) {
                checkMyCar.setChecked(myCar.isVehicleSelected());
            }
            AppCompatRadioButton checkMyCar2 = viewHolder.getCheckMyCar();
            if (checkMyCar2 != null && checkMyCar2.isChecked()) {
                layoutCarCard = viewHolder.getLayoutCarCard();
                if (layoutCarCard != null) {
                    context = this.context;
                    i3 = uat.psa.mym.mycitroenconnect.R.color.light_grey;
                    ExtensionsKt.changeCardBackgroundColor(layoutCarCard, context, i3);
                }
                if (myCar.isViewOnly()) {
                    AppCompatTextView tvViewOnly = viewHolder.getTvViewOnly();
                    if (tvViewOnly != null) {
                        ExtensionsKt.hide(tvViewOnly);
                    }
                } else {
                    AppCompatTextView tvViewOnly2 = viewHolder.getTvViewOnly();
                    if (tvViewOnly2 != null) {
                        ExtensionsKt.show(tvViewOnly2);
                    }
                }
                if (isMoreThanOneCar()) {
                    AppCompatRadioButton checkMyCar3 = viewHolder.getCheckMyCar();
                    if (checkMyCar3 != null) {
                        checkMyCar3.setVisibility(8);
                    }
                    LinearLayoutCompat layoutCarTitle = viewHolder.getLayoutCarTitle();
                    if (layoutCarTitle != null) {
                        layoutCarTitle.setEnabled(false);
                    }
                    LinearLayoutCompat layoutCarTitle2 = viewHolder.getLayoutCarTitle();
                    if (layoutCarTitle2 != null) {
                        layoutCarTitle2.setClickable(false);
                    }
                    AppCompatImageView ivCar2 = viewHolder.getIvCar();
                    if (ivCar2 != null) {
                        ivCar2.setEnabled(false);
                    }
                    AppCompatImageView ivCar3 = viewHolder.getIvCar();
                    if (ivCar3 != null) {
                        ivCar3.setClickable(false);
                    }
                    AppCompatTextView tvCarNumber3 = viewHolder.getTvCarNumber();
                    if (tvCarNumber3 != null) {
                        tvCarNumber3.setEnabled(false);
                    }
                    AppCompatTextView tvCarNumber4 = viewHolder.getTvCarNumber();
                    if (tvCarNumber4 == null) {
                        return;
                    }
                    tvCarNumber4.setClickable(false);
                    return;
                }
                AppCompatRadioButton checkMyCar4 = viewHolder.getCheckMyCar();
                if (checkMyCar4 != null) {
                    checkMyCar4.setVisibility(0);
                }
                LinearLayoutCompat layoutCarTitle3 = viewHolder.getLayoutCarTitle();
                if (layoutCarTitle3 != null) {
                    layoutCarTitle3.setEnabled(true);
                }
                LinearLayoutCompat layoutCarTitle4 = viewHolder.getLayoutCarTitle();
                if (layoutCarTitle4 != null) {
                    layoutCarTitle4.setClickable(true);
                }
                AppCompatImageView ivCar4 = viewHolder.getIvCar();
                if (ivCar4 != null) {
                    ivCar4.setEnabled(true);
                }
                AppCompatImageView ivCar5 = viewHolder.getIvCar();
                if (ivCar5 != null) {
                    ivCar5.setClickable(true);
                }
                AppCompatTextView tvCarNumber5 = viewHolder.getTvCarNumber();
                if (tvCarNumber5 != null) {
                    tvCarNumber5.setEnabled(true);
                }
                AppCompatTextView tvCarNumber6 = viewHolder.getTvCarNumber();
                if (tvCarNumber6 == null) {
                    return;
                }
                tvCarNumber6.setClickable(true);
                return;
            }
            layoutCarCard = viewHolder.getLayoutCarCard();
            if (layoutCarCard != null) {
                context = this.context;
                i3 = uat.psa.mym.mycitroenconnect.R.color.white;
                ExtensionsKt.changeCardBackgroundColor(layoutCarCard, context, i3);
            }
            if (myCar.isViewOnly()) {
            }
            if (isMoreThanOneCar()) {
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (i2 == 0) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(uat.psa.mym.mycitroenconnect.R.layout.item_view_header_text, parent, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context)\n   …ader_text, parent, false)");
            return new HeaderViewHolder(this, inflate);
        }
        View inflate2 = LayoutInflater.from(parent.getContext()).inflate(uat.psa.mym.mycitroenconnect.R.layout.cell_my_car, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate2, "from(parent.context)\n   …ll_my_car, parent, false)");
        return new ViewHolder(this, inflate2);
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void updateCarList(@NotNull List<? extends Object> carArrayList) {
        Intrinsics.checkNotNullParameter(carArrayList, "carArrayList");
        this.carList = carArrayList;
        notifyDataSetChanged();
    }
}
