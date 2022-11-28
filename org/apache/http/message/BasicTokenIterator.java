package org.apache.http.message;

import java.util.NoSuchElementException;
import org.apache.http.HeaderIterator;
import org.apache.http.ParseException;
import org.apache.http.TokenIterator;
import org.apache.http.util.Args;
/* loaded from: classes3.dex */
public class BasicTokenIterator implements TokenIterator {
    public static final String HTTP_SEPARATORS = " ,;=()<>@:\\\"/[]?{}\t";
    protected String currentHeader;
    protected String currentToken;
    protected final HeaderIterator headerIt;
    protected int searchPos = findNext(-1);

    public BasicTokenIterator(HeaderIterator headerIterator) {
        this.headerIt = (HeaderIterator) Args.notNull(headerIterator, "Header iterator");
    }

    protected String createToken(String str, int i2, int i3) {
        return str.substring(i2, i3);
    }

    protected int findNext(int i2) {
        int findTokenSeparator;
        String createToken;
        int i3 = -1;
        if (i2 >= 0) {
            findTokenSeparator = findTokenSeparator(i2);
        } else if (!this.headerIt.hasNext()) {
            return -1;
        } else {
            this.currentHeader = this.headerIt.nextHeader().getValue();
            findTokenSeparator = 0;
        }
        int findTokenStart = findTokenStart(findTokenSeparator);
        if (findTokenStart < 0) {
            createToken = null;
        } else {
            i3 = findTokenEnd(findTokenStart);
            createToken = createToken(this.currentHeader, findTokenStart, i3);
        }
        this.currentToken = createToken;
        return i3;
    }

    protected int findTokenEnd(int i2) {
        Args.notNegative(i2, "Search position");
        int length = this.currentHeader.length();
        do {
            i2++;
            if (i2 >= length) {
                break;
            }
        } while (isTokenChar(this.currentHeader.charAt(i2)));
        return i2;
    }

    protected int findTokenSeparator(int i2) {
        int notNegative = Args.notNegative(i2, "Search position");
        int length = this.currentHeader.length();
        boolean z = false;
        while (!z && notNegative < length) {
            char charAt = this.currentHeader.charAt(notNegative);
            if (isTokenSeparator(charAt)) {
                z = true;
            } else if (!isWhitespace(charAt)) {
                if (isTokenChar(charAt)) {
                    throw new ParseException("Tokens without separator (pos " + notNegative + "): " + this.currentHeader);
                }
                throw new ParseException("Invalid character after token (pos " + notNegative + "): " + this.currentHeader);
            } else {
                notNegative++;
            }
        }
        return notNegative;
    }

    protected int findTokenStart(int i2) {
        int notNegative = Args.notNegative(i2, "Search position");
        boolean z = false;
        while (!z) {
            String str = this.currentHeader;
            if (str == null) {
                break;
            }
            int length = str.length();
            while (!z && notNegative < length) {
                char charAt = this.currentHeader.charAt(notNegative);
                if (isTokenSeparator(charAt) || isWhitespace(charAt)) {
                    notNegative++;
                } else if (!isTokenChar(this.currentHeader.charAt(notNegative))) {
                    throw new ParseException("Invalid character before token (pos " + notNegative + "): " + this.currentHeader);
                } else {
                    z = true;
                }
            }
            if (!z) {
                if (this.headerIt.hasNext()) {
                    this.currentHeader = this.headerIt.nextHeader().getValue();
                    notNegative = 0;
                } else {
                    this.currentHeader = null;
                }
            }
        }
        if (z) {
            return notNegative;
        }
        return -1;
    }

    @Override // org.apache.http.TokenIterator, java.util.Iterator
    public boolean hasNext() {
        return this.currentToken != null;
    }

    protected boolean isHttpSeparator(char c2) {
        return HTTP_SEPARATORS.indexOf(c2) >= 0;
    }

    protected boolean isTokenChar(char c2) {
        if (Character.isLetterOrDigit(c2)) {
            return true;
        }
        return (Character.isISOControl(c2) || isHttpSeparator(c2)) ? false : true;
    }

    protected boolean isTokenSeparator(char c2) {
        return c2 == ',';
    }

    protected boolean isWhitespace(char c2) {
        return c2 == '\t' || Character.isSpaceChar(c2);
    }

    @Override // java.util.Iterator
    public final Object next() {
        return nextToken();
    }

    @Override // org.apache.http.TokenIterator
    public String nextToken() {
        String str = this.currentToken;
        if (str != null) {
            this.searchPos = findNext(this.searchPos);
            return str;
        }
        throw new NoSuchElementException("Iteration already finished.");
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Removing tokens is not supported.");
    }
}
