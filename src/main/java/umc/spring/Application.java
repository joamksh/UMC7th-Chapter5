package umc.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.dto.MissionWithStoreAndStatusDto;
import umc.spring.service.MissionQueryService;
import umc.spring.service.StoreQueryService;

import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	//실습 테스트 용도
//	@Bean
//	public CommandLineRunner run(ApplicationContext context) {
//		return args -> {
//			StoreQueryService storeService = context.getBean(StoreQueryService.class);
//
//			// 쿼리 테스트용 파라미터 설정
//			String name = "요아정";
//			Float score = 4.0f;
//
//			// 쿼리 메서드 호출 및 결과 출력
//			System.out.println("Executing findStoresByNameAndScore with parameters:");
//			System.out.println("Name: " + name);
//			System.out.println("Score: " + score);
//
//			storeService.findStoresByNameAndScore(name, score)
//					.forEach(System.out::println);
//		};
//	}

	//미션1
	@Bean
	public CommandLineRunner run(ApplicationContext context) {
		return args -> {
			MissionQueryService missionService = context.getBean(MissionQueryService.class);

			Long memberId = 1L; // 테스트할 member ID
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
}
