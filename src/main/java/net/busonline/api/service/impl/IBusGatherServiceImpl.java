package net.busonline.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.busonline.core.model.Response;
import net.busonline.core.util.DButils;
import net.busonline.core.util.ProFileUtil;
import net.busonline.dictionary.service.impl.DictionaryService;
import net.busonline.api.dao.IBusGatherMapper;
import net.busonline.api.model.vo.BusLine;
import net.busonline.api.model.vo.Common;
import net.busonline.api.service.IBusGatherService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 公交信息采集业务类
 * @author win7
 *
 */
@Service
public class IBusGatherServiceImpl implements IBusGatherService{
	@Autowired
	private IBusGatherMapper iBusGatherMapper;
	public static Logger logger = LoggerFactory.getLogger(IBusGatherServiceImpl.class);
	/**
	 * 批量插入线路信息
	 */
	@Override
	public Response insertBusInfo(BusLine busLine) {
		Map<String,Object> param = new HashMap<String, Object>();
		BusLine busLineInfo = busLine;
		busLineInfo.setLine_id(getBusLineId(busLine.getLinename()));
		//插入线路信息
		iBusGatherMapper.insertBusLine(busLineInfo);
		//插入站点信息
		param.put("line_id", busLineInfo.getId());
		param.put("linkdir", busLine.getLinkdir());
		param.put("list", busLine.getSite());
		iBusGatherMapper.insertBusBite(param);
		return new Response().success();
	}
    /**
     * 查找已采集所有线路
     */
	@Override
	public Response finBusLineInfo(Map<String, Object> map) {
		Map<String,Object> result = new HashMap<String,Object>();
		List<String> busLine = iBusGatherMapper.finBusLineInfo(map);
		if(busLine.size()>0){
			result.put("busline", busLine);
			return new Response().success(result);
		}
		return new Response().failure("未找到采集线路", "500");
	}
    /**
     * 组装采集前段所需字典信息
     * @param map
     * @return
     */
	@Override
	public Response findBusDictionary(Map<String, Object> map) {
		Map<String,Object> result = new HashMap<String,Object>();
		List<Common> citys = iBusGatherMapper.findCity(map);
		String parent_id = ProFileUtil.getPropertiesValue("dictionary.properties", "segment");
		List<Common> segment = iBusGatherMapper.findSegment(parent_id);
		result.put("city", citys);
		result.put("linetype", segment);
		return new Response().success(result);
	}

	/**
	 * 验证线路名称
	 * @param map
	 * @return
	 */
	@Override
	public Response validateBusLineName(Map<String, Object> map) {
		String str = getBusLineId(map.get("buslinename").toString());
		if(null != str && !str.equals("")){
			return new Response().success();
		}
		return new Response().failure();
	}
	
	/**
	 * 根据线路名称
	 * @param busLine
	 * @return
	 */
	public String getBusLineId(String busLineName){
		Connection conn = DButils.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String str = null;
		try{
		  stmt = conn.createStatement();
		  String sql= "select id from bus_line where name = " + busLineName.trim();
		  rs = stmt.executeQuery(sql);
		  while (rs.next()){
			   str = rs.getString("id");
		  }
		}catch(SQLException e){
			logger.debug("数据库异常");
		}finally{
			DButils.closeResources(conn, null, rs);
        }
		return str;
	}
	
	
	
	
	

}
