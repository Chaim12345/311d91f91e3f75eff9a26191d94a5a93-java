package com.psa.mym.mycitroenconnect.fcm;

import androidx.lifecycle.MutableLiveData;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class UpdatedEvents {
    @NotNull
    public static final UpdatedEvents INSTANCE = new UpdatedEvents();
    @NotNull
    private static final Lazy accessRevokeEvent$delegate;
    @NotNull
    private static final Lazy childInviteEvent$delegate;

    static {
        Lazy lazy;
        Lazy lazy2;
        lazy = LazyKt__LazyJVMKt.lazy(UpdatedEvents$childInviteEvent$2.INSTANCE);
        childInviteEvent$delegate = lazy;
        lazy2 = LazyKt__LazyJVMKt.lazy(UpdatedEvents$accessRevokeEvent$2.INSTANCE);
        accessRevokeEvent$delegate = lazy2;
    }

    private UpdatedEvents() {
    }

    @NotNull
    public final MutableLiveData<String> getAccessRevokeEvent() {
        return (MutableLiveData) accessRevokeEvent$delegate.getValue();
    }

    @NotNull
    public final MutableLiveData<String> getChildInviteEvent() {
        return (MutableLiveData) childInviteEvent$delegate.getValue();
    }
}
