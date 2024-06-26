package JWTend.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SignUpDto {

    private String firstName;
    private String lastName;
    private String login;
    private char[] password;

    public String getLogin() {
        return login;
    }

    public char[] getPassword() {
        return password;
    }
}
