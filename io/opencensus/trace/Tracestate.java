package io.opencensus.trace;

import io.opencensus.internal.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes3.dex */
public abstract class Tracestate {
    private static final int KEY_MAX_SIZE = 256;
    private static final int MAX_KEY_VALUE_PAIRS = 32;
    private static final int VALUE_MAX_SIZE = 256;

    /* loaded from: classes3.dex */
    public static final class Builder {
        private static final Tracestate EMPTY = Tracestate.create(Collections.emptyList());
        @Nullable
        private ArrayList<Entry> entries;
        private final Tracestate parent;

        private Builder(Tracestate tracestate) {
            Utils.checkNotNull(tracestate, "parent");
            this.parent = tracestate;
            this.entries = null;
        }

        public Tracestate build() {
            ArrayList<Entry> arrayList = this.entries;
            return arrayList == null ? this.parent : Tracestate.create(arrayList);
        }

        public Builder remove(String str) {
            Utils.checkNotNull(str, "key");
            if (this.entries == null) {
                this.entries = new ArrayList<>(this.parent.getEntries());
            }
            int i2 = 0;
            while (true) {
                if (i2 >= this.entries.size()) {
                    break;
                } else if (this.entries.get(i2).getKey().equals(str)) {
                    this.entries.remove(i2);
                    break;
                } else {
                    i2++;
                }
            }
            return this;
        }

        public Builder set(String str, String str2) {
            Entry create = Entry.create(str, str2);
            if (this.entries == null) {
                this.entries = new ArrayList<>(this.parent.getEntries());
            }
            int i2 = 0;
            while (true) {
                if (i2 >= this.entries.size()) {
                    break;
                } else if (this.entries.get(i2).getKey().equals(create.getKey())) {
                    this.entries.remove(i2);
                    break;
                } else {
                    i2++;
                }
            }
            this.entries.add(0, create);
            return this;
        }
    }

    @Immutable
    /* loaded from: classes3.dex */
    public static abstract class Entry {
        public static Entry create(String str, String str2) {
            Utils.checkNotNull(str, "key");
            Utils.checkNotNull(str2, "value");
            Utils.checkArgument(Tracestate.validateKey(str), "Invalid key %s", str);
            Utils.checkArgument(Tracestate.validateValue(str2), "Invalid value %s", str2);
            return new AutoValue_Tracestate_Entry(str, str2);
        }

        public abstract String getKey();

        public abstract String getValue();
    }

    public static Builder builder() {
        return new Builder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Tracestate create(List<Entry> list) {
        Utils.checkState(list.size() <= 32, "Invalid size");
        return new AutoValue_Tracestate(Collections.unmodifiableList(list));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean validateKey(String str) {
        if (str.length() > 256 || str.isEmpty() || str.charAt(0) < 'a' || str.charAt(0) > 'z') {
            return false;
        }
        for (int i2 = 1; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if ((charAt < 'a' || charAt > 'z') && !((charAt >= '0' && charAt <= '9') || charAt == '_' || charAt == '-' || charAt == '*' || charAt == '/')) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean validateValue(String str) {
        if (str.length() > 256 || str.charAt(str.length() - 1) == ' ') {
            return false;
        }
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (charAt == ',' || charAt == '=' || charAt < ' ' || charAt > '~') {
                return false;
            }
        }
        return true;
    }

    @Nullable
    public String get(String str) {
        for (Entry entry : getEntries()) {
            if (entry.getKey().equals(str)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public abstract List<Entry> getEntries();

    public Builder toBuilder() {
        return new Builder();
    }
}
