package pagao.deliciasdovovo.dtos;

import pagao.deliciasdovovo.enums.UserType;

import java.math.BigDecimal;

public record CustomerDto(String firstName, String lastName, String email, String phone, UserType userType,
                          BigDecimal balance) {
}
