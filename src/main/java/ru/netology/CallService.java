package ru.netology;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class CallService {

    private final PhoneBook phoneBook = new PhoneBook();
    private final Map<String, Contact> missedCalls = new TreeMap<>();
    private int timeCounter = 0;

    public void addContact(Contact contact, Group... groups) {
        phoneBook.addContact(contact, groups);
    }

    public void clearMissedCall() {
        missedCalls.clear();
    }

    public Map<String, Contact> getMissedCall() {
        return missedCalls;
    }

    public Contact findContact(String phone) {
        return phoneBook.findContactByPhone(phone);
    }

    public List<Contact> getAllContacts() {
        return phoneBook.getAllContacts();
    }

    public List<Contact> getContactsByGroups(Group... groups) {
        return phoneBook.findContactByGroup(groups);
    }

    public void makeCall(String phone) {
        Contact contact = Objects.requireNonNullElse(findContact(phone), new Contact(null, phone));

        StringBuilder str = new StringBuilder("2022-01-01 12:");
        str.append(30 + timeCounter);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        System.out.printf("Входящий вызов %s время %s\n", contact.getName() == null ? contact.getPhone() : contact.getName(), dateTime);
        missedCalls.put(dateTime.toString(), contact);

        timeCounter++;
    }
}
