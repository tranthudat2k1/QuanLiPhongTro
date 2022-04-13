package qlpt.controller;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import qlpt.entity.PhongEntity;

@Transactional
@Controller
@RequestMapping("room/")
public class RoomController {
	
	@Autowired
	SessionFactory factory;
	@RequestMapping("index")
	public String index(ModelMap model,@ModelAttribute("room") PhongEntity room)
	{
		List<PhongEntity> rooms = this.getRooms();
		model.addAttribute("rooms",rooms);
		return "room/index";
	}
	
	public List<PhongEntity> getRooms() {
		Session session = factory.getCurrentSession();
		String hql = "FROM PhongEntity";
		Query query = session.createQuery(hql);
		List<PhongEntity> list = query.list();
		return list;
	}
	
}
