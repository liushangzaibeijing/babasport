package cn.babasport.xiu.test.commons;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * spring的通用的测试类
 * 需要使用spring环境进行测试时，只需要继承该类即可
 * @author xieqixiu
 *
 */


//让测试运行在spring的测试环境
@RunWith(SpringJUnit4ClassRunner.class)
//读取配置文件
@ContextConfiguration(locations = "classpath:application-context.xml")
public class SpringJunit {
	
}