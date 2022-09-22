package pl.edu.wat.projektai.ai.exceptions;

public class VehicleIsAlreadyRented extends RuntimeException {

    public VehicleIsAlreadyRented(String msg){
        super(msg);
    }
}
