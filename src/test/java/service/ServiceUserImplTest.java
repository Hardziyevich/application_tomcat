package service;

import com.hardziyevich.application.dao.DaoUser;
import com.hardziyevich.application.dao.Specification;
import com.hardziyevich.application.dao.connectionpool.ConnectionPool;
import com.hardziyevich.application.dao.connectionpool.ConnectionPoolFabric;
import com.hardziyevich.application.dao.impl.DaoUserImpl;
import com.hardziyevich.application.dao.impl.FindUserByEmailSpecification;
import com.hardziyevich.application.domain.entity.Role;
import com.hardziyevich.application.domain.entity.User;
import com.hardziyevich.application.domain.service.ServiceUser;
import com.hardziyevich.application.domain.service.ServiceUserFactory;
import com.hardziyevich.application.domain.service.dto.UserDto;
import com.hardziyevich.application.exception.DaoException;
import com.hardziyevich.application.exception.ServiceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.hardziyevich.application.domain.service.validator.ConstantValid.*;
import static org.junit.jupiter.api.Assertions.*;

class ServiceUserImplTest {

    private final DaoUser mockDao = Mockito.mock(DaoUserImpl.class);
    private final ServiceUser serviceUser = ServiceUserFactory.testServiceUser(mockDao);
    private final Specification mockSpec = Mockito.mock(FindUserByEmailSpecification.class);

    @Test
    @DisplayName("UserDto is null")
    void createUserException(){
        assertThrows(ServiceException.class, () -> serviceUser.create(null));
    }

    @Test
    void createUserNotValid() throws ServiceException {
        //given
        UserDto userDto = UserDto.builder()
                .firstName("")
                .lastName("")
                .email("")
                .password("")
                .type("user")
                .build();
        //when
        List<String> result = serviceUser.create(userDto);
        //then
        assertEquals(List.of(NAME_NOT_VALID,LAST_NAME_NOT_VALID,EMAIL_NOT_VALID,PASSWORD_NOT_VALID),result);
    }

    @Test
    void createUserExist() throws DaoException, ServiceException {
        //given
        UserDto userDto = UserDto.builder()
                .firstName("Pasha")
                .lastName("Hardziyevich")
                .email("myemail@gmail.com")
                .password("123456")
                .type("ADMIN")
                .build();
        User user = User.builder()
                .id(BigDecimal.ONE)
                .firstName("Pasha")
                .lastName("Hardziyevich")
                .email("myemail@gmail.com")
                .password("123456")
                .type(Role.ADMIN)
                .build();
        //when
        List<User> users = new ArrayList<>();
        users.add(user);
        Mockito.doReturn(users).when(mockDao).find(mockSpec);
        //then
        List<String> result = serviceUser.create(userDto);
        assertEquals(List.of(USER_EXIST),result);
    }
}
