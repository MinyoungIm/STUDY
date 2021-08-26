package im.minyoung.parking.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import im.minyoung.parking.domain.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

	Optional<Member> findByUserEmail(String userEmail);	
	
}
