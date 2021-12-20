package Persistencia;

import Logica.Empleado;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Venta;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmpleadoJpaController implements Serializable {

    public EmpleadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public EmpleadoJpaController(){
    emf = Persistence.createEntityManagerFactory("TPFinalPU");
    }
        
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleado empleado) {
        if (empleado.getLista_ventas() == null) {
            empleado.setLista_ventas(new ArrayList<Venta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Venta> attachedLista_ventas = new ArrayList<Venta>();
            for (Venta lista_ventasVentaToAttach : empleado.getLista_ventas()) {
                lista_ventasVentaToAttach = em.getReference(lista_ventasVentaToAttach.getClass(), lista_ventasVentaToAttach.getNum_venta());
                attachedLista_ventas.add(lista_ventasVentaToAttach);
            }
            empleado.setLista_ventas(attachedLista_ventas);
            em.persist(empleado);
            for (Venta lista_ventasVenta : empleado.getLista_ventas()) {
                Empleado oldempleadoOfLista_ventasVenta = lista_ventasVenta.getEmpleado();
                lista_ventasVenta.setEmpleado(empleado);
                lista_ventasVenta = em.merge(lista_ventasVenta);
                if (oldempleadoOfLista_ventasVenta != null) {
                    oldempleadoOfLista_ventasVenta.getLista_ventas().remove(lista_ventasVenta);
                    oldempleadoOfLista_ventasVenta = em.merge(oldempleadoOfLista_ventasVenta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleado empleado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado persistentEmpleado = em.find(Empleado.class, empleado.getId());
            List<Venta> lista_ventasOld = persistentEmpleado.getLista_ventas();
            List<Venta> lista_ventasNew = empleado.getLista_ventas();
            List<Venta> attachedLista_ventasNew = new ArrayList<Venta>();
            for (Venta lista_ventasNewVentaToAttach : lista_ventasNew) {
                lista_ventasNewVentaToAttach = em.getReference(lista_ventasNewVentaToAttach.getClass(), lista_ventasNewVentaToAttach.getNum_venta());
                attachedLista_ventasNew.add(lista_ventasNewVentaToAttach);
            }
            lista_ventasNew = attachedLista_ventasNew;
            empleado.setLista_ventas(lista_ventasNew);
            empleado = em.merge(empleado);
            for (Venta lista_ventasOldVenta : lista_ventasOld) {
                if (!lista_ventasNew.contains(lista_ventasOldVenta)) {
                    lista_ventasOldVenta.setEmpleado(null);
                    lista_ventasOldVenta = em.merge(lista_ventasOldVenta);
                }
            }
            for (Venta lista_ventasNewVenta : lista_ventasNew) {
                if (!lista_ventasOld.contains(lista_ventasNewVenta)) {
                    Empleado oldempleadoOfLista_ventasNewVenta = lista_ventasNewVenta.getEmpleado();
                    lista_ventasNewVenta.setEmpleado(empleado);
                    lista_ventasNewVenta = em.merge(lista_ventasNewVenta);
                    if (oldempleadoOfLista_ventasNewVenta != null && !oldempleadoOfLista_ventasNewVenta.equals(empleado)) {
                        oldempleadoOfLista_ventasNewVenta.getLista_ventas().remove(lista_ventasNewVenta);
                        oldempleadoOfLista_ventasNewVenta = em.merge(oldempleadoOfLista_ventasNewVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = empleado.getId();
                if (findEmpleado(id) == null) {
                    throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.");
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
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, id);
                empleado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            List<Venta> lista_ventas = empleado.getLista_ventas();
            for (Venta lista_ventasVenta : lista_ventas) {
                lista_ventasVenta.setEmpleado(null);
                lista_ventasVenta = em.merge(lista_ventasVenta);
            }
            em.remove(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleado> findEmpleadoEntities() {
        return findEmpleadoEntities(true, -1, -1);
    }

    public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
        return findEmpleadoEntities(false, maxResults, firstResult);
    }

    private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleado.class));
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

    public Empleado findEmpleado(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleado> rt = cq.from(Empleado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
