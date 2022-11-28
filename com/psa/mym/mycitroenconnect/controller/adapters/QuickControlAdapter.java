package com.psa.mym.mycitroenconnect.controller.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.model.QuickControl;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class QuickControlAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull
    private Context context;
    @NotNull
    private ArrayList<QuickControl> controlList;
    @Nullable
    private QuickControlInterface onControlListener;

    /* loaded from: classes3.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ QuickControlAdapter f10472a;
        @Nullable
        private AppCompatImageButton btnQuickControl;
        @Nullable
        private LinearLayoutCompat linearLayout;
        @Nullable
        private ConstraintLayout quickLayoutRootView;
        @Nullable
        private AppCompatTextView tvControlName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull QuickControlAdapter quickControlAdapter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.f10472a = quickControlAdapter;
            this.tvControlName = (AppCompatTextView) view.findViewById(R.id.tvQCTitle);
            this.linearLayout = (LinearLayoutCompat) view.findViewById(R.id.linearLayout);
            this.btnQuickControl = (AppCompatImageButton) view.findViewById(R.id.btnQuickControl);
            this.quickLayoutRootView = (ConstraintLayout) view.findViewById(R.id.quickLayoutRootView);
            AppCompatImageButton appCompatImageButton = this.btnQuickControl;
            if (appCompatImageButton != null) {
                appCompatImageButton.setOnClickListener(this);
            }
        }

        @Nullable
        public final AppCompatImageButton getBtnQuickControl() {
            return this.btnQuickControl;
        }

        @Nullable
        public final LinearLayoutCompat getLinearLayout() {
            return this.linearLayout;
        }

        @Nullable
        public final AppCompatTextView getTvControlName() {
            return this.tvControlName;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(@Nullable View view) {
            QuickControlInterface quickControlInterface;
            Integer valueOf = view != null ? Integer.valueOf(view.getId()) : null;
            if (valueOf == null || valueOf.intValue() != uat.psa.mym.mycitroenconnect.R.id.btnQuickControl || (quickControlInterface = this.f10472a.onControlListener) == null) {
                return;
            }
            Object obj = this.f10472a.controlList.get(getAbsoluteAdapterPosition());
            Intrinsics.checkNotNullExpressionValue(obj, "controlList[absoluteAdapterPosition]");
            quickControlInterface.onItemClick((QuickControl) obj, getAbsoluteAdapterPosition());
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(@Nullable View view) {
            if (view != null) {
                view.getId();
                return true;
            }
            return true;
        }

        public final void setBtnQuickControl(@Nullable AppCompatImageButton appCompatImageButton) {
            this.btnQuickControl = appCompatImageButton;
        }

        public final void setLinearLayout(@Nullable LinearLayoutCompat linearLayoutCompat) {
            this.linearLayout = linearLayoutCompat;
        }

        public final void setTvControlName(@Nullable AppCompatTextView appCompatTextView) {
            this.tvControlName = appCompatTextView;
        }
    }

    public QuickControlAdapter(@NotNull Context context, @NotNull ArrayList<QuickControl> controlList, @Nullable QuickControlInterface quickControlInterface) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(controlList, "controlList");
        this.context = context;
        this.controlList = controlList;
        this.onControlListener = quickControlInterface;
    }

    public /* synthetic */ QuickControlAdapter(Context context, ArrayList arrayList, QuickControlInterface quickControlInterface, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, arrayList, (i2 & 4) != 0 ? null : quickControlInterface);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.controlList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        QuickControl quickControl = this.controlList.get(holder.getAbsoluteAdapterPosition());
        Intrinsics.checkNotNullExpressionValue(quickControl, "controlList[holder.absoluteAdapterPosition]");
        QuickControl quickControl2 = quickControl;
        AppCompatTextView tvControlName = holder.getTvControlName();
        if (tvControlName != null) {
            tvControlName.setText(quickControl2.getControlName());
        }
        Drawable drawable = this.context.getDrawable(quickControl2.getControlImage());
        AppCompatImageButton btnQuickControl = holder.getBtnQuickControl();
        if (btnQuickControl != null) {
            btnQuickControl.setImageDrawable(drawable);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(uat.psa.mym.mycitroenconnect.R.layout.cell_quick_control, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context)\n   …k_control, parent, false)");
        return new ViewHolder(this, inflate);
    }

    public final void updateControlList(@NotNull ArrayList<QuickControl> controlArrayList) {
        Intrinsics.checkNotNullParameter(controlArrayList, "controlArrayList");
        this.controlList = controlArrayList;
        notifyDataSetChanged();
    }
}
