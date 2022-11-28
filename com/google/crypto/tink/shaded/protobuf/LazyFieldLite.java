package com.google.crypto.tink.shaded.protobuf;

import java.util.Objects;
/* loaded from: classes2.dex */
public class LazyFieldLite {
    private static final ExtensionRegistryLite EMPTY_REGISTRY = ExtensionRegistryLite.getEmptyRegistry();

    /* renamed from: a  reason: collision with root package name */
    protected volatile MessageLite f9769a;
    private ByteString delayedBytes;
    private ExtensionRegistryLite extensionRegistry;
    private volatile ByteString memoizedBytes;

    public LazyFieldLite() {
    }

    public LazyFieldLite(ExtensionRegistryLite extensionRegistryLite, ByteString byteString) {
        checkArguments(extensionRegistryLite, byteString);
        this.extensionRegistry = extensionRegistryLite;
        this.delayedBytes = byteString;
    }

    private static void checkArguments(ExtensionRegistryLite extensionRegistryLite, ByteString byteString) {
        Objects.requireNonNull(extensionRegistryLite, "found null ExtensionRegistry");
        Objects.requireNonNull(byteString, "found null ByteString");
    }

    public static LazyFieldLite fromValue(MessageLite messageLite) {
        LazyFieldLite lazyFieldLite = new LazyFieldLite();
        lazyFieldLite.setValue(messageLite);
        return lazyFieldLite;
    }

    private static MessageLite mergeValueAndBytes(MessageLite messageLite, ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        try {
            return messageLite.toBuilder().mergeFrom(byteString, extensionRegistryLite).build();
        } catch (InvalidProtocolBufferException unused) {
            return messageLite;
        }
    }

