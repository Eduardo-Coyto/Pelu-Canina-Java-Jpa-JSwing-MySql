
package com.mycompany.peluqueriacanina.Logica;

import com.mycompany.peluqueriacanina.Persistencia.ControladoraPersistencia;
import java.util.List;

public class ControladoraLogica {
    
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public void guardarEnLogica(String nombreMasco, String raza, String color, String observaciones, String nombreDuenio, String celDuenio, String alergico, String atenEsp) {
                
        //Creamos el dueño y sasignamos sus valores
        Duenio duenio = new Duenio();
        //utilizo los métodos de getters & setters para cargar en las clases correspondientes
        duenio.setNombre(nombreDuenio);
        duenio.setCelDuenio(celDuenio);
        
        
        //Creamos la mascota y sasignamos sus valores
        Mascota masco = new Mascota();
        masco.setNombre(nombreMasco);
        masco.setRaza(raza);
        masco.setColor(color);
        masco.setAlergico(alergico);
        masco.setAtencion_especial(atenEsp);
        masco.setObservaciones(observaciones);
        
        masco.setUnDuenio(duenio);
        
        controlPersis.guardarEnPersistencia(duenio, masco);
        
    }

    public List<Mascota> traerMascotasLogica() {
        
        return controlPersis.traerMascotasPersistencia();
    }

    public void borrarMascotaLogica(int num_cliente) {
        controlPersis.borrarMascotaPersistencia(num_cliente);
    }

    public Mascota traerMascotaLogica(int num_cliente) {
        return controlPersis.traerMascotaPersistencia(num_cliente);
    }

    public void modificarMascotaLogica(Mascota masco, String nombreMasco, String raza, String color, String observaciones, String alergico, String atenEsp, String nombreDuenio, String celDuenio) {
        
        /*
        Acá la lógica tiene el trabajo de asignarle a masco los nuevos valores que trae por parámetros.
        Debemos recordar que masco es una variable global en clase ModificarDatos y que obtuvo todos los datos del método cargarDatos, por lo tanto son los valores 
        anteriores que tenía la BD.
        Lo que debemos hacer es a masco le seteamos los parámetros del método modificarMascotaLogica
        Como no podemos modificar los datos del dueño utilizo la fución buscarDuenio para buscar el duenio segun el Id
        Dado que obtuve los datos del dueño creo un método que pueda ser reutilizado en caso de querer modificar los datos en otra funcionaldiad.
        */
        
        masco.setNombre(nombreMasco);
        masco.setRaza(raza);
        masco.setColor(color);
        masco.setObservaciones(observaciones);
        masco.setAlergico(alergico);
        masco.setAtencion_especial(atenEsp);
        
        //modifico masco
        controlPersis.modificarMascotaPersistencia(masco);
        
        //obtengo el dueño a través del método buscarDuenio
        Duenio duenio = this.buscarDuenio(masco.getUnDuenio().getId_duenio());
        duenio.setNombre(nombreDuenio);
        duenio.setCelDuenio(celDuenio);
        
        //modifico duenio a través de un método para que pueda ser reutilizado por otra funcionalidad.
        this.modificarDuenio(duenio);
        
    }

    //con este método obtengo al dueño según su id
    private Duenio buscarDuenio(int id_duenio) {
        return controlPersis.traerDuenioPersistencia(id_duenio);
    }

    private void modificarDuenio(Duenio duenio) {
        controlPersis.modificarDuenioPersistencia(duenio);
    }
    
    
    
}
