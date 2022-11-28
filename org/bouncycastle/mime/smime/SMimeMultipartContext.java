package org.bouncycastle.mime.smime;

import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.mime.CanonicalOutputStream;
import org.bouncycastle.mime.Headers;
import org.bouncycastle.mime.MimeContext;
import org.bouncycastle.mime.MimeMultipartContext;
import org.bouncycastle.mime.MimeParserContext;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.io.TeeInputStream;
import org.bouncycastle.util.io.TeeOutputStream;
/* loaded from: classes4.dex */
public class SMimeMultipartContext implements MimeMultipartContext {
    private DigestCalculator[] calculators;
    private final SMimeParserContext parserContext;

    public SMimeMultipartContext(MimeParserContext mimeParserContext, Headers headers) {
        this.parserContext = (SMimeParserContext) mimeParserContext;
        this.calculators = createDigestCalculators(headers);
    }

    private DigestCalculator[] createDigestCalculators(Headers headers) {
        try {
            String str = headers.getContentTypeAttributes().get("micalg");
            if (str != null) {
                String[] split = str.substring(str.indexOf(61) + 1).split(",");
                DigestCalculator[] digestCalculatorArr = new DigestCalculator[split.length];
                for (int i2 = 0; i2 < split.length; i2++) {
                    digestCalculatorArr[i2] = this.parserContext.getDigestCalculatorProvider().get(new AlgorithmIdentifier(SMimeUtils.d(SMimeUtils.e(split[i2]).trim())));
                }
                return digestCalculatorArr;
            }
            throw new IllegalStateException("No micalg field on content-type header");
        } catch (OperatorCreationException unused) {
            return null;
        }
    }

    @Override // org.bouncycastle.mime.MimeContext
    public InputStream applyContext(Headers headers, InputStream inputStream) {
        return inputStream;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DigestCalculator[] b() {
        return this.calculators;
    }

    OutputStream c() {
        DigestCalculator[] digestCalculatorArr = this.calculators;
        int i2 = 1;
        if (digestCalculatorArr.length == 1) {
            return digestCalculatorArr[0].getOutputStream();
        }
        OutputStream outputStream = digestCalculatorArr[0].getOutputStream();
        while (i2 < this.calculators.length) {
            i2++;
            outputStream = new TeeOutputStream(this.calculators[i2].getOutputStream(), outputStream);
        }
        return outputStream;
    }

    @Override // org.bouncycastle.mime.MimeMultipartContext
    public MimeContext createContext(final int i2) {
        return new MimeContext() { // from class: org.bouncycastle.mime.smime.SMimeMultipartContext.1
            @Override // org.bouncycastle.mime.MimeContext
            public InputStream applyContext(Headers headers, InputStream inputStream) {
                if (i2 == 0) {
                    OutputStream c2 = SMimeMultipartContext.this.c();
                    headers.dumpHeaders(c2);
                    c2.write(13);
                    c2.write(10);
                    return new TeeInputStream(inputStream, new CanonicalOutputStream(SMimeMultipartContext.this.parserContext, headers, c2));
                }
                return inputStream;
            }
        };
    }
}
