package net.busonline.dictionary.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface IDictionaryService {
	 public void  insertdict(String dictionaryname,String parentid);
     public String selectDicItme();
	 public List<Map<String,Object>> selectDicTwo(String id);
	 public String updateDicname(String dictionaryname,String id);
	 public String delDic(String id);
	 public String delDicTwo(String id);
	 public String selectDicItmeAll(); 
}
