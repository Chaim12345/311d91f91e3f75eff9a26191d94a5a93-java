package kotlin.system;

import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
@JvmName(name = "ProcessKt")
/* loaded from: classes3.dex */
public final class ProcessKt {
    @InlineOnly
    private static final Void exitProcess(int i2) {
        System.exit(i2);
        throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
    }
}
