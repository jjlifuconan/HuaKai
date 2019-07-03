package com.social.basecommon.util;

import java.io.Closeable;

public class IOUtils {

    public static void closeQuietly(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            if (closeable == null) continue;
            try {
                closeable.close();
            } catch (Exception ignore) {}
        }

    }
}
