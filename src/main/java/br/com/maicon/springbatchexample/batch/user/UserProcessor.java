package br.com.maicon.springbatchexample.batch.user;

import br.com.maicon.springbatchexample.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserProcessor implements ItemProcessor<User, User> {

    private static final Logger logger = LoggerFactory.getLogger(UserProcessor.class);

    private static final Map<String, String> DEPT_NAMES = new HashMap<>();

    public UserProcessor() {
        DEPT_NAMES.put("001", "Technology");
        DEPT_NAMES.put("002", "Operation");
        DEPT_NAMES.put("003", "Accounts");
    }

    @Override
    public User process(User user) throws Exception {
        logger.info("Converting User: " + user);

        user.setDept(DEPT_NAMES.get(user.getDept()));

        logger.info("Converted User: " + user);

        return user;
    }
}
