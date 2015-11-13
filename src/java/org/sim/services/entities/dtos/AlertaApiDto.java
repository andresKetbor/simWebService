package org.sim.services.entities.dtos;
// Generated Aug 22, 2015 1:16:11 PM by Hibernate Tools 4.3.1

import java.util.Date;



public class AlertaApiDto extends CommonDto implements java.io.Serializable {


     private Integer badge;
     private String title;
     private String message;
     private String registrationIdsToSend;

    public AlertaApiDto() {
    }

    /**
     * @return the badge
     */
    public Integer getBadge() {
        return badge;
    }

    /**
     * @param badge the badge to set
     */
    public void setBadge(Integer badge) {
        this.badge = badge;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
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

    /**
     * @return the registrationIdsToSend
     */
    public String getRegistrationIdsToSend() {
        return registrationIdsToSend;
    }

    /**
     * @param registrationIdsToSend the registrationIdsToSend to set
     */
    public void setRegistrationIdsToSend(String registrationIdsToSend) {
        this.registrationIdsToSend = registrationIdsToSend;
    }

	
   
}


