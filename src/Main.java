import java.util.Scanner;

public class Main {

    // Definition for singly-linked list
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    static class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) {
                return null;
            }

            ListNode ptrOne = headA;
            ListNode ptrTwo = headB;

            while (ptrOne != ptrTwo) {
                ptrOne = (ptrOne == null) ? headB : ptrOne.next;
                ptrTwo = (ptrTwo == null) ? headA : ptrTwo.next;
            }

            return ptrOne;
        }
    }

    // Builds listA and listB so they share the SAME tail nodes starting at the shared part
    static ListNode[] buildIntersectingLists(int[] aOnly, int[] bOnly, int[] shared) {
        ListNode sharedDummy = new ListNode(0);
        ListNode tail = sharedDummy;
        for (int v : shared) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        ListNode sharedHead = sharedDummy.next;

        ListNode aDummy = new ListNode(0);
        ListNode aTail = aDummy;
        for (int v : aOnly) {
            aTail.next = new ListNode(v);
            aTail = aTail.next;
        }
        aTail.next = sharedHead;

        ListNode bDummy = new ListNode(0);
        ListNode bTail = bDummy;
        for (int v : bOnly) {
            bTail.next = new ListNode(v);
            bTail = bTail.next;
        }
        bTail.next = sharedHead;

        return new ListNode[] { aDummy.next, bDummy.next };
    }

    static int[] readArray(Scanner sc, String label) {
        System.out.print("Enter number of elements in " + label + ": ");
        int n = Integer.parseInt(sc.nextLine().trim());

        int[] arr = new int[n];
        if (n > 0) {
            System.out.println("Enter " + n + " integers for " + label + " (space-separated):");
            String[] parts = sc.nextLine().trim().split("\\s+");
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(parts[i]);
            }
        } else {
            // consume the blank line for an empty array so input stays aligned
            System.out.println("(0 elements, skipping input line)");
        }
        return arr;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Solution solution = new Solution();

        System.out.println("List A");
        int[] aOnly = readArray(sc, "listA-only prefix");

        System.out.println("List B");
        int[] bOnly = readArray(sc, "listB-only prefix");

        System.out.println("Build the shared tail");
        int[] shared = readArray(sc, "shared tail");

        ListNode[] lists = buildIntersectingLists(aOnly, bOnly, shared);
        ListNode result = solution.getIntersectionNode(lists[0], lists[1]);

        if (result == null) {
            System.out.println("No intersection");
        } else {
            System.out.println("Intersected at '" + result.val + "'");
        }

        sc.close();
    }
}

