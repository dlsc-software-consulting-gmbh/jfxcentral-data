package com.dlsc.jfxcentral.data;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
public class RSSManagerTest {

    @BeforeAll
    public static void setup() {
        DataRepository.setTesting(true);
    }

    @Test
    void shouldCreateLinksOfTheWeekRss() {
        // given
        DataRepository repository = DataRepository.getInstance();
        assertFalse(repository.getLinksOfTheWeek().isEmpty());

        String rss = RSSManager.createRSS();

        System.out.println("RSS length: " + rss.length());

        // then
        assertAll(
                () -> assertTrue(StringUtils.isNotBlank(rss), "RSS output with Links Of The Week is missing"),
                () -> assertTrue(rss.contains("GNUBSD404 Long N162 PacMan XXL"), "Content from March 6, 2026 is missing"),
                () -> assertTrue(rss.length() > 10_000, "RSS content length should be longer")
        );
    }
}
