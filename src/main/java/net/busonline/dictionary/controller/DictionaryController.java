package net.busonline.dictionary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.busonline.dictionary.service.IDictionaryService;

@RestController
@RequestMapping("/dict")
public class DictionaryController {
	@Autowired
	IDictionaryService dictionaryService;

	@RequestMapping(value = "/insertdict", method = { RequestMethod.GET, RequestMethod.POST })
	public String insertdict(String dictionaryname, String parentid) {

		dictionaryService.insertdict(dictionaryname, parentid);
		return null;

	}

}
