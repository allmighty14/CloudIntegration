package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class AccountService {

	private Map<String, MyAccount> accounts = new HashMap<>();
	private Map<String, List<MyAccount>> accountByName = new HashMap<>();

	private List<MyAccount> allAccount = new ArrayList<>();

	public void saveAccount(MyAccount account) {
		allAccount.add(account);
		accounts.put(account.getName(), account);
		if (accountByName.containsKey(account.getName())) {
			accountByName.get(account.getName()).add(account);
		} else {
			List<MyAccount> accountList = new ArrayList<MyAccount>();
			accountList.add(account);
			accountByName.put(account.getName(), accountList);
		}
	}

	public MyAccount getAccount(String id) {
		return accounts.get(id);
	}

	public List<MyAccount> getAllAccount(){ return this.allAccount; }

	public List<MyAccount> getAccounts(String name) {
		return accountByName.get(name);
	}


	public List<MyAccount> updateAccount(String name, MyAccount account) {
		if (accountByName.containsKey(name)) {
			List<MyAccount> accountList = accountByName.get(name);
			for (MyAccount accountInList : accountList) {
				if (accountInList.getName().equals(account.getName())) {
					accountInList.setAmount(account.getAmount());
					accountInList.setNumber(account.getNumber());
				}
			}
		}
		return accountByName.get(name);
	}

	public void deleteAccount(String name) {
		if (accountByName.containsKey(name)) {
			List<MyAccount> accountList = accountByName.get(name);
			for (MyAccount accountInList : accountList) {
				accounts.remove(accountInList.getName());
			}
			accountByName.remove(name);
		}
	}
}
