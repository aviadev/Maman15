/**
 * Created by Aviad on 13/06/2015.
 */
public class WordNode
{
    private String _word;
    private WordNode _next;
    private int _numOfAppearances;       //How many times do this word appears?

    /**
     * Protected method to count the amount of appearances for each word.
     * Complexity o(1).
     * @return number of total words on the list.
     */
    protected int getNumOfAppearances()
    {
        return _numOfAppearances;
    }

    public int setNumOfAppearances(int num)
    {
        _numOfAppearances = num;
        return _numOfAppearances;
    }

    /**
     * Empty constructor.
     */
    protected WordNode ()
    {
    }


    /**
     * Constructor to initialize word node with given word string.
     * If the string is null or empty, ignore it.
     * @param word string the represents the word.
     */
    protected WordNode(String word)
    {
        if (!word.isEmpty())
        _word = word;
        _numOfAppearances = 1;
    }

    /**
     * Constructor to initialize word node with given word string, and pointer to next WordNode.
     * It also initialize the number of appearances property.
     * If the string is null or empty, ignore it.
     * @param word string the represents the word.
     * @param next Represents the next node on the list.
     */
    protected WordNode (String word, WordNode next)
    {
        _word = word;
        _next = next;
        _numOfAppearances = 1;
    }

 /*   protected WordNode(WordNode current, WordNode next)
    {
        _word = current._word;
        _numOfAppearances = current._numOfAppearances;
        _next = next;
    }*/

    /**
     * Protected method to return the string of the current word.
     * Complexity o(1).
     * @return the string of the current word.
     */
    protected String getWord ()
    {
        return _word;
    }

   /* protected void setWord (String word)
    {
        _word = word;
    }*/

    /**
     * Protected method to return the next WordNode after the current one.
     * Complexity o(1).
     * @return the next WordNode after the current one.
     */
    protected WordNode getNext ()
    {
        return _next ;
    }

    /**
     * Protected method to set the next WordNode after the current one.
     * Complexity o(1).
     */
    protected void setNext (WordNode next)
    {
        _next = next;
    }

    protected static WordNode mergeSort(WordNode head)
    {
        if(head == null || head.getNext() == null)
            return head;
        WordNode middle = getMiddle(head);
        WordNode sHalf = middle.getNext();
        middle.setNext(null);
            return merge(mergeSort(head),mergeSort(sHalf));
    }


    protected static WordNode merge(WordNode a, WordNode b)
    {
        WordNode dummyHead, curr;
        dummyHead = new WordNode();
        curr = dummyHead;
        while(a !=null && b!= null)
        {
            if(a.getWord().charAt(0) < b.getWord().charAt(0))
            {
                curr.setNext(a);
                a = a.getNext();
            }
            else
            {
                curr.setNext(b);
                b = b.getNext();
            }
            curr = curr.getNext();
        }
        curr.setNext(a == null? b: a);
        return dummyHead.getNext();
    }


    protected static WordNode getMiddle(WordNode head)
    {
        if(head == null)
            return head;
        WordNode slow, fast;
        slow = fast = head;
        while(fast.getNext() != null && fast.getNext().getNext() != null)
        {
            slow = slow.getNext(); fast = fast.getNext().getNext();
        }
        return slow;
    }

    /**
     * Protected method to return the result of comparison between 2 words.
     * Complexity o(1).
     * @param word Represents word to be inserted to the list.
     * @return the wordNode in it's proper location.
     */
    protected WordNode insertNewNode(String word)
    {
        int compareResult = compareWords(word);
        if (compareResult == 0)     //Words are equal, can increase this on 1.
        {
            _numOfAppearances++;
            return this;
        }
        else if (compareResult < 0)
        {
            if (_next == null)
            {
                _next = new WordNode(word, null);
                //_numOfAppearances = numOfAppearances;
                return this;
            }
            else
            {
                _next = _next.insertNewNode(word);
                //_numOfAppearances = numOfAppearances;
                return this;
            }
        }
        else
            return new WordNode(word, this);
        }

    /**
     * Protected method to return the result of comparison between 2 words.
     * Complexity o(1).
     * @param word Represents word to be compared with.
     * @return the results after comparison.
     */
    protected int compareWords(String word)
    {
        int compareResult = _word.compareTo(word);
        return compareResult;
    }

    /**
     * Refer to Java documentation for learning more on this method.
     */
    public String toString()
    {
        String longString = _word + "\t" + _numOfAppearances + "\n";
        if (_next == null)
            return longString;
        else
            return longString + _next.toString();

    }


}
