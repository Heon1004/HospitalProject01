package reservationAction;

import java.util.List;

import model.ReservationDAO;

public class TimeCheck {
	public static String[] timeCheck(List<String> infolist) {
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

	public static String[] setNow(String[] date) {
		String minst = new ReservationDAO().getTodayDate().substring(14, 16);
		String hourst = new ReservationDAO().getTodayDate().substring(11, 13);
		int hour = Integer.parseInt(hourst); // 09
		int min = Integer.parseInt(minst); // 25
		if (min < 30) {
			minst = ":00";
		} else if (min > 30) {
			minst = ":00";
			hourst = Integer.toString(hour + 1);
		}
		String getNOW = hourst + minst; // 09:00 or 10:00 or 09:30
		String[] setDate = timeCheck(date, getNOW);
		for (int i = 0; i < setDate.length; i++) {
			if (setDate[i] != null && setDate[i].equals(getNOW)) {
				for (int j = 0; j <= i; j++) {
					setDate[j] = "x";
				}
			}
		}
		return setDate;
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

	public static String[] timeCheck(String[] infolist, String getNOW) {
		String check = getNOW;
		switch (check) {
		case "09:00":
			infolist[0] = check;
			break;
		case "09:30":
			infolist[1] = check;
			break;
		case "10:00":
			infolist[2] = check;
			break;
		case "10:30":
			infolist[3] = check;
			break;
		case "11:00":
			infolist[4] = check;
			break;
		case "11:30":
			infolist[5] = check;
			break;
		case "12:00":
			infolist[6] = check;
			break;
		case "12:30":
			infolist[7] = check;
			break;
		case "13:00":
			infolist[8] = check;
			break;
		case "13:30":
			infolist[9] = check;
			break;
		case "14:00":
			infolist[10] = check;
			break;
		case "14:30":
			infolist[11] = check;
			break;
		case "15:00":
			infolist[12] = check;
			break;
		case "15:30":
			infolist[13] = check;
			break;
		case "16:00":
			infolist[14] = check;
			break;
		case "16:30":
			infolist[15] = check;
			break;
		case "17:00":
			infolist[16] = check;
			break;
		case "17:30":
			infolist[17] = check;
			break;
		case "18:00":
			infolist[18] = check;
			break;
		case "18:30":
			infolist[19] = check;
			break;
		default:
			break;

		}
		return infolist;
	}
}
