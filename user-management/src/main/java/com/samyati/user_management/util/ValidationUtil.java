package com.samyati.user_management.util;

import com.samyati.user_management.exceptions.ApplicationException;
import com.samyati.user_management.exceptions.ErrorCode;

public class ValidationUtil {

    public static void validatePasswordChange(String oldPassword, String newPassword) {
        if (newPassword.equals(oldPassword)) {
            throw new ApplicationException(ErrorCode.GENERIC_ERROR);
        }
    }

}
