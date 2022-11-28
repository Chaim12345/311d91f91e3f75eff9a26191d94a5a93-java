package kotlin.text;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class Regex$findAll$1 extends Lambda implements Function0<MatchResult> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Regex f11240a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ CharSequence f11241b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ int f11242c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Regex$findAll$1(Regex regex, CharSequence charSequence, int i2) {
        super(0);
        this.f11240a = regex;
        this.f11241b = charSequence;
        this.f11242c = i2;
    }

    @Override // kotlin.jvm.functions.Function0
    @Nullable
    public final MatchResult invoke() {
        return this.f11240a.find(this.f11241b, this.f11242c);
    }
}
