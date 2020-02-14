package com.offcn.demon;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext-redis.xml")
public class TestRedis {
@Autowired
    private RedisTemplate redisTemplate;
/*
* set设置值
* */
@Test
public void setValue(){
    redisTemplate.boundValueOps("ujiuye").set("java");

}
/*
* get取值
* */
@Test
public void getValue(){
    String ujiuye = (String) redisTemplate.boundValueOps("ujiuye").get();
    System.out.println(ujiuye);
}
/*
* delete删除key
* */
@Test
    public void delete(){
    redisTemplate.delete("ujiuye");
}
/*
* set集合添加操作add
* */
@Test
    public void setList(){
    redisTemplate.boundSetOps("nameset").add("虎宁");
    redisTemplate.boundSetOps("nameset").add("张三");
    redisTemplate.boundSetOps("nameset").add("李四");
}
/*
* 取值members()
* */
@Test
    public void get(){
    Set nameset = redisTemplate.boundSetOps("nameset").members();
    System.out.println(nameset);
}
/*
* 删除集合中的某一个值remove("李四")
* */
@Test
    public void deleteSetOne(){
    redisTemplate.boundSetOps("nameset").remove("李四");
}
/*
* 删除整个set集合delete
* */
@Test
public void deleteSetAll(){
    redisTemplate.delete("nameset");
}
/*
* list集合操作
* */

/*
* 右压栈
* 添加顺序从左向右依次排序rightPush
* 后添加的对象添加在右面
* */
@Test
public void listright(){
    redisTemplate.boundListOps("namelist").rightPush("刘备");
    redisTemplate.boundListOps("namelist").rightPush("关羽");
    redisTemplate.boundListOps("namelist").rightPush("张飞");
}
/*
* 显示右压栈集合range
* */
@Test
  public void getListright(){
      List namelist = redisTemplate.boundListOps("namelist").range(0, 10);
      System.out.println(namelist);
  }
  /*
  * 左压栈
  * 后添加的对象往左排
  * */
  @Test
    public void listLeft(){
      redisTemplate.boundListOps("namelist2").leftPush("哈哈");
      redisTemplate.boundListOps("namelist2").leftPush("嘻嘻");
      redisTemplate.boundListOps("namelist2").leftPush("嚯嚯");
  }
  /*
  * 显示左压栈集合
  * */
  @Test
    public void getListLeft(){
      List namelist2 = redisTemplate.boundListOps("namelist2").range(1, 10);
      System.out.println(namelist2);
  }
  /*
  * 查询list集合某个元素index
  * */
  @Test
    public void findListOne(){
     String findOne = (String) redisTemplate.boundListOps("namelist2").index(3);
      System.out.println(findOne);
  }
  /*
  * 移除list集合中的某一个元素
  * */
  @Test
    public void removeOne(){
      redisTemplate.boundListOps("namelist2").remove(1,"哈哈");
      redisTemplate.boundListOps("namelist2").remove(2,"嘻嘻");
  }
  /*
  * 操作hash集合put(key,value)
  * */
  @Test
    public void testHash(){
      redisTemplate.boundHashOps("kk").put("a","唐僧");
      redisTemplate.boundHashOps("kk").put("b","孙悟空");
      redisTemplate.boundHashOps("kk").put("c","猪八戒");
      redisTemplate.boundHashOps("kk").put("d","沙僧");
  }
  /*
  *取值所有的小key
  * keys()set接收
  * */
  @Test
  public void getHashList(){
      Set kk = redisTemplate.boundHashOps("kk").keys();
      System.out.println(kk);
  }
  /*
  * 提取小key对应的所有值
  * values()  list接收
  * */
  @Test
  public void getHashListValue(){
      List kk = redisTemplate.boundHashOps("kk").values();
      System.out.println(kk);
  }

  /*
  * 根据小key查询对应的值get(key)
  * */
  @Test
    public void findKeyValue(){
      Object o = redisTemplate.boundHashOps("kk").get("c");
      System.out.println(o);
  }
  /*
  * 根据key值移除value
  * */
@Test
public void testRemoveValueByKey(){
    redisTemplate.boundHashOps("kk").delete("c");
}
}
