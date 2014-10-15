package nsgsw1.netcare.alarm.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nsgsw1.netcare.model.res.Card;
import nsgsw1.netcare.model.res.Ctp;
import nsgsw1.netcare.model.res.Me;
import nsgsw1.netcare.model.res.Port;
import nsgsw1.netcare.model.res.Shelf;
import nsgsw1.netcare.model.res.constant.MeDeviceType;

public enum DeviceNameUtil {

	INSTANCE;

	private final static int CTP_RATE_VC4 = 15;
	private final static int CTP_RATE_VC3 = 13;
	private final static int CTP_RATE_VC12 = 11;
	private final static int CTP_RATE_PPI = 99;

	public String getMeInfo(Me me) {
		if (me.getType() == MeDeviceType.SDH)
			return me.getUserLabel();
		if (me.getType() == MeDeviceType.ACCESS)
			return me.getUserLabel();
		else
			return me.getName();
	}

	public String getShelfInfo(Shelf shelf) {
		return shelf.getUserLabel();
	}

	public String getCardInfo(Me me, Card card) {
		if (me.getType() == MeDeviceType.SDH)
			return card.getNo() + "-" + card.getName();
		else
			return card.getUserLabel();
	}

	public String getPtpInfo(Me me, Card card, Port port) {
		if (me.getType() == MeDeviceType.SDH)
			return card.getNo() + "-" + card.getName() + "-" + port.getNo();
		else if (me.getType() == MeDeviceType.SWITCH
				&& port.getAdditionalInfo() != null
				&& !port.getAdditionalInfo().equals(""))
			return port.getAdditionalInfo();
		else
			return port.getName();
	}

	public String getCtpInfo(Me me, Card card, Port port, Ctp ctp) {
		if (me.getType() == MeDeviceType.SDH) {
			String cardNo = "";
			String cardName = "";
			if (card == null) {
				Pattern p = Pattern.compile("slot=\\d+");
				Matcher m = p.matcher(ctp.getDn());
				if (m.find()) {
					cardNo = m.group().substring(5);
				}
			} else {
				cardNo = "" + card.getNo();
			}
			String portNo = "";
			if (port == null) {
				Pattern p = Pattern.compile("port=\\d+");
				Matcher m = p.matcher(ctp.getDn());
				if (m.find()) {
					portNo = m.group().substring(5);
				}
			} else {
				portNo = "" + port.getNo();
			}

			String info = cardNo + "-" + cardName + "-" + portNo;
			String slotInfo = getCtpTimeSlotInfo(ctp);
			if (!slotInfo.equals(""))
				info = info + "-" + slotInfo;
			return info;
		} else
			return ctp.getUserLabel();
	}

	private String getCtpTimeSlotInfo(Ctp ctp) {
		String info = "";
		switch (ctp.getRate().intValue()) {

		case CTP_RATE_VC4:
			if (ctp.getkNumber() != null)
				info = "VC4:" + ctp.getkNumber();
			break;
		case CTP_RATE_VC3:
			if (ctp.getkNumber() != null && ctp.getlNumber() != null)
				info = "VC4:" + ctp.getkNumber() + " VC3:" + ctp.getlNumber();
			break;
		case CTP_RATE_VC12:
			if (ctp.getkNumber() != null)
				info = "VC4:" + ctp.getkNumber() + " VC12:" + buildVC12TUG(ctp);
			break;
		case CTP_RATE_PPI:
			if (ctp.getkNumber() != null && ctp.getlNumber() != null
					&& ctp.getmNumber() == null && ctp.getnNumber() == null)
				info = "VC4:" + ctp.getkNumber() + " VC3:" + ctp.getlNumber();
			else if (ctp.getkNumber() != null && ctp.getlNumber() != null
					&& ctp.getmNumber() != null && ctp.getnNumber() != null)
				info = "VC4:" + ctp.getkNumber() + " VC12:" + buildVC12TUG(ctp);
			break;
		default:
			info = "";
		}
		return info;
	}

	private String buildVC12TUG(Ctp ctp) {
		if (ctp.getlNumber() != null && ctp.getmNumber() != null
				&& ctp.getnNumber() != null)
			return ""
					+ (ctp.getlNumber() + (ctp.getmNumber() - 1) * 3 + (ctp
							.getnNumber() - 1) * 21);
		else
			return "";
	}
}
