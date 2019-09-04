package top.kylewang.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DataController {

	@RequestMapping("queryData")
	public String queryData() {
		// 等待5s后，返回随机字符串
		long sleepTime = 5000;
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return UUID.randomUUID().toString();
	}
}
