package com.chuckerteam.chucker.internal.support;

import android.content.Context;
import android.content.DialogInterface;
import com.chuckerteam.chucker.internal.data.model.DialogData;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u001a4\u0010\u0007\u001a\u00020\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\u0000¨\u0006\b"}, d2 = {"Landroid/content/Context;", "Lcom/chuckerteam/chucker/internal/data/model/DialogData;", "dialogData", "Lkotlin/Function0;", "", "onPositiveClick", "onNegativeClick", "showDialog", "com.github.ChuckerTeam.Chucker.library"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class ContextExtKt {
    public static final void showDialog(@NotNull Context showDialog, @NotNull DialogData dialogData, @Nullable final Function0<Unit> function0, @Nullable final Function0<Unit> function02) {
        Intrinsics.checkNotNullParameter(showDialog, "$this$showDialog");
        Intrinsics.checkNotNullParameter(dialogData, "dialogData");
        new MaterialAlertDialogBuilder(showDialog).setTitle((CharSequence) dialogData.getTitle()).setMessage((CharSequence) dialogData.getMessage()).setPositiveButton((CharSequence) dialogData.getPostiveButtonText(), new DialogInterface.OnClickListener() { // from class: com.chuckerteam.chucker.internal.support.ContextExtKt$showDialog$1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                Function0 function03 = Function0.this;
                if (function03 != null) {
                    Unit unit = (Unit) function03.invoke();
                }
            }
        }).setNegativeButton((CharSequence) dialogData.getNegativeButtonText(), new DialogInterface.OnClickListener() { // from class: com.chuckerteam.chucker.internal.support.ContextExtKt$showDialog$2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                Function0 function03 = Function0.this;
                if (function03 != null) {
                    Unit unit = (Unit) function03.invoke();
                }
            }
        }).show();
    }
}
