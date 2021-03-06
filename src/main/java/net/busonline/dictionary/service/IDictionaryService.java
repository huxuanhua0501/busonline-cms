package net.busonline.dictionary.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface IDictionaryService {
	 public String  insertdict(String dictionaryname,String parentid,String userid);
     public String selectDicItme();
	 public String selectDicTwo(String id);
	 public String updateDicname(String dictionaryname,String id,String userid);
	 public String delDic(String id);
	 public String delDicTwo(String id);
	 public String selectDicOne(); 
	 public List<Map<String,Object>> selectDicByName(String dictionaryname); 
}
