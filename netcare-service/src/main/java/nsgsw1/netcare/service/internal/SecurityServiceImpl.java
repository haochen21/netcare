package nsgsw1.netcare.service.internal;

import java.util.Collection;

import nsgsw1.netcare.model.security.ResSyncRecord;
import nsgsw1.netcare.model.security.ResSyncRecord.Type;
import nsgsw1.netcare.model.security.User;
import nsgsw1.netcare.repository.security.ResSyncRecordRepository;
import nsgsw1.netcare.repository.security.UserRepository;
import nsgsw1.netcare.service.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private ResSyncRecordRepository resSyncRecordRepository;

	@Override
	public Collection<User> findUsersByPermission(String name) {
		return userRepository.findByPermission(name);
	}

	@Override
	public ResSyncRecord findResSyncRecordByType(Type type) {
		return resSyncRecordRepository.findByType(type);
	}

	@Override
	public ResSyncRecord saveResSyncRecord(ResSyncRecord resSyncRecord) {
		return resSyncRecordRepository.save(resSyncRecord);
	}
}
