package com.fredericboisguerin.insa.network.core.ui;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.StringJoiner;

import org.junit.Before;
import org.junit.Test;

import com.fredericboisguerin.insa.network.infrastructure.terminal.Terminal;

public class StartingUITest {

    private static final int NO_ACTION = 0;
    private static final int RECEIVE = 1;
    private static final int SEND = 2;
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String ENTER_YOUR_CHOICE = "Enter your choice: ";
    private static final String POSSIBLE_ACTIONS = new StringJoiner(LINE_SEPARATOR).add("What do you wanna do?")
            .add(RECEIVE + "> Listen to incoming messages")
            .add(SEND + "> Send messages")
            .toString();

    private StartingUI startingUI;
    private ProtocolChooser protocolChooser = mock(ProtocolChooser.class);

    private ReceiveUI receiveUI = mock(ReceiveUI.class);
    private SendUI sendUI = mock(SendUI.class);
    private Terminal terminal = mock(Terminal.class);

    @Before
    public void setUp() throws Exception {
        startingUI = new StartingUI(terminal, protocolChooser, receiveUI, sendUI);
    }

    @Test
    public void should_print_possible_actions() throws Exception {
        when(terminal.readInt()).thenReturn(NO_ACTION);

        startingUI.askForAction();

        verify(terminal).print(POSSIBLE_ACTIONS);
        verify(terminal).print(ENTER_YOUR_CHOICE);
    }

    @Test
    public void should_ask_protocol_to_receiver_ui_when_user_choose_to_listen_to_incoming_messages() throws Exception {
        when(terminal.readInt()).thenReturn(RECEIVE);

        startingUI.askForAction();

        verify(protocolChooser).askProtocolOn(receiveUI);
    }

    @Test
    public void should_ask_protocol_to_sender_ui_when_user_choose_to_send_messages() throws Exception {
        when(terminal.readInt()).thenReturn(SEND);

        startingUI.askForAction();

        verify(protocolChooser).askProtocolOn(sendUI);
    }
}