package com.psa.mym.mycitroenconnect.controller.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.controller.adapters.NotificationsAdapter;
import com.psa.mym.mycitroenconnect.model.notification.Notification;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class NotificationsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_VIEW_TYPE = 1;
    public static final int LOADING_VIEW_TYPE = 0;
    @Nullable
    private Context context;
    @NotNull
    private ArrayList<Notification> filteredNotifications;
    @NotNull
    private ArrayList<Notification> notifications;
    @NotNull
    private OnNotificationClickListener onNotificationClickListener;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes3.dex */
    public final class LoadingViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ NotificationsAdapter f10467a;
        @NotNull
        private TextView loadingMsg;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public LoadingViewHolder(@NotNull NotificationsAdapter notificationsAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.f10467a = notificationsAdapter;
            View findViewById = itemView.findViewById(R.id.loading_msg);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.loading_msg)");
            TextView textView = (TextView) findViewById;
            this.loadingMsg = textView;
            Context context = notificationsAdapter.context;
            textView.setText((context == null || (r2 = context.getString(R.string.loading)) == null) ? "Loading..." : "Loading...");
        }
    }

    /* loaded from: classes3.dex */
    public final class NotificationsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ NotificationsAdapter f10468a;
        @NotNull
        private AppCompatImageView ivType;
        @NotNull
        private RelativeLayout llNotification;
        @NotNull
        private AppCompatTextView message;
        @NotNull
        private AppCompatTextView time;
        @NotNull
        private AppCompatTextView title;
        @NotNull
        private View unreadDot;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NotificationsViewHolder(@NotNull NotificationsAdapter notificationsAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.f10468a = notificationsAdapter;
            View findViewById = itemView.findViewById(R.id.ivType);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.ivType)");
            this.ivType = (AppCompatImageView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.title);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.title)");
            this.title = (AppCompatTextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.time);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.time)");
            this.time = (AppCompatTextView) findViewById3;
            View findViewById4 = itemView.findViewById(R.id.message);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "itemView.findViewById(R.id.message)");
            this.message = (AppCompatTextView) findViewById4;
            View findViewById5 = itemView.findViewById(R.id.llNotification);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "itemView.findViewById(R.id.llNotification)");
            this.llNotification = (RelativeLayout) findViewById5;
            View findViewById6 = itemView.findViewById(R.id.unreadDot);
            Intrinsics.checkNotNullExpressionValue(findViewById6, "itemView.findViewById(R.id.unreadDot)");
            this.unreadDot = findViewById6;
            itemView.setOnClickListener(this);
        }

        @NotNull
        public final AppCompatImageView getIvType() {
            return this.ivType;
        }

        @NotNull
        public final RelativeLayout getLlNotification() {
            return this.llNotification;
        }

        @NotNull
        public final AppCompatTextView getMessage() {
            return this.message;
        }

        @NotNull
        public final AppCompatTextView getTime() {
            return this.time;
        }

        @NotNull
        public final AppCompatTextView getTitle() {
            return this.title;
        }

        @NotNull
        public final View getUnreadDot() {
            return this.unreadDot;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(@Nullable View view) {
            if (getAbsoluteAdapterPosition() <= -1 || !(!this.f10468a.filteredNotifications.isEmpty())) {
                return;
            }
            OnNotificationClickListener onNotificationClickListener = this.f10468a.getOnNotificationClickListener();
            int absoluteAdapterPosition = getAbsoluteAdapterPosition();
            Object obj = this.f10468a.filteredNotifications.get(getAbsoluteAdapterPosition());
            Intrinsics.checkNotNull(obj);
            onNotificationClickListener.onNotificationClick(absoluteAdapterPosition, (Notification) obj);
        }

        public final void setIvType(@NotNull AppCompatImageView appCompatImageView) {
            Intrinsics.checkNotNullParameter(appCompatImageView, "<set-?>");
            this.ivType = appCompatImageView;
        }

        public final void setLlNotification(@NotNull RelativeLayout relativeLayout) {
            Intrinsics.checkNotNullParameter(relativeLayout, "<set-?>");
            this.llNotification = relativeLayout;
        }

        public final void setMessage(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.message = appCompatTextView;
        }

        public final void setTime(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.time = appCompatTextView;
        }

        public final void setTitle(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.title = appCompatTextView;
        }

        public final void setUnreadDot(@NotNull View view) {
            Intrinsics.checkNotNullParameter(view, "<set-?>");
            this.unreadDot = view;
        }
    }

    public NotificationsAdapter(@NotNull ArrayList<Notification> notifications, @NotNull OnNotificationClickListener onNotificationClickListener) {
        Intrinsics.checkNotNullParameter(notifications, "notifications");
        Intrinsics.checkNotNullParameter(onNotificationClickListener, "onNotificationClickListener");
        this.notifications = notifications;
        this.onNotificationClickListener = onNotificationClickListener;
        this.filteredNotifications = new ArrayList<>();
        this.filteredNotifications = this.notifications;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: addLoadingView$lambda-2$lambda-1  reason: not valid java name */
    public static final void m118addLoadingView$lambda2$lambda1(NotificationsAdapter this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.filteredNotifications.add(null);
        this$0.notifyItemInserted(this$0.filteredNotifications.size() - 1);
    }

    public final void addLoadingView() {
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            new Handler(myLooper).post(new Runnable() { // from class: j.c
                @Override // java.lang.Runnable
                public final void run() {
                    NotificationsAdapter.m118addLoadingView$lambda2$lambda1(NotificationsAdapter.this);
                }
            });
        }
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void addNotifications(@NotNull ArrayList<Notification> notifications) {
        Intrinsics.checkNotNullParameter(notifications, "notifications");
        this.filteredNotifications.addAll(notifications);
        notifyDataSetChanged();
    }

    public final void deleteNotification(int i2) {
        if (i2 != -1) {
            this.filteredNotifications.remove(i2);
            notifyItemRemoved(i2);
        }
    }

    @Override // android.widget.Filterable
    @NotNull
    public Filter getFilter() {
        return new Filter() { // from class: com.psa.mym.mycitroenconnect.controller.adapters.NotificationsAdapter$getFilter$1
            /* JADX WARN: Removed duplicated region for block: B:32:0x0070  */
            @Override // android.widget.Filter
            @NotNull
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            protected Filter.FilterResults performFiltering(@Nullable CharSequence charSequence) {
                String str;
                ArrayList arrayList;
                Iterator it;
                ArrayList arrayList2;
                String valueOf = String.valueOf(charSequence);
                NotificationsAdapter notificationsAdapter = NotificationsAdapter.this;
                if ((valueOf.length() == 0) || Intrinsics.areEqual(valueOf, "All")) {
                    arrayList2 = NotificationsAdapter.this.notifications;
                } else {
                    ArrayList arrayList3 = new ArrayList();
                    int hashCode = valueOf.hashCode();
                    if (hashCode == -1506188017) {
                        if (valueOf.equals("General Alerts")) {
                            str = ExifInterface.GPS_MEASUREMENT_2D;
                            arrayList = NotificationsAdapter.this.notifications;
                            it = arrayList.iterator();
                            while (it.hasNext()) {
                            }
                            arrayList2 = arrayList3;
                        }
                        str = "4";
                        arrayList = NotificationsAdapter.this.notifications;
                        it = arrayList.iterator();
                        while (it.hasNext()) {
                        }
                        arrayList2 = arrayList3;
                    } else if (hashCode != 107099704) {
                        if (hashCode == 1480045700 && valueOf.equals("Maintenance Alerts")) {
                            str = ExifInterface.GPS_MEASUREMENT_3D;
                            arrayList = NotificationsAdapter.this.notifications;
                            it = arrayList.iterator();
                            while (it.hasNext()) {
                                Notification notification = (Notification) it.next();
                                if (Intrinsics.areEqual(notification != null ? notification.getPriority() : null, str)) {
                                    arrayList3.add(notification);
                                }
                            }
                            arrayList2 = arrayList3;
                        }
                        str = "4";
                        arrayList = NotificationsAdapter.this.notifications;
                        it = arrayList.iterator();
                        while (it.hasNext()) {
                        }
                        arrayList2 = arrayList3;
                    } else {
                        if (valueOf.equals("Critical Alerts")) {
                            str = "1";
                            arrayList = NotificationsAdapter.this.notifications;
                            it = arrayList.iterator();
                            while (it.hasNext()) {
                            }
                            arrayList2 = arrayList3;
                        }
                        str = "4";
                        arrayList = NotificationsAdapter.this.notifications;
                        it = arrayList.iterator();
                        while (it.hasNext()) {
                        }
                        arrayList2 = arrayList3;
                    }
                }
                notificationsAdapter.filteredNotifications = arrayList2;
                Filter.FilterResults filterResults = new Filter.FilterResults();
                ArrayList arrayList4 = NotificationsAdapter.this.filteredNotifications;
                if (arrayList4.size() > 1) {
                    CollectionsKt__MutableCollectionsJVMKt.sortWith(arrayList4, new Comparator() { // from class: com.psa.mym.mycitroenconnect.controller.adapters.NotificationsAdapter$getFilter$1$performFiltering$$inlined$sortByDescending$1
                        @Override // java.util.Comparator
                        public final int compare(T t2, T t3) {
                            int compareValues;
                            Notification notification2 = (Notification) t3;
                            Notification notification3 = (Notification) t2;
                            compareValues = ComparisonsKt__ComparisonsKt.compareValues(notification2 != null ? Long.valueOf(notification2.getSignalTimeStamp()) : null, notification3 != null ? Long.valueOf(notification3.getSignalTimeStamp()) : null);
                            return compareValues;
                        }
                    });
                }
                filterResults.values = NotificationsAdapter.this.filteredNotifications;
                return filterResults;
            }

            @Override // android.widget.Filter
            @SuppressLint({"NotifyDataSetChanged"})
            protected void publishResults(@Nullable CharSequence charSequence, @Nullable Filter.FilterResults filterResults) {
                NotificationsAdapter notificationsAdapter = NotificationsAdapter.this;
                Object obj = filterResults != null ? filterResults.values : null;
                Objects.requireNonNull(obj, "null cannot be cast to non-null type java.util.ArrayList<com.psa.mym.mycitroenconnect.model.notification.Notification?>");
                notificationsAdapter.filteredNotifications = (ArrayList) obj;
                NotificationsAdapter.this.notifyDataSetChanged();
            }
        };
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.filteredNotifications.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        return this.filteredNotifications.get(i2) == null ? 0 : 1;
    }

    @NotNull
    public final Notification getNotification(int i2) {
        Notification notification = this.filteredNotifications.get(i2);
        Intrinsics.checkNotNull(notification);
        return notification;
    }

    @NotNull
    public final OnNotificationClickListener getOnNotificationClickListener() {
        return this.onNotificationClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull RecyclerView.ViewHolder holder, int i2) {
        Notification notification;
        RelativeLayout llNotification;
        Context context;
        int i3;
        AppCompatImageView ivType;
        Context context2;
        int i4;
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (getItemViewType(i2) != 1 || (notification = this.filteredNotifications.get(i2)) == null) {
            return;
        }
        NotificationsViewHolder notificationsViewHolder = (NotificationsViewHolder) holder;
        String priority = notification.getPriority();
        switch (priority.hashCode()) {
            case 49:
                if (priority.equals("1")) {
                    ivType = notificationsViewHolder.getIvType();
                    context2 = this.context;
                    Intrinsics.checkNotNull(context2);
                    i4 = R.drawable.ic_critical_alert;
                    ivType.setImageDrawable(ContextCompat.getDrawable(context2, i4));
                    break;
                }
                break;
            case 50:
                if (priority.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                    ivType = notificationsViewHolder.getIvType();
                    context2 = this.context;
                    Intrinsics.checkNotNull(context2);
                    i4 = R.drawable.ic_general_alert;
                    ivType.setImageDrawable(ContextCompat.getDrawable(context2, i4));
                    break;
                }
                break;
            case 51:
                if (priority.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                    ivType = notificationsViewHolder.getIvType();
                    context2 = this.context;
                    Intrinsics.checkNotNull(context2);
                    i4 = R.drawable.ic_maintenance_alert;
                    ivType.setImageDrawable(ContextCompat.getDrawable(context2, i4));
                    break;
                }
                break;
            case 52:
                if (priority.equals("4")) {
                    ivType = notificationsViewHolder.getIvType();
                    context2 = this.context;
                    Intrinsics.checkNotNull(context2);
                    i4 = R.drawable.ic_vehicle_alert;
                    ivType.setImageDrawable(ContextCompat.getDrawable(context2, i4));
                    break;
                }
                break;
        }
        notificationsViewHolder.getTitle().setText(notification.getTitle());
        notificationsViewHolder.getMessage().setText(notification.getBody());
        notificationsViewHolder.getTime().setText(ExtensionsKt.getNotificationDate(new Date(notification.getSignalTimeStamp())));
        if (notification.getRead()) {
            ExtensionsKt.hide(notificationsViewHolder.getUnreadDot());
            llNotification = notificationsViewHolder.getLlNotification();
            context = this.context;
            Intrinsics.checkNotNull(context);
            i3 = R.color.white;
        } else {
            ExtensionsKt.show(notificationsViewHolder.getUnreadDot());
            llNotification = notificationsViewHolder.getLlNotification();
            context = this.context;
            Intrinsics.checkNotNull(context);
            i3 = R.color.unread_notification;
        }
        llNotification.setBackgroundColor(ContextCompat.getColor(context, i3));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (this.context == null) {
            this.context = parent.getContext();
        }
        if (i2 == 1) {
            View inflate = LayoutInflater.from(this.context).inflate(R.layout.item_view_notification, parent, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "from(context)\n          …ification, parent, false)");
            return new NotificationsViewHolder(this, inflate);
        }
        View inflate2 = LayoutInflater.from(this.context).inflate(R.layout.progress, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate2, "from(context)\n          ….progress, parent, false)");
        return new LoadingViewHolder(this, inflate2);
    }

    public final void readNotification(int i2) {
        if (i2 != -1) {
            Notification notification = this.filteredNotifications.get(i2);
            if (notification != null) {
                notification.setRead(true);
            }
            notifyItemChanged(i2);
        }
    }

    public final void removeLoadingView() {
        if (this.filteredNotifications.size() != 0) {
            ArrayList<Notification> arrayList = this.filteredNotifications;
            arrayList.remove(arrayList.size() - 1);
            notifyItemRemoved(this.filteredNotifications.size());
        }
    }

    public final void setOnNotificationClickListener(@NotNull OnNotificationClickListener onNotificationClickListener) {
        Intrinsics.checkNotNullParameter(onNotificationClickListener, "<set-?>");
        this.onNotificationClickListener = onNotificationClickListener;
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void updateNotificationList(@NotNull ArrayList<Notification> notifications) {
        Intrinsics.checkNotNullParameter(notifications, "notifications");
        this.filteredNotifications = notifications;
        notifyDataSetChanged();
    }
}
