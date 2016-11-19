package jedis_sakura.sample.digester;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class RedisDigesterTest {

	@Test
	public void test() {
		RedisDigester rd = RedisDigester.getInstance();
		
		assertThat(rd.getRedisConfig().getRcp(), not(nullValue()));
		assertThat(rd.getRedisConfig().getRp(), not(nullValue()));
		
		assertThat(rd.getRedisConfig().getRcp().getMaxWait(), is(-1));
		assertThat(rd.getRedisConfig().getRp().getHost(), is("127.0.0.1"));
	}

}
