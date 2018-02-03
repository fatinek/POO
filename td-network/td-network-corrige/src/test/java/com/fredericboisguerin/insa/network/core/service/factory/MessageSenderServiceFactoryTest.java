package com.fredericboisguerin.insa.network.core.service.factory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.fredericboisguerin.insa.network.core.service.MessageSenderService;
import com.fredericboisguerin.insa.network.core.service.TCPMessageSenderService;
import com.fredericboisguerin.insa.network.core.service.UDPMessageSenderService;

public class MessageSenderServiceFactoryTest {

    private MessageSenderServiceFactory networkMessageSenderServiceFactory;

    @Before
    public void setUp() throws Exception {
        networkMessageSenderServiceFactory = new MessageSenderServiceFactory();
    }

    @Test
    public void should_build_message_receiver_TCP() throws Exception {
        MessageSenderService messageSenderService = networkMessageSenderServiceFactory.onTCP();

        assertThat(messageSenderService).isInstanceOf(TCPMessageSenderService.class);
    }

    @Test
    public void should_build_message_receiver_UDP() throws Exception {
        MessageSenderService messageSenderService = networkMessageSenderServiceFactory.onUDP();

        assertThat(messageSenderService).isInstanceOf(UDPMessageSenderService.class);
    }
}