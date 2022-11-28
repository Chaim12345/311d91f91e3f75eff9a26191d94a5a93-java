package com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.share;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textview.MaterialTextView;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ApplicationListAdapter extends RecyclerView.Adapter<ApplicationViewHolder> {
    @Nullable
    private Context context;
    @Nullable
    private OnApplicationClickListener onApplicationClickListener;
    @Nullable
    private PackageManager packageManage;
    @NotNull
    private List<? extends ResolveInfo> resolveInfoList;

    /* loaded from: classes3.dex */
    public final class ApplicationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ ApplicationListAdapter f10489a;
        @NotNull
        private final AppCompatImageView ivIcon;
        @NotNull
        private final MaterialTextView tvAppName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ApplicationViewHolder(@NotNull ApplicationListAdapter applicationListAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.f10489a = applicationListAdapter;
            View findViewById = itemView.findViewById(R.id.ivIcon);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.ivIcon)");
            this.ivIcon = (AppCompatImageView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.tvAppName);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.tvAppName)");
            this.tvAppName = (MaterialTextView) findViewById2;
            itemView.setOnClickListener(this);
        }

        @NotNull
        public final AppCompatImageView getIvIcon() {
            return this.ivIcon;
        }

        @NotNull
        public final MaterialTextView getTvAppName() {
            return this.tvAppName;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(@Nullable View view) {
            OnApplicationClickListener onApplicationClickListener;
            if (getAbsoluteAdapterPosition() <= -1 || this.f10489a.onApplicationClickListener == null || (onApplicationClickListener = this.f10489a.onApplicationClickListener) == null) {
                return;
            }
            onApplicationClickListener.onChooseApp((ResolveInfo) this.f10489a.resolveInfoList.get(getAbsoluteAdapterPosition()));
        }
    }

    public ApplicationListAdapter(@NotNull List<? extends ResolveInfo> resolveInfoList) {
        Intrinsics.checkNotNullParameter(resolveInfoList, "resolveInfoList");
        this.resolveInfoList = resolveInfoList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.resolveInfoList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ApplicationViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (this.packageManage == null) {
            Context context = this.context;
            this.packageManage = context != null ? context.getPackageManager() : null;
        }
        ResolveInfo resolveInfo = this.resolveInfoList.get(i2);
        holder.getTvAppName().setText(resolveInfo.loadLabel(this.packageManage));
        holder.getIvIcon().setImageDrawable(resolveInfo.loadIcon(this.packageManage));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ApplicationViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (this.context == null) {
            this.context = parent.getContext();
        }
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.item_share_app, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(context)\n          …share_app, parent, false)");
        return new ApplicationViewHolder(this, inflate);
    }

    public final void setOnApplicationClickListener(@NotNull OnApplicationClickListener onApplicationClickListener) {
        Intrinsics.checkNotNullParameter(onApplicationClickListener, "onApplicationClickListener");
        this.onApplicationClickListener = onApplicationClickListener;
    }
}
