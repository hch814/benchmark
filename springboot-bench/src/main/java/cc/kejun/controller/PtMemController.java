package cc.kejun.controller;

import cc.kejun.dto.PageDTO;
import cc.kejun.dto.ResponseDTO;
import cc.kejun.dto.UserDTO;
import cc.kejun.service.PtUserService;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author hch
 * @since 2021/9/5
 */
@RequestMapping("/mem")
@RestController
@RequiredArgsConstructor
@Slf4j
public class PtMemController {
    private final PtUserService service;
    private final LoadingCache<String, PageDTO<UserDTO>> usersCache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build(new CacheLoader<String, PageDTO<UserDTO>>() {
                @Override
                public PageDTO<UserDTO> load(String key) throws Exception {
                    String[] split = key.split(":");
                    return service.listUsers(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                }
            });

    private final LoadingCache<String, UserDTO> userCache = CacheBuilder.newBuilder()
            .maximumSize(3000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build(new CacheLoader<String, UserDTO>() {
                @Override
                public UserDTO load(String key) throws Exception {
                    return service.getUser(Long.parseLong(key));
                }
            });

    @GetMapping("/user")
    public ResponseDTO<UserDTO> user(@RequestParam("id") Long id) {
        return new ResponseDTO<>(userCache.getUnchecked(String.valueOf(id)));
    }

    @GetMapping("/users")
    public ResponseDTO<PageDTO<UserDTO>> users(@RequestParam("pageNum") Integer pageNum,
                                               @RequestParam("pageSize") Integer pageSize) {
        return new ResponseDTO<>(usersCache.getUnchecked(pageNum + ":" + pageSize));
    }
}
