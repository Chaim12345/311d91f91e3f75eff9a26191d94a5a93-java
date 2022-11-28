package com.chuckerteam.chucker.internal.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.chuckerteam.chucker.internal.support.FormatUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(tableName = "throwables")
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\u0003\n\u0002\b\u0003\b\u0081\b\u0018\u00002\u00020\u0001BE\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b*\u0010+B\u0019\b\u0017\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010-\u001a\u00020,¢\u0006\u0004\b*\u0010.J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0003\u0010\u0004J\u000b\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0007\u0010\u0004J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0005HÆ\u0003JX\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0014HÖ\u0001J\u0013\u0010\u0018\u001a\u00020\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003R$\u0010\u000b\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\u0019\u001a\u0004\b\u001a\u0010\u0004\"\u0004\b\u001b\u0010\u001cR$\u0010\f\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\f\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R$\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\r\u0010\u0019\u001a\u0004\b\"\u0010\u0004\"\u0004\b#\u0010\u001cR$\u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u001d\u001a\u0004\b$\u0010\u001f\"\u0004\b%\u0010!R$\u0010\u000f\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\u001d\u001a\u0004\b&\u0010\u001f\"\u0004\b'\u0010!R$\u0010\u0010\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010\u001d\u001a\u0004\b(\u0010\u001f\"\u0004\b)\u0010!¨\u0006/"}, d2 = {"Lcom/chuckerteam/chucker/internal/data/entity/RecordedThrowable;", "", "", "component1", "()Ljava/lang/Long;", "", "component2", "component3", "component4", "component5", "component6", "id", "tag", "date", "clazz", AppConstants.ARG_MESSAGE, FirebaseAnalytics.Param.CONTENT, "copy", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/chuckerteam/chucker/internal/data/entity/RecordedThrowable;", "toString", "", "hashCode", "other", "", "equals", "Ljava/lang/Long;", "getId", "setId", "(Ljava/lang/Long;)V", "Ljava/lang/String;", "getTag", "()Ljava/lang/String;", "setTag", "(Ljava/lang/String;)V", "getDate", "setDate", "getClazz", "setClazz", "getMessage", "setMessage", "getContent", "setContent", "<init>", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "", "throwable", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class RecordedThrowable {
    @ColumnInfo(name = "clazz")
    @Nullable
    private String clazz;
    @ColumnInfo(name = FirebaseAnalytics.Param.CONTENT)
    @Nullable
    private String content;
    @ColumnInfo(name = "date")
    @Nullable
    private Long date;
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @Nullable
    private Long id;
    @ColumnInfo(name = AppConstants.ARG_MESSAGE)
    @Nullable
    private String message;
    @ColumnInfo(name = "tag")
    @Nullable
    private String tag;

    public RecordedThrowable(@Nullable Long l2, @Nullable String str, @Nullable Long l3, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        this.id = l2;
        this.tag = str;
        this.date = l3;
        this.clazz = str2;
        this.message = str3;
        this.content = str4;
    }

    public /* synthetic */ RecordedThrowable(Long l2, String str, Long l3, String str2, String str3, String str4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0L : l2, str, l3, str2, str3, str4);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Ignore
    public RecordedThrowable(@NotNull String tag, @NotNull Throwable throwable) {
        this(null, null, null, null, null, null);
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        this.tag = tag;
        this.date = Long.valueOf(System.currentTimeMillis());
        this.clazz = throwable.getClass().getName();
        this.message = throwable.getMessage();
        this.content = FormatUtils.INSTANCE.formatThrowable(throwable);
    }

    public static /* synthetic */ RecordedThrowable copy$default(RecordedThrowable recordedThrowable, Long l2, String str, Long l3, String str2, String str3, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            l2 = recordedThrowable.id;
        }
        if ((i2 & 2) != 0) {
            str = recordedThrowable.tag;
        }
        String str5 = str;
        if ((i2 & 4) != 0) {
            l3 = recordedThrowable.date;
        }
        Long l4 = l3;
        if ((i2 & 8) != 0) {
            str2 = recordedThrowable.clazz;
        }
        String str6 = str2;
        if ((i2 & 16) != 0) {
            str3 = recordedThrowable.message;
        }
        String str7 = str3;
        if ((i2 & 32) != 0) {
            str4 = recordedThrowable.content;
        }
        return recordedThrowable.copy(l2, str5, l4, str6, str7, str4);
    }

    @Nullable
    public final Long component1() {
        return this.id;
    }

    @Nullable
    public final String component2() {
        return this.tag;
    }

    @Nullable
    public final Long component3() {
        return this.date;
    }

    @Nullable
    public final String component4() {
        return this.clazz;
    }

    @Nullable
    public final String component5() {
        return this.message;
    }

    @Nullable
    public final String component6() {
        return this.content;
    }

    @NotNull
    public final RecordedThrowable copy(@Nullable Long l2, @Nullable String str, @Nullable Long l3, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        return new RecordedThrowable(l2, str, l3, str2, str3, str4);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof RecordedThrowable) {
                RecordedThrowable recordedThrowable = (RecordedThrowable) obj;
                return Intrinsics.areEqual(this.id, recordedThrowable.id) && Intrinsics.areEqual(this.tag, recordedThrowable.tag) && Intrinsics.areEqual(this.date, recordedThrowable.date) && Intrinsics.areEqual(this.clazz, recordedThrowable.clazz) && Intrinsics.areEqual(this.message, recordedThrowable.message) && Intrinsics.areEqual(this.content, recordedThrowable.content);
            }
            return false;
        }
        return true;
    }

    @Nullable
    public final String getClazz() {
        return this.clazz;
    }

    @Nullable
    public final String getContent() {
        return this.content;
    }

    @Nullable
    public final Long getDate() {
        return this.date;
    }

    @Nullable
    public final Long getId() {
        return this.id;
    }

    @Nullable
    public final String getMessage() {
        return this.message;
    }

    @Nullable
    public final String getTag() {
        return this.tag;
    }

    public int hashCode() {
        Long l2 = this.id;
        int hashCode = (l2 != null ? l2.hashCode() : 0) * 31;
        String str = this.tag;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        Long l3 = this.date;
        int hashCode3 = (hashCode2 + (l3 != null ? l3.hashCode() : 0)) * 31;
        String str2 = this.clazz;
        int hashCode4 = (hashCode3 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.message;
        int hashCode5 = (hashCode4 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.content;
        return hashCode5 + (str4 != null ? str4.hashCode() : 0);
    }

    public final void setClazz(@Nullable String str) {
        this.clazz = str;
    }

    public final void setContent(@Nullable String str) {
        this.content = str;
    }

    public final void setDate(@Nullable Long l2) {
        this.date = l2;
    }

    public final void setId(@Nullable Long l2) {
        this.id = l2;
    }

    public final void setMessage(@Nullable String str) {
        this.message = str;
    }

    public final void setTag(@Nullable String str) {
        this.tag = str;
    }

    @NotNull
    public String toString() {
        return "RecordedThrowable(id=" + this.id + ", tag=" + this.tag + ", date=" + this.date + ", clazz=" + this.clazz + ", message=" + this.message + ", content=" + this.content + ")";
    }
}
