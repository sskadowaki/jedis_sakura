package jedis_sakura.sample.digester;

import java.io.File;
import java.io.IOException;

import org.apache.commons.digester3.BeanPropertySetterRule;
import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.ObjectCreateRule;
import org.apache.commons.digester3.SetNextRule;
import org.xml.sax.SAXException;

import jedis_sakura.sample.digester.redis.RedisConfigDto;
import jedis_sakura.sample.digester.redis.RedisConfigRedisConfigPoolDto;
import jedis_sakura.sample.digester.redis.RedisConfigRedisPoolDto;

public class RedisDigester {

	private static final RedisDigester self = new RedisDigester();
	private MiddlewareConfigDto middlewareDto;
	
	private RedisDigester() {
		build();
	}
	
	private void build() {
		Digester di = new Digester();
		di.addRule("middleware", new ObjectCreateRule(MiddlewareConfigDto.class));
		di.addRule("middleware/redis", new ObjectCreateRule(RedisConfigDto.class));
		di.addRule("middleware/redis", new SetNextRule("setRedisDto"));
		
		di.addRule("middleware/redis/redisPoolConfig", new ObjectCreateRule(RedisConfigRedisConfigPoolDto.class));
		di.addRule("middleware/redis/redisPoolConfig", new SetNextRule("setRcp"));
		di.addRule("middleware/redis/redisPoolConfig/maxIdle", new BeanPropertySetterRule("maxIdle"));
		di.addRule("middleware/redis/redisPoolConfig/maxWait", new BeanPropertySetterRule("maxWait"));
		di.addRule("middleware/redis/redisPoolConfig/maxActive", new BeanPropertySetterRule("maxActive"));
		
		di.addRule("middleware/redis/redisPool", new ObjectCreateRule(RedisConfigRedisPoolDto.class));
		di.addRule("middleware/redis/redisPool", new SetNextRule("setRp"));
		di.addRule("middleware/redis/redisPool/host", new BeanPropertySetterRule("host"));
		di.addRule("middleware/redis/redisPool/port", new BeanPropertySetterRule("port"));
		di.addRule("middleware/redis/redisPool/timeout", new BeanPropertySetterRule("timeout"));
		
		try {
			this.middlewareDto = di.parse(new File("/Users/potakong/Documents/workspace/jedis_sakura/src/test/resources/redis/test.xml"));
		} catch (IOException | SAXException e) {
			e.printStackTrace();
			throw new RuntimeException(getClass().getSimpleName(), e.getCause());
		}
	}
	
	public static RedisDigester getInstance() {
		return self;
	}
	
	public RedisConfigDto getRedisConfig() {
		return this.middlewareDto.getRedisDto();
	}
}
