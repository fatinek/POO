package com.fredericboisguerin.insa.contactsmanager.core.ui;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import org.junit.Before;
import org.junit.Test;

public class GeekMenuTest {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String MENU_AS_STRING = new StringJoiner(LINE_SEPARATOR).add("------- ACTIONS -------")
            .add("0: exit")
            .add("1: print all contacts")
            .add("2: search for a contact")
            .add("3: add a contact").add("> ")
            .toString();
    private static final int EXIT_CHOICE = 0;

    private GeekMenu geekMenu;
    private ByteArrayOutputStream out;
    private GeekUI geekUI = mock(GeekUI.class);

    @Before
    public void setUp() throws Exception {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        geekMenu = new GeekMenu(geekUI);
    }

    @Test
    public void should_print_choices_and_quit_when_choice_is_0() throws Exception {
        int exitChoice = 0;
        setUserInput(exitChoice + LINE_SEPARATOR);

        geekMenu.display();

        assertThat(standardOutput()).isEqualTo(MENU_AS_STRING);
    }

    @Test
    public void should_print_all_contacts_when_user_enters_choice_1() throws Exception {
        setUserChoiceBeforeExiting(1);

        geekMenu.display();

        verify(geekUI).printAllContacts();
    }

    @Test
    public void should_search_contact_when_user_enters_choice_2() throws Exception {
        setUserChoiceBeforeExiting(2);

        geekMenu.display();

        verify(geekUI).askForNameToSearch();
    }

    @Test
    public void should_ask_for_contact_information_when_user_enters_choice_3() throws Exception {
        setUserChoiceBeforeExiting(3);

        geekMenu.display();

        verify(geekUI).askForContactInformation();
    }

    @Test
    public void should_display_menu_again_when_the_previous_choice_was_not_0() throws Exception {
        setUserChoiceBeforeExiting(1);

        geekMenu.display();

        assertThat(standardOutput()).isEqualTo(MENU_AS_STRING + MENU_AS_STRING);

    }

    private String standardOutput() {
        return out.toString();
    }

    private static void setUserChoiceBeforeExiting(int choice) {
        String input = choice + LINE_SEPARATOR + EXIT_CHOICE + LINE_SEPARATOR;
        setUserInput(input);
    }

    private static void setUserInput(String input) {
        byte[] inputAsBytes = input.getBytes();
        System.setIn(new ByteArrayInputStream(inputAsBytes));
    }
}