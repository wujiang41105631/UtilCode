package com.xcn.util;

import java.lang.management.ManagementFactory;
import java.util.logging.Logger;

/**
 * Twitter snowflake
 * 参考：http://www.lanindex.com/twitter-snowflake%EF%BC%8C64%E4%BD%8D%E8%87%AA%E5%A2%9Eid%E7%AE%97%E6%B3%95%E8%AF%A6%E8%A7%A3/
 * Created by xupeng.guo on 2016/9/1.
 */
public class IDGenerator {

    private static final Logger LOG = Logger.getLogger(IDGenerator.class.getCanonicalName());

    private long workerId;
    private long datacenterId;
    private long sequence = 0L;

    private static final int BYTE_MASK = 0xFF;

    private static final long twepoch = 1288834974657L;

    private static final long workerIdBits = 5L;
    private static final long datacenterIdBits = 5L;
    private static final long maxWorkerId = ~(-1L << workerIdBits);
    private static final long maxDatacenterId = ~(-1L << datacenterIdBits);
    private static final long sequenceBits = 12L;

    private static final long workerIdShift = sequenceBits;
    private static final long datacenterIdShift = sequenceBits + workerIdBits;
    private static final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private static final long sequenceMask = ~(-1L << sequenceBits);

    private long lastTimestamp = -1L;

    public IDGenerator(long workerId, long dataCenterId) {
        // sanity check for workerId
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (dataCenterId > maxDatacenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = dataCenterId;
        LOG.info(String.format("IDGenerator : DataCenterId %d, WorkerId %d", this.datacenterId, this.workerId));
    }

    public static int getPID() {
        String pid;
        String name = ManagementFactory.getRuntimeMXBean().getName();
        pid = name.substring(0, name.indexOf("@"));
        return Integer.valueOf(pid);
    }

    public synchronized long nextId() {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            LOG.warning(String.format("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp));
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

    private static final long LOCAL_NODE_ID;

    private static final long LOCAL_WORK_ID;

    static {
        byte[] bytes = NetworkUtils.getLocalInetAddress().getAddress();
        int tmp = (bytes[0] & BYTE_MASK) << 24 | (bytes[1] & BYTE_MASK) << 16 | (bytes[2] & BYTE_MASK) << 8 | bytes[3] & BYTE_MASK;
        LOCAL_NODE_ID = Math.abs(tmp % maxDatacenterId);
        LOCAL_WORK_ID = getPID() % maxWorkerId;
    }

    private static final IDGenerator ID_WORKER = new IDGenerator(LOCAL_WORK_ID, LOCAL_NODE_ID);


    public static long createId() {
        return ID_WORKER.nextId();
    }

    public static String createIdStr() {
        return String.valueOf(createId());
    }
}

