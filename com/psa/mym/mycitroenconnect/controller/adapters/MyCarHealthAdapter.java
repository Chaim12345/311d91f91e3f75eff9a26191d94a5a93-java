package com.psa.mym.mycitroenconnect.controller.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.model.my_car.MyCarHealth;
import java.util.ArrayList;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class MyCarHealthAdapter extends RecyclerView.Adapter<MyCarHealthViewHolder> {
    @Nullable
    private Context context;
    @NotNull
    private ArrayList<MyCarHealth> myCarStatusList = new ArrayList<>();

    /* loaded from: classes3.dex */
    public final class MyCarHealthViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @NotNull
        private AppCompatImageView ivIcon;
        @NotNull
        private AppCompatTextView tvCarDiagnosticLbl;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyCarHealthViewHolder(@NotNull MyCarHealthAdapter myCarHealthAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.tvCarDiagnosticLbl);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.tvCarDiagnosticLbl)");
            this.tvCarDiagnosticLbl = (AppCompatTextView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.ivIcon);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.ivIcon)");
            this.ivIcon = (AppCompatImageView) findViewById2;
        }

        @NotNull
        public final AppCompatImageView getIvIcon() {
            return this.ivIcon;
        }

        @NotNull
        public final AppCompatTextView getTvCarDiagnosticLbl() {
            return this.tvCarDiagnosticLbl;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(@Nullable View view) {
        }

        public final void setIvIcon(@NotNull AppCompatImageView appCompatImageView) {
            Intrinsics.checkNotNullParameter(appCompatImageView, "<set-?>");
            this.ivIcon = appCompatImageView;
        }

        public final void setTvCarDiagnosticLbl(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvCarDiagnosticLbl = appCompatTextView;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.myCarStatusList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull MyCarHealthViewHolder holder, int i2) {
        Drawable drawable;
        Intrinsics.checkNotNullParameter(holder, "holder");
        MyCarHealth myCarHealth = this.myCarStatusList.get(i2);
        Intrinsics.checkNotNullExpressionValue(myCarHealth, "myCarStatusList[position]");
        MyCarHealth myCarHealth2 = myCarHealth;
        holder.getTvCarDiagnosticLbl().setText(myCarHealth2.getHealthStatus());
        AppCompatImageView ivIcon = holder.getIvIcon();
        Context context = this.context;
        if (context != null) {
            String lowerCase = myCarHealth2.getLabel().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            drawable = ContextCompat.getDrawable(context, Intrinsics.areEqual(lowerCase, "ok") ? R.drawable.ic_success_green : R.drawable.ic_issue);
        } else {
            drawable = null;
        }
        ivIcon.setImageDrawable(drawable);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public MyCarHealthViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (this.context == null) {
            this.context = parent.getContext();
        }
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.item_view_diagnosed_details, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(context)\n          …d_details, parent, false)");
        return new MyCarHealthViewHolder(this, inflate);
    }

    public final void setVehicleHealthList(@NotNull ArrayList<MyCarHealth> myCarStatusList) {
        Intrinsics.checkNotNullParameter(myCarStatusList, "myCarStatusList");
        this.myCarStatusList = myCarStatusList;
        notifyDataSetChanged();
    }
}
