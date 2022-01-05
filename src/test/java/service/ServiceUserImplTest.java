package service;

import com.hardziyevich.application.dao.DaoUser;
import com.hardziyevich.application.dao.impl.DaoUserImpl;
import com.hardziyevich.application.domain.entity.Role;
import com.hardziyevich.application.domain.entity.User;
import com.hardziyevich.application.domain.service.ServiceUser;
import com.hardziyevich.application.domain.service.ServiceUserFactory;
import com.hardziyevich.application.domain.service.dto.LoginUserDto;
import com.hardziyevich.application.domain.service.dto.UserDto;
import com.hardziyevich.application.exception.DaoException;
import com.hardziyevich.application.exception.ServiceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.hardziyevich.application.domain.service.validator.ConstantValid.*;
import static org.junit.jupiter.api.Assertions.*;

class ServiceUserImplTest {

    private static final DaoUser mockDao = Mockito.mock(DaoUserImpl.class);
    private static final ServiceUser serviceUser = ServiceUserFactory.testServiceUser(mockDao);

    @Test
    @DisplayName("UserDto is null")
    void createUserException(){
        assertThrows(ServiceException.class, () -> serviceUser.create(null));
    }

    @Test
    @DisplayName("User with not correct value")
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
    @DisplayName("User exist in database")
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
        Mockito.doReturn(users).when(mockDao).findByEmail(userDto.getEmail());
        //then
        List<String> result = serviceUser.create(userDto);
        assertEquals(List.of(USER_EXIST),result);
    }

    @Test
    @DisplayName("Test login that is not valid")
    void loginInvalid() throws DaoException, ServiceException {
        //given
        UserDto userDto = UserDto.builder()
                .email("")
                .password("")
                .build();
        //when
        Optional<LoginUserDto> expect = Optional.empty();
        List<User> users = new ArrayList<>();
        Mockito.doReturn(users).when(mockDao).findByEmail(userDto.getEmail());
        //then
        assertEquals(expect,serviceUser.login(userDto));
    }

    @Test
    @DisplayName("Test login that is valid")
    void loginValid() throws DaoException, ServiceException {
        //given
        UserDto userDto = UserDto.builder()
                .email("myemail@gmail.com")
                .password("das2632337")
                .build();
        //when
        User user = User.builder()
                .id(BigDecimal.ONE)
                .firstName("Pasha")
                .lastName("Hardziyevich")
                .email("myemail@gmail.com")
                .password("ZGFzMjYzMjMzNw==")
                .type(Role.ADMIN)
                .build();
        LoginUserDto login = LoginUserDto.builder()
                .firstName("Pasha")
                .lastName("Hardziyevich")
                .email("myemail@gmail.com")
                .type(Role.ADMIN.toString())
                .build();
        Optional<LoginUserDto> expect = Optional.of(login);
        List<User> users = new ArrayList<>();
        users.add(user);
        Mockito.doReturn(users).when(mockDao).findByEmail(userDto.getEmail());
        //then
        assertEquals(expect,serviceUser.login(userDto));
    }

}
