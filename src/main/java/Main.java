import ru.netology.Contact;
import ru.netology.Group;
import ru.netology.CallService;

import java.util.List;
import java.util.Map;

public class Main {

    static private final CallService callService = new CallService();

    public static void main(String... args) {

        final Contact one = new Contact("Миша", "8(087)161-06-67");
        final Contact two = new Contact("Маша1", "8(727)950-66-53");
        final Contact three = new Contact("Вася", "8(1481)901-93-73");
        final Contact four = new Contact("Антон", "8(9144)912-31-85");
        final Contact five = new Contact("Мама", "8(0520)564-03-62");
        final Contact six = new Contact("Папа", "8(892)281-48-09");
        final Contact seven = new Contact("Сестра", "8(592)135-51-49");
        final Contact eight = new Contact("Тянка", "8(55)984-70-58");
        final Contact nine = new Contact("Чувак", "8(839)663-08-65");
        final Contact ten = new Contact("Маша2", "8(458)950-66-44");

        callService.addContact(three, Group.FRIENDS, Group.WORK);
        callService.addContact(two, Group.FRIENDS);
        callService.addContact(five, Group.WORK);
        callService.addContact(four, Group.FRIENDS);
        callService.addContact(six, Group.WORK);
        callService.addContact(ten, Group.FRIENDS, Group.WORK);


        callService.makeCall(ten.getPhone());
        callService.makeCall(nine.getPhone());
        callService.makeCall(eight.getPhone());
        callService.makeCall(three.getPhone());
        callService.makeCall(one.getPhone());
        callService.makeCall(seven.getPhone());

        printMissedCall(callService.getMissedCall());

        System.out.println();
        System.out.println("Хочу найти только контакты, входящие в группы FRIENDS, WORK, FAMILY, должна быть только Маша2");
        // Хочу найти только контакты, входящие в группы FRIENDS, WORK, FAMILY, должна быть только Маша2
        List<Contact> testGroup = callService.getContactsByGroups(Group.FAMILY, Group.FRIENDS, Group.WORK);
        testGroup.forEach(System.out::println);

        System.out.println();
        System.out.println("Хочу найти только контакты, входящие в группы WORK, FAMILY, должны быть только Маша2 и Вася");
        // Хочу найти только контакты, входящие в группы WORK, FAMILY, должны быть только Маша2 и Вася
        List<Contact> testGroup2 = callService.getContactsByGroups(Group.WORK, Group.FRIENDS);
        testGroup2.forEach(System.out::println);

        System.out.println();
        System.out.println("Хочу найти только контакты, входящие в группу FRIENDS, должны быть только Маша2, Маша1, Антон и Вася");
        // Хочу найти только контакты, входящие в группу FRIENDS, должны быть только Маша2, Маша1, Антон и Вася
        List<Contact> testGroup3 = callService.getContactsByGroups(Group.FRIENDS);
        testGroup3.forEach(System.out::println);
    }

    public static void printMissedCall(Map<String, Contact> calls) {
        System.out.println("\nПропущенные вызовы:");
        for (Map.Entry<String, Contact> contactEntry : calls.entrySet()) {
            System.out.printf("%s звонил(a) вам %s\n", contactEntry.getValue().getName() == null ?
                    contactEntry.getValue().getPhone() :
                    contactEntry.getValue().getName(), contactEntry.getKey());
        }
    }
}

