package com.chuckerteam.chucker.internal.support;

import androidx.exifinterface.media.ExifInterface;
import com.chuckerteam.chucker.internal.data.entity.HttpHeader;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u001e\u0010\b\u001a\u00020\u00072\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00022\u0006\u0010\u0006\u001a\u00020\u0005J\u0016\u0010\f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0005J\u000e\u0010\u000e\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007J\u000e\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0007J\u000e\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007J\u000e\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0013R\u0016\u0010\u0017\u001a\u00020\u00168\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0019\u001a\u00020\u00168\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0019\u0010\u0018¨\u0006\u001c"}, d2 = {"Lcom/chuckerteam/chucker/internal/support/FormatUtils;", "", "", "Lcom/chuckerteam/chucker/internal/data/entity/HttpHeader;", "httpHeaders", "", "withMarkup", "", "formatHeaders", "", "bytes", "si", "formatByteCount", "json", "formatJson", "xml", "formatXml", "form", "formatUrlEncodedForm", "", "throwable", "formatThrowable", "", "SI_MULTIPLE", "I", "BASE_TWO_MULTIPLE", "<init>", "()V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class FormatUtils {
    private static final int BASE_TWO_MULTIPLE = 1024;
    public static final FormatUtils INSTANCE = new FormatUtils();
    private static final int SI_MULTIPLE = 1000;

    private FormatUtils() {
    }

    @NotNull
    public final String formatByteCount(long j2, boolean z) {
        int i2 = z ? 1000 : 1024;
        if (j2 < i2) {
            return j2 + " B";
        }
        double d2 = j2;
        double d3 = i2;
        int log = (int) (Math.log(d2) / Math.log(d3));
        char charAt = (z ? "kMGTPE" : "KMGTPE").charAt(log - 1);
        String str = String.valueOf(charAt) + (z ? "" : "i");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.US, "%.1f %sB", Arrays.copyOf(new Object[]{Double.valueOf(d2 / Math.pow(d3, log)), str}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "java.lang.String.format(locale, format, *args)");
        return format;
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x0002, code lost:
        r10 = kotlin.collections.CollectionsKt___CollectionsKt.joinToString$default(r10, "", null, null, 0, null, new com.chuckerteam.chucker.internal.support.FormatUtils$formatHeaders$1(r11), 30, null);
     */
    @NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String formatHeaders(@Nullable List<HttpHeader> list, boolean z) {
        String joinToString$default;
        return (list == null || joinToString$default == null) ? "" : joinToString$default;
    }

    @NotNull
    public final String formatJson(@NotNull String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        try {
            String json2 = JsonConverter.INSTANCE.getInstance().toJson(JsonParser.parseString(json));
            Intrinsics.checkNotNullExpressionValue(json2, "JsonConverter.instance.toJson(je)");
            return json2;
        } catch (JsonParseException unused) {
            return json;
        }
    }

    @NotNull
    public final String formatThrowable(@NotNull Throwable throwable) {
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        StringWriter stringWriter = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stringWriter));
        String stringWriter2 = stringWriter.toString();
        Intrinsics.checkNotNullExpressionValue(stringWriter2, "sw.toString()");
        return stringWriter2;
    }

    @NotNull
    public final String formatUrlEncodedForm(@NotNull String form) {
        boolean isBlank;
        List split$default;
        String joinToString$default;
        Intrinsics.checkNotNullParameter(form, "form");
        try {
            isBlank = StringsKt__StringsJVMKt.isBlank(form);
            if (isBlank) {
                return form;
            }
            split$default = StringsKt__StringsKt.split$default((CharSequence) form, new String[]{"&"}, false, 0, 6, (Object) null);
            joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(split$default, "\n", null, null, 0, null, FormatUtils$formatUrlEncodedForm$1.INSTANCE, 30, null);
            return joinToString$default;
        } catch (UnsupportedEncodingException | IllegalArgumentException unused) {
            return form;
        }
    }

    @NotNull
    public final String formatXml(@NotNull String xml) {
        Intrinsics.checkNotNullParameter(xml, "xml");
        try {
            DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
            Intrinsics.checkNotNullExpressionValue(newInstance, "DocumentBuilderFactory.newInstance()");
            newInstance.setExpandEntityReferences(false);
            DocumentBuilder newDocumentBuilder = newInstance.newDocumentBuilder();
            Intrinsics.checkNotNullExpressionValue(newDocumentBuilder, "documentFactory.newDocumentBuilder()");
            Charset defaultCharset = Charset.defaultCharset();
            Intrinsics.checkNotNullExpressionValue(defaultCharset, "Charset.defaultCharset()");
            byte[] bytes = xml.getBytes(defaultCharset);
            Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
            Document parse = newDocumentBuilder.parse(new InputSource(new ByteArrayInputStream(bytes)));
            Intrinsics.checkNotNullExpressionValue(parse, "documentBuilder.parse(inputSource)");
            DOMSource dOMSource = new DOMSource(parse);
            StringWriter stringWriter = new StringWriter();
            StreamResult streamResult = new StreamResult(stringWriter);
            TransformerFactory newInstance2 = TransformerFactory.newInstance();
            newInstance2.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
            Transformer newTransformer = newInstance2.newTransformer();
            newTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", ExifInterface.GPS_MEASUREMENT_2D);
            newTransformer.setOutputProperty("indent", "yes");
            newTransformer.transform(dOMSource, streamResult);
            String stringWriter2 = stringWriter.toString();
            Intrinsics.checkNotNullExpressionValue(stringWriter2, "writer.toString()");
            return stringWriter2;
        } catch (IOException | TransformerException | SAXParseException unused) {
            return xml;
        }
    }
}
