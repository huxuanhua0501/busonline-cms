package net.busonline.api.service;

import java.util.Map;

import net.busonline.core.model.Response;
import net.busonline.api.model.vo.BusLine;

public interface IBusGatherService {
	public Response insertBusInfo(BusLine busLine) throws Exception;
	public Response finBusLineInfo(Map<String,Object> map)throws Exception;
	public Response findBusDictionary(Map<String,Object>map)throws Exception;
	public Response validateBusLineName(Map<String,Object> map)throws Exception;
}
