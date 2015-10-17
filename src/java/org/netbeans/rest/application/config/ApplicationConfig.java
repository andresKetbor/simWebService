/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;
import org.sim.services.resources.UsuarioResource;

/**
 *
 * @author Andres
 */
@javax.ws.rs.ApplicationPath("resources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(org.sim.services.resources.AlertaResource.class);
        resources.add(org.sim.services.resources.EcgResource.class);
        resources.add(org.sim.services.resources.LibroReportResource.class);
        resources.add(org.sim.services.resources.NotaResource.class);
        resources.add(org.sim.services.resources.PacienteResource.class);
        resources.add(org.sim.services.resources.UsuarioResource.class);
        resources.add(org.sim.services.resources.ValoresFrecuenciaRespiratoria.class);
        resources.add(org.sim.services.resources.ValoresTemperaturaResource.class);
        resources.add(org.sim.services.resources.ValoresTensionArterialResource.class);
    }
    
}
