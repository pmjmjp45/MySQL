package wifiData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class wifiTable {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver");// JDBC 드라이버 로딩
		// 데이터베이스와 연결
		Connection k14_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.63:33060/kopo14", "root", "0405");
		
		Statement k14_stmt = k14_conn.createStatement(); // 스테이트먼트 객체 생성
		// 테이블 만드는 쿼리문
		k14_stmt.execute("create table freewifi("
				+ "inst_place varchar(200),"
				+ "inst_place_detail varchar(200),"
				+ "inst_city varchar(50),"
				+ "inst_country varchar(50),"
				+ "inst_place_flag varchar(50),"
				+ "service_provider varchar(50),"
				+ "wifi_ssid varchar(200),"
				+ "inst_date date,"
				+ "place_addr_road varchar(200),"
				+ "place_addr_land varchar(200),"
				+ "manage_office varchar(50),"
				+ "manage_office_phone varchar(50),"
				+ "latitude double,"
				+ "longitude double,"
				+ "write_date date,"
				+ "number int,"
				+ "primary key(number, inst_place)"
				+ ") DEFAULT CHARSET = utf8;"
				);
		
		// 열었던 반대 순서로 닫기
		k14_stmt.close(); // 스테이트먼트 닫기
		k14_conn.close(); // 커넥션 닫기
	}

}
