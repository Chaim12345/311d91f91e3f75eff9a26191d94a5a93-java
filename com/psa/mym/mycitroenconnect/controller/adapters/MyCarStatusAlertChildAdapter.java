package com.psa.mym.mycitroenconnect.controller.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.model.notification.Notification;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import java.util.Date;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class MyCarStatusAlertChildAdapter extends RecyclerView.Adapter<MyCarStatusAlertChildViewHolder> {
    @NotNull
    private List<Notification> alerts;
    @Nullable
    private Context context;

    /* loaded from: classes3.dex */
    public final class MyCarStatusAlertChildViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @NotNull
        private AppCompatImageView ivIcon;
        @NotNull
        private AppCompatTextView tvMessage;
        @NotNull
        private AppCompatTextView tvTime;
        @NotNull
        private AppCompatTextView tvTitle;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyCarStatusAlertChildViewHolder(@NotNull MyCarStatusAlertChildAdapter myCarStatusAlertChildAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.ivIcon);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.ivIcon)");
            this.ivIcon = (AppCompatImageView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.tvTitle);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.tvTitle)");
            this.tvTitle = (AppCompatTextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.tvTime);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.tvTime)");
            this.tvTime = (AppCompatTextView) findViewById3;
            View findViewById4 = itemView.findViewById(R.id.tvMessage);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "itemView.findViewById(R.id.tvMessage)");
            this.tvMessage = (AppCompatTextView) findViewById4;
        }

        @NotNull
        public final AppCompatImageView getIvIcon() {
            return this.ivIcon;
        }

        @NotNull
        public final AppCompatTextView getTvMessage() {
            return this.tvMessage;
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
        }

        public final void setIvIcon(@NotNull AppCompatImageView appCompatImageView) {
            Intrinsics.checkNotNullParameter(appCompatImageView, "<set-?>");
            this.ivIcon = appCompatImageView;
        }

        public final void setTvMessage(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvMessage = appCompatTextView;
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

    public MyCarStatusAlertChildAdapter(@NotNull List<Notification> alerts) {
        Intrinsics.checkNotNullParameter(alerts, "alerts");
        this.alerts = alerts;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.alerts.size();
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0038, code lost:
        if (r0.equals(androidx.exifinterface.media.ExifInterface.GPS_MEASUREMENT_3D) == false) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0041, code lost:
        if (r0.equals(androidx.exifinterface.media.ExifInterface.GPS_MEASUREMENT_2D) == false) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0044, code lost:
        r0 = r4.getIvIcon();
        r1 = r3.context;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r1);
     */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onBindViewHolder(@NotNull MyCarStatusAlertChildViewHolder holder, int i2) {
        AppCompatImageView ivIcon;
        Context context;
        Intrinsics.checkNotNullParameter(holder, "holder");
        Notification notification = this.alerts.get(i2);
        String priority = notification.getPriority();
        int hashCode = priority.hashCode();
        int i3 = R.drawable.ic_service_alert;
        switch (hashCode) {
            case 49:
                if (priority.equals("1")) {
                    ivIcon = holder.getIvIcon();
                    context = this.context;
                    Intrinsics.checkNotNull(context);
                    i3 = R.drawable.ic_critical_alert_no_background;
                    ivIcon.setImageDrawable(ContextCompat.getDrawable(context, i3));
                    break;
                }
                break;
            case 52:
                if (priority.equals("4")) {
                    ivIcon = holder.getIvIcon();
                    context = this.context;
                    Intrinsics.checkNotNull(context);
                    i3 = R.drawable.ic_vehicle_alert;
                    ivIcon.setImageDrawable(ContextCompat.getDrawable(context, i3));
                    break;
                }
                break;
        }
        holder.getTvTitle().setText(notification.getTitle());
        holder.getTvMessage().setText(notification.getBody());
        holder.getTvTime().setText(ExtensionsKt.getNotificationDate(new Date(notification.getSignalTimeStamp())));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public MyCarStatusAlertChildViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (this.context == null) {
            this.context = parent.getContext();
        }
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.item_view_my_car_status_alert_child, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(context)\n          …ert_child, parent, false)");
        return new MyCarStatusAlertChildViewHolder(this, inflate);
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void updateAlertList(@NotNull List<Notification> alerts) {
        Intrinsics.checkNotNullParameter(alerts, "alerts");
        this.alerts = alerts;
        notifyDataSetChanged();
    }
}
