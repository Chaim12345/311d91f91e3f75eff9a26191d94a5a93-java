package androidx.constraintlayout.core.parser;
/* loaded from: classes.dex */
public class CLParser {

    /* renamed from: a  reason: collision with root package name */
    static boolean f1865a = false;
    private boolean hasComment = false;
    private int lineNumber;
    private String mContent;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.constraintlayout.core.parser.CLParser$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1866a;

        static {
            int[] iArr = new int[TYPE.values().length];
            f1866a = iArr;
            try {
                iArr[TYPE.OBJECT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1866a[TYPE.ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1866a[TYPE.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1866a[TYPE.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1866a[TYPE.KEY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1866a[TYPE.TOKEN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum TYPE {
        UNKNOWN,
        OBJECT,
        ARRAY,
        NUMBER,
        STRING,
        KEY,
        TOKEN
    }

    public CLParser(String str) {
        this.mContent = str;
    }

    private CLElement createElement(CLElement cLElement, int i2, TYPE type, boolean z, char[] cArr) {
        CLElement allocate;
        if (f1865a) {
            System.out.println("CREATE " + type + " at " + cArr[i2]);
        }
        switch (AnonymousClass1.f1866a[type.ordinal()]) {
            case 1:
                allocate = CLObject.allocate(cArr);
                i2++;
                break;
            case 2:
                allocate = CLArray.allocate(cArr);
                i2++;
                break;
            case 3:
                allocate = CLString.allocate(cArr);
                break;
            case 4:
                allocate = CLNumber.allocate(cArr);
                break;
            case 5:
                allocate = CLKey.allocate(cArr);
                break;
            case 6:
                allocate = CLToken.allocate(cArr);
                break;
            default:
                allocate = null;
                break;
        }
        if (allocate == null) {
            return null;
        }
        allocate.setLine(this.lineNumber);
        if (z) {
            allocate.setStart(i2);
        }
        if (cLElement instanceof CLContainer) {
            allocate.setContainer((CLContainer) cLElement);
        }
        return allocate;
    }

    private CLElement getNextJsonElement(int i2, char c2, CLElement cLElement, char[] cArr) {
        if (c2 == '\t' || c2 == '\n' || c2 == '\r' || c2 == ' ') {
            return cLElement;
        }
        if (c2 == '\"' || c2 == '\'') {
            return cLElement instanceof CLObject ? createElement(cLElement, i2, TYPE.KEY, true, cArr) : createElement(cLElement, i2, TYPE.STRING, true, cArr);
        } else if (c2 != '[') {
            if (c2 != ']') {
                if (c2 == '{') {
                    return createElement(cLElement, i2, TYPE.OBJECT, true, cArr);
                }
                if (c2 != '}') {
                    switch (c2) {
                        case '+':
                        case '-':
                        case '.':
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            return createElement(cLElement, i2, TYPE.NUMBER, true, cArr);
                        case ',':
                        case ':':
                            return cLElement;
                        case '/':
                            int i3 = i2 + 1;
                            if (i3 >= cArr.length || cArr[i3] != '/') {
                                return cLElement;
                            }
                            this.hasComment = true;
                            return cLElement;
                        default:
                            if (!(cLElement instanceof CLContainer) || (cLElement instanceof CLObject)) {
                                return createElement(cLElement, i2, TYPE.KEY, true, cArr);
                            }
                            CLElement createElement = createElement(cLElement, i2, TYPE.TOKEN, true, cArr);
                            CLToken cLToken = (CLToken) createElement;
                            if (cLToken.validate(c2, i2)) {
                                return createElement;
                            }
                            throw new CLParsingException("incorrect token <" + c2 + "> at line " + this.lineNumber, cLToken);
                    }
                }
            }
            cLElement.setEnd(i2 - 1);
            CLElement container = cLElement.getContainer();
            container.setEnd(i2);
            return container;
        } else {
            return createElement(cLElement, i2, TYPE.ARRAY, true, cArr);
        }
    }

    public static CLObject parse(String str) {
        return new CLParser(str).parse();
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x005f, code lost:
        if (r9 == '}') goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0068, code lost:
        if (r9 == ']') goto L41;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public CLObject parse() {
        char[] charArray = this.mContent.toCharArray();
        int length = charArray.length;
        int i2 = 1;
        this.lineNumber = 1;
        boolean z = false;
        int i3 = 0;
        while (true) {
            if (i3 >= length) {
                i3 = -1;
                break;
            }
            char c2 = charArray[i3];
            if (c2 == '{') {
                break;
            }
            if (c2 == '\n') {
                this.lineNumber++;
            }
            i3++;
        }
        if (i3 != -1) {
            CLObject allocate = CLObject.allocate(charArray);
            allocate.setLine(this.lineNumber);
            allocate.setStart(i3);
            int i4 = i3 + 1;
            CLObject cLObject = allocate;
            while (i4 < length) {
                char c3 = charArray[i4];
                if (c3 == '\n') {
                    this.lineNumber += i2;
                }
                if (this.hasComment) {
                    if (c3 == '\n') {
                        this.hasComment = z;
                    } else {
                        continue;
                        i4++;
                        i2 = 1;
                        z = false;
                    }
                }
                if (cLObject == null) {
                    break;
                }
                if (!cLObject.isDone()) {
                    if (!(cLObject instanceof CLObject)) {
                        if (!(cLObject instanceof CLArray)) {
                            boolean z2 = cLObject instanceof CLString;
                            if (z2) {
                                long j2 = cLObject.f1859a;
                                if (charArray[(int) j2] == c3) {
                                    cLObject.setStart(j2 + 1);
                                }
                            } else {
                                if (cLObject instanceof CLToken) {
                                    CLToken cLToken = (CLToken) cLObject;
                                    if (!cLToken.validate(c3, i4)) {
                                        throw new CLParsingException("parsing incorrect token " + cLToken.content() + " at line " + this.lineNumber, cLToken);
                                    }
                                }
                                if ((cLObject instanceof CLKey) || z2) {
                                    long j3 = cLObject.f1859a;
                                    char c4 = charArray[(int) j3];
                                    if ((c4 == '\'' || c4 == '\"') && c4 == c3) {
                                        cLObject.setStart(j3 + 1);
                                        cLObject.setEnd(i4 - 1);
                                    }
                                }
                                if (!cLObject.isDone() && (c3 == '}' || c3 == ']' || c3 == ',' || c3 == ' ' || c3 == '\t' || c3 == '\r' || c3 == '\n' || c3 == ':')) {
                                    long j4 = i4 - 1;
                                    cLObject.setEnd(j4);
                                    if (c3 == '}' || c3 == ']') {
                                        cLObject = cLObject.getContainer();
                                        cLObject.setEnd(j4);
                                        if (cLObject instanceof CLKey) {
                                            cLObject = cLObject.getContainer();
                                            cLObject.setEnd(j4);
                                        }
                                    }
                                }
                            }
                        }
                        cLObject.setEnd(i4 - 1);
                    }
                    if (cLObject.isDone() && (!(cLObject instanceof CLKey) || ((CLKey) cLObject).f1856f.size() > 0)) {
                        cLObject = cLObject.getContainer();
                    }
                    i4++;
                    i2 = 1;
                    z = false;
                }
                cLObject = getNextJsonElement(i4, c3, cLObject, charArray);
                if (cLObject.isDone()) {
                    cLObject = cLObject.getContainer();
                }
                i4++;
                i2 = 1;
                z = false;
            }
            while (cLObject != null && !cLObject.isDone()) {
                if (cLObject instanceof CLString) {
                    cLObject.setStart(((int) cLObject.f1859a) + 1);
                }
                cLObject.setEnd(length - 1);
                cLObject = cLObject.getContainer();
            }
            if (f1865a) {
                System.out.println("Root: " + allocate.toJSON());
            }
            return allocate;
        }
        throw new CLParsingException("invalid json content", null);
    }
}
