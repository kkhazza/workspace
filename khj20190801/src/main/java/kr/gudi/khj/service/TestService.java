package kr.gudi.khj.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.gudi.khj.dao.DaoInterface;

@Service
public class TestService implements ServiceInterface{
	
	@Autowired
	DaoInterface di;
	
	final String ADDR = "https://www.kbl.or.kr/stats/team_rank.asp";
	final String ADDR2 = "https://www.kbl.or.kr/stats/attack_defence_comparison.asp";

	@Override
	public HashMap<String, Object> getData() {
		return di.getData();
	}

	@Override
	public HashMap<String, Object> setData() {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		List<HashMap<String, Object>> paramList = getHTMLParser();
		List<HashMap<String, Object>> paramList2 = getHTMLParser2();
		int tot = 0;
		for(int i = 0; i < paramList.size(); i++) {
			tot += di.setData(paramList.get(i));
		}
		resultMap.put("TotalCount", paramList.size());
		resultMap.put("InsertCount", tot);
		System.out.println(resultMap.toString());
		return resultMap;
	}

	private List<HashMap<String, Object>> getHTMLParser() {
		List<HashMap<String, Object>> paramList = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> row;
		try {
			Document doc = Jsoup.connect(ADDR).get();
			Elements newsHeadlines = doc.select("tr");
			for (Element headline : newsHeadlines) {
				Elements newsHeadlines2 = headline.select("td");
				row = new HashMap<String, Object>();
				int i = 0;
				for (Element headline2 : newsHeadlines2) {
					String col = headline2.select("td").text();						
					row.put("col"+i, col);
					i++;
					System.out.print(col+"\t");
				}
				paramList.add(row);
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paramList;
	}
	
	private List<HashMap<String, Object>> getHTMLParser2() {
		List<HashMap<String, Object>> paramList2 = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> row2;
		try {
			Document doc = Jsoup.connect(ADDR2).get();
			Elements newsHeadlines = doc.select("tr");
			for (Element headline : newsHeadlines) {
				Elements newsHeadlines2 = headline.select("td");
				//	row2 = new HashMap<String, Object>();
				for (Element headline2 : newsHeadlines2) {
					String col2 = headline2.select("td").text();	
				//	row2.put("col2", col2);
					System.out.print(col2+"\t");
				}
				//paramList2.add(row2);
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paramList2;
	}
}
