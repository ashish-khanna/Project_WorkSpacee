package projectDAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import projectEntity.*;

public class VehicleDAO {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("CS5200DBMS");
	
	public void createVehicle(Vehicle newVehicle){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		em.persist(newVehicle);
		
	    em.getTransaction().commit();
		em.close();
	}
	
	public List<VehicleShiftMapping> getMappingForVehicle(String regno){
		List<VehicleShiftMapping> vehicleShiftMappings = new ArrayList<VehicleShiftMapping>();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		Vehicle vehicle = em.find(Vehicle.class, regno);
		
		vehicleShiftMappings = vehicle.getVehicleShiftMappings();
		
		em.getTransaction().commit();
		em.close();		
		
		return vehicleShiftMappings;
	}

	public static void main(String[] args) {
		VehicleDAO vehicleDAO = new VehicleDAO();
		Vehicle newVehicle = new Vehicle();
		List<VehicleShiftMapping> vehicleShiftMappings = new ArrayList<VehicleShiftMapping>();
		
		vehicleShiftMappings = vehicleDAO.getMappingForVehicle("MA125");
		
		for(VehicleShiftMapping v : vehicleShiftMappings){
			System.out.println(v.getShift());
		}
		
		
		newVehicle.setRegNo("MA125");
		newVehicle.setDriverName("John");
		newVehicle.setCapacity(5);
		
		//vehicleDAO.createVehicle(newVehicle);
		
		newVehicle.setRegNo("MA500");
		newVehicle.setDriverName("Mike");
		newVehicle.setCapacity(5);
		
		//vehicleDAO.createVehicle(newVehicle);
		
		newVehicle.setRegNo("MA700");
		newVehicle.setDriverName("Nik");
		newVehicle.setCapacity(5);
		
		//vehicleDAO.createVehicle(newVehicle);
		
	}

}
