package com.poc.readreplica.router;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DataSourceRouter extends AbstractRoutingDataSource {
    private static final ThreadLocal<Route> ctx = new ThreadLocal<>();

    public enum Route {
        PRIMARY, READ_ONLY
    }

    public static void clearReplicaRoute() {
        ctx.remove();
    }

    public static void setReplicaRoute() {
        ctx.set(Route.READ_ONLY);
    }
    @Override
    protected Object determineCurrentLookupKey() {
        return ctx.get();
    }
}
