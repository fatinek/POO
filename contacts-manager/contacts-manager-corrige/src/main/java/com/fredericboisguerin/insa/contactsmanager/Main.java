package com.fredericboisguerin.insa.contactsmanager;

import com.fredericboisguerin.insa.contactsmanager.core.service.ContactsManager;
import com.fredericboisguerin.insa.contactsmanager.core.ui.GeekMenu;
import com.fredericboisguerin.insa.contactsmanager.core.ui.GeekUI;

public class Main {

    public static void main(String[] args) throws Exception {
        GeekMenu geekMenu = new GeekMenu(new GeekUI(new ContactsManager()));
        geekMenu.display();
    }
}
