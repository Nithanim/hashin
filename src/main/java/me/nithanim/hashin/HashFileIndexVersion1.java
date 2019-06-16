package me.nithanim.hashin;

import java.io.IOException;
import java.io.InputStream;
import lombok.NonNull;
import lombok.Value;

@Value
public class HashFileIndexVersion1 {
    public static HashFileIndexVersion1 from(int b1, InputStream in) throws IOException {
        int requirements = (b1 & 0b11) + 1;
        byte[] hash = new byte[256 / 8];
        StreamUtil.fillArray(in, hash);
        long filesize = StreamUtil.read(in);
        filesize = (filesize << 8) | StreamUtil.read(in);
        filesize = (filesize << 8) | StreamUtil.read(in);
        filesize = (filesize << 8) | StreamUtil.read(in);
        filesize = (filesize << 8) | StreamUtil.read(in);
        filesize = (filesize << 8) | StreamUtil.read(in);
        return new HashFileIndexVersion1(Requirement.values()[requirements], hash, filesize);
    }

    @NonNull
    public HashFileIndexVersion1(Requirement requirement, byte[] hash, long filesize) {
        if (hash.length != 256 / 8) {
            throw new IllegalArgumentException("Hash must be 256 bits long!");
        }
        this.requirement = requirement;
        this.hash = hash;
        this.filesize = filesize;
    }

    private Requirement requirement;
    private byte[] hash;
    private long filesize;

    public enum Requirement {
        T1, T2, T3, T4;
    }
}
