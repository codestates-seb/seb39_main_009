package teamparkinglot.parkinggo.mock_custom_user;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.member.entity.MemberRole;
import teamparkinglot.parkinggo.security.principal.PrincipalDetails;

public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {

    Member member1 = new Member("email", "password", "nickname1",MemberRole.USER, null, "carNumber1",
            "phone1", 10000L, true, true, true, "provider", "providerId");

    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser customUser) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        PrincipalDetails principalDetails = new PrincipalDetails(member1);
        Authentication auth = new UsernamePasswordAuthenticationToken(principalDetails, "password", principalDetails.getAuthorities());
        context.setAuthentication(auth);
        return context;
    }
}
