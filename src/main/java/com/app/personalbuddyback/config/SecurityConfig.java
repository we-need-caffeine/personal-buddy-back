package com.app.personalbuddyback.config;

import com.app.personalbuddyback.domain.MemberVO;
import com.app.personalbuddyback.service.MemberService;
import com.app.personalbuddyback.util.JwtTokenUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

    private final JwtTokenUtil jwtTokenUtil;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, MemberService memberService) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // CORS 설정 추가
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll() // 모든 경로 허용
                )
                .oauth2Login(oauth -> oauth
                        .redirectionEndpoint(endpoint -> endpoint
                                .baseUri("/login/personalbuddy/code/*")
                        )
                                .successHandler((request, response, authentication) -> {
//                            log.info("{}", authentication);
                                    if(authentication instanceof OAuth2AuthenticationToken){
                                        OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
                                        OAuth2User user = authToken.getPrincipal();
                                        Map<String, Object> attributes = user.getAttributes();

                                        log.info("oauth2AuthenticationToken {}", authToken);

//                                Oauth2 제공자의 정보(Email, name, provider)
                                        String provider = authToken.getAuthorizedClientRegistrationId(); // ex) "google", "naver", "kakao"
                                        String email = "";
                                        String name = "";

                                        if(provider.equals("google")){
                                            email = (String)attributes.get("email");
                                            name = (String)attributes.get("name");
                                        }else if (provider.equals("kakao")){
                                            email = ((Map<String, Object>)attributes.get("kakao_account")).get("email").toString();
                                            name = ((Map<String, Object>)attributes.get("properties")).get("nickname").toString();
                                        }else if(provider.equals("naver")){
                                            email = ((Map<String, Object>)attributes.get("response")).get("email").toString();
                                            name = ((Map<String, Object>)attributes.get("response")).get("name").toString();
                                        }

//                                log.info("attribute {}", attributes);

                                        Map<String, Object> responseMap = new HashMap<>();
                                        Map<String, Object> claims = new HashMap<>();

                                        claims.put("email", email);
                                        claims.put("name", name);

//                                추가 정보를 받기 위해 provider와 email을 화면으로 넘긴다.
                                        Long memberId = memberService.getMemberIdByMemberEmail(email);
                                        String foundMemberProvider = memberService.getMemberInfoById(memberId).map(MemberVO::getMemberProvider).orElse(null);
                                        String redirectUrl = "";
//                                기존 회원의 로그인
                                        if(memberId != null && foundMemberProvider.equals(provider)){
                                            String jwtToken = jwtTokenUtil.generateToken(claims);
                                            redirectUrl = "http://localhost:3000/main?jwtToken=" + jwtToken;

//                                기존 회원이라면 통합
                                        }else if(memberId != null && !foundMemberProvider.equals(provider)){
//                                    기존회원이 타사 로그인
                                            if(foundMemberProvider.equals("local")){
                                                String jwtToken = jwtTokenUtil.generateToken(claims);
                                                memberService.getMemberInfoById(memberId).ifPresent(member -> {
                                                    MemberVO memberVO = new MemberVO();
                                                    memberVO.setId(member.getId());
                                                    memberVO.setMemberEmail(member.getMemberEmail());
                                                    memberVO.setMemberName(member.getMemberName());
                                                    memberVO.setMemberPassword(member.getMemberPassword());
                                                    memberVO.setMemberNickName(member.getMemberNickName());
                                                    memberVO.setMemberProvider(provider);
                                                    memberService.edit(memberVO);
                                                });
                                                redirectUrl = "http://localhost:3000/main?jwtToken=" + jwtToken;
                                            }else{
//                                        타사의 소셜로그인
                                                redirectUrl = "http://localhost:3000/member/login?provider=" + foundMemberProvider + "&login=false";
                                            }
//                                아니라면 신규 가입
                                        }else{
                                            redirectUrl = "http://localhost:3000/member/join?provider=" + provider + "&email=" + email;
                                        }
                                        response.sendRedirect(redirectUrl);
                                    }
                                })
                )
                .logout(logout -> logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("http://localhost:3000/member/login")
                                .logoutSuccessHandler((request, response, authentication) -> {
//                            세션이 있으면 가져와야 한다
                                    HttpSession session = request.getSession(false); // 세션이 있으면 가져와라
                                    if(session != null){
                                        session.invalidate();
                                    }
                                    response.sendRedirect("http://localhost:3000/member/login");
                                })
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000"); // React 앱 주소
        configuration.addAllowedMethod("*"); // 모든 HTTP 메서드 허용
        configuration.addAllowedHeader("*"); // 모든 요청 헤더 허용
        configuration.setAllowCredentials(true); // 인증 정보 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 경로에 적용
        return source;
    }
}
