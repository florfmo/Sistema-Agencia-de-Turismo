package Persistencia;

import Logica.PaqueteTuristico;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.ServicioTuristico;
import java.util.ArrayList;
import java.util.List;
import Logica.Venta;
import Persistencia.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class PaqueteTuristicoJpaController implements Serializable {

    public PaqueteTuristicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public PaqueteTuristicoJpaController(){
    emf = Persistence.createEntityManagerFactory("TPFinalPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PaqueteTuristico paqueteTuristico) {
        if (paqueteTuristico.getLista_servicios_incluidos() == null) {
            paqueteTuristico.setLista_servicios_incluidos(new ArrayList<ServicioTuristico>());
        }
        if (paqueteTuristico.getLista_ventas() == null) {
            paqueteTuristico.setLista_ventas(new ArrayList<Venta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ServicioTuristico> attachedLista_servicios_incluidos = new ArrayList<ServicioTuristico>();
            for (ServicioTuristico lista_servicios_incluidosServicioTuristicoToAttach : paqueteTuristico.getLista_servicios_incluidos()) {
                lista_servicios_incluidosServicioTuristicoToAttach = em.getReference(lista_servicios_incluidosServicioTuristicoToAttach.getClass(), lista_servicios_incluidosServicioTuristicoToAttach.getCodigo_servicio());
                attachedLista_servicios_incluidos.add(lista_servicios_incluidosServicioTuristicoToAttach);
            }
            paqueteTuristico.setLista_servicios_incluidos(attachedLista_servicios_incluidos);
            List<Venta> attachedLista_ventas = new ArrayList<Venta>();
            for (Venta lista_ventasVentaToAttach : paqueteTuristico.getLista_ventas()) {
                lista_ventasVentaToAttach = em.getReference(lista_ventasVentaToAttach.getClass(), lista_ventasVentaToAttach.getNum_venta());
                attachedLista_ventas.add(lista_ventasVentaToAttach);
            }
            paqueteTuristico.setLista_ventas(attachedLista_ventas);
            em.persist(paqueteTuristico);
            for (ServicioTuristico lista_servicios_incluidosServicioTuristico : paqueteTuristico.getLista_servicios_incluidos()) {
                lista_servicios_incluidosServicioTuristico.getLista_paquetes().add(paqueteTuristico);
                lista_servicios_incluidosServicioTuristico = em.merge(lista_servicios_incluidosServicioTuristico);
            }
            for (Venta lista_ventasVenta : paqueteTuristico.getLista_ventas()) {
                PaqueteTuristico oldPaqueteOfLista_ventasVenta = lista_ventasVenta.getPaquete();
                lista_ventasVenta.setPaquete(paqueteTuristico);
                lista_ventasVenta = em.merge(lista_ventasVenta);
                if (oldPaqueteOfLista_ventasVenta != null) {
                    oldPaqueteOfLista_ventasVenta.getLista_ventas().remove(lista_ventasVenta);
                    oldPaqueteOfLista_ventasVenta = em.merge(oldPaqueteOfLista_ventasVenta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PaqueteTuristico paqueteTuristico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PaqueteTuristico persistentPaqueteTuristico = em.find(PaqueteTuristico.class, paqueteTuristico.getCodigo_paquete());
            List<ServicioTuristico> lista_servicios_incluidosOld = persistentPaqueteTuristico.getLista_servicios_incluidos();
            List<ServicioTuristico> lista_servicios_incluidosNew = paqueteTuristico.getLista_servicios_incluidos();
            List<Venta> lista_ventasOld = persistentPaqueteTuristico.getLista_ventas();
            List<Venta> lista_ventasNew = paqueteTuristico.getLista_ventas();
            List<ServicioTuristico> attachedLista_servicios_incluidosNew = new ArrayList<ServicioTuristico>();
            for (ServicioTuristico lista_servicios_incluidosNewServicioTuristicoToAttach : lista_servicios_incluidosNew) {
                lista_servicios_incluidosNewServicioTuristicoToAttach = em.getReference(lista_servicios_incluidosNewServicioTuristicoToAttach.getClass(), lista_servicios_incluidosNewServicioTuristicoToAttach.getCodigo_servicio());
                attachedLista_servicios_incluidosNew.add(lista_servicios_incluidosNewServicioTuristicoToAttach);
            }
            lista_servicios_incluidosNew = attachedLista_servicios_incluidosNew;
            paqueteTuristico.setLista_servicios_incluidos(lista_servicios_incluidosNew);
            List<Venta> attachedLista_ventasNew = new ArrayList<Venta>();
            for (Venta lista_ventasNewVentaToAttach : lista_ventasNew) {
                lista_ventasNewVentaToAttach = em.getReference(lista_ventasNewVentaToAttach.getClass(), lista_ventasNewVentaToAttach.getNum_venta());
                attachedLista_ventasNew.add(lista_ventasNewVentaToAttach);
            }
            lista_ventasNew = attachedLista_ventasNew;
            paqueteTuristico.setLista_ventas(lista_ventasNew);
            paqueteTuristico = em.merge(paqueteTuristico);
            for (ServicioTuristico lista_servicios_incluidosOldServicioTuristico : lista_servicios_incluidosOld) {
                if (!lista_servicios_incluidosNew.contains(lista_servicios_incluidosOldServicioTuristico)) {
                    lista_servicios_incluidosOldServicioTuristico.getLista_paquetes().remove(paqueteTuristico);
                    lista_servicios_incluidosOldServicioTuristico = em.merge(lista_servicios_incluidosOldServicioTuristico);
                }
            }
            for (ServicioTuristico lista_servicios_incluidosNewServicioTuristico : lista_servicios_incluidosNew) {
                if (!lista_servicios_incluidosOld.contains(lista_servicios_incluidosNewServicioTuristico)) {
                    lista_servicios_incluidosNewServicioTuristico.getLista_paquetes().add(paqueteTuristico);
                    lista_servicios_incluidosNewServicioTuristico = em.merge(lista_servicios_incluidosNewServicioTuristico);
                }
            }
            for (Venta lista_ventasOldVenta : lista_ventasOld) {
                if (!lista_ventasNew.contains(lista_ventasOldVenta)) {
                    lista_ventasOldVenta.setPaquete(null);
                    lista_ventasOldVenta = em.merge(lista_ventasOldVenta);
                }
            }
            for (Venta lista_ventasNewVenta : lista_ventasNew) {
                if (!lista_ventasOld.contains(lista_ventasNewVenta)) {
                    PaqueteTuristico oldPaqueteOfLista_ventasNewVenta = lista_ventasNewVenta.getPaquete();
                    lista_ventasNewVenta.setPaquete(paqueteTuristico);
                    lista_ventasNewVenta = em.merge(lista_ventasNewVenta);
                    if (oldPaqueteOfLista_ventasNewVenta != null && !oldPaqueteOfLista_ventasNewVenta.equals(paqueteTuristico)) {
                        oldPaqueteOfLista_ventasNewVenta.getLista_ventas().remove(lista_ventasNewVenta);
                        oldPaqueteOfLista_ventasNewVenta = em.merge(oldPaqueteOfLista_ventasNewVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = paqueteTuristico.getCodigo_paquete();
                if (findPaqueteTuristico(id) == null) {
                    throw new NonexistentEntityException("The paqueteTuristico with id " + id + " no longer exists.");
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
            PaqueteTuristico paqueteTuristico;
            try {
                paqueteTuristico = em.getReference(PaqueteTuristico.class, id);
                paqueteTuristico.getCodigo_paquete();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paqueteTuristico with id " + id + " no longer exists.", enfe);
            }
            List<ServicioTuristico> lista_servicios_incluidos = paqueteTuristico.getLista_servicios_incluidos();
            for (ServicioTuristico lista_servicios_incluidosServicioTuristico : lista_servicios_incluidos) {
                lista_servicios_incluidosServicioTuristico.getLista_paquetes().remove(paqueteTuristico);
                lista_servicios_incluidosServicioTuristico = em.merge(lista_servicios_incluidosServicioTuristico);
            }
            List<Venta> lista_ventas = paqueteTuristico.getLista_ventas();
            for (Venta lista_ventasVenta : lista_ventas) {
                lista_ventasVenta.setPaquete(null);
                lista_ventasVenta = em.merge(lista_ventasVenta);
            }
            em.remove(paqueteTuristico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PaqueteTuristico> findPaqueteTuristicoEntities() {
        return findPaqueteTuristicoEntities(true, -1, -1);
    }

    public List<PaqueteTuristico> findPaqueteTuristicoEntities(int maxResults, int firstResult) {
        return findPaqueteTuristicoEntities(false, maxResults, firstResult);
    }

    private List<PaqueteTuristico> findPaqueteTuristicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PaqueteTuristico.class));
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

    public PaqueteTuristico findPaqueteTuristico(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PaqueteTuristico.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaqueteTuristicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PaqueteTuristico> rt = cq.from(PaqueteTuristico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
