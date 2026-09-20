import java.util.Objects;

public class MyLinkedList {
    private Session data;
    private MyLinkedList next;

    public MyLinkedList(Session data, MyLinkedList next) {
        this.data = data;
        this.next = next;
    }

    public Session data() {
        return this.data;
    }

    public MyLinkedList next() {
        return this.next;
    }

    public MyLinkedList addFirst(Session session) {
        return new MyLinkedList(session, this);
    }

    public MyLinkedList addLast(Session session) {
        if (this.next == null) return (this.next = new MyLinkedList(session, null));
        return this.next.addLast(session);
    }

    public MyLinkedList insertAfter(Session session) {
        if(session.getSessionID() < this.data.getSessionID())
            return new MyLinkedList(session, this);
        else if (this.next == null || session.getSessionID() < this.next.data.getSessionID())
            return (this.next = new MyLinkedList(session, this));
        return (this.next = this.next.insertAfter(session));
    }

    public String searchByID(int session) {
        Session s = this.getByID(session);
        if (s == null) return "Not Found";
        return s.toString();
    }

    public String searchByMentor(String mentor) {
        Session s = this.getByMentor(mentor);
        if(s == null) return "Not Found";
        return s.toString();
    }

    public MyLinkedList remove(Session session) {
        if(this.next == null) return this;
        if(this.next.data.equals(session))
            this.next = this.next.next;
        else this.next.remove(session);
        return this;
    }

    public boolean registerParticipant(Session session) {
        if(session.getCurrentParticipants() == session.getMaxParticipants()) return false;
        session.setCurrentParticipants(session.getCurrentParticipants()+1);
        return true;
    }

    public void display() {
        IO.print(this.data);
        if(this.next != null) this.next.display();
    }

    // Helper Methods

    public MyLinkedList add(Session s) {
        if(this.data().getSessionID() > s.getSessionID()) return this.addFirst(s);
        else if(this.getLast().data().getSessionID() < s.getSessionID()) return this.addLast(s);
        else return this.insertAfter(s);
    }

    public MyLinkedList getLast() {
        MyLinkedList current = this;
        while (current.next != null) current = current.next;
        return current;
    }

    public Session getByID(int session) {
        if(this.data.getSessionID() == session) return this.data;
        if(this.next == null) return null;
        return this.next.getByID(session);
    }

    public Session getByMentor(String mentor) {
        if(this.data.getMentor().equals(mentor)) return this.data;
        if(this.next == null) return null;
        return this.next.getByMentor(mentor);
    }
}
