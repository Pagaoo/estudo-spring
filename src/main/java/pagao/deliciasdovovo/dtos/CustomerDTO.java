package pagao.deliciasdovovo.dtos;

import pagao.deliciasdovovo.enums.UserType;

import java.math.BigDecimal;

public record CustomerDTO(String firstName, String lastName, String email, String phone, UserType userType,
                          BigDecimal balance) {
}
