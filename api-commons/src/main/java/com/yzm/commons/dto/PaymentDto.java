package com.yzm.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String serialNo;

}
