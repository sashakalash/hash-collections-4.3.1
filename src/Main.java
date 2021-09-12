import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

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
            PhoneContacts.addGroup(input);
            System.out.println("Создать еще одну группу (введите название или введите нет)?");
        }
    }

    public static void addContactToGroup() {
        while (true) {
            System.out.println("Создать контакт (введите наименование и его номер или введите нет)?");
            String input = scanner.nextLine();
            if ("нет".equals(input)) {
                break;
            }
            Contact contact = createContact(input);

            System.out.println("Укажите группы контакта через пробел");
            input = scanner.nextLine();
            for (String group : input.split(" ")) {
                PhoneContacts.addContactToGroup(contact, group);
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
        PhoneContacts.getContacts();
    }
}
