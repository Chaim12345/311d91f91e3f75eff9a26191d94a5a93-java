package kotlinx.coroutines.sync;

import kotlin.jvm.JvmField;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class Empty {
    @JvmField
    @NotNull
    public final Object locked;

    public Empty(@NotNull Object obj) {
        this.locked = obj;
    }

    @NotNull
    public String toString() {
        return "Empty[" + this.locked + AbstractJsonLexerKt.END_LIST;
    }
}
