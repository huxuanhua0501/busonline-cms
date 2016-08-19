package net.busonline.core.util;
import java.io.InputStream;
import java.util.Properties;

public class ProFileUtil {
	/**
	 * 根据文件位置，和键读取值
	 * 
	 * @param path
	 * @param key
	 * @return
	 */
	public static String getPropertiesValue(String path, String key) {
		return new ProFileUtil().readValues(path, key);
	}
	protected String readValues(String path, String key) {
		Properties props = new Properties();
		try {
			InputStream in = this.getClass().getClassLoader()
					.getResourceAsStream(path);
			props.load(in);
			String value = props.getProperty(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
