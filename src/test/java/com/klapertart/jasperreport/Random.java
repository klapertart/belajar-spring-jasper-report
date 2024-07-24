package com.klapertart.jasperreport;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author tritr
 * @since 2/12/2024
 */

@SpringBootTest
@Slf4j
public class Random {

    @Test
    void test() {
        String ALARM_DESC_SITE = "MSR Site for $dataSource in site $siteName is below threshold";
        String dataSource = "file";
        String site = "JABODETABEK";

        log.info("MESSAGE: {}",ALARM_DESC_SITE.replace("$dataSource", dataSource).replace("$siteName", site));
    }
}
