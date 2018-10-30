package com.training.fnsrv.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class IpRoute extends GenModel {
    private String destination;
    private String gateway;
    private String genmask;
    private String flags;
    private int metric;
    private int refs;
    private int use;
    private IpInterface iface;

    private IpRoute(Builder builder) {
        reqId = builder.reqId;
        destination = builder.destination;
        gateway = builder.gateway;
        genmask = builder.genmask;
        flags = builder.flags;
        metric = builder.metric;
        refs = builder.refs;
        use = builder.use;
        iface = builder.iface;
    }

    public static class Builder {
        private Long reqId;
        private String destination;
        private String gateway;
        private String genmask;
        private String flags;
        private int metric;
        private int refs;
        private int use;
        private IpInterface iface;

        public IpRoute build() {
            return new IpRoute(this);
        }

        public Builder reqId(Long reqId) {
            this.reqId = reqId;
            return this;
        }

        public Builder destination(String destination) {
            this.destination = destination;
            return this;
        }

        public Builder gateway(String gateway) {
            this.gateway = gateway;
            return this;
        }

        public Builder genmask(String genmask) {
            this.genmask = genmask;
            return this;
        }

        public Builder flags(String flags) {
            this.flags = flags;
            return this;
        }

        public Builder metric(int metric) {
            this.metric = metric;
            return this;
        }

        public Builder refs(int refs) {
            this.refs = refs;
            return this;
        }

        public Builder use(int use) {
            this.use = use;
            return this;
        }

        public Builder iface(IpInterface iface) {
            this.iface = iface;
            return this;
        }
    }
}