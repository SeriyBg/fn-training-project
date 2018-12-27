package com.training.fnsrv.model;

import lombok.Data;

@Data
public class HostRequest {
    private String addr;
    private String user;
    private String password;
}
