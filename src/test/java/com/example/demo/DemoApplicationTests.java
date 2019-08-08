package com.example.demo;

import com.example.demo.activeMQ.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	@Resource
	private Producer producer;

	@Test
	public void contextLoads() {
		producer.sendMsg("test.queue","activeMQ:hello");
	}

}
