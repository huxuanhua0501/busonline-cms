package net.busonline.dictionary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.busonline.dictionary.service.IDictionaryService;
/**
 * 主要是字典表的操作接口
 * @author xuanhua.hu
 *
 */
@RestController
@RequestMapping("/dict")
public class DictionaryController {
	@Autowired
	IDictionaryService dictionaryService;
	/**
	 * 入库操作
	 * @param dictionaryname
	 * @param parentid
	 * @return
	 */

	@RequestMapping(value = "/insertdict", method = { RequestMethod.GET, RequestMethod.POST })
	public String insertdict(String dictionaryname, String parentid,String userid) {
		return  dictionaryService.insertdict(dictionaryname, parentid,userid);

	}
	/**
	 *  查询所有词典
	 * @return
	 */
	@RequestMapping(value = "/selectDicItme", method = { RequestMethod.GET, RequestMethod.POST })
	public String selectDicItme() {
		return  dictionaryService.selectDicItme();

	}
	/**
	 * 查询二级
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/selectDicTwo", method = { RequestMethod.GET, RequestMethod.POST })
	public String selectDicTwo(String id) {
		return  dictionaryService.selectDicTwo(id);

	}
	/**
	 * 更新名字
	 * @param dictionaryname
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/updateDicname", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateDicname(String dictionaryname, String id,String userid) {
		return  dictionaryService.updateDicname(dictionaryname, id,userid);

	}
	/**
	 * 删除字典（二级同时删除）
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delDic", method = { RequestMethod.GET, RequestMethod.POST })
	public String delDic(String id) {
		return  dictionaryService.delDic(id);

	}
	/**
	 * 删除二级字典中的某一个
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delDicTwo", method = { RequestMethod.GET, RequestMethod.POST })
	public String delDicTwo(String id) {
		return  dictionaryService.delDic(id);

	}
	/**
	 * 查询一级字典
	 * @return
	 */
	@RequestMapping(value = "/selectDicOne", method = { RequestMethod.GET, RequestMethod.POST })
	public String selectDicOne() {
		return  dictionaryService.selectDicOne();

	}
}
