package org.example.orderservice.util;

import java.util.UUID;

public class KeyUtil {
    public static String generateUniqueKey() {
        return UUID.randomUUID().toString();
    }
}
