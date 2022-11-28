package com.appmattus.certificatetransparency.internal.loglist.model.v2;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import org.jetbrains.annotations.NotNull;
@Serializable
/* loaded from: classes.dex */
public enum LogType {
    PROD,
    TEST;
    
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final KSerializer<LogType> serializer() {
            return LogType$$serializer.INSTANCE;
        }
    }
}
