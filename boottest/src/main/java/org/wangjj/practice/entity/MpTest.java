package org.wangjj.practice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * ClassName: MqTest <br/>
 * Function: <br/>
 * Reason:  <br/>
 * date: 2019/10/29 下午4:27 <br/>
 *
 * @author wangjunjie
 * @since JDK 1.8
 */
@TableName(value = "mp_test")//指定表名
public class MpTest {
    @TableId(value = "id",type = IdType.AUTO)//指定自增策略
    private Integer id;
    private String name;
    private int age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
