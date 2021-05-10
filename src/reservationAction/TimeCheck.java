package reservationAction;

import java.util.ArrayList;

public class TimeCheck {
	public static String[] timeCheck(ArrayList<String> infolist) {
		String[] time = new String[20];
		String check;
		for (int i = 0; i < infolist.size(); i++) {
			check = infolist.get(i).substring(11, 16);
			switch (check) {
			case "09:00":
				time[0] = check;
				break;
			case "09:30":
				time[1] = check;
				break;
			case "10:00":
				time[2] = check;
				break;
			case "10:30":
				time[3] = check;
				break;
			case "11:00":
				time[4] = check;
				break;
			case "11:30":
				time[5] = check;
				break;
			case "12:00":
				time[6] = check;
				break;
			case "12:30":
				time[7] = check;
				break;
			case "13:00":
				time[8] = check;
				break;
			case "13:30":
				time[9] = check;
				break;
			case "14:00":
				time[10] = check;
				break;
			case "14:30":
				time[11] = check;
				break;
			case "15:00":
				time[12] = check;
				break;
			case "15:30":
				time[13] = check;
				break;
			case "16:00":
				time[14] = check;
				break;
			case "16:30":
				time[15] = check;
				break;
			case "17:00":
				time[16] = check;
				break;
			case "17:30":
				time[17] = check;
				break;
			case "18:00":
				time[18] = check;
				break;
			case "18:30":
				time[19] = check;
				break;
			default:
				break;

			}

		}
		return time;
	}
	
	public static String timeSet(String time) {
		switch (time) {
		case "0":
			time = "09:00";
			break;
		case "1":
			time = "09:30";
			break;
		case "2":
			time = "10:00";
			break;
		case "3":
			time = "10:30";
			break;
		case "4":
			time = "11:00";
			break;
		case "5":
			time = "11:30";
			break;
		case "6":
			time = "12:00";
			break;
		case "7":
			time = "12:30";
			break;
		case "8":
			time = "13:00";
			break;
		case "9":
			time = "13:30";
			break;
		case "10":
			time = "14:00";
			break;
		case "11":
			time = "14:30";
			break;
		case "12":
			time = "15:00";
			break;
		case "13":
			time = "15:30";
			break;
		case "14":
			time = "16:00";
			break;
		case "15":
			time = "16:30";
			break;
		case "16":
			time = "17:00";
			break;
		case "17":
			time = "17:30";
			break;
		case "18":
			time = "18:00";
			break;
		case "19":
			time = "18:30";
			break;
		default:
			break;
		}
	return time;
	}
}
