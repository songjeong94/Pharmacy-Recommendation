package com.example.pharmacy

import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.containers.GenericContainer
import spock.lang.Specification

@SpringBootTest
abstract class AbstractIntegrationContainerBaseTest extends Specification{

    static final GenericContainer MY_REDIS_CONTAINER

    static {
        MY_REDIS_CONTAINER = new GenericContainer<>("redis:6")
        .withExposedPorts(6379)
        //ExposedPorts는 Docker에서의 port이며 host에서 port는 test-container에서 충돌되지 않는 port를 사용해서 매핑을 한다.

        MY_REDIS_CONTAINER.start()

        //spring-boot에게 mapping된 host와 port를 전달해 주어야 한다.
        System.setProperty("spring.redis.host", MY_REDIS_CONTAINER.getHost())
        System.setProperty("spring.redis.port", MY_REDIS_CONTAINER.getMappedPort(6379).toString())
    }
}
