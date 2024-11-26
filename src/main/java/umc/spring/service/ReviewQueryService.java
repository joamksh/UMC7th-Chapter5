package umc.spring.service;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.dto.ReviewWithMemberAndStoreDto;
import umc.spring.domain.QMember;
import umc.spring.domain.QReview;
import umc.spring.domain.QStore;

import java.util.List;

@Service
public class ReviewQueryService {

    private final JPAQueryFactory queryFactory;

    public ReviewQueryService(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Transactional(readOnly = true)
    public List<ReviewWithMemberAndStoreDto> findReviewsByMemberId(Long memberId) {
        QReview review = QReview.review;
        QMember member = QMember.member;
        QStore store = QStore.store;

        return queryFactory
                .select(Projections.constructor(ReviewWithMemberAndStoreDto.class,
                        member.name.as("nickname"),
                        review.score,                     // score를 Float로 유지
                        review.body.as("reviewBody"),
                        store.name.as("storeName"),
                        review.createdAt.as("reviewDate")
                ))
                .from(review)
                .join(review.member, member)
                .join(review.store, store)
                .where(member.id.eq(memberId))
                .fetch();
    }
}
