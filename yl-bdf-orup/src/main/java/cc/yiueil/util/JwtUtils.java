package cc.yiueil.util;

import cc.yiueil.dto.UserDto;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT 工具类
 * @author 弋孓 YIueil@163.com
 * @date 2023/5/30 22:24
 * @version 1.0
 */
@Slf4j
public class JwtUtils {
    /**
     * JWT 私钥
     */
    private static final String TOKEN_SECRET = "yl-bdf";

    /**
     * 生成token，自定义过期时间 毫秒
     *
     * @param user 用户信息
     * @return 根据用户信息生成 jwt token
     */
    public static String generateToken(UserDto user) {
        try {
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");

            return JWT.create()
                    .withHeader(header)
                    .withClaim("user", JsonUtils.toJsonString(user))
                    .withClaim("createTime", JsonUtils.toJsonString(LocalDateTime.now()))
                    // .withExpiresAt(date) // 根据是否使用 redis 设置过期时间
                    .sign(algorithm);
        } catch (Exception e) {
            log.error("generate token occur error, error");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检验 jwt token 是否正确
     *
     * @param token 待校验的 jwt token 信息
     * @return 校验结果
     */
    public static UserDto verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        String userInfo = jwt.getClaim("user").asString();
        return JsonUtils.parse(UserDto.class, userInfo);
    }
}