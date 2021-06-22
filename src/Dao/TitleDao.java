package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.DBUtil;

public class TitleDao {
	public static String searchTitle(String word) {
		JSONArray jsonarrayC=new JSONArray();
		Connection con=DBUtil.getConn();
		Statement state=null;
		//读取数据库
		String sql="select Timu from lunwen where Timu like '%"+word+"%'";
		String timustr="";
		ResultSet res=null;
		try {
			JSONObject jsonobC=new JSONObject();
			state=con.createStatement();
			res=state.executeQuery(sql);
			while(res.next()) {
				timustr=timustr+res.getString("Timu")+"，";
			}
			res.close();
			String str[]=timustr.split("，");
			for(int i=0;i<str.length;i++) {
				sql="select Lianjie from lunwen where Timu='"+str[i]+"'";
				res=state.executeQuery(sql);
				res.next();
				String lianjie=res.getString("Lianjie");
				String Rlianjie="http://openaccess.thecvf.com/"+lianjie;
				jsonobC.put("Title", str[i]);
				jsonobC.put("Link", Rlianjie);
				res.close();
				//写入JSONArray数组
				jsonarrayC.add(jsonobC);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//类型转换，返回
		return jsonarrayC.toString();
	}
}
