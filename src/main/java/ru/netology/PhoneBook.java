package ru.netology;

import java.util.*;

public class PhoneBook {

    private final Map<Group, List<Contact>> data = new HashMap<>();

    public void addContact(Contact contact, Group... groups) {

        boolean exists = false;
        for (Group group : groups) {
            List<Contact> contacts = data.get(group);
            if (contacts != null) {
                int index = contacts.indexOf(contact);
                if (index != -1) {
                    exists = true;
                }
            }
        }
        if (!exists) {
            for (Group group : groups) {
                List<Contact> newContactsList = new ArrayList<>();
                newContactsList.add(contact);
                data.merge(group, newContactsList, (v1, v2) -> {
                    v1.addAll(v2);
                    return v1;
                });
            }
        }
    }

    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        for (Map.Entry<Group, List<Contact>> entry : data.entrySet()) {
            if (!contacts.isEmpty()) {
                contacts.retainAll(entry.getValue());
            } else {
                contacts.addAll(entry.getValue());
            }
        }
        return contacts;
    }

    public Contact findContactByPhone(String phone) {
        for (Map.Entry<Group, List<Contact>> entry : data.entrySet()) {
            for (Contact contact : entry.getValue()) {
                if (contact.getPhone().equals(phone)) {
                    return contact;
                }
            }
        }
        return null;
    }

    public Set<Contact> findContactByName(String name) {
        Set<Contact> contacts = new HashSet<>();
        for (Map.Entry<Group, List<Contact>> entry : data.entrySet()) {
            for (Contact contact : entry.getValue()) {
                if (contact.getName().equals(name)) {
                    contacts.add(contact);
                }
            }
        }
        return contacts;
    }

    /*
    Этот метод проходит циклом по массиву groups.
    При первом проходе contactSet.isEmpty()==true
    поэтому в contactSet запишутся все Контакты группы по первой итерации.
    При каждой следующей итерации contactSet.isEmpty()==false будет вызываться метод retainAll,
    который согласно документации будет исключать из существующей коллекции (contactSet) "подколлекцию" не входящую в параметр метода retainAll.
    Фактически будут удалены все контакты, не входящие в группу этой итерации.

    @param groups Принимает массив Group - группы, в которых должен состоять Искомый контакт
    @return возвращает список найденных контактов, если они нашлись, иначе вернет пустой список
    */
    public List<Contact> findContactByGroup(Group... groups) {
        List<Contact> contactSet = new ArrayList<>();
        for (Group group : groups) {
            try {
                List<Contact> searchGroup = data.get(group);
                if (!contactSet.isEmpty()) {
                    contactSet.retainAll(searchGroup);
                } else {
                    contactSet.addAll(searchGroup);
                }
            } catch (NullPointerException ignored) {
                return contactSet;
            }
        }
        return contactSet;
    }
}
