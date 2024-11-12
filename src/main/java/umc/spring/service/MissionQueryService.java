package umc.spring.service;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.dto.MissionWithStoreAndStatusDto;
import umc.spring.domain.QMember;
import umc.spring.domain.QMission;
import umc.spring.domain.QStore;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.QMemberMission;

import java.util.List;

@Service
public class MissionQueryService {

    private final JPAQueryFactory queryFactory;

    public MissionQueryService(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Transactional(readOnly = true)
    public List<MissionWithStoreAndStatusDto> findMissionsWithStatus(Long memberId, int limit, int offset) {
        QMission mission = QMission.mission;
        QStore store = QStore.store;
        QMemberMission memberMission = QMemberMission.memberMission;
        QMember member = QMember.member;

        return queryFactory
                .select(Projections.constructor(MissionWithStoreAndStatusDto.class,
                        mission.id,
                        mission.reward,
                        mission.missionSpec,
                        mission.createdAt,
                        mission.updatedAt,
                        memberMission.status,
                        memberMission.member.id,
                        store.name))
                .from(mission)
                .join(mission.store, store)
                .join(mission.memberMissionList, memberMission)
                .join(memberMission.member, member)
                .where(member.id.eq(memberId)
                        .and(memberMission.status.in(MissionStatus.COMPLETE, MissionStatus.CHALLENGING)))
                .limit(limit)
                .offset(offset)
                .fetch();
    }
}
