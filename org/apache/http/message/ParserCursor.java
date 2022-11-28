package org.apache.http.message;

import kotlin.text.Typography;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
/* loaded from: classes3.dex */
public class ParserCursor {
    private final int lowerBound;
    private int pos;
    private final int upperBound;

    public ParserCursor(int i2, int i3) {
        if (i2 < 0) {
            throw new IndexOutOfBoundsException("Lower bound cannot be negative");
        }
        if (i2 > i3) {
            throw new IndexOutOfBoundsException("Lower bound cannot be greater then upper bound");
        }
        this.lowerBound = i2;
        this.upperBound = i3;
        this.pos = i2;
    }

    public boolean atEnd() {
        return this.pos >= this.upperBound;
    }

    public int getLowerBound() {
        return this.lowerBound;
    }

    public int getPos() {
        return this.pos;
    }

    public int getUpperBound() {
        return this.upperBound;
    }

    public String toString() {
        return AbstractJsonLexerKt.BEGIN_LIST + Integer.toString(this.lowerBound) + Typography.greater + Integer.toString(this.pos) + Typography.greater + Integer.toString(this.upperBound) + AbstractJsonLexerKt.END_LIST;
    }

    public void updatePos(int i2) {
        if (i2 < this.lowerBound) {
            throw new IndexOutOfBoundsException("pos: " + i2 + " < lowerBound: " + this.lowerBound);
        } else if (i2 <= this.upperBound) {
            this.pos = i2;
        } else {
            throw new IndexOutOfBoundsException("pos: " + i2 + " > upperBound: " + this.upperBound);
        }
    }
}
