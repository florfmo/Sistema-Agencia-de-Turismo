package Persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Cliente;
import Logica.ServicioTuristico;
import Logica.PaqueteTuristico;
import Logica.Empleado;
import Logica.Venta;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class VentaJpaController implements Serializable {

    public VentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public VentaJpaController(){
    emf = Persistence.createEntityManagerFactory("TPFinalPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Venta venta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente = venta.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getId());
                venta.setCliente(cliente);
            }
            ServicioTuristico servicio = venta.getServicio();
            if (servicio != null) {
                servicio = em.getReference(servicio.getClass(), servicio.getCodigo_servicio());
                venta.setServicio(servicio);
            }
            PaqueteTuristico paquete = venta.getPaquete();
            if (paquete != null) {
                paquete = em.getReference(paquete.getClass(), paquete.getCodigo_paquete());
                venta.setPaquete(paquete);
            }
            Empleado nombre_empleado = venta.getEmpleado();
            if (nombre_empleado != null) {
                nombre_empleado = em.getReference(nombre_empleado.getClass(), nombre_empleado.getId());
                venta.setEmpleado(nombre_empleado);
            }
            em.persist(venta);
            if (cliente != null) {
                cliente.getLista_ventas().add(venta);
                cliente = em.merge(cliente);
            }
            if (servicio != null) {
                servicio.getLista_ventas().add(venta);
                servicio = em.merge(servicio);
            }
            if (paquete != null) {
                paquete.getLista_ventas().add(venta);
                paquete = em.merge(paquete);
            }
            if (nombre_empleado != null) {
                nombre_empleado.getLista_ventas().add(venta);
                nombre_empleado = em.merge(nombre_empleado);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Venta venta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venta persistentVenta = em.find(Venta.class, venta.getNum_venta());
            Cliente clienteOld = persistentVenta.getCliente();
            Cliente clienteNew = venta.getCliente();
            ServicioTuristico servicioOld = persistentVenta.getServicio();
            ServicioTuristico servicioNew = venta.getServicio();
            PaqueteTuristico paqueteOld = persistentVenta.getPaquete();
            PaqueteTuristico paqueteNew = venta.getPaquete();
            Empleado nombre_empleadoOld = persistentVenta.getEmpleado();
            Empleado nombre_empleadoNew = venta.getEmpleado();
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getId());
                venta.setCliente(clienteNew);
            }
            if (servicioNew != null) {
                servicioNew = em.getReference(servicioNew.getClass(), servicioNew.getCodigo_servicio());
                venta.setServicio(servicioNew);
            }
            if (paqueteNew != null) {
                paqueteNew = em.getReference(paqueteNew.getClass(), paqueteNew.getCodigo_paquete());
                venta.setPaquete(paqueteNew);
            }
            if (nombre_empleadoNew != null) {
                nombre_empleadoNew = em.getReference(nombre_empleadoNew.getClass(), nombre_empleadoNew.getId());
                venta.setEmpleado(nombre_empleadoNew);
            }
            venta = em.merge(venta);
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.getLista_ventas().remove(venta);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.getLista_ventas().add(venta);
                clienteNew = em.merge(clienteNew);
            }
            if (servicioOld != null && !servicioOld.equals(servicioNew)) {
                servicioOld.getLista_ventas().remove(venta);
                servicioOld = em.merge(servicioOld);
            }
            if (servicioNew != null && !servicioNew.equals(servicioOld)) {
                servicioNew.getLista_ventas().add(venta);
                servicioNew = em.merge(servicioNew);
            }
            if (paqueteOld != null && !paqueteOld.equals(paqueteNew)) {
                paqueteOld.getLista_ventas().remove(venta);
                paqueteOld = em.merge(paqueteOld);
            }
            if (paqueteNew != null && !paqueteNew.equals(paqueteOld)) {
                paqueteNew.getLista_ventas().add(venta);
                paqueteNew = em.merge(paqueteNew);
            }
            if (nombre_empleadoOld != null && !nombre_empleadoOld.equals(nombre_empleadoNew)) {
                nombre_empleadoOld.getLista_ventas().remove(venta);
                nombre_empleadoOld = em.merge(nombre_empleadoOld);
            }
            if (nombre_empleadoNew != null && !nombre_empleadoNew.equals(nombre_empleadoOld)) {
                nombre_empleadoNew.getLista_ventas().add(venta);
                nombre_empleadoNew = em.merge(nombre_empleadoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = venta.getNum_venta();
                if (findVenta(id) == null) {
                    throw new NonexistentEntityException("The venta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venta venta;
            try {
                venta = em.getReference(Venta.class, id);
                venta.getNum_venta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The venta with id " + id + " no longer exists.", enfe);
            }
            Cliente cliente = venta.getCliente();
            if (cliente != null) {
                cliente.getLista_ventas().remove(venta);
                cliente = em.merge(cliente);
            }
            ServicioTuristico servicio = venta.getServicio();
            if (servicio != null) {
                servicio.getLista_ventas().remove(venta);
                servicio = em.merge(servicio);
            }
            PaqueteTuristico paquete = venta.getPaquete();
            if (paquete != null) {
                paquete.getLista_ventas().remove(venta);
                paquete = em.merge(paquete);
            }
            Empleado nombre_empleado = venta.getEmpleado();
            if (nombre_empleado != null) {
                nombre_empleado.getLista_ventas().remove(venta);
                nombre_empleado = em.merge(nombre_empleado);
            }
            em.remove(venta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Venta> findVentaEntities() {
        return findVentaEntities(true, -1, -1);
    }

    public List<Venta> findVentaEntities(int maxResults, int firstResult) {
        return findVentaEntities(false, maxResults, firstResult);
    }

    private List<Venta> findVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Venta.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Venta findVenta(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Venta.class, id);
        } finally {
            em.close();
        }
    }

    public int getVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Venta> rt = cq.from(Venta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
