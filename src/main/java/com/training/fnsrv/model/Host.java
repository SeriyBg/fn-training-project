package com.training.fnsrv.model;

import lombok.Data;

@Data
public class Host {
    private Long id;
    private String addr;
    private String user;
    private String password;
}
