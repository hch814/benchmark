package cc.kejun.conf;

import cc.kejun.domain.Metrics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * @author hch
 * @since 2021/9/5
 */
@Slf4j(topic = "METRICS")
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            private static final String START = "start";

            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                request.setAttribute(START, System.currentTimeMillis());
                return true;
            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
                long start = (long) request.getAttribute(START);
                Metrics metrics = new Metrics()
                        .setMetrics("http")
                        .setGroup(request.getServletPath())
                        .setCost(System.currentTimeMillis() - start)
                        .setResult(true);
                Optional.ofNullable(ex).ifPresent(e -> metrics.setResult(false));
                log.info("{}", metrics);
            }
        }).excludePathPatterns("/");
    }
}
