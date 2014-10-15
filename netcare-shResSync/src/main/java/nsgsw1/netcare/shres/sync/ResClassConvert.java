package nsgsw1.netcare.shres.sync;

import nsgsw1.netcare.model.res.Card;
import nsgsw1.netcare.model.res.Ctp;
import nsgsw1.netcare.model.res.Ems;
import nsgsw1.netcare.model.res.Me;
import nsgsw1.netcare.model.res.Port;
import nsgsw1.netcare.model.res.Region;
import nsgsw1.netcare.model.res.RoomInfo;
import nsgsw1.netcare.model.res.Shelf;
import nsgsw1.netcare.model.res.Slot;
import nsgsw1.netcare.model.res.Vendor;
import nsgsw1.netcare.model.res.constant.MeDeviceType;
import nsgsw1.netcare.shres.model.ResCard;
import nsgsw1.netcare.shres.model.ResCtp;
import nsgsw1.netcare.shres.model.ResEms;
import nsgsw1.netcare.shres.model.ResMe;
import nsgsw1.netcare.shres.model.ResPort;
import nsgsw1.netcare.shres.model.ResRegion;
import nsgsw1.netcare.shres.model.ResRoomInfo;
import nsgsw1.netcare.shres.model.ResShelf;
import nsgsw1.netcare.shres.model.ResSlot;
import nsgsw1.netcare.shres.model.ResVendor;

public enum ResClassConvert {

	INSTANCE;

	public Ems convertEms(ResEms resEms) {
		Ems ems = new Ems();
		ems.setName(resEms.getName());
		ems.setNativeEmsName(resEms.getNativeEmsName());
		ems.setUserLabel(resEms.getUserLabel());
		if (resEms.getRegion() != null)
			ems.setRegion(convertRegion(resEms.getRegion()));
		if (resEms.getVendor() != null)
			ems.setVendor(convertVendor(resEms.getVendor()));
		return ems;
	}

	public Vendor convertVendor(ResVendor resVendor) {
		Vendor vendor = new Vendor();
		vendor.setName(resVendor.getName());
		vendor.setApprev(resVendor.getApprev());
		return vendor;
	}

	public Region convertRegion(ResRegion resRegion) {
		Region region = new Region();
		region.setNameCn(resRegion.getNameCn());
		region.setAbbrevCn(resRegion.getAbbrevCn());
		region.setCode(resRegion.getCode());
		return region;
	}

	public RoomInfo convertRoomInfo(ResRoomInfo resRoomInfo) {
		RoomInfo roomInfo = new RoomInfo();
		roomInfo.setRoomNo(resRoomInfo.getRoomNo());
		roomInfo.setChinaName(resRoomInfo.getChinaName());
		roomInfo.setRoomType(resRoomInfo.getRoomType());
		roomInfo.setRentUnit(resRoomInfo.getRentUnit());
		return roomInfo;
	}

	public Me convertMe(ResMe resMe) {
		Me me = new Me();
		me.setDn(resMe.getDn());
		me.setName(resMe.getName());
		me.setUserLabel(resMe.getUserLabel());
		me.setType(MeDeviceType.getMeDeviceTypeByOrdinal(resMe.getDeviceType()
				.ordinal()));
		me.setProductName(resMe.getProductName());
		me.setSoftwareVersion(resMe.getSoftwareVersion());
		if (resMe.getEms() != null)
			me.setEms(convertEms(resMe.getEms()));
		if (resMe.getRoomInfo() != null)
			me.setRoomInfo(convertRoomInfo(resMe.getRoomInfo()));
		return me;
	}

	public Shelf convertShelf(ResShelf resShelf) {
		Shelf shelf = new Shelf();
		shelf.setDn(resShelf.getDn());
		shelf.setNo(resShelf.getNo());
		shelf.setUserLabel(resShelf.getUserLabel());
		shelf.setFullCnName(resShelf.getFullCnName());
		return shelf;
	}

	public Slot convertSlot(ResSlot resSlot) {
		Slot slot = new Slot();
		slot.setDn(resSlot.getDn());
		slot.setNo(resSlot.getNo());
		return slot;
	}

	public Card convertCard(ResCard resCard) {
		Card card = new Card();
		card.setDn(resCard.getDn());
		card.setName(resCard.getName());
		card.setNativeEmsName(resCard.getNativeEmsName());
		card.setNo(resCard.getNo());
		card.setUserLabel(resCard.getUserLabel());
		card.setFullCnName(resCard.getFullCnName());
		return card;
	}

	public Port convertPort(ResPort resPort) {
		Port port = new Port();
		port.setName(resPort.getName());
		port.setNativeEmsName(resPort.getNativeEmsName());
		port.setNo(resPort.getNo());
		port.setDn(resPort.getDn());
		port.setPtpRate(resPort.getPtpRate());
		port.setAdditionalInfo(resPort.getAdditionalInfo());
		return port;
	}

	public Ctp convertCtp(ResCtp resCtp) {
		Ctp ctp = new Ctp();
		ctp.setDn(resCtp.getDn());
		ctp.setName(resCtp.getName());
		ctp.setFullCnName(resCtp.getFullCnName());
		ctp.setkNumber(resCtp.getkNumber());
		ctp.setlNumber(resCtp.getlNumber());
		ctp.setnNumber(resCtp.getnNumber());
		ctp.setNativeEmsName(resCtp.getNativeEmsName());
		ctp.setAdditionalInfo(resCtp.getAdditionalInfo());
		ctp.setUserLabel(resCtp.getUserLabel());
		ctp.setRate(resCtp.getRate());
		return ctp;
	}
}
