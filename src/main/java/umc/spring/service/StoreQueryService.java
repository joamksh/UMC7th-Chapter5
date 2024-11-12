package umc.spring.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;
import umc.spring.domain.QStore;
import umc.spring.domain.Store;

import java.util.List;

@Service
public class StoreQueryService {

    private final JPAQueryFactory queryFactory;

    public StoreQueryService(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public List<Store> findStoresByNameAndScore(String name, Float score) {
        QStore store = QStore.store;
        return queryFactory
                .selectFrom(store)
                .where(store.name.eq(name).and(store.score.eq(score)))
                .fetch();
    }
}