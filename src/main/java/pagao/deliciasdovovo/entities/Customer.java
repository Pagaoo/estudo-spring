package pagao.deliciasdovovo.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pagao.deliciasdovovo.dtos.CustomerDto;
import pagao.deliciasdovovo.enums.UserType;

@Entity
@Table(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Column(unique = true)
    private String email;
    @NotBlank
    @Column(unique = true)
    private String phone;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public Customer(CustomerDto customerDto) {
        this.firstName = customerDto.firstName();
        this.lastName = customerDto.lastName();
        this.email = customerDto.email();
        this.phone = customerDto.phone();
        this.userType = customerDto.userType();
    }
}
