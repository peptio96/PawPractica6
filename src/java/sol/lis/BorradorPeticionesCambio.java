/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.lis;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import paw.bd.ConnectionManager;

/**
 * Web application lifecycle listener.
 *
 * @author alruiz_o
 */
public class BorradorPeticionesCambio implements ServletContextListener {
    private ScheduledExecutorService planificador;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        planificador = Executors.newSingleThreadScheduledExecutor();
        planificador.scheduleAtFixedRate(new Runnable() {
            public void run() {
                borra();// el código de borrado que debes desarrollar tu
            }
        }, 0, 5, TimeUnit.MINUTES);

    }
    private void borra(){
        Connection con = null;
        Statement stm = null;
        try {
            con = ConnectionManager.getConnection();
            stm = con.createStatement();
            int filasAfectadas = stm.executeUpdate("DELETE FROM recuerdocontrasenia WHERE fecha < DATE_ADD(now(), INTERVAL '-5' MINUTE)");
            Logger.getLogger(BorradorPeticionesCambio.class.getName()).log(Level.SEVERE, "El numero de filas borradas es: " +filasAfectadas, filasAfectadas);
        } catch (SQLException ex) {
            Logger.getLogger(BorradorPeticionesCambio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        planificador.shutdownNow();
    }
}