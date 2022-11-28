package org.bouncycastle.jsse.util;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.jsse.BCSNIHostName;
import org.bouncycastle.util.IPAddress;
/* loaded from: classes3.dex */
public class SNIUtil {
    private static final Logger LOG = Logger.getLogger(SNIUtil.class.getName());

    public static BCSNIHostName getBCSNIHostName(URL url) {
        String host;
        if (url == null || (host = url.getHost()) == null || host.indexOf(46) <= 0 || IPAddress.isValid(host)) {
            return null;
        }
        try {
            return new BCSNIHostName(host);
        } catch (Exception e2) {
            Logger logger = LOG;
            Level level = Level.FINER;
            logger.log(level, "Failed to parse BCSNIHostName from URL: " + url, (Throwable) e2);
            return null;
        }
    }
}
