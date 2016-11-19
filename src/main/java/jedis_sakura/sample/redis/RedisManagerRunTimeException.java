package jedis_sakura.sample.redis;

public class RedisManagerRunTimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RedisManagerRunTimeException(String m) {
		super(m);
	}
	
	public RedisManagerRunTimeException(Throwable t) {
		super(t);
	}

	public RedisManagerRunTimeException(String s, Throwable t) {
		super(s, t);
	}
}
