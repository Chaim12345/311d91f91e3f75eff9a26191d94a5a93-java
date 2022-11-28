package com.facebook.stetho.inspector.helper;

import com.facebook.stetho.common.LogRedirector;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.jsonrpc.DisconnectReceiver;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.jsonrpc.PendingRequestCallback;
import java.nio.channels.NotYetConnectedException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes.dex */
public class ChromePeerManager {
    private static final String TAG = "ChromePeerManager";
    @GuardedBy("this")
    private PeerRegistrationListener mListener;
    @GuardedBy("this")
    private final Map<JsonRpcPeer, DisconnectReceiver> mReceivingPeers = new HashMap();
    @GuardedBy("this")
    private JsonRpcPeer[] mReceivingPeersSnapshot;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class UnregisterOnDisconnect implements DisconnectReceiver {
        private final JsonRpcPeer mPeer;

        public UnregisterOnDisconnect(JsonRpcPeer jsonRpcPeer) {
            this.mPeer = jsonRpcPeer;
        }

        @Override // com.facebook.stetho.inspector.jsonrpc.DisconnectReceiver
        public void onDisconnect() {
            ChromePeerManager.this.removePeer(this.mPeer);
        }
    }

    private synchronized JsonRpcPeer[] getReceivingPeersSnapshot() {
        if (this.mReceivingPeersSnapshot == null) {
            this.mReceivingPeersSnapshot = (JsonRpcPeer[]) this.mReceivingPeers.keySet().toArray(new JsonRpcPeer[this.mReceivingPeers.size()]);
        }
        return this.mReceivingPeersSnapshot;
    }

    private void sendMessageToPeers(String str, Object obj, @Nullable PendingRequestCallback pendingRequestCallback) {
        for (JsonRpcPeer jsonRpcPeer : getReceivingPeersSnapshot()) {
            try {
                jsonRpcPeer.invokeMethod(str, obj, pendingRequestCallback);
            } catch (NotYetConnectedException e2) {
                LogRedirector.e(TAG, "Error delivering data to Chrome", e2);
            }
        }
    }

    public synchronized boolean addPeer(JsonRpcPeer jsonRpcPeer) {
        boolean z;
        if (this.mReceivingPeers.containsKey(jsonRpcPeer)) {
            z = false;
        } else {
            UnregisterOnDisconnect unregisterOnDisconnect = new UnregisterOnDisconnect(jsonRpcPeer);
            jsonRpcPeer.registerDisconnectReceiver(unregisterOnDisconnect);
            this.mReceivingPeers.put(jsonRpcPeer, unregisterOnDisconnect);
            this.mReceivingPeersSnapshot = null;
            PeerRegistrationListener peerRegistrationListener = this.mListener;
            if (peerRegistrationListener != null) {
                peerRegistrationListener.onPeerRegistered(jsonRpcPeer);
            }
            z = true;
        }
        return z;
    }

    public synchronized boolean hasRegisteredPeers() {
        return !this.mReceivingPeers.isEmpty();
    }

    public void invokeMethodOnPeers(String str, Object obj, PendingRequestCallback pendingRequestCallback) {
        Util.throwIfNull(pendingRequestCallback);
        sendMessageToPeers(str, obj, pendingRequestCallback);
    }

    public synchronized void removePeer(JsonRpcPeer jsonRpcPeer) {
        if (this.mReceivingPeers.remove(jsonRpcPeer) != null) {
            this.mReceivingPeersSnapshot = null;
            PeerRegistrationListener peerRegistrationListener = this.mListener;
            if (peerRegistrationListener != null) {
                peerRegistrationListener.onPeerUnregistered(jsonRpcPeer);
            }
        }
    }

    public void sendNotificationToPeers(String str, Object obj) {
        sendMessageToPeers(str, obj, null);
    }

    public synchronized void setListener(PeerRegistrationListener peerRegistrationListener) {
        this.mListener = peerRegistrationListener;
    }
}
