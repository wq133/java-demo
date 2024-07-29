package com.example.devDemo.utils;

import java.util.Random;
import java.util.UUID;

public class GenerateUUID {
    private static final long START_EPOCH = -12219292800000L;
    // 定义版本号和变体标识的位数
    private static final int VERSION_BITS = 4;
    private static final int VARIANT_BITS = 2;
    // 生成的 UUID 二进制形式共 128 位
    private static final int TOTAL_BITS = 128;

    public static String generate() {
        Random random = new Random();
        long timestamp = System.currentTimeMillis() + START_EPOCH;
        long leastSigBits = random.nextLong();
        long mostSigBits = random.nextLong();

        // 根据 RFC 4122 规范设置 UUID 版本号和变体标识
        mostSigBits &= ~(0xfL << 12); // 清空版本号
        mostSigBits |= (4L << 12); // 设置版本号
        leastSigBits &= ~(0x3L << 62); // 清空变体标识
        leastSigBits |= (0x2L << 62); // 设置变体标识

        // 将时间戳写入 UUID 中段
        long timeLow = timestamp & 0xffffffffL;
        long timeMid = (timestamp >> 32) & 0xffffL;
        long timeHiAndVersion = (timestamp >> 48) & 0x0fffL;
        mostSigBits &= ~(0xffffffffffffL << 64); // 清空时间戳
        mostSigBits |= (timeLow << 32);
        mostSigBits |= (timeMid << 16);
        mostSigBits |= timeHiAndVersion;

        // 构造 UUID 对象
        UUID uuid = new UUID(mostSigBits, leastSigBits);
//        System.out.println(uuid.toString());
        return uuid.toString();
    }
}
