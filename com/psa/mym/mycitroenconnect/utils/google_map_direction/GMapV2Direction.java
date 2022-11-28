package com.psa.mym.mycitroenconnect.utils.google_map_direction;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.maps.model.LatLng;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import java.util.ArrayList;
import org.bouncycastle.i18n.TextBundle;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/* loaded from: classes3.dex */
public class GMapV2Direction {
    public static final String MODE_DRIVING = "driving";
    public static final String MODE_WALKING = "walking";

    private ArrayList<LatLng> decodePoly(String str) {
        int i2;
        int i3;
        ArrayList<LatLng> arrayList = new ArrayList<>();
        int length = str.length();
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < length) {
            int i7 = 0;
            int i8 = 0;
            while (true) {
                i2 = i4 + 1;
                int charAt = str.charAt(i4) - '?';
                i7 |= (charAt & 31) << i8;
                i8 += 5;
                if (charAt < 32) {
                    break;
                }
                i4 = i2;
            }
            int i9 = ((i7 & 1) != 0 ? ~(i7 >> 1) : i7 >> 1) + i5;
            int i10 = 0;
            int i11 = 0;
            while (true) {
                i3 = i2 + 1;
                int charAt2 = str.charAt(i2) - '?';
                i10 |= (charAt2 & 31) << i11;
                i11 += 5;
                if (charAt2 < 32) {
                    break;
                }
                i2 = i3;
            }
            int i12 = i10 & 1;
            int i13 = i10 >> 1;
            if (i12 != 0) {
                i13 = ~i13;
            }
            i6 += i13;
            arrayList.add(new LatLng(i9 / 100000.0d, i6 / 100000.0d));
            i5 = i9;
            i4 = i3;
        }
        return arrayList;
    }

    private int getNodeIndex(NodeList nodeList, String str) {
        for (int i2 = 0; i2 < nodeList.getLength(); i2++) {
            if (nodeList.item(i2).getNodeName().equals(str)) {
                return i2;
            }
        }
        return -1;
    }

    public String getCopyRights(Document document) {
        try {
            return document.getElementsByTagName("copyrights").item(0).getTextContent();
        } catch (Exception unused) {
            return AppConstants.DEFAULT_VAL_SPEED_DLG;
        }
    }

    public ArrayList<LatLng> getDirection(Document document) {
        ArrayList<LatLng> arrayList = new ArrayList<>();
        NodeList elementsByTagName = document.getElementsByTagName("step");
        if (elementsByTagName.getLength() > 0) {
            for (int i2 = 0; i2 < elementsByTagName.getLength(); i2++) {
                NodeList childNodes = elementsByTagName.item(i2).getChildNodes();
                NodeList childNodes2 = childNodes.item(getNodeIndex(childNodes, "start_location")).getChildNodes();
                arrayList.add(new LatLng(Double.parseDouble(childNodes2.item(getNodeIndex(childNodes2, "lat")).getTextContent()), Double.parseDouble(childNodes2.item(getNodeIndex(childNodes2, "lng")).getTextContent())));
                NodeList childNodes3 = childNodes.item(getNodeIndex(childNodes, "polyline")).getChildNodes();
                ArrayList<LatLng> decodePoly = decodePoly(childNodes3.item(getNodeIndex(childNodes3, "points")).getTextContent());
                for (int i3 = 0; i3 < decodePoly.size(); i3++) {
                    arrayList.add(new LatLng(decodePoly.get(i3).latitude, decodePoly.get(i3).longitude));
                }
                NodeList childNodes4 = childNodes.item(getNodeIndex(childNodes, "end_location")).getChildNodes();
                arrayList.add(new LatLng(Double.parseDouble(childNodes4.item(getNodeIndex(childNodes4, "lat")).getTextContent()), Double.parseDouble(childNodes4.item(getNodeIndex(childNodes4, "lng")).getTextContent())));
            }
        }
        return arrayList;
    }

    public String getDistanceText(Document document) {
        if (document.getElementsByTagName("distance") != null) {
            NodeList elementsByTagName = document.getElementsByTagName("distance");
            Node item = elementsByTagName.item(elementsByTagName.getLength() - 1);
            if (item.getChildNodes() != null) {
                NodeList childNodes = item.getChildNodes();
                return childNodes.item(getNodeIndex(childNodes, TextBundle.TEXT_ENTRY)).getTextContent();
            }
        }
        return AppConstants.DEFAULT_VAL_SPEED_DLG;
    }

    public int getDistanceValue(Document document) {
        try {
            NodeList elementsByTagName = document.getElementsByTagName("distance");
            NodeList childNodes = elementsByTagName.item(elementsByTagName.getLength() - 1).getChildNodes();
            return Integer.parseInt(childNodes.item(getNodeIndex(childNodes, "value")).getTextContent());
        } catch (Exception unused) {
            return -1;
        }
    }

    public String getDurationText(Document document) {
        if (document.getElementsByTagName(TypedValues.Transition.S_DURATION) != null) {
            NodeList elementsByTagName = document.getElementsByTagName(TypedValues.Transition.S_DURATION);
            Node item = elementsByTagName.item(elementsByTagName.getLength() - 1);
            if (item.getChildNodes() != null) {
                NodeList childNodes = item.getChildNodes();
                return childNodes.item(getNodeIndex(childNodes, TextBundle.TEXT_ENTRY)).getTextContent();
            }
        }
        return AppConstants.DEFAULT_VAL_SPEED_DLG;
    }

    public int getDurationValue(Document document) {
        try {
            NodeList childNodes = document.getElementsByTagName(TypedValues.Transition.S_DURATION).item(0).getChildNodes();
            return Integer.parseInt(childNodes.item(getNodeIndex(childNodes, "value")).getTextContent());
        } catch (Exception unused) {
            return -1;
        }
    }

    public String getEndAddress(Document document) {
        try {
            return document.getElementsByTagName("end_address").item(0).getTextContent();
        } catch (Exception unused) {
            return AppConstants.DEFAULT_VAL_SPEED_DLG;
        }
    }

    public String getPoints(Document document) {
        try {
            return document.getElementsByTagName("overview_polyline").item(0).getTextContent();
        } catch (Exception unused) {
            return AppConstants.DEFAULT_VAL_SPEED_DLG;
        }
    }

    public String getStartAddress(Document document) {
        try {
            return document.getElementsByTagName("start_address").item(0).getTextContent();
        } catch (Exception unused) {
            return AppConstants.DEFAULT_VAL_SPEED_DLG;
        }
    }
}
