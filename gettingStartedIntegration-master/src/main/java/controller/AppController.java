package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.*;
import test.AccountService;
import test.MyAccount;

import java.util.List;

@RestController
@ImportResource("classpath:aggragate.xml")
public class AppController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MessageChannel channelEntree;

    @PostMapping("/accounts")
    public void createAccount(@RequestBody MyAccount account) {
        channelEntree.send(MessageBuilder.withPayload(account).build());
    }

    @GetMapping("/accounts/{name}")
    public List<MyAccount> getAccounts(@PathVariable String name) {
        return accountService.getAccounts(name);
    }

}
