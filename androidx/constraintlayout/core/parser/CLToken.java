package androidx.constraintlayout.core.parser;
/* loaded from: classes.dex */
public class CLToken extends CLElement {

    /* renamed from: f  reason: collision with root package name */
    int f1867f;

    /* renamed from: g  reason: collision with root package name */
    Type f1868g;

    /* renamed from: h  reason: collision with root package name */
    char[] f1869h;

    /* renamed from: i  reason: collision with root package name */
    char[] f1870i;

    /* renamed from: j  reason: collision with root package name */
    char[] f1871j;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.constraintlayout.core.parser.CLToken$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1872a;

        static {
            int[] iArr = new int[Type.values().length];
            f1872a = iArr;
            try {
                iArr[Type.TRUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1872a[Type.FALSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1872a[Type.NULL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1872a[Type.UNKNOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum Type {
        UNKNOWN,
        TRUE,
        FALSE,
        NULL
    }

    public CLToken(char[] cArr) {
        super(cArr);
        this.f1867f = 0;
        this.f1868g = Type.UNKNOWN;
        this.f1869h = "true".toCharArray();
        this.f1870i = "false".toCharArray();
        this.f1871j = "null".toCharArray();
    }

    public static CLElement allocate(char[] cArr) {
        return new CLToken(cArr);
    }

    public boolean getBoolean() {
        Type type = this.f1868g;
        if (type == Type.TRUE) {
            return true;
        }
        if (type == Type.FALSE) {
            return false;
        }
        throw new CLParsingException("this token is not a boolean: <" + content() + ">", this);
    }

    public Type getType() {
        return this.f1868g;
    }

    public boolean isNull() {
        if (this.f1868g == Type.NULL) {
            return true;
        }
        throw new CLParsingException("this token is not a null: <" + content() + ">", this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.constraintlayout.core.parser.CLElement
    public String toFormattedJSON(int i2, int i3) {
        StringBuilder sb = new StringBuilder();
        a(sb, i2);
        sb.append(content());
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.constraintlayout.core.parser.CLElement
    public String toJSON() {
        if (CLParser.f1865a) {
            return "<" + content() + ">";
        }
        return content();
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0045, code lost:
        if ((r3 + 1) == r0.length) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0047, code lost:
        setEnd(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0058, code lost:
        if ((r3 + 1) == r0.length) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0068, code lost:
        if ((r3 + 1) == r0.length) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean validate(char c2, long j2) {
        Type type;
        int i2 = AnonymousClass1.f1872a[this.f1868g.ordinal()];
        if (i2 == 1) {
            char[] cArr = this.f1869h;
            int i3 = this.f1867f;
            r1 = cArr[i3] == c2;
            if (r1) {
            }
        } else if (i2 == 2) {
            char[] cArr2 = this.f1870i;
            int i4 = this.f1867f;
            r1 = cArr2[i4] == c2;
            if (r1) {
            }
        } else if (i2 == 3) {
            char[] cArr3 = this.f1871j;
            int i5 = this.f1867f;
            r1 = cArr3[i5] == c2;
            if (r1) {
            }
        } else if (i2 == 4) {
            char[] cArr4 = this.f1869h;
            int i6 = this.f1867f;
            if (cArr4[i6] == c2) {
                type = Type.TRUE;
            } else if (this.f1870i[i6] == c2) {
                type = Type.FALSE;
            } else if (this.f1871j[i6] == c2) {
                type = Type.NULL;
            }
            this.f1868g = type;
            r1 = true;
        }
        this.f1867f++;
        return r1;
    }
}
