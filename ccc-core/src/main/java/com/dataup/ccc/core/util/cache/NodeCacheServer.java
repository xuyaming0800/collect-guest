package com.dataup.ccc.core.util.cache;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import autonavi.online.framework.util.serializable.KryoUtils;


@Service
public class NodeCacheServer {
	private Logger log = LogManager.getLogger(getClass());
	@Autowired
	private JedisPool jedisPool = null;
	private String cacheKey = null;
	
	private static KryoUtils kryoUtils = new KryoUtils();
	
	public String getCacheKey() {
		return cacheKey;
	}

	public void setCacheKey(String cacheKey) {
		this.cacheKey = cacheKey;
	}
	
	/**
	 * 根据key删除缓存的value数值
	 * @param key
	 * @throws Exception
	 */
	public void removeCacheValue(String key) throws Exception {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.del(key.getBytes());
		}catch(Exception e) {
			log.error("getCacheValue error ", e);
			throw e;
		}finally{
			if(null != jedis) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	
	public String getCacheValue(String key)  throws Exception {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
//			byte []result = jedis.hget(cacheKey.getBytes(), key.getBytes());
			byte []result =  jedis.get(key.getBytes());
			if(null != result) {
				return new String(result);
			}else {
				return null;
			}
			
		}catch(Exception e) {
			log.error("getCacheValue error ", e);
			throw e;
		}finally{
			if(null != jedis) {
				jedisPool.returnResource(jedis);
			}
		}
	}
	
	
	public void putCacheValue(String key, String value, int second) throws Exception {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
//			jedis.hset(cacheKey.getBytes(), key.getBytes(), (value == null ? null : value.getBytes()));
		
			jedis.setex(key, second, value);
			
		}catch(Exception e) {
			log.error("putCacheValue error ", e);
			throw e;
		}finally{
			if(null != jedis) {
				jedisPool.returnResource(jedis);
			}
		}
	}
	
	public void putCacheValue(String key, String value) throws Exception {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
//			jedis.hset(cacheKey.getBytes(), key.getBytes(), (value == null ? null : value.getBytes()));
			jedis.set(key, value);
		}catch(Exception e) {
			log.error("putCacheValue error ", e);
			throw e;
		}finally{
			if(null != jedis) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	public boolean addNode(Node node){
		if(node!=null && StringUtils.hasText(cacheKey)){
			Jedis jedis = null;
			try {
				byte[] nodeBytes = kryoUtils.serialize(node);
				jedis = jedisPool.getResource();

	/*			Map<byte[], byte[]> nodesMap = jedis.hgetAll(cacheKey.getBytes());
				nodesMap.put(node.getId().getBytes(), nodeBytes);
				jedis.hmset(cacheKey.getBytes(), nodesMap);*/
				
				jedis.hset(cacheKey.getBytes(), node.getId().getBytes(), nodeBytes);
				
				return true;
			}catch(Exception e) {
				log.error("增加node error ", e);
				return false;
			}finally {
				if(null != jedis) {
					jedisPool.returnResource(jedis);
				}
			}

		}
		return false;
	}
	
	

	public Node getNodeById(String id) {
		if(!StringUtils.hasText(id) || !StringUtils.hasText(cacheKey)) {
			return null;
		}
		
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
	/*		Map<byte[], byte[]> nodesMap = jedis.hgetAll(cacheKey.getBytes());
			byte[] nodeBytes = nodesMap.get(id.getBytes());*/
			
			byte[] nodeBytes = jedis.hget(cacheKey.getBytes(), id.getBytes());
			if(null == nodeBytes) {
				return null;
			}
			Node node = kryoUtils.deserialize(nodeBytes, Node.class);
			return node;
		}catch(Exception e) {
			log.error("获取node error ", e);
			return null;
		}finally {
			if(null != jedis) {
				jedisPool.returnResource(jedis);
			}
		}
		
		
	}

	public Node getParent(Node n) {

		if (n != null)
			return getNodeById(n.getParent());

		return null;
	}

	public Node getParent(String id) {
		return getParent(getNodeById(id));
	}

	public List<Node> getChild(Node n) {

		if (n != null) {
			List<Node> list = new ArrayList<Node>();
			List<String> c = n.getChild();
			if(c!=null){
				for (int i = 0; i < c.size(); i++) {
					n = getNodeById(c.get(i));
					if (n != null)
						list.add(n);
				}
			}
			return list;
		}

		return null;
	}

	public List<Node> getChild(String id) {
		return getChild(getNodeById(id));
	}

	public List<Node> getAllParent(Node n) {

		if (n != null && n.getParent() != null) {
			List<Node> list = new ArrayList<Node>();
			while ((n = getParent(n)==n?null:getParent(n)) != null) 
				list.add(n);
			return list;
		}

		return null;
	}

	public List<Node> getAllParent(String id) {
		return getAllParent(getNodeById(id));
	}

	public List<Node> getAllChild(Node n) {
		if (n != null) {
			List<Node> total = new ArrayList<Node>();
			List<Node> c = getChild(n);
			if (c != null) {
				total.addAll(c);
				do {
					List<Node> cc = new ArrayList<Node>();
					for (int i = 0; i < c.size(); i++) {
						List<Node> temp = getChild(c.get(i));
						if (temp != null) {
							total.addAll(temp);
							cc.addAll(temp);
						}
					}
					c = cc;
				} while (c.size() > 0);

				return total;
			}
		}

		return null;
	}

	
	public List<Node> getAllChild(String  id){
		return getAllChild(getNodeById(id));
	}
	
	/**
	 * root Node return 1;
	 * null Node return -1;
	 * @param n
	 * @return
	 */
	public int getNodeLevel(Node n){
		if (n != null) {
			if(n.getParent()==null)
				return 1;
			
			int level = 1;
			while ((n = getParent(n)==n?null:getParent(n)) != null) 
				level++;
			return level;
		}

		return -1;
	}
	
	public int getNodeLevel(String id){
		return getNodeLevel(getNodeById(id));
	}
}
