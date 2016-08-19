package net.busonline.api.model.vo;

import java.util.List;

/**
 * 公交线路
 * @author win7
 *
 */
public class BusLine {
	private int id;
	private String line_id;
	private String linename;
	private String linename_bd;
	private String linkdir;
	private String city_id;
	private String linetype; //A网
	private String price;
	private String start_time;
	private String end_time;
	private List<Site> site;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLine_id() {
		return line_id;
	}
	public void setLine_id(String line_id) {
		this.line_id = line_id;
	}
	
	public String getLinename() {
		return linename;
	}
	public void setLinename(String linename) {
		this.linename = linename;
	}
	public String getLinename_bd() {
		return linename_bd;
	}
	public void setLinename_bd(String linename_bd) {
		this.linename_bd = linename_bd;
	}
	public String getLinkdir() {
		return linkdir;
	}
	public void setLinkdir(String linkdir) {
		this.linkdir = linkdir;
	}
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	public String getLinetype() {
		return linetype;
	}
	public void setLinetype(String linetype) {
		this.linetype = linetype;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public List<Site> getSite() {
		return site;
	}
	public void setSite(List<Site> site) {
		this.site = site;
	}
	
	
	

}
