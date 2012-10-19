package langen;

/**
 * Used to automatically generate sentences. It is used for IJP assignment 1
 * to generate 
 * language spoken by ChatBots.
 * 
 * @author Michail Vogiatzis
 * @version 1.0 (November 2011)
 */

public interface LanguageGenerator {

    /**
     * Generate a response to the specified comment
     * 
     * @param remark The comment to reply to
     * 
     * @return A string containing a reply relating to the specified remark
     */
    public String generateReply(String remark);
}
