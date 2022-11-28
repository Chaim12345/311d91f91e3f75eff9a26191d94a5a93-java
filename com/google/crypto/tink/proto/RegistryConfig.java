package com.google.crypto.tink.proto;

import com.google.crypto.tink.proto.KeyTypeEntry;
import com.google.crypto.tink.shaded.protobuf.AbstractMessageLite;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.CodedInputStream;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import com.google.crypto.tink.shaded.protobuf.Internal;
import com.google.crypto.tink.shaded.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
@Deprecated
/* loaded from: classes2.dex */
public final class RegistryConfig extends GeneratedMessageLite<RegistryConfig, Builder> implements RegistryConfigOrBuilder {
    public static final int CONFIG_NAME_FIELD_NUMBER = 1;
    private static final RegistryConfig DEFAULT_INSTANCE;
    public static final int ENTRY_FIELD_NUMBER = 2;
    private static volatile Parser<RegistryConfig> PARSER;
    private String configName_ = "";
    private Internal.ProtobufList<KeyTypeEntry> entry_ = GeneratedMessageLite.n();

    /* renamed from: com.google.crypto.tink.proto.RegistryConfig$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9706a;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f9706a = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9706a[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9706a[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9706a[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9706a[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9706a[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9706a[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* loaded from: classes2.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<RegistryConfig, Builder> implements RegistryConfigOrBuilder {
        private Builder() {
            super(RegistryConfig.DEFAULT_INSTANCE);
        }

        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        public Builder addAllEntry(Iterable<? extends KeyTypeEntry> iterable) {
            d();
            ((RegistryConfig) this.f9750a).addAllEntry(iterable);
            return this;
        }

        public Builder addEntry(int i2, KeyTypeEntry.Builder builder) {
            d();
            ((RegistryConfig) this.f9750a).addEntry(i2, builder.build());
            return this;
        }

        public Builder addEntry(int i2, KeyTypeEntry keyTypeEntry) {
            d();
            ((RegistryConfig) this.f9750a).addEntry(i2, keyTypeEntry);
            return this;
        }

        public Builder addEntry(KeyTypeEntry.Builder builder) {
            d();
            ((RegistryConfig) this.f9750a).addEntry(builder.build());
            return this;
        }

        public Builder addEntry(KeyTypeEntry keyTypeEntry) {
            d();
            ((RegistryConfig) this.f9750a).addEntry(keyTypeEntry);
            return this;
        }

        public Builder clearConfigName() {
            d();
            ((RegistryConfig) this.f9750a).clearConfigName();
            return this;
        }

        public Builder clearEntry() {
            d();
            ((RegistryConfig) this.f9750a).clearEntry();
            return this;
        }

        @Override // com.google.crypto.tink.proto.RegistryConfigOrBuilder
        public String getConfigName() {
            return ((RegistryConfig) this.f9750a).getConfigName();
        }

        @Override // com.google.crypto.tink.proto.RegistryConfigOrBuilder
        public ByteString getConfigNameBytes() {
            return ((RegistryConfig) this.f9750a).getConfigNameBytes();
        }

        @Override // com.google.crypto.tink.proto.RegistryConfigOrBuilder
        public KeyTypeEntry getEntry(int i2) {
            return ((RegistryConfig) this.f9750a).getEntry(i2);
        }

        @Override // com.google.crypto.tink.proto.RegistryConfigOrBuilder
        public int getEntryCount() {
            return ((RegistryConfig) this.f9750a).getEntryCount();
        }

        @Override // com.google.crypto.tink.proto.RegistryConfigOrBuilder
        public List<KeyTypeEntry> getEntryList() {
            return Collections.unmodifiableList(((RegistryConfig) this.f9750a).getEntryList());
        }

        public Builder removeEntry(int i2) {
            d();
            ((RegistryConfig) this.f9750a).removeEntry(i2);
            return this;
        }

        public Builder setConfigName(String str) {
            d();
            ((RegistryConfig) this.f9750a).setConfigName(str);
            return this;
        }

        public Builder setConfigNameBytes(ByteString byteString) {
            d();
            ((RegistryConfig) this.f9750a).setConfigNameBytes(byteString);
            return this;
        }

        public Builder setEntry(int i2, KeyTypeEntry.Builder builder) {
            d();
            ((RegistryConfig) this.f9750a).setEntry(i2, builder.build());
            return this;
        }

        public Builder setEntry(int i2, KeyTypeEntry keyTypeEntry) {
            d();
            ((RegistryConfig) this.f9750a).setEntry(i2, keyTypeEntry);
            return this;
        }
    }

    static {
        RegistryConfig registryConfig = new RegistryConfig();
        DEFAULT_INSTANCE = registryConfig;
        GeneratedMessageLite.L(RegistryConfig.class, registryConfig);
    }

    private RegistryConfig() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllEntry(Iterable<? extends KeyTypeEntry> iterable) {
        ensureEntryIsMutable();
        AbstractMessageLite.a(iterable, this.entry_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addEntry(int i2, KeyTypeEntry keyTypeEntry) {
        keyTypeEntry.getClass();
        ensureEntryIsMutable();
        this.entry_.add(i2, keyTypeEntry);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addEntry(KeyTypeEntry keyTypeEntry) {
        keyTypeEntry.getClass();
        ensureEntryIsMutable();
        this.entry_.add(keyTypeEntry);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearConfigName() {
        this.configName_ = getDefaultInstance().getConfigName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearEntry() {
        this.entry_ = GeneratedMessageLite.n();
    }

    private void ensureEntryIsMutable() {
        if (this.entry_.isModifiable()) {
            return;
        }
        this.entry_ = GeneratedMessageLite.u(this.entry_);
    }

    public static RegistryConfig getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.i();
    }

    public static Builder newBuilder(RegistryConfig registryConfig) {
        return (Builder) DEFAULT_INSTANCE.j(registryConfig);
    }

    public static RegistryConfig parseDelimitedFrom(InputStream inputStream) {
        return (RegistryConfig) GeneratedMessageLite.w(DEFAULT_INSTANCE, inputStream);
    }

    public static RegistryConfig parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (RegistryConfig) GeneratedMessageLite.x(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RegistryConfig parseFrom(ByteString byteString) {
        return (RegistryConfig) GeneratedMessageLite.y(DEFAULT_INSTANCE, byteString);
    }

    public static RegistryConfig parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (RegistryConfig) GeneratedMessageLite.z(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static RegistryConfig parseFrom(CodedInputStream codedInputStream) {
        return (RegistryConfig) GeneratedMessageLite.A(DEFAULT_INSTANCE, codedInputStream);
    }

    public static RegistryConfig parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (RegistryConfig) GeneratedMessageLite.B(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static RegistryConfig parseFrom(InputStream inputStream) {
        return (RegistryConfig) GeneratedMessageLite.C(DEFAULT_INSTANCE, inputStream);
    }

    public static RegistryConfig parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (RegistryConfig) GeneratedMessageLite.D(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RegistryConfig parseFrom(ByteBuffer byteBuffer) {
        return (RegistryConfig) GeneratedMessageLite.E(DEFAULT_INSTANCE, byteBuffer);
    }

    public static RegistryConfig parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (RegistryConfig) GeneratedMessageLite.F(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static RegistryConfig parseFrom(byte[] bArr) {
        return (RegistryConfig) GeneratedMessageLite.G(DEFAULT_INSTANCE, bArr);
    }

    public static RegistryConfig parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (RegistryConfig) GeneratedMessageLite.H(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Parser<RegistryConfig> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeEntry(int i2) {
        ensureEntryIsMutable();
        this.entry_.remove(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setConfigName(String str) {
        str.getClass();
        this.configName_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setConfigNameBytes(ByteString byteString) {
        AbstractMessageLite.b(byteString);
        this.configName_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEntry(int i2, KeyTypeEntry keyTypeEntry) {
        keyTypeEntry.getClass();
        ensureEntryIsMutable();
        this.entry_.set(i2, keyTypeEntry);
    }

    @Override // com.google.crypto.tink.proto.RegistryConfigOrBuilder
    public String getConfigName() {
        return this.configName_;
    }

    @Override // com.google.crypto.tink.proto.RegistryConfigOrBuilder
    public ByteString getConfigNameBytes() {
        return ByteString.copyFromUtf8(this.configName_);
    }

    @Override // com.google.crypto.tink.proto.RegistryConfigOrBuilder
    public KeyTypeEntry getEntry(int i2) {
        return this.entry_.get(i2);
    }

    @Override // com.google.crypto.tink.proto.RegistryConfigOrBuilder
    public int getEntryCount() {
        return this.entry_.size();
    }

    @Override // com.google.crypto.tink.proto.RegistryConfigOrBuilder
    public List<KeyTypeEntry> getEntryList() {
        return this.entry_;
    }

    public KeyTypeEntryOrBuilder getEntryOrBuilder(int i2) {
        return this.entry_.get(i2);
    }

    public List<? extends KeyTypeEntryOrBuilder> getEntryOrBuilderList() {
        return this.entry_;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object m(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AnonymousClass1.f9706a[methodToInvoke.ordinal()]) {
            case 1:
                return new RegistryConfig();
            case 2:
                return new Builder(null);
            case 3:
                return GeneratedMessageLite.v(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ȉ\u0002\u001b", new Object[]{"configName_", "entry_", KeyTypeEntry.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<RegistryConfig> parser = PARSER;
                if (parser == null) {
                    synchronized (RegistryConfig.class) {
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
