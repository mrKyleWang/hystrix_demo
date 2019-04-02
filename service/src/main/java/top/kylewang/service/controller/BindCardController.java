package top.kylewang.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author KyleWang
 * @version 1.0
 * @date 2019年04月02日
 */
@RestController
public class BindCardController {

	@RequestMapping("queryBindCard")
	public String queryBindCard(Long userid) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "TS95271314-" + userid;
	}
}
