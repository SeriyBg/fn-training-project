package com.training.fnsrv.model;

import lombok.Data;

@Data
public class HostRequest extends GenModel {
    private String addr;
    private String user;
    private String password;
}
