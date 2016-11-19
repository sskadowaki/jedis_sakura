package jedis_sakura.sample.util;

import java.util.AbstractMap;


public class Pair<K, V> extends AbstractMap.SimpleEntry<K, V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5588525967230651527L;

	public Pair(K k, V v) {
		super(k, v);
	}
}
