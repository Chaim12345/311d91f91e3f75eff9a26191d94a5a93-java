package kotlinx.serialization.json.internal;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@ExperimentalUnsignedTypes
/* loaded from: classes3.dex */
public final class ComposerForUnsignedNumbers extends Composer {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComposerForUnsignedNumbers(@NotNull JsonStringBuilder sb) {
        super(sb);
        Intrinsics.checkNotNullParameter(sb, "sb");
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(byte b2) {
        super.print(UByte.m248toStringimpl(UByte.m205constructorimpl(b2)));
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(int i2) {
        super.print(UInt.m326toStringimpl(UInt.m281constructorimpl(i2)));
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(long j2) {
        super.print(ULong.m404toStringimpl(ULong.m359constructorimpl(j2)));
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(short s2) {
        super.print(UShort.m508toStringimpl(UShort.m465constructorimpl(s2)));
    }
}
