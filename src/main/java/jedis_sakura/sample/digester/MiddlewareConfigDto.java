package jedis_sakura.sample.digester;

import jedis_sakura.sample.digester.redis.RedisConfigDto;

public class MiddlewareConfigDto {

	private RedisConfigDto redisDto;

	public RedisConfigDto getRedisDto() {
		return redisDto;
	}

	public void setRedisDto(RedisConfigDto redisDto) {
		this.redisDto = redisDto;
	}
}
