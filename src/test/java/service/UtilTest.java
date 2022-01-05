package service;

import com.hardziyevich.application.controller.servlet.Util;
import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilTest {

    @Test
    @DisplayName("Test encode")
    void encode() {
        //given
        String expect = "123";
        //when
        String decode = Util.encode("123");
        String encode = Util.decode(decode);
        //then
        assertEquals(expect,encode);
    }
}
