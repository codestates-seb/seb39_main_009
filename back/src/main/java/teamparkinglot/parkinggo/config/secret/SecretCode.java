package teamparkinglot.parkinggo.config.secret;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
public class SecretCode {

    @Value("${jwt.tokenSecurityKey}")
    private String tokenSecurityKey;

    @Value("${jwt.accessTokenExpireTime}")
    private Long accessTokenExpireTime;

    @Value("${jwt.refreshTokenExpireTime}")
    private Long refreshTokenExpireTime;

    @Value("${client.url}")
    private String clientUrl;

}
