package com.google.android.libraries.places.widget.internal.ui;

import androidx.activity.OnBackPressedCallback;
import com.google.android.libraries.places.internal.zzgf;
/* loaded from: classes2.dex */
final class zzf extends OnBackPressedCallback {
    final /* synthetic */ AutocompleteImplFragment zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzf(AutocompleteImplFragment autocompleteImplFragment, boolean z) {
        super(true);
        this.zza = autocompleteImplFragment;
    }

    @Override // androidx.activity.OnBackPressedCallback
    public final void handleOnBackPressed() {
        zzgf zzgfVar;
        zzgfVar = this.zza.zze;
        zzgfVar.zzj();
    }
}
