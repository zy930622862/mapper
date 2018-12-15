package com.baizhi.mapper;

import com.baizhi.entity.User;
import com.baizhi.entity.UserExample;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Test
    public void test() {
        List<User> list = userMapper.selectAll();//查所有
        for (User user : list) {
            System.out.println(user);
        }                
    }
    @Test
    public void test1(){
        User user = userMapper.selectByPrimaryKey(1);//通过主键查询
        System.out.println(user);
    }
    @Test
    public void test2(){
        User t = new User();
        t.setId(1);
        t.setName("luxw");
       // t.setPassword("123456");
        List<User> select = userMapper.select(t);//根据指定条件查询
        for (User user : select) {
            System.out.println(user+"11111111111111111111111111111111");
        }
    }
    @Test
    public void test3(){
        int i = userMapper.selectCount(new User());//查询数据个数
        System.out.println(i);
    }
    @Test
    public void test4(){
        User user = new User("zhaoy","123456");
        int i =  userMapper.insert(user);//添加数据，主键可以自己指定，也可以为空，添加成功后
        // 可以通过user的主属性来获取ID值
        System.out.println(i);
        System.out.println(user.getId());
    }
    @Test
    public void test5(){
        int i = userMapper.deleteByPrimaryKey(8);//通过主键删除数据
        System.out.println(i);
    }
    @Test
    public void test6(){
        User user = new User(12,"zhaoyue","123456");
        userMapper.updateByPrimaryKey(user);//通过主键修改数据
    }
    @Test
    public void test7(){
        User user = new User(13,"zy",null);
        int i = userMapper.updateByPrimaryKeySelective(user);//通过主键修改数据  为空则不修改
        System.out.println(i);
    }
    @Test
    public void test8(){
        UserExample userExample = new UserExample();

        userExample.createCriteria().andIdBetween(10L,14L).andUsernameLike("zh%");
        userExample.or().andIdLessThan(10L);
        List<User> users = userMapper.selectByExample(userExample);
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void Test9(){//测试分页
        User user = new User(null,null,"123456");
        RowBounds rowBounds = new RowBounds(1,4);//第一个参数是起始下标 第二个参数是每页的条
     //   数
        List<User> users = userMapper.selectByRowBounds(user,rowBounds);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }
}

