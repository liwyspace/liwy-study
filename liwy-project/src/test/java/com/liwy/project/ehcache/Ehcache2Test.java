package com.liwy.project.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

import org.junit.Test;

public class Ehcache2Test {

	@Test
	public void testEhcacheJava() {
		CacheManager cacheManager = CacheManager.newInstance();
		String[] cacheNames = cacheManager.getCacheNames();
		for (String str : cacheNames) {
			System.out.println(str);
		}

		// 创建缓存
		// Cache testCache = new Cache("testCache", 5000, false, false, 5, 2);
		Cache testCache = new Cache(new CacheConfiguration("testCache",
				10000000)
				.memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU)
				.eternal(false).timeToLiveSeconds(3).timeToIdleSeconds(30)
				.diskExpiryThreadIntervalSeconds(0));

		cacheManager.addCache(testCache); // 添加缓存

		Cache cache = cacheManager.getCache("testCache");

		Element element1 = new Element(1L, "liwenyao");
		Element element2 = new Element(2L, "yaonan");
		cache.put(element1); // 添加条目
		cache.put(element2);

		Element liwy = cache.get(1L);
		System.out.println(liwy.getObjectValue());
		System.out.println(cache.isKeyInCache(1L)); // 判断条目存储
		cache.remove(1L); // 移除条目
		System.out.println(cache.isKeyInCache(1L));

		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(cache.isExpired(element2)); // 判断条目是否过期

		cache.flush(); // 清空缓存
		cacheManager.removeCache("testCache"); // 移除缓存

		cacheManager.shutdown(); // 关闭管理器
	}

	@Test
	public void testEhcache() {
		CacheManager cacheManager = CacheManager
				.newInstance("src/main/resources/ehcache.xml");
		
		Cache passwordRetryCache = cacheManager.getCache("HelloWorldCache");
		Element element1 = new Element(1L, "liwenyao");
		Element element2 = new Element(2L, "yaonan");
		passwordRetryCache.put(element1);
		passwordRetryCache.put(element2);

		Element liwy = passwordRetryCache.get(1L);
		System.out.println(liwy.getObjectValue());
		System.out.println(passwordRetryCache.isKeyInCache(1L));
		passwordRetryCache.remove(1L);
		System.out.println(passwordRetryCache.isKeyInCache(1L));

		try {
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(passwordRetryCache.isKeyInCache(2L));

	}
}
