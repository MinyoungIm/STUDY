package im.minyoung.parking.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import im.minyoung.parking.domain.Role;
import im.minyoung.parking.domain.entity.Member;
import im.minyoung.parking.domain.repository.MemberRepository;
import im.minyoung.parking.dto.MemberDto;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {

	private MemberRepository memberRepository;
	
	@Transactional
	public Long joinUser(MemberDto memberDto) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		memberDto.setUserPassword(passwordEncoder.encode(memberDto.getUserPassword()));
		
		return memberRepository.save(memberDto.toEntity()).getId();
	}
	
	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		Optional<Member> userEntiryWrapper = memberRepository.findByUserEmail(userEmail);
		Member member = userEntiryWrapper.get();
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if(("admin@example.com").equals(userEmail)) {
			authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
		} else {
			authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
		}
		
		return new User(member.getUserEmail(), member.getUserPassword(), authorities);
	}
}
