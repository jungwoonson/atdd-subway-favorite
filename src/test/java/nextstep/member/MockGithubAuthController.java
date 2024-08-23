package nextstep.member;

import nextstep.member.application.dto.GithubAccessTokenRequest;
import nextstep.member.application.dto.GithubAccessTokenResponse;
import nextstep.member.application.dto.GithubProfileResponse;
import nextstep.utils.GithubResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MockGithubAuthController {

    @PostMapping("/github/login/oauth/access_token")
    public ResponseEntity<GithubAccessTokenResponse> accessToken(@RequestBody GithubAccessTokenRequest tokenRequest) {
        String accessToken = GithubResponses.lookUpAccessToken(tokenRequest.getCode());
        GithubAccessTokenResponse response = new GithubAccessTokenResponse(accessToken, "", "");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/github/user")
    public ResponseEntity<GithubProfileResponse> user(@RequestHeader("Authorization") String authorization) {
        String accessToken = authorization.split(" ")[1];
        GithubResponses userInfo = GithubResponses.lookUp(accessToken);
        GithubProfileResponse response = new GithubProfileResponse(userInfo.getEmail(), userInfo.getAge());
        return ResponseEntity.ok(response);
    }
}
