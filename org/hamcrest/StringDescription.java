package org.hamcrest;

import java.io.IOException;
/* loaded from: classes4.dex */
public class StringDescription extends BaseDescription {
    private final Appendable out;

    public StringDescription() {
        this(new StringBuilder());
    }

    public StringDescription(Appendable appendable) {
        this.out = appendable;
    }

    public static String asString(SelfDescribing selfDescribing) {
        return toString(selfDescribing);
    }

    public static String toString(SelfDescribing selfDescribing) {
        return new StringDescription().appendDescriptionOf(selfDescribing).toString();
    }

    @Override // org.hamcrest.BaseDescription
    protected void a(char c2) {
        try {
            this.out.append(c2);
        } catch (IOException e2) {
            throw new RuntimeException("Could not write description", e2);
        }
    }

    @Override // org.hamcrest.BaseDescription
    protected void b(String str) {
        try {
            this.out.append(str);
        } catch (IOException e2) {
            throw new RuntimeException("Could not write description", e2);
        }
    }

    public String toString() {
        return this.out.toString();
    }
}
