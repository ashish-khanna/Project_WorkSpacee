package projectDAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import projectEntity.*;

public class VehicleShiftMappingDAO {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("CS5200DBMS");
	
	public void createNewMapping(String shift, String regno){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		Vehicle vehicle = em.find(Vehicle.class, regno);
		
		VehicleShiftMapping vehicleShiftMapping = new VehicleShiftMapping();
		vehicleShiftMapping.setShift(shift);
		vehicleShiftMapping.setVehicle(vehicle);
		
		em.persist(vehicleShiftMapping);
		
		vehicle.getVehicleShiftMappings().add(vehicleShiftMapping);
		em.merge(vehicle);
		
	    em.getTransaction().commit();
		em.close();
	}
	
	public List<Vehicle> getVehiclesForShift(String shift){
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		List<VehicleShiftMapping> vsm = new ArrayList<>();
		
		Query query = em.createQuery("select vsm from VehicleShiftMapping vsm where vsm.shift = :vsmshift");
		query.setParameter("vsmshift", shift);
		vsm = query.getResultList();
		
		for (VehicleShiftMapping vehicleShift : vsm) {
			vehicles.add(vehicleShift.getVehicle());
		}
		
	    em.getTransaction().commit();
		em.close();
		
		return vehicles;
	}

	public static void main(String[] args) {
		VehicleShiftMappingDAO vehicleMappingDAO = new VehicleShiftMappingDAO();
		
		vehicleMappingDAO.createNewMapping("1900", "MA125");

	}

}
