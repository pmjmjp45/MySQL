package Stock;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class testTime {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int millitime =  39467962;
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
		String time = sdf.format(new Timestamp(millitime));
	}

}
