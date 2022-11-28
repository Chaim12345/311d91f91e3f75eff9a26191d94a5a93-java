package com.chuckerteam.chucker.internal.support;

import com.chuckerteam.chucker.internal.data.entity.HttpHeader;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/chuckerteam/chucker/internal/data/entity/HttpHeader;", "header", "", "invoke", "(Lcom/chuckerteam/chucker/internal/data/entity/HttpHeader;)Ljava/lang/CharSequence;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
/* loaded from: classes.dex */
final class FormatUtils$formatHeaders$1 extends Lambda implements Function1<HttpHeader, CharSequence> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ boolean f4895a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FormatUtils$formatHeaders$1(boolean z) {
        super(1);
        this.f4895a = z;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final CharSequence invoke(@NotNull HttpHeader header) {
        StringBuilder sb;
        Intrinsics.checkNotNullParameter(header, "header");
        if (this.f4895a) {
            sb = new StringBuilder();
            sb.append("<b> ");
            sb.append(header.getName());
            sb.append(": </b>");
            sb.append(header.getValue());
            sb.append(" <br />");
        } else {
            sb = new StringBuilder();
            sb.append(header.getName());
            sb.append(": ");
            sb.append(header.getValue());
            sb.append('\n');
        }
        return sb.toString();
    }
}
