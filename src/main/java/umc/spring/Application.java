package umc.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.spring.dto.MemberInfoDto;
import umc.spring.dto.MissionWithStoreAndRegionDto;
import umc.spring.dto.MissionWithStoreAndStatusDto;
import umc.spring.dto.ReviewWithMemberAndStoreDto;
import umc.spring.service.MemberQueryService;
import umc.spring.service.MissionQueryService;
import umc.spring.service.ReviewQueryService;
import umc.spring.service.StoreQueryService;

import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// 실습 테스트 CommandLineRunner
	@Bean
	public CommandLineRunner runPractice(ApplicationContext context) {
		return args -> {
			StoreQueryService storeService = context.getBean(StoreQueryService.class);

			// 쿼리 테스트용 파라미터 설정
			String name = "요아정";
			Float score = 4.0f;

			// 쿼리 메서드 호출 및 결과 출력
			System.out.println("Executing findStoresByNameAndScore with parameters:");
			System.out.println("Name: " + name);
			System.out.println("Score: " + score);

			storeService.findStoresByNameAndScore(name, score)
					.forEach(System.out::println);
		};
	}

	// 미션 1 CommandLineRunner
	@Bean
	public CommandLineRunner runMission1(ApplicationContext context) {
		return args -> {
			MissionQueryService missionService = context.getBean(MissionQueryService.class);

			Long memberId = 1L;
			int limit = 3;
			int offset = 0;

			System.out.println("Executing findMissionsWithStatus with parameters:");
			System.out.println("Member ID: " + memberId);
			System.out.println("Limit: " + limit);
			System.out.println("Offset: " + offset);

			List<MissionWithStoreAndStatusDto> missions = missionService.findMissionsWithStatus(memberId, limit, offset);

			if (missions.isEmpty()) {
				System.out.println("No missions found.");
			} else {
				missions.forEach(mission -> {
					System.out.println("Mission ID: " + mission.getMissionId());
					System.out.println("Reward: " + mission.getReward());
					System.out.println("Mission Spec: " + mission.getMissionSpec());
					System.out.println("Created At: " + mission.getCreatedAt());
					System.out.println("Updated At: " + mission.getUpdatedAt());
					System.out.println("Status: " + mission.getStatus());
					System.out.println("Member ID: " + mission.getMemberId());
					System.out.println("Store Name: " + mission.getStoreName());
					System.out.println("----------");
				});
			}
		};
	}

	// 미션 2 CommandLineRunner
	@Bean
	public CommandLineRunner runMission2(ApplicationContext context) {
		return args -> {
			ReviewQueryService reviewService = context.getBean(ReviewQueryService.class);

			Long memberId = 1L;

			System.out.println("Executing findReviewsByMemberId with Member ID: " + memberId);

			List<ReviewWithMemberAndStoreDto> reviews = reviewService.findReviewsByMemberId(memberId);

			if (reviews.isEmpty()) {
				System.out.println("No reviews found.");
			} else {
				reviews.forEach(review -> {
					System.out.println("Nickname: " + review.getNickname());
					System.out.println("Score: " + review.getScore());
					System.out.println("Review Body: " + review.getReviewBody());
					System.out.println("Store Name: " + review.getStoreName());
					System.out.println("Review Date: " + review.getReviewDate());
					System.out.println("----------");
				});
			}
		};
	}

	// 미션 3 CommandLineRunner
	@Bean
	public CommandLineRunner runMission3(ApplicationContext context) {
		return args -> {
			MissionQueryService missionService = context.getBean(MissionQueryService.class);

			Long memberId = 1L;

			System.out.println("Executing findUnchallengedMissionsByRegionForMember with Member ID: " + memberId);

			List<MissionWithStoreAndRegionDto> missions = missionService.findUnchallengedMissionsByRegionForMember(memberId);

			if (missions.isEmpty()) {
				System.out.println("No unchallenged missions found.");
			} else {
				missions.forEach(mission -> {
					System.out.println("Mission Spec: " + mission.getMissionSpec());
					System.out.println("Reward: " + mission.getReward() + " P");
					System.out.println("Store Name: " + mission.getStoreName());
					System.out.println("Region Name: " + mission.getRegionName());
					System.out.println("미션 도전!");
					System.out.println("----------");
				});
			}
		};
	}

	// 미션 4 CommandLineRunner
	@Bean
	public CommandLineRunner runMission4(ApplicationContext context) {
		return args -> {
			MemberQueryService memberService = context.getBean(MemberQueryService.class);

			Long memberId = 2L;

			System.out.println("Executing findMemberInfoById with Member ID: " + memberId);

			MemberInfoDto memberInfo = memberService.findMemberInfoById(memberId);

			if (memberInfo != null) {
				System.out.println("Email: " + memberInfo.getEmail());
				System.out.println("Social Type: " + memberInfo.getSocialType());
				System.out.println("Points: " + memberInfo.getPoint());
				System.out.println("Name: " + memberInfo.getName());
			} else {
				System.out.println("No member found with ID: " + memberId);
			}
		};
	}
}
