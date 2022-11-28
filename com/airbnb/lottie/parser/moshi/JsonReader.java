package com.airbnb.lottie.parser.moshi;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
/* loaded from: classes.dex */
public abstract class JsonReader implements Closeable {
    private static final String[] REPLACEMENT_CHARS = new String[128];

    /* renamed from: a  reason: collision with root package name */
    int f4450a;

    /* renamed from: b  reason: collision with root package name */
    int[] f4451b = new int[32];

    /* renamed from: c  reason: collision with root package name */
    String[] f4452c = new String[32];

    /* renamed from: d  reason: collision with root package name */
    int[] f4453d = new int[32];

    /* renamed from: e  reason: collision with root package name */
    boolean f4454e;

    /* renamed from: f  reason: collision with root package name */
    boolean f4455f;

    /* loaded from: classes.dex */
    public static final class Options {

        /* renamed from: a  reason: collision with root package name */
        final String[] f4456a;

        /* renamed from: b  reason: collision with root package name */
        final okio.Options f4457b;

        private Options(String[] strArr, okio.Options options) {
            this.f4456a = strArr;
            this.f4457b = options;
        }

        public static Options of(String... strArr) {
            try {
                ByteString[] byteStringArr = new ByteString[strArr.length];
                Buffer buffer = new Buffer();
                for (int i2 = 0; i2 < strArr.length; i2++) {
                    JsonReader.string(buffer, strArr[i2]);
                    buffer.readByte();
                    byteStringArr[i2] = buffer.readByteString();
                }
                return new Options((String[]) strArr.clone(), okio.Options.of(byteStringArr));
            } catch (IOException e2) {
                throw new AssertionError(e2);
            }
        }
    }

    /* loaded from: classes.dex */
    public enum Token {
        BEGIN_ARRAY,
        END_ARRAY,
        BEGIN_OBJECT,
        END_OBJECT,
        NAME,
        STRING,
        NUMBER,
        BOOLEAN,
        NULL,
        END_DOCUMENT
    }

    static {
        for (int i2 = 0; i2 <= 31; i2++) {
            REPLACEMENT_CHARS[i2] = String.format("\\u%04x", Integer.valueOf(i2));
        }
        String[] strArr = REPLACEMENT_CHARS;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
    }

    public static JsonReader of(BufferedSource bufferedSource) {
        return new JsonUtf8Reader(bufferedSource);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:16:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void string(BufferedSink bufferedSink, String str) {
        int i2;
        String str2;
        String[] strArr = REPLACEMENT_CHARS;
        bufferedSink.writeByte(34);
        int length = str.length();
        int i3 = 0;
        while (i2 < length) {
            char charAt = str.charAt(i2);
            if (charAt < 128) {
                str2 = strArr[charAt];
                i2 = str2 == null ? i2 + 1 : 0;
                if (i3 < i2) {
                    bufferedSink.writeUtf8(str, i3, i2);
                }
                bufferedSink.writeUtf8(str2);
                i3 = i2 + 1;
            } else {
                if (charAt == 8232) {
                    str2 = "\\u2028";
                } else if (charAt == 8233) {
                    str2 = "\\u2029";
                }
                if (i3 < i2) {
                }
                bufferedSink.writeUtf8(str2);
                i3 = i2 + 1;
            }
        }
        if (i3 < length) {
            bufferedSink.writeUtf8(str, i3, length);
        }
        bufferedSink.writeByte(34);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b(int i2) {
        int i3 = this.f4450a;
        int[] iArr = this.f4451b;
        if (i3 == iArr.length) {
            if (i3 == 256) {
                throw new JsonDataException("Nesting too deep at " + getPath());
            }
            this.f4451b = Arrays.copyOf(iArr, iArr.length * 2);
            String[] strArr = this.f4452c;
            this.f4452c = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
            int[] iArr2 = this.f4453d;
            this.f4453d = Arrays.copyOf(iArr2, iArr2.length * 2);
        }
        int[] iArr3 = this.f4451b;
        int i4 = this.f4450a;
        this.f4450a = i4 + 1;
        iArr3[i4] = i2;
    }

    public abstract void beginArray();

    public abstract void beginObject();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final JsonEncodingException c(String str) {
        throw new JsonEncodingException(str + " at path " + getPath());
    }

    public abstract void endArray();

    public abstract void endObject();

    public final String getPath() {
        return JsonScope.a(this.f4450a, this.f4451b, this.f4452c, this.f4453d);
    }

    public abstract boolean hasNext();

    public abstract boolean nextBoolean();

    public abstract double nextDouble();

    public abstract int nextInt();

    public abstract String nextName();

    public abstract String nextString();

    public abstract Token peek();

    public abstract int selectName(Options options);

    public abstract void skipName();

    public abstract void skipValue();
}
