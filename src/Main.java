import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static PhoneContacts phoneContacts = new PhoneContacts();

    public static void main(String[] args) {
        System.out.println("Введите название группы контактов:");
        createGroups();
        addContactToGroup();
        showContacts();
    }

    public static void createGroups() {
        while (true) {
            String input = scanner.nextLine();
            if ("нет".equals(input)) {
                break;
            }
            if (phoneContacts.getContacts().containsKey(input)) {
                System.out.println("Группа с таким названием уже существует! Задайте другое название");
                return;
            }
            phoneContacts.addGroup(input);
            System.out.println("Создать еще одну группу (введите название или введите нет)?");
        }
    }

    public static void addContactToGroup() {
        while (true) {
            HashMap<String, ArrayList<Contact>> contacts =  phoneContacts.getContacts();

            System.out.println("Создать контакт (введите наименование и его номер или введите нет)?");
            String input = scanner.nextLine();
            if ("нет".equals(input)) {
                break;
            }
            Contact contact = createContact(input);

            System.out.println("Укажите группы контакта через пробел");
            input = scanner.nextLine();
            for (String groupTitle : input.split(" ")) {
                if (contacts.containsKey(groupTitle)) {
                    phoneContacts.addContactToExistingGroup(contact,groupTitle);
                    System.out.println(contact.getName() + " успешно добавлен в группу '" + groupTitle + "'");
                } else {
                    phoneContacts.addContactToNewGroup(contact,groupTitle);
                    System.out.println("Группы " + groupTitle + " не существует! Создана новая группа. Контакт добавлен в новую группу");
                }
            }
        }

    }

    public static Contact createContact(String data) {
        String name = data.split(" ")[0];
        String phone = data.split(" ")[1];
        return new Contact(name, phone);
    }

    public static void showContacts() {
        System.out.println("Группы в справочнике:");
        phoneContacts.getContacts().entrySet().forEach(entry -> {
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
