package Redis;
import redis.clients.jedis.Jedis;
public class Redis_article {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("123456");
		jedis.select(6);
		
		jedis.sadd("board:6:tags", "�߱�","�ߨ�","�P��","�a�H","�B��","�a�_","���O");
		
		jedis.sadd("post:11:tags", "�߱�","�ߨ�");
		jedis.sadd("post:12:tags", "�߱�","�P��");
		jedis.sadd("post:13:tags", "�߱�");
		jedis.sadd("post:14:tags", "�߱�","�a�_");
		jedis.sadd("post:15:tags", "�߱�","�a�H");
		jedis.sadd("post:16:tags", "�߱�","�ߨ�","�a�H","�B��");
		jedis.sadd("post:17:tags", "�߱�","���O");
		jedis.sadd("post:18:tags", "�߱�","���O","�a�H");
		jedis.sadd("post:19:tags", "�߱�","���O","�P��");
		jedis.sadd("post:20:tags", "�߱�");
		
		jedis.sadd("tag:�߱�:posts", "11", "12", "13","14","15","16","17","18","19","20");
		jedis.sadd("tag:�P��:posts", "12", "19");
		jedis.sadd("tag:�a�_:posts", "14");
		jedis.sadd("tag:�ߨ�:posts", "11","16");
		jedis.sadd("tag:�a�H:posts", "16","18");
		jedis.sadd("tag:�B��:posts", "16");
		jedis.sadd("tag:���O:posts","17","18","19");
		

		
		
		jedis.close();
	}
}
