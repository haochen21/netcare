package nsgsw1.netcare.cnsalarm.jms;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmsOperateResultReceive implements MessageListener {

	private SendEmsOperateResult sendEmsOperateResult;

	private final static Logger logger = LoggerFactory
			.getLogger(AlarmEventReceive.class);

	public SendEmsOperateResult getSendEmsOperateResult() {
		return sendEmsOperateResult;
	}

	public void setSendEmsOperateResult(SendEmsOperateResult sendEmsOperateResult) {
		this.sendEmsOperateResult = sendEmsOperateResult;
	}

	@Override
	public void onMessage(Message message) {
		if (message instanceof MapMessage) {
			MapMessage mapMessage = (MapMessage) message;
			try {
				String emsName = mapMessage.getString("emsName");
				String operateName = mapMessage.getString("operate");
				boolean result = mapMessage.getBoolean("result");
				sendEmsOperateResult.sendOperateInfo(emsName, operateName,
						result);
			} catch (Exception ex) {
				logger.info("parse message error");
			}
		}
	}

}
