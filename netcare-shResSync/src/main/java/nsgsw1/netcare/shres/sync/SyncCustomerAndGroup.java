package nsgsw1.netcare.shres.sync;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import nsgsw1.netcare.model.customer.Customer;
import nsgsw1.netcare.model.customer.CustomerGroup;
import nsgsw1.netcare.model.security.ResSyncRecord;
import nsgsw1.netcare.model.security.User;
import nsgsw1.netcare.service.CustomerService;
import nsgsw1.netcare.service.SecurityService;
import nsgsw1.netcare.shres.model.ResCustomer;
import nsgsw1.netcare.shres.model.ResCustomerGroup;
import nsgsw1.netcare.shres.service.ResCustomerService;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SyncCustomerAndGroup {

	@Autowired
	private ResCustomerService resCustomerService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private SecurityService securityService;

	private Collection<User> syncCustomerUsers;

	private ResSyncRecord resSyncRecord;

	private final static Logger logger = LoggerFactory
			.getLogger(SyncCustomerAndGroup.class);

	public SyncCustomerAndGroup() {

	}

	@Scheduled(cron="0 1 0 * * *")
	public void sync() {
		// 获取资源上次最后更新时间
		getLastUpdateTime();
		// 获取客户全量同步权限
		syncCustomerUsers = securityService
				.findUsersByPermission("SYNC_CUSTOMER");
		Map<String, CustomerGroup> customerGroupsMap = startSyncCustomerGroup();
		startSyncCustomer(customerGroupsMap);
		updateLastUpdateTime();
	}

	private void getLastUpdateTime() {
		resSyncRecord = securityService
				.findResSyncRecordByType(ResSyncRecord.Type.CUSTOMER);
		if (resSyncRecord == null) {
			resSyncRecord = new ResSyncRecord();
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 1990);
			resSyncRecord.setLastSyncTime(calendar.getTime());
			resSyncRecord.setResType(ResSyncRecord.Type.CUSTOMER);
		}
	}

	private void updateLastUpdateTime() {
		Date lastUpdateTime = resCustomerService.findLastUpdateTime();
		resSyncRecord.setLastSyncTime(lastUpdateTime);
		securityService.saveResSyncRecord(resSyncRecord);
	}

	/**
	 * Map -> key : customerGroup resId
	 * 
	 * @return
	 */
	private Map<String, CustomerGroup> startSyncCustomerGroup() {
		logger.info("start sync customerGroup......");
		List<ResCustomerGroup> resCustomerGroups = resCustomerService.findAll();
		List<CustomerGroup> customerGroups = resCustomerGroups
				.stream()
				.map(resCustomerGroup -> {
					CustomerGroup customerGroup = customerService
							.findCustomerGroupByResId(resCustomerGroup
									.getResId());
					if (customerGroup == null) {
						customerGroup = new CustomerGroup();
					}
					copyCustomerGroup(customerGroup, resCustomerGroup);
					customerGroup = customerService
							.saveCustomerGroup(customerGroup);
					return customerGroup;
				}).collect(Collectors.toList());
		logger.info("sync customerGroup end,size is:" + customerGroups.size());
		Map<String, CustomerGroup> customerGroupsMap = customerGroups.stream()
				.collect(
						Collectors.toMap(CustomerGroup::getResId,
								customerGroup -> customerGroup));
		return customerGroupsMap;
	}

	private void copyCustomerGroup(CustomerGroup customerGroup,
			ResCustomerGroup resCustomerGroup) {
		customerGroup.setName(resCustomerGroup.getName());
		customerGroup.setAlias(resCustomerGroup.getAlias());
		customerGroup.setPortrait(resCustomerGroup.getPortrait());
		customerGroup.setPhone(resCustomerGroup.getPhone());
		customerGroup.setEmail(resCustomerGroup.getEmail());
		customerGroup.setLinkMan(resCustomerGroup.getLinkMan());
		customerGroup.setAddress(resCustomerGroup.getAddress());
		customerGroup.setType(resCustomerGroup.getType());
		customerGroup.setLevel(resCustomerGroup.getLevel());
		customerGroup.setResId(resCustomerGroup.getResId());
		customerGroup.setUpdateTime(new Date());
		Set<ObjectId> userIds = syncCustomerUsers.stream().map(user -> {
			return user.getId();
		}).collect(Collectors.toSet());
		customerGroup.getUserIds().addAll(userIds);
	}

	private void startSyncCustomer(Map<String, CustomerGroup> customerGroupsMap) {
		Collection<ResCustomer> resCustomers = resCustomerService
				.findAllCustomerGroupRef(resSyncRecord.getLastSyncTime());
		List<Customer> customers = resCustomers
				.stream()
				.map(resCustomer -> {
					Customer customer = customerService
							.findCustomerByResId(resCustomer.getResId());
					if (customer == null) {
						customer = new Customer();
					}
					copyCustomer(customer, resCustomer, customerGroupsMap);
					customer = customerService.saveCustomer(customer);
					return customer;
				}).collect(Collectors.toList());
		logger.info("sync customer end,size is:" + customers.size());
	}

	private void copyCustomer(Customer customer, ResCustomer resCustomer,
			Map<String, CustomerGroup> customerGroupsMap) {
		customer.setName(resCustomer.getName());
		customer.setPhone(resCustomer.getPhone());
		customer.setEmail(resCustomer.getEmail());
		customer.setLinkMan(resCustomer.getLinkMan());
		customer.setAddress(resCustomer.getAddress());
		customer.setType(resCustomer.getType());
		customer.setLevel(resCustomer.getLevel());
		customer.setResId(resCustomer.getResId());
		customer.setUpdateTime(new Date());
		// 全量同步客户用户分配
		Set<ObjectId> userIds = syncCustomerUsers.stream().map(user -> {
			return user.getId();
		}).collect(Collectors.toSet());
		customer.getUserIds().addAll(userIds);
		// 大客户用户分配
		for (ResCustomerGroup resCustomerGroup : resCustomer
				.getResCustomerGroups()) {
			if (customerGroupsMap.containsKey(resCustomerGroup.getResId())) {
				CustomerGroup customerGroup = customerGroupsMap
						.get(resCustomerGroup.getResId());
				customer.getUserIds().addAll(customerGroup.getUserIds());
			}
		}
		Set<ObjectId> customerGroupIds = resCustomer
				.getResCustomerGroups()
				.stream()
				.filter(resCustomerGroup -> customerGroupsMap
						.containsKey(resCustomerGroup.getResId()))
				.map(resCustomerGroup -> {
					CustomerGroup customerGroup = customerGroupsMap
							.get(resCustomerGroup.getResId());
					return customerGroup.getId();
				}).collect(Collectors.toSet());
		customer.getCustomerGroupIds().addAll(customerGroupIds);
	}
}
