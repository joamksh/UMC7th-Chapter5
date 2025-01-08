package org.example.spring.domain;
import jakarta.persistence.*;
import lombok.*;
import org.example.spring.domain.common.BaseEntity;
import org.example.spring.domain.enums.Gender;
import org.example.spring.domain.enums.MemberStatus;
import org.example.spring.domain.enums.Role;
import org.example.spring.domain.enums.SocialType;
import org.example.spring.domain.mapping.MemberAgree;
import org.example.spring.domain.mapping.MemberMission;
import org.example.spring.domain.mapping.MemberPrefer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@DynamicUpdate
@DynamicInsert
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false, length = 40)
    private String address;

    @Column(nullable = false, length = 40)
    private String specAddress;

    @Enumerated(EnumType.STRING) // Enum을 String으로 저장
    @Column(nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    private LocalDate inactiveDate;

//    @Column(nullable = false, length = 50)
    @Column(nullable = false, unique = true)
    private String email;

//    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")

    @ColumnDefault("0")
    private Integer point;

    @PrePersist
    public void prePersist() {
        if (this.point == null) {
            this.point = 0;
        }
    }

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberAgree> memberAgreeList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberPrefer> memberPreferList = new ArrayList<>();


    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();

    public void encodePassword(String password) {
        this.password = password;
    }
}
