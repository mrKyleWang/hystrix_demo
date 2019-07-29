package top.kylewang.service.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author KyleWang
 * @version 1.0
 * @date 2019年04月02日
 */
@RestController
public class DataController {

	@RequestMapping("queryData")
	public String queryBindCard(int id) {
		long sleepTime = 10000;
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return id + "_" + UUID.randomUUID().toString();
	}
}
