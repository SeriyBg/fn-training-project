package com.training.fnsrv.model;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString
//@Entity
@Table(name = "ip_interface")
public class IpInterface extends GenModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String macAddress;
    @OneToOne
    private IpAddress ipAddress;
    private int MTU;

    public IpInterface() {}

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
