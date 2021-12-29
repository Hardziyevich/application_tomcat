package DaoUser;

import com.hardziyevich.application.dao.ConnectionPool;
import com.hardziyevich.application.dao.impl.DaoFactory;
import com.hardziyevich.application.dao.impl.UserDao;
import com.hardziyevich.application.domain.entity.Role;
import com.hardziyevich.application.domain.entity.User;
import com.hardziyevich.application.execption.DaoException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserDaoTest {

    ConnectionPool connectionPool = ConnectionPool.INSTANCE;
    private UserDao userDao = DaoFactory.testUserDao(connectionPool);

    @BeforeAll
    void destroyConnection() throws DaoException {
        connectionPool.destroyPool();
    }

    @Test
    void testCreteUser() throws DaoException {
        User userTest = User.builder()
                .firstName("pasha")
                .lastName("test")
                .login("test")
                .password("test")
                .type(Role.USER)
                .build();

        userDao.create(userTest);
    }
}
