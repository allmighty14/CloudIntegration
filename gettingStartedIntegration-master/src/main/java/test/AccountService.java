package test;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountService {
	/*
	public MyAccount myService(MyAccount myAccount){
		System.out.println(myAccount);
		MyAccount ma = new MyAccount();
		ma.setAmount(myAccount.getAmount() + 200);
		ma.setName(myAccount.getName());
		ma.setNumber(myAccount.getNumber());
		return ma;
		//myAccount.setAmount(myAccount.getAmount() + 200);
		//return myAccount;
	}

	public MyAccount getAccount(String name){

	}*/
	private Map<String, List<MyAccount>> accounts = new HashMap<>();

	public void myService(MyAccount account) {
		if(!accounts.containsKey(account.getName()))
			accounts.put(account.getName(),new ArrayList<>());
		accounts.get(account.getName()).add(account);
	}

	public List<MyAccount> getAccounts(String name){
		return accounts.get(name);
	}




}
