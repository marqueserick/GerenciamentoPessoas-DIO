package com.erickmarques.personapi.utils;

import com.erickmarques.personapi.dto.request.PhoneDTO;
import com.erickmarques.personapi.entities.Phone;
import com.erickmarques.personapi.enums.PhoneType;

public class PhoneUtils {
    private static final String PHONE_NUMBER = "1199999-9999";
    private static final PhoneType PHONE_TYPE = PhoneType.MOBILE;
    private static final long PHONE_ID = 1L;

    public static PhoneDTO createFakeDTO(){
        return PhoneDTO.builder()
                .phoneNumber(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }

    public static Phone createFakeEntity(){
        return Phone.builder()
                .id(PHONE_ID)
                .phoneNumber(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }
}
