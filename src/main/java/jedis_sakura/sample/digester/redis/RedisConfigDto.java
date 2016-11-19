package jedis_sakura.sample.digester.redis;

public class RedisConfigDto {

	private RedisConfigRedisConfigPoolDto rcp;
	private RedisConfigRedisPoolDto rp;
	
	public RedisConfigRedisConfigPoolDto getRcp() {
		return rcp;
	}
	public void setRcp(RedisConfigRedisConfigPoolDto rcp) {
		this.rcp = rcp;
	}
	public RedisConfigRedisPoolDto getRp() {
		return rp;
	}
	public void setRp(RedisConfigRedisPoolDto rp) {
		this.rp = rp;
	}
}
