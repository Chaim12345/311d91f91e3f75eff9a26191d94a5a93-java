package com.google.crypto.tink.shaded.protobuf;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class Protobuf {
    private static final Protobuf INSTANCE = new Protobuf();
    private final ConcurrentMap<Class<?>, Schema<?>> schemaCache = new ConcurrentHashMap();
    private final SchemaFactory schemaFactory = new ManifestSchemaFactory();

    private Protobuf() {
    }

    public static Protobuf getInstance() {
        return INSTANCE;
    }

    public <T> boolean isInitialized(T t2) {
        return schemaFor((Protobuf) t2).isInitialized(t2);
    }

    public <T> void makeImmutable(T t2) {
        schemaFor((Protobuf) t2).makeImmutable(t2);
    }

    public <T> void mergeFrom(T t2, Reader reader) {
        mergeFrom(t2, reader, ExtensionRegistryLite.getEmptyRegistry());
    }

    public <T> void mergeFrom(T t2, Reader reader, ExtensionRegistryLite extensionRegistryLite) {
        schemaFor((Protobuf) t2).mergeFrom(t2, reader, extensionRegistryLite);
    }

    public Schema<?> registerSchema(Class<?> cls, Schema<?> schema) {
        Internal.b(cls, "messageType");
        Internal.b(schema, "schema");
        return this.schemaCache.putIfAbsent(cls, schema);
    }

    public Schema<?> registerSchemaOverride(Class<?> cls, Schema<?> schema) {
        Internal.b(cls, "messageType");
        Internal.b(schema, "schema");
        return this.schemaCache.put(cls, schema);
    }

    public <T> Schema<T> schemaFor(Class<T> cls) {
        Internal.b(cls, "messageType");
        Schema<T> schema = (Schema<T>) this.schemaCache.get(cls);
        if (schema == null) {
            Schema<T> createSchema = this.schemaFactory.createSchema(cls);
            Schema<T> schema2 = (Schema<T>) registerSchema(cls, createSchema);
            return schema2 != null ? schema2 : createSchema;
        }
        return schema;
    }

    public <T> Schema<T> schemaFor(T t2) {
        return schemaFor((Class) t2.getClass());
    }

    public <T> void writeTo(T t2, Writer writer) {
        schemaFor((Protobuf) t2).writeTo(t2, writer);
    }
}
