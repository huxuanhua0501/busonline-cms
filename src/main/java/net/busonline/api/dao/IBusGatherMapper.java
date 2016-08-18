package net.busonline.api.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import net.busonline.api.model.vo.BusLine;

public interface IBusGatherMapper {
	public BusLine insertBusLine(BusLine busLine);
	public void insertBusBite(Map<String,Object> map);
	public List<String> finBusLineInfo(Map<String,Object> map);
	public List<String> findCity(Map<String,Object> map);
	
}
