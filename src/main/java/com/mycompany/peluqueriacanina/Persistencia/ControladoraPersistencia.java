package com.mycompany.peluqueriacanina.Persistencia;

import com.mycompany.peluqueriacanina.Logica.Duenio;
import com.mycompany.peluqueriacanina.Logica.Mascota;
import com.mycompany.peluqueriacanina.Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {

    DuenioJpaController duenioJpa = new DuenioJpaController();
    MascotaJpaController mascoJpa = new MascotaJpaController();

    public void guardarEnPersistencia(Duenio duenio, Mascota masco) {

        /*
        Cuando vamos a crear una mascota, ya debe estar creado el dueño
         */
        //crear en la BD el Dueño
        duenioJpa.create(duenio);

        //crear en la BD la mascota
        mascoJpa.create(masco);

    }

    public List<Mascota> traerMascotasPersistencia() {
        return mascoJpa.findMascotaEntities();
    }

    public void borrarMascotaPersistencia(int num_cliente) {
        try {
            mascoJpa.destroy(num_cliente);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Mascota traerMascotaPersistencia(int num_cliente) {
        return mascoJpa.findMascota(num_cliente);
    }

    public void modificarMascotaPersistencia(Mascota masco) {
        try {
            mascoJpa.edit(masco);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Duenio traerDuenioPersistencia(int id_duenio) {
        return duenioJpa.findDuenio(id_duenio);
    }

    public void modificarDuenioPersistencia(Duenio duenio) {
        try {
            duenioJpa.edit(duenio);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
