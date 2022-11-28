package com.psa.mym.mycitroenconnect.controller.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.model.NotificationType;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class NotificationTypeAdapter extends RecyclerView.Adapter<NotificationTypeViewHolder> {
    @Nullable
    private Context context;
    @Nullable
    private OnNotificationTypeClickListener onNotificationTypeClickListener;
    @NotNull
    private List<NotificationType> types;

    /* loaded from: classes3.dex */
    public final class NotificationTypeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ NotificationTypeAdapter f10466a;
        @NotNull
        private final AppCompatTextView tvNotificationType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NotificationTypeViewHolder(@NotNull NotificationTypeAdapter notificationTypeAdapter, View iteView) {
            super(iteView);
            Intrinsics.checkNotNullParameter(iteView, "iteView");
            this.f10466a = notificationTypeAdapter;
            View findViewById = this.itemView.findViewById(R.id.tvNotificationType);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.tvNotificationType)");
            this.tvNotificationType = (AppCompatTextView) findViewById;
            iteView.setOnClickListener(this);
        }

        @NotNull
        public final AppCompatTextView getTvNotificationType() {
            return this.tvNotificationType;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(@Nullable View view) {
            OnNotificationTypeClickListener onNotificationTypeClickListener;
            if (getAbsoluteAdapterPosition() <= -1 || (onNotificationTypeClickListener = this.f10466a.getOnNotificationTypeClickListener()) == null) {
                return;
            }
            onNotificationTypeClickListener.onNotificationTypeClick(getAbsoluteAdapterPosition());
        }
    }

    public NotificationTypeAdapter(@NotNull List<NotificationType> types, @Nullable OnNotificationTypeClickListener onNotificationTypeClickListener) {
        Intrinsics.checkNotNullParameter(types, "types");
        this.types = types;
        this.onNotificationTypeClickListener = onNotificationTypeClickListener;
    }

    public /* synthetic */ NotificationTypeAdapter(List list, OnNotificationTypeClickListener onNotificationTypeClickListener, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i2 & 2) != 0 ? null : onNotificationTypeClickListener);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.types.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        return i2;
    }

    @Nullable
    public final OnNotificationTypeClickListener getOnNotificationTypeClickListener() {
        return this.onNotificationTypeClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull NotificationTypeViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getTvNotificationType().setText(this.types.get(i2).getNotificationType());
        holder.getTvNotificationType().setSelected(this.types.get(i2).isSelected());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public NotificationTypeViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (this.context == null) {
            this.context = parent.getContext();
        }
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.item_view_notification_type, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(context)\n          …  false\n                )");
        return new NotificationTypeViewHolder(this, inflate);
    }

    public final void setOnNotificationTypeClickListener(@Nullable OnNotificationTypeClickListener onNotificationTypeClickListener) {
        this.onNotificationTypeClickListener = onNotificationTypeClickListener;
    }
}
