package DaoUser;

import com.hardziyevich.application.dao.DaoFactory;
import com.hardziyevich.application.dao.DaoUser;
import com.hardziyevich.application.domain.entity.Role;
import com.hardziyevich.application.domain.entity.User;
import com.hardziyevich.application.exception.DaoException;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hardziyevich.application.dao.connectionpool.ConnectionPoolFabric.PropertiesFile.*;
import static com.hardziyevich.application.dao.connectionpool.ConnectionPoolFabric.PropertiesFile.POOL_SIZE;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    @DisplayName("Create user.")
    void testCreteUser() throws DaoException{
        //given
        User userTest = User.builder()
                .firstName("pasha")
                .lastName("test")
                .email("test")
                .password("test")
                .type(Role.USER)
                .build();
        //when
        boolean result = daoUserImpl.create(userTest);
        //then
        assertTrue(result);
    }

    @Test
    @DisplayName("Find user by email.")
    void testSearchByEmail() throws DaoException {
        //given
        User user = User.builder()
                .id(BigDecimal.ONE)
                .firstName("Pasha")
                .lastName("Hardziyevich")
                .email("myemail@gmail.com")
                .password("123456")
                .type(Role.ADMIN)
                .build();
        //when
        List<User> result = daoUserImpl.findByEmail("myemail@gmail.com");
        //then
        assertEquals(List.of(user),result);
    }

    @Test
    @DisplayName("Find user by email that doesn`t exist in database.")
    void testSearchByEmailNull() throws DaoException {
        //given
        List<User> users = new ArrayList<>();
        //when
        List<User> result = daoUserImpl.findByEmail("test");
        //then
        assertEquals(users,result);
    }

    @Test
    @DisplayName("Find user by email and password.")
    void testSearchByEmailAndPassword() throws DaoException {
        //given
        User user = User.builder()
                .id(BigDecimal.ONE)
                .firstName("Pasha")
                .lastName("Hardziyevich")
                .email("myemail@gmail.com")
                .password("123456")
                .type(Role.ADMIN)
                .build();
        //when
        List<User> result = daoUserImpl.findByEmailAndPassword("myemail@gmail.com","123456");
        //then
        assertEquals(List.of(user),result);
    }

}
