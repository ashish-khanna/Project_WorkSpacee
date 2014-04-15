package projectDAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import projectEntity.*;

@Path("/vehicleshiftmapping")
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
	
	@GET
	@Path("/vsm/{shift}")
	@Produces(MediaType.APPLICATION_JSON)
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
		
		//vehicleMappingDAO.createNewMapping("1900", "MA125");
		
		List<Vehicle> vl = vehicleMappingDAO.getVehiclesForShift("1800");
		
		for(Vehicle v : vl){
			System.out.println(v.getCapacity());
		}
		

	}

}
