package com.example.demo.controller.kakao;

import com.example.demo.entity.kakao.KakaoProfile;
import com.example.demo.entity.kakao.OAuthToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/")
public class KakaoLogin {

        @GetMapping("/auth/kakao/callback")
        public String kakaoCallback(String code) {
            log.info("code:" + code);
            //post타입으로 key=value 데이터 요청
            RestTemplate rt = new RestTemplate();
            //HttpHeader 오브젝트 생성
            HttpHeaders headers = new HttpHeaders();
            //key=value 데이터 타입이라고 설정
            headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
            //body 데이터를 담을 객체 생성
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            //변수화 시켜서 사용하는게 좋다고함
            params.add("grant_type","authorization_code");
            params.add("client_id","90072789720232b1e9a428ee7202cce4");
            params.add("redirect_uri","http://localhost:7777/auth/kakao/callback");
            params.add("code",code);

            //HttpHeader,Body 하나의 오브젝트에 담기
            HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                    new HttpEntity<>(params,headers);
            //Http 요청하기 - post방식 , 그리고 response변수의 응답 받음
            ResponseEntity<String> response = rt.exchange(
                    "https://kauth.kakao.com/oauth/token", //토큰 발급 받을 주소
                    HttpMethod.POST,//요청 방식
                    kakaoTokenRequest,
                    String.class //응답 받을 데이터 타입
            );

            //json data를 oauthToken 클래스에 담음
            ObjectMapper objectMapper = new ObjectMapper();
            OAuthToken oauthToken = null;

            try {
                oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            System.out.println(oauthToken.getAccess_token());

            //유저 프로필
            //post타입으로 key=value 데이터 요청
            RestTemplate rt2 = new RestTemplate();
            //HttpHeader 오브젝트 생성
            HttpHeaders headers2 = new HttpHeaders();
            //key=value 데이터 타입이라고 설정
            headers2.add("Authorization","Bearer " + oauthToken.getAccess_token());
            headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

            //HttpHeader,Body 하나의 오브젝트에 담기
            HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest =
                    new HttpEntity<>(headers2);
            //Http 요청하기 - post방식 , 그리고 response변수의 응답 받음
            ResponseEntity<String> response2 = rt2.exchange(
                    "https://kapi.kakao.com/v2/user/me", //토큰 발급 받을 주소
                    HttpMethod.POST,//요청 방식
                    kakaoProfileRequest,
                    String.class //응답 받을 데이터 타입
            );

            ObjectMapper objectMapper2 = new ObjectMapper();
            KakaoProfile kakaoProfile = null;

            try {
                kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            System.out.println(kakaoProfile.getId() + " "  + kakaoProfile.getKakao_account().getProfile() + "" +
                    kakaoProfile.getProperties().getNickname());

            UUID garbagePassword = UUID.randomUUID();


            RestTemplate rt3 = new RestTemplate();
            //HttpHeader 오브젝트 생성
            HttpHeaders headers3 = new HttpHeaders();
            //key=value 데이터 타입이라고 설정
            headers3.add("Authorization","Bearer " + oauthToken.getAccess_token());
            headers3.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

            //HttpHeader,Body 하나의 오브젝트에 담기
            HttpEntity<MultiValueMap<String, String>> kakaoLogoutRequest =
                    new HttpEntity<>(headers3);
            //Http 요청하기 - post방식 , 그리고 response변수의 응답 받음
            ResponseEntity<String> response3 = rt3.exchange(
                    "https://kapi.kakao.com//v1/user/unlink", //토큰 발급 받을 주소
                    HttpMethod.POST,//요청 방식
                    kakaoLogoutRequest,
                    String.class //응답 받을 데이터 타입
            );



            return response2.getBody();
        }




}
