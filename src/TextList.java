/**
 * Created by Aviad on 13/06/2015.
 * This class represents list of WordNodes
 */
import java.util.Stack;

public class TextList
{
    private WordNode _head;

    /**
     * Empty constructor to initialize the list
     */
    public TextList ()
    {
        _head = null;
    }


    /**
     * Constructor to initialize the list with given text.
     * Then calling to mergeSort function to sort the list.
     * If the string is null or empty, return empty string.
     * Complexity o(n * log n)
     * @param text string the represents the text.
     */
    public TextList (String text)
    {
        if (text == "" || text.equals("") || text.isEmpty())
        {
            _head = new WordNode(text);
        }
        else
        {
            String currentWord = "";
            for (int i = 0, j=0; i < text.length(); i++)
            {
                while (text.charAt(j) != ' ' && j < text.length() )
                {
                   currentWord += text.charAt(j);
                   if (j < text.length() -1)
                   j++;
                    if (j == text.length() -1)
                    {
                        currentWord += text.charAt(j);
                        addToData(currentWord);
                        break;
                    }
                }
                if (j < text.length() -1 )
                {
                    j++;
                    addToData(currentWord);
                    currentWord = "";
                }
                if (j == text.length() -1) break;
            }
        }
        _head = WordNode.mergeSort(_head);
    }


    /**
     * Public method to add a word to the list, without loosing the alphabeitical order.
     * If the string is empty, do not make any changes.
     * Complexity o(n) (Worst case).
     * @param word string that represents the text that should be added to list.
     */
    public void addToData (String word)
    {
        WordNode wordNodeList = null;
        if (!word.isEmpty())
        {
            if (_head == null)
                wordNodeList = new WordNode(word);
            else
            {
                // Calling private method for inserting the wordNode in correct place,
                // keeping the list sorted.
                wordNodeList = _head.insertNewNode(word);
            }
            _head = wordNodeList;
        }
    }


    /**
     * Public method to count the amount of total words on the list,
     * including those who appear more than once.
     * Complexity o(n).
     * @return number of total words on the list.
     */
    public int howManyWords ()
    {
        int wordCount = 0;
        WordNode currentWordNode = _head;
        while (currentWordNode != null)
        {
            wordCount+= currentWordNode.getNumOfAppearances();
            currentWordNode = currentWordNode.getNext();
        }
        return wordCount;
    }


    /**
     * Public method to count the amount of different words on the list,
     * Complexity o(n).
     * @return number of different words on the list.
     */
    public int howManyDifferentWords ()
    {
        int wordCount = 0;
        WordNode currentWordNode = _head;
        while (currentWordNode != null)
        {
            wordCount++;
            currentWordNode = currentWordNode.getNext();
        }
        return wordCount;
    }

    /**
     * Public method to return most frequent word on the text.
     * If the list is empty, return empty string.
     * If more than one word found - return the first.
     * Complexity o(n) (Worst case).
     * @return most frequent word on the text.
     */
    public String mostFrequentWord ()
    {
        if (_head != null)
        {
            WordNode currentNode = _head;
            WordNode nextNode = _head.getNext();
            WordNode mostFreqWordNode = currentNode;
            while (nextNode != null)
            {
                if (currentNode.getNumOfAppearances() > nextNode.getNumOfAppearances())
                    mostFreqWordNode = currentNode;
                else if (currentNode.getNumOfAppearances() < nextNode.getNumOfAppearances())
                    mostFreqWordNode = nextNode;
                else
                if (currentNode.getWord().compareTo((nextNode.getWord())) < 0)
                {
                    mostFreqWordNode = nextNode;
                }
                currentNode = currentNode.getNext();
                nextNode = nextNode.getNext();

            }
            return mostFreqWordNode.getWord();
        }
        return "";
    }

    /**
     * Public method to count how many words starting with the given letter.
     * If more than one word found - return the first.
     * Complexity o(n) (Worst case).
     * @param letter The letter to look for in words first letter.
     * @return how many words starting with the given letter.
     */
    public int howManyStarting (char letter)
    {
        WordNode currentNode = _head;
        int wordCount = 0;
        // Since the nodes are sorted:
        // It will run only for words that their first char is smaller than letter.
        // Once letter is equal or higher, no need to keep moving.
        while (currentNode != null && currentNode.getWord().charAt(0) <= letter)
        {
            if (currentNode.getWord().charAt(0) == letter)
            {
                wordCount = currentNode.getNumOfAppearances();
                currentNode = currentNode.getNext();
            }
            else
                currentNode = currentNode.getNext();
        }
        return wordCount;
    }

    /**
     * Public method to most frequent starting letter on the text.
     * @return most frequent starting letter on the text.
     * The best complexity that I could think of is o (n).
     *
     */
    public char mostFrequentStartingLetter()
    {
        TextList textList = new TextList();
        return mostFrequentStartingLetter(_head, textList);
    }
    public char mostFrequentStartingLetter(WordNode currentNode, TextList textList)
    {
        if (currentNode != null)
        {
            int num = currentNode.getNumOfAppearances();
            //WordNode Word = new WordNode(currentNode.getWord().substring(0, 1));
            if (num > 1)
            {
                currentNode.setNumOfAppearances(num);
            }
            textList.addToData(currentNode.getWord().substring(0, 1));

            return mostFrequentStartingLetter(currentNode.getNext(), textList);
        }
        else
        {
            //o (n)
            return textList.mostFrequentWord().charAt(0);
        }
    }

    /**
     * Refer to Java documentation for learning more on this method.
     */
    public String toString()
    {
        if (_head != null)
            return _head.toString();
        else
            return null;
    }




}

