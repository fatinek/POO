package com.fredericboisguerin.insa.contactsmanager.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import com.fredericboisguerin.insa.contactsmanager.model.Contact;

public class ContactsManager {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z.-]+\\.[a-zA-Z]+$";
    private final List<Contact> contacts = new ArrayList<>();

    public void addContact(String name, String email, String phoneNumber)
            throws InvalidContactNameException, InvalidEmailException {
        if (name == null || name.isEmpty()) {
            throw new InvalidContactNameException();
        }
        if (email != null && !email.matches(EMAIL_REGEX)) {
            throw new InvalidEmailException();
        }
        contacts.add(new Contact(name, email, phoneNumber));
    }

    public void printAllContacts() {
        contacts.forEach(this::print);
    }

    public void searchContactByName(String name) {
        List<Contact> matchingContacts = contacts.stream()
                .filter(contact -> contact.nameMatches(name))
                .collect(Collectors.toList());
        if (matchingContacts.size() > 0) {
            matchingContacts.forEach(this::print);
        } else {
            System.out.println("No contact found with name : " + name);
        }
    }

    private void print(Contact contact) {
        StringJoiner stringJoiner = new StringJoiner(", ");
        stringJoiner.add(contact.getName());
        String email = contact.getEmail();
        if (email != null) {
            stringJoiner.add(email);
        }
        String phoneNumber = contact.getPhoneNumber();
        if (phoneNumber != null) {
            stringJoiner.add(phoneNumber);
        }
        System.out.println(stringJoiner.toString());
    }
}
