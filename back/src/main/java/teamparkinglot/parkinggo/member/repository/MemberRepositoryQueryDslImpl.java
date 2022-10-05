package teamparkinglot.parkinggo.member.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import teamparkinglot.parkinggo.member.dto.MyPageResDto;
import teamparkinglot.parkinggo.member.dto.SidebarDto;
import teamparkinglot.parkinggo.member.entity.QMember;
import teamparkinglot.parkinggo.reservation.entity.QReservation;

import static teamparkinglot.parkinggo.member.entity.QMember.*;
import static teamparkinglot.parkinggo.reservation.entity.QReservation.*;

@Repository
public class MemberRepositoryQueryDslImpl implements MemberRepositoryQueryDsl {

    private final JPAQueryFactory query;

    public MemberRepositoryQueryDslImpl(JPAQueryFactory jpaQueryFactory) {
        this.query = jpaQueryFactory;
    }

    @Override
    public MyPageResDto findMyPage(String email) {

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(member.email.like(email));

        return query.select(Projections.fields(MyPageResDto.class,
                        member.id, member.email, member.phone, member.carNumber))
                .from(member)
                .where(builder)
                .fetchOne();
    }

    @Override
    public SidebarDto findSidebar(String email) {

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(member.email.like(email));

        return query.select(Projections.fields(SidebarDto.class,
                        member.nickname.as("name"), member.id.as("memberId"), member.email,
                        ExpressionUtils.as(
                                JPAExpressions.select(reservation.count()).from(member)
                                .leftJoin(member.reservation, reservation)
                                .where(reservation.payOrNot.isFalse()), "NumOfReserv"),
                        member.point, member.phone.as("phoneNum"), member.carNumber))
                .from(member)
                .where(builder)
                .fetchOne();
    }
}
