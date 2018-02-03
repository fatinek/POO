package com.fredericboisguerin.insa.contactsmanager.core.ui;

import java.util.Scanner;

import com.fredericboisguerin.insa.contactsmanager.core.service.ContactsManager;
import com.fredericboisguerin.insa.contactsmanager.core.service.InvalidContactNameException;
import com.fredericboisguerin.insa.contactsmanager.core.service.InvalidEmailException;

public class GeekUI {
    private final ContactsManager contactsManager;

    public GeekUI(ContactsManager contactsManager) {
        this.contactsManager = contactsManager;
    }

    public void askForContactInformation() {
        Scanner scanner = createScanner();
        System.out.print("Name (mandatory): ");
        String name = scanner.nextLine();
        System.out.print("Email (optional): ");
        String email = scanner.nextLine();
        System.out.print("Phone number (optional): ");
        String phoneNumber = scanner.nextLine();
        try {
            contactsManager.addContact(name, email, phoneNumber);
        } catch (InvalidContactNameException e) {
            logError("The name entered is not valid.");
        } catch (InvalidEmailException e) {
            logError("The email entered is not valid.");
        }
    }

    public void askForNameToSearch() {
        Scanner scanner = createScanner();
        String pattern = scanner.nextLine();
        contactsManager.searchContactByName(pattern);
    }

    public void printAllContacts() {
        contactsManager.printAllContacts();
    }

    private static void logError(String message) {
        System.out.println("[ERROR] " + message);
    }

    private static Scanner createScanner() {
        return new Scanner(System.in);
    }
}
