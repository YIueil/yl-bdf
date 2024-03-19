package cc.yiueil.repository;

import cc.yiueil.entity.VerifyCodeEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * VerifyCodeRepository
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/3/20 0:31
 */
public interface VerifyCodeRepository extends CrudRepository<VerifyCodeEntity, Long> {
    /**
     * 查询指定用户 指定类型 未使用 未超期 并且和用户输入一致的验证码
     *
     * @param createUserId 创建用户
     * @param useType      使用类型
     * @param status       状态
     * @param code         验证码
     * @param expire       过期时间
     * @return 符合条件的验证码集合
     */
    List<VerifyCodeEntity> findVerifyCodeEntityByCreateUserIdEqualsAndUseTypeEqualsAndStatusEqualsAndCodeEqualsAndExpireAfter(Long createUserId, String useType, String status, String code, LocalDateTime expire);
}