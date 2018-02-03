package com.fredericboisguerin.insa.network.infrastructure.terminal;

import static java.lang.System.setErr;
import static java.lang.System.setOut;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

public class TerminalTest {

    public static final String LINE_SEPARATOR = System.lineSeparator();
    private Terminal terminal;
    private ByteArrayOutputStream standardOutput;
    private ByteArrayOutputStream errorOutput;

    @Before
    public void setUp() throws Exception {
        standardOutput = new ByteArrayOutputStream();
        setOut(new PrintStream(standardOutput));
        errorOutput = new ByteArrayOutputStream();
        setErr(new PrintStream(errorOutput));
        terminal = new Terminal();
    }

    @Test
    public void should_print_out_message() throws Exception {
        String someMessage = "some message";

        terminal.print(someMessage);

        assertThat(consoleOutput()).isEqualTo(someMessage + LINE_SEPARATOR);
    }

    @Test
    public void should_get_integer_choice() throws Exception {
        int userChoice = 3;
        setUserInput(userChoice + LINE_SEPARATOR);

        int readChoice = terminal.readInt();

        assertThat(readChoice).isEqualTo(userChoice);
    }

    @Test
    public void should_read_line() throws Exception {
        String userInput = "some input";
        setUserInput(userInput + LINE_SEPARATOR);

        String readLine = terminal.readLine();

        assertThat(readLine).isEqualTo(userInput);
    }

    @Test
    public void should_print_message_on_error_output() throws Exception {
        String someMessage = "some message";

        terminal.printError(someMessage);

        assertThat(errorOutput()).isEqualTo(someMessage + LINE_SEPARATOR);
    }

    @Test
    public void should_print_exception_stack_trace() throws Exception {
        Exception exception = mock(Exception.class);

        terminal.printError(exception);

        verify(exception).printStackTrace();
    }

    private static void setUserInput(String input) {
        byte[] inputAsBytes = input.getBytes();
        System.setIn(new ByteArrayInputStream(inputAsBytes));
    }

    private String errorOutput() {
        return errorOutput.toString();
    }

    private String consoleOutput() {
        return standardOutput.toString();
    }

}