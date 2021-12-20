package Persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.PaqueteTuristico;
import Logica.ServicioTuristico;
import java.util.ArrayList;
import java.util.List;
import Logica.Venta;
import Persistencia.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ServicioTuristicoJpaController implements Serializable {

    public ServicioTuristicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
        
    }
    
    public ServicioTuristicoJpaController(){
    emf = Persistence.createEntityManagerFactory("TPFinalPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ServicioTuristico servicioTuristico) {
        if (servicioTuristico.getLista_paquetes() == null) {
            servicioTuristico.setLista_paquetes(new ArrayList<PaqueteTuristico>());
        }
        if (servicioTuristico.getLista_ventas() == null) {
            servicioTuristico.setLista_ventas(new ArrayList<Venta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PaqueteTuristico> attachedLista_paquetes = new ArrayList<PaqueteTuristico>();
            for (PaqueteTuristico lista_paquetesPaqueteTuristicoToAttach : servicioTuristico.getLista_paquetes()) {
                lista_paquetesPaqueteTuristicoToAttach = em.getReference(lista_paquetesPaqueteTuristicoToAttach.getClass(), lista_paquetesPaqueteTuristicoToAttach.getCodigo_paquete());
                attachedLista_paquetes.add(lista_paquetesPaqueteTuristicoToAttach);
            }
            servicioTuristico.setLista_paquetes(attachedLista_paquetes);
            List<Venta> attachedLista_ventas = new ArrayList<Venta>();
            for (Venta lista_ventasVentaToAttach : servicioTuristico.getLista_ventas()) {
                lista_ventasVentaToAttach = em.getReference(lista_ventasVentaToAttach.getClass(), lista_ventasVentaToAttach.getNum_venta());
                attachedLista_ventas.add(lista_ventasVentaToAttach);
            }
            servicioTuristico.setLista_ventas(attachedLista_ventas);
            em.persist(servicioTuristico);
            for (PaqueteTuristico lista_paquetesPaqueteTuristico : servicioTuristico.getLista_paquetes()) {
                lista_paquetesPaqueteTuristico.getLista_servicios_incluidos().add(servicioTuristico);
                lista_paquetesPaqueteTuristico = em.merge(lista_paquetesPaqueteTuristico);
            }
            for (Venta lista_ventasVenta : servicioTuristico.getLista_ventas()) {
                ServicioTuristico oldServicioOfLista_ventasVenta = lista_ventasVenta.getServicio();
                lista_ventasVenta.setServicio(servicioTuristico);
                lista_ventasVenta = em.merge(lista_ventasVenta);
                if (oldServicioOfLista_ventasVenta != null) {
                    oldServicioOfLista_ventasVenta.getLista_ventas().remove(lista_ventasVenta);
                    oldServicioOfLista_ventasVenta = em.merge(oldServicioOfLista_ventasVenta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ServicioTuristico servicioTuristico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServicioTuristico persistentServicioTuristico = em.find(ServicioTuristico.class, servicioTuristico.getCodigo_servicio());
            List<PaqueteTuristico> lista_paquetesOld = persistentServicioTuristico.getLista_paquetes();
            List<PaqueteTuristico> lista_paquetesNew = servicioTuristico.getLista_paquetes();
            List<Venta> lista_ventasOld = persistentServicioTuristico.getLista_ventas();
            List<Venta> lista_ventasNew = servicioTuristico.getLista_ventas();
            List<PaqueteTuristico> attachedLista_paquetesNew = new ArrayList<PaqueteTuristico>();
            for (PaqueteTuristico lista_paquetesNewPaqueteTuristicoToAttach : lista_paquetesNew) {
                lista_paquetesNewPaqueteTuristicoToAttach = em.getReference(lista_paquetesNewPaqueteTuristicoToAttach.getClass(), lista_paquetesNewPaqueteTuristicoToAttach.getCodigo_paquete());
                attachedLista_paquetesNew.add(lista_paquetesNewPaqueteTuristicoToAttach);
            }
            lista_paquetesNew = attachedLista_paquetesNew;
            servicioTuristico.setLista_paquetes(lista_paquetesNew);
            List<Venta> attachedLista_ventasNew = new ArrayList<Venta>();
            for (Venta lista_ventasNewVentaToAttach : lista_ventasNew) {
                lista_ventasNewVentaToAttach = em.getReference(lista_ventasNewVentaToAttach.getClass(), lista_ventasNewVentaToAttach.getNum_venta());
                attachedLista_ventasNew.add(lista_ventasNewVentaToAttach);
            }
            lista_ventasNew = attachedLista_ventasNew;
            servicioTuristico.setLista_ventas(lista_ventasNew);
            servicioTuristico = em.merge(servicioTuristico);
            for (PaqueteTuristico lista_paquetesOldPaqueteTuristico : lista_paquetesOld) {
                if (!lista_paquetesNew.contains(lista_paquetesOldPaqueteTuristico)) {
                    lista_paquetesOldPaqueteTuristico.getLista_servicios_incluidos().remove(servicioTuristico);
                    lista_paquetesOldPaqueteTuristico = em.merge(lista_paquetesOldPaqueteTuristico);
                }
            }
            for (PaqueteTuristico lista_paquetesNewPaqueteTuristico : lista_paquetesNew) {
                if (!lista_paquetesOld.contains(lista_paquetesNewPaqueteTuristico)) {
                    lista_paquetesNewPaqueteTuristico.getLista_servicios_incluidos().add(servicioTuristico);
                    lista_paquetesNewPaqueteTuristico = em.merge(lista_paquetesNewPaqueteTuristico);
                }
            }
            for (Venta lista_ventasOldVenta : lista_ventasOld) {
                if (!lista_ventasNew.contains(lista_ventasOldVenta)) {
                    lista_ventasOldVenta.setServicio(null);
                    lista_ventasOldVenta = em.merge(lista_ventasOldVenta);
                }
            }
            for (Venta lista_ventasNewVenta : lista_ventasNew) {
                if (!lista_ventasOld.contains(lista_ventasNewVenta)) {
                    ServicioTuristico oldServicioOfLista_ventasNewVenta = lista_ventasNewVenta.getServicio();
                    lista_ventasNewVenta.setServicio(servicioTuristico);
                    lista_ventasNewVenta = em.merge(lista_ventasNewVenta);
                    if (oldServicioOfLista_ventasNewVenta != null && !oldServicioOfLista_ventasNewVenta.equals(servicioTuristico)) {
                        oldServicioOfLista_ventasNewVenta.getLista_ventas().remove(lista_ventasNewVenta);
                        oldServicioOfLista_ventasNewVenta = em.merge(oldServicioOfLista_ventasNewVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = servicioTuristico.getCodigo_servicio();
                if (findServicioTuristico(id) == null) {
                    throw new NonexistentEntityException("The servicioTuristico with id " + id + " no longer exists.");
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
            ServicioTuristico servicioTuristico;
            try {
                servicioTuristico = em.getReference(ServicioTuristico.class, id);
                servicioTuristico.getCodigo_servicio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicioTuristico with id " + id + " no longer exists.", enfe);
            }
            List<PaqueteTuristico> lista_paquetes = servicioTuristico.getLista_paquetes();
            for (PaqueteTuristico lista_paquetesPaqueteTuristico : lista_paquetes) {
                lista_paquetesPaqueteTuristico.getLista_servicios_incluidos().remove(servicioTuristico);
                lista_paquetesPaqueteTuristico = em.merge(lista_paquetesPaqueteTuristico);
            }
            List<Venta> lista_ventas = servicioTuristico.getLista_ventas();
            for (Venta lista_ventasVenta : lista_ventas) {
                lista_ventasVenta.setServicio(null);
                lista_ventasVenta = em.merge(lista_ventasVenta);
            }
            em.remove(servicioTuristico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ServicioTuristico> findServicioTuristicoEntities() {
        return findServicioTuristicoEntities(true, -1, -1);
    }

    public List<ServicioTuristico> findServicioTuristicoEntities(int maxResults, int firstResult) {
        return findServicioTuristicoEntities(false, maxResults, firstResult);
    }

    private List<ServicioTuristico> findServicioTuristicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ServicioTuristico.class));
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

    public ServicioTuristico findServicioTuristico(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ServicioTuristico.class, id);
        } finally {
            em.close();
        }
    }

    public int getServicioTuristicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ServicioTuristico> rt = cq.from(ServicioTuristico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
