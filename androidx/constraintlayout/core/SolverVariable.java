package androidx.constraintlayout.core;

import androidx.exifinterface.media.ExifInterface;
import java.util.Arrays;
/* loaded from: classes.dex */
public class SolverVariable implements Comparable<SolverVariable> {
    private static final boolean INTERNAL_DEBUG = false;
    public static final int STRENGTH_BARRIER = 6;
    public static final int STRENGTH_CENTERING = 7;
    public static final int STRENGTH_EQUALITY = 5;
    public static final int STRENGTH_FIXED = 8;
    public static final int STRENGTH_HIGH = 3;
    public static final int STRENGTH_HIGHEST = 4;
    public static final int STRENGTH_LOW = 1;
    public static final int STRENGTH_MEDIUM = 2;
    public static final int STRENGTH_NONE = 0;
    private static final boolean VAR_USE_HASH = false;
    private static int uniqueConstantId = 1;
    private static int uniqueErrorId = 1;
    private static int uniqueId = 1;
    private static int uniqueSlackId = 1;
    private static int uniqueUnrestrictedId = 1;

    /* renamed from: a  reason: collision with root package name */
    int f1665a;

    /* renamed from: b  reason: collision with root package name */
    float[] f1666b;

    /* renamed from: c  reason: collision with root package name */
    float[] f1667c;
    public float computedValue;

    /* renamed from: d  reason: collision with root package name */
    Type f1668d;

    /* renamed from: e  reason: collision with root package name */
    ArrayRow[] f1669e;

    /* renamed from: f  reason: collision with root package name */
    int f1670f;

    /* renamed from: g  reason: collision with root package name */
    boolean f1671g;

    /* renamed from: h  reason: collision with root package name */
    int f1672h;

    /* renamed from: i  reason: collision with root package name */
    float f1673i;
    public int id;
    public boolean inGoal;
    public boolean isFinalValue;
    private String mName;
    public int strength;
    public int usageInRowCount;

    /* renamed from: androidx.constraintlayout.core.SolverVariable$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1674a;

        static {
            int[] iArr = new int[Type.values().length];
            f1674a = iArr;
            try {
                iArr[Type.UNRESTRICTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1674a[Type.CONSTANT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1674a[Type.SLACK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1674a[Type.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1674a[Type.UNKNOWN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* loaded from: classes.dex */
    public enum Type {
        UNRESTRICTED,
        CONSTANT,
        SLACK,
        ERROR,
        UNKNOWN
    }

    public SolverVariable(Type type, String str) {
        this.id = -1;
        this.f1665a = -1;
        this.strength = 0;
        this.isFinalValue = false;
        this.f1666b = new float[9];
        this.f1667c = new float[9];
        this.f1669e = new ArrayRow[16];
        this.f1670f = 0;
        this.usageInRowCount = 0;
        this.f1671g = false;
        this.f1672h = -1;
        this.f1673i = 0.0f;
        this.f1668d = type;
    }

