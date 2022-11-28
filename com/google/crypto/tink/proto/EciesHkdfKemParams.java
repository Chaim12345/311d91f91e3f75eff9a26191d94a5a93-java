package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.CodedInputStream;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import com.google.crypto.tink.shaded.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes2.dex */
public final class EciesHkdfKemParams extends GeneratedMessageLite<EciesHkdfKemParams, Builder> implements EciesHkdfKemParamsOrBuilder {
    public static final int CURVE_TYPE_FIELD_NUMBER = 1;
    private static final EciesHkdfKemParams DEFAULT_INSTANCE;
    public static final int HKDF_HASH_TYPE_FIELD_NUMBER = 2;
    public static final int HKDF_SALT_FIELD_NUMBER = 11;
    private static volatile Parser<EciesHkdfKemParams> PARSER;
    private int curveType_;
    private int hkdfHashType_;
    private ByteString hkdfSalt_ = ByteString.EMPTY;

    /* renamed from: com.google.crypto.tink.proto.EciesHkdfKemParams$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9678a;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f9678a = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9678a[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9678a[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9678a[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9678a[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9678a[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9678a[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* loaded from: classes2.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<EciesHkdfKemParams, Builder> implements EciesHkdfKemParamsOrBuilder {
        private Builder() {
            super(EciesHkdfKemParams.DEFAULT_INSTANCE);
        }

        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        public Builder clearCurveType() {
            d();
            ((EciesHkdfKemParams) this.f9750a).clearCurveType();
            return this;
        }

        public Builder clearHkdfHashType() {
            d();
            ((EciesHkdfKemParams) this.f9750a).clearHkdfHashType();
            return this;
        }

        public Builder clearHkdfSalt() {
            d();
            ((EciesHkdfKemParams) this.f9750a).clearHkdfSalt();
            return this;
        }

        @Override // com.google.crypto.tink.proto.EciesHkdfKemParamsOrBuilder
        public EllipticCurveType getCurveType() {
            return ((EciesHkdfKemParams) this.f9750a).getCurveType();
        }

        @Override // com.google.crypto.tink.proto.EciesHkdfKemParamsOrBuilder
        public int getCurveTypeValue() {
            return ((EciesHkdfKemParams) this.f9750a).getCurveTypeValue();
        }

        @Override // com.google.crypto.tink.proto.EciesHkdfKemParamsOrBuilder
        public HashType getHkdfHashType() {
            return ((EciesHkdfKemParams) this.f9750a).getHkdfHashType();
        }

        @Override // com.google.crypto.tink.proto.EciesHkdfKemParamsOrBuilder
        public int getHkdfHashTypeValue() {
            return ((EciesHkdfKemParams) this.f9750a).getHkdfHashTypeValue();
        }

        @Override // com.google.crypto.tink.proto.EciesHkdfKemParamsOrBuilder
        public ByteString getHkdfSalt() {
            return ((EciesHkdfKemParams) this.f9750a).getHkdfSalt();
        }

        public Builder setCurveType(EllipticCurveType ellipticCurveType) {
            d();
            ((EciesHkdfKemParams) this.f9750a).setCurveType(ellipticCurveType);
            return this;
        }

        public Builder setCurveTypeValue(int i2) {
            d();
            ((EciesHkdfKemParams) this.f9750a).setCurveTypeValue(i2);
            return this;
        }

        public Builder setHkdfHashType(HashType hashType) {
            d();
            ((EciesHkdfKemParams) this.f9750a).setHkdfHashType(hashType);
            return this;
        }

        public Builder setHkdfHashTypeValue(int i2) {
            d();
            ((EciesHkdfKemParams) this.f9750a).setHkdfHashTypeValue(i2);
            return this;
        }

        public Builder setHkdfSalt(ByteString byteString) {
            d();
            ((EciesHkdfKemParams) this.f9750a).setHkdfSalt(byteString);
            return this;
        }
    }

    static {
        EciesHkdfKemParams eciesHkdfKemParams = new EciesHkdfKemParams();
        DEFAULT_INSTANCE = eciesHkdfKemParams;
        GeneratedMessageLite.L(EciesHkdfKemParams.class, eciesHkdfKemParams);
    }

    private EciesHkdfKemParams() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCurveType() {
        this.curveType_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearHkdfHashType() {
        this.hkdfHashType_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearHkdfSalt() {
        this.hkdfSalt_ = getDefaultInstance().getHkdfSalt();
    }

    public static EciesHkdfKemParams getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.i();
    }

    public static Builder newBuilder(EciesHkdfKemParams eciesHkdfKemParams) {
        return (Builder) DEFAULT_INSTANCE.j(eciesHkdfKemParams);
    }

    public static EciesHkdfKemParams parseDelimitedFrom(InputStream inputStream) {
        return (EciesHkdfKemParams) GeneratedMessageLite.w(DEFAULT_INSTANCE, inputStream);
    }

    public static EciesHkdfKemParams parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (EciesHkdfKemParams) GeneratedMessageLite.x(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static EciesHkdfKemParams parseFrom(ByteString byteString) {
        return (EciesHkdfKemParams) GeneratedMessageLite.y(DEFAULT_INSTANCE, byteString);
    }

    public static EciesHkdfKemParams parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (EciesHkdfKemParams) GeneratedMessageLite.z(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static EciesHkdfKemParams parseFrom(CodedInputStream codedInputStream) {
        return (EciesHkdfKemParams) GeneratedMessageLite.A(DEFAULT_INSTANCE, codedInputStream);
    }

    public static EciesHkdfKemParams parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (EciesHkdfKemParams) GeneratedMessageLite.B(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static EciesHkdfKemParams parseFrom(InputStream inputStream) {
        return (EciesHkdfKemParams) GeneratedMessageLite.C(DEFAULT_INSTANCE, inputStream);
    }

    public static EciesHkdfKemParams parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (EciesHkdfKemParams) GeneratedMessageLite.D(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static EciesHkdfKemParams parseFrom(ByteBuffer byteBuffer) {
        return (EciesHkdfKemParams) GeneratedMessageLite.E(DEFAULT_INSTANCE, byteBuffer);
    }

    public static EciesHkdfKemParams parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (EciesHkdfKemParams) GeneratedMessageLite.F(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static EciesHkdfKemParams parseFrom(byte[] bArr) {
        return (EciesHkdfKemParams) GeneratedMessageLite.G(DEFAULT_INSTANCE, bArr);
    }

    public static EciesHkdfKemParams parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (EciesHkdfKemParams) GeneratedMessageLite.H(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Parser<EciesHkdfKemParams> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurveType(EllipticCurveType ellipticCurveType) {
        this.curveType_ = ellipticCurveType.getNumber();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurveTypeValue(int i2) {
        this.curveType_ = i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHkdfHashType(HashType hashType) {
        this.hkdfHashType_ = hashType.getNumber();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHkdfHashTypeValue(int i2) {
        this.hkdfHashType_ = i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHkdfSalt(ByteString byteString) {
        byteString.getClass();
        this.hkdfSalt_ = byteString;
    }

    @Override // com.google.crypto.tink.proto.EciesHkdfKemParamsOrBuilder
    public EllipticCurveType getCurveType() {
        EllipticCurveType forNumber = EllipticCurveType.forNumber(this.curveType_);
        return forNumber == null ? EllipticCurveType.UNRECOGNIZED : forNumber;
    }

    @Override // com.google.crypto.tink.proto.EciesHkdfKemParamsOrBuilder
    public int getCurveTypeValue() {
        return this.curveType_;
    }

    @Override // com.google.crypto.tink.proto.EciesHkdfKemParamsOrBuilder
    public HashType getHkdfHashType() {
        HashType forNumber = HashType.forNumber(this.hkdfHashType_);
        return forNumber == null ? HashType.UNRECOGNIZED : forNumber;
    }

    @Override // com.google.crypto.tink.proto.EciesHkdfKemParamsOrBuilder
    public int getHkdfHashTypeValue() {
        return this.hkdfHashType_;
    }

    @Override // com.google.crypto.tink.proto.EciesHkdfKemParamsOrBuilder
    public ByteString getHkdfSalt() {
        return this.hkdfSalt_;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object m(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AnonymousClass1.f9678a[methodToInvoke.ordinal()]) {
            case 1:
                return new EciesHkdfKemParams();
            case 2:
                return new Builder(null);
            case 3:
                return GeneratedMessageLite.v(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u000b\u0003\u0000\u0000\u0000\u0001\f\u0002\f\u000b\n", new Object[]{"curveType_", "hkdfHashType_", "hkdfSalt_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<EciesHkdfKemParams> parser = PARSER;
                if (parser == null) {
                    synchronized (EciesHkdfKemParams.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
