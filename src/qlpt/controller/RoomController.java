package qlpt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("room/")
public class RoomController {
	@RequestMapping("index")
	public String index()
	{
		return "room/index";
	}
}
