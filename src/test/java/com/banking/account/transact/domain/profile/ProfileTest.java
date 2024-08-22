package com.banking.account.transact.domain.profile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ProfileTest {

    @Test
    void testGetId() {
        Profile profile = new Profile();
        Long expectedId = 1L;

        // Para este exemplo, utilizamos Reflection para definir o valor do id,
        // já que o campo não tem um setter público
        try {
            var idField = Profile.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(profile, expectedId);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Falha ao definir o campo 'id': " + e.getMessage());
        }

        assertEquals(expectedId, profile.getId());
    }

    @Test
    void testGetAuthority() {
        Profile profile = new Profile();
        String expectedAuthority = "ROLE_USER";

        // Para este exemplo, utilizamos Reflection para definir o valor do nome,
        // já que o campo não tem um setter público
        try {
            var nameField = Profile.class.getDeclaredField("nome");
            nameField.setAccessible(true);
            nameField.set(profile, expectedAuthority);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Falha ao definir o campo 'nome': " + e.getMessage());
        }

        assertEquals(expectedAuthority, profile.getAuthority());
    }
}
