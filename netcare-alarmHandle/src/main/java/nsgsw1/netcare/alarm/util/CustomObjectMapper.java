package nsgsw1.netcare.alarm.util;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class CustomObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 2672187615647976692L;

	public CustomObjectMapper() {
		SimpleModule module = new SimpleModule("ObjectIdmodule");
		module.addSerializer(ObjectId.class, new ObjectIdSerializer());
		this.registerModule(module);
	}
}
