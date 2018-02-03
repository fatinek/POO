package com.fredericboisguerin.insa.network.core.service.factory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.fredericboisguerin.insa.network.core.service.MessageReceiverService;
import com.fredericboisguerin.insa.network.core.service.TCPMessageReceiverService;
import com.fredericboisguerin.insa.network.core.service.UDPMessageReceiverService;

public class MessageReceiverServiceFactoryTest {

    private MessageReceiverServiceFactory networkMessageReceiverServiceFactory;

    @Before
    public void setUp() throws Exception {
        networkMessageReceiverServiceFactory = new MessageReceiverServiceFactory();
    }

    @Test
    public void should_build_message_receiver_TCP() throws Exception {
        MessageReceiverService messageReceiverService = networkMessageReceiverServiceFactory.onTCP();

        assertThat(messageReceiverService).isInstanceOf(TCPMessageReceiverService.class);
    }

    @Test
    public void should_build_message_receiver_UDP() throws Exception {
        MessageReceiverService messageReceiverService = networkMessageReceiverServiceFactory.onUDP();

        assertThat(messageReceiverService).isInstanceOf(UDPMessageReceiverService.class);
    }
}