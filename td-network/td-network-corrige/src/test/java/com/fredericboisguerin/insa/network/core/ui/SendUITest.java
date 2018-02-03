package com.fredericboisguerin.insa.network.core.ui;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.fredericboisguerin.insa.network.core.service.MessageSenderService;
import com.fredericboisguerin.insa.network.core.service.factory.MessageSenderServiceFactory;
import com.fredericboisguerin.insa.network.infrastructure.terminal.Terminal;

public class SendUITest {

    private static final String USER_IP_ADDRESS = "some IP address";
    private static final int USER_PORT = 1234;
    private static final String USER_MESSAGE = "some message";
    private static final String ERROR_MESSAGE = "[ERROR] An error occured while trying to send message";
    private static final String NOTIFICATION_FORMAT = "[INFO] Your message has been sent to %s on port %d";

    @Mock private MessageSenderServiceFactory messageSenderServiceFactory;
    @Mock private MessageSenderService tcpMessageSender;
    @Mock private MessageSenderService udpMessageSender;
    @Mock private Terminal terminal;

    private SendUI sendUI;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(messageSenderServiceFactory.onTCP()).thenReturn(tcpMessageSender);
        when(messageSenderServiceFactory.onUDP()).thenReturn(udpMessageSender);
        when(terminal.readLine()).thenReturn(USER_IP_ADDRESS).thenReturn(USER_MESSAGE);
        when(terminal.readInt()).thenReturn(USER_PORT);
        sendUI = new SendUI(messageSenderServiceFactory, terminal);
    }

    @Test
    public void should_send_message_in_TCP_with_information_given_by_user() throws Exception {
        sendUI.onTCP();

        verify(tcpMessageSender).sendMessageOn(USER_IP_ADDRESS, USER_PORT, USER_MESSAGE);
    }

    @Test
    public void should_send_message_in_UDP_with_information_given_by_user() throws Exception {
        sendUI.onUDP();

        verify(udpMessageSender).sendMessageOn(USER_IP_ADDRESS, USER_PORT, USER_MESSAGE);
    }

    @Test
    public void should_inform_that_message_was_sent_in_TCP() throws Exception {
        String expectedMessage = String.format(NOTIFICATION_FORMAT, USER_IP_ADDRESS, USER_PORT);

        sendUI.onTCP();

        verify(terminal).print(expectedMessage);
    }

    @Test
    public void should_inform_that_message_was_sent_in_UDP() throws Exception {
        String expectedMessage = String.format(NOTIFICATION_FORMAT, USER_IP_ADDRESS, USER_PORT);

        sendUI.onUDP();

        verify(terminal).print(expectedMessage);
    }

    @Test
    public void should_inform_that_an_error_occured_on_TCP() throws Exception {
        doThrow(Exception.class).when(tcpMessageSender).sendMessageOn(USER_IP_ADDRESS, USER_PORT, USER_MESSAGE);

        sendUI.onTCP();

        verify(terminal).printError(ERROR_MESSAGE);
    }

    @Test
    public void should_inform_that_an_error_occured_on_UDP() throws Exception {
        doThrow(Exception.class).when(udpMessageSender).sendMessageOn(USER_IP_ADDRESS, USER_PORT, USER_MESSAGE);

        sendUI.onUDP();

        verify(terminal).printError(ERROR_MESSAGE);
    }
}