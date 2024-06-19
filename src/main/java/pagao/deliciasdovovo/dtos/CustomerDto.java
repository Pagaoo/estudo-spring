package pagao.deliciasdovovo.dtos;

import pagao.deliciasdovovo.enums.UserType;

public record CustomerDto(String firstName, String lastName, String email, String phone, UserType userType) {
}
