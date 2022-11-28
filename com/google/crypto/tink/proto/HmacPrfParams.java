package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.CodedInputStream;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import com.google.crypto.tink.shaded.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes2.dex */
public final class HmacPrfParams extends GeneratedMessageLite<HmacPrfParams, Builder> implements HmacPrfParamsOrBuilder {
    private static final HmacPrfParams DEFAULT_INSTANCE;
    public static final int HASH_FIELD_NUMBER = 1;
    private static volatile Parser<HmacPrfParams> PARSER;
    private int hash_;

    /* renamed from: com.google.crypto.tink.proto.HmacPrfParams$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9693a;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f9693a = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9693a[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9693a[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9693a[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9693a[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9693a[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9693a[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* loaded from: classes2.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<HmacPrfParams, Builder> implements HmacPrfParamsOrBuilder {
        private Builder() {
            super(HmacPrfParams.DEFAULT_INSTANCE);
        }

        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        public Builder clearHash() {
            d();
            ((HmacPrfParams) this.f9750a).clearHash();
            return this;
        }

        @Override // com.google.crypto.tink.proto.HmacPrfParamsOrBuilder
        public HashType getHash() {
            return ((HmacPrfParams) this.f9750a).getHash();
        }

        @Override // com.google.crypto.tink.proto.HmacPrfParamsOrBuilder
        public int getHashValue() {
            return ((HmacPrfParams) this.f9750a).getHashValue();
        }

        public Builder setHash(HashType hashType) {
            d();
            ((HmacPrfParams) this.f9750a).setHash(hashType);
            return this;
        }

        public Builder setHashValue(int i2) {
            d();
            ((HmacPrfParams) this.f9750a).setHashValue(i2);
            return this;
        }
    }

    static {
        HmacPrfParams hmacPrfParams = new HmacPrfParams();
        DEFAULT_INSTANCE = hmacPrfParams;
        GeneratedMessageLite.L(HmacPrfParams.class, hmacPrfParams);
    }

    private HmacPrfParams() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearHash() {
        this.hash_ = 0;
    }

    public static HmacPrfParams getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.i();
    }

    public static Builder newBuilder(HmacPrfParams hmacPrfParams) {
        return (Builder) DEFAULT_INSTANCE.j(hmacPrfParams);
    }

    public static HmacPrfParams parseDelimitedFrom(InputStream inputStream) {
        return (HmacPrfParams) GeneratedMessageLite.w(DEFAULT_INSTANCE, inputStream);
    }

    public static HmacPrfParams parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (HmacPrfParams) GeneratedMessageLite.x(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static HmacPrfParams parseFrom(ByteString byteString) {
        return (HmacPrfParams) GeneratedMessageLite.y(DEFAULT_INSTANCE, byteString);
    }

    public static HmacPrfParams parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (HmacPrfParams) GeneratedMessageLite.z(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static HmacPrfParams parseFrom(CodedInputStream codedInputStream) {
        return (HmacPrfParams) GeneratedMessageLite.A(DEFAULT_INSTANCE, codedInputStream);
    }

    public static HmacPrfParams parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (HmacPrfParams) GeneratedMessageLite.B(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static HmacPrfParams parseFrom(InputStream inputStream) {
        return (HmacPrfParams) GeneratedMessageLite.C(DEFAULT_INSTANCE, inputStream);
    }

    public static HmacPrfParams parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (HmacPrfParams) GeneratedMessageLite.D(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static HmacPrfParams parseFrom(ByteBuffer byteBuffer) {
        return (HmacPrfParams) GeneratedMessageLite.E(DEFAULT_INSTANCE, byteBuffer);
    }

    public static HmacPrfParams parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (HmacPrfParams) GeneratedMessageLite.F(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static HmacPrfParams parseFrom(byte[] bArr) {
        return (HmacPrfParams) GeneratedMessageLite.G(DEFAULT_INSTANCE, bArr);
    }

    public static HmacPrfParams parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (HmacPrfParams) GeneratedMessageLite.H(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Parser<HmacPrfParams> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHash(HashType hashType) {
        this.hash_ = hashType.getNumber();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHashValue(int i2) {
        this.hash_ = i2;
    }

    @Override // com.google.crypto.tink.proto.HmacPrfParamsOrBuilder
    public HashType getHash() {
        HashType forNumber = HashType.forNumber(this.hash_);
        return forNumber == null ? HashType.UNRECOGNIZED : forNumber;
    }

    @Override // com.google.crypto.tink.proto.HmacPrfParamsOrBuilder
    public int getHashValue() {
        return this.hash_;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object m(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AnonymousClass1.f9693a[methodToInvoke.ordinal()]) {
            case 1:
                return new HmacPrfParams();
            case 2:
                return new Builder(null);
            case 3:
                return GeneratedMessageLite.v(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\f", new Object[]{"hash_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<HmacPrfParams> parser = PARSER;
                if (parser == null) {
                    synchronized (HmacPrfParams.class) {
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
