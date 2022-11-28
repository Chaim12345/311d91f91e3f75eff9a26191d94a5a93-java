package com.appmattus.certificatetransparency.loglist;

import com.appmattus.certificatetransparency.internal.loglist.parser.RawLogListToLogListResultTransformer;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
final class LogListDataSourceFactory$createDataSource$2 extends Lambda implements Function1<RawLogListResult, LogListResult> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ RawLogListToLogListResultTransformer f4638a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LogListDataSourceFactory$createDataSource$2(RawLogListToLogListResultTransformer rawLogListToLogListResultTransformer) {
        super(1);
        this.f4638a = rawLogListToLogListResultTransformer;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final LogListResult invoke(@NotNull RawLogListResult it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return this.f4638a.transform(it);
    }
}
