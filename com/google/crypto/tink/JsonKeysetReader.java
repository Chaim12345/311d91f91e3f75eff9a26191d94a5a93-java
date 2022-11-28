package com.google.crypto.tink;

import androidx.core.app.NotificationCompat;
import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.KeysetInfo;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.subtle.Base64;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public final class JsonKeysetReader implements KeysetReader {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final boolean closeStreamAfterReading;
    private final InputStream inputStream;
    private final JSONObject json;
    private boolean urlSafeBase64;

    private JsonKeysetReader(InputStream inputStream, boolean z) {
        this.urlSafeBase64 = false;
        this.inputStream = inputStream;
        this.closeStreamAfterReading = z;
        this.json = null;
    }

    private JsonKeysetReader(JSONObject jSONObject) {
        this.urlSafeBase64 = false;
        this.json = jSONObject;
        this.inputStream = null;
        this.closeStreamAfterReading = false;
    }

    private EncryptedKeyset encryptedKeysetFromJson(JSONObject jSONObject) {
        validateEncryptedKeyset(jSONObject);
        return EncryptedKeyset.newBuilder().setEncryptedKeyset(ByteString.copyFrom(this.urlSafeBase64 ? Base64.urlSafeDecode(jSONObject.getString("encryptedKeyset")) : Base64.decode(jSONObject.getString("encryptedKeyset")))).setKeysetInfo(keysetInfoFromJson(jSONObject.getJSONObject("keysetInfo"))).build();
    }

    private static KeyData.KeyMaterialType getKeyMaterialType(String str) {
        if (str.equals("SYMMETRIC")) {
            return KeyData.KeyMaterialType.SYMMETRIC;
        }
        if (str.equals("ASYMMETRIC_PRIVATE")) {
            return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
        }
        if (str.equals("ASYMMETRIC_PUBLIC")) {
            return KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC;
        }
        if (str.equals("REMOTE")) {
            return KeyData.KeyMaterialType.REMOTE;
        }
        throw new JSONException("unknown key material type: " + str);
    }

    private static OutputPrefixType getOutputPrefixType(String str) {
        if (str.equals("TINK")) {
            return OutputPrefixType.TINK;
        }
        if (str.equals("RAW")) {
            return OutputPrefixType.RAW;
        }
        if (str.equals("LEGACY")) {
            return OutputPrefixType.LEGACY;
        }
        if (str.equals("CRUNCHY")) {
            return OutputPrefixType.CRUNCHY;
        }
        throw new JSONException("unknown output prefix type: " + str);
    }

    private static KeyStatusType getStatus(String str) {
        if (str.equals("ENABLED")) {
            return KeyStatusType.ENABLED;
        }
        if (str.equals("DISABLED")) {
            return KeyStatusType.DISABLED;
        }
        throw new JSONException("unknown status: " + str);
    }

    private KeyData keyDataFromJson(JSONObject jSONObject) {
        validateKeyData(jSONObject);
        return KeyData.newBuilder().setTypeUrl(jSONObject.getString("typeUrl")).setValue(ByteString.copyFrom(this.urlSafeBase64 ? Base64.urlSafeDecode(jSONObject.getString("value")) : Base64.decode(jSONObject.getString("value")))).setKeyMaterialType(getKeyMaterialType(jSONObject.getString("keyMaterialType"))).build();
    }

    private Keyset.Key keyFromJson(JSONObject jSONObject) {
        validateKey(jSONObject);
        return Keyset.Key.newBuilder().setStatus(getStatus(jSONObject.getString(NotificationCompat.CATEGORY_STATUS))).setKeyId(jSONObject.getInt("keyId")).setOutputPrefixType(getOutputPrefixType(jSONObject.getString("outputPrefixType"))).setKeyData(keyDataFromJson(jSONObject.getJSONObject("keyData"))).build();
    }

    private static KeysetInfo.KeyInfo keyInfoFromJson(JSONObject jSONObject) {
        return KeysetInfo.KeyInfo.newBuilder().setStatus(getStatus(jSONObject.getString(NotificationCompat.CATEGORY_STATUS))).setKeyId(jSONObject.getInt("keyId")).setOutputPrefixType(getOutputPrefixType(jSONObject.getString("outputPrefixType"))).setTypeUrl(jSONObject.getString("typeUrl")).build();
    }

    private Keyset keysetFromJson(JSONObject jSONObject) {
        validateKeyset(jSONObject);
        Keyset.Builder newBuilder = Keyset.newBuilder();
        if (jSONObject.has("primaryKeyId")) {
            newBuilder.setPrimaryKeyId(jSONObject.getInt("primaryKeyId"));
        }
        JSONArray jSONArray = jSONObject.getJSONArray("key");
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            newBuilder.addKey(keyFromJson(jSONArray.getJSONObject(i2)));
        }
        return newBuilder.build();
    }

    private static KeysetInfo keysetInfoFromJson(JSONObject jSONObject) {
        KeysetInfo.Builder newBuilder = KeysetInfo.newBuilder();
        if (jSONObject.has("primaryKeyId")) {
            newBuilder.setPrimaryKeyId(jSONObject.getInt("primaryKeyId"));
        }
        if (jSONObject.has("keyInfo")) {
            JSONArray jSONArray = jSONObject.getJSONArray("keyInfo");
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                newBuilder.addKeyInfo(keyInfoFromJson(jSONArray.getJSONObject(i2)));
            }
        }
        return newBuilder.build();
    }

    private static void validateEncryptedKeyset(JSONObject jSONObject) {
        if (!jSONObject.has("encryptedKeyset")) {
            throw new JSONException("invalid encrypted keyset");
        }
    }

    private static void validateKey(JSONObject jSONObject) {
        if (!jSONObject.has("keyData") || !jSONObject.has(NotificationCompat.CATEGORY_STATUS) || !jSONObject.has("keyId") || !jSONObject.has("outputPrefixType")) {
            throw new JSONException("invalid key");
        }
    }

    private static void validateKeyData(JSONObject jSONObject) {
        if (!jSONObject.has("typeUrl") || !jSONObject.has("value") || !jSONObject.has("keyMaterialType")) {
            throw new JSONException("invalid keyData");
        }
    }

    private static void validateKeyset(JSONObject jSONObject) {
        if (!jSONObject.has("key") || jSONObject.getJSONArray("key").length() == 0) {
            throw new JSONException("invalid keyset");
        }
    }

    public static JsonKeysetReader withBytes(byte[] bArr) {
        return new JsonKeysetReader(new ByteArrayInputStream(bArr), true);
    }

    public static JsonKeysetReader withFile(File file) {
        return new JsonKeysetReader(new FileInputStream(file), true);
    }

    public static KeysetReader withInputStream(InputStream inputStream) {
        return new JsonKeysetReader(inputStream, false);
    }

    public static JsonKeysetReader withJsonObject(JSONObject jSONObject) {
        return new JsonKeysetReader(jSONObject);
    }

    public static JsonKeysetReader withPath(String str) {
        return withFile(new File(str));
    }

    public static JsonKeysetReader withPath(Path path) {
        return withFile(path.toFile());
    }

    public static JsonKeysetReader withString(String str) {
        return new JsonKeysetReader(new ByteArrayInputStream(str.getBytes(UTF_8)), true);
    }

    @Override // com.google.crypto.tink.KeysetReader
    public Keyset read() {
        try {
            try {
                JSONObject jSONObject = this.json;
                if (jSONObject != null) {
                    return keysetFromJson(jSONObject);
                }
                Keyset keysetFromJson = keysetFromJson(new JSONObject(new String(Util.readAll(this.inputStream), UTF_8)));
                InputStream inputStream = this.inputStream;
                if (inputStream != null && this.closeStreamAfterReading) {
                    inputStream.close();
                }
                return keysetFromJson;
            } catch (JSONException e2) {
                throw new IOException(e2);
            }
        } finally {
            InputStream inputStream2 = this.inputStream;
            if (inputStream2 != null && this.closeStreamAfterReading) {
                inputStream2.close();
            }
        }
    }

    @Override // com.google.crypto.tink.KeysetReader
    public EncryptedKeyset readEncrypted() {
        try {
            try {
                JSONObject jSONObject = this.json;
                if (jSONObject != null) {
                    return encryptedKeysetFromJson(jSONObject);
                }
                EncryptedKeyset encryptedKeysetFromJson = encryptedKeysetFromJson(new JSONObject(new String(Util.readAll(this.inputStream), UTF_8)));
                InputStream inputStream = this.inputStream;
                if (inputStream != null && this.closeStreamAfterReading) {
                    inputStream.close();
                }
                return encryptedKeysetFromJson;
            } catch (JSONException e2) {
                throw new IOException(e2);
            }
        } finally {
            InputStream inputStream2 = this.inputStream;
            if (inputStream2 != null && this.closeStreamAfterReading) {
                inputStream2.close();
            }
        }
    }

    public JsonKeysetReader withUrlSafeBase64() {
        this.urlSafeBase64 = true;
        return this;
    }
}
