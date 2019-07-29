package top.kylewang.invoker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kylewang.invoker.service.AsyncRestTemplateService;
import top.kylewang.invoker.service.RestTemplateService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author KyleWang
 * @version 1.0
 * @date 2019年04月02日
 */
@RestController
public class InvokerController {

	@Autowired
	private RestTemplateService restTemplateService;

	@Autowired
	private AsyncRestTemplateService asyncRestTemplateService;

	@RequestMapping("query")
	public Map queryData(int id) {
		HashMap<Object, Object> map = new HashMap<>();
		switch (id) {
		case 0:
			map.put("data", restTemplateService.queryData(id));
			break;
		case 1:
			map.put("data", asyncRestTemplateService.queryData(id));
			break;
		}
		return map;
	}
}
