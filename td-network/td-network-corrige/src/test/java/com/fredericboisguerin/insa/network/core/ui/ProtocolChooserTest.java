package com.fredericboisguerin.insa.network.core.ui;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.fredericboisguerin.insa.network.infrastructure.terminal.Terminal;

public class ProtocolChooserTest {

    private static final int UDP_CODE = 1;
    private static final int TCP_CODE = 2;

    private ProtocolChooser protocolChooser;
    private CommunicationUI communicationUI = mock(CommunicationUI.class);
    private Terminal terminal = mock(Terminal.class);

    @Before
    public void setUp() throws Exception {
        protocolChooser = new ProtocolChooser(terminal);
    }

    @Test
    public void should_inform_UDP_to_receiving_ui_when_user_chose_option_1() throws Exception {
        when(terminal.readInt()).thenReturn(UDP_CODE);

        protocolChooser.askProtocolOn(communicationUI);

        verify(communicationUI).onUDP();
    }

    @Test
    public void should_inform_UDP_to_receiving_ui_when_user_chose_option_2() throws Exception {
        when(terminal.readInt()).thenReturn(TCP_CODE);

        protocolChooser.askProtocolOn(communicationUI);

        verify(communicationUI).onTCP();
    }
}