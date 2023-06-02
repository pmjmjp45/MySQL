import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class insertData {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.cj.jdbc.Driver");// JDBC 드라이버 로딩
		// 데이터베이스와 연결
		Connection k14_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.63:33060/kopo14", "root", "0405");
		
		Statement k14_stmt = k14_conn.createStatement(); // 스테이트먼트 객체 생성
		//데이터 입력 insert into 테이블명 (필드) values (값)
		k14_stmt.execute("insert into examtable (name, studentid, kor, eng, mat) values ('나연', 209901, 95 , 100, 95);");
		k14_stmt.execute("insert into examtable (name, studentid, kor, eng, mat) values ('정연', 209902, 95 , 95, 95);");
		k14_stmt.execute("insert into examtable (name, studentid, kor, eng, mat) values ('모모', 209903, 100 , 100, 100);");
		k14_stmt.execute("insert into examtable (name, studentid, kor, eng, mat) values ('사나', 209904, 100 , 95, 90);");
		k14_stmt.execute("insert into examtable (name, studentid, kor, eng, mat) values ('지효', 209905, 80 , 100, 70);");
		k14_stmt.execute("insert into examtable (name, studentid, kor, eng, mat) values ('미나', 209906, 100 , 100, 70);");
		k14_stmt.execute("insert into examtable (name, studentid, kor, eng, mat) values ('다현', 209907, 70 , 70, 70);");
		k14_stmt.execute("insert into examtable (name, studentid, kor, eng, mat) values ('채영', 209908, 55 , 100, 60);");
		k14_stmt.execute("insert into examtable (name, studentid, kor, eng, mat) values ('쯔위', 209909, 50 , 80, 45);");
		
		// 열었던 반대 순서로 닫기
		k14_stmt.close(); // 스테이트먼트 닫기
		k14_conn.close(); // 커넥션 닫기
	}

}
