package net.busonline.dictionary.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DictionaryMapper {
	/**
	 * 字典入库
	 * @param map
	 */
 public void  insertdict(@Param("map")Map<String,Object>map);
 /**
  * 查询所有词典
  * @return
  */
 public List<Map<String,Object>> selectDicItme();
 /**
  * 查询二级词典
  * @param map
  * @return
  */
 public List<Map<String,Object>> selectDicTwo(@Param("id")String id);
 /**
  * 更新名字
  * @param map
  */
 public void updateDicname(@Param("map")Map<String,Object>map);
 /**
  * 删除一级及它的子级别
  * @param map
  */
 public void delDic(@Param("id")String id);
 /**
  * 删除二级
  * @param map
  */
 public void delDicTwo(@Param("id")String id);
 /**
  * 查询所有一级字典
  * @return
  */
 public List<Map<String,Object>> selectDicOne(); 

}
