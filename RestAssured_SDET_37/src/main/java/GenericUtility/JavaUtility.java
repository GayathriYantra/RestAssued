package GenericUtility;

import java.util.Date;
import java.util.Random;

/**
 * 
 * @author SanjayBabu
 *
 */
public class JavaUtility {
	/**
	 * to get the random number
	 * @return
	 */
	public int getRandomNum()
	{
		Random random = new Random();
		int randNum = random.nextInt(1000);
		return randNum;
	}
	/**
	 * to get system date and time 
	 * @return
	 */
	public String getSystemDate()
	{
		Date date = new Date();
		String dateTime = date.toString();
		return dateTime;
	}
	/**
	 * to get system date and time in required format
	 * @return
	 */
	public String getSystemDateInFormat()
	{
		Date datte = new Date();
		String dateTime = datte.toString();
		String[] dateArr = dateTime.split(" ");

		int month = datte.getMonth()+1;
		String date = dateArr[2];
		String year = dateArr[5];
		int day = datte.getDay();
		String time = dateArr[3];

		String finalFormat = month+" "+date+" "+year+" "+day+" "+time;
		return finalFormat;
	}
}
