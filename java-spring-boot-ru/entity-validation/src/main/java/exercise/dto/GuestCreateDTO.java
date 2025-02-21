package exercise.dto;

import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

// BEGIN
@Setter
@Getter
public class GuestCreateDTO {

    private String name;

    @Email
    private String email;

    @Pattern(regexp = "^\\+\\d{11,13}$", message = "Must start with '+' and contain 11 to 13 digits")
    private String phoneNumber;

    @Size(min = 4, max=4)

    private String clubCard;

    @FutureOrPresent
    private LocalDate cardValidUntil;
}

// END
