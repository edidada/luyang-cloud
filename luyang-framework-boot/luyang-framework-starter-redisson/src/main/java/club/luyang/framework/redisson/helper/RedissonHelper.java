package club.luyang.framework.redisson.helper;

import club.luyang.framework.redisson.RedissonManager;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Redisson 助手类
 *
 * @author lu_yang
 */
public class RedissonHelper {

	private static final Logger logger = LoggerFactory.getLogger(RedissonHelper.class);

	private RedissonManager redissonManager;

	public RedissonManager getRedissonManager() {
		return redissonManager;
	}

	public void setRedissonManager(RedissonManager redissonManager) {
		this.redissonManager = redissonManager;
	}

	/**
	 * 锁住不设置超时时间(拿不到lock就不罢休，不然线程就一直block)
	 *
	 * @param lockName 锁名
	 * @return org.redisson.api.RLock
	 * @author lu_yang
	 */
	public RLock lock(String lockName) {
		var lock = redissonManager.getRedisson().getLock(lockName);
		lock.lock();
		return lock;
	}

	/**
	 * 加锁 设置释放时间 单位:秒
	 *
	 * @param lockName  锁名
	 * @param leaseTime 获取锁后保持锁的最长时间（如果尚未通过调用解锁释放）。如果leaseTime 为-1，则保持锁定直到显式解锁。 单位:秒
	 * @return org.redisson.api.RLock
	 * @author lu_yang
	 */
	public RLock lock(String lockName, long leaseTime) {
		var lock = this.redissonManager.getRedisson().getLock(lockName);
		lock.lock(leaseTime, TimeUnit.SECONDS);
		return lock;
	}

	/**
	 * 加锁 设置释放时间 自定义时间单位
	 *
	 * @param lockName  锁名
	 * @param unit      时间单位
	 * @param leaseTime 获取锁后保持锁的最长时间（如果尚未通过调用解锁释放）。如果leaseTime 为-1，则保持锁定直到显式解锁
	 * @return org.redisson.api.RLock
	 * @author lu_yang
	 */
	public RLock lock(String lockName, TimeUnit unit, long leaseTime) {
		var lock = this.redissonManager.getRedisson().getLock(lockName);
		lock.lock(leaseTime, unit);
		return lock;
	}

	/**
	 * 尝试获取锁
	 *
	 * @param lockName  锁名
	 * @param unit      时间单位
	 * @param waitTime  获得锁的最长时间
	 * @param leaseTime 租期
	 * @return boolean
	 * @author lu_yang
	 */
	public boolean tryLock(String lockName, TimeUnit unit, long waitTime, long leaseTime) {
		var lock = this.redissonManager.getRedisson().getLock(lockName);
		try {
			return lock.tryLock(waitTime, leaseTime, unit);
		} catch (InterruptedException e) {
			logger.error("获取Redisson分布式锁[异常]，lockName:{}", lockName, e);
			Thread.currentThread().interrupt();
		}

		return false;
	}

	/**
	 * 通过锁名解锁
	 *
	 * @param lockName 锁名
	 * @author lu_yang
	 */
	public void unlock(String lockName) {
		this.redissonManager.getRedisson().getLock(lockName).unlock();
	}

	/**
	 * 通过锁名解锁
	 *
	 * @param lock 锁
	 * @author lu_yang
	 */
	public void unlock(RLock lock) {
		lock.unlock();
	}
}
