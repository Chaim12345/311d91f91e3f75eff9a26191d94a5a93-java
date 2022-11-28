package com.chuckerteam.chucker.internal.support;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0004\u001a\n \u0001*\u0004\u0018\u00010\u00000\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lcom/google/gson/Gson;", "kotlin.jvm.PlatformType", "invoke", "()Lcom/google/gson/Gson;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
/* loaded from: classes.dex */
final class JsonConverter$instance$2 extends Lambda implements Function0<Gson> {
    public static final JsonConverter$instance$2 INSTANCE = new JsonConverter$instance$2();

    JsonConverter$instance$2() {
        super(0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final Gson invoke() {
        return new GsonBuilder().disableHtmlEscaping().serializeNulls().setPrettyPrinting().create();
    }
}
