package org.apache.http.conn.routing;

import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.util.Args;
@Contract(threading = ThreadingBehavior.IMMUTABLE)
/* loaded from: classes3.dex */
public class BasicRouteDirector implements HttpRouteDirector {
    protected int directStep(RouteInfo routeInfo, RouteInfo routeInfo2) {
        if (routeInfo2.getHopCount() <= 1 && routeInfo.getTargetHost().equals(routeInfo2.getTargetHost()) && routeInfo.isSecure() == routeInfo2.isSecure()) {
            return (routeInfo.getLocalAddress() == null || routeInfo.getLocalAddress().equals(routeInfo2.getLocalAddress())) ? 0 : -1;
        }
        return -1;
    }

    protected int firstStep(RouteInfo routeInfo) {
        return routeInfo.getHopCount() > 1 ? 2 : 1;
    }

    @Override // org.apache.http.conn.routing.HttpRouteDirector
    public int nextStep(RouteInfo routeInfo, RouteInfo routeInfo2) {
        Args.notNull(routeInfo, "Planned route");
        return (routeInfo2 == null || routeInfo2.getHopCount() < 1) ? firstStep(routeInfo) : routeInfo.getHopCount() > 1 ? proxiedStep(routeInfo, routeInfo2) : directStep(routeInfo, routeInfo2);
    }

    protected int proxiedStep(RouteInfo routeInfo, RouteInfo routeInfo2) {
        int hopCount;
        int hopCount2;
        if (routeInfo2.getHopCount() > 1 && routeInfo.getTargetHost().equals(routeInfo2.getTargetHost()) && (hopCount = routeInfo.getHopCount()) >= (hopCount2 = routeInfo2.getHopCount())) {
            for (int i2 = 0; i2 < hopCount2 - 1; i2++) {
                if (!routeInfo.getHopTarget(i2).equals(routeInfo2.getHopTarget(i2))) {
                    return -1;
                }
            }
            if (hopCount > hopCount2) {
                return 4;
            }
            if ((!routeInfo2.isTunnelled() || routeInfo.isTunnelled()) && (!routeInfo2.isLayered() || routeInfo.isLayered())) {
                if (!routeInfo.isTunnelled() || routeInfo2.isTunnelled()) {
                    if (!routeInfo.isLayered() || routeInfo2.isLayered()) {
                        return routeInfo.isSecure() != routeInfo2.isSecure() ? -1 : 0;
                    }
                    return 5;
                }
                return 3;
            }
            return -1;
        }
        return -1;
    }
}
