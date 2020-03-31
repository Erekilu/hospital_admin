package top.lsyweb.hosadm.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-31
 */
@Component
public class RedisUtil
{

	@Autowired
	private JedisPool jedisPool;

	/**
	 * 向Redis中存值，永久有效
	 */
	public String set(String key, String value)
	{
		Jedis jedis = null;
		try
		{
			jedis = jedisPool.getResource();
			return jedis.set(key, value);
		}
		catch (Exception e)
		{
			return "0";
		}
		finally
		{
			returnResource(jedisPool, jedis);
		}
	}

	/**
	 * 根据传入Key获取指定Value
	 */
	public String get(String key)
	{
		Jedis jedis = null;
		String value;
		try
		{
			jedis = jedisPool.getResource();
			value = jedis.get(key);
		}
		catch (Exception e)
		{
			return "0";
		}
		finally
		{
			returnResource(jedisPool, jedis);
		}
		return value;
	}

	/**
	 * 校验Key值是否存在
	 */
	public Boolean exists(String key)
	{
		Jedis jedis = null;
		try
		{
			jedis = jedisPool.getResource();
			return jedis.exists(key);
		}
		catch (Exception e)
		{
			return false;
		}
		finally
		{
			returnResource(jedisPool, jedis);
		}
	}

	/**
	 * 删除指定Key-Value
	 */
	public Long delete(String key)
	{
		Jedis jedis = null;
		try
		{
			jedis = jedisPool.getResource();
			return jedis.del(key);
		}
		catch (Exception e)
		{
			return 0L;
		}
		finally
		{
			returnResource(jedisPool, jedis);
		}
	}

	/**
	 * 存int[]
	 * @param array 要存的数组
	 * @param name 数组在redis的保存名
	 */
	public void setIntArray(int [] array, String name)
	{
		ObjectMapper mapper = new ObjectMapper();
		// 存值
		try
		{
			String jsonString = mapper.writeValueAsString(array);
			this.set("testArray", jsonString);
		}
		catch (JsonProcessingException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 取int []
	 * @param name 数组在redis的保存名
	 * @return
	 */
	public int[] getIntArray(String name)
	{
		//取值测试
		ObjectMapper mapper = new ObjectMapper();
		// 此时的值为 [x, x, x]
		String jsonString = this.get(name);
		String str2 = jsonString.substring(1);
		// 截取了左括号和右括号的str ，此时的值为 x, x, x
		String idsStr = str2.substring(0,str2.length()-1);
		// 分割逗号
		String[] idsStrArray = idsStr.split(",");
		int[] ids = new int[idsStrArray.length];
		for(int i=0; i < idsStrArray.length; i++)
		{
			ids[i] = Integer.parseInt(idsStrArray[i]);
		}
		return ids;
	}


	// 以上为常用方法，更多方法自行百度

	/**
	 * 释放连接
	 */
	private static void returnResource(JedisPool jedisPool, Jedis jedis)
	{
		if (jedis != null)
		{
			jedis.close();
		}
	}
}
