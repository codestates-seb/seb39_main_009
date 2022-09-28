package teamparkinglot.parkinggo.token.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
public class RefreshToken {

    @Id @GeneratedValue
    private Long id;
    private String email;
    private String token;

    public RefreshToken(String email, String token) {
        this.email = email;
        this.token = token;
    }
}
