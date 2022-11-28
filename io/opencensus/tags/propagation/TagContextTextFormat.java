package io.opencensus.tags.propagation;

import io.opencensus.tags.TagContext;
import java.util.List;
import javax.annotation.Nullable;
/* loaded from: classes3.dex */
public abstract class TagContextTextFormat {

    /* loaded from: classes3.dex */
    public static abstract class Getter<C> {
        @Nullable
        public abstract String get(C c2, String str);
    }

    /* loaded from: classes3.dex */
    public static abstract class Setter<C> {
        public abstract void put(C c2, String str, String str2);
    }

    public abstract <C> TagContext extract(C c2, Getter<C> getter);

    public abstract List<String> fields();

    public abstract <C> void inject(TagContext tagContext, C c2, Setter<C> setter);
}
