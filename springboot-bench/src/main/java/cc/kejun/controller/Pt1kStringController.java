package cc.kejun.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hch
 * @since 2021/9/5
 */
@RestController
public class Pt1kStringController {
    private String s = RandomStringUtils.randomAlphanumeric(1000);

    @GetMapping("/1k")
    public String get() {
        return s;
    }
}
