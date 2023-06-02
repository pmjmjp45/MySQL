package cctv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;

public class dataCCTV {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver");// JDBC 드라이버 로딩
		// 데이터베이스와 연결
		Connection k14_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.63:33060/kopo14", "root", "0405");
		
		Statement k14_stmt = k14_conn.createStatement(); // 스테이트먼트 객체 생성
		//파일 연결
		File k14_f = new File("C:\\Users\\kopo14\\Desktop\\강의자료\\데이터베이스프로그래밍(v2023)\\data\\전국cctv표준데이터2.txt");
		BufferedReader k14_br = new BufferedReader(new FileReader(k14_f));//버퍼리더 생성
		
		String k14_readtxt; // 문자열선언
		
		if ((k14_readtxt = k14_br.readLine()) == null) { // 빈 파일일 경우
			System.out.println("빈 파일입니다");
			return;
		}
		String[] field_name = k14_readtxt.split("\t"); // 필드명
	
		int LineCnt = 0; // 개수 세기

		while((k14_readtxt = k14_br.readLine()) != null) { // 한 줄씩 읽음

			String[] field = k14_readtxt.split("\t"); // 한 줄씩 배열에 저장
			String QueryTxt; // 쿼리문 
		
			// 한줄씩 칼럼에 값 입력하는 쿼리문
			QueryTxt = String.format("insert into cctv ("
					+ "number, managing, address_road, address_land, purpose,"
					+ "num_camera, graphic_camera, information, save_due, install_day,"
					+ "managing_phone, latitude, longitude, write_day)"
					+ "values ("
					+ "'%s', '%s', '%s', '%s', '%s', "
					+ "'%s', '%s', '%s', '%s', '%s', "
					+ "'%s', %s, %s, '%s');",
					field[0], field[1], field[2], field[3], field[4],
					field[5], field[6], field[7], field[8], field[9],
					field[10], field[11], field[12], field[13]);
			k14_stmt.execute(QueryTxt); // 쿼리문 실행
			
			System.out.printf("%d번째 항목 Insert OK\n", LineCnt); // 진행 상황 출력
			
			LineCnt++;// 카운터
		
		}
		// 열었던 반대 순서로 닫기
		k14_br.close(); // 버퍼리더
		k14_stmt.close(); // 스테이트먼트 닫기
		k14_conn.close(); // 커넥션 닫기
		
		System.out.println("총 레코드 : " + LineCnt); // 총 레코드 수 출력
	}
}
