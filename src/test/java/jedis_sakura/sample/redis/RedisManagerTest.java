package jedis_sakura.sample.redis;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import jedis_sakura.sample.util.Pair;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class RedisManagerTest {
	
	private RedisManager rm;
	
	@Before
	public void setup() {
		rm = RedisManager.getInstance();
	}

	@Test
	public void test() {
		final String key = "setHoge";
		this.rm.set(key, "firstSet");
		assertThat(this.rm.get(key), is("firstSet"));
		this.rm.del(key);
	}

	@Test
	public void succession() {
		IntStream.range(0, 100).forEach(v -> {
			this.rm.set(String.valueOf(v), String.valueOf(Math.pow(v, 2)));
		});
		IntStream.range(0, 100).forEach(v -> {
			assertThat(this.rm.get(String.valueOf(v)), is(String.valueOf(Math.pow(v, 2))));
			this.rm.del(String.valueOf(v));
		});
	}
	
	@Test
	public void pipeline() {
		List<Pair<String, String>> kvList = new ArrayList<Pair<String,String>>();
		IntStream.range(0, 100).forEach(v -> {
			kvList.add(new Pair<String, String>(String.valueOf(v), String.valueOf(Math.pow(v, 3))));
		});
		this.rm.pipeline(kvList);
		IntStream.range(0, 100).forEach(v -> {
			assertThat(this.rm.get(String.valueOf(v)), is(String.valueOf(Math.pow(v, 3))));
			this.rm.del(String.valueOf(v));
		});
	}
}
