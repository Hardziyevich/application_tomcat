package DaoUser;

import com.hardziyevich.application.dao.DaoFactory;
import com.hardziyevich.application.dao.DaoUser;
import com.hardziyevich.application.domain.entity.Role;
import com.hardziyevich.application.domain.entity.User;
import com.hardziyevich.application.exception.DaoException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.HashMap;
import java.util.Map;

import static com.hardziyevich.application.dao.connectionpool.ConnectionPoolFabric.PropertiesFile.*;
import static com.hardziyevich.application.dao.connectionpool.ConnectionPoolFabric.PropertiesFile.POOL_SIZE;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DaoUserImplTest {

    @Container
    private static final PostgreSQLContainer<TestContainer> postgreSQLContainer = TestContainer.getContainer();

    private static DaoUser daoUserImpl;

    @BeforeAll
    void createContainer() throws DaoException {
        postgreSQLContainer.start();
        while (!postgreSQLContainer.isCreated());
        Map<String, String> attributes = new HashMap<>();
        attributes.put(PASSWORD_KEY, postgreSQLContainer.getPassword());
        attributes.put(USERNAME_KEY, postgreSQLContainer.getUsername());
        attributes.put(URL_KEY, postgreSQLContainer.getJdbcUrl());
        attributes.put(POOL_SIZE, "5");
        daoUserImpl = DaoFactory.flexibleUserDao(attributes);
    }

    @AfterAll
    void destroyContainer() {
        postgreSQLContainer.stop();
    }

    @Test
    void testCreteUser() throws DaoException{
        User userTest = User.builder()
                .firstName("pasha")
                .lastName("test")
                .login("test")
                .password("test")
                .type(Role.USER)
                .build();
        assertTrue(daoUserImpl.create(userTest));
    }
}
