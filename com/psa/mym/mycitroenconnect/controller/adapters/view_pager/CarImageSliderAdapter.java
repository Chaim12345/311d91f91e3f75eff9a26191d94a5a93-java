package com.psa.mym.mycitroenconnect.controller.adapters.view_pager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCar;
import java.util.ArrayList;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class CarImageSliderAdapter extends PagerAdapter {
    @NotNull
    private ArrayList<MyCar> carList;
    @NotNull
    private Context context;
    @Nullable
    private AppCompatImageButton ivNavLeftArrow;
    @Nullable
    private AppCompatImageButton ivNavRightArrow;

    public CarImageSliderAdapter(@NotNull Context context, @NotNull ArrayList<MyCar> carList) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(carList, "carList");
        this.context = context;
        this.carList = carList;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(@NotNull ViewGroup container, int i2, @NotNull Object object) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(object, "object");
        container.removeView((ConstraintLayout) object);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.carList.size();
    }

    @Nullable
    public final AppCompatImageButton getIvNavLeftArrow() {
        return this.ivNavLeftArrow;
    }

    @Nullable
    public final AppCompatImageButton getIvNavRightArrow() {
        return this.ivNavRightArrow;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    @NotNull
    public Object instantiateItem(@NotNull ViewGroup container, int i2) {
        Intrinsics.checkNotNullParameter(container, "container");
        Object systemService = this.context.getSystemService("layout_inflater");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.view.LayoutInflater");
        View inflate = ((LayoutInflater) systemService).inflate(R.layout.item_view_drawer_car, container, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "layoutInflater.inflate(R…er_car, container, false)");
        View findViewById = inflate.findViewById(R.id.tvCarName);
        Objects.requireNonNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        View findViewById2 = inflate.findViewById(R.id.tvCarModelName);
        Objects.requireNonNull(findViewById2, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        View findViewById3 = inflate.findViewById(R.id.ivCarImage);
        Objects.requireNonNull(findViewById3, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatImageView");
        AppCompatImageView appCompatImageView = (AppCompatImageView) findViewById3;
        ((AppCompatTextView) findViewById).setText(this.carList.get(i2).getCarModelName());
        ((AppCompatTextView) findViewById2).setText(this.carList.get(i2).getVehicleRegNo());
        appCompatImageView.setScaleType(ImageView.ScaleType.CENTER);
        Integer vehicleImage = this.carList.get(i2).getVehicleImage();
        if (vehicleImage != null) {
            appCompatImageView.setImageResource(vehicleImage.intValue());
        }
        Objects.requireNonNull(container);
        container.addView(inflate);
        return inflate;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(@NotNull View view, @NotNull Object object) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(object, "object");
        return view == ((ConstraintLayout) object);
    }

    public final void setIvNavLeftArrow(@Nullable AppCompatImageButton appCompatImageButton) {
        this.ivNavLeftArrow = appCompatImageButton;
    }

    public final void setIvNavRightArrow(@Nullable AppCompatImageButton appCompatImageButton) {
        this.ivNavRightArrow = appCompatImageButton;
    }

    public final void updateCarList(@NotNull ArrayList<MyCar> carList) {
        Intrinsics.checkNotNullParameter(carList, "carList");
        this.carList = carList;
        notifyDataSetChanged();
    }
}
