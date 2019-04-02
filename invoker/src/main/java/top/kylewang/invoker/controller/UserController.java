package top.kylewang.invoker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.kylewang.invoker.entity.User;
import top.kylewang.invoker.service.UserService;

/**
 * @author KyleWang
 * @version 1.0
 * @date 2019年04月02日
 */
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("queryUser")
	public User queryUser(long userid) {
		return userService.queryUser(userid);
	}
}
