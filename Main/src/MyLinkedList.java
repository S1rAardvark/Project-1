public class MyLinkedList {
    private Session data;
    private MyLinkedList next;

    // Returns the session stored in this node.
    public Session data(){
        return this.data;
    }

    // Returns the next node in the list (null if this is the last node).
    public MyLinkedList next(){
        return this.next;
    }

    // Creates a node holding the given session and pointing to the given next node.
    public MyLinkedList(Session data, MyLinkedList next){
        this.data = data;
        this.next = next;
    }

    // Adds a session to the front of the list and returns the new head.
    public MyLinkedList addFirst(Session value){
        if(this.data==null){
            this.data = value;
            return this;
        }
        return new MyLinkedList(value, this);
    }

    // Adds a session to the end of the list and returns the head.
    public MyLinkedList addLast(Session value){
        MyLinkedList current = this;
        if(this.data==null){
            this.data=value;
            return this;
        }
        while(current.next != null){
            current = current.next;
        }
        current.next = new MyLinkedList(value, null);
        return this;
    }

    // Inserts a session after the target session; adds it to the end if the target isn't found.
    public MyLinkedList insertAfter(Session session){
        if(session.getSessionID() < this.data.getSessionID())
            return new MyLinkedList(session, this);
        else if (this.next == null || session.getSessionID() < this.next.data.getSessionID())
            return (this.next = new MyLinkedList(session, this.next));
        return (this.next = this.next.insertAfter(session));
    }

    // Returns the details of the session with the given ID, or a "not found" message.
    public String searchByID(int id){
        MyLinkedList current = this;
        while(current != null) {
            if(this.data == null){
                return "Not found";
            }
            if(current.data.getSessionID() == id){
                return current.data.toString();
            }
            current = current.next;
        }
        return "Not found";

    }

    // Returns the details of the first session run by the given mentor, or a "not found" message.
    public String searchByMentor(String mentor){
        MyLinkedList current = this;
        while(current != null) {
            if(this.data == null){
                return "Not found";
            }
            if(current.data.getMentor().equals(mentor)){
                return current.data.toString();
            }
            current = current.next;
        }
        return "Not found";
    }

    //Removes the session with a matching ID and reports whether it was removed.
    public MyLinkedList remove(Session value){
        MyLinkedList current = this;
        if(this.data==null){
            return this;
        }
        if(this.data.getSessionID() == value.getSessionID()){
            if(this.next == null){
                this.data = null;
            } else {
                this.data = this.next.data;
                this.next = this.next.next;
            }
            return this;
        }
        while(current.next != null) {
            if(current.next.data.getSessionID() == value.getSessionID()) {
                current.next = current.next.next;
                return this;
            }
            current = current.next;
        }
        return this;
    }

    // Adds one participant to the matching session if it isn't full; returns true if it succeeded.
    public boolean registerParticipant(Session value){
        if (value.getCurrentParticipants() < value.getMaxParticipants()) {
            value.setCurrentParticipants(value.getCurrentParticipants() + 1);
            return true;
        } else {
            return false;
        }
    }

    // Prints every session in the list, or a message if the list is empty.
    public void display(){
        MyLinkedList current = this;
        if(current.data == null){
            System.out.println("The list is empty!");
            return;
        }
        while(current != null){
            System.out.println(current.data);
            current = current.next;
        }
    }


    // Helper Methods

    public MyLinkedList add(Session s) {
        if(this.data == null || this.data().getSessionID() > s.getSessionID()) return this.addFirst(s);
        else if(this.getLast().data().getSessionID() < s.getSessionID()) return this.addLast(s);
        else return this.insertAfter(s);
    }

    // Walks to the end of the list and returns the last node.
    public MyLinkedList getLast() {
        MyLinkedList current = this;
        while(current.next != null) current = current.next;
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

    @Override
    public String toString() {
        return this.data.toString() + (this.next == null ? "" : (", " + this.next.toString()));
    }
}
