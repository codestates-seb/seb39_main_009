package teamparkinglot.parkinggo.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import teamparkinglot.parkinggo.exception.BusinessException;
import teamparkinglot.parkinggo.exception.ExceptionCode;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.member.repository.MemberRepository;
import teamparkinglot.parkinggo.secret.SecretCode;
import teamparkinglot.parkinggo.security.principal.PrincipalDetails;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private MemberRepository memberRepository;
    private SecretCode secretCode;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository, SecretCode secretCode) {
        super(authenticationManager);
        this.memberRepository = memberRepository;
        this.secretCode = secretCode;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.info("===== 권한 필요 주소 요청 됨 =====");

        String header = request.getHeader("Authorization");
        log.info("header 잘 들어 오능가? = {}", header);

        if (header == null || !header.startsWith("Bearer")) {
            log.info("토큰이 안들어왔어요!");
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace("Bearer ", "");

        Date expiresAt = JWT.decode(token).getExpiresAt();
        Date now = new Date(System.currentTimeMillis());

        if (expiresAt.before(now)) {
            log.info("=== 액세스 토큰 시간 만료 ===");
            chain.doFilter(request, response);
            return;
        }

        String email = JWT.require(Algorithm.HMAC512(secretCode.getTokenSecurityKey())).build().verify(token).getClaim("email").asString();

        if (email != null) {
            Member member = findVerifyMember(email);
            PrincipalDetails principalDetails = new PrincipalDetails(member);
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request, response);
        }
    }

    private Member findVerifyMember(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new BusinessException(ExceptionCode.MEMBER_NOT_EXISTS)
        );
        return member;
    }
}
