package jedis_sakura.sample.redis;

import java.util.ArrayList;
import java.util.List;

import jedis_sakura.sample.digester.RedisDigester;
import jedis_sakura.sample.digester.redis.RedisConfigDto;
import jedis_sakura.sample.util.Pair;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.exceptions.JedisException;

public class RedisManager {

	final private String REDIS_RESPONSE_OK = "OK";
	final private long REDIS_RESPONS_DEL_OK = 1L;
	final private static RedisManager self = new RedisManager();
	private JedisPool pool = null;
	
	private RedisManager() {
		setConfig();
	}
	
	private void setConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		RedisConfigDto rd = RedisDigester.getInstance().getRedisConfig();
		jedisPoolConfig.setMaxIdle(rd.getRcp().getMaxIdle());
		jedisPoolConfig.setMaxWaitMillis(rd.getRcp().getMaxWait());
		
		this.pool = new JedisPool(jedisPoolConfig, rd.getRp().getHost(), rd.getRp().getPort());
	}
	
	public static RedisManager getInstance() {
		return self;
	}
	
	private Jedis getResource() {
		try {
			return pool.getResource();
		}
		catch(JedisException ex) {
			throw new RedisManagerRunTimeException(getClass().getSimpleName() + "", ex.getCause());
		}
	}
	
	public void set(String key, String value) {
		final Jedis jedis = getResource();
		String v = jedis.set(key, value);
		if (! REDIS_RESPONSE_OK.equals(v)) {
			
		}
		jedis.close();
	}
	
	public String get(String key) {
		final Jedis jedis = getResource();
		String ret = jedis.get(key);
		jedis.close();
		return ret;
	}
	
	public void del(String key) {
		final Jedis jedis = getResource();
		long ret = jedis.del(key);
		jedis.close();
		if (REDIS_RESPONS_DEL_OK != ret) {
			
		}
	}
	
	public void pipeline(List<Pair<String, String>> kv) {
		final Jedis jedis = getResource();
		final Pipeline pl = jedis.pipelined();
		final List<Response<String>> ret = new ArrayList<Response<String>>();
		kv.forEach(p -> {
			ret.add(pl.set(p.getKey(), p.getValue()));
		});
		pl.sync();
		jedis.close();
	}
	
	public void save() {
		getResource().save();
	}
	
	public void bsave() {
		getResource().bgsave();
	}
	
	public void destroy() {
		try {
			pool.destroy();
		}
		catch(JedisException ex) {
			throw new RedisManagerRunTimeException(getClass().getSimpleName() + "", ex.getCause());
		}
	}
}
