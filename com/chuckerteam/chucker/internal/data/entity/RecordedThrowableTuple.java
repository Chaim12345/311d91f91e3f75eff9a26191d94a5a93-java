package com.chuckerteam.chucker.internal.data.entity;

import androidx.room.ColumnInfo;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0013\b\u0080\b\u0018\u00002\u00020\u0001B;\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b&\u0010'J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0003\u0010\u0004J\u000b\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0007\u0010\u0004J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0005HÆ\u0003JL\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0012HÖ\u0001J\u0013\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003R$\u0010\n\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u0017\u001a\u0004\b\u0018\u0010\u0004\"\u0004\b\u0019\u0010\u001aR$\u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR$\u0010\f\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\f\u0010\u0017\u001a\u0004\b \u0010\u0004\"\u0004\b!\u0010\u001aR$\u0010\r\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\r\u0010\u001b\u001a\u0004\b\"\u0010\u001d\"\u0004\b#\u0010\u001fR$\u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u001b\u001a\u0004\b$\u0010\u001d\"\u0004\b%\u0010\u001f¨\u0006("}, d2 = {"Lcom/chuckerteam/chucker/internal/data/entity/RecordedThrowableTuple;", "", "", "component1", "()Ljava/lang/Long;", "", "component2", "component3", "component4", "component5", "id", "tag", "date", "clazz", AppConstants.ARG_MESSAGE, "copy", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;)Lcom/chuckerteam/chucker/internal/data/entity/RecordedThrowableTuple;", "toString", "", "hashCode", "other", "", "equals", "Ljava/lang/Long;", "getId", "setId", "(Ljava/lang/Long;)V", "Ljava/lang/String;", "getTag", "()Ljava/lang/String;", "setTag", "(Ljava/lang/String;)V", "getDate", "setDate", "getClazz", "setClazz", "getMessage", "setMessage", "<init>", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;)V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class RecordedThrowableTuple {
    @ColumnInfo(name = "clazz")
    @Nullable
    private String clazz;
    @ColumnInfo(name = "date")
    @Nullable
    private Long date;
    @ColumnInfo(name = "id")
    @Nullable
    private Long id;
    @ColumnInfo(name = AppConstants.ARG_MESSAGE)
    @Nullable
    private String message;
    @ColumnInfo(name = "tag")
    @Nullable
    private String tag;

    public RecordedThrowableTuple(@Nullable Long l2, @Nullable String str, @Nullable Long l3, @Nullable String str2, @Nullable String str3) {
        this.id = l2;
        this.tag = str;
        this.date = l3;
        this.clazz = str2;
        this.message = str3;
    }

    public /* synthetic */ RecordedThrowableTuple(Long l2, String str, Long l3, String str2, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0L : l2, str, l3, str2, str3);
    }

    public static /* synthetic */ RecordedThrowableTuple copy$default(RecordedThrowableTuple recordedThrowableTuple, Long l2, String str, Long l3, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            l2 = recordedThrowableTuple.id;
        }
        if ((i2 & 2) != 0) {
            str = recordedThrowableTuple.tag;
        }
        String str4 = str;
        if ((i2 & 4) != 0) {
            l3 = recordedThrowableTuple.date;
        }
        Long l4 = l3;
        if ((i2 & 8) != 0) {
            str2 = recordedThrowableTuple.clazz;
        }
        String str5 = str2;
        if ((i2 & 16) != 0) {
            str3 = recordedThrowableTuple.message;
        }
        return recordedThrowableTuple.copy(l2, str4, l4, str5, str3);
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

    @NotNull
    public final RecordedThrowableTuple copy(@Nullable Long l2, @Nullable String str, @Nullable Long l3, @Nullable String str2, @Nullable String str3) {
        return new RecordedThrowableTuple(l2, str, l3, str2, str3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof RecordedThrowableTuple) {
                RecordedThrowableTuple recordedThrowableTuple = (RecordedThrowableTuple) obj;
                return Intrinsics.areEqual(this.id, recordedThrowableTuple.id) && Intrinsics.areEqual(this.tag, recordedThrowableTuple.tag) && Intrinsics.areEqual(this.date, recordedThrowableTuple.date) && Intrinsics.areEqual(this.clazz, recordedThrowableTuple.clazz) && Intrinsics.areEqual(this.message, recordedThrowableTuple.message);
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
        return hashCode4 + (str3 != null ? str3.hashCode() : 0);
    }

    public final void setClazz(@Nullable String str) {
        this.clazz = str;
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
        return "RecordedThrowableTuple(id=" + this.id + ", tag=" + this.tag + ", date=" + this.date + ", clazz=" + this.clazz + ", message=" + this.message + ")";
    }
}
