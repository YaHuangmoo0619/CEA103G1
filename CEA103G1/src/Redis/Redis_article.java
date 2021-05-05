package Redis;
import redis.clients.jedis.Jedis;
public class Redis_article {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("123456");
		jedis.select(6);
		
		jedis.sadd("board:6:tags", "心情","心事","感情","家人","朋友","靠北","壓力");
		
		jedis.sadd("post:11:tags", "心情","心事");
		jedis.sadd("post:12:tags", "心情","感情");
		jedis.sadd("post:13:tags", "心情");
		jedis.sadd("post:14:tags", "心情","靠北");
		jedis.sadd("post:15:tags", "心情","家人");
		jedis.sadd("post:16:tags", "心情","心事","家人","朋友");
		jedis.sadd("post:17:tags", "心情","壓力");
		jedis.sadd("post:18:tags", "心情","壓力","家人");
		jedis.sadd("post:19:tags", "心情","壓力","感情");
		jedis.sadd("post:20:tags", "心情");
		
		jedis.sadd("tag:心情:posts", "11", "12", "13","14","15","16","17","18","19","20");
		jedis.sadd("tag:感情:posts", "12", "19");
		jedis.sadd("tag:靠北:posts", "14");
		jedis.sadd("tag:心事:posts", "11","16");
		jedis.sadd("tag:家人:posts", "16","18");
		jedis.sadd("tag:朋友:posts", "16");
		jedis.sadd("tag:壓力:posts","17","18","19");
		

		
		
		jedis.close();
	}
}
