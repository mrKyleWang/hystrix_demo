package top.kylewang.invoker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kylewang.invoker.service.RestTemplateService;

import java.util.HashMap;
import java.util.Map;

@RestController
public class InvokerController {

	@Autowired
	private RestTemplateService restTemplateService;

	@RequestMapping("query")
	public Map queryData(int id) {
		HashMap<Object, Object> map = new HashMap<>();
		map.put("data", restTemplateService.queryData(id));
		return map;
	}
}
