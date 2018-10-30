package com.training.fnsrv.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class IpAddress {
    private String addr;
    private String netmask;

    private IpAddress(Builder builder) {
        addr = builder.addr;
        netmask = builder.netmask;
    }

    public static class Builder {
        @Getter private String addr;
        @Getter private String netmask;

        public IpAddress build() {
            return new IpAddress(this);
        }

        public Builder addr(String addr) {
            this.addr = addr;
            return this;
        }

        public Builder netmask(String netmask) {
            this.netmask = netmask;
            return this;
        }
    }
}
