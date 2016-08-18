package net.busonline.api.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.busonline.api.dao.IBusGatherMapper;
import net.busonline.api.model.Response;
import net.busonline.api.model.vo.BusLine;
import net.busonline.api.service.IBusGatherService;

/**
 * 公交信息采集业务类
 * @author win7
 *
 */
@Service
public class IBusGatherServiceImpl implements IBusGatherService{
	@Autowired
	private IBusGatherMapper iBusGatherMapper;
	
	//批量插入线路信息
	@Override
	public Response insertBusInfo(BusLine busLine) {
		//插入线路信息
		BusLine busInfo = iBusGatherMapper.insertBusLine(busLine);
		//插入站点信息
		
		
		
		
		return null;
	}

	@Override
	public Response finBusLineInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response findBusDictionary(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response findBusLineName(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response validateBusLineName(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
