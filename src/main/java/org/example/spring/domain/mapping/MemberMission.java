package org.example.spring.domain.mapping;
import jakarta.persistence.*;
import lombok.*;
import org.example.spring.domain.Member;
import org.example.spring.domain.Mission;
import org.example.spring.domain.common.BaseEntity;
import org.example.spring.domain.enums.MissionStatus;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'CHALLENGING'")
    private MissionStatus status;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="mission_id")
    private Mission mission;
}
