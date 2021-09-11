package cc.kejun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author hch
 * @since 2021/9/11
 */
@RestController
public class PtCPUController {
    private static final Random RANDOM = new Random();
    @GetMapping("/cpu")
    public void cpu() {
        for (int i = 0; i < 10000; i++) {
            Math.log(RANDOM.nextDouble());
        }
    }
}
