package net.busonline.api.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import net.busonline.api.model.vo.BusLine;
import net.busonline.api.model.vo.Common;

public interface IBusGatherMapper {
	public int insertBusLine(BusLine busLine);
	public void insertBusBite(Map<String,Object> map);
	public List<String> finBusLineInfo(Map<String,Object> map);
	public List<Common> findCity(Map<String,Object> map);
	public List<Common> findSegment(String parent_id);
}
