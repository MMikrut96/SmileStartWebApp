import {Component, OnInit} from '@angular/core';
import {VehicleServiceService} from '../../services/vehicle-service.service';
import {Vehicle} from '../../models/vehicle';
import {Router} from '@angular/router';

@Component({
  selector: 'app-add-vehicle',
  templateUrl: './add-vehicle.component.html',
  styleUrls: ['./add-vehicle.component.css']
})
export class AddVehicleComponent implements OnInit {

  inputBrand;
  inputModel;
  inputRegisterNo;
  inputVinNo;

  constructor(private vehicleService: VehicleServiceService, private router: Router) {
  }

  ngOnInit() {
  }

  clearForm() {
    this.inputBrand = '';
    this.inputModel = '';
    this.inputRegisterNo = '';
    this.inputVinNo = '';
  }

  addVehicle() {
    const vehicle = new Vehicle();
    vehicle.brand = this.inputBrand;
    vehicle.model = this.inputModel;
    vehicle.registerNo = this.inputRegisterNo;
    vehicle.vin = this.inputVinNo;
    vehicle.repair = false;
    this.vehicleService.addVehicle(vehicle).subscribe((data) => {
      alert('Poprawnie dodano pojazd ' + vehicle.brand + ' ' + vehicle.model);
      this.router.navigateByUrl('vehicle-list');
    });
  }

}
