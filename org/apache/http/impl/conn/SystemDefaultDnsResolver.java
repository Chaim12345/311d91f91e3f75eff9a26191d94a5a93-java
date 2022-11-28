package org.apache.http.impl.conn;

import java.net.InetAddress;
import org.apache.http.conn.DnsResolver;
/* loaded from: classes3.dex */
public class SystemDefaultDnsResolver implements DnsResolver {
    public static final SystemDefaultDnsResolver INSTANCE = new SystemDefaultDnsResolver();

    @Override // org.apache.http.conn.DnsResolver
    public InetAddress[] resolve(String str) {
        return InetAddress.getAllByName(str);
    }
}
