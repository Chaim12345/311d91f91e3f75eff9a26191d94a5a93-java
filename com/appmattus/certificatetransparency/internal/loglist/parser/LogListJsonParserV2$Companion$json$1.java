package com.appmattus.certificatetransparency.internal.loglist.parser;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.json.JsonBuilder;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
final class LogListJsonParserV2$Companion$json$1 extends Lambda implements Function1<JsonBuilder, Unit> {
    public static final LogListJsonParserV2$Companion$json$1 INSTANCE = new LogListJsonParserV2$Companion$json$1();

    LogListJsonParserV2$Companion$json$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(JsonBuilder jsonBuilder) {
        invoke2(jsonBuilder);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull JsonBuilder Json) {
        Intrinsics.checkNotNullParameter(Json, "$this$Json");
        Json.setIgnoreUnknownKeys(true);
    }
}
