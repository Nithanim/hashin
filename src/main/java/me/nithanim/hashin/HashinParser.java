package me.nithanim.hashin;

import java.io.IOException;
import java.io.InputStream;

public class HashinParser {
    public void parse(InputStream in) throws IOException {
        int b1 = in.read();
        int vbits = b1 >>> 6;
        if (vbits == 0b10) {
            throw new UnsupportedOperationException("Version 0 not supported!");
        } else if (vbits == 0b000000) {
            HashFileIndexVersion1.from(b1, in);
        } else if (vbits == 0b000001) {
            throw new UnsupportedOperationException("No extension supported!");
        } else {
            throw new UnsupportedOperationException("Unsupported version!");
        }
    }
}
