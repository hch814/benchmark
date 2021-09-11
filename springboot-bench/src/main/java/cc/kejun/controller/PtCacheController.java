package cc.kejun.controller;

import cc.kejun.dto.PageDTO;
import cc.kejun.dto.ResponseDTO;
import cc.kejun.dto.UserDTO;
import cc.kejun.service.PtUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author hch
 * @since 2021/7/18
 */
@RestController
@RequestMapping("/cache")
@RequiredArgsConstructor
@Slf4j
public class PtCacheController {
    private final PtUserService service;
    private static final String REDIS_KEY_PREFIX = "pt:";
    private final RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/user")
    // @Cacheable(value = REDIS_KEY_PREFIX, key = "'user:'+#id", unless = "#result==null")
    public ResponseDTO<UserDTO> user(@RequestParam("id") Long id) {
        String key = REDIS_KEY_PREFIX + "user:" + id;
        ResponseDTO<UserDTO> dto = (ResponseDTO<UserDTO>) redisTemplate.opsForValue().get(key);
        if (dto == null) {
            dto = new ResponseDTO<>(service.getUser(id));
            redisTemplate.opsForValue().set(key, dto, 10, TimeUnit.MINUTES);
        }
        return dto;
    }

    @GetMapping("/users")
    // @Cacheable(value = REDIS_KEY_PREFIX, key = "'users:'+#pageNum+'-'+#pageSize", unless = "#result==null")
    public ResponseDTO<PageDTO<UserDTO>> users(@RequestParam("pageNum") Integer pageNum,
                                               @RequestParam("pageSize") Integer pageSize) {
        String key = REDIS_KEY_PREFIX + "users:" + pageNum + "-" + pageSize;
        ResponseDTO<PageDTO<UserDTO>> dto = (ResponseDTO<PageDTO<UserDTO>>) redisTemplate.opsForValue().get(key);
        if (dto == null) {
            dto = new ResponseDTO<>(service.listUsers(pageNum, pageSize));
            redisTemplate.opsForValue().set(key, dto, 10, TimeUnit.MINUTES);
        }
        return dto;
    }
}
