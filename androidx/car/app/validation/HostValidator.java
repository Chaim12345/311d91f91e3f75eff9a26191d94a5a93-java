package androidx.car.app.validation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.os.Build;
import android.os.Process;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.ArrayRes;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.car.app.HostInfo;
import androidx.car.app.utils.LogTags;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes.dex */
public final class HostValidator {
    @NonNull
    public static final HostValidator ALLOW_ALL_HOSTS_VALIDATOR = new HostValidator(null, new HashMap(), true);
    public static final String TEMPLATE_RENDERER_PERMISSION = "android.car.permission.TEMPLATE_RENDERER";
    private final boolean mAllowAllHosts;
    private final Map<String, List<String>> mAllowedHosts;
    private final Map<String, Pair<Integer, Boolean>> mCallerChecked = new HashMap();
    @Nullable
    private final PackageManager mPackageManager;

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(28)
    /* loaded from: classes.dex */
    public static final class Api28Impl {
        private Api28Impl() {
        }

        @NonNull
        @DoNotInline
        static PackageInfo getPackageInfo(@NonNull PackageManager packageManager, @NonNull String str) {
            return packageManager.getPackageInfo(str, 134221824);
        }

        @Nullable
        @DoNotInline
        static Signature[] getSignatures(@NonNull PackageInfo packageInfo) {
            SigningInfo signingInfo = packageInfo.signingInfo;
            if (signingInfo == null) {
                return null;
            }
            return signingInfo.getSigningCertificateHistory();
        }
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private final Map<String, List<String>> mAllowedHosts = new HashMap();
        private final Context mContext;

        public Builder(@NonNull Context context) {
            this.mContext = context;
        }

        private String cleanUp(String str) {
            return str.toLowerCase(Locale.US).replace(" ", "");
        }

        @NonNull
        public Builder addAllowedHost(@NonNull String str, @NonNull String str2) {
            Objects.requireNonNull(str);
            Objects.requireNonNull(str2);
            List<String> list = this.mAllowedHosts.get(str);
            if (list == null) {
                list = new ArrayList<>();
                this.mAllowedHosts.put(str, list);
            }
            list.add(str2);
            return this;
        }

        @NonNull
        @SuppressLint({"MissingGetterMatchingBuilder"})
        public Builder addAllowedHosts(@ArrayRes int i2) {
            String[] stringArray = this.mContext.getResources().getStringArray(i2);
            if (stringArray == null) {
                throw new IllegalArgumentException("Invalid allowlist res id: " + i2);
            }
            for (String str : stringArray) {
                String[] split = str.split(",", -1);
                if (split.length != 2) {
                    throw new IllegalArgumentException("Invalid allowed host entry: '" + str + "'");
                }
                addAllowedHost(cleanUp(split[1]), cleanUp(split[0]));
            }
            return this;
        }

        @NonNull
        public HostValidator build() {
            return new HostValidator(this.mContext.getPackageManager(), this.mAllowedHosts, false);
        }
    }

    HostValidator(@Nullable PackageManager packageManager, @NonNull Map<String, List<String>> map, boolean z) {
        this.mPackageManager = packageManager;
        this.mAllowedHosts = map;
        this.mAllowAllHosts = z;
    }

    @Nullable
    private Boolean checkCache(HostInfo hostInfo) {
        Pair<Integer, Boolean> pair = this.mCallerChecked.get(hostInfo.getPackageName());
        if (pair != null && ((Integer) pair.first).intValue() == hostInfo.getUid()) {
            return (Boolean) pair.second;
        }
        return null;
    }

    @Nullable
    private String getDigest(Signature signature) {
        byte[] byteArray = signature.toByteArray();
        MessageDigest messageDigest = getMessageDigest();
        if (messageDigest == null) {
            return null;
        }
        messageDigest.update(byteArray);
        byte[] digest = messageDigest.digest();
        StringBuilder sb = new StringBuilder((digest.length * 3) - 1);
        int length = digest.length;
        for (int i2 = 0; i2 < length; i2++) {
            sb.append(String.format("%02x", Byte.valueOf(digest[i2])));
        }
        return sb.toString();
    }

    @Nullable
    private static MessageDigest getMessageDigest() {
        try {
            return MessageDigest.getInstance("SHA256");
        } catch (NoSuchAlgorithmException e2) {
            Log.e(LogTags.TAG_HOST_VALIDATION, "Could not find SHA256 hash algorithm", e2);
            return null;
        }
    }

