package net.busonline.api.service;

import java.util.Map;

import net.busonline.api.model.Response;
import net.busonline.api.model.vo.BusLine;

public interface IBusGatherService {
	public Response insertBusInfo(BusLine busLine);
	public Response finBusLineInfo(Map<String,Object> map);
	public Response findBusDictionary(Map<String,Object>map);
	public Response findBusLineName(Map<String,Object> map);
	public Response validateBusLineName(Map<String,Object> map);
}
