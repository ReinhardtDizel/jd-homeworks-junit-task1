import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.Contact;
import ru.netology.Group;
import ru.netology.CallService;

import java.util.ArrayList;
import java.util.List;

public class MainTest {

    final CallService callService = new CallService();
    final Contact one = new Contact("Миша", "8(087)161-06-67");
    final Contact two = new Contact("Маша", "8(727)950-66-53");
    final Contact three = new Contact("Вася", "8(1481)901-93-73");
    final Contact threeTest = new Contact("Антон", "8(1481)901-93-73");
    final Contact seven = new Contact("Сестра", "8(592)135-51-49");
    final Contact eight = new Contact("Тянка", "8(55)984-70-58");
    final Contact nine = new Contact("Чувак", "8(839)663-08-65");
    final Contact ten = new Contact("Маша", "8(458)950-66-44");

    @Test
    @DisplayName("Add Contacts")
    public void addContactTest() {

        callService.addContact(one, Group.FRIENDS);
        Assertions.assertEquals("8(087)161-06-67", callService.findContact(one.getPhone()).getPhone(), "Контакт добавился неверно!");

        callService.addContact(two, Group.FRIENDS);
        Assertions.assertEquals("8(727)950-66-53", callService.findContact(two.getPhone()).getPhone(), "Контакт добавился неверно!");

        callService.addContact(three, Group.FRIENDS, Group.WORK);
        Assertions.assertEquals("8(1481)901-93-73", callService.findContact(three.getPhone()).getPhone(), "Контакт добавился неверно!");
    }

    @Test
    @DisplayName("Add Contacts With Exists Phone Number")
    public void addContactWithExistsPhoneTest() {

        callService.addContact(three, Group.FRIENDS, Group.WORK);
        Assertions.assertEquals("8(1481)901-93-73", callService.findContact(three.getPhone()).getPhone(), "Контакт добавился неверно!");

        callService.addContact(threeTest, Group.WORK);
        Assertions.assertNotEquals("Антон", callService.findContact(threeTest.getPhone()).getName(), "Контакт не должен добавиться!неверно!");
    }

    @Test
    @DisplayName("Make Call")
    public void makeCallTest() {

        callService.addContact(three, Group.FRIENDS, Group.WORK);
        callService.addContact(one, Group.FRIENDS);
        callService.makeCall(ten.getPhone());
        callService.makeCall(nine.getPhone());
        callService.makeCall(eight.getPhone());
        callService.makeCall(three.getPhone());
        callService.makeCall(one.getPhone());
        callService.makeCall(seven.getPhone());
        List<Contact> contactList = new ArrayList<>(callService.getMissedCall().values());
        Assertions.assertEquals(ten.getPhone(), contactList.get(0).getPhone(), "Телефоны должны храниться в порядке поступления звонка!");
    }
}
