package com.dlsc.jfxcentral.data;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ApplicationExtension.class)
public class RSSManagerTest {

    @BeforeAll
    public static void setup() {
        DataRepository.setTesting(true);
    }

    @Test
    public void shouldCreateLinksOfTheWeekRss() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

        assertFalse(repository.getLinksOfTheWeek().isEmpty());

        String rss = RSSManager.createRSS();

        System.out.println(rss);

        // when
        assertTrue(StringUtils.isNotBlank(rss), "RSS output with Links Of The Week is missing");
    }
}
