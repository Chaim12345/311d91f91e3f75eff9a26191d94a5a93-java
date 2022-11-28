package com.chuckerteam.chucker.internal.support;

import java.net.URLDecoder;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "entry", "", "invoke", "(Ljava/lang/String;)Ljava/lang/CharSequence;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class FormatUtils$formatUrlEncodedForm$1 extends Lambda implements Function1<String, CharSequence> {
    public static final FormatUtils$formatUrlEncodedForm$1 INSTANCE = new FormatUtils$formatUrlEncodedForm$1();

    FormatUtils$formatUrlEncodedForm$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final CharSequence invoke(@NotNull String entry) {
        List split$default;
        Intrinsics.checkNotNullParameter(entry, "entry");
        split$default = StringsKt__StringsKt.split$default((CharSequence) entry, new String[]{"="}, false, 0, 6, (Object) null);
        String str = (String) split$default.get(0);
        String decode = split$default.size() > 1 ? URLDecoder.decode((String) split$default.get(1), "UTF-8") : "";
        return str + ": " + decode;
    }
}
