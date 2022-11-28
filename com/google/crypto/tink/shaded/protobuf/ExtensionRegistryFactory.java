package com.google.crypto.tink.shaded.protobuf;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class ExtensionRegistryFactory {

    /* renamed from: a  reason: collision with root package name */
    static final Class f9740a = b();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(ExtensionRegistryLite extensionRegistryLite) {
        Class cls = f9740a;
        return cls != null && cls.isAssignableFrom(extensionRegistryLite.getClass());
    }

    static Class b() {
        try {
            return Class.forName("com.google.crypto.tink.shaded.protobuf.ExtensionRegistry");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static ExtensionRegistryLite create() {
        ExtensionRegistryLite invokeSubclassFactory = invokeSubclassFactory("newInstance");
        return invokeSubclassFactory != null ? invokeSubclassFactory : new ExtensionRegistryLite();
    }

    public static ExtensionRegistryLite createEmpty() {
        ExtensionRegistryLite invokeSubclassFactory = invokeSubclassFactory("getEmptyRegistry");
        return invokeSubclassFactory != null ? invokeSubclassFactory : ExtensionRegistryLite.f9741a;
    }

    private static final ExtensionRegistryLite invokeSubclassFactory(String str) {
        Class cls = f9740a;
        if (cls == null) {
            return null;
        }
        try {
            return (ExtensionRegistryLite) cls.getDeclaredMethod(str, new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