    @Nullable
    private PackageInfo getPackageInfo(String str) {
        try {
            PackageManager packageManager = this.mPackageManager;
            if (packageManager != null) {
                return Build.VERSION.SDK_INT >= 28 ? Api28Impl.getPackageInfo(packageManager, str) : packageManager.getPackageInfo(str, 4160);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("PackageManager is null. Package info cannot be found for package ");
            sb.append(str);
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Package ");
            sb2.append(str);
            sb2.append(" not found");
            return null;
        }
    }

    @Nullable
    private Signature[] getSignatures(PackageInfo packageInfo) {
        if (Build.VERSION.SDK_INT >= 28) {
            return Api28Impl.getSignatures(packageInfo);
        }
        Signature[] signatureArr = packageInfo.signatures;
        if (signatureArr == null || signatureArr.length != 1) {
            return null;
        }
        return signatureArr;
    }

    private static boolean hasPermissionGranted(PackageInfo packageInfo, String str) {
        if (packageInfo.requestedPermissionsFlags != null && packageInfo.requestedPermissions != null) {
            int i2 = 0;
            while (true) {
                int[] iArr = packageInfo.requestedPermissionsFlags;
                if (i2 >= iArr.length) {
                    break;
                }
                if (iArr[i2] == 2) {
                    String[] strArr = packageInfo.requestedPermissions;
                    if (i2 < strArr.length && str.equals(strArr[i2])) {
                        return true;
                    }
                }
                i2++;
            }
        }
        return false;
    }

    private boolean isAllowListed(String str, Signature[] signatureArr) {
        List<String> list = this.mAllowedHosts.get(str);
        if (list == null) {
            return false;
        }
        for (Signature signature : signatureArr) {
            if (list.contains(getDigest(signature))) {
                return true;
            }
        }
        return false;
    }

    private void updateCache(HostInfo hostInfo, boolean z) {
        this.mCallerChecked.put(hostInfo.getPackageName(), Pair.create(Integer.valueOf(hostInfo.getUid()), Boolean.valueOf(z)));
    }

    private boolean validateHost(HostInfo hostInfo) {
        String packageName = hostInfo.getPackageName();
        PackageInfo packageInfo = getPackageInfo(packageName);
        if (packageInfo == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Rejected - package name ");
            sb.append(packageName);
            sb.append(" not found");
            return false;
        }
        Signature[] signatures = getSignatures(packageInfo);
        if (signatures == null || signatures.length == 0) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Package ");
            sb2.append(packageName);
            sb2.append(" is not signed or it has more than one signature");
            return false;
        }
        int i2 = packageInfo.applicationInfo.uid;
        if (i2 != hostInfo.getUid()) {
            throw new IllegalStateException("Host " + hostInfo + " doesn't match caller's actual UID " + i2);
        }
        boolean hasPermissionGranted = hasPermissionGranted(packageInfo, TEMPLATE_RENDERER_PERMISSION);
        boolean isAllowListed = isAllowListed(packageName, signatures);
        if (i2 == Process.myUid()) {
            Log.isLoggable(LogTags.TAG_HOST_VALIDATION, 3);
            return true;
        } else if (isAllowListed) {
            Log.isLoggable(LogTags.TAG_HOST_VALIDATION, 3);
            return true;
        } else if (i2 == 1000) {
            Log.isLoggable(LogTags.TAG_HOST_VALIDATION, 3);
            return true;
        } else if (hasPermissionGranted) {
            Log.isLoggable(LogTags.TAG_HOST_VALIDATION, 3);
            return true;
        } else {
            Log.e(LogTags.TAG_HOST_VALIDATION, String.format("Unrecognized host.\nIf this is a valid caller, please add the following to your CarAppService#createHostValidator() implementation:\nreturn new HostValidator.Builder(context)\n\t.addAllowedHost(\"%s\", \"%s\");\n\t.build()", packageName, getDigest(signatures[0])));
            return false;
        }
    }

    @NonNull
    public Map<String, List<String>> getAllowedHosts() {
        return Collections.unmodifiableMap(this.mAllowedHosts);
    }

    public boolean isValidHost(@NonNull HostInfo hostInfo) {
        Objects.requireNonNull(hostInfo);
        if (Log.isLoggable(LogTags.TAG_HOST_VALIDATION, 3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Evaluating ");
            sb.append(hostInfo);
        }
        if (this.mAllowAllHosts) {
            Log.isLoggable(LogTags.TAG_HOST_VALIDATION, 3);
            return true;
        }
        Boolean checkCache = checkCache(hostInfo);
        if (checkCache != null) {
            return checkCache.booleanValue();
        }
        boolean validateHost = validateHost(hostInfo);
        updateCache(hostInfo, validateHost);
        return validateHost;
    }
}
