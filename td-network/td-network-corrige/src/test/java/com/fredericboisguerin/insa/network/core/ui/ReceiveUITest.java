package com.fredericboisguerin.insa.network.core.ui;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.fredericboisguerin.insa.network.core.service.MessageReceiverService;
import com.fredericboisguerin.insa.network.core.service.factory.MessageReceiverServiceFactory;
import com.fredericboisguerin.insa.network.infrastructure.terminal.Terminal;

public class ReceiveUITest {

    private static final int PORT_CHOOSEN_BY_USER = 1234;
    private static final String ERROR_MESSAGE = "[ERROR] An error occured while trying to listen on port";

    @Mock private MessageReceiverServiceFactory messageReceiverServiceFactory;
    @Mock private MessageReceiverService tcpMessageReceiver;
    @Mock private MessageReceiverService udpMessageReceiver;
    @Mock private Terminal terminal;

    private ReceiveUI receiveUI;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(messageReceiverServiceFactory.onTCP()).thenReturn(tcpMessageReceiver);
        when(messageReceiverServiceFactory.onUDP()).thenReturn(udpMessageReceiver);
        when(terminal.readInt()).thenReturn(PORT_CHOOSEN_BY_USER);
        receiveUI = new ReceiveUI(terminal, messageReceiverServiceFactory);
    }

    @Test
    public void should_listen_on_TCP_port_given_by_user() throws Exception {
        receiveUI.onTCP();

        verify(tcpMessageReceiver).listenOnPort(PORT_CHOOSEN_BY_USER, receiveUI);
    }

    @Test
    public void should_listen_on_UDP_port_given_by_user() throws Exception {
        receiveUI.onUDP();

        verify(udpMessageReceiver).listenOnPort(PORT_CHOOSEN_BY_USER, receiveUI);
    }

    @Test
    public void should_inform_that_an_error_occured_on_TCP() throws Exception {
        doThrow(Exception.class).when(tcpMessageReceiver).listenOnPort(PORT_CHOOSEN_BY_USER, receiveUI);

        receiveUI.onTCP();

        verify(terminal).printError(ERROR_MESSAGE);
    }

    @Test
    public void should_inform_that_an_error_occured_on_UDP() throws Exception {
        doThrow(Exception.class).when(udpMessageReceiver).listenOnPort(PORT_CHOOSEN_BY_USER, receiveUI);

        receiveUI.onUDP();

        verify(terminal).printError(ERROR_MESSAGE);
    }

    @Test
    public void should_print_incoming_messages() throws Exception {
        String receivedMessage = "some message";

        receiveUI.onNewIncomingMessage(receivedMessage);

        verify(terminal).print("New incoming message: " + receivedMessage);
    }
}