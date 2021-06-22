package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import util.DBUtil;

public class CloudDao {
	public static Map<String, Integer>getallmax(){
		String sql="select * from lunwen";
		Map<String, Integer>map=new HashMap<String, Integer>();
		Connection conn=null;
		Statement state=null;
		ResultSet res=null;
		conn=DBUtil.getConn();
		try {
			state=conn.createStatement();
			res=state.executeQuery(sql);
			while(res.next()) {
				String timu=res.getString("Timu");
				String[]keyword=timu.split(" ");
				for(int i=0; i<keyword.length; i++) {
					if(map.get(keyword[i])==null) {
						map.put(keyword[i], 0);
					}else {
						map.replace(keyword[i], map.get(keyword[i])+1);
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		DBUtil.close(res, state, conn);
		Map<String, Integer>sorted=new LinkedHashMap<>();
		map.entrySet().stream()
		   .sorted(Map.Entry.<String,Integer>comparingByValue().reversed())
		   .forEachOrdered(x->sorted.put(x.getKey(), x.getValue()));
		return sorted;
	}
}
