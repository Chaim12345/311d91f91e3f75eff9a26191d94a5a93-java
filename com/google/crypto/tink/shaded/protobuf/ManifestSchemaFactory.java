package com.google.crypto.tink.shaded.protobuf;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class ManifestSchemaFactory implements SchemaFactory {
    private static final MessageInfoFactory EMPTY_FACTORY = new MessageInfoFactory() { // from class: com.google.crypto.tink.shaded.protobuf.ManifestSchemaFactory.1
        @Override // com.google.crypto.tink.shaded.protobuf.MessageInfoFactory
        public boolean isSupported(Class<?> cls) {
            return false;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.MessageInfoFactory
        public MessageInfo messageInfoFor(Class<?> cls) {
            throw new IllegalStateException("This should never be called.");
        }
    };
    private final MessageInfoFactory messageInfoFactory;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class CompositeMessageInfoFactory implements MessageInfoFactory {
        private MessageInfoFactory[] factories;

        CompositeMessageInfoFactory(MessageInfoFactory... messageInfoFactoryArr) {
            this.factories = messageInfoFactoryArr;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.MessageInfoFactory
        public boolean isSupported(Class<?> cls) {
            for (MessageInfoFactory messageInfoFactory : this.factories) {
                if (messageInfoFactory.isSupported(cls)) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.MessageInfoFactory
        public MessageInfo messageInfoFor(Class<?> cls) {
            MessageInfoFactory[] messageInfoFactoryArr;
            for (MessageInfoFactory messageInfoFactory : this.factories) {
                if (messageInfoFactory.isSupported(cls)) {
                    return messageInfoFactory.messageInfoFor(cls);
                }
            }
            throw new UnsupportedOperationException("No factory is available for message type: " + cls.getName());
        }
    }

    public ManifestSchemaFactory() {
        this(getDefaultMessageInfoFactory());
    }

    private ManifestSchemaFactory(MessageInfoFactory messageInfoFactory) {
        this.messageInfoFactory = (MessageInfoFactory) Internal.b(messageInfoFactory, "messageInfoFactory");
    }

    private static MessageInfoFactory getDefaultMessageInfoFactory() {
        return new CompositeMessageInfoFactory(GeneratedMessageInfoFactory.getInstance(), getDescriptorMessageInfoFactory());
    }

    private static MessageInfoFactory getDescriptorMessageInfoFactory() {
        try {
            return (MessageInfoFactory) Class.forName("com.google.crypto.tink.shaded.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return EMPTY_FACTORY;
        }
    }

    private static boolean isProto2(MessageInfo messageInfo) {
        return messageInfo.getSyntax() == ProtoSyntax.PROTO2;
    }

    private static <T> Schema<T> newSchema(Class<T> cls, MessageInfo messageInfo) {
        return GeneratedMessageLite.class.isAssignableFrom(cls) ? isProto2(messageInfo) ? MessageSchema.b(cls, messageInfo, NewInstanceSchemas.b(), ListFieldSchema.b(), SchemaUtil.unknownFieldSetLiteSchema(), ExtensionSchemas.b(), MapFieldSchemas.b()) : MessageSchema.b(cls, messageInfo, NewInstanceSchemas.b(), ListFieldSchema.b(), SchemaUtil.unknownFieldSetLiteSchema(), null, MapFieldSchemas.b()) : isProto2(messageInfo) ? MessageSchema.b(cls, messageInfo, NewInstanceSchemas.a(), ListFieldSchema.a(), SchemaUtil.proto2UnknownFieldSetSchema(), ExtensionSchemas.a(), MapFieldSchemas.a()) : MessageSchema.b(cls, messageInfo, NewInstanceSchemas.a(), ListFieldSchema.a(), SchemaUtil.proto3UnknownFieldSetSchema(), null, MapFieldSchemas.a());
    }

    @Override // com.google.crypto.tink.shaded.protobuf.SchemaFactory
    public <T> Schema<T> createSchema(Class<T> cls) {
        UnknownFieldSchema<?, ?> proto2UnknownFieldSetSchema;
        ExtensionSchema a2;
        SchemaUtil.requireGeneratedMessage(cls);
        MessageInfo messageInfoFor = this.messageInfoFactory.messageInfoFor(cls);
        if (messageInfoFor.isMessageSetWireFormat()) {
            if (GeneratedMessageLite.class.isAssignableFrom(cls)) {
                proto2UnknownFieldSetSchema = SchemaUtil.unknownFieldSetLiteSchema();
                a2 = ExtensionSchemas.b();
            } else {
                proto2UnknownFieldSetSchema = SchemaUtil.proto2UnknownFieldSetSchema();
                a2 = ExtensionSchemas.a();
            }
            return MessageSetSchema.a(proto2UnknownFieldSetSchema, a2, messageInfoFor.getDefaultInstance());
        }
        return newSchema(cls, messageInfoFor);
    }
}
