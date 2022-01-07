package org.wangjj.practice;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wangjj.practice.mapper.MpTestMapper;

/**
 * ClassName: MpTest <br/>
 * Function: <br/>
 * Reason:  <br/>
 * date: 2019/10/29 下午4:51 <br/>
 *
 * @author wangjunjie
 * @since JDK 1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootTestApplication.class)
public class MybatisPTest {

    @Autowired
    private MpTestMapper mpTestMapper;

    @Test
    public void test() {
//        MpTest mpTest = new MpTest();
//        mpTest.setAge(10);
//        mpTest.setName("test");
//        List<MpTest> s = mpTestMapper.selectList(null);
//        System.out.println(s);
        //mpTestMapper.insert(mpTest);
        // ================= 需要修改的配置 =================

        // 数据源配置
//        String jdbcUrl = "jdbc:mysql://mysql.test.baiwang-inner.com:3306/doc_db?useUnicode=true&allowMultiQueries=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
//        String jdbcDriver = "com.alibaba.druid.pool.DruidDataSource";
//        String jdbcUsername = "doc_db";
//        String jdbcPassword = "doc_db";

        String jdbcUrl = "jdbc:mysql://mysql.test.baiwang-inner.com:3306/v_ukey";
        String jdbcDriver = "com.alibaba.druid.pool.DruidDataSource";
        String jdbcUsername = "v_ukey";
        String jdbcPassword = "v_ukey";

        // 父级包名配置
        String parentPackage = "com.baiwang.deviceovercloud.ukeyservice.bean.entity";

        // 生成代码的 @author 值
        String author = "wangjunjie";

        // 要生成代码的表名配置
//        String[] tables = {
//                "doc_sk_device","doc_sk_device_op_record","doc_sk_dict","doc_sk_group"
//                ,"doc_sk_host_device","doc_sk_server"
//        };
        String[] tables = {
                "ukey_host_channel_bind"
        };
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("/Users/baiwang-077/Documents/mybatis_plus");
        gc.setAuthor(author);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        // 生成完毕后是否打开输出目录
        gc.setOpen(false);
        // 为true时生成entity将继承Model类，单类即可完成基于单表的业务逻辑操作，按需开启
        gc.setActiveRecord(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(jdbcUrl);
        dsc.setDriverName(jdbcDriver);
        dsc.setUsername(jdbcUsername);
        dsc.setPassword(jdbcPassword);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        // 父级包名，按需修改
        pc.setParent(parentPackage);
        // 设置模块名, 会在parent包下生成一个指定的模块包
        pc.setModuleName(null);
        mpg.setPackageInfo(pc);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setRestControllerStyle(false);
        strategy.setInclude(tables);
        strategy.setSuperEntityColumns("id");
        // Controller驼峰连字符，如开启，则requestMapping由 helloWorld 变为 hello-world 默认false
        strategy.setControllerMappingHyphenStyle(false);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        // 开启后将使用lombok注解代替set-get方法，false则生成set-get方法
        //strategy.setEntityLombokModel(true);
        // 在实体类中移除is前缀
        //strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.setCfg(new InjectionConfig() {
            @Override
            public void initMap() {

            }
        });
        mpg.execute();

    }
}
