public class Main {
    public static void main(String[] args) {
        Session s1 = new Session(103, "Resume Building", "Alice Chen", "HR",
                "2026-10-01", "10:00 AM", "Room 101", 5);
        Session s2 = new Session(101, "Public Speaking", "Ben Torres", "Marketing",
                "2026-10-02", "1:00 PM", "Room 202", 3);
        Session s3 = new Session(105, "Career Planning", "Alice Chen", "HR",
                "2026-10-03", "9:00 AM", "Room 101", 2);
        Session s4 = new Session(104, "Leadership Basics", "Dana Kim", "Operations",
                "2026-10-04", "3:00 PM", "Room 303", 4);

        MyLinkedList list = new MyLinkedList(s1, null);
        list = list.addFirst(s2);
        list = list.addLast(s3);
        list = list.insertAfter(s4);

        list.display();

        System.out.println(list.searchByID(104));
        System.out.println(list.searchByID(999));

        System.out.println(list.searchByMentor("Alice Chen"));
        System.out.println(list.searchByMentor("Nobody"));

        System.out.println(list.registerParticipant(s2));

        System.out.println(list.searchByID(101));

        list.registerParticipant(s3);
        list.registerParticipant(s3);
        System.out.println(list.registerParticipant(s3)); // should be false, session full
        System.out.println(list.searchByID(105));

        System.out.println(list.remove(s4));
        list.display();

        System.out.println(list.remove(s2));
        list.display();

        System.out.println(list.remove(s4));
        list.display();
    }
}
