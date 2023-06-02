package Stock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class stockData {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver");// JDBC 드라이버 로딩
		// 데이터베이스와 연결
		Connection k14_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.63:33060/kopo14", "root", "0405");
		k14_conn.setAutoCommit(false); // 오토커밋 안되게 설정

		// 데이터 입력하는 쿼리문 / prepared statement 이용하므로 ?로 표시
		String k14_sql = "insert into stock ("
				+ "bsop_date, shrn_iscd, stck_prpr, stck_oprc, stck_hgpr,"
				+ "stck_lwpr, fn_acml_vol, fn_acml_tr_pbmn)"
				+ "values (?,?,?,?,?,?,?,?)"; 
		PreparedStatement k14_pstmt = k14_conn.prepareStatement(k14_sql); // 대용량데이터 입력 위해 prepared statement 이용
		
		//파일 연결
		File k14_f = new File("C:\\Users\\kopo14\\Desktop\\강의자료\\데이터베이스프로그래밍(v2023)\\data\\day_data\\StockDailyPrice.csv");
		BufferedReader k14_br = new BufferedReader(new FileReader(k14_f));//버퍼리더 생성
		
		String k14_readtxt; // 문자열선언
		
		if ((k14_readtxt = k14_br.readLine()) == null) { // 빈 파일일 경우
			System.out.println("빈 파일입니다");
			return;
		}
		String[] k14_field_name = k14_readtxt.split(","); // 필드명
	
		int k14_LineCnt = 0; // 개수 세기
		long k14_start = System.currentTimeMillis(); // 시작 시간 찍기
		
		while((k14_readtxt = k14_br.readLine()) != null) { // 한 줄씩 읽음
			String[] k14_field = k14_readtxt.split(","); // 한 줄씩 배열에 저장
			k14_pstmt.setString(1, k14_field[1]); // prepared statement에 값 인덱스 맞춰 저장
			k14_pstmt.setString(2, k14_field[2]);
			k14_pstmt.setString(3, k14_field[3]);
			k14_pstmt.setString(4, k14_field[4]);
			k14_pstmt.setString(5, k14_field[5]);
			k14_pstmt.setString(6, k14_field[6]);
			k14_pstmt.setString(7, k14_field[11]);
			k14_pstmt.setString(8, k14_field[12]);
			k14_pstmt.addBatch(); // 캐시에 저장
			
			if (k14_LineCnt % 10000 == 0) { // 라인카운트 10000번마다 출력
				System.out.printf("%d번째 항목 addBatch ok\n", k14_LineCnt);
			}
			k14_pstmt.clearParameters(); //파라미터 삭제해서 새로 담을 수 있게 만듦
			k14_LineCnt++; // 카운터 올림
			
			try { 
				if (k14_LineCnt % 50000 == 0) { // 메모리 관리 위해 50000번마다
					k14_pstmt.executeBatch(); // 저장된 캐시 실행
					k14_pstmt.clearBatch(); // 캐시 삭제해서 새로 담을 수 있게 함
					k14_conn.commit(); // DB에 커밋
				} 
			} catch(Exception k14_e) { // 실행 실패하면 오류 문구 출력
				k14_e.printStackTrace();
			}
		
		try { 
			k14_pstmt.executeBatch(); // while 로프 끝난 뒤 남은 부분 실행
		} catch (Exception k14_e) { // 실행 실패하면 오류 문구 출력
			k14_e.printStackTrace();
		}
		
		}
		k14_conn.commit(); // DB에 커밋
		k14_conn.setAutoCommit(true); // 자동 커밋 재설정
		long k14_end = System.currentTimeMillis(); // 끝나는 시간 저장
		
		System.out.println("Insert End"); // 실행 완료 문구 출력
		System.out.printf("total : %d\n", k14_LineCnt); // 총 개수 출력
		System.out.printf("time  : %dms\n", k14_end - k14_start); // 걸린 시간 출력
		
		// 열었던 반대 순서로 닫기
		k14_br.close(); // 버퍼리더
		k14_pstmt.close(); // 스테이트먼트 닫기
		k14_conn.close(); // 커넥션 닫기
		
	}
}

