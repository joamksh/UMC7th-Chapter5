package umc.spring.service;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.QMember;
import umc.spring.dto.MemberInfoDto;

@Service
public class MemberQueryService {

    private final JPAQueryFactory queryFactory;

    public MemberQueryService(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Transactional(readOnly = true)
    public MemberInfoDto findMemberInfoById(Long memberId) {
        QMember member = QMember.member;

        return queryFactory
                .select(Projections.constructor(MemberInfoDto.class,
                        member.email,
                        member.socialType.stringValue(),
                        member.point,
                        member.name))
                .from(member)
                .where(member.id.eq(memberId))
                .fetchOne();
    }
}