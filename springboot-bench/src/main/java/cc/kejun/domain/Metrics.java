package cc.kejun.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author hch
 * @since 2021/9/12
 */
@Getter
@Setter
@Accessors(chain = true)
public class Metrics {
    private String metrics;
    private String group;
    private Long cost;
    private Boolean result;

    @Override
    public String toString() {
        return "metrics=" + metrics + ", group=" + group + ", cost=" + cost + ", result=" + result;
    }
}
