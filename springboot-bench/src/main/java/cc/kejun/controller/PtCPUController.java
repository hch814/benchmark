package cc.kejun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hch
 * @since 2021/9/11
 */
@RestController
public class PtCPUController {
    @GetMapping("/cpu")
    public void cpu() {
        for (int i = 0; i < 10000; i++) {
            Math.log(1.1);
        }
    }
}
