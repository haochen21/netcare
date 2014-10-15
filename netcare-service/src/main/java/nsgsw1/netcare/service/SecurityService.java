package nsgsw1.netcare.service;

import java.util.Collection;

import nsgsw1.netcare.model.security.ResSyncRecord;
import nsgsw1.netcare.model.security.User;

public interface SecurityService {

	Collection<User> findUsersByPermission(String name);

	ResSyncRecord findResSyncRecordByType(ResSyncRecord.Type type);

	ResSyncRecord saveResSyncRecord(ResSyncRecord resSyncRecord);
}
