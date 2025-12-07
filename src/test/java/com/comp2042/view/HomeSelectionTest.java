package com.comp2042.view;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HomeSelectionTest {

    @Test
    void constructor_StoresModeAndOption() {
        HomeSelection selection =
                new HomeSelection(HomeSelection.Mode.TIME_RACING, "1 Minute Sprint");

        assertEquals(HomeSelection.Mode.TIME_RACING, selection.mode());
        assertEquals("1 Minute Sprint", selection.option());
    }

    @Test
    void equalsAndHashCode_BasedOnModeAndOption() {
        HomeSelection first =
                new HomeSelection(HomeSelection.Mode.COUNTRY_EXPLORE, "China");
        HomeSelection second =
                new HomeSelection(HomeSelection.Mode.COUNTRY_EXPLORE, "China");
        HomeSelection differentMode =
                new HomeSelection(HomeSelection.Mode.TIME_RACING, "China");
        HomeSelection differentOption =
                new HomeSelection(HomeSelection.Mode.COUNTRY_EXPLORE, "Japan");

        assertEquals(first, second, "Selections with same mode and option should be equal");
        assertEquals(first.hashCode(), second.hashCode(),
                "Equal selections should have same hashCode");

        assertNotEquals(first, differentMode,
                "Different mode should make selections unequal");
        assertNotEquals(first, differentOption,
                "Different option should make selections unequal");
    }
}
