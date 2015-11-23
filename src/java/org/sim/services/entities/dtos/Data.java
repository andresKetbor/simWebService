package org.sim.services.entities.dtos;
// Generated Aug 22, 2015 1:16:11 PM by Hibernate Tools 4.3.1

import java.util.List;



public class Data {


     
     private String title;
     private String message;
          

    public Data(String title, String message) {
        
        this.title=title;
        this.message= message;
    }


    public String getTitle() {
        return title;
    }

    /**
     * @param ttitle the ttitle to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
 
}
