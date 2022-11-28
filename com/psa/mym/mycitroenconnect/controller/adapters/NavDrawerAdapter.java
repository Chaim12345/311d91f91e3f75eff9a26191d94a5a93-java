package com.psa.mym.mycitroenconnect.controller.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.model.NavItem;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class NavDrawerAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull
    private Context context;
    @NotNull
    private ArrayList<NavItem> navList;
    @Nullable
    private NavDrawerListInterface onNavListener;

    /* loaded from: classes3.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ NavDrawerAdapter f10464a;
        @NotNull
        private AppCompatTextView menuItem;
        @NotNull
        private AppCompatTextView menuTitle;
        @Nullable
        private ConstraintLayout navLayoutRootView;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull NavDrawerAdapter navDrawerAdapter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.f10464a = navDrawerAdapter;
            AppCompatTextView appCompatTextView = (AppCompatTextView) view.findViewById(R.id.tvTitle);
            Intrinsics.checkNotNullExpressionValue(appCompatTextView, "view.tvTitle");
            this.menuTitle = appCompatTextView;
            AppCompatTextView appCompatTextView2 = (AppCompatTextView) view.findViewById(R.id.tvNavItem);
            Intrinsics.checkNotNullExpressionValue(appCompatTextView2, "view.tvNavItem");
            this.menuItem = appCompatTextView2;
            ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.navLayoutRootView);
            this.navLayoutRootView = constraintLayout;
            if (constraintLayout != null) {
                constraintLayout.setOnClickListener(this);
            }
            this.menuItem.setOnClickListener(this);
        }

        @NotNull
        public final AppCompatTextView getMenuItem() {
            return this.menuItem;
        }

        @NotNull
        public final AppCompatTextView getMenuTitle() {
            return this.menuTitle;
        }

        @Nullable
        public final ConstraintLayout getNavLayoutRootView() {
            return this.navLayoutRootView;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(@Nullable View view) {
            NavDrawerListInterface onNavListener;
            Integer valueOf = view != null ? Integer.valueOf(view.getId()) : null;
            if (valueOf != null && valueOf.intValue() == uat.psa.mym.mycitroenconnect.R.id.navLayoutRootView) {
                onNavListener = this.f10464a.getOnNavListener();
                if (onNavListener == null) {
                    return;
                }
            } else if (valueOf == null || valueOf.intValue() != uat.psa.mym.mycitroenconnect.R.id.tvNavItem || (onNavListener = this.f10464a.getOnNavListener()) == null) {
                return;
            }
            Object obj = this.f10464a.navList.get(getAbsoluteAdapterPosition());
            Intrinsics.checkNotNullExpressionValue(obj, "navList[absoluteAdapterPosition]");
            onNavListener.onItemClick((NavItem) obj);
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(@Nullable View view) {
            Integer valueOf = view != null ? Integer.valueOf(view.getId()) : null;
            if (valueOf == null) {
                return true;
            }
            valueOf.intValue();
            return true;
        }

        public final void setMenuItem(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.menuItem = appCompatTextView;
        }

        public final void setMenuTitle(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.menuTitle = appCompatTextView;
        }

        public final void setNavLayoutRootView(@Nullable ConstraintLayout constraintLayout) {
            this.navLayoutRootView = constraintLayout;
        }
    }

    public NavDrawerAdapter(@NotNull Context context, @NotNull ArrayList<NavItem> navList, @Nullable NavDrawerListInterface navDrawerListInterface) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(navList, "navList");
        this.context = context;
        this.navList = navList;
        this.onNavListener = navDrawerListInterface;
    }

    public /* synthetic */ NavDrawerAdapter(Context context, ArrayList arrayList, NavDrawerListInterface navDrawerListInterface, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, arrayList, (i2 & 4) != 0 ? null : navDrawerListInterface);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.navList.size();
    }

    @Nullable
    public final NavDrawerListInterface getOnNavListener() {
        return this.onNavListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        NavItem navItem = this.navList.get(holder.getAbsoluteAdapterPosition());
        Intrinsics.checkNotNullExpressionValue(navItem, "navList[holder.absoluteAdapterPosition]");
        NavItem navItem2 = navItem;
        if (navItem2.isTitle()) {
            holder.getMenuTitle().setVisibility(0);
            holder.getMenuItem().setVisibility(8);
            holder.getMenuTitle().setText(navItem2.getMenuName());
            return;
        }
        holder.getMenuItem().setVisibility(0);
        holder.getMenuTitle().setVisibility(8);
        holder.getMenuItem().setText(navItem2.getMenuName());
        holder.getMenuItem().setCompoundDrawablesWithIntrinsicBounds(AppCompatResources.getDrawable(this.context, navItem2.getMenuDrawable()), (Drawable) null, (Drawable) null, (Drawable) null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(uat.psa.mym.mycitroenconnect.R.layout.cell_navigation, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context)\n   …avigation, parent, false)");
        return new ViewHolder(this, inflate);
    }

    public final void setOnNavListener(@Nullable NavDrawerListInterface navDrawerListInterface) {
        this.onNavListener = navDrawerListInterface;
    }
}
