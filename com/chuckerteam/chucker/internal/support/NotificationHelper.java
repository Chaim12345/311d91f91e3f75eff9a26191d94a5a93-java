package com.chuckerteam.chucker.internal.support;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.LongSparseArray;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.chuckerteam.chucker.R;
import com.chuckerteam.chucker.internal.data.entity.HttpTransaction;
import com.chuckerteam.chucker.internal.data.entity.RecordedThrowable;
import com.chuckerteam.chucker.internal.support.ClearDatabaseService;
import com.chuckerteam.chucker.internal.ui.BaseChuckerActivity;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt___RangesKt;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000 $2\u00020\u0001:\u0001$B\u000f\u0012\u0006\u0010\u001e\u001a\u00020\u001d¢\u0006\u0004\b\"\u0010#J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bJ\u0006\u0010\r\u001a\u00020\u0004J\u0006\u0010\u000e\u001a\u00020\u0004J\u0006\u0010\u000f\u001a\u00020\u0004R\u0016\u0010\u0011\u001a\u00020\u00108\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R%\u0010\u0019\u001a\n \u0014*\u0004\u0018\u00010\u00130\u00138B@\u0002X\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R%\u0010\u001c\u001a\n \u0014*\u0004\u0018\u00010\u00130\u00138B@\u0002X\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u0016\u001a\u0004\b\u001b\u0010\u0018R\u0019\u0010\u001e\u001a\u00020\u001d8\u0006@\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!¨\u0006%"}, d2 = {"Lcom/chuckerteam/chucker/internal/support/NotificationHelper;", "", "Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;", "transaction", "", "addToBuffer", "Lcom/chuckerteam/chucker/internal/support/ClearDatabaseService$ClearAction;", "clearAction", "Landroidx/core/app/NotificationCompat$Action;", "createClearAction", "show", "Lcom/chuckerteam/chucker/internal/data/entity/RecordedThrowable;", "throwable", "dismissTransactionsNotification", "dismissErrorsNotification", "dismissNotifications", "Landroid/app/NotificationManager;", "notificationManager", "Landroid/app/NotificationManager;", "Landroid/app/PendingIntent;", "kotlin.jvm.PlatformType", "transactionsScreenIntent$delegate", "Lkotlin/Lazy;", "getTransactionsScreenIntent", "()Landroid/app/PendingIntent;", "transactionsScreenIntent", "errorsScreenIntent$delegate", "getErrorsScreenIntent", "errorsScreenIntent", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "Companion", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class NotificationHelper {
    private static final int BUFFER_SIZE = 10;
    private static final String ERRORS_CHANNEL_ID = "chucker_errors";
    private static final int ERROR_NOTIFICATION_ID = 3546;
    private static final int INTENT_REQUEST_CODE = 11;
    private static final String TRANSACTIONS_CHANNEL_ID = "chucker_transactions";
    private static final int TRANSACTION_NOTIFICATION_ID = 1138;
    @NotNull
    private final Context context;
    private final Lazy errorsScreenIntent$delegate;
    private final NotificationManager notificationManager;
    private final Lazy transactionsScreenIntent$delegate;
    public static final Companion Companion = new Companion(null);
    private static final LongSparseArray<HttpTransaction> transactionBuffer = new LongSparseArray<>();
    private static final HashSet<Long> transactionIdsSet = new HashSet<>();

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0018\u0010\u000bJ\u0006\u0010\u0003\u001a\u00020\u0002R\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u001c\u0010\b\u001a\u00020\u00078\u0002@\u0003X\u0083T¢\u0006\f\n\u0004\b\b\u0010\t\u0012\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\u00020\u00048\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\f\u0010\u0006R\u0016\u0010\r\u001a\u00020\u00048\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\r\u0010\u0006R\u0016\u0010\u000e\u001a\u00020\u00078\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u000e\u0010\tR\u0016\u0010\u000f\u001a\u00020\u00048\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u000f\u0010\u0006R\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u00148\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017¨\u0006\u0019"}, d2 = {"Lcom/chuckerteam/chucker/internal/support/NotificationHelper$Companion;", "", "", "clearBuffer", "", "BUFFER_SIZE", "I", "", "ERRORS_CHANNEL_ID", "Ljava/lang/String;", "getERRORS_CHANNEL_ID$annotations", "()V", "ERROR_NOTIFICATION_ID", "INTENT_REQUEST_CODE", "TRANSACTIONS_CHANNEL_ID", "TRANSACTION_NOTIFICATION_ID", "Landroid/util/LongSparseArray;", "Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;", "transactionBuffer", "Landroid/util/LongSparseArray;", "Ljava/util/HashSet;", "", "transactionIdsSet", "Ljava/util/HashSet;", "<init>", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Deprecated(message = "This variable will be removed in 4.x release")
        private static /* synthetic */ void getERRORS_CHANNEL_ID$annotations() {
        }

        public final void clearBuffer() {
            synchronized (NotificationHelper.transactionBuffer) {
                NotificationHelper.transactionBuffer.clear();
                NotificationHelper.transactionIdsSet.clear();
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    public NotificationHelper(@NotNull Context context) {
        Lazy lazy;
        Lazy lazy2;
        List<NotificationChannel> listOf;
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        Object systemService = context.getSystemService("notification");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        NotificationManager notificationManager = (NotificationManager) systemService;
        this.notificationManager = notificationManager;
        lazy = LazyKt__LazyJVMKt.lazy(new NotificationHelper$transactionsScreenIntent$2(this));
        this.transactionsScreenIntent$delegate = lazy;
        lazy2 = LazyKt__LazyJVMKt.lazy(new NotificationHelper$errorsScreenIntent$2(this));
        this.errorsScreenIntent$delegate = lazy2;
        if (Build.VERSION.SDK_INT >= 26) {
            listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new NotificationChannel[]{new NotificationChannel(TRANSACTIONS_CHANNEL_ID, context.getString(R.string.chucker_network_notification_category), 2), new NotificationChannel(ERRORS_CHANNEL_ID, context.getString(R.string.chucker_throwable_notification_category), 2)});
            notificationManager.createNotificationChannels(listOf);
        }
    }

    private final void addToBuffer(HttpTransaction httpTransaction) {
        if (httpTransaction.getId() == 0) {
            return;
        }
        LongSparseArray<HttpTransaction> longSparseArray = transactionBuffer;
        synchronized (longSparseArray) {
            transactionIdsSet.add(Long.valueOf(httpTransaction.getId()));
            longSparseArray.put(httpTransaction.getId(), httpTransaction);
            if (longSparseArray.size() > 10) {
                longSparseArray.removeAt(0);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final NotificationCompat.Action createClearAction(ClearDatabaseService.ClearAction clearAction) {
        String string = this.context.getString(R.string.chucker_clear);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.chucker_clear)");
        Intent intent = new Intent(this.context, ClearDatabaseService.class);
        intent.putExtra(ClearDatabaseService.EXTRA_ITEM_TO_CLEAR, clearAction);
        return new NotificationCompat.Action(R.drawable.chucker_ic_delete_white, string, PendingIntent.getService(this.context, 11, intent, 1073741824));
    }

    private final PendingIntent getErrorsScreenIntent() {
        return (PendingIntent) this.errorsScreenIntent$delegate.getValue();
    }

    private final PendingIntent getTransactionsScreenIntent() {
        return (PendingIntent) this.transactionsScreenIntent$delegate.getValue();
    }

    public final void dismissErrorsNotification() {
        this.notificationManager.cancel(ERROR_NOTIFICATION_ID);
    }

    public final void dismissNotifications() {
        this.notificationManager.cancel(TRANSACTION_NOTIFICATION_ID);
        this.notificationManager.cancel(ERROR_NOTIFICATION_ID);
    }

    public final void dismissTransactionsNotification() {
        this.notificationManager.cancel(TRANSACTION_NOTIFICATION_ID);
    }

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    public final void show(@NotNull HttpTransaction transaction) {
        IntProgression downTo;
        Intrinsics.checkNotNullParameter(transaction, "transaction");
        addToBuffer(transaction);
        if (BaseChuckerActivity.Companion.isInForeground()) {
            return;
        }
        NotificationCompat.Builder addAction = new NotificationCompat.Builder(this.context, TRANSACTIONS_CHANNEL_ID).setContentIntent(getTransactionsScreenIntent()).setLocalOnly(true).setSmallIcon(R.drawable.chucker_ic_transaction_notification).setColor(ContextCompat.getColor(this.context, R.color.chucker_color_primary)).setContentTitle(this.context.getString(R.string.chucker_http_notification_title)).setAutoCancel(true).addAction(createClearAction(ClearDatabaseService.ClearAction.Transaction.INSTANCE));
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        LongSparseArray<HttpTransaction> longSparseArray = transactionBuffer;
        synchronized (longSparseArray) {
            int i2 = 0;
            downTo = RangesKt___RangesKt.downTo(longSparseArray.size() - 1, 0);
            Iterator<Integer> it = downTo.iterator();
            while (it.hasNext()) {
                HttpTransaction valueAt = transactionBuffer.valueAt(((IntIterator) it).nextInt());
                if (valueAt != null && i2 < 10) {
                    if (i2 == 0) {
                        addAction.setContentText(valueAt.getNotificationText());
                    }
                    inboxStyle.addLine(valueAt.getNotificationText());
                }
                i2++;
            }
            addAction.setStyle(inboxStyle);
            if (Build.VERSION.SDK_INT >= 24) {
                addAction.setSubText(String.valueOf(transactionIdsSet.size()));
            } else {
                addAction.setNumber(transactionIdsSet.size());
            }
        }
        this.notificationManager.notify(TRANSACTION_NOTIFICATION_ID, addAction.build());
    }

    public final void show(@NotNull RecordedThrowable throwable) {
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        if (BaseChuckerActivity.Companion.isInForeground()) {
            return;
        }
        this.notificationManager.notify(ERROR_NOTIFICATION_ID, new NotificationCompat.Builder(this.context, ERRORS_CHANNEL_ID).setContentIntent(getErrorsScreenIntent()).setLocalOnly(true).setSmallIcon(R.drawable.chucker_ic_error_notifications).setColor(ContextCompat.getColor(this.context, R.color.chucker_status_error)).setContentTitle(throwable.getClazz()).setAutoCancel(true).setContentText(throwable.getMessage()).addAction(createClearAction(ClearDatabaseService.ClearAction.Error.INSTANCE)).build());
    }
}
