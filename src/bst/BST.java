package bst;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BST {

    private TreeNode root;
    
    public BST()
    {
        ArrayList<String> dictionary = new ArrayList<String>();
        File inputFile = new File("word_list_moby_crossword.flat.txt");
        try
        {
            Scanner input = new Scanner(inputFile);
            int i = 0;
            while (input.hasNext())
            {
                dictionary.add(input.next());
                i++;
                if (i%1000 == 0)
                {
                    System.out.println("Loaded "+i+" words.");
                }
            }
            input.close();
        }
        catch (FileNotFoundException fnfe)
        {
            System.out.println("File not found.");
        }
        
        Scanner keyboard = new Scanner(System.in);
        
        System.out.print("How many words should I add to the tree? ");
        int numWords = keyboard.nextInt();
        
        for (int i=0; i<numWords; i++)
        {
            int which = (int)(Math.random()*dictionary.size());
            System.out.println(dictionary.get(which));
            add(dictionary.get(which));
        }
    
        System.out.println("------------------\n"+this.toString());
        System.out.println("The reverse order is:");
        backwards(root);
        counter(root);
        System.out.println("");
        System.out.println("There are " + i + " letters");
        System.out.print("Search a word ");
        keyboard.nextLine(); // clears the carriager return from the integer input earlier.
        String searchword = keyboard.nextLine();
        System.out.println(search(root, searchword));
        depth(root);
        System.out.println("The depth is " + j + " words");
        
    }
    
    public String toString()
    {
        
        return subString("",root);
    }
        
    public String subString(String prefix, TreeNode subroot)
    {
        if (subroot == null)
            return "";
        else 
        {
            String result = "";
            result+= subString(prefix+"\t",subroot.getLeft());
            result+= prefix+subroot.getValue()+"\n";
            result+= subString(prefix+"\t",subroot.getRight());
            return result; 
        }
    }
    public void add(String s)
    {
        if (root == null)
            root = new TreeNode(s);
        else
            addToSubTree(s,root);
    }
    
    public void addToSubTree(String s, TreeNode subroot)
    {
        int rel = s.compareTo(subroot.getValue());
        if (rel<0)
        {
            if (subroot.getLeft() == null)
                subroot.setLeft(new TreeNode(s));
            else
                addToSubTree(s,subroot.getLeft());
        }
        else
        {
            if (subroot.getRight() == null)
                subroot.setRight(new TreeNode(s));
            else
                addToSubTree(s,subroot.getRight());
        }
    }
    
    public void backwards(TreeNode subroot)
    {
    	if (subroot.getRight() != null)
    	{
    		backwards(subroot.getRight());
    	}
    	System.out.println(subroot.getValue());
    	if (subroot.getLeft() != null)
    	{
    		backwards(subroot.getLeft());
    	}
    }
    int i = 0;
    public int counter(TreeNode subroot)
    {
    	if (subroot.getRight() != null)
    	{
    		counter(subroot.getRight());
    	}
    	i = i + (subroot.getValue().length());
    	if (subroot.getLeft() != null)
    	{
    		counter(subroot.getLeft());
    	}
    	return i;
    }

    public boolean search(TreeNode subroot, String searchword) //add searchword to this
    {
    	if (searchword.equals(subroot.getValue()))
    	{
    		return true;
    	}
    	if (subroot.getRight() != null)
    	{
    		if (search(subroot.getRight(), searchword) == true)
    		{
    			return true;
    		}
    	}
    	if (subroot.getLeft() != null)
    	{
    		if (search(subroot.getLeft(), searchword) == true)
    		{
    			return true;
    		}
    	}
    	return false;
    }
    
    int j = 0;
    public int depth(TreeNode subroot)
    {
    	if (subroot.getRight() != null)
    	{
    		depth(subroot.getRight());
    	}
    	j = j + 1;
    	if (subroot.getLeft() != null)
    	{
    		depth(subroot.getLeft());
    	}
    	if (subroot.getLeft() != null && subroot.getRight() != null)
    	{
    		j = j -1;
    	}
    	return j;
    }
    
    
    
    
}
