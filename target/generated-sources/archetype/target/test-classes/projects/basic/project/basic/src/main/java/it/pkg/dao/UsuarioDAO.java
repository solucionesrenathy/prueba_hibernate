package it.pkg.dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tono2
 */

import it.pkg.entidades.Usuario;
import it.pkg.util.HibernateUtil;
import it.pkg.util.HashUtil;
import org.hibernate.Session;

public class UsuarioDAO {

    /**
     * Busca un usuario ACTIVO con ese correo y clave.
     * @return el Usuario encontrado o null si las credenciales no coinciden
     */
    public Usuario autenticar(String correo, String clave) {
        String hql = "FROM Usuario u "
                   + "WHERE u.Correo = :correo AND u.Clave = :clave AND u.Estado = 'ACTIVO'";

        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            return sesion.createQuery(hql, Usuario.class)
                    .setParameter("correo", correo)
                    .setParameter("clave", HashUtil.md5(clave))
                    .uniqueResultOptional()
                    .orElse(null);
        }
    }
}
