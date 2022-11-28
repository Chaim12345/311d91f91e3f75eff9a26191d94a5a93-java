package com.chuckerteam.chucker.api;

import android.content.Context;
import com.chuckerteam.chucker.api.RetentionManager;
import com.chuckerteam.chucker.internal.data.entity.HttpTransaction;
import com.chuckerteam.chucker.internal.data.entity.RecordedThrowable;
import com.chuckerteam.chucker.internal.data.repository.RepositoryProvider;
import com.chuckerteam.chucker.internal.support.NotificationHelper;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u001d\u001a\u00020\u001c\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u001e¢\u0006\u0004\b \u0010!J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0017\u0010\f\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0000¢\u0006\u0004\b\r\u0010\u000bR\u0016\u0010\u0010\u001a\u00020\u000f8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0013\u001a\u00020\u00128\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\"\u0010\u0016\u001a\u00020\u00158\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006\""}, d2 = {"Lcom/chuckerteam/chucker/api/ChuckerCollector;", "", "", "tag", "", "throwable", "", "onError", "Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;", "transaction", "onRequestSent$com_github_ChuckerTeam_Chucker_library", "(Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;)V", "onRequestSent", "onResponseReceived$com_github_ChuckerTeam_Chucker_library", "onResponseReceived", "Lcom/chuckerteam/chucker/api/RetentionManager;", "retentionManager", "Lcom/chuckerteam/chucker/api/RetentionManager;", "Lcom/chuckerteam/chucker/internal/support/NotificationHelper;", "notificationHelper", "Lcom/chuckerteam/chucker/internal/support/NotificationHelper;", "", "showNotification", "Z", "getShowNotification", "()Z", "setShowNotification", "(Z)V", "Landroid/content/Context;", "context", "Lcom/chuckerteam/chucker/api/RetentionManager$Period;", "retentionPeriod", "<init>", "(Landroid/content/Context;ZLcom/chuckerteam/chucker/api/RetentionManager$Period;)V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class ChuckerCollector {
    private final NotificationHelper notificationHelper;
    private final RetentionManager retentionManager;
    private boolean showNotification;

    @JvmOverloads
    public ChuckerCollector(@NotNull Context context) {
        this(context, false, null, 6, null);
    }

    @JvmOverloads
    public ChuckerCollector(@NotNull Context context, boolean z) {
        this(context, z, null, 4, null);
    }

    @JvmOverloads
    public ChuckerCollector(@NotNull Context context, boolean z, @NotNull RetentionManager.Period retentionPeriod) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(retentionPeriod, "retentionPeriod");
        this.showNotification = z;
        this.retentionManager = new RetentionManager(context, retentionPeriod);
        this.notificationHelper = new NotificationHelper(context);
        RepositoryProvider.INSTANCE.initialize(context);
    }

    public /* synthetic */ ChuckerCollector(Context context, boolean z, RetentionManager.Period period, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? true : z, (i2 & 4) != 0 ? RetentionManager.Period.ONE_WEEK : period);
    }

    public final boolean getShowNotification() {
        return this.showNotification;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "This fun will be removed in 4.x release as part of Throwable functionality removal.", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public final void onError(@NotNull String tag, @NotNull Throwable throwable) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        RecordedThrowable recordedThrowable = new RecordedThrowable(tag, throwable);
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new ChuckerCollector$onError$1(recordedThrowable, null), 3, null);
        if (this.showNotification) {
            this.notificationHelper.show(recordedThrowable);
        }
        this.retentionManager.doMaintenance$com_github_ChuckerTeam_Chucker_library();
    }

    public final void onRequestSent$com_github_ChuckerTeam_Chucker_library(@NotNull HttpTransaction transaction) {
        Intrinsics.checkNotNullParameter(transaction, "transaction");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new ChuckerCollector$onRequestSent$1(transaction, null), 3, null);
        if (this.showNotification) {
            this.notificationHelper.show(transaction);
        }
        this.retentionManager.doMaintenance$com_github_ChuckerTeam_Chucker_library();
    }

    public final void onResponseReceived$com_github_ChuckerTeam_Chucker_library(@NotNull HttpTransaction transaction) {
        Intrinsics.checkNotNullParameter(transaction, "transaction");
        int updateTransaction = RepositoryProvider.INSTANCE.transaction().updateTransaction(transaction);
        if (!this.showNotification || updateTransaction <= 0) {
            return;
        }
        this.notificationHelper.show(transaction);
    }

    public final void setShowNotification(boolean z) {
        this.showNotification = z;
    }
}
