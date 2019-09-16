package kr.gudi.khj.dao;

import java.util.HashMap;

public interface DaoInterface {
	
	public HashMap<String, Object> getData();
	//public int delData(HashMap<String, Object> param);
	public int setData(HashMap<String, Object> param);	
	//public void clear();
}
