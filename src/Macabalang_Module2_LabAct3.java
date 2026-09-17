import java.util.Scanner;

public class Macabalang_Module2_LabAct3 {

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

    static int[] readIntArray(Scanner sc, String label) {
        System.out.print("Enter " + label + ": ");
        String line = sc.nextLine().trim();
        if (line.isEmpty()) {
            return new int[0];
        }
        String[] parts = line.split("\\s+");
        int[] arr = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }
        return arr;
    }

    static ListNode buildList(int[] values) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int v : values) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        return dummy.next;
    }

    static ListNode getNodeAtSkip(ListNode head, int skip) {
        ListNode node = head;
        for (int i = 0; i < skip && node != null; i++) {
            node = node.next;
        }
        return node;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Solution solution = new Solution();

        System.out.print("Enter intersectVal: ");
        int intersectVal = Integer.parseInt(sc.nextLine().trim());

        int[] aValues = readIntArray(sc, "listA");
        int[] bValues = readIntArray(sc, "listB");

        System.out.print("Enter skipA: ");
        int skipA = Integer.parseInt(sc.nextLine().trim());

        System.out.print("Enter skipB: ");
        int skipB = Integer.parseInt(sc.nextLine().trim());

        ListNode headA = buildList(aValues);
        ListNode headB = buildList(bValues);

        if (intersectVal != 0) {
            if (skipA >= aValues.length || bValues.length == 0 || skipB >= bValues.length
                    || aValues[skipA] != intersectVal || bValues[skipB] != intersectVal) {
                System.out.println("Warning: intersectVal does not match. Check your input.");
            } else {
                ListNode intersectNode = getNodeAtSkip(headA, skipA);
                ListNode bNodeBeforeIntersect = getNodeAtSkip(headB, skipB - 1);

                if (skipB == 0) {
                    headB = intersectNode;
                } else if (bNodeBeforeIntersect != null) {
                    bNodeBeforeIntersect.next = intersectNode;
                }
            }
        }

        ListNode result = solution.getIntersectionNode(headA, headB);

        if (result == null) {
            System.out.println("No intersection");
        } else {
            System.out.println("Intersected at '" + result.val + "'");
        }

        sc.close();
    }
}

