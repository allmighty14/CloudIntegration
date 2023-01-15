package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.*;
import test.AccountService;
import test.MyAccount;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/accounts")
@ImportResource("classpath:aggragate.xml")
public class AppController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private MessageChannel channelEntree;

    @Autowired
    private MessageChannel aggregatorChannel;

    @PostMapping
    public void createAccount(@RequestBody MyAccount account) {
        aggregatorChannel.send(MessageBuilder.withPayload(account).build());
        accountService.saveAccount(account);
    }

    @GetMapping("/{name}")
    public List<MyAccount> getAccounts(@PathVariable String name) {
        return accountService.getAccounts(name);
    }

    @GetMapping
    public List<MyAccount> getAllAccount() {
        return accountService.getAllAccount();
    }

    @PutMapping("/{name}")
    public void updateAccount(@PathVariable String name, @RequestBody MyAccount account) {
        accountService.updateAccount(name, account);
    }

    @DeleteMapping("/{name}")
    public void deleteAccount(@PathVariable String name) {
        accountService.deleteAccount(name);
    }

    public static void main(String[] args) {
        //ApplicationContext ctx = new ClassPathXmlApplicationContext("aggragate.xml");
        SpringApplication.run(AppController.class, args);
    }

}

