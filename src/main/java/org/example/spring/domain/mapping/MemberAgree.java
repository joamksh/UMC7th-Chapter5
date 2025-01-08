package org.example.spring.domain.mapping;
import jakarta.persistence.*;
import lombok.*;
import org.example.spring.domain.Member;
import org.example.spring.domain.Terms;
import org.example.spring.domain.common.BaseEntity;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberAgree extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="terms_id")
    private Terms terms;
}
