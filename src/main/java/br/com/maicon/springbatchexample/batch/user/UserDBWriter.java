package br.com.maicon.springbatchexample.batch.user;

import br.com.maicon.springbatchexample.model.User;
import br.com.maicon.springbatchexample.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserDBWriter implements ItemWriter<User> {

    private static final Logger logger = LoggerFactory.getLogger(ItemWriter.class);

    private final UserRepository userRepository;

    @Override
    public void write(List<? extends User> users) throws Exception {
        logger.info("Data saved for users: " + users);
        userRepository.saveAll(users);
    }
}
