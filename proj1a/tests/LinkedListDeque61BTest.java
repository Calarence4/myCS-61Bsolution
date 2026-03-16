import edu.princeton.cs.algs4.In;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {
    
    @Test
    /** In this test, we have three different assert statements that verify that addFirst works correctly. */ public void addFirstTestBasic() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        
        lld1.addFirst("back"); // after this call we expect: ["back"]
        assertThat(lld1.toList()).containsExactly("back").inOrder();
        
        lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
        assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();
        
        lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
    }
    
    @Test
    /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
     *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */ public void addLastTestBasic() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        
        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
    }
    
    @Test
    /** This test performs interspersed addFirst and addLast calls. */ public void addFirstAndAddLastTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]
        
        assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
    }
    
    // Below, you'll write your own tests for LinkedListDeque61B.
    @Test
    public void isEmptyTest() {
        Deque61B<Integer> input = new LinkedListDeque61B<>();
        assertThat(input.isEmpty()).isTrue();
        input.addLast(0);   // [0]
        input.addLast(1);   // [0, 1]
        input.addFirst(-1); // [-1, 0, 1]
        input.addLast(2);   // [-1, 0, 1, 2]
        input.addFirst(-2); // [-2, -1, 0, 1, 2]
        
        assertThat(input.isEmpty()).isFalse();
    }
    
    @Test
    public void sizeTest() {
        Deque61B<Integer> input = new LinkedListDeque61B<>();
        assertThat(input.size() == 0).isTrue();
        input.addFirst(0);
        assertThat(input.size() == 1).isTrue();
        input.addLast(1);
        assertThat(input.size() == 2).isTrue();
        input.addFirst(-1);
        assertThat(input.size() == 3).isTrue();
    }
    
    @Test
    public void getTest() {
        Deque61B<Integer> input = new LinkedListDeque61B<>();
        assertThat(input.get(-1)).isNull();
        assertThat(input.get(1)).isNull();
        assertThat(input.get(0)).isNull();
        input.addLast(0);   // [0]
        assertThat(input.get(0)).isEqualTo(0);
        input.addLast(1);   // [0, 1]
        assertThat(input.get(1)).isEqualTo(1);
        input.addFirst(-1); // [-1, 0, 1]
        assertThat(input.get(0)).isEqualTo(-1);
        input.addLast(2);   // [-1, 0, 1, 2]
        assertThat(input.get(0)).isEqualTo(-1);
        input.addFirst(-2); // [-2, -1, 0, 1, 2]
        assertThat(input.get(0)).isEqualTo(-2);
        
        assertThat(input.get(54654)).isNull();
        assertThat(input.get(-4874)).isNull();
        
    }
    
    @Test
    public void getRecursiveTest() {
        Deque61B<Integer> input = new LinkedListDeque61B<>();
        
        /*空链表测试*/
        assertThat(input.getRecursive(-1)).isNull();
        assertThat(input.getRecursive(1)).isNull();
        assertThat(input.getRecursive(0)).isNull();
        
        
        input.addLast(0);   // [0]
        assertThat(input.getRecursive(0)).isEqualTo(0);
        input.addLast(1);   // [0, 1]
        assertThat(input.getRecursive(1)).isEqualTo(1);
        input.addFirst(-1); // [-1, 0, 1]
        assertThat(input.getRecursive(0)).isEqualTo(-1);
        input.addLast(2);   // [-1, 0, 1, 2]
        assertThat(input.getRecursive(0)).isEqualTo(-1);
        input.addFirst(-2); // [-2, -1, 0, 1, 2]
        assertThat(input.getRecursive(0)).isEqualTo(-2);
        
        assertThat(input.getRecursive(54654)).isNull();
        assertThat(input.getRecursive(-4874)).isNull();
        
    }
    
    @Test
    public void removeFirstTest() {
        Deque61B<Integer> input = new LinkedListDeque61B<>();
        
        //空链表
        assertThat(input.removeFirst()).isEqualTo(null);
        
        input.addFirst(8);      // [8]
        input.addLast(13);      // [8, 13]
        input.addFirst(3);      // [3, 8, 13]
        input.addLast(21);      // [3, 8, 13, 21]
        input.addFirst(1);      // [1, 3, 8, 13, 21]
        
        
        assertThat(input.removeFirst()).isEqualTo(1);    // [3, 8, 13, 21]
        assertThat(input.size()).isEqualTo(4);
        assertThat(input.removeFirst()).isEqualTo(3);    // [8, 13, 21]
        assertThat(input.size()).isEqualTo(3);
        assertThat(input.removeFirst()).isEqualTo(8);    // [13, 21]
        assertThat(input.size()).isEqualTo(2);
        assertThat(input.removeFirst()).isEqualTo(13);   // [21]
        assertThat(input.size()).isEqualTo(1);
        assertThat(input.removeFirst()).isEqualTo(21);   // []
        assertThat(input.size()).isEqualTo(0);
        assertThat(input.removeFirst()).isEqualTo(null); // []
        assertThat(input.size()).isEqualTo(0);
    }
    
    @Test
    public void removeLastTest() {
        Deque61B<Integer> input = new LinkedListDeque61B<>();
        
        //空链表
        assertThat(input.removeLast()).isEqualTo(null);
        
        input.addFirst(8);      // [8]
        input.addLast(13);      // [8, 13]
        input.addFirst(3);      // [3, 8, 13]
        input.addLast(21);      // [3, 8, 13, 21]
        input.addFirst(1);      // [1, 3, 8, 13, 21]
        
        
        assertThat(input.removeLast()).isEqualTo(21);    // [1, 3, 8, 13]
        assertThat(input.size()).isEqualTo(4);
        assertThat(input.removeLast()).isEqualTo(13);    // [1, 3, 8]
        assertThat(input.size()).isEqualTo(3);
        assertThat(input.removeLast()).isEqualTo(8);     // [1, 3]
        assertThat(input.size()).isEqualTo(2);
        assertThat(input.removeLast()).isEqualTo(3);     // [1]
        assertThat(input.size()).isEqualTo(1);
        assertThat(input.removeLast()).isEqualTo(1);     // []
        assertThat(input.size()).isEqualTo(0);
        assertThat(input.removeLast()).isEqualTo(null);  // []
        assertThat(input.size()).isEqualTo(0);
    }
}