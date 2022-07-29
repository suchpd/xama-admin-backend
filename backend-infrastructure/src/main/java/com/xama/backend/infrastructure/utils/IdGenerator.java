package com.xama.backend.infrastructure.utils;

public class IdGenerator {
    private final static long beginTs = 1483200000000L;

    private static long lastTs = 0L;

    private static long processId;
    private final static int processIdBits = 10;

    private static long sequence = 0L;
    private final static int sequenceBits = 12;

    public IdGenerator() {
    }

    public IdGenerator(long processId) {
        if (processId > ((1 << processIdBits) - 1)) {
            throw new RuntimeException("进程ID超出范围，设置位数" + processIdBits + "，最大" + ((1 << processIdBits) - 1));
        }
        this.processId = processId;
    }

    protected static long timeGen() {
        return System.currentTimeMillis();
    }

    public static synchronized long nextId() {
        long ts = timeGen();
        // 刚刚生成的时间戳比上次的时间戳还小，出错
        if (ts < lastTs) {
            throw new RuntimeException("时间戳顺序错误");
        }
        // 刚刚生成的时间戳跟上次的时间戳一样，则需要生成一个sequence序列号
        if (ts == lastTs) {
            // sequence循环自增
            sequence = (sequence + 1) & ((1 << sequenceBits) - 1);
            // 如果sequence=0则需要重新生成时间戳
            if (sequence == 0) {
                // 且必须保证时间戳序列往后
                ts = nextTs(lastTs);
            }
            // 如果ts>lastTs，时间戳序列已经不同了，此时可以不必生成sequence了，直接取0
        } else {
            sequence = 0L;
        }
        // 更新lastTs时间戳
        lastTs = ts;
        return ((ts - beginTs) << (processIdBits + sequenceBits)) | (processId << sequenceBits) | sequence;
    }

    public Long nextShortId() {
        Long shortId = System.currentTimeMillis() / 100 % 1000000000;
        if (shortId < 100000000) {
            shortId += 100000000;
        }
        return shortId;
    }

    protected static long nextTs(long lastTs) {
        long ts = timeGen();
        while (ts <= lastTs) {
            ts = timeGen();
        }
        return ts;
    }
}