    public SolverVariable(String str, Type type) {
        this.id = -1;
        this.f1665a = -1;
        this.strength = 0;
        this.isFinalValue = false;
        this.f1666b = new float[9];
        this.f1667c = new float[9];
        this.f1669e = new ArrayRow[16];
        this.f1670f = 0;
        this.usageInRowCount = 0;
        this.f1671g = false;
        this.f1672h = -1;
        this.f1673i = 0.0f;
        this.mName = str;
        this.f1668d = type;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a() {
        uniqueErrorId++;
    }

    private static String getUniqueName(Type type, String str) {
        StringBuilder sb;
        int i2;
        if (str != null) {
            sb = new StringBuilder();
            sb.append(str);
            i2 = uniqueErrorId;
        } else {
            int i3 = AnonymousClass1.f1674a[type.ordinal()];
            if (i3 == 1) {
                sb = new StringBuilder();
                sb.append("U");
                i2 = uniqueUnrestrictedId + 1;
                uniqueUnrestrictedId = i2;
            } else if (i3 == 2) {
                sb = new StringBuilder();
                sb.append("C");
                i2 = uniqueConstantId + 1;
                uniqueConstantId = i2;
            } else if (i3 == 3) {
                sb = new StringBuilder();
                sb.append(ExifInterface.LATITUDE_SOUTH);
                i2 = uniqueSlackId + 1;
                uniqueSlackId = i2;
            } else if (i3 == 4) {
                sb = new StringBuilder();
                sb.append("e");
                i2 = uniqueErrorId + 1;
                uniqueErrorId = i2;
            } else if (i3 != 5) {
                throw new AssertionError(type.name());
            } else {
                sb = new StringBuilder();
                sb.append(ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
                i2 = uniqueId + 1;
                uniqueId = i2;
            }
        }
        sb.append(i2);
        return sb.toString();
    }

    public final void addToRow(ArrayRow arrayRow) {
        int i2 = 0;
        while (true) {
            int i3 = this.f1670f;
            if (i2 >= i3) {
                ArrayRow[] arrayRowArr = this.f1669e;
                if (i3 >= arrayRowArr.length) {
                    this.f1669e = (ArrayRow[]) Arrays.copyOf(arrayRowArr, arrayRowArr.length * 2);
                }
                ArrayRow[] arrayRowArr2 = this.f1669e;
                int i4 = this.f1670f;
                arrayRowArr2[i4] = arrayRow;
                this.f1670f = i4 + 1;
                return;
            } else if (this.f1669e[i2] == arrayRow) {
                return;
            } else {
                i2++;
            }
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(SolverVariable solverVariable) {
        return this.id - solverVariable.id;
    }

    public String getName() {
        return this.mName;
    }

    public final void removeFromRow(ArrayRow arrayRow) {
        int i2 = this.f1670f;
        int i3 = 0;
        while (i3 < i2) {
            if (this.f1669e[i3] == arrayRow) {
                while (i3 < i2 - 1) {
                    ArrayRow[] arrayRowArr = this.f1669e;
                    int i4 = i3 + 1;
                    arrayRowArr[i3] = arrayRowArr[i4];
                    i3 = i4;
                }
                this.f1670f--;
                return;
            }
            i3++;
        }
    }

    public void reset() {
        this.mName = null;
        this.f1668d = Type.UNKNOWN;
        this.strength = 0;
        this.id = -1;
        this.f1665a = -1;
        this.computedValue = 0.0f;
        this.isFinalValue = false;
        this.f1671g = false;
        this.f1672h = -1;
        this.f1673i = 0.0f;
        int i2 = this.f1670f;
        for (int i3 = 0; i3 < i2; i3++) {
            this.f1669e[i3] = null;
        }
        this.f1670f = 0;
        this.usageInRowCount = 0;
        this.inGoal = false;
        Arrays.fill(this.f1667c, 0.0f);
    }

    public void setFinalValue(LinearSystem linearSystem, float f2) {
        this.computedValue = f2;
        this.isFinalValue = true;
        this.f1671g = false;
        this.f1672h = -1;
        this.f1673i = 0.0f;
        int i2 = this.f1670f;
        this.f1665a = -1;
        for (int i3 = 0; i3 < i2; i3++) {
            this.f1669e[i3].updateFromFinalVariable(linearSystem, this, false);
        }
        this.f1670f = 0;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public void setSynonym(LinearSystem linearSystem, SolverVariable solverVariable, float f2) {
        this.f1671g = true;
        this.f1672h = solverVariable.id;
        this.f1673i = f2;
        int i2 = this.f1670f;
        this.f1665a = -1;
        for (int i3 = 0; i3 < i2; i3++) {
            this.f1669e[i3].updateFromSynonymVariable(linearSystem, this, false);
        }
        this.f1670f = 0;
        linearSystem.displayReadableRows();
    }

    public void setType(Type type, String str) {
        this.f1668d = type;
    }

    public String toString() {
        StringBuilder sb;
        if (this.mName != null) {
            sb = new StringBuilder();
            sb.append("");
            sb.append(this.mName);
        } else {
            sb = new StringBuilder();
            sb.append("");
            sb.append(this.id);
        }
        return sb.toString();
    }

    public final void updateReferencesWithNewDefinition(LinearSystem linearSystem, ArrayRow arrayRow) {
        int i2 = this.f1670f;
        for (int i3 = 0; i3 < i2; i3++) {
            this.f1669e[i3].updateFromRow(linearSystem, arrayRow, false);
        }
        this.f1670f = 0;
    }
}