    protected void a(MessageLite messageLite) {
        ByteString byteString;
        if (this.f9769a != null) {
            return;
        }
        synchronized (this) {
            if (this.f9769a != null) {
                return;
            }
            try {
                if (this.delayedBytes != null) {
                    this.f9769a = messageLite.getParserForType().parseFrom(this.delayedBytes, this.extensionRegistry);
                    byteString = this.delayedBytes;
                } else {
                    this.f9769a = messageLite;
                    byteString = ByteString.EMPTY;
                }
                this.memoizedBytes = byteString;
            } catch (InvalidProtocolBufferException unused) {
                this.f9769a = messageLite;
                this.memoizedBytes = ByteString.EMPTY;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(Writer writer, int i2) {
        ByteString byteString;
        if (this.memoizedBytes != null) {
            byteString = this.memoizedBytes;
        } else {
            byteString = this.delayedBytes;
            if (byteString == null) {
                if (this.f9769a != null) {
                    writer.writeMessage(i2, this.f9769a);
                    return;
                }
                byteString = ByteString.EMPTY;
            }
        }
        writer.writeBytes(i2, byteString);
    }

    public void clear() {
        this.delayedBytes = null;
        this.f9769a = null;
        this.memoizedBytes = null;
    }

    public boolean containsDefaultInstance() {
        ByteString byteString;
        ByteString byteString2 = this.memoizedBytes;
        ByteString byteString3 = ByteString.EMPTY;
        return byteString2 == byteString3 || (this.f9769a == null && ((byteString = this.delayedBytes) == null || byteString == byteString3));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LazyFieldLite) {
            LazyFieldLite lazyFieldLite = (LazyFieldLite) obj;
            MessageLite messageLite = this.f9769a;
            MessageLite messageLite2 = lazyFieldLite.f9769a;
            return (messageLite == null && messageLite2 == null) ? toByteString().equals(lazyFieldLite.toByteString()) : (messageLite == null || messageLite2 == null) ? messageLite != null ? messageLite.equals(lazyFieldLite.getValue(messageLite.getDefaultInstanceForType())) : getValue(messageLite2.getDefaultInstanceForType()).equals(messageLite2) : messageLite.equals(messageLite2);
        }
        return false;
    }

    public int getSerializedSize() {
        if (this.memoizedBytes != null) {
            return this.memoizedBytes.size();
        }
        ByteString byteString = this.delayedBytes;
        if (byteString != null) {
            return byteString.size();
        }
        if (this.f9769a != null) {
            return this.f9769a.getSerializedSize();
        }
        return 0;
    }

    public MessageLite getValue(MessageLite messageLite) {
        a(messageLite);
        return this.f9769a;
    }

    public int hashCode() {
        return 1;
    }

    public void merge(LazyFieldLite lazyFieldLite) {
        ByteString byteString;
        if (lazyFieldLite.containsDefaultInstance()) {
            return;
        }
        if (containsDefaultInstance()) {
            set(lazyFieldLite);
            return;
        }
        if (this.extensionRegistry == null) {
            this.extensionRegistry = lazyFieldLite.extensionRegistry;
        }
        ByteString byteString2 = this.delayedBytes;
        if (byteString2 != null && (byteString = lazyFieldLite.delayedBytes) != null) {
            this.delayedBytes = byteString2.concat(byteString);
        } else if (this.f9769a == null && lazyFieldLite.f9769a != null) {
            setValue(mergeValueAndBytes(lazyFieldLite.f9769a, this.delayedBytes, this.extensionRegistry));
        } else if (this.f9769a == null || lazyFieldLite.f9769a != null) {
            setValue(this.f9769a.toBuilder().mergeFrom(lazyFieldLite.f9769a).build());
        } else {
            setValue(mergeValueAndBytes(this.f9769a, lazyFieldLite.delayedBytes, lazyFieldLite.extensionRegistry));
        }
    }

    public void mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        ByteString concat;
        if (containsDefaultInstance()) {
            concat = codedInputStream.readBytes();
        } else {
            if (this.extensionRegistry == null) {
                this.extensionRegistry = extensionRegistryLite;
            }
            ByteString byteString = this.delayedBytes;
            if (byteString == null) {
                try {
                    setValue(this.f9769a.toBuilder().mergeFrom(codedInputStream, extensionRegistryLite).build());
                    return;
                } catch (InvalidProtocolBufferException unused) {
                    return;
                }
            }
            concat = byteString.concat(codedInputStream.readBytes());
            extensionRegistryLite = this.extensionRegistry;
        }
        setByteString(concat, extensionRegistryLite);
    }

    public void set(LazyFieldLite lazyFieldLite) {
        this.delayedBytes = lazyFieldLite.delayedBytes;
        this.f9769a = lazyFieldLite.f9769a;
        this.memoizedBytes = lazyFieldLite.memoizedBytes;
        ExtensionRegistryLite extensionRegistryLite = lazyFieldLite.extensionRegistry;
        if (extensionRegistryLite != null) {
            this.extensionRegistry = extensionRegistryLite;
        }
    }

    public void setByteString(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        checkArguments(extensionRegistryLite, byteString);
        this.delayedBytes = byteString;
        this.extensionRegistry = extensionRegistryLite;
        this.f9769a = null;
        this.memoizedBytes = null;
    }

    public MessageLite setValue(MessageLite messageLite) {
        MessageLite messageLite2 = this.f9769a;
        this.delayedBytes = null;
        this.memoizedBytes = null;
        this.f9769a = messageLite;
        return messageLite2;
    }

    public ByteString toByteString() {
        if (this.memoizedBytes != null) {
            return this.memoizedBytes;
        }
        ByteString byteString = this.delayedBytes;
        if (byteString != null) {
            return byteString;
        }
        synchronized (this) {
            if (this.memoizedBytes != null) {
                return this.memoizedBytes;
            }
            this.memoizedBytes = this.f9769a == null ? ByteString.EMPTY : this.f9769a.toByteString();
            return this.memoizedBytes;
        }
    }
}
