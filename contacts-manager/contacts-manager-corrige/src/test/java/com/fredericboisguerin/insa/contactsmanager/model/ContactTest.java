package com.fredericboisguerin.insa.contactsmanager.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ContactTest {

    private static final String NO_EMAIL = null;
    private static final String NO_PHONE_NUMBER = null;

    @Test
    public void should_match_if_contact_name_contains_pattern() {
        Contact contact = new Contact("Daniela Dragomirescu", NO_EMAIL, NO_PHONE_NUMBER);

        boolean nameMatches = contact.nameMatches("drago");

        assertThat(nameMatches).isTrue();
    }

    @Test
    public void should_not_match_if_contact_name_does_not_contain_pattern() {
        Contact contact = new Contact("Daniela Dragomirescu", NO_EMAIL, NO_PHONE_NUMBER);

        boolean nameMatches = contact.nameMatches("patrick");

        assertThat(nameMatches).isFalse();
    }
}