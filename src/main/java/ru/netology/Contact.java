package ru.netology;

public class Contact implements Comparable<Contact> {

    private final String name;
    private final String phone;

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;

        Contact contact = (Contact) o;

        return getPhone().equals(contact.getPhone());
    }

    @Override
    public int hashCode() {
        return getPhone().hashCode();
    }

    @Override
    public int compareTo(Contact o) {
        int result = name.compareTo(o.getName());
        if (result == 0) {
            result = phone.compareTo(o.getPhone());
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", name == null ? "" : name, phone);
    }
}
