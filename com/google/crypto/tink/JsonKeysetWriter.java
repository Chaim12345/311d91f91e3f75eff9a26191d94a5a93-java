package com.google.crypto.tink;

import androidx.core.app.NotificationCompat;
import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.KeysetInfo;
import com.google.crypto.tink.subtle.Base64;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public final class JsonKeysetWriter implements KeysetWriter {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final OutputStream outputStream;

    private JsonKeysetWriter(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    private JSONObject toJson(EncryptedKeyset encryptedKeyset) {
        return new JSONObject().put("encryptedKeyset", Base64.encode(encryptedKeyset.getEncryptedKeyset().toByteArray())).put("keysetInfo", toJson(encryptedKeyset.getKeysetInfo()));
    }

    private JSONObject toJson(KeyData keyData) {
        return new JSONObject().put("typeUrl", keyData.getTypeUrl()).put("value", Base64.encode(keyData.getValue().toByteArray())).put("keyMaterialType", keyData.getKeyMaterialType().name());
    }

    private JSONObject toJson(Keyset.Key key) {
        return new JSONObject().put("keyData", toJson(key.getKeyData())).put(NotificationCompat.CATEGORY_STATUS, key.getStatus().name()).put("keyId", toUnsignedLong(key.getKeyId())).put("outputPrefixType", key.getOutputPrefixType().name());
    }

    private JSONObject toJson(Keyset keyset) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("primaryKeyId", toUnsignedLong(keyset.getPrimaryKeyId()));
        JSONArray jSONArray = new JSONArray();
        for (Keyset.Key key : keyset.getKeyList()) {
            jSONArray.put(toJson(key));
        }
        jSONObject.put("key", jSONArray);
        return jSONObject;
    }

    private JSONObject toJson(KeysetInfo.KeyInfo keyInfo) {
        return new JSONObject().put("typeUrl", keyInfo.getTypeUrl()).put(NotificationCompat.CATEGORY_STATUS, keyInfo.getStatus().name()).put("keyId", keyInfo.getKeyId()).put("outputPrefixType", keyInfo.getOutputPrefixType().name());
    }

    private JSONObject toJson(KeysetInfo keysetInfo) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("primaryKeyId", toUnsignedLong(keysetInfo.getPrimaryKeyId()));
        JSONArray jSONArray = new JSONArray();
        for (KeysetInfo.KeyInfo keyInfo : keysetInfo.getKeyInfoList()) {
            jSONArray.put(toJson(keyInfo));
        }
        jSONObject.put("keyInfo", jSONArray);
        return jSONObject;
    }

    private long toUnsignedLong(int i2) {
        return i2 & BodyPartID.bodyIdMax;
    }

    public static KeysetWriter withFile(File file) {
        return new JsonKeysetWriter(new FileOutputStream(file));
    }

    public static KeysetWriter withOutputStream(OutputStream outputStream) {
        return new JsonKeysetWriter(outputStream);
    }

    public static KeysetWriter withPath(String str) {
        return withFile(new File(str));
    }

    public static KeysetWriter withPath(Path path) {
        return withFile(path.toFile());
    }

    @Override // com.google.crypto.tink.KeysetWriter
    public void write(EncryptedKeyset encryptedKeyset) {
        try {
            try {
                OutputStream outputStream = this.outputStream;
                String jSONObject = toJson(encryptedKeyset).toString(4);
                Charset charset = UTF_8;
                outputStream.write(jSONObject.getBytes(charset));
                this.outputStream.write(System.lineSeparator().getBytes(charset));
            } catch (JSONException e2) {
                throw new IOException(e2);
            }
        } finally {
            this.outputStream.close();
        }
    }

    @Override // com.google.crypto.tink.KeysetWriter
    public void write(Keyset keyset) {
        try {
            try {
                OutputStream outputStream = this.outputStream;
                String jSONObject = toJson(keyset).toString(4);
                Charset charset = UTF_8;
                outputStream.write(jSONObject.getBytes(charset));
                this.outputStream.write(System.lineSeparator().getBytes(charset));
            } catch (JSONException e2) {
                throw new IOException(e2);
            }
        } finally {
            this.outputStream.close();
        }
    }
}
