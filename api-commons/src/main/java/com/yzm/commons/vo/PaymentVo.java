package com.yzm.commons.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "PaymentVo对象", description = "")
public class PaymentVo {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("序列号")
    private String serial;
}
