import java.util.List;

public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    static ListNode merge(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val){
                cur.next = list1;
                cur = cur.next;
                list1 = list1.next;
            } else {
                cur.next = list2;
                cur = cur.next;
                list2 = list2.next;
            }
        }

        cur.next = list1 != null ? list1 : list2;

        return dummy.next;
    }

    static ListNode removeOdd(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        ListNode cur = head;

        while (cur != null){
            if (cur.val % 2 == 1) {
                prev.next = cur.next;
            } else {
                prev = prev.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    static void loopList(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(0);
        ListNode prev = head1;
        for (int i = 2; i <= 6; i += 2){
          ListNode node = new ListNode(i);
          prev.next = node;
          prev = node;
        }

        ListNode head2 = new ListNode(1);
        prev = head2;
        for (int i = 3; i <= 6; i += 2){
            ListNode node = new ListNode(i);
            prev.next = node;
            prev = node;
        }

        ListNode merged = ListNode.merge(head1, head2);
//        ListNode.loopList(merged);

        ListNode allEvenHead = ListNode.removeOdd(merged);
        ListNode.loopList(allEvenHead);
    }


}
