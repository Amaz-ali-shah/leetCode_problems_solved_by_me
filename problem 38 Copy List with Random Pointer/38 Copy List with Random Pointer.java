class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        
        Map<Node, Node> map = new HashMap<>();
        
        // First pass: create all copy nodes
        Node curr = head;
        while (curr != null) {
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }
        
        // Second pass: link next and random
        curr = head;
        while (curr != null) {
            Node copy = map.get(curr);
            copy.next = map.get(curr.next);      // map.get(null) returns null
            copy.random = map.get(curr.random);
            curr = curr.next;
        }
        
        return map.get(head);
    }
}
