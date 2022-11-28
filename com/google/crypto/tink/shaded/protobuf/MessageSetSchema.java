package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.ArrayDecoders;
import com.google.crypto.tink.shaded.protobuf.FieldSet;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import com.google.crypto.tink.shaded.protobuf.LazyField;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes2.dex */
final class MessageSetSchema<T> implements Schema<T> {
    private final MessageLite defaultInstance;
    private final ExtensionSchema<?> extensionSchema;
    private final boolean hasExtensions;
    private final UnknownFieldSchema<?, ?> unknownFieldSchema;

    private MessageSetSchema(UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MessageLite messageLite) {
        this.unknownFieldSchema = unknownFieldSchema;
        this.hasExtensions = extensionSchema.e(messageLite);
        this.extensionSchema = extensionSchema;
        this.defaultInstance = messageLite;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MessageSetSchema a(UnknownFieldSchema unknownFieldSchema, ExtensionSchema extensionSchema, MessageLite messageLite) {
        return new MessageSetSchema(unknownFieldSchema, extensionSchema, messageLite);
    }

    private <UT, UB> int getUnknownFieldsSerializedSize(UnknownFieldSchema<UT, UB> unknownFieldSchema, T t2) {
        return unknownFieldSchema.i(unknownFieldSchema.g(t2));
    }

    private <UT, UB, ET extends FieldSet.FieldDescriptorLite<ET>> void mergeFromHelper(UnknownFieldSchema<UT, UB> unknownFieldSchema, ExtensionSchema<ET> extensionSchema, T t2, Reader reader, ExtensionRegistryLite extensionRegistryLite) {
        Object f2 = unknownFieldSchema.f(t2);
        FieldSet<ET> d2 = extensionSchema.d(t2);
        do {
            try {
                if (reader.getFieldNumber() == Integer.MAX_VALUE) {
                    return;
                }
            } finally {
                unknownFieldSchema.o(t2, f2);
            }
        } while (parseMessageSetItemOrUnknownField(reader, extensionRegistryLite, extensionSchema, d2, unknownFieldSchema, f2));
    }

    private <UT, UB, ET extends FieldSet.FieldDescriptorLite<ET>> boolean parseMessageSetItemOrUnknownField(Reader reader, ExtensionRegistryLite extensionRegistryLite, ExtensionSchema<ET> extensionSchema, FieldSet<ET> fieldSet, UnknownFieldSchema<UT, UB> unknownFieldSchema, UB ub) {
        int tag = reader.getTag();
        if (tag != WireFormat.f9793a) {
            if (WireFormat.getTagWireType(tag) == 2) {
                Object b2 = extensionSchema.b(extensionRegistryLite, this.defaultInstance, WireFormat.getTagFieldNumber(tag));
                if (b2 != null) {
                    extensionSchema.h(reader, b2, extensionRegistryLite, fieldSet);
                    return true;
                }
                return unknownFieldSchema.m(ub, reader);
            }
            return reader.skipField();
        }
        int i2 = 0;
        Object obj = null;
        ByteString byteString = null;
        while (reader.getFieldNumber() != Integer.MAX_VALUE) {
            int tag2 = reader.getTag();
            if (tag2 == WireFormat.f9795c) {
                i2 = reader.readUInt32();
                obj = extensionSchema.b(extensionRegistryLite, this.defaultInstance, i2);
            } else if (tag2 == WireFormat.f9796d) {
                if (obj != null) {
                    extensionSchema.h(reader, obj, extensionRegistryLite, fieldSet);
                } else {
                    byteString = reader.readBytes();
                }
            } else if (!reader.skipField()) {
                break;
            }
        }
        if (reader.getTag() == WireFormat.f9794b) {
            if (byteString != null) {
                if (obj != null) {
                    extensionSchema.i(byteString, obj, extensionRegistryLite, fieldSet);
                } else {
                    unknownFieldSchema.d(ub, i2, byteString);
                }
            }
            return true;
        }
        throw InvalidProtocolBufferException.a();
    }

    private <UT, UB> void writeUnknownFieldsHelper(UnknownFieldSchema<UT, UB> unknownFieldSchema, T t2, Writer writer) {
        unknownFieldSchema.s(unknownFieldSchema.g(t2), writer);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public boolean equals(T t2, T t3) {
        if (this.unknownFieldSchema.g(t2).equals(this.unknownFieldSchema.g(t3))) {
            if (this.hasExtensions) {
                return this.extensionSchema.c(t2).equals(this.extensionSchema.c(t3));
            }
            return true;
        }
        return false;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public int getSerializedSize(T t2) {
        int unknownFieldsSerializedSize = getUnknownFieldsSerializedSize(this.unknownFieldSchema, t2) + 0;
        return this.hasExtensions ? unknownFieldsSerializedSize + this.extensionSchema.c(t2).getMessageSetSerializedSize() : unknownFieldsSerializedSize;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public int hashCode(T t2) {
        int hashCode = this.unknownFieldSchema.g(t2).hashCode();
        return this.hasExtensions ? (hashCode * 53) + this.extensionSchema.c(t2).hashCode() : hashCode;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public final boolean isInitialized(T t2) {
        return this.extensionSchema.c(t2).isInitialized();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void makeImmutable(T t2) {
        this.unknownFieldSchema.j(t2);
        this.extensionSchema.f(t2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void mergeFrom(T t2, Reader reader, ExtensionRegistryLite extensionRegistryLite) {
        mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, t2, reader, extensionRegistryLite);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void mergeFrom(T t2, T t3) {
        SchemaUtil.D(this.unknownFieldSchema, t2, t3);
        if (this.hasExtensions) {
            SchemaUtil.B(this.extensionSchema, t2, t3);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00cb A[EDGE_INSN: B:57:0x00cb->B:34:0x00cb ?: BREAK  , SYNTHETIC] */
    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void mergeFrom(T t2, byte[] bArr, int i2, int i3, ArrayDecoders.Registers registers) {
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) t2;
        UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
        if (unknownFieldSetLite == UnknownFieldSetLite.getDefaultInstance()) {
            unknownFieldSetLite = UnknownFieldSetLite.f();
            generatedMessageLite.unknownFields = unknownFieldSetLite;
        }
        FieldSet M = ((GeneratedMessageLite.ExtendableMessage) t2).M();
        GeneratedMessageLite.GeneratedExtension generatedExtension = null;
        while (i2 < i3) {
            int I = ArrayDecoders.I(bArr, i2, registers);
            int i4 = registers.int1;
            if (i4 == WireFormat.f9793a) {
                int i5 = 0;
                ByteString byteString = null;
                while (I < i3) {
                    I = ArrayDecoders.I(bArr, I, registers);
                    int i6 = registers.int1;
                    int tagFieldNumber = WireFormat.getTagFieldNumber(i6);
                    int tagWireType = WireFormat.getTagWireType(i6);
                    if (tagFieldNumber != 2) {
                        if (tagFieldNumber == 3) {
                            if (generatedExtension != null) {
                                I = ArrayDecoders.p(Protobuf.getInstance().schemaFor((Class) generatedExtension.getMessageDefaultInstance().getClass()), bArr, I, i3, registers);
                                M.setField(generatedExtension.f9761d, registers.object1);
                            } else if (tagWireType == 2) {
                                I = ArrayDecoders.b(bArr, I, registers);
                                byteString = (ByteString) registers.object1;
                            }
                        }
                        if (i6 != WireFormat.f9794b) {
                            break;
                        }
                        I = ArrayDecoders.N(i6, bArr, I, i3, registers);
                    } else if (tagWireType == 0) {
                        I = ArrayDecoders.I(bArr, I, registers);
                        i5 = registers.int1;
                        generatedExtension = (GeneratedMessageLite.GeneratedExtension) this.extensionSchema.b(registers.extensionRegistry, this.defaultInstance, i5);
                    } else if (i6 != WireFormat.f9794b) {
                    }
                }
                if (byteString != null) {
                    unknownFieldSetLite.h(WireFormat.a(i5, 2), byteString);
                }
                i2 = I;
            } else if (WireFormat.getTagWireType(i4) == 2) {
                GeneratedMessageLite.GeneratedExtension generatedExtension2 = (GeneratedMessageLite.GeneratedExtension) this.extensionSchema.b(registers.extensionRegistry, this.defaultInstance, WireFormat.getTagFieldNumber(i4));
                if (generatedExtension2 != null) {
                    i2 = ArrayDecoders.p(Protobuf.getInstance().schemaFor((Class) generatedExtension2.getMessageDefaultInstance().getClass()), bArr, I, i3, registers);
                    M.setField(generatedExtension2.f9761d, registers.object1);
                } else {
                    i2 = ArrayDecoders.G(i4, bArr, I, i3, unknownFieldSetLite, registers);
                }
                generatedExtension = generatedExtension2;
            } else {
                i2 = ArrayDecoders.N(i4, bArr, I, i3, registers);
            }
        }
        if (i2 != i3) {
            throw InvalidProtocolBufferException.g();
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public T newInstance() {
        return (T) this.defaultInstance.newBuilderForType().buildPartial();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void writeTo(T t2, Writer writer) {
        Iterator it = this.extensionSchema.c(t2).iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            FieldSet.FieldDescriptorLite fieldDescriptorLite = (FieldSet.FieldDescriptorLite) entry.getKey();
            if (fieldDescriptorLite.getLiteJavaType() != WireFormat.JavaType.MESSAGE || fieldDescriptorLite.isRepeated() || fieldDescriptorLite.isPacked()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            writer.writeMessageSetItem(fieldDescriptorLite.getNumber(), entry instanceof LazyField.LazyEntry ? ((LazyField.LazyEntry) entry).getField().toByteString() : entry.getValue());
        }
        writeUnknownFieldsHelper(this.unknownFieldSchema, t2, writer);
    }
}
