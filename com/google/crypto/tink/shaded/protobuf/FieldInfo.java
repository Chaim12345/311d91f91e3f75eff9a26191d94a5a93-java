package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.Internal;
import java.lang.reflect.Field;
/* loaded from: classes2.dex */
final class FieldInfo implements Comparable<FieldInfo> {
    private final Field cachedSizeField;
    private final boolean enforceUtf8;
    private final Internal.EnumVerifier enumVerifier;
    private final Field field;
    private final int fieldNumber;
    private final Object mapDefaultEntry;
    private final Class<?> messageClass;
    private final OneofInfo oneof;
    private final Class<?> oneofStoredType;
    private final Field presenceField;
    private final int presenceMask;
    private final boolean required;
    private final FieldType type;

    /* renamed from: com.google.crypto.tink.shaded.protobuf.FieldInfo$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9744a;

        static {
            int[] iArr = new int[FieldType.values().length];
            f9744a = iArr;
            try {
                iArr[FieldType.MESSAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9744a[FieldType.GROUP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9744a[FieldType.MESSAGE_LIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9744a[FieldType.GROUP_LIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        private Field cachedSizeField;
        private boolean enforceUtf8;
        private Internal.EnumVerifier enumVerifier;
        private Field field;
        private int fieldNumber;
        private Object mapDefaultEntry;
        private OneofInfo oneof;
        private Class<?> oneofStoredType;
        private Field presenceField;
        private int presenceMask;
        private boolean required;
        private FieldType type;

        private Builder() {
        }

        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        public FieldInfo build() {
            OneofInfo oneofInfo = this.oneof;
            if (oneofInfo != null) {
                return FieldInfo.forOneofMemberField(this.fieldNumber, this.type, oneofInfo, this.oneofStoredType, this.enforceUtf8, this.enumVerifier);
            }
            Object obj = this.mapDefaultEntry;
            if (obj != null) {
                return FieldInfo.forMapField(this.field, this.fieldNumber, obj, this.enumVerifier);
            }
            Field field = this.presenceField;
            if (field == null) {
                Internal.EnumVerifier enumVerifier = this.enumVerifier;
                if (enumVerifier != null) {
                    Field field2 = this.cachedSizeField;
                    return field2 == null ? FieldInfo.forFieldWithEnumVerifier(this.field, this.fieldNumber, this.type, enumVerifier) : FieldInfo.forPackedFieldWithEnumVerifier(this.field, this.fieldNumber, this.type, enumVerifier, field2);
                }
                Field field3 = this.cachedSizeField;
                return field3 == null ? FieldInfo.forField(this.field, this.fieldNumber, this.type, this.enforceUtf8) : FieldInfo.forPackedField(this.field, this.fieldNumber, this.type, field3);
            }
            boolean z = this.required;
            Field field4 = this.field;
            int i2 = this.fieldNumber;
            FieldType fieldType = this.type;
            int i3 = this.presenceMask;
            boolean z2 = this.enforceUtf8;
            Internal.EnumVerifier enumVerifier2 = this.enumVerifier;
            return z ? FieldInfo.forProto2RequiredField(field4, i2, fieldType, field, i3, z2, enumVerifier2) : FieldInfo.forProto2OptionalField(field4, i2, fieldType, field, i3, z2, enumVerifier2);
        }

        public Builder withCachedSizeField(Field field) {
            this.cachedSizeField = field;
            return this;
        }

        public Builder withEnforceUtf8(boolean z) {
            this.enforceUtf8 = z;
            return this;
        }

        public Builder withEnumVerifier(Internal.EnumVerifier enumVerifier) {
            this.enumVerifier = enumVerifier;
            return this;
        }

        public Builder withField(Field field) {
            if (this.oneof == null) {
                this.field = field;
                return this;
            }
            throw new IllegalStateException("Cannot set field when building a oneof.");
        }

        public Builder withFieldNumber(int i2) {
            this.fieldNumber = i2;
            return this;
        }

        public Builder withMapDefaultEntry(Object obj) {
            this.mapDefaultEntry = obj;
            return this;
        }

        public Builder withOneof(OneofInfo oneofInfo, Class<?> cls) {
            if (this.field == null && this.presenceField == null) {
                this.oneof = oneofInfo;
                this.oneofStoredType = cls;
                return this;
            }
            throw new IllegalStateException("Cannot set oneof when field or presenceField have been provided");
        }

        public Builder withPresence(Field field, int i2) {
            this.presenceField = (Field) Internal.b(field, "presenceField");
            this.presenceMask = i2;
            return this;
        }

        public Builder withRequired(boolean z) {
            this.required = z;
            return this;
        }

        public Builder withType(FieldType fieldType) {
            this.type = fieldType;
            return this;
        }
    }

    private FieldInfo(Field field, int i2, FieldType fieldType, Class<?> cls, Field field2, int i3, boolean z, boolean z2, OneofInfo oneofInfo, Class<?> cls2, Object obj, Internal.EnumVerifier enumVerifier, Field field3) {
        this.field = field;
        this.type = fieldType;
        this.messageClass = cls;
        this.fieldNumber = i2;
        this.presenceField = field2;
        this.presenceMask = i3;
        this.required = z;
        this.enforceUtf8 = z2;
        this.oneof = oneofInfo;
        this.oneofStoredType = cls2;
        this.mapDefaultEntry = obj;
        this.enumVerifier = enumVerifier;
        this.cachedSizeField = field3;
    }

    private static void checkFieldNumber(int i2) {
        if (i2 > 0) {
            return;
        }
        throw new IllegalArgumentException("fieldNumber must be positive: " + i2);
    }

    public static FieldInfo forField(Field field, int i2, FieldType fieldType, boolean z) {
        checkFieldNumber(i2);
        Internal.b(field, "field");
        Internal.b(fieldType, "fieldType");
        if (fieldType == FieldType.MESSAGE_LIST || fieldType == FieldType.GROUP_LIST) {
            throw new IllegalStateException("Shouldn't be called for repeated message fields.");
        }
        return new FieldInfo(field, i2, fieldType, null, null, 0, false, z, null, null, null, null, null);
    }

    public static FieldInfo forFieldWithEnumVerifier(Field field, int i2, FieldType fieldType, Internal.EnumVerifier enumVerifier) {
        checkFieldNumber(i2);
        Internal.b(field, "field");
        return new FieldInfo(field, i2, fieldType, null, null, 0, false, false, null, null, null, enumVerifier, null);
    }

    public static FieldInfo forMapField(Field field, int i2, Object obj, Internal.EnumVerifier enumVerifier) {
        Internal.b(obj, "mapDefaultEntry");
        checkFieldNumber(i2);
        Internal.b(field, "field");
        return new FieldInfo(field, i2, FieldType.MAP, null, null, 0, false, true, null, null, obj, enumVerifier, null);
    }

    public static FieldInfo forOneofMemberField(int i2, FieldType fieldType, OneofInfo oneofInfo, Class<?> cls, boolean z, Internal.EnumVerifier enumVerifier) {
        checkFieldNumber(i2);
        Internal.b(fieldType, "fieldType");
        Internal.b(oneofInfo, "oneof");
        Internal.b(cls, "oneofStoredType");
        if (fieldType.isScalar()) {
            return new FieldInfo(null, i2, fieldType, null, null, 0, false, z, oneofInfo, cls, null, enumVerifier, null);
        }
        throw new IllegalArgumentException("Oneof is only supported for scalar fields. Field " + i2 + " is of type " + fieldType);
    }

    public static FieldInfo forPackedField(Field field, int i2, FieldType fieldType, Field field2) {
        checkFieldNumber(i2);
        Internal.b(field, "field");
        Internal.b(fieldType, "fieldType");
        if (fieldType == FieldType.MESSAGE_LIST || fieldType == FieldType.GROUP_LIST) {
            throw new IllegalStateException("Shouldn't be called for repeated message fields.");
        }
        return new FieldInfo(field, i2, fieldType, null, null, 0, false, false, null, null, null, null, field2);
    }

    public static FieldInfo forPackedFieldWithEnumVerifier(Field field, int i2, FieldType fieldType, Internal.EnumVerifier enumVerifier, Field field2) {
        checkFieldNumber(i2);
        Internal.b(field, "field");
        return new FieldInfo(field, i2, fieldType, null, null, 0, false, false, null, null, null, enumVerifier, field2);
    }

    public static FieldInfo forProto2OptionalField(Field field, int i2, FieldType fieldType, Field field2, int i3, boolean z, Internal.EnumVerifier enumVerifier) {
        checkFieldNumber(i2);
        Internal.b(field, "field");
        Internal.b(fieldType, "fieldType");
        Internal.b(field2, "presenceField");
        if (field2 == null || isExactlyOneBitSet(i3)) {
            return new FieldInfo(field, i2, fieldType, null, field2, i3, false, z, null, null, null, enumVerifier, null);
        }
        throw new IllegalArgumentException("presenceMask must have exactly one bit set: " + i3);
    }

    public static FieldInfo forProto2RequiredField(Field field, int i2, FieldType fieldType, Field field2, int i3, boolean z, Internal.EnumVerifier enumVerifier) {
        checkFieldNumber(i2);
        Internal.b(field, "field");
        Internal.b(fieldType, "fieldType");
        Internal.b(field2, "presenceField");
        if (field2 == null || isExactlyOneBitSet(i3)) {
            return new FieldInfo(field, i2, fieldType, null, field2, i3, true, z, null, null, null, enumVerifier, null);
        }
        throw new IllegalArgumentException("presenceMask must have exactly one bit set: " + i3);
    }

    public static FieldInfo forRepeatedMessageField(Field field, int i2, FieldType fieldType, Class<?> cls) {
        checkFieldNumber(i2);
        Internal.b(field, "field");
        Internal.b(fieldType, "fieldType");
        Internal.b(cls, "messageClass");
        return new FieldInfo(field, i2, fieldType, cls, null, 0, false, false, null, null, null, null, null);
    }

    private static boolean isExactlyOneBitSet(int i2) {
        return i2 != 0 && (i2 & (i2 + (-1))) == 0;
    }

    public static Builder newBuilder() {
        return new Builder(null);
    }

    @Override // java.lang.Comparable
    public int compareTo(FieldInfo fieldInfo) {
        return this.fieldNumber - fieldInfo.fieldNumber;
    }

    public Field getCachedSizeField() {
        return this.cachedSizeField;
    }

    public Internal.EnumVerifier getEnumVerifier() {
        return this.enumVerifier;
    }

    public Field getField() {
        return this.field;
    }

    public int getFieldNumber() {
        return this.fieldNumber;
    }

    public Class<?> getListElementType() {
        return this.messageClass;
    }

    public Object getMapDefaultEntry() {
        return this.mapDefaultEntry;
    }

    public Class<?> getMessageFieldClass() {
        int i2 = AnonymousClass1.f9744a[this.type.ordinal()];
        if (i2 == 1 || i2 == 2) {
            Field field = this.field;
            return field != null ? field.getType() : this.oneofStoredType;
        } else if (i2 == 3 || i2 == 4) {
            return this.messageClass;
        } else {
            return null;
        }
    }

    public OneofInfo getOneof() {
        return this.oneof;
    }

    public Class<?> getOneofStoredType() {
        return this.oneofStoredType;
    }

    public Field getPresenceField() {
        return this.presenceField;
    }

    public int getPresenceMask() {
        return this.presenceMask;
    }

    public FieldType getType() {
        return this.type;
    }

    public boolean isEnforceUtf8() {
        return this.enforceUtf8;
    }

    public boolean isRequired() {
        return this.required;
    }
}
