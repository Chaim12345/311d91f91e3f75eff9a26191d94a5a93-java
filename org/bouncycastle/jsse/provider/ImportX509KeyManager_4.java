package org.bouncycastle.jsse.provider;

import java.net.Socket;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509KeyManager;
import org.bouncycastle.jsse.BCX509ExtendedKeyManager;
import org.bouncycastle.jsse.BCX509Key;
/* loaded from: classes3.dex */
final class ImportX509KeyManager_4 extends BCX509ExtendedKeyManager implements ImportX509KeyManager {

    /* renamed from: a  reason: collision with root package name */
    final X509KeyManager f13882a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImportX509KeyManager_4(X509KeyManager x509KeyManager) {
        this.f13882a = x509KeyManager;
    }

    @Override // org.bouncycastle.jsse.BCX509ExtendedKeyManager
    protected BCX509Key a(String str, String str2) {
        return ProvX509Key.a(this.f13882a, str, str2);
    }

    @Override // javax.net.ssl.X509KeyManager
    public String chooseClientAlias(String[] strArr, Principal[] principalArr, Socket socket) {
        return this.f13882a.chooseClientAlias(strArr, principalArr, socket);
    }

    @Override // javax.net.ssl.X509KeyManager
    public String chooseServerAlias(String str, Principal[] principalArr, Socket socket) {
        return this.f13882a.chooseServerAlias(str, principalArr, socket);
    }

    @Override // javax.net.ssl.X509KeyManager
    public X509Certificate[] getCertificateChain(String str) {
        return this.f13882a.getCertificateChain(str);
    }

    @Override // javax.net.ssl.X509KeyManager
    public String[] getClientAliases(String str, Principal[] principalArr) {
        return this.f13882a.getClientAliases(str, principalArr);
    }

    @Override // javax.net.ssl.X509KeyManager
    public PrivateKey getPrivateKey(String str) {
        return this.f13882a.getPrivateKey(str);
    }

    @Override // javax.net.ssl.X509KeyManager
    public String[] getServerAliases(String str, Principal[] principalArr) {
        return this.f13882a.getServerAliases(str, principalArr);
    }

    @Override // org.bouncycastle.jsse.provider.ImportX509KeyManager
    public X509KeyManager unwrap() {
        return this.f13882a;
    }
}
