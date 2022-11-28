package com.chuckerteam.chucker.internal.data.room;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.chuckerteam.chucker.internal.data.entity.HttpTransaction;
import com.chuckerteam.chucker.internal.data.entity.RecordedThrowable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Database(entities = {RecordedThrowable.class, HttpTransaction.class}, exportSchema = false, version = 4)
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b!\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u0003\u001a\u00020\u0002H&J\b\u0010\u0005\u001a\u00020\u0004H&¨\u0006\t"}, d2 = {"Lcom/chuckerteam/chucker/internal/data/room/ChuckerDatabase;", "Landroidx/room/RoomDatabase;", "Lcom/chuckerteam/chucker/internal/data/room/RecordedThrowableDao;", "throwableDao", "Lcom/chuckerteam/chucker/internal/data/room/HttpTransactionDao;", "transactionDao", "<init>", "()V", "Companion", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public abstract class ChuckerDatabase extends RoomDatabase {
    public static final Companion Companion = new Companion(null);
    private static final String DB_NAME = "chucker.db";
    private static final String OLD_DB_NAME = "chuck.db";

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002R\u0016\u0010\u0007\u001a\u00020\u00068\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\u00020\u00068\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\t\u0010\b¨\u0006\f"}, d2 = {"Lcom/chuckerteam/chucker/internal/data/room/ChuckerDatabase$Companion;", "", "Landroid/content/Context;", "applicationContext", "Lcom/chuckerteam/chucker/internal/data/room/ChuckerDatabase;", "create", "", "DB_NAME", "Ljava/lang/String;", "OLD_DB_NAME", "<init>", "()V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final ChuckerDatabase create(@NotNull Context applicationContext) {
            Intrinsics.checkNotNullParameter(applicationContext, "applicationContext");
            applicationContext.getDatabasePath(ChuckerDatabase.OLD_DB_NAME).delete();
            RoomDatabase build = Room.databaseBuilder(applicationContext, ChuckerDatabase.class, ChuckerDatabase.DB_NAME).fallbackToDestructiveMigration().build();
            Intrinsics.checkNotNullExpressionValue(build, "Room.databaseBuilder(app…\n                .build()");
            return (ChuckerDatabase) build;
        }
    }

    @NotNull
    public abstract RecordedThrowableDao throwableDao();

    @NotNull
    public abstract HttpTransactionDao transactionDao();
}
