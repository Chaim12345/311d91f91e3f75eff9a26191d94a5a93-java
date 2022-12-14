package kotlin.sequences;

import java.util.Iterator;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public interface Sequence<T> {
    @NotNull
    Iterator<T> iterator();
}
