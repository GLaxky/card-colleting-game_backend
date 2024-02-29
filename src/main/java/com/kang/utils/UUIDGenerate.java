package com.kang.utils;

import java.util.UUID;

public class UUIDGenerate {
    public static String generate(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
