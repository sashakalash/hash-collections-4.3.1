import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class PhoneContacts {
    private static HashMap<String, ArrayList<Contact>> contacts = new HashMap<>();

    public static void addGroup(String title) {
        if (contacts.containsKey(title)) {
            System.out.println("Группа с таким названием уже существует! Задайте другое название");
            return;
        }
        contacts.put(title, new ArrayList());
    }

    public static void addContactToGroup(Contact contact, String groupTitle) {
        if (contacts.containsKey(groupTitle)) {
            ArrayList<Contact> groupContacts = contacts.get(groupTitle);

            int index = 0;
            Iterator<Contact> it = groupContacts.iterator();
            while(it.hasNext()) {
                Contact c = it.next();
                int i = Collections.binarySearch(groupContacts, c);
                if (c.compareTo(contact) == 1) {
                    break;
                }
                if (c.compareTo(contact) < 0) {
                    index = i + 1;
                }

            }
            groupContacts.add(index, contact);
            System.out.println(contact.getName() + " успешно добавлен в группу '" + groupTitle + "'");
        } else {
            addGroup(groupTitle);
            contacts.get(groupTitle).add(contact);
            System.out.println("Группы " + groupTitle + " не существует! Создана новая группа. Контакт добавлен в новую группу");
        }
    }

    public static void getContacts() {
        contacts.entrySet().forEach(entry -> {
            String group = entry.getKey();
            ArrayList<Contact> contacts = entry.getValue();
            System.out.println("- " + group + ":");
            if (contacts.size() > 0) {
                for (Contact contact : contacts) {
                    System.out.println("Имя: " + contact.getName() + ", Телефон: " + contact.getPhone());
                }
            } else {
                System.out.println("Пока контактов нет");
            }
        });
    }
}
