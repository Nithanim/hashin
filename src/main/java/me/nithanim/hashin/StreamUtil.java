package me.nithanim.hashin;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

class StreamUtil {
    public static void fillArray(InputStream in, byte[] arr) throws IOException {
        int pos = 0;
        int toRead = arr.length;
        int r;
        while ((toRead > 0 && (r = in.read(arr, pos, toRead)) != -1)) {
            toRead -= r;
            pos += r;
        }
        if (toRead > 0) {
            throw new EOFException("Wanted to read reaminig " + toRead + " bytes of total " + arr.length);
        }
    }

    public static int read(InputStream in) throws IOException {
        int b = in.read();
        if (b == -1) {
            throw new EOFException();
        } else {
            return b;
        }
    }
}
