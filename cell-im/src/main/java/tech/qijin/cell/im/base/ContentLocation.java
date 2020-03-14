package tech.qijin.cell.im.base;

import lombok.Data;
import tech.qijin.util4j.web.validation.annotation.Lat;
import tech.qijin.util4j.web.validation.annotation.Lng;

import javax.validation.constraints.NotBlank;

/**
 * @author michealyang
 * @date 2019-11-15
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Data
public class ContentLocation extends AbstractContent {
    @NotBlank(message = "地址经度不能为空")
    @Lng(message = "地址经度不合法")
    private String lng;

    @NotBlank(message = "地址纬度不能为空")
    @Lat(message = "地址纬度不合法")
    private String lat;

    private String address;
}
