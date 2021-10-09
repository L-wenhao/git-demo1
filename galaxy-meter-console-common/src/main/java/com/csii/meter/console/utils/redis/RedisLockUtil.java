package com.csii.meter.console.utils.redis;

import com.csii.meter.console.exception.RedisLockException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁
 */
@Slf4j
@Data
@Component
public class RedisLockUtil {

    @Autowired
    private RedissonClient redissonClient;

    public static class RLockContainer implements AutoCloseable {
        private RLock lock;

        public RLock getLock() {
            return lock;
        }

        public RLockContainer(RLock lock) {
            this.lock = lock;
        }

        public void close() {
            if (lock != null) {
                lock.unlock();
            }
        }

        public void tryLock(long time, TimeUnit unit) {
            boolean flag;
            try {
                flag = lock.tryLock(time, unit);
            } catch (InterruptedException e) {
                log.error("获取不到锁 ");
                throw new RedisLockException(e.getMessage());
            }
            if (!flag) {
                log.warn("redis分布锁获取不到锁， 操作频繁");
                throw new RedisLockException("操作频繁");
            }
        }
    }

    public RLock getLock(String lockKey) {
        return redissonClient.getLock(lockKey);
    }

    public RLockContainer getLockContainer(String lockKey) {
        return new RLockContainer(getLock(lockKey));
    }


}
