package com.chuckerteam.chucker.internal.data.model;

import com.psa.mym.mycitroenconnect.common.AppConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\b\u0080\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\t\u0010\u0003\u001a\u00020\u0002HÆ\u0003J\t\u0010\u0004\u001a\u00020\u0002HÆ\u0003J\u000b\u0010\u0005\u001a\u0004\u0018\u00010\u0002HÆ\u0003J\u000b\u0010\u0006\u001a\u0004\u0018\u00010\u0002HÆ\u0003J5\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\t\u0010\f\u001a\u00020\u0002HÖ\u0001J\t\u0010\u000e\u001a\u00020\rHÖ\u0001J\u0013\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\u0007\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0019\u0010\b\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\b\u0010\u0012\u001a\u0004\b\u0015\u0010\u0014R\u001b\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\t\u0010\u0012\u001a\u0004\b\u0016\u0010\u0014R\u001b\u0010\n\u001a\u0004\u0018\u00010\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\n\u0010\u0012\u001a\u0004\b\u0017\u0010\u0014¨\u0006\u001a"}, d2 = {"Lcom/chuckerteam/chucker/internal/data/model/DialogData;", "", "", "component1", "component2", "component3", "component4", "title", AppConstants.ARG_MESSAGE, "postiveButtonText", "negativeButtonText", "copy", "toString", "", "hashCode", "other", "", "equals", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "getMessage", "getPostiveButtonText", "getNegativeButtonText", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class DialogData {
    @NotNull
    private final String message;
    @Nullable
    private final String negativeButtonText;
    @Nullable
    private final String postiveButtonText;
    @NotNull
    private final String title;

    public DialogData(@NotNull String title, @NotNull String message, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        this.title = title;
        this.message = message;
        this.postiveButtonText = str;
        this.negativeButtonText = str2;
    }

    public static /* synthetic */ DialogData copy$default(DialogData dialogData, String str, String str2, String str3, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = dialogData.title;
        }
        if ((i2 & 2) != 0) {
            str2 = dialogData.message;
        }
        if ((i2 & 4) != 0) {
            str3 = dialogData.postiveButtonText;
        }
        if ((i2 & 8) != 0) {
            str4 = dialogData.negativeButtonText;
        }
        return dialogData.copy(str, str2, str3, str4);
    }

    @NotNull
    public final String component1() {
        return this.title;
    }

    @NotNull
    public final String component2() {
        return this.message;
    }

    @Nullable
    public final String component3() {
        return this.postiveButtonText;
    }

    @Nullable
    public final String component4() {
        return this.negativeButtonText;
    }

    @NotNull
    public final DialogData copy(@NotNull String title, @NotNull String message, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        return new DialogData(title, message, str, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof DialogData) {
                DialogData dialogData = (DialogData) obj;
                return Intrinsics.areEqual(this.title, dialogData.title) && Intrinsics.areEqual(this.message, dialogData.message) && Intrinsics.areEqual(this.postiveButtonText, dialogData.postiveButtonText) && Intrinsics.areEqual(this.negativeButtonText, dialogData.negativeButtonText);
            }
            return false;
        }
        return true;
    }

    @NotNull
    public final String getMessage() {
        return this.message;
    }

    @Nullable
    public final String getNegativeButtonText() {
        return this.negativeButtonText;
    }

    @Nullable
    public final String getPostiveButtonText() {
        return this.postiveButtonText;
    }

    @NotNull
    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        String str = this.title;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.message;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.postiveButtonText;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.negativeButtonText;
        return hashCode3 + (str4 != null ? str4.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "DialogData(title=" + this.title + ", message=" + this.message + ", postiveButtonText=" + this.postiveButtonText + ", negativeButtonText=" + this.negativeButtonText + ")";
    }
}
