package com.ds.demo.moc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dai.sha
 * @version V1.0
 * @Title: HelloController.java
 * @Package com.ds.demo.moc.controller
 * @Description
 * @date 2018 12-04 14:32.
 */
@RestController
@RequestMapping("/")
public class HelloController {

	@RequestMapping("/hello")
	public String hello(){
		return "<h1>Welcome to our community</h1>";
	}
}
