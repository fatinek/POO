package com.fredericboisguerin.insa.contactsmanager.core.service;

import static java.lang.System.lineSeparator;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

public class ContactsManagerMust {

    private static final String FIELD_SEPARATOR = ", ";

    private static final String NICOLE_FERRONI_NAME = "Nicole Ferroni";
    private static final String NICOLE_FERRONI_EMAIL = "contact@nicoleferroni.fr";
    private static final String NICOLE_FERRONI_PHONE_NUMBER = "0651387945";

    private static final String GUILLAUME_MEURICE_NAME = "Guillaume Meurice";
    private static final String GUILLAUME_MEURICE_EMAIL = "contact@guillaumemeurice.fr";
    private static final String GUILLAUME_MEURICE_PHONE_NUMBER = "0615389254";
    public static final String LINE_SEPARATOR = lineSeparator();

    private ByteArrayOutputStream out;
    private ContactsManager contactsManager;

    @Before
    public void setUp() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        contactsManager = new ContactsManager();
    }

    @Test
    public void list_one_contact_without_email_nor_phone_number() throws Exception {
        String noEmail = null;
        String noPhoneNumber = null;
        contactsManager.addContact(NICOLE_FERRONI_NAME, noEmail, noPhoneNumber);

        contactsManager.printAllContacts();

        assertThat(standardOutput()).contains(NICOLE_FERRONI_NAME);
    }

    @Test
    public void list_one_contact_without_email() throws Exception {
        String noEmail = null;
        contactsManager.addContact(NICOLE_FERRONI_NAME, noEmail, NICOLE_FERRONI_PHONE_NUMBER);

        contactsManager.printAllContacts();

        String expectedOutput = NICOLE_FERRONI_NAME + FIELD_SEPARATOR + NICOLE_FERRONI_PHONE_NUMBER;
        assertThat(standardOutput()).contains(expectedOutput);
    }

    @Test
    public void list_one_contact_without_phone_number() throws Exception {
        String noPhoneNumber = null;
        contactsManager.addContact(NICOLE_FERRONI_NAME, NICOLE_FERRONI_EMAIL, noPhoneNumber);

        contactsManager.printAllContacts();

        String expectedOutput = NICOLE_FERRONI_NAME + FIELD_SEPARATOR + NICOLE_FERRONI_EMAIL;
        assertThat(standardOutput()).contains(expectedOutput);
    }

    @Test
    public void list_one_added_contact() throws Exception {
        contactsManager.addContact(NICOLE_FERRONI_NAME, NICOLE_FERRONI_EMAIL, NICOLE_FERRONI_PHONE_NUMBER);

        contactsManager.printAllContacts();

        String expectedOutput = NICOLE_FERRONI_NAME + FIELD_SEPARATOR + NICOLE_FERRONI_EMAIL + FIELD_SEPARATOR + NICOLE_FERRONI_PHONE_NUMBER;
        assertThat(standardOutput()).contains(expectedOutput);
    }

    @Test
    public void list_two_added_contacts() throws Exception {
        contactsManager.addContact(NICOLE_FERRONI_NAME, NICOLE_FERRONI_EMAIL, NICOLE_FERRONI_PHONE_NUMBER);
        contactsManager.addContact(GUILLAUME_MEURICE_NAME, GUILLAUME_MEURICE_EMAIL, GUILLAUME_MEURICE_PHONE_NUMBER);

        contactsManager.printAllContacts();

        String standardOutput = standardOutput();
        String firstContactInfo = NICOLE_FERRONI_NAME + FIELD_SEPARATOR + NICOLE_FERRONI_EMAIL + FIELD_SEPARATOR + NICOLE_FERRONI_PHONE_NUMBER;
        String secondContactInfo = GUILLAUME_MEURICE_NAME + FIELD_SEPARATOR + GUILLAUME_MEURICE_EMAIL + FIELD_SEPARATOR + GUILLAUME_MEURICE_PHONE_NUMBER;
        assertThat(standardOutput).contains(firstContactInfo);

        assertThat(standardOutput).contains(secondContactInfo);
    }

    @Test
    public void retrieve_a_contact_from_its_name() throws Exception {
        contactsManager.addContact(NICOLE_FERRONI_NAME, NICOLE_FERRONI_EMAIL, NICOLE_FERRONI_PHONE_NUMBER);
        contactsManager.addContact(GUILLAUME_MEURICE_NAME, GUILLAUME_MEURICE_EMAIL, GUILLAUME_MEURICE_PHONE_NUMBER);

        contactsManager.searchContactByName("meuri");

        String expectedOutput = GUILLAUME_MEURICE_NAME + FIELD_SEPARATOR + GUILLAUME_MEURICE_EMAIL + FIELD_SEPARATOR + GUILLAUME_MEURICE_PHONE_NUMBER;
        assertThat(standardOutput()).isEqualTo(expectedOutput + LINE_SEPARATOR);
    }

    @Test
    public void should_inform_when_no_contact_has_been_found() throws Exception {
        String notExisting = "not existing";

        contactsManager.searchContactByName(notExisting);

        assertThat(standardOutput()).isEqualTo("No contact found with name : " + notExisting + LINE_SEPARATOR);
    }

    private String standardOutput() {
        return out.toString();
    }

}
