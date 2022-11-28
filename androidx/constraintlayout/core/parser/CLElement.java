package androidx.constraintlayout.core.parser;

import java.io.PrintStream;
import kotlin.jvm.internal.LongCompanionObject;
import org.apache.commons.cli.HelpFormatter;
import org.apache.http.message.TokenParser;
/* loaded from: classes.dex */
public class CLElement {

    /* renamed from: d  reason: collision with root package name */
    protected static int f1857d = 80;

    /* renamed from: e  reason: collision with root package name */
    protected static int f1858e = 2;

    /* renamed from: a  reason: collision with root package name */
    protected long f1859a = -1;

    /* renamed from: b  reason: collision with root package name */
    protected long f1860b = LongCompanionObject.MAX_VALUE;

    /* renamed from: c  reason: collision with root package name */
    protected CLContainer f1861c;
    private int line;
    private final char[] mContent;

    public CLElement(char[] cArr) {
        this.mContent = cArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(StringBuilder sb, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            sb.append(TokenParser.SP);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String b() {
        if (CLParser.f1865a) {
            return c() + " -> ";
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String c() {
        String cls = getClass().toString();
        return cls.substring(cls.lastIndexOf(46) + 1);
    }

    public String content() {
        int i2;
        String str = new String(this.mContent);
        long j2 = this.f1860b;
        if (j2 != LongCompanionObject.MAX_VALUE) {
            long j3 = this.f1859a;
            if (j2 >= j3) {
                i2 = (int) j3;
                return str.substring(i2, ((int) j2) + 1);
            }
        }
        j2 = this.f1859a;
        i2 = (int) j2;
        return str.substring(i2, ((int) j2) + 1);
    }

    public CLElement getContainer() {
        return this.f1861c;
    }

    public long getEnd() {
        return this.f1860b;
    }

    public float getFloat() {
        if (this instanceof CLNumber) {
            return ((CLNumber) this).getFloat();
        }
        return Float.NaN;
    }

    public int getInt() {
        if (this instanceof CLNumber) {
            return ((CLNumber) this).getInt();
        }
        return 0;
    }

    public int getLine() {
        return this.line;
    }

    public long getStart() {
        return this.f1859a;
    }

    public boolean isDone() {
        return this.f1860b != LongCompanionObject.MAX_VALUE;
    }

    public boolean isStarted() {
        return this.f1859a > -1;
    }

    public boolean notStarted() {
        return this.f1859a == -1;
    }

    public void setContainer(CLContainer cLContainer) {
        this.f1861c = cLContainer;
    }

    public void setEnd(long j2) {
        if (this.f1860b != LongCompanionObject.MAX_VALUE) {
            return;
        }
        this.f1860b = j2;
        if (CLParser.f1865a) {
            PrintStream printStream = System.out;
            printStream.println("closing " + hashCode() + " -> " + this);
        }
        CLContainer cLContainer = this.f1861c;
        if (cLContainer != null) {
            cLContainer.add(this);
        }
    }

    public void setLine(int i2) {
        this.line = i2;
    }

    public void setStart(long j2) {
        this.f1859a = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String toFormattedJSON(int i2, int i3) {
        return "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String toJSON() {
        return "";
    }

    public String toString() {
        long j2 = this.f1859a;
        long j3 = this.f1860b;
        if (j2 > j3 || j3 == LongCompanionObject.MAX_VALUE) {
            return getClass() + " (INVALID, " + this.f1859a + HelpFormatter.DEFAULT_OPT_PREFIX + this.f1860b + ")";
        }
        String substring = new String(this.mContent).substring((int) this.f1859a, ((int) this.f1860b) + 1);
        return c() + " (" + this.f1859a + " : " + this.f1860b + ") <<" + substring + ">>";
    }
}
