package com.psa.mym.mycitroenconnect.model.secondary_user;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class Message {
    @SerializedName("Errors")
    @NotNull
    private ArrayList<Errors> errors;

    public Message() {
        this(null, 1, null);
    }

    public Message(@NotNull ArrayList<Errors> errors) {
        Intrinsics.checkNotNullParameter(errors, "errors");
        this.errors = errors;
    }

    public /* synthetic */ Message(ArrayList arrayList, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? new ArrayList() : arrayList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Message copy$default(Message message, ArrayList arrayList, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            arrayList = message.errors;
        }
        return message.copy(arrayList);
    }

    @NotNull
    public final ArrayList<Errors> component1() {
        return this.errors;
    }

    @NotNull
    public final Message copy(@NotNull ArrayList<Errors> errors) {
        Intrinsics.checkNotNullParameter(errors, "errors");
        return new Message(errors);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof Message) && Intrinsics.areEqual(this.errors, ((Message) obj).errors);
    }

    @NotNull
    public final ArrayList<Errors> getErrors() {
        return this.errors;
    }

    public int hashCode() {
        return this.errors.hashCode();
    }

    public final void setErrors(@NotNull ArrayList<Errors> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.errors = arrayList;
    }

    @NotNull
    public String toString() {
        return "Message(errors=" + this.errors + ')';
    }
}
