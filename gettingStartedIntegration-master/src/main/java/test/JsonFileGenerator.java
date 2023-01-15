package test;

import org.springframework.integration.file.FileNameGenerator;
import org.springframework.messaging.Message;

public class JsonFileGenerator implements FileNameGenerator {
    @Override
    public String generateFileName(Message<?> message) {
        MyAccount myAccount = (MyAccount) message.getPayload();
        return myAccount.getName() + ".json";
    }
}
