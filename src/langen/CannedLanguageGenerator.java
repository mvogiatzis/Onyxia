package langen;


import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;


/**
 * A simple canned text language generator. Imported from IJP assignment 
 * 1 to generate language for the ChatBots. Now used only to provide replies
 * in exact words found in human remarks.
 * 
 * @author Michail Vogiatzis
 * @version 1.0 (November 2011)
 */
public class CannedLanguageGenerator implements LanguageGenerator {
    
    
    /**
     * HashMap that is used to contain words associated with canned 
     * text responses for given remarks.
     */
    private HashMap <String , String> cannedReplies;


    /**
     * Creates a TemplateLanguageGenerator:
     * - sets up the sentence templates
     */
    public CannedLanguageGenerator() {
        
        
        cannedReplies = new HashMap <String , String>();
        //Function that fills the HashMap used to answer canned
        //text to given remarks. It associates remarks (first string)
        //with relevant answers (second string).
        fillCannedReplies();
    } 
    
    

    /**
     * Use key word matching to generate a canned response to the specified
     * comment.
     * 
     * @param remark The input which we must respond to
     * 
     * @return A string containing a reply to the specified remark
     */
    private String keyWordMatch(String remark){
        
        //the returned string
        String matchedresult = null;
        
        //get all the keys in cannedReplies and store them in a Set.
        Set<String> mapkeys= cannedReplies.keySet();
        
        Iterator<String> it = mapkeys.iterator();
        
        //find out whether the remark contains one of our keys
        //so if it does, return the relevant reply from HashMap.
        while(it.hasNext())
        {
            String key = it.next();
            if (remark.contains(key))
            {
                matchedresult = cannedReplies.get(key);
                return matchedresult;
            }
        }
       
        //At this point, the remark is not included in our HashMap
        //so let's return "I don't know" message.
        matchedresult = "Ermm.. What? What are you talking about?";
        return matchedresult;
        
        
    }
    
    /**
     * Public wrapper of keywordmatch. It will return something that makes sense
     * only if the input contains one of the words 
     * in the hashmap.
     */
    public String testkeyWordMatch(String testremark)
    {
            return keyWordMatch(testremark);
    }
    
    /**
     * Function that fills the Hashmap. It associates the remark (first string)
     * with a relevant reply (second string), so that the reply to the input makes
     * sense.
     */
    private void fillCannedReplies()
    {
        String onyxiaReply = "Onyxia is a powerful dragon that lives in a cave. Not only " +
        		"magical abilities are needed to kill her, but also something to protect you" +
        		" from her powerful fire spells... I could give you a fire protection chest if you" +
        		" want !";
        cannedReplies.put("onyxia",onyxiaReply);
        cannedReplies.put("dragon", onyxiaReply);
        cannedReplies.put("ring","I've heard that the ring is somewhere in a cave, guarded by a "
                + "mighty dragon called \"Onyxia\".");
        
        cannedReplies.put("cave", "Hmm.. Go to the entrance and you will find your way...");
        
        cannedReplies.put("help", "What do you want to know..?");
        
        String thanksReply = "You're welcome !";
        cannedReplies.put("thanks", thanksReply);
        cannedReplies.put("thank", thanksReply);
        
        String helloreply = "Hi there, I'm the genie and I can help you !";
        cannedReplies.put("hello", helloreply);
        cannedReplies.put("hi", helloreply);
        
        String fireProtectionReply = "Here is your fire protection chest! Take care!";
        cannedReplies.put("fire", fireProtectionReply);
        cannedReplies.put("protection", fireProtectionReply);
        cannedReplies.put("chest", fireProtectionReply);
        
        
    }

    

    /**
     * Generate a reply to the specified remark. The reply is related to the 
     * input remark in the vague sense that it contains a word relating to one 
     * of the words in the input
     * 
     * @param remark The remark which must be responded to
     * 
     * @return A string containing a response to the specified remark
     */
    @Override
    public String generateReply(String remark) {
        
        return keyWordMatch(remark);
    }
    
    

    
    
    
    
}  
