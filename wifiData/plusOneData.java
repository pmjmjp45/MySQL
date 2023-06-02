package wifiData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class plusOneData {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
//		// TODO Auto-generated method stub
//		Class.forName("com.mysql.cj.jdbc.Driver");// JDBC 드라이버 로딩
//		// 데이터베이스와 연결
//		Connection k14_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.63:33060/kopo14", "root", "0405");
//		k14_conn.setAutoCommit(false); // 오토커밋 안되게 설정
//
//		// 데이터 입력하는 쿼리문 / prepared statement 이용하므로 ?로 표시
//		String k14_sql = "insert into stock ("
//				+ "bsop_date, shrn_iscd, stck_prpr, stck_oprc, stck_hgpr,"
//				+ "stck_lwpr, fn_acml_vol, fn_acml_tr_pbmn)"
//				+ "values (?,?,?,?,?,?,?,?)"; 
//		Statement k14_stmt = k14_conn.createStatement();
		
		//파일 연결
		File k14_f = new File("C:\\Users\\kopo14\\Desktop\\강의자료\\데이터베이스프로그래밍(v2023)\\data\\day_data\\StockDailyPrice.csv");
		BufferedReader k14_br = new BufferedReader(new FileReader(k14_f));//버퍼리더 생성
	
		String k14_readtxt = k14_br.readLine();  // 한 줄 읽음
			
		String[] k14_field = k14_readtxt.split(","); // 필드 나눠서 배열에 저장
		System.out.println("<<<첫줄 데이터>>>"); // 출력
		System.out.printf("bsop_date: %s\nshrn_iscd: %s\nstck_prpr: %s\nstck_oprc: %s\nstck_hgpr: %s\n"
				+ "stck_lwpr: %s\nfn_acml_vol: %s\nfn_acml_tr_pbmn: %s\n", 
				k14_field[1], k14_field[2], k14_field[3], k14_field[4], k14_field[5],
				k14_field[6], k14_field[11], k14_field[12]);
		
		
//		String k14_readtxt2 = k14_br.readLine();  // 한 줄 읽음
//		
//		String[] k14_field2 = k14_readtxt2.split(","); // 한 줄씩 배열에 저장
//
//		for (int i = 0; i < k14_field2.length; i++) {
//			System.out.println(k14_field2[i]);
//		}
//		String QueryTxt = String.format("insert into stock ("
//				+ "bsop_date, shrn_iscd, stck_prpr, stck_oprc, stck_hgpr,"
//				+ "stck_lwpr, fn_acml_vol, fn_acml_tr_pbmn)"
//				+ "values ("
//				+ "'%s', '%s', '%s', '%s', '%s',"
//				+ "'%s, '%s', '%s');",
//				k14_field[1], k14_field[2], k14_field[3], k14_field[4], k14_field[5],
//				k14_field[6], k14_field[11], k14_field[12]);
//		k14_stmt.execute(QueryTxt); // 쿼리문 실행
//		
//				
//				
//			k14_conn.commit(); // DB에 커밋
//			k14_conn.setAutoCommit(true); // 자동 커밋 재설정
//			long k14_end = System.currentTimeMillis(); // 끝나는 시간 저장
//			
//			System.out.println("Insert End"); // 실행 완료 문구 출력
//			System.out.printf("total : %d\n", k14_LineCnt); // 총 개수 출력
//			System.out.printf("time  : %dms\n", k14_end - k14_start); // 걸린 시간 출력
//			
//			// 열었던 반대 순서로 닫기
//			k14_br.close(); // 버퍼리더
//			k14_pstmt.close(); // 스테이트먼트 닫기
//			k14_conn.close(); // 커넥션 닫기
//			
		}
	}
