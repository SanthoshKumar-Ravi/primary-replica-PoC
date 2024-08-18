package com.poc.readreplica;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class ReadReplicaPocApplicationTests {


    /** This test case will only pass when the docker containers to be up and running **/
    @Test
    void contextLoads() {
    }

}
