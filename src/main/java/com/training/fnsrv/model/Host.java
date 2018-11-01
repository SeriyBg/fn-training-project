package com.training.fnsrv.model;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString
@Entity
@Table(name = "host")
public class Host extends GenModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ipAddress;
    private String user;
    private String password;

    public Host() {}

    private Host(Builder builder) {
        reqId = builder.reqId;
        ipAddress = builder.ipAddress;
        user = builder.user;
        password = builder.password;
    }

    public static class Builder {
        private Long reqId;
        private String ipAddress;
        private String user;
        private String password;

        public Host build() {
            return new Host(this);
        }

        public Builder reqId(Long reqId) {
            this.reqId = reqId;
            return this;
        }

        public Builder ipAddress(String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        public Builder user(String user) {
            this.user = user;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }
    }
}
