package cc.kejun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 模拟io密集型
 *
 * @author hch
 * @since 2021/9/11
 */
@RestController
public class PtIOController {
    @GetMapping("/io")
    public void io(Long wait) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(Optional.ofNullable(wait).orElse(20L));
    }
}
