package com.training.fnsrv.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class IpInterface {
    @Setter(AccessLevel.NONE) private String name;
    @Setter(AccessLevel.NONE) private String macAddress;
    @Setter(AccessLevel.NONE) private IpAddress ipAddress;
    @Setter(AccessLevel.NONE) private int MTU;

    private IpInterface(Builder builder) {
        name = builder.name;
        macAddress = builder.macAddress;
        ipAddress = builder.ipAddress;
        MTU = builder.MTU;
    }

    public static class Builder {
        @Getter private String name;
        @Getter private String macAddress;
        @Getter private IpAddress ipAddress;
        @Getter private int MTU;

        public IpInterface build() {
            return new IpInterface(this);
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder macAddress(String macAddress) {
            this.macAddress = macAddress;
            return this;
        }

        public Builder ipAddress(IpAddress ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        public Builder MTU(int MTU) {
            this.MTU = MTU;
            return this;
        }
    }
}
