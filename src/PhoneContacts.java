import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class PhoneContacts {
    private HashMap<String, ArrayList<Contact>> contacts = new HashMap<>();

    public void addGroup(String title) {
        contacts.put(title, new ArrayList());
    }

    public void addContactToExistingGroup(Contact contact, String groupTitle) {
        ArrayList<Contact> groupContacts = contacts.get(groupTitle);

        int index = 0;
        Iterator<Contact> it = groupContacts.iterator();
        while (it.hasNext()) {
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
    }

    public void addContactToNewGroup(Contact contact, String groupTitle) {
        addGroup(groupTitle);
        contacts.get(groupTitle).add(contact);
    }

    public HashMap<String, ArrayList<Contact>> getContacts() {
        return contacts;
    }
}
