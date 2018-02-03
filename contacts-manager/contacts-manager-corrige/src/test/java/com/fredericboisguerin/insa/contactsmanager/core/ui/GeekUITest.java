package com.fredericboisguerin.insa.contactsmanager.core.ui;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import com.fredericboisguerin.insa.contactsmanager.core.service.ContactsManager;
import com.fredericboisguerin.insa.contactsmanager.core.service.InvalidContactNameException;
import com.fredericboisguerin.insa.contactsmanager.core.service.InvalidEmailException;

public class GeekUITest {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String SOME_PHONE_NUMBER = "some phone number";
    private static final String SOME_EMAIL = "some email";
    private static final String SOME_NAME = "some name";

    private GeekUI geekUI;
    private ContactsManager contactsManager = mock(ContactsManager.class);
    private ByteArrayOutputStream out;

    @Before
    public void setUp() throws Exception {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        geekUI = new GeekUI(contactsManager);
    }

    @Test
    public void should_add_a_contact_to_contacts_manager_when_user_enter_contact_information() throws Exception {
        setUserInput(SOME_NAME + LINE_SEPARATOR + SOME_EMAIL + LINE_SEPARATOR + SOME_PHONE_NUMBER + LINE_SEPARATOR);

        geekUI.askForContactInformation();

        verify(contactsManager).addContact(SOME_NAME, SOME_EMAIL, SOME_PHONE_NUMBER);
    }

    @Test
    public void should_search_contact_when_user_enter_a_name_to_search() throws Exception {
        setUserInput(SOME_NAME + LINE_SEPARATOR);

        geekUI.askForNameToSearch();

        verify(contactsManager).searchContactByName(SOME_NAME);
    }

    @Test
    public void should_print_all_contacts_from_contacts_manager() throws Exception {
        geekUI.printAllContacts();

        verify(contactsManager).printAllContacts();
    }

    @Test
    public void should_inform_that_name_is_incorrect_on_invalid_contact_name_exception() throws Exception {
        setUserInput(SOME_NAME + LINE_SEPARATOR + SOME_EMAIL + LINE_SEPARATOR + SOME_PHONE_NUMBER + LINE_SEPARATOR);
        doThrow(InvalidContactNameException.class).when(contactsManager).addContact(anyString(), anyString(), anyString());

        geekUI.askForContactInformation();

        assertThat(standardOutput()).contains("[ERROR] The name entered is not valid." + LINE_SEPARATOR);
    }

    @Test
    public void should_inform_that_email_is_incorrect_on_invalid_contact_name_exception() throws Exception {
        setUserInput(SOME_NAME + LINE_SEPARATOR + SOME_EMAIL + LINE_SEPARATOR + SOME_PHONE_NUMBER + LINE_SEPARATOR);
        doThrow(InvalidEmailException.class).when(contactsManager).addContact(anyString(), anyString(), anyString());

        geekUI.askForContactInformation();

        assertThat(standardOutput()).contains("[ERROR] The email entered is not valid." + LINE_SEPARATOR);
    }

    private String standardOutput() {
        return out.toString();
    }

    private void setUserInput(String input) {
        byte[] inputAsBytes = input.getBytes();
        System.setIn(new ByteArrayInputStream(inputAsBytes));
    }
}