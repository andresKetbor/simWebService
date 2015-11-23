package org.sim.services.entities.dtos;
// Generated Aug 22, 2015 1:16:11 PM by Hibernate Tools 4.3.1

import java.util.List;



public class AlertaApiDto extends CommonDto implements java.io.Serializable {


    private Data data;
     private List<String> registration_ids;
          

    public AlertaApiDto() {
    }

    /**
     * @return the to
     */
    

    /**
     * @return the registration_ids
     */
    public List<String> getRegistration_ids() {
        return registration_ids;
    }

    /**
     * @param registration_ids the registration_ids to set
     */
    public void setRegistration_ids(List<String> registration_ids) {
        this.registration_ids = registration_ids;
    }

    /**
     * @return the data
     */
    public Data getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Data data) {
        this.data = data;
    }
 
}
